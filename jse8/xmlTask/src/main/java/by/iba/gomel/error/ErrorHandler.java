package by.iba.gomel.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import by.iba.gomel.Constants;

/**
 * This class describes methods errors.
 */
public class ErrorHandler extends DefaultHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandler.class);

    @Override
    public void error(final SAXParseException exception) throws SAXParseException {
        throw exception;
    }

    @Override
    public void fatalError(final SAXParseException exception) {
        ErrorHandler.LOGGER.error(Constants.SAX_PARSE_EXCEPTION, exception);
    }

    @Override
    public void warning(final SAXParseException exception) {
        ErrorHandler.LOGGER.error(Constants.SAX_PARSE_EXCEPTION, exception);
    }
}
