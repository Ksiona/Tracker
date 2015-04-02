package ru.shmoylova.tracker.logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.TransformerException;

/**
 *
 * @author Ksiona
 */
public class XsltProcessor {

    private Reader rXml;
    private Reader rXls;
    private String xhtmlPath;
    private OutputStream htmlResult;
    private XslTransformer tr;

    public XsltProcessor(File xml, URL xlsUrl, String xhtmlPath) throws IOException {
        rXml = new InputStreamReader(new FileInputStream(xml));
        rXls = new InputStreamReader((InputStream) xlsUrl.getContent());
        this.xhtmlPath = xhtmlPath;
        tr = new XslTransformer();
    }

    public void transform() throws IOException {
        try {
            File res = new File(xhtmlPath);
            OutputStreamWriter osw = new FileWriter(res);
            tr.process(rXml, rXls, osw);
        } catch (TransformerException ex) {
            Logger.getLogger(XsltProcessor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
//            rXml.close();
//            rXls.close();
        }
    }

}
