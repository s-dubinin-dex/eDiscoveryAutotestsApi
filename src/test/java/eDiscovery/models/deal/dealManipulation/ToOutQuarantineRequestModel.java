package eDiscovery.models.deal.dealManipulation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ToOutQuarantineRequestModel {
    public List<String> entityIds;
    public boolean isProblemDocuments;
}
