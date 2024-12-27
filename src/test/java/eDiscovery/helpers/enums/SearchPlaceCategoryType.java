package eDiscovery.helpers.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public enum SearchPlaceCategoryType {
    Undefined,
    Workspace,
    FileShare;

    public static List<SearchPlaceCategoryType> getValidSearchPlaceCategoryTypes(){

        return Arrays.stream(SearchPlaceCategoryType.values())
                .filter(searchPlaceCategoryType -> !getInvalidSearchPlaceCategoryTypes().contains(searchPlaceCategoryType))
                .collect(Collectors.toList());

    }

    public static List<SearchPlaceCategoryType> getInvalidSearchPlaceCategoryTypes(){
        return Collections.singletonList(SearchPlaceCategoryType.Undefined);
    }
}
