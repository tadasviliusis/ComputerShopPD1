package lt.viko.eif.tviliusis.computershopsystem.util;


import lt.viko.eif.tviliusis.computershopsystem.model.Account;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * This package contains utility classes for the Computer Shop System application.
 * These classes provide functionality for transforming objects to XML format and vice versa.
 *
 * Transformation provides methods for transforming
 * an Account object to XML format and vice versa.
 *
 * Validation provides methods for validating an XML file
 * against a DTD file and an XSD schema.
 *
 * JaxbUtil provides a method for converting an Account object
 * to XML format and printing it to the console.
 */
public class Transformation {


    public  static void transformToXML(Account account1) throws JAXBException, IOException, jakarta.xml.bind.JAXBException {

        JAXBContext context = JAXBContext.newInstance(Account.class);

        System.out.println(account1);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
        OutputStream os = new FileOutputStream("output.xml");
        marshaller.marshal(account1, System.out);
        marshaller.marshal(account1, os);
    }


    public static Account transformToPOJO() throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(Account.class);

        Unmarshaller unmarshaller = context.createUnmarshaller();

        Path path = Path.of("output.xml");

        String xmlContent = Files.readString(path);
        System.out.println(xmlContent);

        StringReader reader = new StringReader(xmlContent);
        Account account = (Account) unmarshaller.unmarshal(reader);
        return account;
    }
}
