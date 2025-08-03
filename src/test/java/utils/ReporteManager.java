package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.util.concurrent.ConcurrentHashMap;

public class ReporteManager {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();
    private static ConcurrentHashMap<String, ExtentTest> testMap = new ConcurrentHashMap<>();

    public static void iniciarReporte() {
        ExtentSparkReporter spark = new ExtentSparkReporter("target/ReporteCompleto.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    public static ExtentTest crearTest(String nombreTest) {
        ExtentTest test = extent.createTest(nombreTest);
        testThread.set(test);
        testMap.put(nombreTest, test);
        return test;
    }

    public static ExtentTest getTest() {
        return testThread.get();
    }

    public static void finalizarReporte() {
        extent.flush();
    }
}


