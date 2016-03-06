package by.iba.gomel.sax;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.Scanner;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.iba.gomel.Constants;
import by.iba.gomel.error.ErrorHandler;
import by.iba.gomel.interfaces.IXMLParser;
import by.iba.gomel.sax.filters.AddFilter;
import by.iba.gomel.sax.filters.DeleteFilter;
import by.iba.gomel.sax.filters.UpdateFilter;
import by.iba.gomel.sax.process.OutputSpareProcess;
import by.iba.gomel.sax.process.SearchSpareProcess;
import by.iba.gomel.spare.Spare;

public class SparesSAXBuilder implements IXMLParser {

    private static final Logger    LOGGER = LoggerFactory.getLogger(SparesSAXBuilder.class);
    private Scanner                in     = new Scanner(System.in, Charset.defaultCharset().name());
    private final SparesSAXHandler saxHandler;
    private XMLReader              reader;
    private String                 pathToXMLFile;
    private final String           pathToXSDFile;

    /**
     *
     * @param pathToXMLFile
     *            path to xml file.
     */
    public SparesSAXBuilder(final String pathToXMLFile, final String pathToXSDFile,
            final Scanner in) {
        this.in = in;
        this.saxHandler = new SparesSAXHandler();
        this.pathToXMLFile = pathToXMLFile;
        this.pathToXSDFile = pathToXSDFile;
        try {
            this.reader = XMLReaderFactory.createXMLReader();
            this.reader.setContentHandler(this.saxHandler);
            this.reader.setErrorHandler(new ErrorHandler());
        } catch (final SAXException e) {
            SparesSAXBuilder.LOGGER.error(Constants.SAX_EXCEPTION, e);
        }
    }

    @Override
    public void addRecord() {
        try {
            final String[] values = this.getDataFromConsole(Constants.TYPE_ALL);
            final XMLReader xr = new AddFilter(this.reader, values[Constants.KEY_POSITION],
                    values[Constants.MARK_AUTO_POSITION], values[Constants.MODEL_AUTO_POSITION],
                    Integer.parseInt(values[Constants.COST_POSITION]));
            this.writeToFile(xr, this.pathToXMLFile, true);
        } catch (final NumberFormatException e) {
            SparesSAXBuilder.LOGGER.error(Constants.NUMBER_FORMAT_EXCEPTION, e);
            SparesSAXBuilder.LOGGER.info(Constants.PHRASE_WRONG_COST);
        }
    }

    /**
     *
     * @param spare
     *            add object in xml file.
     */
    public void addRecord(final Spare spare) {
        final XMLReader xr = new AddFilter(this.reader, spare.getId(), spare.getMarkAuto(),
                spare.getModelAuto(), spare.getCost());
        this.writeToFile(xr, this.pathToXMLFile, true);
    }

    @Override
    public void connectAnotherXMLFile() {
        final String[] values = this.getDataFromConsole(Constants.TYPE_PATH);

        if (ValidatorSAXXSD.validate(values[Constants.PATH_POSITION], this.pathToXSDFile)) {
            this.pathToXMLFile = values[Constants.PATH_POSITION];
        }
    }

    @Override
    public void deleteRecord() {
        final String[] values = this.getDataFromConsole(Constants.TYPE_KEY);
        final XMLReader xr = new DeleteFilter(this.reader, values[Constants.KEY_POSITION], this.in);
        this.writeToFile(xr, this.pathToXMLFile, false);
    }

    @Override
    public void editRecord() {
        final String[] values = this.getDataFromConsole(Constants.TYPE_ALL);
        final XMLReader xr = new UpdateFilter(this.reader, values[Constants.KEY_POSITION],
                values[Constants.MARK_AUTO_POSITION], values[Constants.MODEL_AUTO_POSITION],
                Integer.parseInt(values[Constants.COST_POSITION]));
        this.writeToFile(xr, this.pathToXMLFile, true);
    }

    @Override
    public void searchRecord() {
        final String[] values = this.getDataFromConsole(Constants.TYPE_SEARCH_INFO);
        this.saxHandler.setProcess(
                new SearchSpareProcess(values[Constants.SEARCH_INFO_POSITION].toLowerCase()));
        final String header = String.format(Constants.FORMAT_HEADER_SAX, Constants.HEADER_ITEM,
                Constants.HEADER_KEY, Constants.HEADER_MARK_AUTO, Constants.HEADER_MODEL_AUTO,
                Constants.HEADER_COST);
        SparesSAXBuilder.LOGGER.info(header + Constants.DIVIDING_LINE);
        try {
            this.reader.parse(this.pathToXMLFile);
        } catch (final IOException e) {
            SparesSAXBuilder.LOGGER.error(Constants.IO_EXCEPTION, e);
        } catch (final SAXException e) {
            SparesSAXBuilder.LOGGER.error(Constants.SAX_EXCEPTION, e);
        }

    }

    @Override
    public void showAllRecords() {
        this.saxHandler.setProcess(new OutputSpareProcess());
        final String header = String.format(Constants.FORMAT_HEADER_SAX, Constants.HEADER_ITEM,
                Constants.HEADER_KEY, Constants.HEADER_MARK_AUTO, Constants.HEADER_MODEL_AUTO,
                Constants.HEADER_COST);
        SparesSAXBuilder.LOGGER.info(header + Constants.DIVIDING_LINE);
        try {
            this.reader.parse(this.pathToXMLFile);
        } catch (final IOException e) {
            SparesSAXBuilder.LOGGER.error(Constants.IO_EXCEPTION, e);
        } catch (final SAXException e) {
            SparesSAXBuilder.LOGGER.error(Constants.SAX_EXCEPTION, e);
        }
    }

    /**
     *
     * @param type
     *            type of getting data from console.
     * @return array of data.
     */
    private String[] getDataFromConsole(final String type) {
        String[] values;
        switch (type) {
            case Constants.TYPE_KEY:
                values = new String[Constants.ONE];
                SparesSAXBuilder.LOGGER.info(Constants.PHRASE_KEY);
                values[Constants.KEY_POSITION] = this.in.nextLine();
                break;
            case Constants.TYPE_PATH:
                values = new String[Constants.ONE];
                SparesSAXBuilder.LOGGER.info(Constants.PHRASE_ENTER_PATH);
                values[Constants.PATH_POSITION] = this.in.nextLine();
                break;
            case Constants.TYPE_ALL:
                values = this.getPersonValuesFromConsole();
                break;
            case Constants.TYPE_SEARCH_INFO:
                values = new String[Constants.ONE];
                SparesSAXBuilder.LOGGER.info(Constants.PHRASE_SEARCH_INFO_SPARE);
                values[Constants.SEARCH_INFO_POSITION] = this.in.nextLine();
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
        final String[] values = new String[Constants.QUANTITY_SPARE_FIELDS];
        SparesSAXBuilder.LOGGER.info(Constants.PHRASE_KEY);
        values[Constants.KEY_POSITION] = this.in.nextLine();
        SparesSAXBuilder.LOGGER.info(Constants.PHRASE_MARK_AUTO);
        values[Constants.MARK_AUTO_POSITION] = this.in.nextLine();
        SparesSAXBuilder.LOGGER.info(Constants.PHRASE_MODEL_AUTO);
        values[Constants.MODEL_AUTO_POSITION] = this.in.nextLine();
        SparesSAXBuilder.LOGGER.info(Constants.PHRASE_COST);
        values[Constants.COST_POSITION] = this.in.nextLine();
        return values;
    }

    /**
     *
     * @param xr
     *            updated xmlReader.
     * @param pathToXMLFile
     *            file for writing.
     */
    private void writeToFile(final XMLReader xr, final String pathToXMLFile,
            final boolean validation) {
        final StringWriter writtenResult = new StringWriter();
        final Source src = new SAXSource(xr, new InputSource(pathToXMLFile));
        final Result res = new StreamResult(writtenResult);

        if (validation
                && !ValidatorSAXXSD.validate(src, new StreamSource(Constants.PATH_TO_XSD_FILE))) {
            this.reader.setContentHandler(this.saxHandler);
            return;
        }

        try {
            TransformerFactory.newInstance().newTransformer().transform(src, res);
        } catch (final TransformerConfigurationException e) {
            SparesSAXBuilder.LOGGER.error(Constants.TRANSFORMER_CONFIG_EXCEPTION, e);
        } catch (final TransformerException e) {
            SparesSAXBuilder.LOGGER.error(Constants.TRANSFORMER_EXCEPTION, e);
        }
        final String result = writtenResult.toString();
        FileWriter write;
        try {
            write = new FileWriter(this.pathToXMLFile);
            write.write(result);
            write.close();
            this.reader.setContentHandler(this.saxHandler);
        } catch (final IOException e) {
            SparesSAXBuilder.LOGGER.error(Constants.IO_EXCEPTION, e);
        }
    }

}
