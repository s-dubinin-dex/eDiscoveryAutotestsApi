package eDiscovery.models.classifier.searchQuery;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommonSearchQueryClassifierResponseModel {
    public String name;
    public String type;
    public String id;
    public String value;
    public String createdUtc;
    public String deletedUtc;
}
