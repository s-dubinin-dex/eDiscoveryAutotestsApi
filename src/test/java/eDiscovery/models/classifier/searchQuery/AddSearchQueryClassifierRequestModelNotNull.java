package eDiscovery.models.classifier.searchQuery;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddSearchQueryClassifierRequestModelNotNull {
    public String type;
    public String name;
    public String value;
}
