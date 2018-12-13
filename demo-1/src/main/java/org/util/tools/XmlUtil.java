package org.util.tools;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;


/**
 * Xml 工具
 * @author renjx
 *
 */
public class XmlUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String xmlContent="<xml><ToUserName><![CDATA[gh_3e22f77007d6]]></ToUserName><FromUserName><![CDATA[oEt0ft1ILjMsyPnzcTdTUFuGp8uk]]></FromUserName><CreateTime>1392799697</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[呵呵]]></Content><MsgId>5982029148695795967</MsgId></xml>";
		System.out.println(xmlContent);

	}
	
	/**
	 * xml 转换为 Bean
	 * @param cls bean的类, 属性必须为小写字母
	 * (<business_trans version="2.0" > 中 version 对应 business_trans_version字段)
	 * <result><id>0000</id><comment>成功</comment></result> 分别对应 result_id result_comment字段
	 * @param xmlContent
	 * @return
	 */
	public static Object xml2Bean(Class cls,String xmlContent){
	   Object obj = null;
       try { 
            SAXBuilder parser = new SAXBuilder();
            HashMap map = new HashMap();
            xmlContent = xmlContent.trim();
            //xmlContent = xmlContent.toLowerCase();
            InputStream in = new ByteArrayInputStream(xmlContent.getBytes("UTF-8"));
            Document document = parser.build(in);
            process(null,document.getRootElement(),map);
            obj = cls.newInstance();
            BeanUtils.populate(obj, map);
        }catch (JDOMException e) { 
            e.printStackTrace();
        }catch (IOException e) { 
            e.printStackTrace(); 
        }catch (Exception e) { 
            e.printStackTrace(); 
        }
        return obj;
	}
	
	
	
	
	
	
	private static void process(Element parentElement,Element element,HashMap map) { 
        List content = element.getContent(); 
        Iterator iterator = content.iterator(); 
        String qualifiedName = element.getQualifiedName();
        //System.out.print(qualifiedName + ":" + element.getText());
        //System.out.println(element.getText());
        map.put(qualifiedName, element.getText());
        map.put(firstCharToLowerCase(qualifiedName), element.getText());
        if(qualifiedName.indexOf("_")>0){
        	String tmpName = replaceName(qualifiedName,"_");
        	map.put(tmpName, element.getText());
        	map.put(firstCharToLowerCase(tmpName), element.getText());
        }
        if(qualifiedName.indexOf("-")>0){
        	String tmpName = replaceName(qualifiedName,"-");
        	map.put(tmpName, element.getText());
        	map.put(firstCharToLowerCase(tmpName), element.getText());
        }        
        if(parentElement!=null && !parentElement.isRootElement()){
        	qualifiedName=parentElement.getQualifiedName()+"_"+qualifiedName;
	        //System.out.println(qualifiedName + ":" + element.getTextNormalize());
	        qualifiedName = qualifiedName.toLowerCase();
	        map.put(qualifiedName, element.getText());
	        if(qualifiedName.indexOf("_")>0){
	        	//map.put(replaceName(qualifiedName), element.getText());
	        	String tmpName = replaceName(qualifiedName,"_");
	        	map.put(tmpName, element.getText());
	        	map.put(firstCharToLowerCase(tmpName), element.getText());
	        }
	        if(qualifiedName.indexOf("-")>0){
	        	//map.put(replaceName(qualifiedName), element.getText());
	        	String tmpName = replaceName(qualifiedName,"-");
	        	map.put(tmpName, element.getText());
	        	map.put(firstCharToLowerCase(tmpName), element.getText());
	        }	        
        }
        List attributes = element.getAttributes(); 
        if (!attributes.isEmpty()) { 
            Iterator iterator1 = attributes.iterator(); 
            while (iterator1.hasNext()) { 
                Attribute attribute = (Attribute) iterator1.next(); 
                String name = attribute.getName(); 
                String value = attribute.getValue(); 
                name = qualifiedName +"_"+name;
                //System.out.println(name + ':' + value+" aaaa");
                name = name.toLowerCase();
                map.put(name, value);
            } 
        } 
        while (iterator.hasNext()) { 
            Object o = iterator.next(); 
            if (o instanceof Element) { 
                Element child = (Element)o; 
                process(element,child,map); 
            } 
        } 
    }
	
	public static String replaceName(String name,String re){
		int iIndex = -1;
		while((iIndex=name.indexOf(re))>=0 && iIndex!=(name.length()-1)){
			name = name.substring(0, iIndex) +name.substring(iIndex+1,iIndex+2).toUpperCase()+ name.substring(iIndex+2);
		}
		name = name.replaceAll(re, "");
		return name;
	}
	public static String firstCharToLowerCase(String name){
		String fc = name.substring(0, 1);
		String newName = name;
		if(fc.equals(fc.toUpperCase())){
			newName = fc.toLowerCase() + name.substring(1); 
		}
		return newName;
	}

}
