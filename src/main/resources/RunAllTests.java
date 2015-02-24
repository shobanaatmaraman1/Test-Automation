package main.resources;

/**
 * Created by xAD-inc on 2/18/2015.
 */


import org.testng.TestNG;
import java.io.FileWriter;
import java.util.Arrays;


public class RunAllTests {


    static TestNG testng;

    public static void main(String[] args) {

        try {
            testng = new TestNG();
            testng.setPreserveOrder(true);
            testng.setVerbose(0);
            testng.setTestSuites(Arrays.asList("src/config/testng.xml"));
            testng.run();
            String Passed =  testng.hasFailure() ? "Failed":"Passed";
            // Create file
            FileWriter fstream = new FileWriter("src/bin/exitstatus.txt");
            fstream.write(Passed);
            //Close the output stream
            fstream.close();

        } catch (Exception e) {
            //Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }
}
