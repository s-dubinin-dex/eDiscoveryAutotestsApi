package eDiscovery.models.deal.dealManipulation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DealSearchQueryModel {
    @JsonInclude()
    public boolean isActive;
    @JsonInclude()
    public String id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String name;

    public DealSearchQueryModel(String id, boolean isActive){
        this.id = id;
        this.isActive = isActive;
    }
}

