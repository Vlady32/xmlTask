package by.iba.gomel;

/**
 * This class contains all constants in this application.
 */
public class Constants {

    public static final String ITEMS_MENU_PARSER               = "Choose desired XML parser:\n1) SAX parser\n2) DOM parser"
            + "\n3) Exit\nPlease, enter number: ";
    public static final int    SAX_PARSER_CHOICE               = 1;
    public static final int    DOM_PARSER_CHOICE               = 2;
    public static final int    MENU_PARSER_EXIT_CHOICE         = 3;
    public static final int    SHOW_ALL_RECORDS_CHOICE         = 1;
    public static final int    ADD_RECORD_CHOICE               = 2;
    public static final int    EDIT_RECORD_CHOICE              = 3;
    public static final int    DELETE_RECORD_CHOICE            = 4;
    public static final int    SEARCH_RECORD_CHOICE            = 5;
    public static final int    CONNECT_ANOTHER_XML_FILE_CHOICE = 6;
    public static final int    MENU_EXIT_CHOICE                = 7;
    public static final String ITEMS_MENU                      = "Menu:\n1) Show all records\n2) Add record"
            + "\n3) Edit record\n4) Delete record\n5) Search record\n6) Connect another xml file\n7) Exit\nPlease, enter number: ";
    public static final String PATH_TO_XML_FILE                = "src/main/resources/PhoneBook.xml";
    public static final int    ID_POSITION                     = 0;
    public static final int    FULL_NAME_POSITION              = 1;
    public static final int    ADDRESS_POSITION                = 2;
    public static final int    TELEPHONE_POSITION              = 3;
    public static final int    QUANTITY_PERSON_FIELDS          = 4;
    public static final String PHRASE_ID                       = "ID: ";
    public static final String PHRASE_FULL_NAME                = "Full Name: ";
    public static final String PHRASE_ADDRESS                  = "Address: ";
    public static final String PHRASE_TELEPHONE                = "Telephone: ";
    public static final String HEADER_ID                       = "ID";
    public static final String HEADER_FULL_NAME                = "Full Name";
    public static final String HEADER_ADDRESS                  = "Address";
    public static final String HEADER_TELEPHONE                = "Telephone";
    public static final int    ZERO                            = 0;
    public static final int    ONE                             = 1;
    public static final int    TWO                             = 2;
    public static final int    THREE                           = 3;
    public static final int    FOUR                            = 4;
    public static final int    FIVE                            = 5;
    public static final int    SIX                             = 6;
    public static final int    SEVEN                           = 7;
    public static final String FORMAT_ID                       = "%5s";
    public static final String FORMAT_FULL_NAME                = "%35s";
    public static final String FORMAT_ADDRESS                  = "%50s";
    public static final String FORMAT_TELEPHONE                = "%15s%n";
    public static final String FORMAT_HEADER                   = "%5s%35s%50s%15s%n";
    public static final String SPACE                           = " ";
    public static final String PLUS                            = "+";
    public static final String LINE_BREAK                      = "\n";
    public static final String ELEMENT_PERSONS                 = "persons";
    public static final String ELEMENT_PERSON                  = "person";
    public static final String ELEMENT_FULL_NAME               = "fullName";
    public static final String ELEMENT_ADDRESS                 = "address";
    public static final String ELEMENT_TELEPHONE               = "telephone";
    public static final String ATTRIBUTE_ID                    = "id";
    public static final String PARSER_CONFIG_EXCEPTION         = "ParserConfigurationException";
    public static final String TRANSFORMER_CONFIG_EXCEPTION    = "TransformerConfigurationException";
    public static final String TRANSFORMER_EXCEPTION           = "TransformerException";
    public static final String SAX_EXCEPTION                   = "SAXException";
    public static final String SAX_PARSE_EXCEPTION             = "SAXParseException";
    public static final String IO_EXCEPTION                    = "IOException";
    public static final String ENTER_NUMBER                    = "Please, enter number: ";
    public static final String PATH_TO_DTD                     = "DTDPhoneBook.dtd";
    public static final String PHRASE_YES                      = "yes";
    public static final String PHRASE_NOT_FOUND_RECORD         = "Record wasn't found\n";
    public static final String PHRASE_RECORD_ADDED             = "Record was added!\n";
    public static final String PHRASE_RECORD_CHANGED           = "Record was successfully changed!\n";
    public static final String PHRASE_RECORD_DELETED           = "Record was successfully deleted!\n";
    public static final String PHRASE_SEARCH_INFO              = "Enter name or address or telephone: ";
    public static final String PHRASE_ENTER_PATH               = "Enter path to xml file: ";
    public static final String PHRASE_CONFIRMATION             = "Are you sure? (1 - yes, 2-no): ";
    public static final String PATH_TO_RESOURCES               = "src/main/resources/";
    public static final String PATH_TO_DTD_FILE                = "src/main/resources/DTDPhoneBook.dtd";
    public static final String PHRASE_NOT_INCLUDE              = "Included file contains errors, please, include another file\n";
    public static final String TYPE_ID                         = "ID";
    public static final String TYPE_PATH                       = "PATH";
    public static final String TYPE_ALL                        = "ALL";
    public static final String TYPE_SEARCH_INFO                = "SEARCH";
    public static final int    ID_POSITION_CONSOLE             = 0;
    public static final int    PATH_POSITION                   = 0;
    public static final int    ALL_POSITION                    = 0;
    public static final int    SEARCH_INFO_POSITION            = 0;
    public static final String PHRASE_ERROR_DTD                = "Operation with record was not implemented.\n";

    /**
     * Default private constructor.
     */
    private Constants() {

    }
}
