package by.iba.gomel.menu;

import java.nio.charset.Charset;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.iba.gomel.Constants;
import by.iba.gomel.dom.PersonsDOMBuilder;
import by.iba.gomel.interfaces.IXMLParserBuilder;
import by.iba.gomel.sax.PersonsSAXBuilder;

/**
 * This class contains methods to working with menu.
 */
public class Menu {

    private static final Logger LOGGER        = LoggerFactory.getLogger(Menu.class);
    private final Scanner       in            = new Scanner(System.in,
            Charset.defaultCharset().name());
    private IXMLParserBuilder   builder;
    private String              pathToXMLFile = Constants.PATH_TO_XML_FILE;

    /**
     *
     * @param pathToXMLFile
     *            set new xml file.
     */
    public void setPathToXMLFile(final String pathToXMLFile) {
        this.pathToXMLFile = pathToXMLFile;
    }

    /**
     * This method shows main menu on the console for choosing parser.
     */
    public void showMenuForChoosingParser() {
        int choice;
        Menu.LOGGER.info(Constants.ITEMS_MENU_PARSER);
        choice = Integer.parseInt(this.in.nextLine());
        switch (choice) {
            case Constants.SAX_PARSER_CHOICE:
                this.builder = new PersonsSAXBuilder();
                this.showMenuForWorkingWithXMLFile();
                break;
            case Constants.DOM_PARSER_CHOICE:
                this.builder = new PersonsDOMBuilder(this.pathToXMLFile, this.in);
                this.showMenuForWorkingWithXMLFile();
                break;
            case Constants.MENU_PARSER_EXIT_CHOICE:
                this.in.close();
                return;
            default:
                break;
        }
    }

    /**
     * This method shows menu on the console for working with xml file..
     */
    private void showMenuForWorkingWithXMLFile() {
        int choice;
        while (true) {
            Menu.LOGGER.info(Constants.ITEMS_MENU);
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
        }
    }

}
