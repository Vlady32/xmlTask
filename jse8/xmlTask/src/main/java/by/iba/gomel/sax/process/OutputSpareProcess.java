package by.iba.gomel.sax.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.iba.gomel.Constants;
import by.iba.gomel.interfaces.Process;
import by.iba.gomel.spare.Spare;

/**
 * This class implements interface Process and realizes method processing. This method prints to
 * console data of spare.
 */
public class OutputSpareProcess implements Process {

    private static final Logger LOGGER = LoggerFactory.getLogger(OutputSpareProcess.class);

    @Override
    public void processing(final Spare spare, final int position) { // NOSONAR
        final String outputSpare = String.format(Constants.FORMAT_SPARE, position, spare.getId(),
                spare.getMarkAuto(), spare.getModelAuto(), spare.getCost());
        OutputSpareProcess.LOGGER.info(outputSpare);
    }

}
