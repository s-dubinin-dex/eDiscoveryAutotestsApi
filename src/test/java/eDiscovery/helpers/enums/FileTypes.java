package eDiscovery.helpers.enums;

public enum FileTypes {
    Presentation("Презентации"),
    Spreadsheet("Электронные таблицы"),
    Image("Изображения"),
    Text("Текстовые файлы"),
    Document("Документы типа Word"),
    PDF("Документы типа PDF"),
    Archives("Архивы");

    public final String description;

    FileTypes(String description){
        this.description = description;
    }

}
