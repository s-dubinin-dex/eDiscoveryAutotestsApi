package eDiscovery.models.deal.filterAttributeSchema;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FilterAttributeSchemaResponseModel {
    public String id;
    public String title;
    public String type;
    public String value;
}
