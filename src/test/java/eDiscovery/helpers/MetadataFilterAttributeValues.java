package eDiscovery.helpers;

import com.google.gson.Gson;
import eDiscovery.apiMethods.deal.ApiMethodsFileType;
import eDiscovery.apiMethods.deal.ApiMethodsFilterAttributeSchema;
import eDiscovery.helpers.enums.MetadataFilterAttributeTypes;
import eDiscovery.models.deal.fileType.FileTypeResponseModel;
import eDiscovery.models.deal.filterAttributeSchema.FilterAttributeSchemaResponseModel;
import eDiscovery.models.deal.metadataFilter.MetadataFilterAttributeValue;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class MetadataFilterAttributeValues {

    private static final List<FilterAttributeSchemaResponseModel> FILTER_ATTRIBUTE_SCHEMAS = ApiMethodsFilterAttributeSchema.getFilterAttributeSchemaList().jsonPath().getList("", FilterAttributeSchemaResponseModel.class);

    @AllArgsConstructor
    private static class MetadataFilterFileSizeModel{
        Integer From;
        Integer To;
    }

    @AllArgsConstructor
    private static class MetadataFilterFileTypeModel{
        List<String> Value;
    }

    @AllArgsConstructor
    private static class MetadataFilterFileNameModel{
        String Value;
    }

    @AllArgsConstructor
    private static class MetadataFilterFilePathModel{
        List<String> Value;
    }

    @AllArgsConstructor
    private static class MetadataFilterFileCreationDate{
        String From;
        String To;
    }

    @AllArgsConstructor
    private static class MetadataFilterFileListFileEditDate{
        String From;
        String To;
    }

    private static final Gson GSON = new Gson();

    public static MetadataFilterAttributeValue getAttributesForFileSize(Integer from, Integer to){

        return new MetadataFilterAttributeValue(
                getAttributeIdByTitle(MetadataFilterAttributeTypes.FILE_SIZE.title),
                GSON.toJson(new MetadataFilterFileSizeModel(from, to))
        );
    }

    public static MetadataFilterAttributeValue getAttributesForFileType(List<String> values){

        List<FileTypeResponseModel> fileTypeModels = ApiMethodsFileType.getFileTypeListOData().jsonPath().getList("value", FileTypeResponseModel.class);

        List<String> fileTypeIDs = values == null ? null : fileTypeModels.stream()
                .filter(fileTypeResponseModel -> values.contains(fileTypeResponseModel.name))
                .map(FileTypeResponseModel::getId)
                .toList();

        return new MetadataFilterAttributeValue(
                getAttributeIdByTitle(MetadataFilterAttributeTypes.FILE_TYPE.title),
                GSON.toJson(new MetadataFilterFileTypeModel(fileTypeIDs))
        );
    }

    public static MetadataFilterAttributeValue getAttributesForFileName(String fileName){

        return new MetadataFilterAttributeValue(
                getAttributeIdByTitle(MetadataFilterAttributeTypes.FILE_NAME.title),
                GSON.toJson(new MetadataFilterFileNameModel(fileName))
        );
    }

    public static MetadataFilterAttributeValue getAttributesForFilePath(List<String> filePaths){
        return new MetadataFilterAttributeValue(
                getAttributeIdByTitle(MetadataFilterAttributeTypes.FILE_PATH.title),
                GSON.toJson(new MetadataFilterFilePathModel(filePaths))
        );
    }

    public static MetadataFilterAttributeValue getAttributesForFileCreationDate(LocalDate from, LocalDate to){
        return new MetadataFilterAttributeValue(
                getAttributeIdByTitle(MetadataFilterAttributeTypes.FILE_CREATION_DATE.title),
                GSON.toJson(new MetadataFilterFileCreationDate(
                        from == null ? null: from.toString(),
                        to == null ? null: to.toString()
                        )
                )
        );
    }

    public static MetadataFilterAttributeValue getAttributesForFileLastEditingDate(LocalDate from, LocalDate to){
        return new MetadataFilterAttributeValue(
                getAttributeIdByTitle(MetadataFilterAttributeTypes.FILE_LAST_EDIT_DATE.title),
                GSON.toJson(new MetadataFilterFileListFileEditDate(
                        from == null ? null : from.toString(),
                        to ==  null ? null : to.toString()
                        )
                )
        );
    }

    private static String getAttributeIdByTitle(String title) {

        FilterAttributeSchemaResponseModel matchedFilterAttributeSchema = FILTER_ATTRIBUTE_SCHEMAS.stream()
                .filter(filterAttributeSchema -> filterAttributeSchema.title.equals(title))
                .findFirst().orElse(null);

        return (matchedFilterAttributeSchema == null) ? null : matchedFilterAttributeSchema.getId();
    }


}
