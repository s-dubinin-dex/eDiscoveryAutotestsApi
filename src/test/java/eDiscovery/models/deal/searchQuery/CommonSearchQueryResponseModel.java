package eDiscovery.models.deal.searchQuery;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommonSearchQueryResponseModel {
    public String name;
    public String type;
    public String id;
    public String value;
    public String createdUtc;
}
