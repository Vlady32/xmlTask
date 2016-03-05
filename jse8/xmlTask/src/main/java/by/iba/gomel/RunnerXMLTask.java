package by.iba.gomel;

import by.iba.gomel.menu.Menu;

/**
 * This class contains method main that launches this application.
 */
public class RunnerXMLTask {

    /**
     * Default private constructor.
     */
    private RunnerXMLTask() {

    }

    /**
     *
     * @param args
     *            input parameters (not used).
     */
    public static void main(final String[] args) {
        new Menu().showMenuForChoosingParser();
    }

}
