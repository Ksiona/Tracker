package ru.shmoylova.tracker.logic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author Ksiona
 */
public class RemoteFile {

    private File src;
    private URL url;
    byte[] buf = new byte[4096];
    BufferedInputStream bis = null;
    BufferedOutputStream bos = null;

    public RemoteFile() {
    }

    public boolean upload(URL url, File src) {
        if (url == null || src == null) {
            return false;
        }
        try {
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            bos = new BufferedOutputStream(conn.getOutputStream());
            bis = new BufferedInputStream(new FileInputStream(src));
            int tmp = bis.read();

            while (tmp != -1) {
                bos.write(tmp);
                tmp = bis.read();
            }
            bos.flush();
            bos.close();
            bis.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally{
            
        }
    }

}
