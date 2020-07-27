package Beans;

import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import javax.xml.bind.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class XmlMapping {
    public static void main(String[] args) throws JAXBException, JDOMException, IOException {
        SAXBuilder saxBuilder = new SAXBuilder();
        Element root = saxBuilder.build("src/main/resources/properties.xml").getDocument().getRootElement();
        System.out.println(root.getName());
        List<Element>list = root.getChildren();
        for(Element e :list){
            System.out.println(e.getAttribute("name").getValue());
        }
        HashMap<String,Element>hashMap = new HashMap<>();
        for(Element e :list){
            hashMap.put(e.getAttribute("name").getValue(),e);
        }
        for(Map.Entry<String,Element> entry:hashMap.entrySet()){
            System.out.println(entry.getKey());
            List<Element>list1 = entry.getValue().getChildren();
            for(Element element : list1){
                System.out.println(element.getValue());
            }
        }
    }
}
