package lt.viko.eif.tviliusis.computershopsystem.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

import javax.persistence.*;
import java.util.List;


/**
 * The Categories class represents a product category in the computer shop system.
 * A category contains a name and a list of components that belong to it.
 */
@XmlType(propOrder = {"id", "categorieName", "components"})
@Entity
@Table(name = "categories")
public class Categories {

    /**
     * The ID of this category in the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    /**
     * The name of this category.
     */
    private String categorieName;

    /**
     * The list of components that belong to this category.
     */
    @OneToMany(targetEntity = Components.class, cascade = CascadeType.ALL)
    @XmlElement(name = "components")
    private List<Components> components;

    /**
     * Creates a new, empty Categories object.
     */
    public Categories() {

    }

    /**
     * Returns the ID of this category in the database.
     *
     * @return the ID of this category
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of this category in the database.
     *
     * @param id the new ID of this category
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the name of this category.
     *
     * @return the name of this category
     */
    public String getCategorieName() {
        return categorieName;
    }

    /**
     * Sets the name of this category.
     *
     * @param categorieName the new name of this category
     */
    public void setCategorieName(String categorieName) {
        this.categorieName = categorieName;
    }

    /**
     * Creates a new Categories object with the given ID, name, and list of components.
     *
     * @param id            the ID of the new category
     * @param categorieName the name of the new category
     * @param components    the list of components that belong to the new category
     */
    public Categories(int id, String categorieName, List<Components> components) {
        this.id = id;
        this.categorieName = categorieName;
        this.components = components;
    }

    /**
     * Creates a new Categories object with the given name and list of components.
     *
     * @param categorieName the name of the new category
     * @param components    the list of components that belong to the new category
     */
    public Categories(String categorieName, List<Components> components) {
        this.categorieName = categorieName;
        this.components = components;
    }

    @Override
    public String toString() {
        return
                String.format(
                        "Category: \n" +
                                "\t\tCategorieName = %s\n\t" +
                                "\tComponents:  %s\n"
                        ,
                        this.categorieName,
                        this.components);


    }


}
