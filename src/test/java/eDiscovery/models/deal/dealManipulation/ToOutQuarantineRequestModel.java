package eDiscovery.models.deal.dealManipulation;

import lombok.Builder;
import java.util.List;

@Builder
public class ToOutQuarantineRequestModel {
    public List<String> entityIds;
}
