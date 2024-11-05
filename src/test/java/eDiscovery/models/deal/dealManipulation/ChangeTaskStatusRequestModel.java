package eDiscovery.models.deal.dealManipulation;

import lombok.Builder;
import java.util.List;

@Builder
public class ChangeTaskStatusRequestModel {
    public String entityId;
    public String dealTaskStatus;
    public List<SearchTaskErrors> searchTaskErrors;
}

class SearchTaskErrors {
    public String error;
    public String documentName;
    public String documentPath;
}