package lt.viko.eif.tviliusis.computershopsystem.util;

import org.xml.sax.*;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The Validation class provides methods for validating XML files against a DTD or an XSD schema.
 */

public class Validation {

    /**
     * Validates an XML file against a DTD using the SAX parser.
     *
     * @param path The path to the XML file to be validated.
     * @return True if the XML is valid, false otherwise.
     * @throws ParserConfigurationException If there is a configuration error.
     * @throws IOException                  If an I/O error occurs.
     */
    public static boolean validateWithDTDUsingSAX(String path)
            throws ParserConfigurationException, IOException {
        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(true);
            factory.setNamespaceAware(true);

            //String xml = new String(Files.readAllBytes(Paths.get(path)));

            SAXParser parser = factory.newSAXParser();

            XMLReader reader = parser.getXMLReader();
            reader.setErrorHandler(
                    new ErrorHandler() {
                        public void warning(SAXParseException e) throws SAXException {
                            System.out.println("WARNING : " + e.getMessage()); // do nothing
                        }

                        public void error(SAXParseException e) throws SAXException {
                            System.out.println("ERROR : " + e.getMessage());
                            throw e;
                        }

                        public void fatalError(SAXParseException e) throws SAXException {
                            System.out.println("FATAL : " + e.getMessage());
                            throw e;
                        }
                    }
            );
            reader.parse(new InputSource(path));
            return true;
        } catch (ParserConfigurationException pce) {
            throw pce;
        } catch (IOException io) {
            throw io;
        } catch (SAXException se) {
            return false;
        }
    }

    /**
     * Validates an XML file against an XSD schema.
     *
     * @param XSD The path to the XSD schema file.
     * @param XML The path to the XML file to be validated.
     * @return True if the XML is valid, false otherwise.
     */
    public static boolean validateXMLSchema(String XSD, String XML) {
        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File(XSD));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(XML)));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
        return true;
    }

}
