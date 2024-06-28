package eDiscovery.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorModel {
    public String type;
    public String message;
    public String stackTrace;
    public ErrorModelData data;

    public String title;
    public int status;
    public String detail;
    public ErrorModelErrors errors;

    public static class ErrorModelData {
        public String key;
        public String type;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ErrorModelErrors{
        public ArrayList<ErrorModelDetail> editEntity;
        public ArrayList<ErrorModelDetail> name;
        public ArrayList<ErrorModelDetail> newEntity;
    }

    @Data
    public static class ErrorModelDetail{
        public String errorCode;
        public String params;
    }
}
