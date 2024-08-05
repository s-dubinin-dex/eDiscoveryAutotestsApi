package eDiscovery.helpers.enums;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum SearchQueryType {
    Undefined,
    Text,
    Regex;

    public static List<String> getValidSearchQueryTypes(){
        List<String> result = new ArrayList<>();

        for (SearchQueryType entity: SearchQueryType.values()){
            if (entity.equals(SearchQueryType.Undefined)){
                continue;
            }
            result.add(entity.name());
        }
        return result;
    }

    public static List<String> getInvalidSearchQueryTypes(){
        return Collections.singletonList(SearchQueryType.Undefined.name());
    }
}
