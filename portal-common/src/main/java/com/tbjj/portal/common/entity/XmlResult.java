package com.tbjj.portal.common.entity;

import lombok.Data;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by Administrator on 2018/2/27/027.
 */
@Data
public class XmlResult {

    public String responseXml(String errorCode,String msg){
        Document document= DocumentHelper.createDocument();
        Element business=document.addElement("business");
        Element errorCodeElement=business.addElement("errorCode");
        errorCodeElement.addText(errorCode);
        Element msgElement = business.addElement("msg");
        msgElement.addText(msg);

        // 设置XML文档格式
        OutputFormat outputFormat = OutputFormat.createPrettyPrint();
        // 设置XML编码方式,即是用指定的编码方式保存XML文档到字符串(String),这里也可以指定为GBK或是ISO8859-1
        outputFormat.setEncoding("UTF-8");
        outputFormat.setIndent(true); //设置是否缩进
        outputFormat.setNewlines(true); //设置是否换行
        // stringWriter字符串是用来保存XML文档的
        StringWriter stringWriter = new StringWriter();
        try {
            // xmlWriter是用来把XML文档写入字符串的(工具)
            XMLWriter xmlWriter = new XMLWriter(stringWriter, outputFormat);
            // 把创建好的XML文档写入字符串
            xmlWriter.write(document);
            xmlWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringWriter.toString();
    }

}
