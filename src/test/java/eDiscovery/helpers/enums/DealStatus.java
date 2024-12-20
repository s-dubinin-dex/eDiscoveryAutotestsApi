package eDiscovery.helpers.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public enum DealStatus {
    Undefined,
    Waiting,
    Running,
    Stopped,
    Closed;

    public static List<DealStatus> getValidDealStatuses(){
        return Arrays.stream(DealStatus.values()).filter(dealStatus -> !getInvalidDealStatuses().contains(dealStatus)).collect(Collectors.toList());
    }

    public static List<DealStatus> getInvalidDealStatuses(){
        return Collections.singletonList(DealStatus.Undefined);
    }

}
