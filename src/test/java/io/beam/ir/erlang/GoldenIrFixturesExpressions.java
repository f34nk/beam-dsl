package io.beam.ir.erlang;

import java.util.List;

final class GoldenIrFixturesExpressions {

  private GoldenIrFixturesExpressions() {}

  static Expression clientDispatchGetNameExpression() {
    return MatchExpr.bind(
        "Req",
        RemoteCallExpr.of(
            "http_service_rest_json_1", "encode_get_name_request", List.of(Variable.of("Input"))),
        dispatchGetNameCase(Variable.of("Req")));
  }

  static Expression clientDispatchGetNameRetryExpression() {
    Expression inner =
        MatchExpr.bind(
            "Req",
            RemoteCallExpr.of(
                "http_service_rest_json_1",
                "encode_get_name_request",
                List.of(Variable.of("Input"))),
            dispatchGetNameCase(Variable.of("Req")));
    return MatchExpr.bind(
        "RetryOpts",
        RemoteCallExpr.of(
            "maps",
            "get",
            List.of(AtomExpr.of("retry"), Variable.of("Config"), MapExpr.of(List.of()))),
        RemoteCallExpr.of(
            "retry_mod",
            "with_retry",
            List.of(
                Fun.of(List.of(FunClause.of(List.of(), null, inner))), Variable.of("RetryOpts"))));
  }

  static Expression clientDispatchGetNameSigv4Expression() {
    return MatchExpr.bind(
        "Req",
        RemoteCallExpr.of(
            "http_service_rest_json_1", "encode_get_name_request", List.of(Variable.of("Input"))),
        MatchExpr.bind(
            "SignedReq",
            CaseExpr.of(
                RemoteCallExpr.of(
                    "maps",
                    "get",
                    List.of(
                        AtomExpr.of("credentials"),
                        Variable.of("Config"),
                        AtomExpr.of("undefined"))),
                List.of(
                    Clause.of(AtomPattern.of("undefined"), Variable.of("Req")),
                    Clause.of(
                        WildcardPattern.of(),
                        RemoteCallExpr.of(
                            "http_service_sigv4",
                            "sign",
                            List.of(
                                Variable.of("Config"),
                                AtomExpr.of("get_name"),
                                Variable.of("Req")))))),
            dispatchGetNameCase(Variable.of("SignedReq"))));
  }

  static Expression clientDispatchListWidgetsPageExpression() {
    return MatchExpr.bind(
        "Req",
        RemoteCallExpr.of(
            "paginated_service_rest_json_1",
            "encode_list_widgets_request",
            List.of(Variable.of("Input"))),
        CaseExpr.of(
            RemoteCallExpr.of(
                "runtime_http", "dispatch", List.of(Variable.of("Config"), Variable.of("Req"))),
            List.of(
                Clause.of(
                    TuplePattern.of(List.of(AtomPattern.of("ok"), VariablePattern.of("Resp"))),
                    CaseExpr.of(
                        RemoteCallExpr.of(
                            "paginated_service_rest_json_1",
                            "decode_list_widgets_response",
                            List.of(Variable.of("Resp"))),
                        List.of(
                            Clause.of(
                                TuplePattern.of(
                                    List.of(AtomPattern.of("ok"), VariablePattern.of("Output"))),
                                MatchExpr.bind(
                                    "NewAcc",
                                    InfixExpr.of(
                                        Variable.of("Acc"),
                                        "++",
                                        RecordFieldAccessExpr.of(
                                            Variable.of("Output"),
                                            "list_widgets_output",
                                            "widgets")),
                                    CaseExpr.of(
                                        RecordFieldAccessExpr.of(
                                            Variable.of("Output"),
                                            "list_widgets_output",
                                            "next_token"),
                                        List.of(
                                            Clause.of(
                                                AtomPattern.of("undefined"),
                                                TupleExpr.of(
                                                    List.of(
                                                        AtomExpr.of("ok"), Variable.of("NewAcc")))),
                                            Clause.of(
                                                VariablePattern.of("NextToken"),
                                                MatchExpr.bind(
                                                    "NextInput",
                                                    RecordExpr.update(
                                                        Variable.of("Input"),
                                                        "list_widgets_input",
                                                        List.of(
                                                            RecordField.of(
                                                                "next_token",
                                                                Variable.of("NextToken")))),
                                                    LocalCallExpr.of(
                                                        "list_widgets",
                                                        List.of(
                                                            Variable.of("Config"),
                                                            Variable.of("NextInput"),
                                                            Variable.of("NewAcc"))))))))),
                            Clause.of(
                                TuplePattern.of(
                                    List.of(AtomPattern.of("error"), VariablePattern.of("Reason"))),
                                TupleExpr.of(
                                    List.of(AtomExpr.of("error"), Variable.of("Reason"))))))),
                Clause.of(
                    TuplePattern.of(List.of(AtomPattern.of("error"), VariablePattern.of("Reason"))),
                    TupleExpr.of(List.of(AtomExpr.of("error"), Variable.of("Reason")))))));
  }

  static Expression httpChecksumResponseGuardExpression() {
    return CaseExpr.of(
        LocalCallExpr.of(
            "validate_response_checksum",
            List.of(
                Variable.of("Body"),
                Variable.of("Headers"),
                ListExpr.of(
                    List.of(
                        BinaryExpr.of("x-amz-checksum-crc32c"),
                        BinaryExpr.of("x-amz-checksum-sha256"))))),
        List.of(
            Clause.of(
                AtomPattern.of("ok"),
                TupleExpr.of(List.of(AtomExpr.of("ok"), Variable.of("Output")))),
            Clause.of(
                TuplePattern.of(List.of(AtomPattern.of("error"), VariablePattern.of("Reason"))),
                TupleExpr.of(
                    List.of(
                        AtomExpr.of("error"),
                        TupleExpr.of(
                            List.of(
                                AtomExpr.of("checksum_validation_failed"),
                                Variable.of("Reason"))))))));
  }

  static Expression jsonBodyMapEntriesBasicItemExpression() {
    return MapEntriesExpr.of(
        List.of(
            MapEntry.of(BinaryExpr.of("name"), Variable.of("Name")),
            MapEntry.of(BinaryExpr.of("count"), Variable.of("Count"))));
  }

  static Expression jsonDecodedBodyPreludeExpression() {
    return GoldenIrFixturesVerbatim.expression("json_decoded_body_prelude");
  }

  private static CaseExpr dispatchGetNameCase(Expression request) {
    return CaseExpr.of(
        RemoteCallExpr.of("runtime_http", "dispatch", List.of(Variable.of("Config"), request)),
        List.of(
            Clause.of(
                TuplePattern.of(List.of(AtomPattern.of("ok"), VariablePattern.of("Resp"))),
                RemoteCallExpr.of(
                    "http_service_rest_json_1",
                    "decode_get_name_response",
                    List.of(Variable.of("Resp")))),
            Clause.of(
                TuplePattern.of(List.of(AtomPattern.of("error"), VariablePattern.of("Reason"))),
                TupleExpr.of(List.of(AtomExpr.of("error"), Variable.of("Reason"))))));
  }
}
