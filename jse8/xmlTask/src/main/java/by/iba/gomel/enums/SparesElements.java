package by.iba.gomel.enums;

import by.iba.gomel.Constants;

/**
 * This enumeration describes all elements and attributes in xml file.
 */
public enum SparesElements {
    SPARES(Constants.ELEMENT_SPARES), SPARE(Constants.ELEMENT_SPARE), MARKAUTO(
            Constants.ELEMENT_MARK_AUTO), MODELAUTO(Constants.ELEMENT_MODEL_AUTO), COST(
                    Constants.ELEMENT_COST), KEY(Constants.ATTRIBUTE_KEY);
    String nameElement;

    private SparesElements(final String nameElement) {
        this.nameElement = nameElement;
    }

    public String getNameElement() {
        return this.nameElement;
    }
}
