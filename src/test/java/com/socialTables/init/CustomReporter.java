package com.socialTables.init;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;

import com.beust.testng.TestNG;

public class CustomReporter implements IReporter
{   
    File fd = new File("");

    String date;
    DateFormat df = new SimpleDateFormat("dd-MM-yyyy_hh-mm-ss");
    String folderNameWithTimeStamp = df.format(new Date());
    String currentDir = System.getProperty("user.dir") + "//Reports//";
    String finalPath = currentDir + folderNameWithTimeStamp;

    @SuppressWarnings("deprecation")
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) 
    {
        TestNG.getDefault().setOutputDirectory(finalPath);
        TestNG.getDefault().setXmlSuites(xmlSuites);
    }
}