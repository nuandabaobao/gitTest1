package com.tbjj.portal.business;

import com.tbjj.portal.common.entity.XmlResult;
import com.tbjj.portal.common.exception.ServiceException;
import com.tbjj.portal.common.utils.DateUtil;
import com.tbjj.portal.core.WaitService;
import com.tbjj.portal.core.bo.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/27/027.
 */
@WebService(targetNamespace = "http://webService.business.com/",endpointInterface = "com.tbjj.portal.business.Business")
public class BusinessImpl implements Business{

    @Autowired
    private WaitService waitService;

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String demo(String name) {
        logger.info("进入方法-----------demo----------,参数："+name);
        return "1";
    }

    /**
     * 业务系统发起的申请接口
     * @param xmlStr
     * @return
     */
    @Override
    public String addApply(String xmlStr) {
        logger.info("addApply-----进入");
        logger.info("参数"+xmlStr);
        XmlResult xmlResult=new XmlResult();
        try {
            logger.info("jinrudaoDocument------");
            Document document=DocumentHelper.parseText(xmlStr);
            Element root=document.getRootElement();
            List<Element> elements = root.elements();
            Map<String,Object> map=new HashMap<String,Object>();
            if(elements!=null && elements.size()>0){
                for (Element element:elements) {
                    map.put(element.getName(),element.getTextTrim());
                }
            }
            logger.info("Document-----documentwancheng");
            String errorCode="1001";
            String msg="参数错误";
            if(map.get("source")==null){
                return xmlResult.responseXml(errorCode, msg);
            }
            if(map.get("eventId")==null){
                return xmlResult.responseXml(errorCode, msg);
            }
            if(map.get("title")==null){
                return xmlResult.responseXml(errorCode, msg);
            }
            if(map.get("sender")==null){
                return xmlResult.responseXml(errorCode, msg);
            }
            if(map.get("operationTime")==null){
                return xmlResult.responseXml(errorCode, msg);
            }
            if(map.get("url")==null){
                return xmlResult.responseXml(errorCode, msg);
            }
            ApplyBO bo = new ApplyBO();
            bo.setSource(map.get("source").toString());
            bo.setEventId(map.get("eventId").toString());
            bo.setTitle(map.get("title").toString());
            bo.setSender(map.get("sender").toString());
            bo.setOperationTime(map.get("operationTime").toString());
//            bo.setUrl(map.get("url").toString());

            //设置单点登录
            bo.setUrl("http://60.29.46.254:8060/Product/Interface/SSO/Login.aspx?UserCode="+bo.getSender()+"&PageUrl="+ URLEncoder.encode(map.get("url").toString(),"utf-8"));

            int count = waitService.addApply(bo);
            errorCode="0";
            msg="成功";
            return xmlResult.responseXml(errorCode,msg);
        } catch (Exception e) {
            e.printStackTrace();
            StackTraceElement stackTraceElement = e.getStackTrace()[0];
            logger.info("Line---"+stackTraceElement.getLineNumber());
            logger.info("message---"+e.getMessage());
            return xmlResult.responseXml("10003 ","服务器异常");
        }
    }

    /**
     * 待办
     * @param xmlStr
     * @return
     */
    @Override
    public String addWait(String xmlStr) {
        logger.info("daibanchuanrucanshu------"+xmlStr);
        XmlResult xmlResult=new XmlResult();
        try {
            Document document=DocumentHelper.parseText(xmlStr);
            Element root=document.getRootElement();
            List<Element> elements = root.elements();
            Map<String,Object> map=new HashMap<String,Object>();
            if(elements!=null && elements.size()>0){
                for (Element element:elements) {
                    map.put(element.getName(),element.getTextTrim());
                }
            }
            String errorCode="1001";
            String msg="参数错误";
            if(map.get("eventId")==null){
                return xmlResult.responseXml(errorCode, msg);
            }
            if(map.get("title")==null){
                return xmlResult.responseXml(errorCode, msg);
            }
            if(map.get("sender")==null){
                return xmlResult.responseXml(errorCode, msg);
            }
            if(map.get("receiver")==null){
                return xmlResult.responseXml(errorCode, msg);
            }
            if(map.get("operationTime")==null){
                return xmlResult.responseXml(errorCode, msg);
            }
            if(map.get("approveUrl")==null){
                return xmlResult.responseXml(errorCode, msg);
            }
            if(map.get("backlogId")==null){
                return xmlResult.responseXml(errorCode, msg);
            }
            if(map.get("isUpdate")==null){
                return xmlResult.responseXml(errorCode, msg);
            }
            WaitBO bo = new WaitBO();
            bo.setEventId(map.get("eventId").toString());
//            bo.setApproveUrl(map.get("approveUrl").toString());
            bo.setBacklogId(map.get("backlogId").toString());
            bo.setOperationTime(map.get("operationTime").toString());
            bo.setRevceier(map.get("receiver").toString());
            bo.setSender(map.get("sender").toString());
            bo.setTitle(map.get("title").toString());
            bo.setIsUpdate(map.get("isUpdate").toString());

            //设置单点登录
            bo.setApproveUrl("http://60.29.46.254:8060/Product/Interface/SSO/Login.aspx?UserCode="+bo.getRevceier()+"&PageUrl="+URLEncoder.encode(map.get("approveUrl").toString(),"utf-8"));

            Integer count = waitService.addWait(bo);
            if(count==3){
                errorCode="1002";
                msg="该事件尚未进行申请";
            }else{
                errorCode="0";
                msg="成功";
            }
            return xmlResult.responseXml(errorCode,msg);
        } catch (Exception e) {
            e.printStackTrace();
            return xmlResult.responseXml("10003 ","服务器异常");
        }
    }

    /**
     * 待办转已办
     * @param xmlStr
     * @return
     */
    @Override
    public String waitToFinish(String xmlStr) {
        XmlResult xmlResult=new XmlResult();
        logger.info("daibanzhuanyiban------"+xmlStr);
        try {
            Document document=DocumentHelper.parseText(xmlStr);
            Element root=document.getRootElement();
            List<Element> elements = root.elements();
            Map<String,Object> map=new HashMap<String,Object>();
            if(elements!=null && elements.size()>0){
                for (Element element:elements) {
                    map.put(element.getName(),element.getTextTrim());
                }
            }
            String errorCode="1001";
            String msg="参数错误";
            if(map.get("eventId")==null){
                return xmlResult.responseXml(errorCode, msg);
            }
            if(map.get("operationTime")==null){
                return xmlResult.responseXml(errorCode, msg);
            }
            if(map.get("backlogId")==null){
                return xmlResult.responseXml(errorCode, msg);
            }
            if(map.get("backlogId")==null){
                return xmlResult.responseXml(errorCode, msg);
            }
            WaitToFinishBO bo = new WaitToFinishBO();
            bo.setBacklogId(map.get("backlogId").toString());
            bo.setEventId(map.get("eventId").toString());
            bo.setOperationTime(DateUtil.formatDate(map.get("operationTime").toString()));
            if(map.get("backlogId")!=null){
                bo.setIsArchive(map.get("isArchive").toString());
            }
            waitService.waitToFinish(bo);
            errorCode="0";
            msg="成功";
            return xmlResult.responseXml(errorCode,msg);
        } catch (Exception e) {
            e.printStackTrace();
            return xmlResult.responseXml("10003 ","服务器异常");
        }
    }

    //制单
    @Override
    public String createItemOrder(String xmlStr) {
        logger.info("------------jinruzhidan1------------------");
        logger.info(xmlStr);
        XmlResult xmlResult=new XmlResult();
//        try {
        Document document= null;
        try {
            document = DocumentHelper.parseText(xmlStr);
        } catch (DocumentException e) {
            e.printStackTrace();
            return xmlResult.responseXml("1001 ","xml格式错误");
        }
        logger.info("------------jinruzhidan2------------------");
        Element root=document.getRootElement();
            List<Element> elements = root.elements();
            Map<String,Object> map=new HashMap<String,Object>();
            if(elements!=null && elements.size()>0){
                for (Element element:elements) {
                    if("businessParams".equals(element.getName())){
                        Map<String,Object> paramMap=new HashMap<>();
                        List<Element> paramElements = element.elements();
                        if(paramElements!=null && paramElements.size()>0){
                            for (Element paramElement:paramElements) {
                                paramMap.put(paramElement.getName(),paramElement.getTextTrim());
                            }
                        }
                        map.put(element.getName(),paramMap);
                    }else{
                        map.put(element.getName(),element.getTextTrim());
                    }
                }
            }
        logger.info("------------jinruzhidan3------------------");
        String errorCode="1001";
        String msg="参数错误";
        if(map.get("source")==null){
            return xmlResult.responseXml(errorCode, msg);
        }
        if(map.get("eventId")==null){
            return xmlResult.responseXml(errorCode, msg);
        }
        if(map.get("title")==null){
            return xmlResult.responseXml(errorCode, msg);
        }
        if(map.get("sender")==null){
            return xmlResult.responseXml(errorCode, msg);
        }
        if(map.get("operationTime")==null){
            return xmlResult.responseXml(errorCode, msg);
        }
        //参数封装
        CreateItemOrderBO createItemOrderBO=new CreateItemOrderBO();
        createItemOrderBO.setSource(map.get("source").toString());
        createItemOrderBO.setEventCode(map.get("eventId").toString());
        createItemOrderBO.setTitle(map.get("title").toString());
        createItemOrderBO.setSender(map.get("sender").toString());
        createItemOrderBO.setReceiver(map.get("sender").toString());
        createItemOrderBO.setOperationTime(DateUtil.formatDate(map.get("operationTime").toString()));
        BillReqBO billReqBO=new BillReqBO();
        if(map.get("businessParams")==null){
            throw new ServiceException(1001,null,"合同参数为空");
        }
        logger.info("jinruzhidan4");
        Map<String,Object> businessParams =(Map) map.get("businessParams");
        if(businessParams.get("companyName")!=null){
            billReqBO.setCompanyName(businessParams.get("companyName").toString());
        }
        if(businessParams.get("contractType")!=null){
            billReqBO.setContractType(businessParams.get("contractType").toString());
        }
        if(businessParams.get("applyUserName")!=null){
            billReqBO.setApplyUserName(businessParams.get("applyUserName").toString());
        }
        if(businessParams.get("applyDept")!=null){
            billReqBO.setApplyDept(businessParams.get("applyDept").toString());
        }
        if(businessParams.get("clauseItemName")!=null){
            billReqBO.setClauseItemName(businessParams.get("clauseItemName").toString());
        }
        if(businessParams.get("itemName")!=null){
            billReqBO.setItemName(businessParams.get("itemName").toString());
        }
        if(businessParams.get("contractCode")!=null){
            billReqBO.setContractCode(businessParams.get("contractCode").toString());
        }
        if(businessParams.get("contractName")!=null){
            billReqBO.setContractName(businessParams.get("contractName").toString());
        }
        if(businessParams.get("jiaCompany")!=null){
            billReqBO.setJiaCompany(businessParams.get("jiaCompany").toString());
        }
        if(businessParams.get("yiCompany")!=null){
            billReqBO.setYiCompany(businessParams.get("yiCompany").toString());
        }
        if(businessParams.get("bingCompany")!=null){
            billReqBO.setBingCompany(businessParams.get("bingCompany").toString());
        }
        if(businessParams.get("contractMoney")!=null){
            billReqBO.setContractMoney(businessParams.get("contractMoney").toString());
        }
        if(businessParams.get("payMoney")!=null){
            billReqBO.setPayMoney(businessParams.get("payMoney").toString());
        }
        if(businessParams.get("totalPayMoney")!=null){
            billReqBO.setTotalPayMoney(businessParams.get("totalPayMoney").toString());
        }
        if(businessParams.get("applyMoney")!=null){
            billReqBO.setApplyMoney(businessParams.get("applyMoney").toString());
        }
        if(businessParams.get("payCause")!=null){
            billReqBO.setPayCause(businessParams.get("payCause").toString());
        }
        if(businessParams.get("unitid")!=null){
            billReqBO.setUnitid(businessParams.get("unitid").toString());
        }
        billReqBO.setEventCode(map.get("eventId").toString());
        createItemOrderBO.setBillReqBO(billReqBO);
        createItemOrderBO.setTitle(createItemOrderBO.getBillReqBO().getContractName()+"-"+createItemOrderBO.getTitle());
        logger.info("--------------jinruzhidan5---------------");
        waitService.createItemOrder(createItemOrderBO);
        errorCode="0";
        msg="成功";
        return xmlResult.responseXml(errorCode,msg);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return xmlResult.responseXml("10003 ","服务器异常");
//        }
    }
}
