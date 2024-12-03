package eDiscovery.helpers.enums;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum SearchPlaceCategoryType {
    Undefined,
    Workspace,
    FileShare;

    public static List<String> getValidSearchPlaceCategoryTypes(){

        List<String> result = new ArrayList<>();

        for (SearchPlaceCategoryType entity: SearchPlaceCategoryType.values()){
            if (entity.equals(SearchPlaceCategoryType.Undefined)){
                continue;
            }
            result.add(entity.name());
        }
        return result;
    }

    public static List<String> getInvalidSearchPlaceCategoryTypes(){
        return Collections.singletonList(SearchPlaceCategoryType.Undefined.name());
    }
}
