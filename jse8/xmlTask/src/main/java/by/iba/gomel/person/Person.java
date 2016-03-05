package by.iba.gomel.person;

/**
 * This class contains all information about person.
 */
public class Person {

    private String id;
    private String fullName;
    private String address;
    private String telephone;

    /**
     * Default public constructor.
     */
    public Person() {
        // Empty constructor.
    }

    /**
     *
     * @param id
     *            unique person's id.
     * @param fullName
     *            person's full name.
     * @param address
     *            person's address.
     * @param telephone
     *            person's telephone.
     */
    public Person(final String id, final String fullName, final String address,
            final String telephone) {
        super();
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.telephone = telephone;
    }

    public String getAddress() {
        return this.address;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getId() {
        return this.id;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public void setTelephone(final String telephone) {
        this.telephone = telephone;
    }
}
