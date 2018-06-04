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
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;

/**
 * Created by Administrator on 2018/2/27/027.
 */
public class Demo {
    public static void main(String[] args) throws Exception{

        Document document= DocumentHelper.createDocument();
        Element business=document.addElement("business");


        //测试我发起的申请
//        Element source=business.addElement("source");
//        source.addText("001");
//        Element eventId = business.addElement("eventId");
//        eventId.addText("10002");
//        Element title = business.addElement("title");
//        title.addText("测试");
//        Element sender = business.addElement("sender");
//        sender.addText("zhaoxin");
//        Element operationTime = business.addElement("operationTime");
//        operationTime.addText("2018-01-08 11:54:35");
//        Element url = business.addElement("url");
//        url.addText("");

        //测试我的待办
//        Element eventId=business.addElement("eventId");
//        eventId.addText("10002");
//        Element title=business.addElement("title");
//        title.addText("“采购管理_招标控制价审批-0019”请您及时审批！");
//        Element sender=business.addElement("sender");
//        sender.addText("zhaoxin");
//        Element receiver=business.addElement("receiver");
//        receiver.addText("zhaoxin");
//        Element operationTime=business.addElement("operationTime");
//        operationTime.addText("2018-03-06 10:09:40");
//        Element approveUrl=business.addElement("approveUrl");
//        approveUrl.addText("");
//        Element backlogId=business.addElement("backlogId");
//        backlogId.addText("1001");

        //测试待办转已办
//        Element eventId=business.addElement("eventId");
//        eventId.addText("10001");
//        Element operationTime=business.addElement("operationTime");
//        operationTime.addText("2018-03-01 11:42:01");
//        Element backlogId=business.addElement("backlogId");
//        backlogId.addText("B9B91D6D-E320-E811-80C9-00155D0B0C04");
//        Element isArchive = business.addElement("isArchive");
//        isArchive.addText("2");

        //测试制单
        Element source=business.addElement("source");
        source.addText("001");
        Element eventId=business.addElement("eventId");
        eventId.addText("d6635166-70e0-4c09-a858-c8665bc14koh");
        Element title=business.addElement("title");
        title.addText("合同付款审批");
        Element sender=business.addElement("sender");
        sender.addText("zhangchi");
        Element operationTime=business.addElement("operationTime");
        operationTime.addText("2018-03-20 10:11:00");
        Element businessParams=business.addElement("businessParams");

        Element companyName=businessParams.addElement("companyName");
        companyName.addText("天津滨海开元房地产开发有限公司");
        Element contractType=businessParams.addElement("contractType");
        contractType.addText("03.02.01");
        Element applyUserName=businessParams.addElement("applyUserName");
        applyUserName.addText("zhangchi");
        Element applyDept=businessParams.addElement("applyDept");
        applyDept.addText("预算控制部");
        Element clauseItemName=businessParams.addElement("clauseItemName");
        clauseItemName.addText("进度款");
        Element itemName=businessParams.addElement("itemName");
//        itemName.addText("金海岸E03");
        Element contractCode=businessParams.addElement("contractCode");
        contractCode.addText("ZF-JHAE03-2016061");
        Element contractName=businessParams.addElement("contractName");
        contractName.addText("天保金海岸E03地块住宅项目桩基检测");
        Element jiaCompany=businessParams.addElement("jiaCompany");
        jiaCompany.addText("天津滨海开元房地产开发有限公司");
        Element yiCompany=businessParams.addElement("yiCompany");
        yiCompany.addText("天津兴油建筑工程技术有限公司");
        Element bingCompany=businessParams.addElement("bingCompany");
        bingCompany.addText("");
        Element contractMoney=businessParams.addElement("contractMoney");
        contractMoney.addText("349400.0000");
//        Element payMoney=businessParams.addElement("payMoney");
//        payMoney.addText("");
        Element totalPayMoney=businessParams.addElement("totalPayMoney");
        totalPayMoney.addText("0.0000");
        Element applyMoney=businessParams.addElement("applyMoney");
        applyMoney.addText("10000.0000");
        Element payCause=businessParams.addElement("payCause");
        payCause.addText("一般付款");
        Element unitid=businessParams.addElement("unitid");
        unitid.addText("天津滨海开元房地产开发有限公司");



        // 设置XML文档格式
        OutputFormat outputFormat = OutputFormat.createPrettyPrint();
        // 设置XML编码方式,即是用指定的编码方式保存XML文档到字符串(String)
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

        System.err.println(stringWriter.toString());


        //这是直接调用的
//        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
//        String wsUrl = "http://60.29.46.254:18087/tbjj/business.asmx?wsdl";
//        Client client = dcf.createClient(wsUrl);
//        String method = "createItemOrder";//webservice的方法名
//        Object[] result = null;
//        result = client.invoke(method, stringWriter.toString());//调用webservice
//        System.err.println(result[0]);

        org.apache.axis.client.Service service=new org.apache.axis.client.Service();
        Call call=(Call) service.createCall();
        call.setOperationName(new QName("http://webService.business.com/","createItemOrder"));//addApply addWait waitToFinish  createItemOrder
        call.setTargetEndpointAddress(new URL("http://60.29.46.254:18087/tbjj/business.asmx?wsdl"));
        call.addParameter("xmlStr", XMLType.XSD_STRING, ParameterMode.IN);
        call.setReturnType(XMLType.XSD_STRING);
        String result=(String)call.invoke(new Object[]{stringWriter.toString()});
        System.err.println(result);
    }
}
