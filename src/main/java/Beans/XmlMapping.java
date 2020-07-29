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
    private HashMap<String, Element> pageElement;
    private HashMap<String, Element> typeElement;

    public XmlMapping() throws JDOMException, IOException {
        SAXBuilder saxBuilder = new SAXBuilder();
        Element root = saxBuilder.build("src/main/resources/properties.xml").getDocument().getRootElement();

        List<Element> typeList = root.getChildren();
        pageElement = new HashMap<>();
        typeElement = new HashMap<>();
        for (Element typeE : typeList) {
            typeElement.put(typeE.getAttribute("name").getValue(), typeE);
            List<Element> pageList = typeE.getChildren();
            for (Element pageE : pageList) {
                pageElement.put(pageE.getAttribute("name").getValue(), pageE);
            }
        }
        for (Map.Entry<String, Element> entry : pageElement.entrySet()) {
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
                //添加标签尾
                stringBuilder.append("</" + ((Element) child).getName() + " >");

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
        return pageElement.get(str);
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
