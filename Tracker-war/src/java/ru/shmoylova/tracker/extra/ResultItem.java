package ru.shmoylova.tracker.extra;

import java.io.Serializable;

/**
 *
 * @author Ksiona
 */
public class ResultItem implements Serializable {

    private String page;
    private int size;

    public ResultItem(int size, String page) {
        this.size = size;
        this.page = page;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
