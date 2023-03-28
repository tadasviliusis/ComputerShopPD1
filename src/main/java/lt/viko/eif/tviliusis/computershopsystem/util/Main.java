package lt.viko.eif.tviliusis.computershopsystem.util;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import lt.viko.eif.tviliusis.computershopsystem.model.Account;
import lt.viko.eif.tviliusis.computershopsystem.model.Categories;
import lt.viko.eif.tviliusis.computershopsystem.model.ClientShipping;
import lt.viko.eif.tviliusis.computershopsystem.model.Components;


import javax.xml.parsers.ParserConfigurationException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * This package contains utility classes for the Computer Shop System application.
 * <p>
 * The classes included in this package provide functionality such as:
 * - Object to XML transformation using JAXB.
 * - XML validation with DTD and XML Schema.
 * - XML transformation using XSLT.
 * - Database connection and session management with Hibernate.
 * </p>
 * <p>
 * The main class is
 * used to demonstrate the functionality of the various utility classes included in this package.
 * </p>
 */
public class Main {
    public static void main(String[] args) throws JAXBException, IOException, ParserConfigurationException, javax.xml.bind.JAXBException {

        Components mb1 = new Components(1, "Asus ROG STRIX B550", 120.99);
        Components mb2 = new Components(2, "Gigabyte Elite AX V2", 169.99);
        Categories category = new Categories(1, "Motherboard", List.of(mb1, mb2));
        ClientShipping clientShipping = new ClientShipping(1, 869784526, "Verkiu g.45", "LT00762");
        Account account1 = new Account(1, "Tadas", "1515", clientShipping, List.of(category));

        System.out.println(account1);


        if (Validation.validateWithDTDUsingSAX(Path.of("new.xml").toString())) {
            System.out.println("DTD is Validaded");

        } else
            System.out.println("DTD is not Validaded");

        if (Validation.validateXMLSchema(Path.of("generated.xsd").toString(), Path.of("new.xml").toString())) {
            System.out.println("XSX is Validaded");
        } else
            System.out.println("XSD is not Validaded");

        Transformation.transformToXML(account1);

        Account account = Transformation.transformToPOJO();

        System.out.println(account);

    }

}