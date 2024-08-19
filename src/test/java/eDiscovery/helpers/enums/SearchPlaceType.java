package eDiscovery.helpers.enums;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum SearchPlaceType {
    Undefined,
    SMB,
    FTP,
    NFS,
    LOCAL;

    public static List<String> getValidSearchPlaceTypes(){
        List<String> result = new ArrayList<>();

        for (SearchPlaceType entity: SearchPlaceType.values()){
            if (entity.equals(SearchPlaceType.Undefined)){
                continue;
            }
            result.add(entity.name());
        }
        return result;
    }

    public static List<String> getInvalidSearchPlaceTypes(){
        return Collections.singletonList(SearchPlaceCategoryType.Undefined.name());
    }


}
