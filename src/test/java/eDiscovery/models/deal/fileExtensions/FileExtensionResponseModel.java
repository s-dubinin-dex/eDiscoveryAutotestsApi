package eDiscovery.models.deal.fileExtensions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileExtensionResponseModel {
    public String id;
    public String extension;
    public String mimeType;

}
