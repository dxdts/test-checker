package com.curr.crypto.listener;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestRunner;
import org.testng.internal.IResultListener;

import com.curr.crypto.client.RezClient;
import com.curr.crypto.model.data.Feature;
import com.curr.crypto.model.data.Project;
import com.curr.crypto.model.data.TestData;

import io.qameta.allure.Epic;

public class RezListener implements IResultListener {

    private RezClient rezClient;
    private TestData testData;
    private Project project;
    private Feature feature;

    @Override
    public void onStart(ITestContext testContext) {
        rezClient = new RezClient();
        this.project = new Project(getEpicValue(getAnnotatedMethod(testContext, Epic.class)));
        this.feature = new Feature(getFeatureValue(getAnnotatedMethod(testContext, io.qameta.allure.Feature.class)));
    }

    private Method getAnnotatedMethod(final ITestContext testContext, final Class<? extends Annotation> annotation) {
        final var class1 = ((TestRunner) testContext)
                .getTest().getClasses().stream().findAny().orElseThrow().getSupportClass();
        return Arrays.stream(class1.getMethods())
                .filter(p -> p.isAnnotationPresent(annotation)).findAny()
                .orElseThrow(() -> new AssertionError("no required annotation found"));
    }

    @Override
    public void onFinish(ITestContext testContext) {
        rezClient.addProject(this.project);
        rezClient.addFeature(this.project.getName(), this.feature);
        rezClient.addTestData(project.getName(), feature.getName(), testData);
    }

    @Override
    public void onTestSuccess(ITestResult testResult) {
        testData = createTestData(testResult);
    }

    @Override
    public void onTestFailure(ITestResult testResult) {
        testData = createTestData(testResult);
    }

    private TestData createTestData(ITestResult testResult) {
        final var testData = new TestData();
        testData.setTestName(testResult.getName());
        final long startedAt = testResult.getStartMillis();
        final long finishedAt = testResult.getEndMillis();
        final long duration = finishedAt - startedAt;
        testData.setDuration(duration);
        testData.setPassed(testResult.isSuccess());
        return testData;
    }

    private String getEpicValue(final Method method) {
        return method.getAnnotation(Epic.class).value();
    }

    private String getFeatureValue(final Method method) {
        return method.getAnnotation(io.qameta.allure.Feature.class).value();
    }
}
