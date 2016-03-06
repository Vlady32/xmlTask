package by.iba.gomel.sax;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import by.iba.gomel.Constants;

/**
 * This class contains method validate for checking xml file by xsd schema.
 */
public class ValidatorSAXXSD {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidatorSAXXSD.class);

    /**
     * Default private constructor.
     */
    private ValidatorSAXXSD() {

    }

    /**
     *
     * @param xmlFile
     *            xmlFile for checking.
     * @param xsdSchema
     *            xsd schema for checking.
     * @return result of validating.
     */
    public static boolean validate(final Source xmlFile, final Source xsdSchema) {
        final SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema;
        try {
            schema = factory.newSchema(xsdSchema);
            final Validator validator = schema.newValidator();
            final Source source = xmlFile;
            validator.validate(source);
        } catch (final SAXException e) {
            ValidatorSAXXSD.LOGGER.info(Constants.SAX_EXCEPTION, e);
            return false;
        } catch (final IOException e) {
            ValidatorSAXXSD.LOGGER.info(Constants.IO_EXCEPTION, e);
        }
        return true;
    }

    /**
     *
     * @param pathToXMLFile
     *            path to xml file.
     * @param pathToXSDSchema
     *            path to xsd file.
     * @return result of validating.
     */
    public static boolean validate(final String pathToXMLFile, final String pathToXSDSchema) {
        final SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        final File schemaLocation = new File(pathToXSDSchema);
        Schema schema;
        try {
            schema = factory.newSchema(schemaLocation);
            final Validator validator = schema.newValidator();
            final Source source = new StreamSource(pathToXMLFile);
            validator.validate(source);
        } catch (final SAXException e) {
            ValidatorSAXXSD.LOGGER.info(Constants.SAX_EXCEPTION, e);
            return false;
        } catch (final IOException e) {
            ValidatorSAXXSD.LOGGER.info(Constants.IO_EXCEPTION, e);
        }
        return true;
    }
}
