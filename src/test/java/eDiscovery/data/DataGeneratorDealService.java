package eDiscovery.data;

import com.github.javafaker.Faker;
import eDiscovery.apiMethods.deal.ApiMethodsDealManipulation;
import eDiscovery.apiMethods.deal.ApiMethodsSearchPlace;
import eDiscovery.apiMethods.deal.ApiMethodsSearchQuery;
import eDiscovery.helpers.enums.DealStatus;
import eDiscovery.models.deal.dealManipulation.AddDealManipulationRequestModel;
import eDiscovery.models.deal.dealManipulation.CommonDealManipulationResponseModel;
import eDiscovery.models.deal.searchPlace.AddSearchPlaceRequestModel;
import eDiscovery.models.deal.searchPlace.CommonSearchPlaceResponseModel;
import eDiscovery.models.deal.searchQuery.AddSearchQueryRequestModel;
import eDiscovery.helpers.enums.SearchPlaceCategoryType;
import eDiscovery.helpers.enums.SearchPlaceType;
import eDiscovery.helpers.enums.SearchQueryType;
import eDiscovery.models.deal.searchQuery.CommonSearchQueryResponseModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class DataGeneratorDealService {

    public static Faker faker = new Faker();

    /*
    * SearchPlace
    * */

    /*
    * SearchPlace models
    * */

    public static AddSearchPlaceRequestModel getSearchPlaceModelWithOnlyRequiredParameters(){
        return AddSearchPlaceRequestModel.builder()
                .name(getRandomName())
                .build();
    }

    public static AddSearchPlaceRequestModel getBasicSearchPlaceModel(SearchPlaceCategoryType categoryType, SearchPlaceType type){
        return AddSearchPlaceRequestModel.builder()
                .name(getRandomName())
                .categoryType(categoryType)
                .type(type)
                .parameters(null)
                .excludes(null)
                .build();
    }

    public static AddSearchPlaceRequestModel getBasicSearchPlaceModelArmLocal(){
        return getBasicSearchPlaceModel(SearchPlaceCategoryType.ARM, SearchPlaceType.LOCAL);
    }

    public static AddSearchPlaceRequestModel getBasicSearchPlaceModelFileShareSMB(){
        return getBasicSearchPlaceModel(SearchPlaceCategoryType.FileShare, SearchPlaceType.SMB);
    }

    /*
    * SearchPlace generators
    * */

    public static CommonSearchPlaceResponseModel createSearchPlaceWithOnlyRequiredParameters(){
        return ApiMethodsSearchPlace.addSearchPlace(getSearchPlaceModelWithOnlyRequiredParameters()).as(CommonSearchPlaceResponseModel.class);
    }

    public static CommonSearchPlaceResponseModel createBasicSearchPlaceArmLocal(){
        return ApiMethodsSearchPlace.addSearchPlace(getBasicSearchPlaceModelArmLocal()).as(CommonSearchPlaceResponseModel.class);
    }

    public static CommonSearchPlaceResponseModel createBasicSearchPlaceFileShareSMB(){
        return ApiMethodsSearchPlace.addSearchPlace(getBasicSearchPlaceModelFileShareSMB()).as(CommonSearchPlaceResponseModel.class);
    }

    /*
     * SearchPlace attributes
     * */

    public static String[] getValidSearchPlaceNames(){
        return getValidNames();
    }

    public static SearchPlaceCategoryType[] getValidSearchPlaceCategoryTypes(){
        return SearchPlaceCategoryType.values();
    }

    public static SearchPlaceType[] getValidSearchPlaceTypes(){
        return SearchPlaceType.values();
    }

    public static String[] getValidSearchPlaceExclusions(){
        return getValidFolderExclusions();
    }

    public static Stream<List<String>>  getValidSearchPlaceExclusionsWithDifferentCount(){
        return getValidFolderExclusionsWithDifferentCount();
    }

    /*
    * SearchPlace URI Parameters
    * */

    public static String[] getValidSearchPlaceURIInParameters(){
        return new String[]{

                "smb://server/share",
                "\\server\\share",
                "\\\\server\\share",

                "smb://user@server/share",
                "\\user@server\\share",
                "\\\\user@server\\share",

                "smb://192.168.1.1/share",
                "\\192.168.1.1\\share",
                "\\\\192.168.1.1\\share",

                "smb://server/share/subdir",

                "\\server\\share\\subdir",
                "\\\\server\\share\\subdir",

                faker.regexify("[a-z]{1}"),             // Строка длиной 1 символ
                faker.regexify("[a-z]{500}")            // Строка длиной 500 символов
        };
    }

    public static String[] getValidSearchPlaceUsernamesInParameters() {
        return getValidNames();
    }

    public static String[] getValidSearchPlacePasswordsInParameters(){
        return new  String[]{
                faker.regexify("[a-z]{1}"),             // Строка из 1 символа
                faker.regexify("[a-z]{256}"),           // Строка из 256 символов
                faker.regexify("[а-я]{25}"),            // Строка из русских символов в нижней раскладке
                faker.regexify("[А-Я]{25}"),            // Строка из русских символов в верхней раскладке
                faker.regexify("[А-Яа-я]{25}"),         // Строка из русских символов в смешанной раскладке
                faker.regexify("[a-z]{25}"),            // Строка из английских символов в нижней раскладке
                faker.regexify("[A-Z]{25}"),            // Строка из английских символов в верхней раскладке
                faker.regexify("[A-Za-z]{25}"),         // Строка из английских символов в смешанной раскладке
                faker.regexify("[A-Za-zА-Яа-я]{25}"),   // Строка из английских и русских символов в смешанной раскладке
                faker.regexify("[0-9]{25}"),            // Строка из цифр
                faker.regexify("[A-Za-z0-9]{25}"),      // Строка из английских символов вперемешку с цифрами
                faker.regexify("[А-Яа-я0-9]{25}"),      // Строка из русских символов вперемешку с цифрами
                "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~",   // Строка из спецсимволов
                "ё"                                     // Буква "ё"
        };
    };

    /*
    * Search Query
    * */

    public static AddSearchQueryRequestModel getSearchQueryModelWithOnlyRequiredParameters(){
        return AddSearchQueryRequestModel.builder()
                .name(getRandomName())
                .value("\\d{10}")
                .build();
    }

    public static CommonSearchQueryResponseModel createSearchQueryWithOnlyRequiredParameters(){
        return ApiMethodsSearchQuery.addSearchQuery(getSearchQueryModelWithOnlyRequiredParameters()).as(CommonSearchQueryResponseModel.class);
    }

    public static AddSearchQueryRequestModel getBasicSearchQueryModel(){
        return AddSearchQueryRequestModel.builder()
                .name(getRandomName())
                .type(SearchQueryType.Regex)
                .value("\\d{10}")
                .build();
    }

    public static CommonSearchQueryResponseModel createBasicSearchQuery(){
        return ApiMethodsSearchQuery.addSearchQuery(getBasicSearchQueryModel()).as(CommonSearchQueryResponseModel.class);
    }

    /*
    * Search Query attributes
    * */

    public static String[] getValidSearchQueryNames(){
        return getValidNames();
    }

    public static SearchQueryType[] getValidSearchQueryTypes(){
        return SearchQueryType.values();
    }

    public static String[] getValidSearchQueryValues(){
        return getValidNames();
    }

    /*
    * Deal manipulation
    * */

    public static AddDealManipulationRequestModel getDealManipulationModelWithOnlyRequiredParameters(List<String> searchPlaceIDs, List<String> searchQueryIDs){
        return AddDealManipulationRequestModel.builder()
                .name(getRandomName())
                .searchPlaces(searchPlaceIDs)
                .searchQueueries(searchQueryIDs)
                .build();

    }

    public static CommonDealManipulationResponseModel createDealManipulationWithOnlyRequiredParameters(List<String> searchPlaceIDs, List<String> searchQueryIDs){
        AddDealManipulationRequestModel requestDealCreationBody = getDealManipulationModelWithOnlyRequiredParameters(
                searchPlaceIDs,
                searchQueryIDs
        );

        return ApiMethodsDealManipulation.addDeal(requestDealCreationBody).as(CommonDealManipulationResponseModel.class);
    }

    public static CommonDealManipulationResponseModel createDealManipulationWithOnlyRequiredParameters(){
        AddDealManipulationRequestModel requestDealCreationBody = getDealManipulationModelWithOnlyRequiredParameters(
                Collections.singletonList(createSearchPlaceWithOnlyRequiredParameters().id),
                Collections.singletonList(createSearchQueryWithOnlyRequiredParameters().id)
        );

        return ApiMethodsDealManipulation.addDeal(requestDealCreationBody).as(CommonDealManipulationResponseModel.class);
    }

    public static AddDealManipulationRequestModel getBasicDealManipulationModel(List<String> searchPlaceIDs, List<String> searchQueryIDs){
        return AddDealManipulationRequestModel.builder()
                .name(getRandomName())
                .searchPlaces(searchPlaceIDs)
                .excludes(new ArrayList<>())
                .searchQueueries(searchQueryIDs)
                .dealStatus(DealStatus.Waiting)
                .quarantine(false)
                .build();
    }

    public static CommonDealManipulationResponseModel createBasicDealManipulation(){
        AddDealManipulationRequestModel requestDealCreationBody = getBasicDealManipulationModel(
                Collections.singletonList(createBasicSearchPlaceArmLocal().id),
                Collections.singletonList(createBasicSearchQuery().id)
        );

        return ApiMethodsDealManipulation.addDeal(requestDealCreationBody).as(CommonDealManipulationResponseModel.class);
    }

    /*
    * Basic generators
    * */

    public static String[] getValidNames(){
        return new String[]{
                faker.regexify("[a-z]{1}"),             // Строка из 1 символа
                faker.regexify("[a-z]{256}"),           // Строка из 256 символов
                faker.regexify("[а-я]{25}"),            // Строка из русских символов в нижней раскладке
                faker.regexify("[А-Я]{25}"),            // Строка из русских символов в верхней раскладке
                faker.regexify("[А-Яа-я]{25}"),         // Строка из русских символов в смешанной раскладке
                faker.regexify("[a-z]{25}"),            // Строка из английских символов в нижней раскладке
                faker.regexify("[A-Z]{25}"),            // Строка из английских символов в верхней раскладке
                faker.regexify("[A-Za-z]{25}"),         // Строка из английских символов в смешанной раскладке
                faker.regexify("[A-Za-zА-Яа-я]{25}"),   // Строка из английских и русских символов в смешанной раскладке
                faker.regexify("[0-9]{25}"),            // Строка из цифр
                faker.regexify("[A-Za-z0-9]{25}"),      // Строка из английских символов вперемешку с цифрами
                faker.regexify("[А-Яа-я0-9]{25}"),      // Строка из русских символов вперемешку с цифрами
                faker.letterify("?????? ??????? ??????"),   // Строка, состоящая из нескольких слов, разделённых пробелами
                faker.letterify("??????     ??????"),       // Строка с несколькими пробелами подряд
                faker.letterify(" ??????"),                 // Строка, начинающаяся с пробела
                faker.letterify("?????? "),                 // Строка, оканчивающаяяся пробелом
                "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~" + getRandomName(),     // Строка из спецсимволов
                "ё" + getRandomName()                                       // Буква "ё"
        };
    }

    public static String[] getInvalidNames(){
        return new String[]{
                "",
                " "
        };
    }

    public static String[] getValidFolderExclusions(){
        return new String[]{
                "C:\\",                                 // Windows-style
                "C:\\Program files\\",                  // Windows-style
                "C:\\Program files\\Документ Microsoft Word.docx",          // Windows-style с путём к файлу


                "/proc",                                // Unix-style
                "/home/admin/",                         // Unix-style
                "/home/admin/Документ Microsoft Word.docx",                 // Unix-style с путём к файлу

                "smb://server/share",                   // SMB-style
                "\\server\\share",
                "\\\\server\\share",
                "\\\\server\\share\\Документ Microsoft Word.docx",            // SMB-style с путём к файлу

                "smb://user@server/share",
                "\\user@server\\share",
                "\\\\user@server\\share",

                "smb://192.168.1.1/share",
                "\\192.168.1.1\\share",
                "\\\\192.168.1.1\\share",

                "smb://server/share/subdir",

                "\\server\\share\\subdir",
                "\\\\server\\share\\subdir",

                "",                                     // Пустая строка
                faker.regexify("[a-z]{1}"),             // Строка из 1 символа
                faker.regexify("[a-z]{500}"),           // Строка из 500 символов
                faker.regexify("[а-я]{25}"),            // Строка из русских символов в нижней раскладке
                faker.regexify("[А-Я]{25}"),            // Строка из русских символов в верхней раскладке
                faker.regexify("[А-Яа-я]{25}"),         // Строка из русских символов в смешанной раскладке
                faker.regexify("[a-z]{25}"),            // Строка из английских символов в нижней раскладке
                faker.regexify("[A-Z]{25}"),            // Строка из английских символов в верхней раскладке
                faker.regexify("[A-Za-z]{25}"),         // Строка из английских символов в смешанной раскладке
                faker.regexify("[A-Za-zА-Яа-я]{25}"),   // Строка из английских и русских символов в смешанной раскладке
                faker.regexify("[0-9]{25}"),            // Строка из цифр
                faker.regexify("[A-Za-z0-9]{25}"),      // Строка из английских символов вперемешку с цифрами
                faker.regexify("[А-Яа-я0-9]{25}"),      // Строка из русских символов вперемешку с цифрами
                "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~" + getRandomName(),   // Строка из спецсимволов
                "ё" + getRandomName()                                     // Буква "ё"
        };
    }

    public static Stream<List<String>> getValidFolderExclusionsWithDifferentCount(){

        return Stream.of(
                List.of(),              // Пустой список
                List.of(
                        "C:\\"
                ),                      // Список из 1 пути
                List.of(
                        "C:\\",
                        "smb://server/share"
                )                       // Список из 2 путей
        );

    }

    public static String getRandomName(){
        return getRandomName(30);
    }

    public static String getRandomName(int length){
        return faker.regexify("[a-zA-Z]{" + length + "}");
    }
}
