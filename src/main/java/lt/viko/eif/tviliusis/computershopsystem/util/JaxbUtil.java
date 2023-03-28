package lt.viko.eif.tviliusis.computershopsystem.util;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import lt.viko.eif.tviliusis.computershopsystem.model.Account;

import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * The  JaxbUtil class provides utility methods for performing JAXB operations on Account objects.
 * The convertToXML method can be used to convert an  Account object to its XML representation
 * using the JAXB API.
 */
public class JaxbUtil {
    /**
     * Converts an Account object to its XML representation using the JAXB API.
     * <p>
     * This method marshals the given Account object to XML format and prints the resulting XML
     * to standard output.
     *
     * @param 'account' the Account object to convert to XML
     * @throws RuntimeException if an error occurs while marshalling the Account object to XML
     */

    public static void convertToXML(Account student) {
        try {
            JAXBContext context = JAXBContext.newInstance(Account.class);

            Marshaller marshaller = context.createMarshaller();

            marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
            marshaller.marshal(student, System.out);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

}
