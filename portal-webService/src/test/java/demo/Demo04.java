package demo;

import com.alibaba.fastjson.JSON;
import org.apache.axis.client.Call;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.encoding.XMLType;
import java.io.StringWriter;
import java.net.URL;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/19/019.
 */
public class Demo04 {
    public static void main(String[] args){
        // 创建XML文档树
        Document document = DocumentHelper.createDocument();

        Element business = document.addElement("business");
        Element fkApplyGUID = business.addElement("FkApplyGUID");
        fkApplyGUID.addText("BD9C1349-044F-438C-B055-008BF16E14C6");
        Element contractNo = business.addElement("ContractNo");
        contractNo.addText("ZF-JHAE03-2016062");//合同编号
        Element jbDate = business.addElement("JbDate");
        jbDate.addText("2018-03-09 09:09:09");//付款日期
        Element items = business.addElement("Items");
        Element payItemEntity = items.addElement("PayItemEntity");
        Element itemName = payItemEntity.addElement("ItemName");
        itemName.addText("直接调用方式的款项名称");
//        Element itemType = payItemEntity.addElement("ItemType");
//        itemType.addText("结算方式");
        Element realPayMoney = payItemEntity.addElement("RealPayMoney");
        realPayMoney.addText("1111");//实付金额
        Element element = business.addElement("Invoices");


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
            String s = stringWriter.toString();
            System.err.println(s);


//            org.apache.axis.client.Service service=new org.apache.axis.client.Service();
//            Call call = (Call) service.createCall();
//            call.setTargetEndpointAddress(new URL("http://60.29.46.254:8060/Cbgl/WebService/HTFKService.asmx?wsdl"));
//            call.setOperationName(new QName("http://tempuri.org/","payMoney"));
////            call.addParameter("xmlstr", XMLType.XSD_STRING, ParameterMode.IN);
//
//            call.addParameter("xmlstr",XMLType.XSD_STRING, ParameterMode.IN);
//
//            call.setUseSOAPAction(true);
//
//            call.setReturnType(XMLType.XSD_STRING);
//            call.setSOAPActionURI("http://tempuri.org/PayMoney");
//            String result=(String)call.invoke(new Object[]{s});
//            System.err.println(result);
//            Map<String, Object> map = (Map<String, Object>) JSON.parse(result);
//            System.err.println(map.get("errorCode"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
