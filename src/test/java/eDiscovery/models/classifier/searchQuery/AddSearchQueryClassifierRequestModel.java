package eDiscovery.models.classifier.searchQuery;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
public class AddSearchQueryClassifierRequestModel {
    @JsonInclude()
    public String type;
    @JsonInclude()
    public String name;
    @JsonInclude()
    public String value;
}