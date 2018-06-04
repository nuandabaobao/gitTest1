package com.tbjj.portal.business;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by Administrator on 2018/2/27/027.
 */
@WebService(targetNamespace = "http://webService.business.com/")
public interface Business {

    @WebMethod
    String demo(@WebParam(name="name1") String name1);

    /**
     * 我发起的申请
     * @param xmlStr
     * @return
     */
    @WebMethod
    public String addApply(@WebParam(name="xmlStr") String xmlStr);

    /**
     * 待办
     * @return
     */
    @WebMethod
    public String addWait(@WebParam(name="xmlStr") String xmlStr);

    /**
     * 待办转已办
     * @return
     */
    @WebMethod
    public String waitToFinish(@WebParam(name="xmlStr") String xmlStr);

    //制单
    @WebMethod
    public String createItemOrder(@WebParam(name="xmlStr") String xmlStr);
}
