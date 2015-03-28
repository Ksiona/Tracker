package ru.shmoylova.tracker.logic;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.TransformerException;

/**
 *
 * @author Ksiona
 */
public class XsltProcessor {

    private URL xmlUrl;
    private URL xlsUrl;
    private URLConnection conn;
    private XslTransformer tr;

    public XsltProcessor(URL xmlUrl, URL xlsUrl, URL xhtmlUrl) throws IOException {
        this.xmlUrl = xmlUrl;
        this.xlsUrl = xlsUrl;
        this.conn = xhtmlUrl.openConnection();
        conn.setDoOutput(true);
        tr = new XslTransformer();
    }

    public void transform() throws IOException {
        try (Reader rXls = new InputStreamReader((InputStream) xlsUrl.getContent());
                Reader rXml = new InputStreamReader((InputStream) xmlUrl.getContent());
                Writer htmlResult = new OutputStreamWriter(conn.getOutputStream());) {
            tr.process(rXml, rXls, htmlResult);
        } catch (TransformerException ex) {
            Logger.getLogger(EmployeeSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
