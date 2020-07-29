package Beans;


import lombok.extern.slf4j.Slf4j;
import org.jdom2.*;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import javax.xml.bind.*;
import java.io.IOException;
import java.util.*;

@Slf4j
public class XmlMapping {
    private HashMap<String, Element> nameElement;

    public XmlMapping() throws JDOMException, IOException {
        SAXBuilder saxBuilder = new SAXBuilder();
        Element root = saxBuilder.build("src/main/resources/properties.xml").getDocument().getRootElement();
        //System.out.println(root.getChild("page").getChild("form").getChild("userId").getValue());
        List<Element> list = root.getChildren();
        nameElement = new HashMap<>();
        for (Element e : list) {
            nameElement.put(e.getAttribute("name").getValue(), e);
        }
        for (Map.Entry<String, Element> entry : nameElement.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(createElementString(entry.getValue()));
        }
    }

    public String createElementString(Element element) {

        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = element.getContent().iterator();
        while (true) {
            Content child;
            do {
                if (!it.hasNext()) {
                    return stringBuilder.toString();
                }
                child = (Content) it.next();
            } while (!(child instanceof Element) && !(child instanceof Text));
            if (child instanceof Element) {
                //添加标签头
                stringBuilder.append("<" + ((Element) child).getName());
                //添加属性
                for (Attribute attribute : ((Element) child).getAttributes()) {
                    stringBuilder.append(" " + attribute.getName() + "=");
                    stringBuilder.append("\"" + attribute.getValue() + "\"");

                }
                stringBuilder.append(">");
                //添加标签头完毕 进入递归
                stringBuilder.append(createElementString((Element) child));
                //如果不是单标签的情况 添加标签尾
                if (((Element) child).getName() != "input") {
                    stringBuilder.append("</" + ((Element) child).getName() + " >");
                }
            }
            if (child instanceof Text) {
                stringBuilder.append(createTextString((Text) child));
            }
        }
    }

    public String createTextString(Text text) {
        return text.getValue();
    }

    public Element getElement(String str) {
        return nameElement.get(str);
    }

    public static void main(String[] args) throws JAXBException, JDOMException, IOException {
        System.out.println("\"");
        new XmlMapping();
        log.info("asdasd");
        XmlMapping xmlMapping = new XmlMapping();
        Element element = xmlMapping.getElement("queryLogin");
        System.out.println(xmlMapping.createElementString(element));
    }
}
