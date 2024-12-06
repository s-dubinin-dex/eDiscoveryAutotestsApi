package eDiscovery.models.deal.filterAttributeSchema;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class FilterAttributeSchemaResponseModel {
    public String id;
    public String title;
    public String type;
    public String value;
}
