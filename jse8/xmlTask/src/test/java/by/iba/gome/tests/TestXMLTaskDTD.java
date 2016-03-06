package by.iba.gome.tests;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import by.iba.gomel.Constants;
import by.iba.gomel.RunnerXMLTask;
import by.iba.gomel.dom.PersonsDOMBuilder;
import by.iba.gomel.menu.Menu;
import by.iba.gomel.person.Person;

public class TestXMLTaskDTD {

    public static final String PATH_TO_TEST_XML_FILE = "src/test/resources/testPhoneBook.xml";
    @Rule
    public final SystemOutRule RULE                  = new SystemOutRule().enableLog();

    @Test
    public void includeAnotherNotValidFile() {
        final ByteArrayInputStream in = new ByteArrayInputStream(
                "2\n6\nsrc/test/resources/anotherNotValidTestPhoneBook.xml\n1\n7".getBytes());
        System.setIn(in);
        final Menu testMenu = new Menu();
        testMenu.showMenuForChoosingParser();
        final String expectedException = "org.xml.sax.SAXParseException";
        Assert.assertTrue(
                "Mistake is in class PersonsDOMBuilder method includeAnotherFile. Wrong validation",
                this.RULE.getLog().contains(expectedException));
    }

    @Test
    public void includeAnotherValidFile() {
        final ByteArrayInputStream in = new ByteArrayInputStream(
                "2\n6\nsrc/test/resources/anotherTestPhoneBook.xml\n1\n7".getBytes());
        System.setIn(in);
        final Menu testMenu = new Menu();
        testMenu.showMenuForChoosingParser();
        final boolean[] expectedResults = {true, true};
        final boolean[] actualResults = {this.RULE.getLog().contains("Lotish Dmitry"),
                this.RULE.getLog().contains("Klimenkov Andrei")};
        Assert.assertArrayEquals("Mistake is in class PersonsDOMBuilder method connectAnotherFile",
                expectedResults, actualResults);
    }

    @Test
    public void testAddRecordConsole() {
        final ByteArrayInputStream in = new ByteArrayInputStream(
                "2\n2\np3\nSidorov\nGomel\n80255432123\n1\n4\np3\n1\n7".getBytes());
        System.setIn(in);
        final Menu testMenu = new Menu();
        testMenu.setPathToXMLFile(TestXMLTaskDTD.PATH_TO_TEST_XML_FILE);
        testMenu.showMenuForChoosingParser();
        final boolean[] expectedResults = {true, true, true};
        final boolean[] actualResults = {
                this.RULE.getLog().contains("Kolotsey Vladislav Vladzimirovich"),
                this.RULE.getLog().contains("Melnikov Konstantin"),
                this.RULE.getLog().contains("Sidorov")};
        Assert.assertArrayEquals("Mistake is in class PersonsDOMBuilder method addRecord",
                expectedResults, actualResults);
    }

    @Test
    public void testAddRecordObject() {
        final ByteArrayInputStream in = new ByteArrayInputStream("p3".getBytes());
        System.setIn(in);
        final Scanner testIn = new Scanner(System.in);
        final PersonsDOMBuilder builder = new PersonsDOMBuilder(
                TestXMLTaskDTD.PATH_TO_TEST_XML_FILE, Constants.PATH_TO_DTD_FILE, testIn);
        final Person testPerson = new Person();
        testPerson.setId("p3");
        testPerson.setFullName("Sidorov");
        testPerson.setAddress("Gomel");
        testPerson.setTelephone("80255432123");
        builder.addRecord(testPerson);
        builder.showAllRecords();
        final boolean[] expectedResults = {true, true, true};
        final boolean[] actualResults = {
                this.RULE.getLog().contains("Kolotsey Vladislav Vladzimirovich"),
                this.RULE.getLog().contains("Melnikov Konstantin"),
                this.RULE.getLog().contains("Sidorov")};
        Assert.assertArrayEquals("Mistake is in class PersonsDOMBuilder method addRecord(Person)",
                expectedResults, actualResults);
        testIn.close();
    }

    @Test
    public void testDeleteRecord() {
        final ByteArrayInputStream in = new ByteArrayInputStream(
                "2\n2\np3\nSidorov\nGomel\n80255432123\n4\np3\n1\n1\n7".getBytes());
        System.setIn(in);
        final Menu testMenu = new Menu();
        testMenu.setPathToXMLFile(TestXMLTaskDTD.PATH_TO_TEST_XML_FILE);
        testMenu.showMenuForChoosingParser();
        final boolean[] expectedResults = {true, true, false};
        final boolean[] actualResults = {
                this.RULE.getLog().contains("Kolotsey Vladislav Vladzimirovich"),
                this.RULE.getLog().contains("Melnikov Konstantin"),
                this.RULE.getLog().contains("Sidorov")};
        Assert.assertArrayEquals("Mistake is in class PersonsDOMBuilder method deleteRecord()",
                expectedResults, actualResults);
    }

    @Test
    public void testEditRecord() {
        final ByteArrayInputStream in = new ByteArrayInputStream(
                "2\n2\np3\nSidorov\nGomel\n80255432123\n3\np3\nBelov\nMoskow\n21312321321\n1\n4\np3\n1\n7"
                        .getBytes());
        System.setIn(in);
        final Menu testMenu = new Menu();
        testMenu.setPathToXMLFile(TestXMLTaskDTD.PATH_TO_TEST_XML_FILE);
        testMenu.showMenuForChoosingParser();
        final boolean[] expectedResults = {true, true, true};
        final boolean[] actualResults = {
                this.RULE.getLog().contains("Kolotsey Vladislav Vladzimirovich"),
                this.RULE.getLog().contains("Melnikov Konstantin"),
                this.RULE.getLog().contains("Belov")};
        Assert.assertArrayEquals("Mistake is in class PersonsDOMBuilder method editRecord()",
                expectedResults, actualResults);
    }

    @Test
    public void testMain() {
        final ByteArrayInputStream in = new ByteArrayInputStream("2\n7".getBytes());
        System.setIn(in);
        RunnerXMLTask.main(new String[] {});
        Assert.assertEquals("Mistakes are in class Menu, method showMenu",
                "Choose desired XML parser:\n1) SAX parser\n2) DOM parser\n3) Exit\nPlease, enter number: Menu:\n1) Show all records\n2) Add record\n3) Edit record\n4) Delete record\n5) Search record\n6) Connect another xml file\n7) Exit\nPlease, enter number: ",
                this.RULE.getLog());
    }

    @Test
    public void testSearchRecord() {
        final ByteArrayInputStream in = new ByteArrayInputStream(
                "2\n2\np3\nSidorov\nGomel\n80255432123\n5\nSidorov\n4\np3\n1\n7".getBytes());
        System.setIn(in);
        final Menu testMenu = new Menu();
        testMenu.setPathToXMLFile(TestXMLTaskDTD.PATH_TO_TEST_XML_FILE);
        testMenu.showMenuForChoosingParser();
        final boolean[] expectedResults = {false, false, true};
        final boolean[] actualResults = {
                this.RULE.getLog().contains("Kolotsey Vladislav Vladzimirovich"),
                this.RULE.getLog().contains("Melnikov Konstantin"),
                this.RULE.getLog().contains("Sidorov")};
        Assert.assertArrayEquals("Mistake is in class PersonsDOMBuilder method searchRecord()",
                expectedResults, actualResults);
    }

    @Test
    public void testShowAllRecords() {
        final ByteArrayInputStream in = new ByteArrayInputStream("2\n1\n7".getBytes());
        System.setIn(in);
        final Menu testMenu = new Menu();
        testMenu.setPathToXMLFile(TestXMLTaskDTD.PATH_TO_TEST_XML_FILE);
        testMenu.showMenuForChoosingParser();
        final boolean[] expectedResults = {true, true};
        final boolean[] actualResults = {
                this.RULE.getLog().contains("Kolotsey Vladislav Vladzimirovich"),
                this.RULE.getLog().contains("Melnikov Konstantin")};
        Assert.assertArrayEquals("Mistake is in class PersonsDOMBuilder method showAllRecords",
                expectedResults, actualResults);
    }

}
