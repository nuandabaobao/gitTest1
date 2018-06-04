package demo;

import com.alibaba.fastjson.JSON;
import com.tbjj.portal.core.mywebservice.HTFKService;
import com.tbjj.portal.core.mywebservice.HTFKServiceSoap;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.StringWriter;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/21/021.
 */
public class Demo05 {

    public static void main(String[] args) throws Exception{
        // 创建XML文档树
        Document document = DocumentHelper.createDocument();
        Element business = document.addElement("business");
        Element fkApplyGUID = business.addElement("FkApplyGUID");
        fkApplyGUID.addText("d5256916-410e-4a0c-8dd2-3748a0e761f1");
        Element contractNo = business.addElement("ContractNo");
        contractNo.addText("ZF-DLDD-2017011");
        Element jbDate = business.addElement("JbDate");
        jbDate.addText("2018-05-29 09:43:00");
        Element items = business.addElement("Items");
        Element payItemEntity = items.addElement("PayItemEntity");
        Element itemName = payItemEntity.addElement("ItemName");
        itemName.addText("进度款");
        Element realPayMoney = payItemEntity.addElement("RealPayMoney");
        realPayMoney.addText("333.0");
        Element rate = payItemEntity.addElement("Rate");
        rate.addText("1");
        Element element = business.addElement("Invoices");
        // 设置XML文档格式
        OutputFormat outputFormat = OutputFormat.createPrettyPrint();
        // 设置XML编码方式,即是用指定的编码方式保存XML文档到字符串(String),这里也可以指定为GBK或是ISO8859-1
        outputFormat.setEncoding("UTF-8");
        outputFormat.setIndent(true); //设置是否缩进
        outputFormat.setNewlines(true); //设置是否换行
        // stringWriter字符串是用来保存XML文档的
        StringWriter stringWriter = new StringWriter();
        // xmlWriter是用来把XML文档写入字符串的(工具)
        XMLWriter xmlWriter = new XMLWriter(stringWriter, outputFormat);
        // 把创建好的XML文档写入字符串
        try {
            xmlWriter.write(document);
            xmlWriter.close();
        }catch (Exception e){
        }
        System.err.println(stringWriter.toString());
        HTFKService htfkService=new HTFKService();
        HTFKServiceSoap htfkServiceSoap = htfkService.getHTFKServiceSoap();
        String result = htfkServiceSoap.payMoney(stringWriter.toString());
        Map<String,Object> mapResult =(Map<String, Object>) JSON.parse(result);
        System.err.println(mapResult.get("errorCode"));
    }
}
