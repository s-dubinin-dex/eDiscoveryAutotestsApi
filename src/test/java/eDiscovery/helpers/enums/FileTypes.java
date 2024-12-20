package eDiscovery.helpers.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public static List<FileTypes> getFileTypesAvailableForDiscoveredDocumentFilter(){
        return Arrays.stream(FileTypes.values())
                .filter(fileType -> fileType != FileTypes.Archives)
                .collect(Collectors.toList()
                );
    }

}
