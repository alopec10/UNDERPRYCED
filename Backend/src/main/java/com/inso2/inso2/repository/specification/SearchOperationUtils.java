package com.inso2.inso2.repository.specification;

public class SearchOperationUtils {
    public static SearchOperation convert(String operator){
        switch (operator){
            case "==":
                return SearchOperation.EQUAL;
            case "<":
                return SearchOperation.LESS_THAN;
            case "<=":
                return SearchOperation.LESS_THAN_EQUAL;
            case ">":
                return SearchOperation.GREATER_THAN;
            case ">=":
                return SearchOperation.GREATER_THAN_EQUAL;
            case "!=":
                return SearchOperation.NOT_EQUAL;
            case "match":
                return SearchOperation.MATCH;
            case "match_start":
                return SearchOperation.MATCH_START;
            case "match_end":
                return SearchOperation.MATCH_END;
            case "in":
                return SearchOperation.IN;
            case "notin":
                return SearchOperation.NOT_IN;
            default:
                return SearchOperation.MATCH;
        }
    }
}
