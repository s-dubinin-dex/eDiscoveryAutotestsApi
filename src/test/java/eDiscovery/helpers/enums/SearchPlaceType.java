package eDiscovery.helpers.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public enum SearchPlaceType {
    Undefined,
    SMB,
    FTP,
    NFS,
    LOCAL;

    public static List<SearchPlaceType> getValidSearchPlaceTypes(){

        return Arrays.stream(SearchPlaceType.values())
                .filter(searchPlaceType -> !getInvalidSearchPlaceTypes().contains(searchPlaceType))
                .collect(Collectors.toList());

    }

    public static List<SearchPlaceType> getInvalidSearchPlaceTypes(){
        return Collections.singletonList(SearchPlaceType.Undefined);
    }


}
