package eDiscovery.models.deal.searchPlace;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchPlaceParametersModel {
    public String uri;
    public String username;
    public String password;
}
