package com.epam.task2.validator;

import com.epam.task2.exception.PlantException;
import com.epam.task2.handler.PlantErrorHandler;
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
    public static boolean validateXml(String xmlFilepath, String xsdSchemaFilepath) throws PlantException {
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
            throw new PlantException("File is not valid :"+xmlFilepath+" ; schema :"+xsdSchemaFilepath,e);
        }
    }
}
