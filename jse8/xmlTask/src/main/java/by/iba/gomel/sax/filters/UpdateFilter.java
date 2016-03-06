package by.iba.gomel.sax.filters;

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

    private SparesElements currentElement;
    private final String   key;
    private final String   markAuto;
    private final String   modelAuto;
    private final int      cost;
    private boolean        isSpare;

    /**
     *
     * @param reader
     *            xml reader.
     * @param key
     *            key for updating.
     * @param mark
     *            auto mark auto for updating.
     * @param model
     *            auto model auto for updating.
     * @param cost
     *            cost for updating.
     */
    public UpdateFilter(final XMLReader reader, final String key, final String markAuto,
            final String modelAuto, final int cost) {
        super(reader);
        this.key = key;
        this.markAuto = markAuto;
        this.modelAuto = modelAuto;
        this.cost = cost;
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
        super.endElement(uri, localName, qName);
    }

    @Override
    public void startElement(final String uri, final String localName, final String qName,
            final Attributes atts) throws SAXException {
        if (qName.equals(Constants.ELEMENT_SPARE)
                && (atts.getValue(Constants.ATTRIBUTE_KEY).equals(this.key))) {
            this.isSpare = true;
        }
        if (this.isSpare && !qName.equals(Constants.ELEMENT_SPARE)) {
            this.currentElement = SparesElements.valueOf(localName.toUpperCase());
        }
        super.startElement(uri, localName, qName, atts);
    }

}
