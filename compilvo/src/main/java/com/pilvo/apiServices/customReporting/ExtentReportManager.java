package com.pilvo.apiServices.customReporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.pilvo.apiServices.globalVariable.Constant;

public class ExtentReportManager {
    private static ExtentReports extent = null;

    public ExtentReportManager() {
    }

    public synchronized static ExtentReports getInstance() {
        if (extent == null)
            createInstance();
        return extent;
    }

    public synchronized static ExtentReports createInstance() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(Constant.OUTPUT_FOLDER_REPORT + Constant.FILE_NAME_REPORT);
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName(" API Automation Reports ");
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setEncoding("utf-8");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        return extent;
    }

    public static void addExecutionDetails_ExtentReport() {
        extent.flush();
    }
}
