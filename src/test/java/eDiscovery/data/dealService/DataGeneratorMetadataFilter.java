package eDiscovery.data.dealService;

import eDiscovery.apiMethods.deal.ApiMethodsMetadataFilter;
import eDiscovery.helpers.MetadataFilterAttributeValues;
import eDiscovery.helpers.enums.DataSearchType;
import eDiscovery.models.deal.metadataFilter.AddMetadataFilterRequestBody;
import eDiscovery.models.deal.metadataFilter.CommonMetadataFilterResponseBody;

import java.util.Collections;

import static eDiscovery.data.DataGeneratorCommon.getRandomName;

public class DataGeneratorMetadataFilter {

    public static AddMetadataFilterRequestBody getBasicMetadataFilterBody(){
        return AddMetadataFilterRequestBody.builder()
                .name(String.format(getRandomName()))
                .dataSearchType(DataSearchType.Document.name())
                .filterAttributeValues(Collections.singletonList(
                        MetadataFilterAttributeValues.getAttributesForFileSize(10_000, 50_000)
                ))
                .build();
    }

    public static CommonMetadataFilterResponseBody createBasicMetadataFilter(){
        return ApiMethodsMetadataFilter.addMetadataFilter(getBasicMetadataFilterBody()).as(CommonMetadataFilterResponseBody.class);
    }

}
