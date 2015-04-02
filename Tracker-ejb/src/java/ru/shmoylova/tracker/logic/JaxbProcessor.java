package ru.shmoylova.tracker.logic;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author Ksiona
 */
public class JaxbProcessor {

    JAXBContext jc;
    Marshaller jaxbMarshaller;
    Unmarshaller unmarshaller;

    public JaxbProcessor() throws JAXBException {
        this.jc = JAXBContext.newInstance(XmlResult.class);
        this.jaxbMarshaller = jc.createMarshaller();
        this.unmarshaller = jc.createUnmarshaller();
    }

    public void executeMarshal(XmlResult root, OutputStream xmlStream) throws JAXBException, IOException {
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        //TODO: store ref in DB
        jaxbMarshaller.marshal(root, xmlStream);
    }

    public void executeUnmarshalValidate(InputStream xml, String SCHEME_LOC) throws MalformedURLException, JAXBException, SAXException {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
//        URL xsd = new URL(SCHEME_LOC);
//        Schema schema = schemaFactory.newSchema(xsd);
//        unmarshaller.setSchema(schema);
        unmarshaller.setEventHandler(new ValidationEventHandler() {
            @Override
            public boolean handleEvent(ValidationEvent event) {
                return false;
            }
        });
        XmlResult result = (XmlResult) unmarshaller.unmarshal(xml);
    }

}
