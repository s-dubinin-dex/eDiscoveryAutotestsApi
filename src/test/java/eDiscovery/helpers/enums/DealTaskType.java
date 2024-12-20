package eDiscovery.helpers.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public enum DealTaskType {
    Undefined,
    Search,
    ToQuarantine,
    OutQuarantine,
    Export;

    public static List<DealTaskType> getValidDealTaskTypes(){
        return Arrays.stream(DealTaskType.values()).filter(dealTaskType -> !getInvalidDealTaskTypes().contains(dealTaskType)).collect(Collectors.toList());
    }

    public static List<DealTaskType> getInvalidDealTaskTypes(){
        return Collections.singletonList(DealTaskType.Undefined);
    }
}
