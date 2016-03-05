package by.iba.gomel.dom;

import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumSet;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import by.iba.gomel.Constants;
import by.iba.gomel.enums.PersonsElements;
import by.iba.gomel.error.ErrorHandler;
import by.iba.gomel.interfaces.IXMLParserBuilder;
import by.iba.gomel.person.Person;

/**
 * This class includes methods to working with DOM parsing.
 */
public class PersonsDOMBuilder implements IXMLParserBuilder {

    private static final Logger  LOGGER = LoggerFactory.getLogger(PersonsDOMBuilder.class);
    private Scanner              in     = new Scanner(System.in, Charset.defaultCharset().name());
    private DocumentBuilder      docBuilder;
    final DocumentBuilderFactory docFactory;
    private String               pathToXMLFile;

    /**
     *
     * Constructor creates instance of DOMParser.
     *
     * @param pathToXMLFile
     *            path to xml file.
     */
    public PersonsDOMBuilder(final String pathToXMLFile, final Scanner in) {
        this.in = in;
        // Create DOM analyzer
        this.docFactory = DocumentBuilderFactory.newInstance();
        this.docFactory.setValidating(true);
        this.pathToXMLFile = pathToXMLFile;
        try {
            this.docBuilder = this.docFactory.newDocumentBuilder();
            this.docBuilder.setErrorHandler(new ErrorHandler());
        } catch (final ParserConfigurationException e) {
            PersonsDOMBuilder.LOGGER.error(Constants.PARSER_CONFIG_EXCEPTION, e);
        }
    }

    /**
     *
     * Add dtd file to file for validating.
     *
     * @param pathToAnotherXMLFile
     *            path to another xml file.
     */
    private static void addDTD(final String pathToAnotherXMLFile) {
        final Path pathToDTDFile = Paths.get(Constants.PATH_TO_DTD_FILE);
        final StringWriter xmlAsWriter = new StringWriter();
        FileWriter fw = null;
        try {
            final Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,
                    pathToDTDFile.toAbsolutePath().toString());
            transformer.setOutputProperty(OutputKeys.INDENT, Constants.PHRASE_YES);
            transformer.transform(new StreamSource(pathToAnotherXMLFile),
                    new StreamResult(xmlAsWriter));
            fw = new FileWriter(pathToAnotherXMLFile);
            fw.write(xmlAsWriter.toString());
            fw.close();
        } catch (final TransformerConfigurationException e) {
            PersonsDOMBuilder.LOGGER.error(Constants.TRANSFORMER_CONFIG_EXCEPTION, e);
        } catch (final TransformerException e) {
            PersonsDOMBuilder.LOGGER.error(Constants.TRANSFORMER_EXCEPTION, e);
        } catch (final IOException e) {
            PersonsDOMBuilder.LOGGER.error(Constants.IO_EXCEPTION, e);
        }
    }

    /**
     *
     * @param personEl
     *            to get values from Element.
     * @return all values.
     */
    private static String buildPerson(final Element personEl) {
        final String id = String.format(Constants.FORMAT_ID,
                personEl.getAttribute(PersonsElements.ID.getNameElement()));
        final String fullName = String.format(Constants.FORMAT_FULL_NAME,
                PersonsDOMBuilder
                        .getNodeElement(personEl, PersonsElements.FULL_NAME.getNameElement())
                        .getTextContent());
        final String address = String.format(Constants.FORMAT_ADDRESS,
                PersonsDOMBuilder.getNodeElement(personEl, PersonsElements.ADDRESS.getNameElement())
                        .getTextContent());
        final String tel = String.format(Constants.FORMAT_TELEPHONE,
                PersonsDOMBuilder
                        .getNodeElement(personEl, PersonsElements.TELEPHONE.getNameElement())
                        .getTextContent());
        return id + fullName + address + tel;
    }

    /**
     *
     * @param element
     *            to get value from this element.
     * @param elementName
     *            it's tag where to get text value.
     * @return text value.
     */
    private static Node getNodeElement(final Element element, final String elementName) {
        final NodeList nList = element.getElementsByTagName(elementName);
        return nList.item(0);
    }

    @Override
    public void addRecord() {
        final String[] values = this.getDataFromConsole(Constants.TYPE_ALL);
        try {
            final Document doc = this.docBuilder.parse(this.pathToXMLFile);
            final Element root = doc.getDocumentElement();
            final Element person = doc.createElement(PersonsElements.PERSON.getNameElement());
            person.setAttribute(PersonsElements.ID.getNameElement(), values[Constants.ID_POSITION]);
            final Element personFullName = doc
                    .createElement(PersonsElements.FULL_NAME.getNameElement());
            personFullName.setTextContent(values[Constants.FULL_NAME_POSITION]);
            person.appendChild(personFullName);
            final Element personAddress = doc
                    .createElement(PersonsElements.ADDRESS.getNameElement());
            personAddress.setTextContent(values[Constants.ADDRESS_POSITION]);
            person.appendChild(personAddress);
            final Element personTel = doc.createElement(PersonsElements.TELEPHONE.getNameElement());
            personTel.setTextContent(values[Constants.TELEPHONE_POSITION]);
            person.appendChild(personTel);
            root.appendChild(person);
            this.writeToFile(doc);
            PersonsDOMBuilder.LOGGER.info(Constants.PHRASE_RECORD_ADDED);
        } catch (final SAXParseException e) {
            PersonsDOMBuilder.LOGGER.error(Constants.SAX_PARSE_EXCEPTION, e);
        } catch (final SAXException e) {
            PersonsDOMBuilder.LOGGER.error(Constants.SAX_EXCEPTION, e);
        } catch (final IOException e) {
            PersonsDOMBuilder.LOGGER.error(Constants.IO_EXCEPTION, e);
        }
    }

    /**
     *
     * @param objPerson
     *            object person.
     */
    public void addRecord(final Person objPerson) {
        try {
            final Document doc = this.docBuilder.parse(this.pathToXMLFile);
            final Element root = doc.getDocumentElement();
            final Element person = doc.createElement(PersonsElements.PERSON.getNameElement());
            person.setAttribute(PersonsElements.ID.getNameElement(), objPerson.getId());
            final Element personFullName = doc
                    .createElement(PersonsElements.FULL_NAME.getNameElement());
            personFullName.setTextContent(objPerson.getFullName());
            person.appendChild(personFullName);
            final Element personAddress = doc
                    .createElement(PersonsElements.ADDRESS.getNameElement());
            personAddress.setTextContent(objPerson.getAddress());
            person.appendChild(personAddress);
            final Element personTel = doc.createElement(PersonsElements.TELEPHONE.getNameElement());
            personTel.setTextContent(objPerson.getTelephone());
            person.appendChild(personTel);
            root.appendChild(person);
            this.writeToFile(doc);
            PersonsDOMBuilder.LOGGER.info(Constants.PHRASE_RECORD_ADDED);
        } catch (final SAXParseException e) {
            PersonsDOMBuilder.LOGGER.error(Constants.SAX_PARSE_EXCEPTION, e);
            PersonsDOMBuilder.LOGGER.info(Constants.PHRASE_NOT_ADDED_RECORD);
        } catch (final SAXException e) {
            PersonsDOMBuilder.LOGGER.error(Constants.SAX_EXCEPTION, e);
        } catch (final IOException e) {
            PersonsDOMBuilder.LOGGER.error(Constants.IO_EXCEPTION, e);
        }
    }

    @Override
    public void connectAnotherXMLFile() {
        final String[] pathToAnotherXMLFile = this.getDataFromConsole(Constants.TYPE_PATH);
        try {
            PersonsDOMBuilder.addDTD(pathToAnotherXMLFile[Constants.PATH_POSITION]);
            this.docBuilder.parse(pathToAnotherXMLFile[Constants.PATH_POSITION]);
            this.pathToXMLFile = pathToAnotherXMLFile[Constants.PATH_POSITION];
        } catch (final SAXParseException e) {
            PersonsDOMBuilder.LOGGER.error(Constants.SAX_PARSE_EXCEPTION, e);
            PersonsDOMBuilder.LOGGER.error(Constants.PHRASE_NOT_INCLUDE);
        } catch (final SAXException e) {
            PersonsDOMBuilder.LOGGER.error(Constants.SAX_EXCEPTION, e);
        } catch (final IOException e) {
            PersonsDOMBuilder.LOGGER.error(Constants.IO_EXCEPTION, e);
        }
    }

    @Override
    public void deleteRecord() {
        final String[] id = this.getDataFromConsole(Constants.TYPE_ID);
        try {
            final Document doc = this.docBuilder.parse(this.pathToXMLFile);
            final Element root = doc.getDocumentElement();
            final NodeList personsList = root
                    .getElementsByTagName(PersonsElements.PERSON.getNameElement());
            for (int i = 0; i < personsList.getLength(); i++) {
                final Node personElement = personsList.item(i);
                final String foundId = ((Element) personElement)
                        .getAttribute(PersonsElements.ID.getNameElement());
                if (foundId.equals(id[Constants.ID_POSITION_CONSOLE])) {
                    PersonsDOMBuilder.LOGGER.info(Constants.PHRASE_CONFIRMATION);
                    final String response = this.in.nextLine();
                    if (response.equals(Constants.PHRASE_YES) // NOSONAR
                            || response.equals(String.valueOf(Constants.ONE))) {
                        root.removeChild(personElement);
                        PersonsDOMBuilder.LOGGER.info(Constants.PHRASE_RECORD_DELETED);
                    }
                }
            }
            this.writeToFile(doc);
        } catch (final SAXException e) {
            PersonsDOMBuilder.LOGGER.error(Constants.SAX_EXCEPTION, e);
        } catch (final IOException e) {
            PersonsDOMBuilder.LOGGER.error(Constants.IO_EXCEPTION, e);
        }
    }

    @Override
    public void editRecord() {
        final String[] values = this.getDataFromConsole(Constants.TYPE_ALL);
        try {
            final Document doc = this.docBuilder.parse(this.pathToXMLFile);
            final Element root = doc.getDocumentElement();
            final NodeList personsList = root
                    .getElementsByTagName(PersonsElements.PERSON.getNameElement());
            for (int i = 0; i < personsList.getLength(); i++) {
                final Element personElement = (Element) personsList.item(i);
                final String foundId = personElement
                        .getAttribute(PersonsElements.ID.getNameElement());
                if (foundId.equals(values[Constants.ID_POSITION])) {
                    Node node = PersonsDOMBuilder.getNodeElement(personElement,
                            PersonsElements.FULL_NAME.getNameElement());
                    node.setTextContent(values[Constants.FULL_NAME_POSITION]);
                    node = PersonsDOMBuilder.getNodeElement(personElement,
                            PersonsElements.ADDRESS.getNameElement());
                    node.setTextContent(values[Constants.ADDRESS_POSITION]);
                    node = PersonsDOMBuilder.getNodeElement(personElement,
                            PersonsElements.TELEPHONE.getNameElement());
                    node.setTextContent(values[Constants.TELEPHONE_POSITION]);
                    this.writeToFile(doc);
                    PersonsDOMBuilder.LOGGER.info(Constants.PHRASE_RECORD_CHANGED);
                    return;
                }
            }
            PersonsDOMBuilder.LOGGER.info(Constants.PHRASE_NOT_FOUND_RECORD);
        } catch (final SAXParseException e) {
            PersonsDOMBuilder.LOGGER.error(Constants.SAX_PARSE_EXCEPTION, e);
        } catch (final SAXException e) {
            PersonsDOMBuilder.LOGGER.error(Constants.SAX_EXCEPTION, e);
        } catch (final IOException e) {
            PersonsDOMBuilder.LOGGER.error(Constants.IO_EXCEPTION, e);
        }

    }

    @Override
    public void searchRecord() {
        final String[] searchInfo = this.getDataFromConsole(Constants.TYPE_SEARCH_INFO);
        try {
            final Document doc = this.docBuilder.parse(this.pathToXMLFile);
            final Element root = doc.getDocumentElement();
            final NodeList personsList = root
                    .getElementsByTagName(PersonsElements.PERSON.getNameElement());
            final EnumSet<PersonsElements> elEnumSet = EnumSet.range(PersonsElements.FULL_NAME,
                    PersonsElements.TELEPHONE);
            final String header = String.format(Constants.FORMAT_HEADER, Constants.HEADER_ID,
                    Constants.HEADER_FULL_NAME, Constants.HEADER_ADDRESS,
                    Constants.HEADER_TELEPHONE);
            PersonsDOMBuilder.LOGGER.info(header);
            for (int i = 0; i < personsList.getLength(); i++) {
                final Element personElement = (Element) personsList.item(i);
                for (final PersonsElements pEl : elEnumSet) {
                    final Node node = PersonsDOMBuilder.getNodeElement(personElement,
                            pEl.getNameElement());
                    if (node.getTextContent()// NOSONAR
                            .contains(searchInfo[Constants.SEARCH_INFO_POSITION])) {
                        PersonsDOMBuilder.LOGGER.info(PersonsDOMBuilder.buildPerson(personElement));
                        break;
                    }
                }
            }
        } catch (final SAXException e) {
            PersonsDOMBuilder.LOGGER.error(Constants.SAX_EXCEPTION, e);
        } catch (final IOException e) {
            PersonsDOMBuilder.LOGGER.error(Constants.IO_EXCEPTION, e);
        }

    }

    @Override
    public void showAllRecords() {
        try {
            final Document doc = this.docBuilder.parse(this.pathToXMLFile);
            final Element root = doc.getDocumentElement();
            final NodeList personsList = root
                    .getElementsByTagName(PersonsElements.PERSON.getNameElement());
            final String header = String.format(Constants.FORMAT_HEADER, Constants.HEADER_ID,
                    Constants.HEADER_FULL_NAME, Constants.HEADER_ADDRESS,
                    Constants.HEADER_TELEPHONE);
            PersonsDOMBuilder.LOGGER.info(header);
            for (int i = 0; i < personsList.getLength(); i++) {
                final Element personElement = (Element) personsList.item(i);
                PersonsDOMBuilder.LOGGER.info(PersonsDOMBuilder.buildPerson(personElement));
            }
        } catch (final SAXException e) {
            PersonsDOMBuilder.LOGGER.error(Constants.SAX_EXCEPTION, e);
        } catch (final IOException e) {
            PersonsDOMBuilder.LOGGER.error(Constants.IO_EXCEPTION, e);
        }

    }

    private String[] getDataFromConsole(final String type) {
        String[] values;
        switch (type) {
            case Constants.TYPE_ID:
                values = new String[Constants.ONE];
                PersonsDOMBuilder.LOGGER.info(Constants.PHRASE_ID);
                values[Constants.ID_POSITION] = this.in.nextLine();
                break;
            case Constants.TYPE_PATH:
                values = new String[Constants.ONE];
                PersonsDOMBuilder.LOGGER.info(Constants.PHRASE_ENTER_PATH);
                values[Constants.PATH_POSITION] = this.in.nextLine();
                break;
            case Constants.TYPE_ALL:
                values = this.getPersonValuesFromConsole();
                break;
            case Constants.TYPE_SEARCH_INFO:
                values = new String[Constants.ONE];
                PersonsDOMBuilder.LOGGER.info(Constants.PHRASE_SEARCH_INFO);
                values[Constants.ALL_POSITION] = this.in.nextLine();
                break;
            default:
                values = null;
                break;
        }
        return values;
    }

    /**
     *
     * @return array of all values for adding new record.
     */
    private String[] getPersonValuesFromConsole() {
        final String[] values = new String[Constants.QUANTITY_PERSON_FIELDS];
        PersonsDOMBuilder.LOGGER.info(Constants.PHRASE_ID);
        values[Constants.ID_POSITION] = this.in.nextLine();
        PersonsDOMBuilder.LOGGER.info(Constants.PHRASE_FULL_NAME);
        values[Constants.FULL_NAME_POSITION] = this.in.nextLine();
        PersonsDOMBuilder.LOGGER.info(Constants.PHRASE_ADDRESS);
        values[Constants.ADDRESS_POSITION] = this.in.nextLine();
        PersonsDOMBuilder.LOGGER.info(Constants.PHRASE_TELEPHONE);
        values[Constants.TELEPHONE_POSITION] = this.in.nextLine();
        return values;
    }

    /**
     *
     * @param document
     *            input Document to writing in xml file.
     */
    private void writeToFile(final Document document) throws SAXParseException {
        final Path pathToDTDFile = Paths.get(Constants.PATH_TO_DTD_FILE);
        final StringWriter xmlAsWriter = new StringWriter();
        FileWriter fw = null;
        try {
            final Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,
                    pathToDTDFile.toAbsolutePath().toString());
            transformer.setOutputProperty(OutputKeys.INDENT, Constants.PHRASE_YES);
            transformer.transform(new DOMSource(document), new StreamResult(xmlAsWriter));
            final ByteArrayInputStream inputStream = new ByteArrayInputStream(
                    xmlAsWriter.toString().getBytes(Charset.defaultCharset()));
            this.docBuilder.parse(inputStream);
            fw = new FileWriter(this.pathToXMLFile);
            fw.write(xmlAsWriter.toString());
            fw.close();
        } catch (final TransformerConfigurationException e) {
            PersonsDOMBuilder.LOGGER.error(Constants.TRANSFORMER_CONFIG_EXCEPTION, e);
        } catch (final TransformerException e) {
            PersonsDOMBuilder.LOGGER.error(Constants.TRANSFORMER_EXCEPTION, e);
        } catch (final SAXParseException e) {
            PersonsDOMBuilder.LOGGER.info(Constants.PHRASE_ERROR_DTD);
            throw e;
        } catch (final SAXException e) {
            PersonsDOMBuilder.LOGGER.info(Constants.PHRASE_ERROR_DTD);
            PersonsDOMBuilder.LOGGER.error(Constants.SAX_EXCEPTION, e);
        } catch (final IOException e) {
            PersonsDOMBuilder.LOGGER.error(Constants.IO_EXCEPTION, e);
        }
    }

}
