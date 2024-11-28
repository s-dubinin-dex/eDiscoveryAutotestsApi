package eDiscovery.data.dealService;

import eDiscovery.models.deal.fileExtensions.FileExtensionResponseModel;
import eDiscovery.models.deal.fileType.FileTypeResponseModel;

import java.util.List;
import java.util.stream.Collectors;

public class DataGeneratorFileExtension {

    public static List<FileExtensionResponseModel> getEtalonFileExtensions(){

        return DataGeneratorFileType.getEtalonFileTypes().stream()
                .map(FileTypeResponseModel::getFileExtensions)
                .flatMap(List::stream)
                .collect(Collectors.toList());

    }
}
