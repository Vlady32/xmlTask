package by.iba.gomel.sax.filters;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.XMLFilterImpl;

import by.iba.gomel.Constants;

public class AddFilter extends XMLFilterImpl {

    private final String key;
    private final String markAuto;
    private final String modelAuto;
    private final int    cost;

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
    public AddFilter(final XMLReader reader, final String key, final String markAuto,
            final String modelAuto, final int cost) {
        super(reader);
        this.key = key;
        this.markAuto = markAuto;
        this.modelAuto = modelAuto;
        this.cost = cost;
    }

    @Override
    public void endElement(final String uri, final String localName, final String qName)
            throws SAXException {
        if (localName.equals(Constants.ELEMENT_SPARES)) {
            final AttributesImpl emptyAttr = new AttributesImpl();
            final AttributesImpl attrKey = new AttributesImpl();
            attrKey.addAttribute(Constants.EMPTY_LINE, Constants.ATTRIBUTE_KEY,
                    Constants.ATTRIBUTE_KEY, Constants.TYPE_ID, this.key);
            super.startElement(Constants.EMPTY_LINE, Constants.ELEMENT_SPARE,
                    Constants.ELEMENT_SPARE, attrKey);
            super.characters(Constants.EMPTY_LINE.toCharArray(), 0, Constants.EMPTY_LINE.length());
            this.writeElement(Constants.EMPTY_LINE, Constants.ELEMENT_MARK_AUTO,
                    Constants.ELEMENT_MARK_AUTO, emptyAttr, this.markAuto);
            this.writeElement(Constants.EMPTY_LINE, Constants.ELEMENT_MODEL_AUTO,
                    Constants.ELEMENT_MODEL_AUTO, emptyAttr, this.modelAuto);
            this.writeElement(Constants.EMPTY_LINE, Constants.ELEMENT_COST, Constants.ELEMENT_COST,
                    emptyAttr, String.valueOf(this.cost));
            super.endElement(Constants.EMPTY_LINE, Constants.ELEMENT_SPARE,
                    Constants.ELEMENT_SPARE);
        }
        super.endElement(uri, localName, qName);
    }

    /**
     *
     * @param uri
     *            uri.
     * @param localName
     *            local name of tag.
     * @param qName
     *            full name of tag.
     * @param atts
     *            attributes.
     * @param data
     *            data for inserting.
     * @throws SAXException
     *             saxException.
     */
    private void writeElement(final String uri, final String localName, final String qName,
            final Attributes atts, final String data) throws SAXException {
        super.startElement(uri, localName, qName, atts);
        super.characters(data.toCharArray(), 0, data.length());
        super.endElement(uri, localName, qName);
    }
}
