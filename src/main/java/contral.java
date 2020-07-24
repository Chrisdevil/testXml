import lombok.extern.slf4j.Slf4j;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.util.Iterator;
import java.util.List;

@Slf4j
public class contral {
    public static void main(String[] args) throws DocumentException, IOException {

        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("src/main/resources/properties.xml"));
        Element root = document.getRootElement();
        //log.info("root name{} root value{}",root.getName(),root.getStringValue());
        Iterator<Element>iterator = root.elementIterator();
        while (iterator.hasNext()){
            Element element = iterator.next();
            System.out.println(element.getName());
            System.out.println(element.getStringValue());
            Iterator<Element>iterator1 = element.elementIterator();
            while (iterator.hasNext()){
                Element element1 =iterator1.next();
                System.out.println(element1.getName());
                System.out.println(element1.getStringValue());
            }

        }
    }
}
