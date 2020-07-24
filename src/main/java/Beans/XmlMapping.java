package Beans;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.PrintStream;


public class XmlMapping {
    public static void main(String[] args) throws JAXBException {
        File file = new File("test.xml");
        Page page = new Page("see",33,22,true);
        JAXBContext context = JAXBContext.newInstance(Page.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
        PrintStream out = System.out;
        marshaller.marshal(page,file);

    }
}
