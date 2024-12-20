package eDiscovery.models.deal.fileType;

import eDiscovery.models.deal.fileExtensions.FileExtensionResponseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileTypeResponseModel {
    public String id;
    public String name;
    public String description;
    public List<FileExtensionResponseModel> fileExtensions;
}
