package eDiscovery.data.dealService;

import eDiscovery.models.deal.fileExtensions.FileExtensionResponseModel;
import eDiscovery.models.deal.fileType.FileTypeResponseModel;

import java.util.List;
import java.util.stream.Collectors;

public class DataGeneratorFileType {

    public static List<FileTypeResponseModel> getEtalonFileTypes(){

        return List.of(
                new FileTypeResponseModel(
                        "762a9665-cad7-4052-8630-4954f9e9e642",
                        "Presentation",
                        "Презентации",
                        List.of(
                                new FileExtensionResponseModel("1efa8022-e095-45a8-9782-63da8d1cbdce", ".pptx", null),
                                new FileExtensionResponseModel("7787ceaf-3d6b-45b6-827e-9558fb8d9a75", ".odp", null)
                        )
                ),
                new FileTypeResponseModel(
                        "a081a772-18a6-4adb-9cd3-5a72a0fd3b13",
                        "Spreadsheet",
                        "Электронные таблицы",
                        List.of(
                                new FileExtensionResponseModel("74fd3f6d-a4b0-4660-8a1c-e45ff734fc2d", ".csv", null),
                                new FileExtensionResponseModel("2353e0d7-3257-45ac-b7fe-929e0f6025f8", ".xlsx", null),
                                new FileExtensionResponseModel("2df5c1b6-0c1d-434b-925d-d4b745944db9", ".ods", null)
                        )
                ),
                new FileTypeResponseModel(
                        "a951cf94-d444-4a97-9eca-1f28c0874e70",
                        "Image",
                        "Изображения",
                        List.of(

                        )
                ),
                new FileTypeResponseModel(
                        "aa429757-472c-4905-bd3c-ce93fdd26fba",
                        "Text",
                        "Текстовые файлы",
                        List.of(
                                new FileExtensionResponseModel("311b7edf-5a6c-4fc5-9d37-d343d8cc7180", ".txt", null)
                        )
                ),
                new FileTypeResponseModel(
                        "b46a19e1-ab85-4c23-a9b2-2ae0f500803a",
                        "Document",
                        "Документы типа Word",
                        List.of(
                                new FileExtensionResponseModel("82216a32-a259-4f27-8f57-edbd0757c1e8", ".odt", null),
                                new FileExtensionResponseModel("230fcec1-c8ab-490b-9234-9b024e43ac25", ".docx", null)
                        )
                ),
                new FileTypeResponseModel(
                        "b7a8fd5d-16da-4204-9f74-ed69b380978d",
                        "PDF",
                        "Документы типа PDF",
                        List.of(
                                new FileExtensionResponseModel("168a7bc5-7daf-4e38-b656-52398cf959ae", ".pdf", null)
                        )
                ),
                new FileTypeResponseModel(
                        "d17d8b24-6b29-4f6c-a638-238345b77eca",
                        "Archives",
                        "Архивы",
                        List.of(
                                new FileExtensionResponseModel("3353abdf-fe71-4cb4-8107-4d5210990121", ".zip", null)
                        )
                )
        );
    }

}
