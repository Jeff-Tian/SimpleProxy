import java.net.*;
import java.io.*;
import java.util.*;

public class Test {
    public static String getMessage(String expectedResult, String actualResult) {
        return "期待：" + "【" + expectedResult + "】，却得到了：【" + actualResult + "】";
    }

    private static String getMessage(boolean expectedResult, Boolean actualResult) {
        return "期待：" + "【" + expectedResult + "】，却得到了：【" + actualResult + "】";
    }

    private static void test(String expected, String actual) throws Exception {
        if (!expected.equals(actual)) {
            throw new Exception("\n\t" + expected + " != " + actual + "！" + getMessage(expected, actual));
        } else {
            System.out.println("\n\t" + expected + " == " + actual + "。正确！");
        }
    }

    private static void test(boolean expected, Boolean actual) throws Exception {
        if (expected != actual) {
            throw new Exception("\n\t" + expected + " != " + actual + "！" + getMessage(expected, actual));
        } else {
            System.out.println("\n\t" + expected + " == " + actual + "。正确！");
        }
    }

    private static void testCase(String name) {
        System.out.println(name);
    }

    private static void testUrlParser() throws Exception {
        String url = "http://someone.com/path/resource";
        Url parsed = Url.parse(url);

        testCase(url);
        test("http://", parsed.protocol);
        test(false, parsed.ssl);
        test("someone.com", parsed.host);
        test("/path/resource", parsed.pathname);

        String sslUrl = "https://someone.com/path/resource/";
        Url parsedSSL = Url.parse(sslUrl);

        testCase(sslUrl);
        test("https://", parsedSSL.protocol);
        test(true, parsedSSL.ssl);
        test("someone.com", parsedSSL.host);
        test("/path/resource/", parsedSSL.pathname);

    }

    private static void testFileNameExpert() throws Exception {
        String url = "https://github.com/syncthing/syncthing/releases/download/v1.3.4/syncthing-windows-amd64-v1.3.4.zip";

        testCase(url);

        String fileFullName = FileNameExpert.getFileFullNameFromUrl(url);

        String osName = System.getProperty("os.name").toLowerCase();
        System.out.println(osName);

        if (osName.startsWith("windows")) {
            test("C:\\mirror\\github.com\\syncthing\\syncthing\\releases\\download\\v1.3.4\\syncthing-windows-amd64-v1.3.4.zip",
                    fileFullName);
        } else {
            test("/mirror/github.com/syncthing/syncthing/releases/download/v1.3.4/syncthing-windows-amd64-v1.3.4.zip",
                    fileFullName);
        }
    }

    public static void main(String args[]) throws Exception {
        System.out.println("Testing...");

        Test.testUrlParser();
        Test.testFileNameExpert();

        System.out.println("Testing pass!");
    }
}