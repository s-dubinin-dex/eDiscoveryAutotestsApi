package eDiscovery.helpers.enums;

public enum MetadataFilterAttributeTypes {

    FILE_SIZE("Размер файла"),
    FILE_CREATION_DATE("Дата создания файла"),
    FILE_PATH("Расположение файла"),
    FILE_LAST_EDIT_DATE("Дата последнего редактирования файла"),
    FILE_NAME("Название файла"),
    FILE_TYPE("Тип файла");

    public final String title;

    MetadataFilterAttributeTypes(String title){
        this.title = title;
    }

}
