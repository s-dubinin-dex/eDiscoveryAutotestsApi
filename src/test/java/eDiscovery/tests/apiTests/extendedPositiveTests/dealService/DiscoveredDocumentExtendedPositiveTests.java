package eDiscovery.tests.apiTests.extendedPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsDiscoveredDocument;
import eDiscovery.data.dealService.DataGeneratorDealManipulation;
import eDiscovery.helpers.OdataParametersBuilder;
import eDiscovery.helpers.enums.FileTypes;
import eDiscovery.models.deal.dealManipulation.CommonDealManipulationResponseModel;
import eDiscovery.models.deal.discoveredDocument.DiscoveredDocumentModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DisplayName("Extended positive tests: Deal - DiscoveredDocument")
public class DiscoveredDocumentExtendedPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Deal")
    @Feature("Обнаруженные документы")
    @Story("Получение списка обнаруженных документов")
    @Tag("webui")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка обнаруженных документов для карточки дела для карточки дела - список Документы")
    @Description("Тест проверяет возможность получения списка обнаруженных документов  - список Документы")
    public void testGetDiscoveredDocumentForDealCardDocumentsTab(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonDealManipulationResponseModel responseBodyDealManipulation = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

        Map<String, String> parameters = OdataParametersBuilder.builder()
                .withFilter(String.format("((contains(tolower(fileName),'')) or (contains(tolower(filePath),'')) or (contains(tolower(innerFileName),'')) or (contains(tolower(innerFilePath),''))) and dealId eq %s and searchProblemReason eq null", responseBodyDealManipulation.id))
                .withExpand("SearchPlace")
                .withOrderBy("findedUtc desc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<DiscoveredDocumentModel> responseBody = ApiMethodsDiscoveredDocument.getDiscoveredDocumentOData(parameters).jsonPath().getList("value", DiscoveredDocumentModel.class);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Обнаруженные документы")
    @Story("Получение списка обнаруженных документов")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка обнаруженных документов для карточки дела для карточки дела - список Документы с фильтром по периоду")
    @Description("Тест проверяет возможность получения списка обнаруженных документов  - список Документы с фильтром по периоду")
    public void testGetDiscoveredDocumentForDealCardDocumentsTabWithFilterFindedUtc(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonDealManipulationResponseModel responseBodyDealManipulation = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

        Map<String, String> parameters = OdataParametersBuilder.builder()
                .withFilter(String.format("((contains(tolower(fileName),'')) or (contains(tolower(filePath),'')) or (contains(tolower(innerFileName),'')) or (contains(tolower(innerFilePath),''))) and findedUtc ge 2024-12-02T22:00:00.000Z and findedUtc le 2024-12-06T21:59:59.999Z and dealId eq %s and searchProblemReason eq null", responseBodyDealManipulation.id))
                .withExpand("SearchPlace")
                .withOrderBy("findedUtc desc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<DiscoveredDocumentModel> responseBody = ApiMethodsDiscoveredDocument.getDiscoveredDocumentOData(parameters).jsonPath().getList("value", DiscoveredDocumentModel.class);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Обнаруженные документы")
    @Story("Получение списка обнаруженных документов")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка обнаруженных документов для карточки дела для карточки дела - список Документы с фильтром по месту поиска")
    @Description("Тест проверяет возможность получения списка обнаруженных документов  - список Документы с фильтром по месту поиска")
    public void testGetDiscoveredDocumentForDealCardDocumentsTabWithFilterSearchPlaceId(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonDealManipulationResponseModel responseBodyDealManipulation = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

        Map<String, String> parameters = OdataParametersBuilder.builder()
                .withFilter(String.format("SearchPlace/id eq 049400b1-f643-42dc-a1d6-1a731ce2c5e7 and ((contains(tolower(fileName),'')) or (contains(tolower(filePath),'')) or (contains(tolower(innerFileName),'')) or (contains(tolower(innerFilePath),''))) and dealId eq %s and searchProblemReason eq null", responseBodyDealManipulation.id))
                .withExpand("SearchPlace")
                .withOrderBy("findedUtc desc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<DiscoveredDocumentModel> responseBody = ApiMethodsDiscoveredDocument.getDiscoveredDocumentOData(parameters).jsonPath().getList("value", DiscoveredDocumentModel.class);
    }

    @ParameterizedTest
    @Epic("Сервис Deal")
    @Feature("Обнаруженные документы")
    @Story("Получение списка обнаруженных документов")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка обнаруженных документов для карточки дела для карточки дела - список Документы с фильтром по типу документа")
    @Description("Тест проверяет возможность получения списка обнаруженных документов  - список Документы с фильтром по типу документа")
    @MethodSource("eDiscovery.helpers.enums.FileTypes#getFileTypesAvailableForDiscoveredDocumentFilter")
    public void testGetDiscoveredDocumentForDealCardDocumentsTabWithFilterFileType(FileTypes fileType){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonDealManipulationResponseModel responseBodyDealManipulation = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

        Map<String, String> parameters = OdataParametersBuilder.builder()
                .withFilter(String.format("((contains(tolower(fileName),'')) or (contains(tolower(filePath),'')) or (contains(tolower(innerFileName),'')) or (contains(tolower(innerFilePath),''))) and fileType eq '%s' and dealId eq %s and searchProblemReason eq null", fileType.name(), responseBodyDealManipulation.id))
                .withExpand("SearchPlace")
                .withOrderBy("findedUtc desc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<DiscoveredDocumentModel> responseBody = ApiMethodsDiscoveredDocument.getDiscoveredDocumentOData(parameters).jsonPath().getList("value", DiscoveredDocumentModel.class);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Обнаруженные документы")
    @Story("Получение списка обнаруженных документов")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка обнаруженных документов для карточки дела для карточки дела - список Документы с фильтром по признаку \"в архиве\"")
    @Description("Тест проверяет возможность получения списка обнаруженных документов  - список Документы с фильтром по признаку \"в архиве\"")
    public void testGetDiscoveredDocumentForDealCardDocumentsTabWithFilterInArchive(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonDealManipulationResponseModel responseBodyDealManipulation = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

        Map<String, String> parameters = OdataParametersBuilder.builder()
                .withFilter(String.format("((contains(tolower(fileName),'')) or (contains(tolower(filePath),'')) or (contains(tolower(innerFileName),'')) or (contains(tolower(innerFilePath),''))) and innerFileName ne null and dealId eq %s and searchProblemReason eq null", responseBodyDealManipulation.id))
                .withExpand("SearchPlace")
                .withOrderBy("findedUtc desc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<DiscoveredDocumentModel> responseBody = ApiMethodsDiscoveredDocument.getDiscoveredDocumentOData(parameters).jsonPath().getList("value", DiscoveredDocumentModel.class);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Обнаруженные документы")
    @Story("Получение списка обнаруженных документов")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка обнаруженных документов для карточки дела для карточки дела - список Документы с сортировкой по месту поиска")
    @Description("Тест проверяет возможность получения списка обнаруженных документов  - список Документы с сортировкой по месту поиска")
    public void testGetDiscoveredDocumentForDealCardDocumentsTabWithSortingSearchPlace(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonDealManipulationResponseModel responseBodyDealManipulation = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

        Map<String, String> parameters = OdataParametersBuilder.builder()
                .withFilter(String.format("((contains(tolower(fileName),'')) or (contains(tolower(filePath),'')) or (contains(tolower(innerFileName),'')) or (contains(tolower(innerFilePath),''))) and dealId eq %s and searchProblemReason eq null", responseBodyDealManipulation.id))
                .withExpand("SearchPlace")
                .withOrderBy("searchPlace/name asc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<DiscoveredDocumentModel> responseBody = ApiMethodsDiscoveredDocument.getDiscoveredDocumentOData(parameters).jsonPath().getList("value", DiscoveredDocumentModel.class);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Обнаруженные документы")
    @Story("Получение списка обнаруженных документов")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка обнаруженных документов для карточки дела для карточки дела - список Документы с сортировкой по типу документа")
    @Description("Тест проверяет возможность получения списка обнаруженных документов  - список Документы с сортировкой по типу документа")
    public void testGetDiscoveredDocumentForDealCardDocumentsTabWithSortingFileType(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonDealManipulationResponseModel responseBodyDealManipulation = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

        Map<String, String> parameters = OdataParametersBuilder.builder()
                .withFilter(String.format("((contains(tolower(fileName),'')) or (contains(tolower(filePath),'')) or (contains(tolower(innerFileName),'')) or (contains(tolower(innerFilePath),''))) and dealId eq %s and searchProblemReason eq null", responseBodyDealManipulation.id))
                .withExpand("SearchPlace")
                .withOrderBy("fileType asc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<DiscoveredDocumentModel> responseBody = ApiMethodsDiscoveredDocument.getDiscoveredDocumentOData(parameters).jsonPath().getList("value", DiscoveredDocumentModel.class);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Обнаруженные документы")
    @Story("Получение списка обнаруженных документов")
    @Tag("webui")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка обнаруженных документов для карточки дела для карточки дела - список Необработанные")
    @Description("Тест проверяет возможность получения списка обнаруженных документов  - список Необработанные")
    public void testGetDiscoveredDocumentForDealCardUnprocessedTab(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonDealManipulationResponseModel responseBodyDealManipulation = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

        Map<String, String> parameters = OdataParametersBuilder.builder()
                .withFilter(String.format("((contains(tolower(fileName),'')) or (contains(tolower(filePath),'')) or (contains(tolower(innerFileName),'')) or (contains(tolower(innerFilePath),''))) and dealId eq %s and searchProblemReason ne null", responseBodyDealManipulation.id))
                .withExpand("SearchPlace")
                .withOrderBy("findedUtc desc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<DiscoveredDocumentModel> responseBody = ApiMethodsDiscoveredDocument.getDiscoveredDocumentOData(parameters).jsonPath().getList("value", DiscoveredDocumentModel.class);
    }

}
