package eDiscovery.models.deal.fileType;

import eDiscovery.models.deal.fileExtensions.FileExtensionResponseModel;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileTypeResponseModel {
    public String id;
    public String name;
    public String description;
    public List<FileExtensionResponseModel> fileExtensions;
}
