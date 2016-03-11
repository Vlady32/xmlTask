package by.iba.gomel.menu;

import java.nio.charset.Charset;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.iba.gomel.Constants;
import by.iba.gomel.dom.PersonsDOMBuilder;
import by.iba.gomel.interfaces.IXMLParser;
import by.iba.gomel.sax.SparesSAXBuilder;

/**
 * This class contains methods to working with menu.
 */
public class Menu {

    private static final Logger LOGGER        = LoggerFactory.getLogger(Menu.class);
    private final Scanner       in            = new Scanner(System.in,
            Charset.defaultCharset().name());
    private IXMLParser          builder;
    private String              pathToXMLFile = null;
    private String              pathToDTDFile = Constants.PATH_TO_DTD_FILE;
    private String              pathToXSDFile = Constants.PATH_TO_XSD_FILE;

    /**
     *
     * @param pathToDTDFile
     *            set new dtd file.
     */
    public void setPathToDTDFile(final String pathToDTDFile) {
        this.pathToDTDFile = pathToDTDFile;
    }

    /**
     *
     * @param pathToXMLFile
     *            set new xml file.
     */
    public void setPathToXMLFile(final String pathToXMLFile) {
        this.pathToXMLFile = pathToXMLFile;
    }

    /**
     *
     * @param pathToXSDFile
     *            set path to xsd file.
     */
    public void setPathToXSDFile(final String pathToXSDFile) {
        this.pathToXSDFile = pathToXSDFile;
    }

    /**
     * This method shows main menu on the console for choosing parser.
     */
    public void showMenuForChoosingParser() {
        int choice;
        Menu.LOGGER.info(Constants.ITEMS_MENU_PARSER);
        try {
            choice = Integer.parseInt(this.in.nextLine());
            switch (choice) {
                case Constants.SAX_PARSER_CHOICE: // NOSONAR
                    if (this.pathToXMLFile == null) {
                        this.setPathToXMLFile(Constants.PATH_TO_XML_FILE_SAX);
                    }
                    this.builder = new SparesSAXBuilder(this.pathToXMLFile, this.pathToXSDFile,
                            this.in);
                    this.showMenuForWorkingWithXMLFile();
                    break;
                case Constants.DOM_PARSER_CHOICE: // NOSONAR
                    if (this.pathToXMLFile == null) {
                        this.setPathToXMLFile(Constants.PATH_TO_XML_FILE_DOM);
                    }
                    this.builder = new PersonsDOMBuilder(this.pathToXMLFile, this.pathToDTDFile,
                            this.in);
                    this.showMenuForWorkingWithXMLFile();
                    break;
                case Constants.MENU_PARSER_EXIT_CHOICE:
                    this.in.close();
                    return;
                default:
                    break;
            }
        } catch (final NumberFormatException e) {
            Menu.LOGGER.info(Constants.ERROR_NUMBER_FORMAT_MENU);
            Menu.LOGGER.error(Constants.NUMBER_FORMAT_EXCEPTION, e);
        }
    }

    /**
     * This method shows menu on the console for working with xml file..
     */
    private void showMenuForWorkingWithXMLFile() {
        int choice;
        while (true) {
            Menu.LOGGER.info(Constants.ITEMS_MENU);
            try {
                choice = Integer.parseInt(this.in.nextLine());
                switch (choice) {
                    case Constants.SHOW_ALL_RECORDS_CHOICE:
                        this.builder.showAllRecords();
                        break;
                    case Constants.ADD_RECORD_CHOICE:
                        this.builder.addRecord();
                        break;
                    case Constants.EDIT_RECORD_CHOICE:
                        this.builder.editRecord();
                        break;
                    case Constants.DELETE_RECORD_CHOICE:
                        this.builder.deleteRecord();
                        break;
                    case Constants.SEARCH_RECORD_CHOICE:
                        this.builder.searchRecord();
                        break;
                    case Constants.CONNECT_ANOTHER_XML_FILE_CHOICE:
                        this.builder.connectAnotherXMLFile();
                        break;
                    case Constants.MENU_EXIT_CHOICE:
                        this.in.close();
                        return;
                    default:
                        break;
                }
            } catch (final NumberFormatException e) {
                Menu.LOGGER.info(Constants.ERROR_NUMBER_FORMAT_MENU);
                Menu.LOGGER.error(Constants.NUMBER_FORMAT_EXCEPTION, e);
            }
        }
    }

}
