package demo;

import com.alibaba.fastjson.JSON;
import demo01.HTFKService;
import demo01.HTFKServiceSoap;

import java.util.Map;

/**
 * Created by Administrator on 2018/3/9/009.
 */
public class Demo03 {
    public static void main(String[] args){
        HTFKService htfkService=new HTFKService();
        HTFKServiceSoap htfkServiceSoap = htfkService.getHTFKServiceSoap();
//        htfkServiceSoap.approveResult();
        String aaa = htfkServiceSoap.approveResult("c41be5d1-bc14-4536-94b3-bff91fd411dd",null,"2018-03-09 09:13:12",2,"测试审批结果接口");
        System.err.println(aaa);
        Map<String,Object> mapResult =(Map<String, Object>) JSON.parse(aaa);
        System.err.println(mapResult.get("errorCode")+"----"+mapResult.get("msg"));
        // 创建XML文档树
//        Document document = DocumentHelper.createDocument();
//
//        Element business = document.addElement("business");
//        Element fkApplyGUID = business.addElement("FkApplyGUID");
//        fkApplyGUID.addText("BD9C1349-044F-438C-B055-008BF16E14C6");
//        Element contractNo = business.addElement("ContractNo");
//        contractNo.addText("ZF-JHAE03-2016062");//合同编号
//        Element jbDate = business.addElement("JbDate");
//        jbDate.addText("2018-03-09 09:09:09");//付款日期
//        Element items = business.addElement("Items");
//        Element payItemEntity = items.addElement("PayItemEntity");
//        Element itemName = payItemEntity.addElement("ItemName");
//        itemName.addText("付款接口款项名称");
////        Element itemType = payItemEntity.addElement("ItemType");
////        itemType.addText("结算方式");
//        Element realPayMoney = payItemEntity.addElement("RealPayMoney");
//        realPayMoney.addText("1000");//实付金额
//        Element element = business.addElement("Invoices");
//
//
//        // 设置XML文档格式
//        OutputFormat outputFormat = OutputFormat.createPrettyPrint();
//        // 设置XML编码方式,即是用指定的编码方式保存XML文档到字符串(String),这里也可以指定为GBK或是ISO8859-1
//        outputFormat.setEncoding("UTF-8");
//        outputFormat.setIndent(true); //设置是否缩进
//        outputFormat.setNewlines(true); //设置是否换行
//        // stringWriter字符串是用来保存XML文档的
//        StringWriter stringWriter = new StringWriter();
//
//        try {
//            // xmlWriter是用来把XML文档写入字符串的(工具)
//            XMLWriter xmlWriter = new XMLWriter(stringWriter, outputFormat);
//            // 把创建好的XML文档写入字符串
//            xmlWriter.write(document);
//            xmlWriter.close();
//            System.err.println(stringWriter.toString());
//            String s = htfkServiceSoap.payMoney(stringWriter.toString());
//            System.err.println(s);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
////        VoucherEntity voucherEntity = new VoucherEntity();
////        voucherEntity.setFkApplyGUID("BD9C1349-044F-438C-B055-008BF16E14C6");
////        voucherEntity.setContractNo("11");
//////        voucherEntity.setReceiveComplay("");
//////        voucherEntity.setPayCompany("");
//////        voucherEntity.setReceiveBank("");
//////        voucherEntity.setPayBank("");
//////        voucherEntity.setInvoType("");
//////        voucherEntity.setInovNo("");
//////            voucherEntity.setBusinessType("预付款");
//////            voucherEntity.setJbr("zhangchi");
////        voucherEntity.setJbDate("2018-03-09 09:09:09");
////
////        ArrayOfPayItemEntity arrayOfPayItemEntity=new ArrayOfPayItemEntity();
////
////        PayItemEntity payItemEntity=new PayItemEntity();
////
////        payItemEntity.setItemName("款项名称");
//////            payItemEntity.setItemType("123");
//////        payItemEntity.setBz("");
////        payItemEntity.setRealPayMoney(null);
//////        payItemEntity.setRate(null);
//////        payItemEntity.setPayBankName("");
//////        payItemEntity.setFinanceJsMode("");
//////        payItemEntity.setFinanceJsCode("");
//////        payItemEntity.setFundSource("");
//////        payItemEntity.setPayMode("");
////        List<PayItemEntity> payItemEntity1 = arrayOfPayItemEntity.getPayItemEntity();
////        payItemEntity1.add(payItemEntity);
////
////
////        ArrayOfInvoiceEntity arrayOfInvoiceEntity  =new ArrayOfInvoiceEntity();
////        InvoiceEntity invoiceEntity=new InvoiceEntity();
//////        invoiceEntity.setReceiveUnitName("");
//////        invoiceEntity.setInvoNO("");
//////        invoiceEntity.setInvoType("");
//////        invoiceEntity.setExcludingTaxInvoiceAmount(null);
//////        invoiceEntity.setInputTaxAmount(null);
//////        invoiceEntity.setInvoiceAmount(null);
//////        invoiceEntity.setInvoiceDate(null);
////        List<InvoiceEntity> invoiceEntity1 = arrayOfInvoiceEntity.getInvoiceEntity();
////        invoiceEntity1.add(invoiceEntity);
////
////
////        String s = htfkServiceSoap.payMoney(voucherEntity, arrayOfPayItemEntity, arrayOfInvoiceEntity);
////
////        System.err.println(s);
////        Map<String, Object> map = (Map<String, Object>) JSON.parse(s);
////        System.err.println(map.get("errorCode")+"---"+map.get("msg"));

    }
}
