package com.epam.task2.builder;

import com.epam.task2.entity.PlantEntity;
import com.epam.task2.exception.PlantException;
import com.epam.task2.tag.Tag;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class PlantStaxBuilder extends AbstractPlantBuilder {
    private Set<PlantEntity> plantEntitySet;
    private XMLInputFactory inputFactory;

    public PlantStaxBuilder() {
        inputFactory = XMLInputFactory.newInstance();
        plantEntitySet = new HashSet<PlantEntity>();
    }

    public Set<PlantEntity> getPlants() {
        return plantEntitySet;
    }

    @Override
    public void buildPlant(String filePath) throws PlantException {

    }

    public void buildSetPlants(String filename) throws PlantException {
        XMLStreamReader reader;
        String name;
        try (FileInputStream inputStream = new FileInputStream(new File(filename))) {
            reader = inputFactory.createXMLStreamReader(inputStream);
            // StAX parsing
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals(Tag.NAME.toLowerCase())) {
                       // PlantEntity plantEntity = buildStudent(reader);
                        //plantEntitySet.add(plantEntity);
                    }
                }
            }
        } catch (XMLStreamException | FileNotFoundException e) {
            throw new PlantException("XML stream Exception", e);
        } catch (IOException e) {
            throw new PlantException("Stax parsing exception", e);
        }
    }

}
