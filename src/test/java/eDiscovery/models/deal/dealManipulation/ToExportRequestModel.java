package eDiscovery.models.deal.dealManipulation;

import lombok.Builder;
import java.util.List;

@Builder
public class ToExportRequestModel {
    public List<String> entityIds;
}
