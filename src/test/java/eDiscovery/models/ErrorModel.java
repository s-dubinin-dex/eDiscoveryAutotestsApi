package eDiscovery.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorModel {
    public String type;
    public String message;
    public String stackTrace;
    public ErrorData data;

    public static class ErrorData{
        public String key;
        public String type;

    }

}
