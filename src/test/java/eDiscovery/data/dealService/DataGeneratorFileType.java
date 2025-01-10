package eDiscovery.data.dealService;

import eDiscovery.apiMethods.deal.ApiMethodsFileType;
import eDiscovery.helpers.enums.FileExtensions;
import eDiscovery.helpers.enums.FileTypes;
import eDiscovery.models.deal.fileExtensions.FileExtensionResponseModel;
import eDiscovery.models.deal.fileType.FileTypeResponseModel;

import java.util.List;

public class DataGeneratorFileType {

    public static List<FileTypeResponseModel> getEtalonFileTypes(){

        return List.of(
                new FileTypeResponseModel(
                        "762a9665-cad7-4052-8630-4954f9e9e642",
                        FileTypes.Presentation.name(),
                        FileTypes.Presentation.description,
                        List.of(
                                new FileExtensionResponseModel("1efa8022-e095-45a8-9782-63da8d1cbdce", FileExtensions.PPTX.extension, null),
                                new FileExtensionResponseModel("7787ceaf-3d6b-45b6-827e-9558fb8d9a75", FileExtensions.ODP.extension, null)
                        )
                ),
                new FileTypeResponseModel(
                        "a081a772-18a6-4adb-9cd3-5a72a0fd3b13",
                        FileTypes.Spreadsheet.name(),
                        FileTypes.Spreadsheet.description,
                        List.of(
                                new FileExtensionResponseModel("74fd3f6d-a4b0-4660-8a1c-e45ff734fc2d", FileExtensions.CSV.extension, null),
                                new FileExtensionResponseModel("2353e0d7-3257-45ac-b7fe-929e0f6025f8", FileExtensions.XLSX.extension, null),
                                new FileExtensionResponseModel("2df5c1b6-0c1d-434b-925d-d4b745944db9", FileExtensions.ODS.extension, null)
                        )
                ),
                new FileTypeResponseModel(
                        "a951cf94-d444-4a97-9eca-1f28c0874e70",
                        FileTypes.Image.name(),
                        FileTypes.Image.description,
                        List.of(

                        )
                ),
                new FileTypeResponseModel(
                        "aa429757-472c-4905-bd3c-ce93fdd26fba",
                        FileTypes.Text.name(),
                        FileTypes.Text.description,
                        List.of(
                                new FileExtensionResponseModel("311b7edf-5a6c-4fc5-9d37-d343d8cc7180", FileExtensions.TXT.extension, null),
                                new FileExtensionResponseModel("26fa2968-f478-4313-886f-8ce50ae8f2cb", FileExtensions.XML.extension, null),
                                new FileExtensionResponseModel("f0c5959a-9d1b-4dae-aaa6-62c42d9e49db", FileExtensions.HTM.extension, null),
                                new FileExtensionResponseModel("e3cb6511-ead3-45a3-bd2e-eef0b288c6bf", FileExtensions.HTML.extension, null)
                        )
                ),
                new FileTypeResponseModel(
                        "b46a19e1-ab85-4c23-a9b2-2ae0f500803a",
                        FileTypes.Document.name(),
                        FileTypes.Document.description,
                        List.of(
                                new FileExtensionResponseModel("82216a32-a259-4f27-8f57-edbd0757c1e8", FileExtensions.ODT.extension, null),
                                new FileExtensionResponseModel("230fcec1-c8ab-490b-9234-9b024e43ac25", FileExtensions.DOCX.extension, null)
                        )
                ),
                new FileTypeResponseModel(
                        "b7a8fd5d-16da-4204-9f74-ed69b380978d",
                        FileTypes.PDF.name(),
                        FileTypes.PDF.description,
                        List.of(
                                new FileExtensionResponseModel("168a7bc5-7daf-4e38-b656-52398cf959ae", FileExtensions.PDF.extension, null)
                        )
                ),
                new FileTypeResponseModel(
                        "d17d8b24-6b29-4f6c-a638-238345b77eca",
                        FileTypes.Archives.name(),
                        FileTypes.Archives.description,
                        List.of(
                                new FileExtensionResponseModel("3353abdf-fe71-4cb4-8107-4d5210990121", FileExtensions.ZIP.extension, null)
                        )
                )
        );
    }

    public static FileTypeResponseModel getFileTypeIdByFileType(FileTypes fileType){

        List<FileTypeResponseModel> fileTypes = ApiMethodsFileType.getFileTypeListOData().jsonPath().getList("value", FileTypeResponseModel.class);
        return fileTypes.stream().filter(e -> e.name.equals(fileType.name())).findFirst().orElse(null);

    }


}
