package eDiscovery.tests.apiTests.negativeTestsWithValidData.dealService;

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

import java.time.LocalDate;
import java.util.Collections;

import static eDiscovery.data.DataGeneratorCommon.getRandomName;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Negative tests with valid data: Deal - MetadataFilter")
public class MetadataFilterNegativeTestsWithValidDataTests extends TestBase {

    @Test
    @Epic("Сервис Deal")
    @Feature("Фильтры по метаданным")
    @Story("Создание фильтра по метаданным фильтра по метаданным (Размер файла), в котором From больше чем To")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность создания")
    @Description("Тест проверяет невозможность создания фильтра по метаданным (Размер файла), в котором From больше чем To")
    public void testAddMetadataFilterFileSizeWhereFromLargeThanTo(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());

        AddMetadataFilterRequestBody requestBody = AddMetadataFilterRequestBody.builder()
                .name(String.format("Фильтр по файлам от 100_000 до 5_000 байт %s", getRandomName()))
                .dataSearchType(DataSearchType.Document.name())
                .filterAttributeValues(Collections.singletonList(
                        MetadataFilterAttributeValues.getAttributesForFileSize(
                                100_000,
                                5_000)
                ))
                .build();

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());
        ErrorModel responseBody = ApiMethodsMetadataFilter.addMetadataFilter(requestBody).as(ErrorModel.class);

        assertThat(responseBody.type).isEqualTo(ErrorDescription.ERRORS_VALIDATION_EXCEPTION);
        assertThat(responseBody.message).matches(ErrorDescription.FROM_MUST_BE_LESS_OR_EQUAL_TO);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Фильтры по метаданным")
    @Story("Создание фильтра по метаданным")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность создания фильтра по метаданным (Дата создания файла), в котором From больше чем To")
    @Description("Тест проверяет невозможность создания фильтра по метаданным (Дата создания файла), в котором From больше чем To")
    public void testAddMetadataFilterFileCreationDateWhereFromLargeThanTo(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());

        AddMetadataFilterRequestBody requestBody = AddMetadataFilterRequestBody.builder()
                .name(String.format("Фильтр по файлам, созданным с 17 марта 2022 по 20 октября 2024 %s", getRandomName()))
                .dataSearchType(DataSearchType.Document.name())
                .filterAttributeValues(Collections.singletonList(
                        MetadataFilterAttributeValues.getAttributesForFileCreationDate(
                                LocalDate.of(2022, 3, 17),
                                LocalDate.of(2019, 10, 20)
                        )
                ))
                .build();

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());
        ErrorModel responseBody = ApiMethodsMetadataFilter.addMetadataFilter(requestBody).as(ErrorModel.class);

        assertThat(responseBody.type).isEqualTo(ErrorDescription.ERRORS_VALIDATION_EXCEPTION);
        assertThat(responseBody.message).matches(ErrorDescription.FROM_MUST_BE_LESS_OR_EQUAL_TO);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Фильтры по метаданным")
    @Story("Создание фильтра по метаданным")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность создания фильтра по метаданным (Дата последнего редактирования файла), в котором From больше чем To")
    @Description("Тест проверяет невозможность создания фильтра по метаданным (Дата последнего редактирования файла), в котором From больше чем To")
    public void testAddMetadataFilterFileLastEditingDateWhereFromLargeThanTo(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());

        AddMetadataFilterRequestBody requestBody = AddMetadataFilterRequestBody.builder()
                .name(String.format("Фильтр по файлам, созданным с 17 марта 2022 по 20 октября 2024 %s", getRandomName()))
                .dataSearchType(DataSearchType.Document.name())
                .filterAttributeValues(Collections.singletonList(
                        MetadataFilterAttributeValues.getAttributesForFileLastEditingDate(
                                LocalDate.of(2022, 3, 17),
                                LocalDate.of(2019, 10, 20)
                        )
                ))
                .build();

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());
        ErrorModel responseBody = ApiMethodsMetadataFilter.addMetadataFilter(requestBody).as(ErrorModel.class);

        assertThat(responseBody.type).isEqualTo(ErrorDescription.ERRORS_VALIDATION_EXCEPTION);
        assertThat(responseBody.message).matches(ErrorDescription.FROM_MUST_BE_LESS_OR_EQUAL_TO);

    }

}
