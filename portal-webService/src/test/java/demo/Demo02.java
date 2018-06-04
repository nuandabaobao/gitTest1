package demo;

import com.alibaba.fastjson.JSON;
import org.apache.axis.client.Call;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.encoding.XMLType;
import java.net.URL;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/8/008.
 */
public class Demo02 {

    public static void main(String[] agrs){

        try {
            org.apache.axis.client.Service service=new org.apache.axis.client.Service();
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(new URL("http://60.29.46.254:8060/Cbgl/WebService/HTFKService.asmx?wsdl"));
            call.setOperationName(new QName("http://tempuri.org/","approveResult"));
            call.addParameter("eventId", XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter("operationTime", XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter("result", XMLType.XSD_INT, ParameterMode.IN);
            call.addParameter("responseDesc", XMLType.XSD_STRING, ParameterMode.IN);
            call.setUseSOAPAction(true);
            call.setReturnType(XMLType.XSD_STRING);
            call.setSOAPActionURI("http://tempuri.org/ApproveResult");
            String result=(String)call.invoke(new Object[]{"10001","2018-03-08 09:08:08",1,"aaa"});
            Map<String, Object> map = (Map<String, Object>) JSON.parse(result);
            System.err.println(map.get("errorCode"));
            System.err.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        http://60.29.46.254:8060/Cbgl/WebService/HTFKService.asmx
    }
}
