package eDiscovery.models.deal.searchQuery;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
public class UpdateSearchQueryRequestModel {
    //TODO: Подумать, как сделать конструктор копирования. Сделать констраутор, который принимает объект AddSearchQuery, а возвращается UpdateSearchQuery
    @JsonInclude()
    public String name;
    @JsonInclude()
    public String type;
    @JsonInclude()
    public String value;
    @JsonInclude()
    public String id;
}
