/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;
import ru.shmoylova.tracker.logic.XmlResult;

/**
 *
 * @author Ksiona
 */
public class ValidatingTest {

    String TEST_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
            + "<ns2:xmlResult xmlns:ns2=\"http:\\\\localhost\\8080\\Tracker-war\">"
            + "<employee>\n"
            + "    <empId>1</empId>\n"
            + "    <lastName>last</lastName>\n"
            + "    <firstName>first</firstName>\n"
            + "    <surName>sur</surName>\n"
            + "    <jobTitle>job</jobTitle>\n"
            + "    <login>login</login>\n"
            + "    <pass>pass</pass>\n"
            + "    <department>\n"
            + "        <deptId>1</deptId>\n"
            + "        <deptName>IT</deptName>\n"
            + "    </department>\n"
            + "    <role>\n"
            + "        <roleId>1</roleId>\n"
            + "        <roleName>mgr1</roleName>\n"
            + "    </role>\n"
            + "</employee>\n"
            + "</ns2:xmlResult>";

    public ValidatingTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void test() throws SAXException, JAXBException {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File("C:/Project/TrackerXMLScheme.xsd"));

        JAXBContext jc = JAXBContext.newInstance(XmlResult.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
       // unmarshaller.setSchema(schema);

        unmarshaller.setEventHandler(new ValidationEventHandler() {
            @Override
            public boolean handleEvent(ValidationEvent event) {
                return false;
            }
        });

        InputStream is = new ByteArrayInputStream(TEST_XML.getBytes());
        XmlResult result = (XmlResult) unmarshaller.unmarshal(is);

    }
}
