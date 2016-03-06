package by.iba.gomel.sax;

import java.util.EnumSet;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import by.iba.gomel.Constants;
import by.iba.gomel.enums.SparesElements;
import by.iba.gomel.interfaces.Process;
import by.iba.gomel.spare.Spare;

public class SparesSAXHandler extends DefaultHandler {
    private Spare                         currentSpare    = null;
    private SparesElements                currentEnum     = null;
    private Process                       process         = null;
    private final EnumSet<SparesElements> rangeSpareFeatures;
    private int                           currentPosition = Constants.ONE;

    /**
     * Constructor creates set of spares.
     */
    public SparesSAXHandler() {
        this.rangeSpareFeatures = EnumSet.range(SparesElements.MARKAUTO, SparesElements.COST);
    }

    @Override
    public void characters(final char[] ch, final int start, final int length) throws SAXException {
        final String line = new String(ch, start, length);
        if (this.currentEnum != null) {
            switch (this.currentEnum) {
                case MARKAUTO:
                    this.currentSpare.setMarkAuto(line);
                    break;
                case MODELAUTO:
                    this.currentSpare.setModelAuto(line);
                    break;
                case COST:
                    this.currentSpare.setCost(Integer.parseInt(line));
                    break;
                default:
                    throw new EnumConstantNotPresentException(this.currentEnum.getDeclaringClass(),
                            this.currentEnum.name());
            }
        }
        this.currentEnum = null;
    }

    @Override
    public void endDocument() throws SAXException {
        this.process = null;
        this.currentPosition = Constants.ONE;
    }

    @Override
    public void endElement(final String uri, final String localName, final String qName)
            throws SAXException {
        if (SparesElements.SPARE.getNameElement().equals(localName)) {
            this.process.processing(this.currentSpare, this.currentPosition);
            this.currentPosition++;
        }
    }

    /**
     *
     * @param process
     *            set the process: show or search.
     */
    public void setProcess(final Process process) {
        this.process = process;
    }

    @Override
    public void startElement(final String uri, final String localName, final String qName,
            final Attributes attributes) throws SAXException {
        if (SparesElements.SPARE.getNameElement().equals(localName)) {
            this.currentSpare = new Spare();
            this.currentSpare.setId(attributes.getValue(0));
        } else {
            final SparesElements temp = SparesElements.valueOf(localName.toUpperCase());
            if (this.rangeSpareFeatures.contains(temp)) {
                this.currentEnum = temp;
            }
        }
    }
}
