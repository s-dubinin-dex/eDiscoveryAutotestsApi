package eDiscovery.models.admin.role;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class PolicyResponseModel {
    public String category;
    public List<Policy> policies;

    @NoArgsConstructor
    @AllArgsConstructor
    public static class Policy {
        public String value;
    }

}
