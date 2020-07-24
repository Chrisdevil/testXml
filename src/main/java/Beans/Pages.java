package Beans;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Pages")
public class Pages {
    public Pages(List<Page> list) {
        this.list = list;
    }

    private List<Page>list;
    @XmlElement(name = "Page")

    public List<Page> getList() {
        return list;
    }

    public void setList(List<Page> list) {
        this.list = list;
    }
}
