package eDiscovery.tests.apiTests.extendedPositiveTests.classifierService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.classifier.ApiMethodsMarkerResult;
import eDiscovery.helpers.OdataParametersBuilder;
import eDiscovery.models.classifier.markerResult.CommonMarkerResultResponseModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

@DisplayName("Extended positive tests: Classifier - MarkerResult")
public class MarkerResultExtendedPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Classifier")
    @Feature("Результаты маркирования")
    @Story("Получение результатов маркирования")
    @Tag("webui")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение результатов маркирования для отображения в списке")
    @Description("Тест проверяет возможность получения результатов маркирования для отображения в списке")
    public void testGetMarkerResultListODataWEBUI(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("((contains(tolower(fileName),'')) or (contains(tolower(filePath),'')) or (contains(tolower(innerFileName),'')) or (contains(tolower(innerFilePath),'')))")
                .withExpand("startMarker,resultMarker,startPolicy,resultPolicy")
                .withOrderBy("actionDate desc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonMarkerResultResponseModel> responseBody = ApiMethodsMarkerResult.getMarkerResultListOData(params).jsonPath().getList("value", CommonMarkerResultResponseModel.class);

    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Результаты маркирования")
    @Story("Получение результатов маркирования")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение результатов маркирования для отображения в списке с фильтром по периоду")
    @Description("Тест проверяет возможность получения результатов маркирования для отображения в списке с фильтром по периоду")
    public void testGetMarkerResultListODataWEBUIWithFilterActionDate(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("((contains(tolower(fileName),'')) or (contains(tolower(filePath),'')) or (contains(tolower(innerFileName),'')) or (contains(tolower(innerFilePath),''))) and actionDate ge 2024-12-03T22:00:00.000Z and actionDate le 2024-12-06T21:59:59.999Z")
                .withExpand("startMarker,resultMarker,startPolicy,resultPolicy")
                .withOrderBy("actionDate desc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonMarkerResultResponseModel> responseBody = ApiMethodsMarkerResult.getMarkerResultListOData(params).jsonPath().getList("value", CommonMarkerResultResponseModel.class);

    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Результаты маркирования")
    @Story("Получение результатов маркирования")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение результатов маркирования для отображения в списке с фильтром по Метка до (Нет метки)")
    @Description("Тест проверяет возможность получения результатов маркирования для отображения в списке с фильтром по Метка до (Нет метки)")
    public void testGetMarkerResultListODataWEBUIWithFilterStartMarkerNoMarker(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("((contains(tolower(fileName),'')) or (contains(tolower(filePath),'')) or (contains(tolower(innerFileName),'')) or (contains(tolower(innerFilePath),''))) and startMarker eq null")
                .withExpand("startMarker,resultMarker,startPolicy,resultPolicy")
                .withOrderBy("actionDate desc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonMarkerResultResponseModel> responseBody = ApiMethodsMarkerResult.getMarkerResultListOData(params).jsonPath().getList("value", CommonMarkerResultResponseModel.class);

    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Результаты маркирования")
    @Story("Получение результатов маркирования")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение результатов маркирования для отображения в списке с фильтром по Метка до (Неизвестная метка)")
    @Description("Тест проверяет возможность получения результатов маркирования для отображения в списке с фильтром по Метка до (Неизвестная метка)")
    public void testGetMarkerResultListODataWEBUIWithFilterStartMarkerUnknownMarker(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("((contains(tolower(fileName),'')) or (contains(tolower(filePath),'')) or (contains(tolower(innerFileName),'')) or (contains(tolower(innerFilePath),''))) and isStartMarkerFinded eq false")
                .withExpand("startMarker,resultMarker,startPolicy,resultPolicy")
                .withOrderBy("actionDate desc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonMarkerResultResponseModel> responseBody = ApiMethodsMarkerResult.getMarkerResultListOData(params).jsonPath().getList("value", CommonMarkerResultResponseModel.class);

    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Результаты маркирования")
    @Story("Получение результатов маркирования")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение результатов маркирования для отображения в списке с фильтром по Метка до")
    @Description("Тест проверяет возможность получения результатов маркирования для отображения в списке с фильтром по Метка до")
    public void testGetMarkerResultListODataWEBUIWithFilterStartMarker(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("((contains(tolower(fileName),'')) or (contains(tolower(filePath),'')) or (contains(tolower(innerFileName),'')) or (contains(tolower(innerFilePath),''))) and startMarker/id eq 0000e83a-0e36-49aa-a3dd-791439391336")
                .withExpand("startMarker,resultMarker,startPolicy,resultPolicy")
                .withOrderBy("actionDate desc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonMarkerResultResponseModel> responseBody = ApiMethodsMarkerResult.getMarkerResultListOData(params).jsonPath().getList("value", CommonMarkerResultResponseModel.class);

    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Результаты маркирования")
    @Story("Получение результатов маркирования")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение результатов маркирования для отображения в списке с фильтром по Метка после (Нет метки)")
    @Description("Тест проверяет возможность получения результатов маркирования для отображения в списке с фильтром по Метка после (Нет метки)")
    public void testGetMarkerResultListODataWEBUIWithFilterResultMarkerNoMarker(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("((contains(tolower(fileName),'')) or (contains(tolower(filePath),'')) or (contains(tolower(innerFileName),'')) or (contains(tolower(innerFilePath),''))) and resultMarker eq null")
                .withExpand("startMarker,resultMarker,startPolicy,resultPolicy")
                .withOrderBy("actionDate desc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonMarkerResultResponseModel> responseBody = ApiMethodsMarkerResult.getMarkerResultListOData(params).jsonPath().getList("value", CommonMarkerResultResponseModel.class);

    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Результаты маркирования")
    @Story("Получение результатов маркирования")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение результатов маркирования для отображения в списке с фильтром по Метка после (Неизвестная метка)")
    @Description("Тест проверяет возможность получения результатов маркирования для отображения в списке с фильтром по Метка после (Неизвестная метка)")
    public void testGetMarkerResultListODataWEBUIWithFilterResultMarkerUnknownMarker(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("((contains(tolower(fileName),'')) or (contains(tolower(filePath),'')) or (contains(tolower(innerFileName),'')) or (contains(tolower(innerFilePath),''))) and isResultMarkerFinded eq false")
                .withExpand("startMarker,resultMarker,startPolicy,resultPolicy")
                .withOrderBy("actionDate desc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonMarkerResultResponseModel> responseBody = ApiMethodsMarkerResult.getMarkerResultListOData(params).jsonPath().getList("value", CommonMarkerResultResponseModel.class);

    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Результаты маркирования")
    @Story("Получение результатов маркирования")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение результатов маркирования для отображения в списке с фильтром по Метка после")
    @Description("Тест проверяет возможность получения результатов маркирования для отображения в списке с фильтром по Метка после")
    public void testGetMarkerResultListODataWEBUIWithFilterResultMarker(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("((contains(tolower(fileName),'')) or (contains(tolower(filePath),'')) or (contains(tolower(innerFileName),'')) or (contains(tolower(innerFilePath),''))) and resultMarker/id eq 0000e83a-0e36-49aa-a3dd-791439391336")
                .withExpand("startMarker,resultMarker,startPolicy,resultPolicy")
                .withOrderBy("actionDate desc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonMarkerResultResponseModel> responseBody = ApiMethodsMarkerResult.getMarkerResultListOData(params).jsonPath().getList("value", CommonMarkerResultResponseModel.class);

    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Результаты маркирования")
    @Story("Получение результатов маркирования")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение результатов маркирования для отображения в списке с фильтром по Политика до (Нет политики)")
    @Description("Тест проверяет возможность получения результатов маркирования для отображения в списке с фильтром по Политика до (Нет политики)")
    public void testGetMarkerResultListODataWEBUIWithFilterStartPolicyNoPolicy(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("((contains(tolower(fileName),'')) or (contains(tolower(filePath),'')) or (contains(tolower(innerFileName),'')) or (contains(tolower(innerFilePath),''))) and startPolicy eq null")
                .withExpand("startMarker,resultMarker,startPolicy,resultPolicy")
                .withOrderBy("actionDate desc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonMarkerResultResponseModel> responseBody = ApiMethodsMarkerResult.getMarkerResultListOData(params).jsonPath().getList("value", CommonMarkerResultResponseModel.class);

    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Результаты маркирования")
    @Story("Получение результатов маркирования")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение результатов маркирования для отображения в списке с фильтром по Политика до (Неизвестная политика)")
    @Description("Тест проверяет возможность получения результатов маркирования для отображения в списке с фильтром по Политика до (Неизвестная политика)")
    public void testGetMarkerResultListODataWEBUIWithFilterStartPolicyUnknownPolicy(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("((contains(tolower(fileName),'')) or (contains(tolower(filePath),'')) or (contains(tolower(innerFileName),'')) or (contains(tolower(innerFilePath),''))) and isStartPolicyFinded eq false")
                .withExpand("startMarker,resultMarker,startPolicy,resultPolicy")
                .withOrderBy("actionDate desc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonMarkerResultResponseModel> responseBody = ApiMethodsMarkerResult.getMarkerResultListOData(params).jsonPath().getList("value", CommonMarkerResultResponseModel.class);

    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Результаты маркирования")
    @Story("Получение результатов маркирования")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение результатов маркирования для отображения в списке с фильтром по Политика до")
    @Description("Тест проверяет возможность получения результатов маркирования для отображения в списке с фильтром по Политика до")
    public void testGetMarkerResultListODataWEBUIWithFilterStartPolicy(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("((contains(tolower(fileName),'')) or (contains(tolower(filePath),'')) or (contains(tolower(innerFileName),'')) or (contains(tolower(innerFilePath),''))) and startPolicy/id eq 14f8ee3e-a1bd-46c5-991e-5bab450c0496")
                .withExpand("startMarker,resultMarker,startPolicy,resultPolicy")
                .withOrderBy("actionDate desc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonMarkerResultResponseModel> responseBody = ApiMethodsMarkerResult.getMarkerResultListOData(params).jsonPath().getList("value", CommonMarkerResultResponseModel.class);

    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Результаты маркирования")
    @Story("Получение результатов маркирования")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение результатов маркирования для отображения в списке с фильтром по Политика после (Нет политики)")
    @Description("Тест проверяет возможность получения результатов маркирования для отображения в списке с фильтром по Политика после (Нет политики)")
    public void testGetMarkerResultListODataWEBUIWithFilterResultPolicyNoPolicy(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("((contains(tolower(fileName),'')) or (contains(tolower(filePath),'')) or (contains(tolower(innerFileName),'')) or (contains(tolower(innerFilePath),''))) and resultPolicy eq null")
                .withExpand("startMarker,resultMarker,startPolicy,resultPolicy")
                .withOrderBy("actionDate desc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonMarkerResultResponseModel> responseBody = ApiMethodsMarkerResult.getMarkerResultListOData(params).jsonPath().getList("value", CommonMarkerResultResponseModel.class);

    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Результаты маркирования")
    @Story("Получение результатов маркирования")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение результатов маркирования для отображения в списке с фильтром по Политика после (Неизвестная политика)")
    @Description("Тест проверяет возможность получения результатов маркирования для отображения в списке с фильтром по Политика после (Неизвестная политика)")
    public void testGetMarkerResultListODataWEBUIWithFilterResultPolicyUnknownPolicy(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("((contains(tolower(fileName),'')) or (contains(tolower(filePath),'')) or (contains(tolower(innerFileName),'')) or (contains(tolower(innerFilePath),''))) and isResultPolicyFinded eq false")
                .withExpand("startMarker,resultMarker,startPolicy,resultPolicy")
                .withOrderBy("actionDate desc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonMarkerResultResponseModel> responseBody = ApiMethodsMarkerResult.getMarkerResultListOData(params).jsonPath().getList("value", CommonMarkerResultResponseModel.class);

    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Результаты маркирования")
    @Story("Получение результатов маркирования")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение результатов маркирования для отображения в списке с фильтром по Политика после")
    @Description("Тест проверяет возможность получения результатов маркирования для отображения в списке с фильтром по Политика после")
    public void testGetMarkerResultListODataWEBUIWithFilterSResultPolicy(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("((contains(tolower(fileName),'')) or (contains(tolower(filePath),'')) or (contains(tolower(innerFileName),'')) or (contains(tolower(innerFilePath),''))) and resultPolicy/id eq 14f8ee3e-a1bd-46c5-991e-5bab450c0496")
                .withExpand("startMarker,resultMarker,startPolicy,resultPolicy")
                .withOrderBy("actionDate desc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonMarkerResultResponseModel> responseBody = ApiMethodsMarkerResult.getMarkerResultListOData(params).jsonPath().getList("value", CommonMarkerResultResponseModel.class);

    }

}
