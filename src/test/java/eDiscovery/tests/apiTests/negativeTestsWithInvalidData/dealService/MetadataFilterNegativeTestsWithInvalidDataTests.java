package eDiscovery.tests.apiTests.negativeTestsWithInvalidData.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsMetadataFilter;
import eDiscovery.helpers.ErrorDescription;
import eDiscovery.helpers.MetadataFilterAttributeValues;
import eDiscovery.helpers.enums.DataSearchType;
import eDiscovery.models.ErrorModel;
import eDiscovery.models.deal.metadataFilter.AddMetadataFilterRequestBody;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static eDiscovery.data.DataGeneratorCommon.getRandomName;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Negative tests with invalid data: Deal - MetadataFilter")
public class MetadataFilterNegativeTestsWithInvalidDataTests extends TestBase {

    @Test
    @Epic("Сервис Deal")
    @Feature("Фильтры по метаданным")
    @Story("Создание фильтра по метаданным")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность создания фильтра по метаданным (Размер файла) без указания отрибутов From и To")
    @Description("Тест проверяет невозможность создания фильтра по метаданным (Размер файла) без указания отрибутов From и To")
    public void testAddMetadataFilterFileSizeWithoutValuesFromTo(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());

        AddMetadataFilterRequestBody requestBody = AddMetadataFilterRequestBody.builder()
                .name(String.format("Фильтр по файлам с неуказанным размером %s", getRandomName()))
                .dataSearchType(DataSearchType.Document.name())
                .filterAttributeValues(Collections.singletonList(
                        MetadataFilterAttributeValues.getAttributesForFileSize(
                                null,
                                null)
                ))
                .build();

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());
        ErrorModel responseBody = ApiMethodsMetadataFilter.addMetadataFilter(requestBody).as(ErrorModel.class);

        assertThat(responseBody.type).isEqualTo(ErrorDescription.ERRORS_VALIDATION_EXCEPTION);
        assertThat(responseBody.message).isEqualTo(ErrorDescription.PARAMETERS_FROM_TO_CANNOT_BE_NULL_SIMULTANEOUSLY);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Фильтры по метаданным")
    @Story("Создание фильтра по метаданным")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность создания фильтра по метаданным (Тип файла) при передаче Null значения")
    @Description("Тест проверяет невозможность создания фильтра по метаданным (Тип файла) при передаче Null значения")
    public void testAddMetadataFilterFileTypeWithNullValueFileType(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());

        AddMetadataFilterRequestBody requestBody = AddMetadataFilterRequestBody.builder()
                .name(String.format("Фильтр по типу файлов с передачей пустого Value %s", getRandomName()))
                .dataSearchType(DataSearchType.Document.name())
                .filterAttributeValues(Collections.singletonList(
                                MetadataFilterAttributeValues.getAttributesForFileType(null)
                        )
                )
                .build();

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());
        ErrorModel responseBody = ApiMethodsMetadataFilter.addMetadataFilter(requestBody).as(ErrorModel.class);

        assertThat(responseBody.type).isEqualTo(ErrorDescription.ERRORS_ARGUMENT_NULL_EXCEPTION);
        assertThat(responseBody.message).isEqualTo(ErrorDescription.VALUE_CANNOT_BE_NULL_VALUE);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Фильтры по метаданным")
    @Story("Создание фильтра по метаданным")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность создания фильтра по метаданным (Тип файла) при передаче пустого списка типов документов")
    @Description("Тест проверяет невозможность создания фильтра по метаданным (Тип файла) при передаче пустого списка типов документов")
    public void testAddMetadataFilterFileTypeWithEmptyListFileTypes(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());

        AddMetadataFilterRequestBody requestBody = AddMetadataFilterRequestBody.builder()
                .name(String.format("Фильтр по типу файлов без указания типов %s", getRandomName()))
                .dataSearchType(DataSearchType.Document.name())
                .filterAttributeValues(Collections.singletonList(
                                MetadataFilterAttributeValues.getAttributesForFileType(List.of())
                        )
                )
                .build();

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());
        ErrorModel responseBody = ApiMethodsMetadataFilter.addMetadataFilter(requestBody).as(ErrorModel.class);

        assertThat(responseBody.type).isEqualTo(ErrorDescription.ERRORS_VALIDATION_EXCEPTION);
        assertThat(responseBody.message).isEqualTo(ErrorDescription.ARRAY_CAN_NOT_BE_EMPTY);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Фильтры по метаданным")
    @Story("Создание фильтра по метаданным")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность создания фильтра по метаданным (Название файла) с null name")
    @Description("Тест проверяет невозможность создания фильтра по метаданным (Название файла) с null name")
    public void testAddMetadataFilterFileNameWithNullFileName(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());

        AddMetadataFilterRequestBody requestBody = AddMetadataFilterRequestBody.builder()
                .name(String.format("Фильтр по файлам c именами, содержащим null name %s", getRandomName()))
                .dataSearchType(DataSearchType.Document.name())
                .filterAttributeValues(Collections.singletonList(
                        MetadataFilterAttributeValues.getAttributesForFileName(null))
                )
                .build();

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());
        ErrorModel responseBody = ApiMethodsMetadataFilter.addMetadataFilter(requestBody).as(ErrorModel.class);

        assertThat(responseBody.type).isEqualTo(ErrorDescription.ERRORS_ARGUMENT_NULL_EXCEPTION);
        assertThat(responseBody.message).isEqualTo(ErrorDescription.VALUE_CANNOT_BE_NULL_VALUE);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Фильтры по метаданным")
    @Story("Создание фильтра по метаданным")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность создания фильтра по метаданным (Расположение файла) с указанием пустого массива путей")
    @Description("Тест проверяет невозможность создания фильтра по метаданным (Расположение файла) с указанием пустого массива путей")
    public void testAddMetadataFilterFilePathWithEmptyListFilePaths(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());

        AddMetadataFilterRequestBody requestBody = AddMetadataFilterRequestBody.builder()
                .name(String.format("Фильтр по файлам, расположенным в пустом списке директорий %s", getRandomName()))
                .dataSearchType(DataSearchType.Document.name())
                .filterAttributeValues(Collections.singletonList(
                                MetadataFilterAttributeValues.getAttributesForFilePath(
                                        List.of()
                                )
                        )
                )
                .build();

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());
        ErrorModel responseBody = ApiMethodsMetadataFilter.addMetadataFilter(requestBody).as(ErrorModel.class);

        assertThat(responseBody.type).isEqualTo(ErrorDescription.ERRORS_VALIDATION_EXCEPTION);
        assertThat(responseBody.message).isEqualTo(ErrorDescription.ARRAY_CAN_NOT_BE_EMPTY);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Фильтры по метаданным")
    @Story("Создание фильтра по метаданным")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность создания фильтра по метаданным (Расположение файла) с указанием null value")
    @Description("Тест проверяет невозможность создания фильтра по метаданным (Расположение файла) с указанием null value")
    public void testAddMetadataFilterFilePathWithNullFilePath(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());

        AddMetadataFilterRequestBody requestBody = AddMetadataFilterRequestBody.builder()
                .name(String.format("Фильтр по файлам, расположенным в null %s", getRandomName()))
                .dataSearchType(DataSearchType.Document.name())
                .filterAttributeValues(Collections.singletonList(
                                MetadataFilterAttributeValues.getAttributesForFilePath(
                                        null
                                )
                        )
                )
                .build();

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());
        ErrorModel responseBody = ApiMethodsMetadataFilter.addMetadataFilter(requestBody).as(ErrorModel.class);

        assertThat(responseBody.type).isEqualTo(ErrorDescription.ERRORS_ARGUMENT_NULL_EXCEPTION);
        assertThat(responseBody.message).isEqualTo(ErrorDescription.VALUE_CANNOT_BE_NULL_VALUE);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Фильтры по метаданным")
    @Story("Создание фильтра по метаданным")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность создания фильтра по метаданным (Дата создания файла) без указания From или To")
    @Description("Тест проверяет невозможность создания фильтра по метаданным (Дата создания файла) без указания From или To")
    public void testAddMetadataFilterFileCreationDateWithoutFromTo(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());

        AddMetadataFilterRequestBody requestBody = AddMetadataFilterRequestBody.builder()
                .name(String.format("Фильтр по файлам без указания диапазона дат создания %s", getRandomName()))
                .dataSearchType(DataSearchType.Document.name())
                .filterAttributeValues(Collections.singletonList(
                        MetadataFilterAttributeValues.getAttributesForFileCreationDate(
                                null,
                                null
                        )
                ))
                .build();

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());
        ErrorModel responseBody = ApiMethodsMetadataFilter.addMetadataFilter(requestBody).as(ErrorModel.class);

        assertThat(responseBody.type).isEqualTo(ErrorDescription.ERRORS_VALIDATION_EXCEPTION);
        assertThat(responseBody.message).isEqualTo(ErrorDescription.PARAMETERS_FROM_TO_CANNOT_BE_NULL_SIMULTANEOUSLY);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Фильтры по метаданным")
    @Story("Создание фильтра по метаданным")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Невозможность создания фильтра по метаданным (Дата изменения файла) без указания From или To")
    @Description("Тест проверяет невозможность создания фильтра по метаданным (Дата изменения файла) без указания From или To")
    public void testAddMetadataFilterLastEditingDateWithoutFromTo(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());

        AddMetadataFilterRequestBody requestBody = AddMetadataFilterRequestBody.builder()
                .name(String.format("Фильтр по файлам без указания диапазона дат последнего изменения %s", getRandomName()))
                .dataSearchType(DataSearchType.Document.name())
                .filterAttributeValues(Collections.singletonList(
                        MetadataFilterAttributeValues.getAttributesForFileLastEditingDate(
                                null,
                                null
                        )
                ))
                .build();

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());
        ErrorModel responseBody = ApiMethodsMetadataFilter.addMetadataFilter(requestBody).as(ErrorModel.class);

        assertThat(responseBody.type).isEqualTo(ErrorDescription.ERRORS_VALIDATION_EXCEPTION);
        assertThat(responseBody.message).isEqualTo(ErrorDescription.PARAMETERS_FROM_TO_CANNOT_BE_NULL_SIMULTANEOUSLY);

    }

}
