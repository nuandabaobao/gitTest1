package demo;

import com.alibaba.fastjson.JSON;
import com.tbjj.portal.common.exception.ServiceException;
import com.tbjj.portal.core.mywebservice.HTFKService;
import com.tbjj.portal.core.mywebservice.HTFKServiceSoap;

import java.util.Date;
import java.util.Map;

public class Demo06 {
    public static void main(String[] args){
        HTFKService htfkService=new HTFKService();
        HTFKServiceSoap htfkServiceSoap = htfkService.getHTFKServiceSoap();
        String approveResult = htfkServiceSoap.approveResult("d5256916-410e-4a0c-8dd2-3748a0e761f1",null, "2018-05-29 09:43:00", 1, null);

        Map<String, Object> approveMapResult = (Map<String, Object>) JSON.parse(approveResult);
        if(approveMapResult.get("errorCode")==null || !"0".equals(approveMapResult.get("errorCode").toString())){
            throw new ServiceException(1,null,"审批结果接口调用业务系统接口错误_shenpijieguo");
        }
        System.err.println(approveMapResult.get("errorCode")+"--------"+approveMapResult.get("msg"));
    }
}
