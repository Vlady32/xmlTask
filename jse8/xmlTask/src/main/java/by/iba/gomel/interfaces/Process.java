package by.iba.gomel.interfaces;

import by.iba.gomel.spare.Spare;

/**
 * This interface describes one method processing.
 */
public interface Process {

    /**
     *
     * @param spare
     *            input spare.
     * @param position
     *            iteml of spare.
     */
    public void processing(Spare spare, int position);
}
