package Beans;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.PrintStream;


public class XmlMapping {
    public static void main(String[] args) throws JAXBException {
        File file = new File("src/main/resources/properties.xml");

        JAXBContext context = JAXBContext.newInstance(Page.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);

        marshaller.marshal(file,System.out);

    }
}
