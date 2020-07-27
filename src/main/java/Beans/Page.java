package Beans;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Page {
    private String text;
    private int length;
    private int width;
    private boolean view;
    private String name;
    @XmlAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Page(String text, int length, int width, boolean view, String name) {
        this.text = text;
        this.length = length;
        this.width = width;
        this.view = view;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Page{" +
                "text='" + text + '\'' +
                ", length=" + length +
                ", width=" + width +
                ", view=" + view +
                ", name='" + name + '\'' +
                '}';
    }

    public Page() {

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
