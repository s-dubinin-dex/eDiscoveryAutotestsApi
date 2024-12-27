package eDiscovery.helpers.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public enum SearchQueryType {
    Undefined,
    Text,
    Regex;

    public static List<SearchQueryType> getValidSearchQueryTypes(){

        return Arrays.stream(SearchQueryType.values())
                .filter(searchQueryType -> !getInvalidSearchQueryTypes().contains(searchQueryType))
                .collect(Collectors.toList());
    }

    public static List<SearchQueryType> getInvalidSearchQueryTypes(){
        return Collections.singletonList(SearchQueryType.Undefined);
    }
}
