package com.curr.crypto.api;

import com.curr.crypto.model.data.Feature;
import com.curr.crypto.model.data.Project;
import com.curr.crypto.model.data.TestData;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface RezApi {

    @RequestLine("POST /projects")
    @Headers("Content-Type: application/json")
    Object addProject(Project project);

    @RequestLine("POST /projects/{projectName}/features")
    @Headers("Content-Type: application/json")
    Object addFeature(@Param("projectName") String projectName, Feature feature);

    @RequestLine("POST /projects/{projectName}/features/{featureName}")
    @Headers("Content-Type: application/json")
    TestData addTestResult(@Param("projectName") String projectName, @Param("featureName") String featureName, TestData testData);
}
