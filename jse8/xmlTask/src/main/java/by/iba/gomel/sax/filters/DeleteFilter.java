package by.iba.gomel.sax.filters;

import java.nio.charset.Charset;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLFilterImpl;

import by.iba.gomel.Constants;

/**
 * This class extends XMLFilterImpl and realizes methods for deleting element from xml file.
 */
public class DeleteFilter extends XMLFilterImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteFilter.class);
    private Scanner             in     = new Scanner(System.in, Charset.defaultCharset().name());
    private boolean             isRightSpare;
    private final String        key;

    /**
     *
     * @param reader
     *            xml reader.
     * @param key
     *            key for deleting.
     */
    public DeleteFilter(final XMLReader reader, final String key, final Scanner in) {
        super(reader);
        this.in = in;
        this.key = key;
    }

    @Override
    public void characters(final char[] ch, final int start, final int length) throws SAXException {
        if (!this.isRightSpare) {
            super.characters(ch, start, length);
        }
    }

    @Override
    public void endElement(final String uri, final String localName, final String qName)
            throws SAXException {
        if (!this.isRightSpare || localName.equals(Constants.ELEMENT_SPARES)) {
            super.endElement(uri, localName, qName);
        }
    }

    @Override
    public void startElement(final String uri, final String localName, final String qName,
            final Attributes atts) throws SAXException {
        if ((atts.getValue(Constants.ATTRIBUTE_KEY) != null)
                || localName.equals(Constants.ELEMENT_SPARES)) {
            this.isRightSpare = false;
        }
        if (localName.equals(Constants.ELEMENT_SPARE)
                && atts.getValue(Constants.ATTRIBUTE_KEY).equals(this.key)) {
            DeleteFilter.LOGGER.info(Constants.PHRASE_CONFIRMATION);
            final String response = this.in.nextLine();
            if (response.equals(Constants.PHRASE_YES)
                    || response.equals(String.valueOf(Constants.ONE))) {
                this.isRightSpare = true;
            }
        }
        if (!this.isRightSpare) {
            super.startElement(uri, localName, qName, atts);
        }
    }
}
