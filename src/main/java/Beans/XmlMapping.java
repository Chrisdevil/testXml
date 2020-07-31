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

    //页面名是键 对应的页面的element是值
    private final HashMap<String, Element> pageElement;
    //页面类型是键 对应的类型element是值
    private final HashMap<String, Element> typeElement;

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
            //System.out.println(createElementString(entry.getValue()));
        }
    }

    //创建页面的字符串
    public String createElementString(Element element) {

        StringBuilder stringBuilder = new StringBuilder();

//        if(element==null){
//            return "";
//        }
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
                if (((Element) child).getName().equals("form")) {
                    stringBuilder.append("<div class=\"card-body card-block\">");
                } else if ((((Element) child).getName().equals("input") && ((Element) child).getAttribute("type").getValue().equals("text")) || ((Element) child).getName().equals("select")) {
                    stringBuilder.append("<div class=\"row form-group\"><div class=\"col col-md-3\"><label class=\" form-control-label\">" +
                            ((Element) child).getAttribute("name").getValue() +
                            "</label></div><div class=\"col-12 col-md-9\">");
                }
                //添加标签头
                stringBuilder.append("<" + ((Element) child).getName());
                //添加属性
                for (Attribute attribute : ((Element) child).getAttributes()) {
                    stringBuilder.append(" " + attribute.getName() + "=");
                    stringBuilder.append("\"" + attribute.getValue() + "\"");

                }
                ///////根据不同的元素添加特别的属性


                //表单特殊属性
                if (((Element) child).getName().equals("form")) {
                    stringBuilder.append("class=\"form-horizontal\">");
                    //输入框的特殊属性
                } else if (((Element) child).getName().equals("input") || ((Element) child).getName().equals("select")) {
                    stringBuilder.append("class=\"form-control\">");
                } else if (((Element) child).getName().equals("option")) {
                    stringBuilder.append(">");
                    stringBuilder.append(child.getValue());
                }

                //添加标签头完毕 进入递归
                stringBuilder.append(createElementString((Element) child));
                //添加标签尾
                if (!((Element) child).getName().equals("input")) {
                    stringBuilder.append("</" + ((Element) child).getName() + " >");
                }
                if (((Element) child).getName().equals("form")) {
                    stringBuilder.append("</div>");
                } else if (!(((Element) child).getName().equals("input") && ((Element) child).getAttribute("type").getValue().equals("submit")) && !((Element) child).getName().equals("option")) {
                    stringBuilder.append("</div>");
                    stringBuilder.append("</div>");
                }

            }
            if (child instanceof Text) {
                //stringBuilder.append(createTextString((Text) child));
            }
        }
    }

    //生成text类型对象的字符串
    public String createTextString(Text text) {
        return text.getValue();
    }

    //获取页面的element
    public Element getElement(String str) {
        return pageElement.get(str);
    }

    public String createAsideString() {
        System.out.println("///////////////////////////////////////////////////////");
        StringBuilder stringBuilder = new StringBuilder();
        //这里的外层是一个div，从这里是ul开始
        stringBuilder.append("<ul class=\"nav navbar-nav\">");

        for (Map.Entry<String, Element> entry : typeElement.entrySet()) {
            //添加种类名称 每一个种类是li
            stringBuilder.append("<li class=\"menu-item-has-children dropdown\">");
            stringBuilder.append("<a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" aria-haspopup=\"true\"\n" +
                    "aria-expanded=\"false\" >" + entry.getKey() + "</a>");
            //每一个种类还是一个ul 里面的每个页面是一个li
            stringBuilder.append(" <ul class=\"sub-menu children dropdown-menu\">");
            System.out.println(entry.getKey());
            for (Element element : entry.getValue().getChildren()) {
                stringBuilder.append("<li><a href=\"" + element.getAttribute("url").getValue() + "\">" + element.getAttribute("name").getValue() + "</a></li>");
                System.out.println(element.getAttribute("name").getValue());
            }
            stringBuilder.append("</ul>");
            stringBuilder.append("</li>");
        }
        stringBuilder.append("</ul>");
        return stringBuilder.toString();
    }

    public static void main(String[] args) throws JAXBException, JDOMException, IOException {
        System.out.println("\"");
        new XmlMapping();
        log.info("asdasd");
        XmlMapping xmlMapping = new XmlMapping();
        //Element element = xmlMapping.getElement("queryLogin");
        //System.out.println(xmlMapping.createElementString(element));
        System.out.println("//////////////////////////////////////////////////////////////////////");
        System.out.println(xmlMapping.createElementString(xmlMapping.getElement("queryTrophies")));
        System.out.println(xmlMapping.createAsideString());
    }
}
