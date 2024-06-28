package eDiscovery.helpers;

public class ErrorDescription {
    public static final String DUPLICATE_KEY_SEARCH_PLACE_NAME                      = "duplicate key value violates unique constraint \"IX_SearchPlace_Name\"";

    public static final String ERRORS_ARGUMENT_EXCEPTION                            = "errors.ArgumentException";
    public static final String ERRORS_ENTITY_ALREADY_EXISTS_EXCEPTION               = "errors.EntityAlreadyExistsException";
    public static final String ERRORS_ENTITY_IN_USE_EXCEPTION_SEARCH_PLACE_INFO     = "errors.EntityInUseException.SearchPlaceInfo";
    public static final String ERRORS_ENTITY_NOT_FOUND_EXCEPTION_SEARCH_PLACE_INFO  = "errors.EntityNotFoundException.SearchPlaceInfo";

    public static final String SEARCH_PLACE_INFO                                    = "SearchPlaceInfo";

    public static final String EXCEPTION_ENTITY_NOT_FOUND_SEARCH_PLACE_INFO         = "Exception of type 'Shared.Domain.Exceptions.EntityNotFoundException`1[Deal.Application.Abstraction.Models.Queries.SearchPlaces.SearchPlaceInfo]' was thrown.";
    public static final String EXCEPTION_ENTITY_IN_USE_EXCEPTION_SEARCH_PLACE_INFO  = "Exception of type 'Shared.Domain.Exceptions.EntityInUseException`1[Deal.Application.Abstraction.Models.Queries.SearchPlaces.SearchPlaceInfo]' was thrown.";

    public static final String FOR_LOCAL_AGENT_ALLOWED_ONLY_EXCEPTIONS_UPDATE       = "Для локального агента разрешено обновлять в местах поиска только 'Excludes'.";
    public static final String FOR_FILE_SHARE_CATEGORY_USED_IN_DEAL_ALLOWED_ONLY_NAME_EXCLUDES_PARAMETERS_UPDATE = "Для категории места поиска FileShare используемых в деле разрешено обновлять только 'Name', 'Excludes' и 'Parameters'.";
    public static final String DELETING_SEARCH_PLACES_FOR_LOCAL_AGENT_IS_FORBIDDEN  = "Места поиска созданные для локального агента удалить нельзя!";

    public static final String REQUEST_VALIDATION_ERROR                             = "Request Validation Error";
    public static final String SEE_ERRORS_FOR_DETAILS                               = "See Errors for details";
    public static final String UNSUPPORTED_MEDIA_TYPE                               = "Unsupported Media Type";

    public static final String VALIDATIONS_A_NON_EMPTY_REQUEST_BODY_IS_REQUIRED     = "validations.A non-empty request body is required.";
    public static final String VALIDATIONS_THE_NAME_FIELD_IS_REQUIRED               = "validations.The Name field is required.";
    public static final String VALIDATIONS_THE_NEW_ENTITY_FIELD_IS_REQUIRED         = "validations.The newEntity field is required.";
    public static final String VALIDATIONS_THE_URI_FIELD_IS_REQUIRED                = "validations.The Uri field is required.";
    public static final String VALIDATIONS_THE_USERNAME_FIELD_IS_REQUIRED           = "validations.The Username field is required.";
    public static final String VALIDATIONS_THE_PASSWORD_FIELD_IS_REQUIRED           = "validations.The Password field is required.";
}
