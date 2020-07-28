package Beans;


import lombok.extern.slf4j.Slf4j;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import javax.xml.bind.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class XmlMapping {
    private HashMap<String,Element>nameElement;
    public XmlMapping() throws JDOMException, IOException {
        SAXBuilder saxBuilder = new SAXBuilder();
        Element root = saxBuilder.build("src/main/resources/properties.xml").getDocument().getRootElement();
        List<Element>list = root.getChildren();
        HashMap<String,Element>nameElement = new HashMap<>();
        for(Element e :list){
            nameElement.put(e.getAttribute("name").getValue(),e);
        }
        for(Map.Entry<String,Element> entry:nameElement.entrySet()){
            System.out.println(entry.getKey());
            List<Element>list1 = entry.getValue().getChildren();
            System.out.println(entry.getValue().getChildren().size());
            for(Element element : list1){
                System.out.println(element.getValue());
            }
        }
        nameElement.forEach((k,v)-> System.out.println("k:"+k+" v:"+v));
    }
   
    public void sendMessage(String pageName,Element element){

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<form>");

    }
    public String testSendMessage(){
        return "<form >userId:<input type=text><input type=submit></form>";
    }
    public static void main(String[] args) throws JAXBException, JDOMException, IOException {
        new XmlMapping();
        log.info("asdasd");
        
    }
}
