package by.iba.gomel.spare;

/**
 * This class contains all information about person.
 */
public class Spare {

    private String id;
    private String markAuto;
    private String modelAuto;
    private int    cost;

    /**
     * Default public constructor.
     */
    public Spare() {
        // Empty constructor.
    }

    /**
     *
     * @param id
     *            spare's id.
     * @param markAuto
     *            spare's mark auto.
     * @param modelAuto
     *            spare's model auto.
     * @param cost
     *            spare's cost.
     */
    public Spare(final String id, final String markAuto, final String modelAuto, final int cost) {
        this.id = id;
        this.markAuto = markAuto;
        this.modelAuto = modelAuto;
        this.cost = cost;
    }

    public int getCost() {
        return this.cost;
    }

    public String getId() {
        return this.id;
    }

    public String getMarkAuto() {
        return this.markAuto;
    }

    public String getModelAuto() {
        return this.modelAuto;
    }

    public void setCost(final int cost) {
        this.cost = cost;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public void setMarkAuto(final String markAuto) {
        this.markAuto = markAuto;
    }

    public void setModelAuto(final String modelAuto) {
        this.modelAuto = modelAuto;
    }

}
