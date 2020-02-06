package com.pilvo.apiServices.customListner;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.pilvo.apiServices.customReporting.ExtentReportManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.util.HashMap;

public class CustomListner implements ITestListener {

    ExtentTest extentTest;

    public synchronized void onTestStart(ITestResult result) {
        String methodName = getMethodNamewithParams(result);
        String className = result.getMethod().getRealClass().getSimpleName();
        extentTest = ExtentReportManager.getInstance().createTest(methodName, result.getMethod().getDescription());


    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        System.out.println(result.getName() + " = [Pass]\n");
        Reporter.log(result.getName() + " = [Pass]<br>");
        String className = result.getMethod().getRealClass().getSimpleName();
        extentTest.assignCategory(className);
        extentTest.createNode(MarkupHelper.createLabel("Test passed", ExtentColor.GREEN).getMarkup());
        ExtentReportManager.getInstance().flush();
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        System.out.println(result.getName() + " = [Fail]\n");
        Reporter.log(result.getName() + " = [Fail]<br>");
        String className = result.getMethod().getRealClass().getSimpleName();
        extentTest.assignCategory(className);
        extentTest.createNode(MarkupHelper.createLabel("Test Failed", ExtentColor.RED).getMarkup())
                .fail(result.getThrowable());
        ExtentReportManager.getInstance().flush();
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {

    }

    @Override
    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public synchronized void onStart(ITestContext context) {

    }

    @Override
    public synchronized void onFinish(ITestContext context) {

    }


    public String getMethodNamewithParams(ITestResult result) {
        String methodName = result.getName();
        String nextLineCharacter = "<br>";
        if (result.getParameters().length > 0 && result.getParameters()[0] instanceof HashMap) {
            methodName = methodName + nextLineCharacter + result.getParameters()[0].toString();
        }
        System.out.println("Method Nname :" + methodName);

        return methodName;
    }
}
