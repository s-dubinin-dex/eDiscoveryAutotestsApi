package eDiscovery.tests.apiTests.extendedPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsMetadataFilter;
import eDiscovery.data.dealService.DataGeneratorMetadataFilter;
import eDiscovery.helpers.MetadataFilterAttributeValues;
import eDiscovery.helpers.enums.DataSearchType;
import eDiscovery.helpers.enums.FileTypes;
import eDiscovery.models.deal.metadataFilter.AddMetadataFilterRequestBody;
import eDiscovery.models.deal.metadataFilter.CommonMetadataFilterResponseBody;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static eDiscovery.data.DataGeneratorCommon.getRandomName;
import static eDiscovery.helpers.DataChecker.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Extended positive tests: Deal - MetadataFilter")
public class MetadataFilterExtendedPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Deal")
    @Feature("Фильтры по метаданным")
    @Story("Создание фильтра по метаданным")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Создание фильтра по метаданным (Размер файла) c указанием только параметра To")
    @Description("Тест проверяет возможность создания фильтра по метаданным (Размер файла) c указанием только параметра To")
    public void testAddMetadataFilterFileSizeWithTo(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddMetadataFilterRequestBody requestBody = AddMetadataFilterRequestBody.builder()
                .name(String.format("Фильтр по файлам до 1_000 байт %s", getRandomName()))
                .dataSearchType(DataSearchType.Document.name())
                .filterAttributeValues(Collections.singletonList(
                        MetadataFilterAttributeValues.getAttributesForFileSize(
                                null,
                                1_000
                        )
                ))
                .build();

        CommonMetadataFilterResponseBody responseBody = ApiMethodsMetadataFilter.addMetadataFilter(requestBody).as(CommonMetadataFilterResponseBody.class);

        assertThat(isValidUUID(responseBody.id)).isTrue();
        assertThat(responseBody.dataSearchType).isEqualTo(requestBody.dataSearchType);
        assertThat(responseBody.name).isEqualTo(requestBody.name);

        assertThat(responseBody.attributeValues).usingRecursiveComparison().ignoringFields("name").isEqualTo(requestBody.filterAttributeValues);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Фильтры по метаданным")
    @Story("Создание фильтра по метаданным")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Создание фильтра по метаданным (Размер файла) c указанием только параметра From")
    @Description("Тест проверяет возможность создания фильтра по метаданным (Размер файла) c указанием только параметра From")
    public void testAddMetadataFilterFileSizeWithFrom(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddMetadataFilterRequestBody requestBody = AddMetadataFilterRequestBody.builder()
                .name(String.format("Фильтр по файлам свыше 50_000_000 байт %s", getRandomName()))
                .dataSearchType(DataSearchType.Document.name())
                .filterAttributeValues(Collections.singletonList(
                        MetadataFilterAttributeValues.getAttributesForFileSize(
                                50_000_000,
                                null
                        )
                ))
                .build();

        CommonMetadataFilterResponseBody responseBody = ApiMethodsMetadataFilter.addMetadataFilter(requestBody).as(CommonMetadataFilterResponseBody.class);

        assertThat(isValidUUID(responseBody.id)).isTrue();
        assertThat(responseBody.dataSearchType).isEqualTo(requestBody.dataSearchType);
        assertThat(responseBody.name).isEqualTo(requestBody.name);

        assertThat(responseBody.attributeValues).usingRecursiveComparison().ignoringFields("name").isEqualTo(requestBody.filterAttributeValues);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Фильтры по метаданным")
    @Story("Создание фильтра по метаданным")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Создание фильтра по метаданным (Тип файла) c указанием нескольких нескольких типов файла")
    @Description("Тест проверяет возможность создания фильтра по метаданным (Тип файла) c указанием нескольких нескольких типов файла")
    public void testAddMetadataFilterFileTypeWithTwoFileTypes(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddMetadataFilterRequestBody requestBody = AddMetadataFilterRequestBody.builder()
                .name("Фильтр по файлам типов Document и Spreadsheet" + getRandomName())
                .dataSearchType(DataSearchType.Document.name())
                .filterAttributeValues(Collections.singletonList(
                                MetadataFilterAttributeValues.getAttributesForFileType(List.of(
                                        FileTypes.Document.name(),
                                        FileTypes.Spreadsheet.name())
                                )
                        )
                )
                .build();

        CommonMetadataFilterResponseBody responseBody = ApiMethodsMetadataFilter.addMetadataFilter(requestBody).as(CommonMetadataFilterResponseBody.class);

        assertThat(isValidUUID(responseBody.id)).isTrue();
        assertThat(responseBody.dataSearchType).isEqualTo(requestBody.dataSearchType);
        assertThat(responseBody.name).isEqualTo(requestBody.name);

        assertThat(responseBody.attributeValues).usingRecursiveComparison().ignoringFields("name").isEqualTo(requestBody.filterAttributeValues);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Фильтры по метаданным")
    @Story("Создание фильтра по метаданным")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Создание фильтра по метаданным (Название файла) с пустой строкой в качестве значения")
    @Description("Тест проверяет возможность создания фильтра по метаданным (Название файла) с пустой строкой в качестве значения")
    public void testAddMetadataFilterFileNameWithEmptyFileName(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddMetadataFilterRequestBody requestBody = AddMetadataFilterRequestBody.builder()
                .name(String.format("Фильтр по файлам c именами, содержащим пустую строку %s", getRandomName()))
                .dataSearchType(DataSearchType.Document.name())
                .filterAttributeValues(Collections.singletonList(
                        MetadataFilterAttributeValues.getAttributesForFileName(""))
                )
                .build();

        CommonMetadataFilterResponseBody responseBody = ApiMethodsMetadataFilter.addMetadataFilter(requestBody).as(CommonMetadataFilterResponseBody.class);

        assertThat(isValidUUID(responseBody.id)).isTrue();
        assertThat(responseBody.dataSearchType).isEqualTo(requestBody.dataSearchType);
        assertThat(responseBody.name).isEqualTo(requestBody.name);

        assertThat(responseBody.attributeValues).usingRecursiveComparison().ignoringFields("name").isEqualTo(requestBody.filterAttributeValues);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Фильтры по метаданным")
    @Story("Создание фильтра по метаданным")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Создание фильтра по метаданным (Расположение файла) с указанием пустой строки в качестве пути")
    @Description("Тест проверяет возможность создания фильтра по метаданным (Расположение файла) с указанием пустой строки в качестве пути")
    public void testAddMetadataFilterFilePathWithEmptyFilePath(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddMetadataFilterRequestBody requestBody = AddMetadataFilterRequestBody.builder()
                .name(String.format("Фильтр по файлам, расположенным в директории, указанной как пустая строка %s", getRandomName()))
                .dataSearchType(DataSearchType.Document.name())
                .filterAttributeValues(Collections.singletonList(
                                MetadataFilterAttributeValues.getAttributesForFilePath(
                                        Collections.singletonList("")
                                )
                        )
                )
                .build();

        CommonMetadataFilterResponseBody responseBody = ApiMethodsMetadataFilter.addMetadataFilter(requestBody).as(CommonMetadataFilterResponseBody.class);

        assertThat(isValidUUID(responseBody.id)).isTrue();
        assertThat(responseBody.dataSearchType).isEqualTo(requestBody.dataSearchType);
        assertThat(responseBody.name).isEqualTo(requestBody.name);

        assertThat(responseBody.attributeValues).usingRecursiveComparison().ignoringFields("name").isEqualTo(requestBody.filterAttributeValues);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Фильтры по метаданным")
    @Story("Создание фильтра по метаданным")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Создание фильтра по метаданным (Расположение файла) с указанием нескольких расположений")
    @Description("Тест проверяет возможность создания фильтра по метаданным (Расположение файла) с указанием нескольких расположений")
    public void testAddMetadataFilterFilePathWithTwoFilePaths(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddMetadataFilterRequestBody requestBody = AddMetadataFilterRequestBody.builder()
                .name(String.format("Фильтр по файлам, расположенным в D:\\filesToSearchTest и D:\\WORK Projects\\eDiscovery - %s",getRandomName()))
                .dataSearchType(DataSearchType.Document.name())
                .filterAttributeValues(Collections.singletonList(
                                MetadataFilterAttributeValues.getAttributesForFilePath(List.of(
                                        "D:\\filesToSearchTest",
                                        "D:\\WORK Projects\\eDiscovery")
                                )
                        )
                )
                .build();

        CommonMetadataFilterResponseBody responseBody = ApiMethodsMetadataFilter.addMetadataFilter(requestBody).as(CommonMetadataFilterResponseBody.class);

        assertThat(isValidUUID(responseBody.id)).isTrue();
        assertThat(responseBody.dataSearchType).isEqualTo(requestBody.dataSearchType);
        assertThat(responseBody.name).isEqualTo(requestBody.name);

        assertThat(responseBody.attributeValues).usingRecursiveComparison().ignoringFields("name").isEqualTo(requestBody.filterAttributeValues);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Фильтры по метаданным")
    @Story("Создание фильтра по метаданным")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Создание фильтра по метаданным (Дата создания файла) с указанием только From")
    @Description("Тест проверяет возможность создания фильтра по метаданным (Дата создания файла) с указанием только From")
    public void testAddMetadataFilterFileCreationDateWithFrom(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddMetadataFilterRequestBody requestBody = AddMetadataFilterRequestBody.builder()
                .name(String.format("Фильтр по файлам, созданным после 23 мая 2018 %s", getRandomName()))
                .dataSearchType(DataSearchType.Document.name())
                .filterAttributeValues(Collections.singletonList(
                        MetadataFilterAttributeValues.getAttributesForFileCreationDate(
                                LocalDate.of(2018, 5, 23),
                                null
                        )
                ))
                .build();

        CommonMetadataFilterResponseBody responseBody = ApiMethodsMetadataFilter.addMetadataFilter(requestBody).as(CommonMetadataFilterResponseBody.class);

        assertThat(isValidUUID(responseBody.id)).isTrue();
        assertThat(responseBody.dataSearchType).isEqualTo(requestBody.dataSearchType);
        assertThat(responseBody.name).isEqualTo(requestBody.name);

        assertThat(responseBody.attributeValues).usingRecursiveComparison().ignoringFields("name").isEqualTo(requestBody.filterAttributeValues);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Фильтры по метаданным")
    @Story("Создание фильтра по метаданным")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Создание фильтра по метаданным (Дата создания файла) с указанием только To")
    @Description("Тест проверяет возможность создания фильтра по метаданным (Дата создания файла) с указанием только To")
    public void testAddMetadataFilterFileCreationDateWithTo(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddMetadataFilterRequestBody requestBody = AddMetadataFilterRequestBody.builder()
                .name(String.format("Фильтр по файлам, созданным до 20 октября 2021 %s", getRandomName()))
                .dataSearchType(DataSearchType.Document.name())
                .filterAttributeValues(Collections.singletonList(
                        MetadataFilterAttributeValues.getAttributesForFileCreationDate(
                                null,
                                LocalDate.of(2021, 10, 20)
                        )
                ))
                .build();

        CommonMetadataFilterResponseBody responseBody = ApiMethodsMetadataFilter.addMetadataFilter(requestBody).as(CommonMetadataFilterResponseBody.class);

        assertThat(isValidUUID(responseBody.id)).isTrue();
        assertThat(responseBody.dataSearchType).isEqualTo(requestBody.dataSearchType);
        assertThat(responseBody.name).isEqualTo(requestBody.name);

        assertThat(responseBody.attributeValues).usingRecursiveComparison().ignoringFields("name").isEqualTo(requestBody.filterAttributeValues);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Фильтры по метаданным")
    @Story("Создание фильтра по метаданным")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Создание фильтра по метаданным (Дата последнего редактирования файла) с указанием только From")
    @Description("Тест проверяет возможность создания фильтра по метаданным (Дата последнего редактирования файла) с указанием только From")
    public void testAddMetadataFilterFileLastEditingDateWithFrom(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddMetadataFilterRequestBody requestBody = AddMetadataFilterRequestBody.builder()
                .name(String.format("Фильтр по файлам, изменённым с 6 мая 2024 %s", getRandomName()))
                .dataSearchType(DataSearchType.Document.name())
                .filterAttributeValues(Collections.singletonList(
                        MetadataFilterAttributeValues.getAttributesForFileLastEditingDate(
                                LocalDate.of(2022, 5, 6),
                                null
                        )
                ))
                .build();

        CommonMetadataFilterResponseBody responseBody = ApiMethodsMetadataFilter.addMetadataFilter(requestBody).as(CommonMetadataFilterResponseBody.class);

        assertThat(isValidUUID(responseBody.id)).isTrue();
        assertThat(responseBody.dataSearchType).isEqualTo(requestBody.dataSearchType);
        assertThat(responseBody.name).isEqualTo(requestBody.name);

        assertThat(responseBody.attributeValues).usingRecursiveComparison().ignoringFields("name").isEqualTo(requestBody.filterAttributeValues);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Фильтры по метаданным")
    @Story("Создание фильтра по метаданным")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Создание фильтра по метаданным (Дата последнего редактирования файла) с указанием только To")
    @Description("Тест проверяет возможность создания фильтра по метаданным (Дата последнего редактирования файла) с указанием только To")
    public void testAddMetadataFilterFileLastEditingDateWithTo(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddMetadataFilterRequestBody requestBody = AddMetadataFilterRequestBody.builder()
                .name(String.format("Фильтр по файлам, изменённым до 5 июля 2021 %s", getRandomName()))
                .dataSearchType(DataSearchType.Document.name())
                .filterAttributeValues(Collections.singletonList(
                        MetadataFilterAttributeValues.getAttributesForFileLastEditingDate(
                                null,
                                LocalDate.of(2021, 7, 5)
                        )
                ))
                .build();

        CommonMetadataFilterResponseBody responseBody = ApiMethodsMetadataFilter.addMetadataFilter(requestBody).as(CommonMetadataFilterResponseBody.class);

        assertThat(isValidUUID(responseBody.id)).isTrue();
        assertThat(responseBody.dataSearchType).isEqualTo(requestBody.dataSearchType);
        assertThat(responseBody.name).isEqualTo(requestBody.name);

        assertThat(responseBody.attributeValues).usingRecursiveComparison().ignoringFields("name").isEqualTo(requestBody.filterAttributeValues);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Фильтры по метаданным")
    @Story("Создание фильтра по метаданным")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Создание фильтра по метаданным по 2 видам метаданным (Тип файла и Дата последнего редактирования файла)")
    @Description("Тест проверяет возможность создания фильтра по 2 видам метаданным (Тип файла и Дата последнего редактирования файла)")
    public void testAddMetadataFilterWithTwoParametersInFilter(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddMetadataFilterRequestBody requestBody = AddMetadataFilterRequestBody.builder()
                .name("Фильтр по Document и SpreadSheet файлам, изменённым с 6 мая 2022 по 26 октября 2024" + getRandomName())
                .dataSearchType(DataSearchType.Document.name())
                .filterAttributeValues(
                        List.of(
                                MetadataFilterAttributeValues.getAttributesForFileType(List.of(
                                        FileTypes.Document.name(),
                                        FileTypes.Spreadsheet.name())),
                                MetadataFilterAttributeValues.getAttributesForFileLastEditingDate(
                                        LocalDate.of(2022, 5, 6),
                                        LocalDate.of(2024, 10, 26)
                                )
                        )
                )
                .build();

        CommonMetadataFilterResponseBody responseBody = ApiMethodsMetadataFilter.addMetadataFilter(requestBody).as(CommonMetadataFilterResponseBody.class);

        assertThat(isValidUUID(responseBody.id)).isTrue();
        assertThat(responseBody.dataSearchType).isEqualTo(requestBody.dataSearchType);
        assertThat(responseBody.name).isEqualTo(requestBody.name);

        assertThat(responseBody.attributeValues).usingRecursiveComparison().ignoringCollectionOrder().ignoringFields("name").isEqualTo(requestBody.filterAttributeValues);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Фильтры по метаданным")
    @Story("Удаление фильтра по метаданным")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("В удалёном фильтре по метаданным возвращается deletedUtc")
    @Description("Тест проверяет, что в удалёном фильтре по метаданным возвращается deletedUtc")
    public void tesGetDeleteMetadataFilterByIdReturnsDeletedUtc(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());

        CommonMetadataFilterResponseBody responseBody = DataGeneratorMetadataFilter.createBasicMetadataFilter();
        ApiMethodsMetadataFilter.deleteMetadataFilter(responseBody.id);

        CommonMetadataFilterResponseBody responseBodyAfterDeletion = ApiMethodsMetadataFilter.getMetadataFilterByIdPath(responseBody.id).as(CommonMetadataFilterResponseBody.class);

        assertThat(responseBodyAfterDeletion.deletedUtc).matches(dateTimeCommonPattern());

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Фильтры по метаданным")
    @Story("Удаление фильтра по метаданным")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("В удалёном фильтре по метаданным возвращается deletedUtc в списке фильтров метаданных")
    @Description("Тест проверяет, что в удалёном фильтре по метаданным возвращается deletedUtc в списке фильтров метаданных")
    public void tesGetDeleteMetadataFilterByIdReturnsDeletedUtcInList(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());

        CommonMetadataFilterResponseBody responseBody = DataGeneratorMetadataFilter.createBasicMetadataFilter();
        ApiMethodsMetadataFilter.deleteMetadataFilter(responseBody.id);

        HashMap<String, String> requestParameters = new HashMap<>();
        requestParameters.put("includeDeleted", "true");

        CommonMetadataFilterResponseBody responseBodyAfterDeletion = ApiMethodsMetadataFilter.getMetadataFilterListOData(requestParameters).jsonPath().getList("value", CommonMetadataFilterResponseBody.class)
                .stream().filter(el -> el.name.equals(responseBody.name)).findFirst().orElse(null);

        assertThat(responseBodyAfterDeletion).isNotNull();
        assertThat(responseBodyAfterDeletion.deletedUtc).matches(dateTimeCommonPattern());

    }

}
