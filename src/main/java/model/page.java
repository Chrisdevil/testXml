package model;

public class page {

    private String text;
    private int length;
    private int width;
    private boolean view;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean isView() {
        return view;
    }

    public void setView(boolean view) {
        this.view = view;
    }

    @Override
    public String toString() {
        return "page{" +
                "text='" + text + '\'' +
                ", length=" + length +
                ", width=" + width +
                ", view=" + view +
                '}';
    }
}
