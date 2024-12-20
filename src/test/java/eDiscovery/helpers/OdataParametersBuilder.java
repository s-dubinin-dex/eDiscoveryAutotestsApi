package eDiscovery.helpers;

import java.util.HashMap;
import java.util.Map;

public class OdataParametersBuilder {

    private final Map <String, String> parameters = new HashMap<>();

    private OdataParametersBuilder(){}

    public static OdataParametersBuilder builder(){
        return new OdataParametersBuilder();
    }

    public OdataParametersBuilder withCount(boolean value){
        parameters.put("$count", String.valueOf(value));
        return this;
    }

    public OdataParametersBuilder withExpand(String value){
        parameters.put("$expand", value);
        return this;
    }

    public OdataParametersBuilder withFilter (String value){
        parameters.put("$filter", value);
        return this;
    }

    public OdataParametersBuilder withOrderBy(String value){
        parameters.put("$orderby", value);
        return this;
    }

    public OdataParametersBuilder withSkip(int value){
        parameters.put("$skip", String.valueOf(value));
        return this;
    }

    public OdataParametersBuilder withTop(int value){
        parameters.put("$top", String.valueOf(value));
        return this;
    }

    public OdataParametersBuilder withIncludeDeleted(boolean value){
        parameters.put("includeDeleted", String.valueOf(value));
        return this;
    }

    public Map<String, String> build(){
        return new HashMap<>(parameters);
    }
}
