package lt.viko.eif.tviliusis.computershopsystem.model;

import jakarta.xml.bind.annotation.*;

import javax.persistence.*;
import java.util.List;

/**
 * The Account class represents a user account in the computer shop system.
 * An account contains a username, password, and shipping information for the client, as well as a list of categories
 * associated with the account.
 *
 * @XmlRootElement and @XmlAccessorType annotations are used for XML binding.
 * @Entity and @Table annotations are used for database mapping.
 */

@XmlRootElement(name = "Account")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id", "userName", "password", "clientShipping", "categories"})
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    private String userName;
    private String password;
    @OneToOne(targetEntity = ClientShipping.class, cascade = CascadeType.ALL)
    private ClientShipping clientShipping;

    @OneToMany(targetEntity = Categories.class, cascade = CascadeType.ALL)
    @XmlElement(name = "categories")
    private List<Categories> categories;

    /**
     * Get the unique identifier of the Account.
     *
     * @return The unique identifier of the Account.
     */
    public int getId() {
        return id;
    }

    /**
     * Set the unique identifier of the Account.
     *
     * @param id The unique identifier of the Account.
     */

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the username of the Account.
     *
     * @return The username of the Account.
     */

    public String getUserName() {
        return userName;
    }

    /**
     * Set the username of the Account.
     *
     * @param userName The username of the Account.
     */

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Get the password of the Account.
     *
     * @return The password of the Account.
     */

    public String getPassword() {
        return password;
    }

    /**
     * Set the password of the Account.
     *
     * @param password The password of the Account.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public Account() {

    }

    /**
     * Constructor for the Account class with parameters for all fields.
     *
     * @param id             The ID of the account in the database.
     * @param userName       The username associated with the account.
     * @param password       The password associated with the account.
     * @param clientShipping The shipping information associated with the client for the account.
     * @param categories     The list of categories associated with the account.
     */
    public Account(int id, String userName, String password, ClientShipping clientShipping, List<Categories> categories) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.clientShipping = clientShipping;
        this.categories = categories;


    }

    /**
     * Constructor for the Account class with parameters for user name and password only.
     *
     * @param userName The username associated with the account.
     * @param password The password associated with the account.
     */
    public Account(String userName, String password) {

        this.userName = userName;
        this.password = password;

    }

    /**
     * Constructor for the Account class with parameters for all fields except ID.
     *
     * @param userName       The username associated with the account.
     * @param password       The password associated with the account.
     * @param clientShipping The shipping information associated with the client for the account.
     * @param categories     The list of categories associated with the account.
     */

    public Account(String userName, String password, ClientShipping clientShipping, List<Categories> categories) {
        this.userName = userName;
        this.password = password;
        this.clientShipping = clientShipping;
        this.categories = categories;

    }

    @Override
    public String toString() {
        return
                String.format("Account:\n\t" +
                                "Username = %s\n\t" +
                                "Password = %s\n" +
                                "\tClientShipping: \n\t%s\n" +
                                "\tCategories: \n%s"
                        ,
                        this.userName,
                        this.password,
                        this.clientShipping,
                        this.categories);
    }
}
