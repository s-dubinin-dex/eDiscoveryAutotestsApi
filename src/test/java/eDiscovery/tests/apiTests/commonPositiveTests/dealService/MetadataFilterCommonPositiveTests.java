package eDiscovery.tests.apiTests.commonPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsMetadataFilter;
import eDiscovery.data.dealService.DataGeneratorMetadataFilter;
import eDiscovery.helpers.MetadataFilterAttributeValues;
import eDiscovery.helpers.enums.DataSearchType;
import eDiscovery.helpers.enums.FileTypes;
import eDiscovery.models.deal.metadataFilter.AddMetadataFilterRequestBody;
import eDiscovery.models.deal.metadataFilter.CommonMetadataFilterResponseBody;
import eDiscovery.models.deal.metadataFilter.MetadataFilterAttributeValue;
import eDiscovery.models.deal.metadataFilter.UpdateMetadataFilterRequestBody;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static eDiscovery.data.DataGeneratorCommon.getRandomName;
import static eDiscovery.helpers.DataChecker.isValidUUID;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Common positive tests: Deal - MetadataFilter")
public class MetadataFilterCommonPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Deal")
    @Feature("Фильтр по метаданным")
    @Story("Создание фильтра по метаданным")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание фильтра по метаданным (Размер файла)")
    @Description("Тест проверяет возможность создания фильтра по метаданным (Размер файла)")
    public void testAddMetadataFilterFileSizeWithFromTo(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddMetadataFilterRequestBody requestBody = AddMetadataFilterRequestBody.builder()
                .name(String.format("Фильтр по файлам от 10_000 до 50_000 байт %s", getRandomName()))
                .dataSearchType(DataSearchType.Document.name())
                .filterAttributeValues(Collections.singletonList(
                        MetadataFilterAttributeValues.getAttributesForFileSize(
                                10_000,
                                50_000
                        )
                ))
                .build();

        CommonMetadataFilterResponseBody responseBody = ApiMethodsMetadataFilter.addMetadataFilter(requestBody).as(CommonMetadataFilterResponseBody.class);

        assertThat(isValidUUID(responseBody.id)).isTrue();
        assertThat(responseBody.dataSearchType).isEqualTo(requestBody.dataSearchType);
        assertThat(responseBody.name).isEqualTo(requestBody.name);
        assertThat(responseBody.deletedUtc).isNull();
        assertThat(responseBody.attributeValues).usingRecursiveComparison().ignoringFields("name").isEqualTo(requestBody.filterAttributeValues);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Фильтр по метаданным")
    @Story("Создание фильтра по метаданным")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание фильтра по метаданным (Тип файла)")
    @Description("Тест проверяет возможность создания фильтра по метаданным (Тип файла)")
    public void testAddMetadataFilterFileTypeOneFileType(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddMetadataFilterRequestBody requestBody = AddMetadataFilterRequestBody.builder()
                .name(String.format("Фильтр по файлам типа Text %s", getRandomName()))
                .dataSearchType(DataSearchType.Document.name())
                .filterAttributeValues(Collections.singletonList(
                                MetadataFilterAttributeValues.getAttributesForFileType(
                                        Collections.singletonList(
                                                FileTypes.Text.name()
                                        )
                                )
                        )
                )
                .build();

        CommonMetadataFilterResponseBody responseBody = ApiMethodsMetadataFilter.addMetadataFilter(requestBody).as(CommonMetadataFilterResponseBody.class);

        assertThat(isValidUUID(responseBody.id)).isTrue();
        assertThat(responseBody.dataSearchType).isEqualTo(requestBody.dataSearchType);
        assertThat(responseBody.name).isEqualTo(requestBody.name);
        assertThat(responseBody.deletedUtc).isNull();
        assertThat(responseBody.attributeValues).usingRecursiveComparison().ignoringFields("name").isEqualTo(requestBody.filterAttributeValues);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Фильтр по метаданным")
    @Story("Создание фильтра по метаданным")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание фильтра по метаданным (Название файла)")
    @Description("Тест проверяет возможность создания фильтра по метаданным (Название файла)")
    public void testAddMetadataFilterFileName(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddMetadataFilterRequestBody requestBody = AddMetadataFilterRequestBody.builder()
                .name(String.format("Фильтр по файлам c именами, содержащими текст metadataForTest %s", getRandomName()))
                .dataSearchType(DataSearchType.Document.name())
                .filterAttributeValues(Collections.singletonList(
                        MetadataFilterAttributeValues.getAttributesForFileName("metadataForTest"))
                )
                .build();

        CommonMetadataFilterResponseBody responseBody = ApiMethodsMetadataFilter.addMetadataFilter(requestBody).as(CommonMetadataFilterResponseBody.class);

        assertThat(isValidUUID(responseBody.id)).isTrue();
        assertThat(responseBody.dataSearchType).isEqualTo(requestBody.dataSearchType);
        assertThat(responseBody.name).isEqualTo(requestBody.name);
        assertThat(responseBody.deletedUtc).isNull();
        assertThat(responseBody.attributeValues).usingRecursiveComparison().ignoringFields("name").isEqualTo(requestBody.filterAttributeValues);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Фильтр по метаданным")
    @Story("Создание фильтра по метаданным")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание фильтра по метаданным (Расположение файла)")
    @Description("Тест проверяет возможность создания фильтра по метаданным (Расположение файла)")
    public void testAddMetadataFilterFilePathOneFilePath(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddMetadataFilterRequestBody requestBody = AddMetadataFilterRequestBody.builder()
                .name(String.format("Фильтр по файлам, расположенным в D:\\filesToSearchTest и D:\\WORK Projects\\eDiscovery %s", getRandomName()))
                .dataSearchType(DataSearchType.Document.name())
                .filterAttributeValues(Collections.singletonList(
                                MetadataFilterAttributeValues.getAttributesForFilePath(
                                        Collections.singletonList("D:\\WORK Projects")
                                )
                        )
                )
                .build();

        CommonMetadataFilterResponseBody responseBody = ApiMethodsMetadataFilter.addMetadataFilter(requestBody).as(CommonMetadataFilterResponseBody.class);

        assertThat(isValidUUID(responseBody.id)).isTrue();
        assertThat(responseBody.dataSearchType).isEqualTo(requestBody.dataSearchType);
        assertThat(responseBody.name).isEqualTo(requestBody.name);
        assertThat(responseBody.deletedUtc).isNull();
        assertThat(responseBody.attributeValues).usingRecursiveComparison().ignoringFields("name").isEqualTo(requestBody.filterAttributeValues);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Фильтр по метаданным")
    @Story("Создание фильтра по метаданным")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание фильтра по метаданным (Дата создания файла)")
    @Description("Тест проверяет возможность создания фильтра по метаданным (Дата создания файла)")
    public void testAddMetadataFilterFileCreationDateWithFromTo(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddMetadataFilterRequestBody requestBody = AddMetadataFilterRequestBody.builder()
                .name(String.format("Фильтр по файлам, созданным с 17 марта 2022 по 20 октября 2024 %s", getRandomName()))
                .dataSearchType(DataSearchType.Document.name())
                .filterAttributeValues(Collections.singletonList(
                        MetadataFilterAttributeValues.getAttributesForFileCreationDate(
                                LocalDate.of(2022, 3, 17),
                                LocalDate.of(2024, 10, 20)
                        )
                ))
                .build();

        CommonMetadataFilterResponseBody responseBody = ApiMethodsMetadataFilter.addMetadataFilter(requestBody).as(CommonMetadataFilterResponseBody.class);

        assertThat(isValidUUID(responseBody.id)).isTrue();
        assertThat(responseBody.dataSearchType).isEqualTo(requestBody.dataSearchType);
        assertThat(responseBody.name).isEqualTo(requestBody.name);
        assertThat(responseBody.deletedUtc).isNull();
        assertThat(responseBody.attributeValues).usingRecursiveComparison().ignoringFields("name").isEqualTo(requestBody.filterAttributeValues);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Фильтр по метаданным")
    @Story("Создание фильтра по метаданным")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание фильтра по метаданным (Дата последнего редактирования файла)")
    @Description("Тест проверяет возможность создания фильтра по метаданным (Дата последнего редактирования файла)")
    public void testAddMetadataFilterFileLastEditingDateWithFromTo(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddMetadataFilterRequestBody requestBody = AddMetadataFilterRequestBody.builder()
                .name(String.format("Фильтр по файлам, изменённым с 6 мая 2022 по 26 октября 2024 %s", getRandomName()))
                .dataSearchType(DataSearchType.Document.name())
                .filterAttributeValues(Collections.singletonList(
                        MetadataFilterAttributeValues.getAttributesForFileLastEditingDate(
                                LocalDate.of(2022, 5, 6),
                                LocalDate.of(2024, 10, 26)
                        )
                ))
                .build();

        CommonMetadataFilterResponseBody responseBody = ApiMethodsMetadataFilter.addMetadataFilter(requestBody).as(CommonMetadataFilterResponseBody.class);

        assertThat(isValidUUID(responseBody.id)).isTrue();
        assertThat(responseBody.dataSearchType).isEqualTo(requestBody.dataSearchType);
        assertThat(responseBody.name).isEqualTo(requestBody.name);
        assertThat(responseBody.deletedUtc).isNull();
        assertThat(responseBody.attributeValues).usingRecursiveComparison().ignoringFields("name").isEqualTo(requestBody.filterAttributeValues);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Фильтр по метаданным")
    @Story("Изменение фильтра по метаданным")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Изменение фильтра по метаданным (Добавление фильтра)")
    @Description("Тест проверяет возможность изменения фильтра по метаданным (Добавление фильтра)")
    public void testUpdateMetadataFilter(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddMetadataFilterRequestBody requestBodyCreation = AddMetadataFilterRequestBody.builder()
                .name(getRandomName())
                .dataSearchType(DataSearchType.Document.name())
                .filterAttributeValues(Collections.singletonList(
                    MetadataFilterAttributeValues.getAttributesForFileSize(1,100)
                ))
                .build();

        CommonMetadataFilterResponseBody responseBodyCreation = ApiMethodsMetadataFilter.addMetadataFilter(requestBodyCreation).as(CommonMetadataFilterResponseBody.class);

        UpdateMetadataFilterRequestBody requestBody = UpdateMetadataFilterRequestBody.builder()
                .id(responseBodyCreation.id)
                .name(getRandomName())
                .dataSearchType(DataSearchType.Document.name())
                .filterAttributeValues(
                        List.of(
                                new MetadataFilterAttributeValue(
                                        responseBodyCreation.attributeValues.get(0).id,
                                        responseBodyCreation.attributeValues.get(0).value
                                ),
                                MetadataFilterAttributeValues.getAttributesForFileType(Collections.singletonList(FileTypes.Document.name()))
                        )
                )
                .build();

        CommonMetadataFilterResponseBody responseBody = ApiMethodsMetadataFilter.updateMetadataFilter(requestBody).as(CommonMetadataFilterResponseBody.class);

        assertThat(responseBody.id).isEqualTo(requestBody.id);
        assertThat(responseBody.dataSearchType).isEqualTo(requestBody.dataSearchType);
        assertThat(responseBody.name).isEqualTo(requestBody.name);
        assertThat(responseBody.deletedUtc).isNull();
        assertThat(responseBody.attributeValues).usingRecursiveComparison().ignoringFields("name").isEqualTo(requestBody.filterAttributeValues);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Фильтр по метаданным")
    @Story("Удаление фильтра по метаданным")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Удаление фильтра по метаданным")
    @Description("Тест проверяет возможность удаления фильтра по метаданным")
    public void testDeleteMetadataFilter(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());

        CommonMetadataFilterResponseBody responseBody = DataGeneratorMetadataFilter.createBasicMetadataFilter();

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200WithEmptyBody());
        ApiMethodsMetadataFilter.deleteMetadataFilter(responseBody.id);
    }


    @Test
    @Epic("Сервис Deal")
    @Feature("Фильтр по метаданным")
    @Story("Получение списка фильтров по метаданным")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка фильтров по метаданным")
    @Description("Тест проверяет возможность получения фильтров по метаданным")
    public void testGetMetadataFilterList(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorMetadataFilter.createBasicMetadataFilter();

        List<CommonMetadataFilterResponseBody> responseBody = ApiMethodsMetadataFilter.getMetadataFilterList().jsonPath().getList("", CommonMetadataFilterResponseBody.class);

        assertThat(responseBody).isNotNull();
        assertThat(responseBody).isNotEmpty();
        assertThat(responseBody.get(0)).isNotNull();

        assertThat(isValidUUID(responseBody.get(0).id)).isTrue();
        assertThat(responseBody.get(0).name).isNotBlank();
        assertThat(responseBody.get(0).dataSearchType).isNotBlank();

        assertThat(responseBody.get(0).attributeValues).isNotNull();
        assertThat(responseBody.get(0).attributeValues.get(0)).isNotNull();

        assertThat(isValidUUID(responseBody.get(0).attributeValues.get(0).id)).isTrue();
        assertThat(responseBody.get(0).attributeValues.get(0).name).isNotBlank();
        assertThat(responseBody.get(0).attributeValues.get(0).value).isNotBlank();

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Фильтр по метаданным")
    @Story("Получение списка фильтров по метаданным")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка фильтров по метаданным по протоколу odata")
    @Description("Тест проверяет возможность получения фильтров по метаданным по протоколу odata")
    public void testGetMetadataFilterListOData(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorMetadataFilter.createBasicMetadataFilter();

        List<CommonMetadataFilterResponseBody> responseBody = ApiMethodsMetadataFilter.getMetadataFilterListOData().jsonPath().getList("value", CommonMetadataFilterResponseBody.class);

        assertThat(responseBody).isNotNull();
        assertThat(responseBody).isNotEmpty();
        assertThat(responseBody.get(0)).isNotNull();

        assertThat(isValidUUID(responseBody.get(0).id)).isTrue();
        assertThat(responseBody.get(0).name).isNotBlank();
        assertThat(responseBody.get(0).dataSearchType).isNotBlank();

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Фильтр по метаданным")
    @Story("Получение фильтра по метаданным по id")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение фильтра по метаданным по id")
    @Description("Тест проверяет возможность получения фильтра по метаданным по id")
    public void testGetMetadataFilterById(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonMetadataFilterResponseBody responseBodyCreation = DataGeneratorMetadataFilter.createBasicMetadataFilter();

        CommonMetadataFilterResponseBody responseBody = ApiMethodsMetadataFilter.getMetadataFilterById(responseBodyCreation.id).as(CommonMetadataFilterResponseBody.class);

        assertThat(responseBody.id).isEqualTo(responseBodyCreation.id);
        assertThat(responseBody.name).isEqualTo(responseBodyCreation.name);
        assertThat(responseBody.dataSearchType).isEqualTo(responseBodyCreation.dataSearchType);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Фильтр по метаданным")
    @Story("Получение фильтра по метаданным по id")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение фильтра по метаданным по id в path param")
    @Description("Тест проверяет возможность получения фильтра по метаданным по id в path param")
    public void testGetMetadataFilterByIdPath(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonMetadataFilterResponseBody responseBodyCreation = DataGeneratorMetadataFilter.createBasicMetadataFilter();

        CommonMetadataFilterResponseBody responseBody = ApiMethodsMetadataFilter.getMetadataFilterByIdPath(responseBodyCreation.id).as(CommonMetadataFilterResponseBody.class);

        assertThat(responseBody.id).isEqualTo(responseBodyCreation.id);
        assertThat(responseBody.name).isEqualTo(responseBodyCreation.name);
        assertThat(responseBody.dataSearchType).isEqualTo(responseBodyCreation.dataSearchType);

    }

}
