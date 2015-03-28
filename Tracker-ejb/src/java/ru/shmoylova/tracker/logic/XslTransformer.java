package ru.shmoylova.tracker.logic;

import java.io.File;
import java.io.Reader;
import java.io.Writer;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XslTransformer {

    private TransformerFactory factory;
// Use system defaults for transformer.

    public XslTransformer() {
        factory = TransformerFactory.newInstance();
    }

    /**
     * For transforming an XML documents as a String StringReader residing in
     * memory, not on disk. The output document could easily be handled as a
     * String (StringWriter) or as a JSPWriter in a JavaServer page.
     * @param xmlFile
     * @param xslFile
     * @param output
     * @throws javax.xml.transform.TransformerException
     */
    public void process(Reader xmlFile, Reader xslFile, Writer output) throws TransformerException {

        process(new StreamSource(xmlFile), new StreamSource(xslFile), new StreamResult(output));
    }

    /**
     * For transforming an XML and XSL document as Files, placing the result in
     * a Writer.
     * @param xmlFile
     * @param xslFile
     * @param output
     * @throws javax.xml.transform.TransformerException
     */
    public void process(File xmlFile, File xslFile, Writer output) throws TransformerException {
        process(new StreamSource(xmlFile), new StreamSource(xslFile), new StreamResult(output));
    }

    /**
     * Transform an XML File based on an XSL File, placing the resulting
     * transformed do cument in an OutputStream. * Convenient for handling the
     * result as a FileOutputStream or ByteArrayOutputStream.
     * @param xml
     * @param xsl
     * @param result
     * @throws javax.xml.transform.TransformerException
     */
    public void process(Source xml, Source xsl, Result result) throws TransformerException {
        try {
            Templates template = factory.newTemplates(xsl);
            Transformer transformer = template.newTransformer();
            transformer.transform(xml, result);
        } catch (TransformerConfigurationException tce) {
            throw new TransformerException(tce.getMessageAndLocation());
        } catch (TransformerException te) {
            throw new TransformerException(te.getMessageAndLocation());
        }
    }
}
