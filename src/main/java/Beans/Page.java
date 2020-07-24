package Beans;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Page {
    private String text;
    private int length;
    private int width;
    private boolean view;

    public Page(){

    }
    public Page(String text, int length, int width, boolean view) {
        this.text = text;
        this.length = length;
        this.width = width;
        this.view = view;
    }

    @Override
    public String toString() {
        return "Page{" +
                "text='" + text + '\'' +
                ", length=" + length +
                ", width=" + width +
                ", view=" + view +
                '}';
    }

    @XmlElement(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @XmlElement(name = "length")
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @XmlElement(name = "width")
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @XmlElement(name = "view")
    public boolean isView() {
        return view;
    }

    public void setView(boolean view) {
        this.view = view;
    }
}
