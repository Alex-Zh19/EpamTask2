package com.epam.alex.task2.xmlValidator;

import com.epam.alex.task2.plantHandler.PlantErrorHandler;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlValidator {
    public static boolean validateXml(String xmlFilepath, String xsdSchemaFilepath) {
        String language = XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(xsdSchemaFilepath);
        try {
            // schema creation
            Schema schema = factory.newSchema(schemaLocation);
            // creating a schema-based validator
            Validator validator = schema.newValidator();
            Source source = new StreamSource(xmlFilepath);
            // document check
            validator.setErrorHandler(new PlantErrorHandler());
            validator.validate(source);
            return true;
        } catch (SAXException | IOException e) {
            System.err.println(xmlFilepath + " is not correct or valid");
            return false;
        }
    }
}
