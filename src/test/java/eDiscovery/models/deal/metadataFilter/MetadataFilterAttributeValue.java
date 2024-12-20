package eDiscovery.models.deal.metadataFilter;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MetadataFilterAttributeValue {
    public String id;
    public String value;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String name;

    public MetadataFilterAttributeValue(String id, String value){
        this.id = id;
        this.value = value;
    }
}
