defmodule SyntaxFixture do
  @moduledoc "Syntax coverage fixture for Elixir IR rendering."

  alias SyntaxTypes.Item, as: Item
  alias SyntaxTypes.Request, as: Request
  alias SyntaxTypes.Response, as: Response
  alias SyntaxTypes.TaggedError, as: TaggedError

  @default_handler :default
  @handlers_key :handlers

  @type config :: %{binary() => term()}

  @doc "Decode input with spec and documentation."
  @spec decode(nil | map()) :: Item.t() | nil
  def decode(nil), do: nil

  def decode(map) when is_map(map) do
    %Item{
      name: Map.get(map, "name"),
      count: Map.get(map, "count")
    }
  end

  @spec encode(Item.t() | nil) :: map() | nil
  def encode(nil), do: nil

  def encode(record = %Item{}) do
    %{"name" => record.name, "count" => record.count}
    |> Enum.reject(fn {_k, v} -> is_nil(v) end)
    |> Map.new()
  end

  def no_spec_no_doc(nil), do: nil
  def no_spec_no_doc(value), do: value

  @doc "Return a plain map from a struct."
  @spec with_spec_and_doc(Item.t()) :: map()
  def with_spec_and_doc(record = %Item{}) do
    %{name: record.name, count: record.count}
  end

  @spec with_spec_no_doc(binary()) :: atom() | {:unknown, binary()}
  def with_spec_no_doc("a"), do: :a
  def with_spec_no_doc("b"), do: :b

  def with_spec_no_doc(v) when is_binary(v) do
    {:unknown, v}
  end

  def decode_enum("a"), do: :a
  def decode_enum("b"), do: :b
  def decode_enum(v) when is_binary(v), do: {:unknown, v}
  def decode_enum(nil), do: nil

  def encode_enum(:a), do: "a"
  def encode_enum(:b), do: "b"
  def encode_enum(nil), do: nil

  def decode_union(map) when is_map(map) do
    case Map.to_list(map) do
      [{"left", v}] -> {:left, v}
      [{"right", v}] -> {:right, v}
      [{k, _v}] -> {:unknown, k}
      _ -> nil
    end
  end

  def decode_union(nil), do: nil

  def encode_union({:left, v}), do: %{"left" => v}
  def encode_union({:right, v}), do: %{"right" => v}

  def encode_union({:unknown, k}) when is_binary(k) do
    %{k => nil}
  end

  def encode_union(nil), do: nil

  def dispatch(config, request) do
    handler = Map.get(config, :handler, @default_handler)
    dispatch(handler, config, request)
  end

  def dispatch(handler, _config, %Request{method: method, path: path}) when is_atom(handler) do
    {handler, method, path}
  end

  @spec match_patterns(term()) :: term()
  def match_patterns(nil), do: nil
  def match_patterns(%{"key" => value}), do: {:map, value}
  def match_patterns(%Item{name: name}), do: {:record, name}
  def match_patterns(%TaggedError{}), do: {:error_record, true}
  def match_patterns({:tag, value}), do: {:tuple, value}
  def match_patterns([h | t]) when is_list(t), do: {:list, h, length(t)}
  def match_patterns(<<_::binary>> = bin), do: {:binary, bin}
  def match_patterns(v) when is_function(v, 1), do: v.(:syntax)
  def match_patterns(v) when is_integer(v), do: {:int, v}
  def match_patterns(v) when is_atom(v), do: {:atom, v}

  @spec transform(config(), Request.t()) :: Response.t()
  def transform(config, %Request{path: path, query: query, headers: headers}) do
    base_url =
      case Map.get(config, :base_url) do
        nil ->
          coalesce([
            Map.get(config, :endpoint),
            resolve_host(config)
          ])

        given_url ->
          given_url
      end

    query_str =
      case Map.to_list(query) do
        [] ->
          ""

        pairs ->
          encoded = URI.encode_query(pairs)
          "?" <> encoded
      end

    {scheme, authority} = split_base_url(base_url)
    req_url = scheme <> authority <> path <> query_str

    %Response{
      status: 200,
      headers: headers,
      body: req_url
    }
  end

  @doc "Invoke fun with exponential backoff on retryable errors."
  @spec with_retry((-> term()), map()) :: term()
  def with_retry(fun, opts) do
    max = Map.get(opts, :max_attempts, 3)
    base = Map.get(opts, :base_delay_ms, 100)
    with_retry(fun, max, base, 1)
  end

  defp with_retry(fun, 0, _base, _n), do: fun.()

  defp with_retry(fun, attempts, base, n) do
    case fun.() do
      {:ok, _} = ok ->
        ok

      {:error, _} = err ->
        if retryable?(err) and attempts > 1 do
          Process.sleep(trunc(base * :math.pow(2, n - 1)))
          with_retry(fun, attempts - 1, base, n + 1)
        else
          err
        end
    end
  end

  defp retryable?({:error, %TaggedError{}}), do: true
  defp retryable?(_), do: false

  @spec risky_call((-> term())) :: term() | {:error, term()}
  def risky_call(fun) do
    try do
      fun.()
    catch
      _, reason -> {:error, reason}
    end
  end

  @spec helpers() :: term()
  def helpers do
    result =
      case fetch("x") do
        {:ok, value} -> value
        :error -> :default
      end

    filtered =
      Enum.filter_map(
        [result],
        fn
          v when v != nil -> encode_value(v)
          _ -> :pop
        end
      )

    if length(filtered) > 0 do
      hd(filtered)
    else
      nil
    end
  end

  defp fetch(key) do
    case Map.get(%{}, key) do
      nil -> :error
      v -> {:ok, v}
    end
  end

  defp encode_value(v) when is_boolean(v), do: Atom.to_string(v)
  defp encode_value(v) when is_integer(v), do: Integer.to_string(v)
  defp encode_value(v) when is_float(v), do: Float.to_string(v)
  defp encode_value(v) when is_binary(v), do: v
  defp encode_value(v) when is_atom(v), do: Atom.to_string(v)

  defp coalesce([h | rest]) do
    case h do
      nil -> coalesce(rest)
      "" -> coalesce(rest)
      value -> value
    end
  end

  defp coalesce([]), do: nil

  defp resolve_host(config), do: Map.get(config, :host, "localhost")

  defp split_base_url(""), do: {"", ""}

  defp split_base_url(base_url) do
    case URI.parse(base_url) do
      %URI{scheme: scheme, host: host} = uri when is_binary(host) ->
        port_suffix =
          case uri.port do
            nil -> ""
            port -> ":#{port}"
          end

        {scheme <> "://", host <> port_suffix}

      _ ->
        {"", base_url}
    end
  end

  defp parse_header("[" <> _ = line), do: String.trim(line)
  defp parse_header(line), do: String.trim(line)

  defp flatten_pairs(map) when is_map(map) do
    map
    |> Map.to_list()
    |> Enum.with_index(1)
    |> Enum.flat_map(fn {i, {key, v}} ->
      if is_nil(v) do
        []
      else
        flatten_entry("#{key}.#{i}", v)
      end
    end)
  end

  defp flatten_entry(_key, nil), do: []

  defp flatten_entry(key, value) when is_map(value) do
    [{key, value}]
  end

  defp flatten_entry(key, value), do: [{key, value}]

  defp content_type_matches(headers, expected) do
    case List.keyfind(headers, "content-type", 0) do
      {_, ^expected} ->
        true

      {_, ct} when is_binary(ct) ->
        ct_base(ct) == ct_base(expected)

      _ ->
        false
    end
  end

  defp ct_base(ct) do
    case String.split(ct, ";") do
      [base | _] -> base
      _ -> ct
    end
  end

  defp update_request(req, host), do: %Request{req | host: host}

  defp dispatch_handler(fun, ctx, input, meta) do
    handlers = Map.get(%{}, @handlers_key, %{})

    case Map.get(handlers, fun) do
      handler when is_function(handler, 3) -> handler.(ctx, input, meta)
      _ -> {:error, :not_implemented}
    end
  end

  defp decode_sparse_map(nil), do: nil

  defp decode_sparse_map(map) when is_map(map) do
    Map.new(map, fn
      {k, nil} -> {k, nil}
      {k, v} -> {k, v}
    end)
  end

  defp decode_list(nil), do: nil
  defp decode_list(list) when is_list(list), do: Enum.reject(list, &is_nil/1)

  defp decode_json_body(""), do: %{}

  defp decode_json_body(body) do
    case Jason.decode(body) do
      {:ok, map} when is_map(map) -> map
      _ -> %{}
    end
  end

  defp merge_params(config, params) do
    config_params = config_to_params(config)
    client_params = client_params(config)
    Map.merge(Map.merge(config_params, client_params), params)
  end

  defp config_to_params(config) do
    case Map.get(config, :region) do
      nil -> %{}
      value -> %{"Region" => value}
    end
  end

  defp client_params(config) do
    Map.merge(
      optional_param(config, :region, "Region"),
      optional_param(config, :bucket, "Bucket")
    )
  end

  defp optional_param(config, key, param_key) do
    case Map.get(config, key) do
      nil -> %{}
      value -> %{param_key => value}
    end
  end

  defp prefix_headers_to_list(_prefix, nil), do: []

  defp prefix_headers_to_list(prefix, map) when is_map(map) do
    Enum.map(map, fn {h, v} -> {prefix <> h, to_binary(v)} end)
  end

  defp prefix_headers_from_list(headers, prefix) do
    headers
    |> Enum.filter(fn {name, _} -> byte_size(name) > byte_size(prefix) end)
    |> Enum.filter(fn {name, _} -> binary_part(name, 0, byte_size(prefix)) == prefix end)
    |> Map.new(fn {name, val} ->
      {binary_part(name, byte_size(prefix), byte_size(name) - byte_size(prefix)), val}
    end)
    |> case do
      map when map == %{} -> nil
      map -> map
    end
  end

  defp to_binary(v) when is_binary(v), do: v
  defp to_binary(v) when is_atom(v), do: Atom.to_string(v)
  defp to_binary(v) when is_integer(v), do: Integer.to_string(v)
end
