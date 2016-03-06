package by.iba.gomel.sax.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.iba.gomel.Constants;
import by.iba.gomel.interfaces.Process;
import by.iba.gomel.spare.Spare;

/**
 * This class implements interface Process and realizes method processing. This method searches key,
 * model auto and mark auto and print out to console.
 */
public class SearchSpareProcess implements Process {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchSpareProcess.class);
    private final String        searchInfo;

    public SearchSpareProcess(final String searchInfo) {
        this.searchInfo = searchInfo;
    }

    @Override
    public void processing(final Spare spare, final int position) { // NOSONAR
        if (spare.getId().toLowerCase().contains(this.searchInfo)
                || spare.getMarkAuto().toLowerCase().contains(this.searchInfo)
                || spare.getModelAuto().toLowerCase().contains(this.searchInfo)) {
            final String outputSpare = String.format(Constants.FORMAT_SPARE, position,
                    spare.getId(), spare.getMarkAuto(), spare.getModelAuto(), spare.getCost());
            SearchSpareProcess.LOGGER.info(outputSpare);
        }
    }

}
