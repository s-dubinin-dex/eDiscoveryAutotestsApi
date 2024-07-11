package eDiscovery.models.deal.dealManipulation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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