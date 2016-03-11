package by.iba.gomel.sax.filters;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLFilterImpl;

import by.iba.gomel.Constants;
import by.iba.gomel.enums.SparesElements;

/**
 * This class extends XMLFilterImpl and realizes methods for updating element from xml file.
 */
public class UpdateFilter extends XMLFilterImpl {

    private static final Logger LOGGER        = LoggerFactory.getLogger(UpdateFilter.class);
    private SparesElements      currentElement;
    private final String        key;
    private String              markAuto;
    private String              modelAuto;
    private int                 cost;
    private boolean             isSpare;
    private final Scanner       in;
    private boolean             isFoundRecord = false;

    public UpdateFilter(final XMLReader reader, final String key, final Scanner in) {
        super(reader);
        this.key = key;
        this.markAuto = new String();
        this.modelAuto = new String();
        this.cost = 0;
        this.in = in;
    }

    @Override
    public void characters(final char[] ch, final int start, final int length) throws SAXException {
        String replaceLine;
        if (this.isSpare && (this.currentElement != null)) {
            switch (this.currentElement) {
                case MARKAUTO:
                    replaceLine = this.markAuto;
                    break;
                case MODELAUTO:
                    replaceLine = this.modelAuto;
                    break;
                case COST:
                    replaceLine = String.valueOf(this.cost);
                    break;
                default:
                    throw new EnumConstantNotPresentException(
                            this.currentElement.getDeclaringClass(), this.currentElement.name());
            }
            super.characters(replaceLine.toCharArray(), 0, replaceLine.length());
        } else {
            super.characters(ch, start, length);
        }
    }

    @Override
    public void endElement(final String uri, final String localName, final String qName)
            throws SAXException {
        if (qName.equals(Constants.ELEMENT_SPARE) && this.isSpare) {
            this.isSpare = false;
        }
        this.currentElement = null;
        if (localName.equals(Constants.ELEMENT_SPARES) && !this.isFoundRecord) {
            UpdateFilter.LOGGER.info(Constants.PHRASE_NOT_FOUND_RECORD);
        }
        super.endElement(uri, localName, qName);
    }

    public boolean isFoundRecord() {
        return this.isFoundRecord;
    }

    @Override
    public void startElement(final String uri, final String localName, final String qName,
            final Attributes atts) throws SAXException {
        if (qName.equals(Constants.ELEMENT_SPARE)
                && (atts.getValue(Constants.ATTRIBUTE_KEY).equals(this.key))) {
            this.isSpare = true;
            this.isFoundRecord = true;
            final String[] values = this.getSpareValuesFromConsole();
            this.markAuto = values[Constants.MARK_AUTO_POSITION];
            this.modelAuto = values[Constants.MODEL_AUTO_POSITION];
            this.cost = Integer.parseInt(values[Constants.COST_POSITION]);
        }

        if (this.isSpare && !qName.equals(Constants.ELEMENT_SPARE)) {
            this.currentElement = SparesElements.valueOf(localName.toUpperCase());
        }
        super.startElement(uri, localName, qName, atts);
    }

    /**
     *
     * @return array of all values for adding new record.
     */
    private String[] getSpareValuesFromConsole() {
        final String[] values = new String[Constants.QUANTITY_SPARE_FIELDS];
        values[Constants.KEY_POSITION] = this.key;
        UpdateFilter.LOGGER.info(Constants.PHRASE_MARK_AUTO);
        values[Constants.MARK_AUTO_POSITION] = this.in.nextLine();
        UpdateFilter.LOGGER.info(Constants.PHRASE_MODEL_AUTO);
        values[Constants.MODEL_AUTO_POSITION] = this.in.nextLine();
        UpdateFilter.LOGGER.info(Constants.PHRASE_COST);
        values[Constants.COST_POSITION] = this.in.nextLine();
        return values;
    }

}
