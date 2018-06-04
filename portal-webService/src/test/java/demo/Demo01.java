package demo;

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

/**
 * Created by Administrator on 2018/3/6/006.
 */
public class Demo01 {

    public static void main(String[] args){
        // 创建XML文档树
        Document document = DocumentHelper.createDocument();
        // 创建根节点items
        Element sscinterface = document.addElement("sscinterface");
        sscinterface.addAttribute("dataid","a80eda15-d025-e811-97a8-801844e12aaaa");//
        sscinterface.addAttribute("operatertype","ADD");
        sscinterface.addAttribute("userid","zhangchi");
        sscinterface.addAttribute("unitid","天津滨海开元房地产开发有限公司");
        sscinterface.addAttribute("billdefine","GCFKD");
        sscinterface.addAttribute("systemname","FKD");
        Element bill=sscinterface.addElement("bill");
        Element zhubiao = bill.addElement("zhubiao");
        //设置参数
        Element companyName = zhubiao.addElement("companyName");
        companyName.addText("天津滨海开元房地产开发有限公司");
        Element contractType = zhubiao.addElement("contractType");
        contractType.addText("190201");
        Element applyUserName = zhubiao.addElement("applyUserName");
        applyUserName.addText("zhangchi");
        Element applyDept = zhubiao.addElement("applyDept");
        applyDept.addText("预算控制部");
        Element clauseItemName = zhubiao.addElement("clauseItemName");
        clauseItemName.addText("进度款");
        Element itemName = zhubiao.addElement("itemName");
        itemName.addText("E03");
        Element contractCode = zhubiao.addElement("contractCode");
        contractCode.addText("ZF-JHAE03-2016062");
        Element contractName = zhubiao.addElement("contractName");
        contractName.addText("天保金海岸E03地块住宅项目沉降观测、基坑监测");
        Element jiaCompany = zhubiao.addElement("jiaCompany");
        jiaCompany.addText("天津滨海开元房地产开发有限公司");
        Element yiCompany = zhubiao.addElement("yiCompany");
        yiCompany.addText("北京腾远建筑设计有限公司");
        Element bingCompany = zhubiao.addElement("bingCompany");
        bingCompany.addText("");
        Element contractMoney = zhubiao.addElement("contractMoney");
        contractMoney.addText("289600.0000");
        Element payMoney = zhubiao.addElement("payMoney");
        payMoney.addText("");
        Element totalPayMoney = zhubiao.addElement("totalPayMoney");
        totalPayMoney.addText("0.0000");
        Element applyMoney = zhubiao.addElement("applyMoney");
        applyMoney.addText("100.0000");
        Element payCause = zhubiao.addElement("payCause");
        payCause.addText("一般付款");
        Element eventId = zhubiao.addElement("eventId");
        eventId.addText("a80eda15-d025-e811-97a8-801844e12aaaa");
        Element isContract = zhubiao.addElement("isContract");
        isContract.addText("true");
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

            System.err.println(stringWriter.toString());
            org.apache.axis.client.Service service=new org.apache.axis.client.Service();
            Call call=(Call) service.createCall();
            call.setOperationName(new QName("http://jiuqi.com.cn","parseStrWithAuth"));
            call.setTargetEndpointAddress(new URL("http://60.29.46.254:8888/dna_ws/ImpBillWebService?wsdl"));
            call.addParameter("xmlStr", XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter("userName", XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter("userPWD", XMLType.XSD_STRING, ParameterMode.IN);
            call.setReturnType(XMLType.XSD_STRING);
            String result=(String)call.invoke(new Object[]{stringWriter.toString(),"jq","8EE8AD6B3F36473A16E8F2B155D53C9F"});
            System.err.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
