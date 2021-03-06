package by.iba.gomel;

/**
 * This class contains all constants in this application.
 */
public class Constants {

    public static final String ITEMS_MENU_PARSER                 = "Choose desired XML parser:\n1) SAX parser\n2) DOM parser"
            + "\n3) Exit\nPlease, enter number: ";
    public static final int    SAX_PARSER_CHOICE                 = 1;
    public static final int    DOM_PARSER_CHOICE                 = 2;
    public static final int    MENU_PARSER_EXIT_CHOICE           = 3;
    public static final int    SHOW_ALL_RECORDS_CHOICE           = 1;
    public static final int    ADD_RECORD_CHOICE                 = 2;
    public static final int    EDIT_RECORD_CHOICE                = 3;
    public static final int    DELETE_RECORD_CHOICE              = 4;
    public static final int    SEARCH_RECORD_CHOICE              = 5;
    public static final int    CONNECT_ANOTHER_XML_FILE_CHOICE   = 6;
    public static final int    MENU_EXIT_CHOICE                  = 7;
    public static final String ITEMS_MENU                        = "Menu:\n1) Show all records\n2) Add record"
            + "\n3) Edit record\n4) Delete record\n5) Search record\n6) Connect another xml file\n7) Exit\nPlease, enter number: ";
    public static final String PATH_TO_XML_FILE_DOM              = "src/main/resources/PhoneBook.xml";
    public static final String PATH_TO_XML_FILE_SAX              = "src/main/resources/Spares.xml";
    public static final int    ID_POSITION                       = 0;
    public static final int    KEY_POSITION                      = 0;
    public static final int    FULL_NAME_POSITION                = 1;
    public static final int    MARK_AUTO_POSITION                = 1;
    public static final int    ADDRESS_POSITION                  = 2;
    public static final int    MODEL_AUTO_POSITION               = 2;
    public static final int    TELEPHONE_POSITION                = 3;
    public static final int    COST_POSITION                     = 3;
    public static final int    QUANTITY_PERSON_FIELDS            = 4;
    public static final int    QUANTITY_SPARE_FIELDS             = 4;
    public static final int    QUANTITY_SPARE_FIELDS_WITHOUT_KEY = 3;
    public static final String PHRASE_ID                         = "ID: ";
    public static final String PHRASE_FULL_NAME                  = "Full Name: ";
    public static final String PHRASE_ADDRESS                    = "Address: ";
    public static final String PHRASE_TELEPHONE                  = "Telephone: ";
    public static final String HEADER_ID                         = "ID";
    public static final String HEADER_FULL_NAME                  = "Full Name";
    public static final String HEADER_ADDRESS                    = "Address";
    public static final String HEADER_TELEPHONE                  = "Telephone";
    public static final int    ZERO                              = 0;
    public static final int    ONE                               = 1;
    public static final int    TWO                               = 2;
    public static final int    THREE                             = 3;
    public static final int    FOUR                              = 4;
    public static final int    FIVE                              = 5;
    public static final int    SIX                               = 6;
    public static final int    SEVEN                             = 7;
    public static final String FORMAT_ID                         = "%5s";
    public static final String FORMAT_FULL_NAME                  = "%35s";
    public static final String FORMAT_ADDRESS                    = "%50s";
    public static final String FORMAT_TELEPHONE                  = "%15s%n";
    public static final String FORMAT_HEADER_DOM                 = "%5s%35s%50s%15s%n";
    public static final String FORMAT_SPARE                      = "%4d%10s%25s%25s%20d%n";
    public static final String FORMAT_HEADER_SAX                 = "%2s%10s%25s%25s%20s%n";
    public static final String SPACE                             = " ";
    public static final String PLUS                              = "+";
    public static final String LINE_BREAK                        = "\n";
    public static final String ELEMENT_PERSONS                   = "persons";
    public static final String ELEMENT_PERSON                    = "person";
    public static final String ELEMENT_FULL_NAME                 = "fullName";
    public static final String ELEMENT_ADDRESS                   = "address";
    public static final String ELEMENT_TELEPHONE                 = "telephone";
    public static final String ATTRIBUTE_ID                      = "id";
    public static final String ELEMENT_SPARES                    = "spares";
    public static final String ELEMENT_SPARE                     = "spare";
    public static final String ELEMENT_MARK_AUTO                 = "markAuto";
    public static final String ELEMENT_MODEL_AUTO                = "modelAuto";
    public static final String ELEMENT_COST                      = "cost";
    public static final String ATTRIBUTE_KEY                     = "key";
    public static final String PARSER_CONFIG_EXCEPTION           = "ParserConfigurationException";
    public static final String TRANSFORMER_CONFIG_EXCEPTION      = "TransformerConfigurationException";
    public static final String TRANSFORMER_EXCEPTION             = "TransformerException";
    public static final String SAX_EXCEPTION                     = "SAXException";
    public static final String SAX_PARSE_EXCEPTION               = "SAXParseException";
    public static final String NUMBER_FORMAT_EXCEPTION           = "NumberFormatException";
    public static final String FILE_NOT_FOUND_EXCEPTION          = "FileNotFoundException";
    public static final String PHRASE_WRONG_COST                 = "Wrong cost\n";
    public static final String IO_EXCEPTION                      = "IOException";
    public static final String ENTER_NUMBER                      = "Please, enter number: ";
    public static final String PATH_TO_DTD                       = "DTDPhoneBook.dtd";
    public static final String PHRASE_YES                        = "yes";
    public static final String PHRASE_NOT_FOUND_FILE             = "File wasn't found\n";
    public static final String PHRASE_NOT_FOUND_RECORD           = "Record wasn't found\n";
    public static final String PHRASE_NOT_ADDED_RECORD           = "Record wasn't added\n";
    public static final String PHRASE_RECORD_ADDED               = "Record was added!\n";
    public static final String PHRASE_FILE_CONNECTED             = "File was connected!\n";
    public static final String PHRASE_RECORD_CHANGED             = "Record was successfully changed!\n";
    public static final String PHRASE_RECORD_DELETED             = "Record was successfully deleted!\n";
    public static final String PHRASE_RECORD_NOT_DELETED         = "Record wasn't deleted!\n";
    public static final String PHRASE_SEARCH_INFO                = "Enter name or address or telephone: ";
    public static final String PHRASE_SEARCH_INFO_SPARE          = "Enter key or mark auto or model auto: ";
    public static final String PHRASE_ENTER_PATH                 = "Enter path to xml file: ";
    public static final String PHRASE_CONFIRMATION               = "Are you sure? (1 - yes, 2-no): ";
    public static final String PATH_TO_RESOURCES                 = "src/main/resources/";
    public static final String PATH_TO_DTD_FILE                  = "src/main/resources/DTDPhoneBook.dtd";
    public static final String PATH_TO_XSD_FILE                  = "src/main/resources/SparesXSD.xsd";
    public static final String PHRASE_NOT_INCLUDE                = "Included file contains errors, please, include another file\n";
    public static final String TYPE_ID                           = "ID";
    public static final String TYPE_KEY                          = "KEY";
    public static final String TYPE_PATH                         = "PATH";
    public static final String TYPE_ALL                          = "ALL";
    public static final String TYPE_SEARCH_INFO                  = "SEARCH";
    public static final String TYPE_ALL_WITHOUT_ID               = "ALL_WITHOUT_ID";
    public static final int    ID_POSITION_CONSOLE               = 0;
    public static final int    PATH_POSITION                     = 0;
    public static final int    ALL_POSITION                      = 0;
    public static final int    SEARCH_INFO_POSITION              = 0;
    public static final String PHRASE_ERROR_DTD                  = "Operation with record was not done.\n";
    public static final String PHRASE_INCORRECT_DATA             = "Incorrect data\n";
    public static final String EMPTY_LINE                        = "";
    public static final String ERROR_XSD                         = "XML file contains errors according to xsd schema.Please, include another file or fix errors in xml file.\n";
    public static final String PHRASE_ITEM                       = "Item: ";
    public static final String PHRASE_KEY                        = "Key: ";
    public static final String PHRASE_MARK_AUTO                  = "Mark auto: ";
    public static final String PHRASE_MODEL_AUTO                 = "Model auto: ";
    public static final String PHRASE_COST                       = "Cost: ";
    public static final String HEADER_ITEM                       = "Item";
    public static final String HEADER_KEY                        = "Key";
    public static final String HEADER_MARK_AUTO                  = "Mark auto";
    public static final String HEADER_MODEL_AUTO                 = "Model auto";
    public static final String HEADER_COST                       = "Cost";
    public static final String DIVIDING_LINE                     = "------------------------------------------------------------------------------------\n";
    public static final String ERROR_ADD_EDIT_RECORD             = "Added record contains errors according to xsd schema.\n";
    public static final int    LAST_NUMBER_ELEMENT_SPARES        = 2;
    public static final String ERROR_NUMBER_FORMAT_MENU          = "Please, write the number in console\n";
    public static final String PHRASE_FILE_NOT_FOUND             = "File was not found. Please, enter correct path\n";
    public static final String TELEPHONE_REGEX                   = "(\\d|\\*)+";
    public static final String ERROR_TELEPHONE                   = "Telephone contains error. Valid characters: numbers or *\n";

    /**
     * Default private constructor.
     */
    private Constants() {

    }
}
