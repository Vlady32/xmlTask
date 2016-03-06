package by.iba.gomel.interfaces;

/**
 * This interface describes basic methods for working with xml.
 */
public interface IXMLParser {

    /**
     * Add record in xml file.
     */
    public void addRecord();

    /**
     * Connect another xml file for working with it.
     */
    public void connectAnotherXMLFile();

    /**
     * Delete record in xml file.
     */
    public void deleteRecord();

    /**
     * Edit record in xml file.
     */
    public void editRecord();

    /**
     * Search record in xml file.
     */
    public void searchRecord();

    /**
     * Show all records from xml file.
     */
    public void showAllRecords();
}
