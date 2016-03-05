package by.iba.gomel.enums;

import by.iba.gomel.Constants;

/**
 * This enumeration describes all elements and attributes in xml file.
 */
public enum PersonsElements {
    PERSONS(Constants.ELEMENT_PERSONS), PERSON(Constants.ELEMENT_PERSON), FULL_NAME(
            Constants.ELEMENT_FULL_NAME), ADDRESS(Constants.ELEMENT_ADDRESS), TELEPHONE(
                    Constants.ELEMENT_TELEPHONE), ID(Constants.ATTRIBUTE_ID);
    String nameElement;

    private PersonsElements(final String nameElement) {
        this.nameElement = nameElement;
    }

    public String getNameElement() {
        return this.nameElement;
    }

}
