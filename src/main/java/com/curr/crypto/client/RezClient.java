package com.curr.crypto.client;

import com.curr.crypto.api.RezApi;
import com.curr.crypto.model.data.Feature;
import com.curr.crypto.model.data.Project;
import com.curr.crypto.model.data.TestData;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

public class RezClient {
    private static final String URL = "http://localhost:8080";
    private final RezApi rezApi;

    public RezClient() {
        this(URL);
    }

    public RezClient(String baseUrl) {
        this.rezApi = Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .target(RezApi.class, baseUrl);
    }

    public Object addProject(Project project) {
        return rezApi.addProject(project);
    }

    public Object addFeature(String projectName, Feature feature) {
        return rezApi.addFeature(projectName, feature);
    }

    public TestData addTestData(String projectName, String featureName, TestData testData) {
        return rezApi.addTestResult(projectName, featureName, testData);
    }
}
