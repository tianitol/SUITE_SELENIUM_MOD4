package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReporteManager {
    private static ExtentReports extent;
    private static ExtentTest test;

    public static void iniciarReporte() {
        ExtentSparkReporter spark = new ExtentSparkReporter("target/ReporteRegistro.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    public static ExtentTest crearTest(String nombreTest) {
        test = extent.createTest(nombreTest);
        return test;
    }

    public static void finalizarReporte() {
        extent.flush();
    }

    public static ExtentTest getTest() {
        return test;
    }
}

