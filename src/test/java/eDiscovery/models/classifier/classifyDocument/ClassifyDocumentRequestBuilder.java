package eDiscovery.models.classifier.classifyDocument;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ClassifyDocumentRequestBuilder {

    private final Map<String, Object> parameters = new HashMap<>();

    private ClassifyDocumentRequestBuilder(){};

    public static ClassifyDocumentRequestBuilder builder(){
        return new ClassifyDocumentRequestBuilder();
    }

    public ClassifyDocumentRequestBuilder withDealId(String id){
        parameters.put("DealId", id);
        return this;
    }

    public ClassifyDocumentRequestBuilder withTaskId(String id){
        parameters.put("TaskId", id);
        return this;
    }

    public ClassifyDocumentRequestBuilder withFileId(String id){
        parameters.put("FileId", id);
        return this;
    }

    public ClassifyDocumentRequestBuilder withProfileId(String id){
        parameters.put("ProfileId", id);
        return this;
    }

    public ClassifyDocumentRequestBuilder withFile(File file){
        parameters.put("File", file);
        return this;
    }

    public Map<String, Object> build(){
        return this.parameters;
    }

}
