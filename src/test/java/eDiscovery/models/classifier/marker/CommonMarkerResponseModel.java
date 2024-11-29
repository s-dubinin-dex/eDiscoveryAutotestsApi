package eDiscovery.models.classifier.marker;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommonMarkerResponseModel {
    public String id;
    public String name;
    public String description;
    public String isActive;
    public String level;
    public String color;
}
