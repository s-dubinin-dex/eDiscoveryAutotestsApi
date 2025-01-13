package eDiscovery.models.deal.dealManipulation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DealSearchQueryModel {
    @JsonInclude()
    public String id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String name;
    @JsonInclude()
    @JsonProperty("isActive")
    public boolean isActive;

    public DealSearchQueryModel(String id, boolean isActive){
        this.id = id;
        this.isActive = isActive;
    }

}

