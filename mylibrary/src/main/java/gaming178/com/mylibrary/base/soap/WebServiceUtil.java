package gaming178.com.mylibrary.base.soap;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;

import java.util.List;

public class WebServiceUtil {
    private static MAndroidHttpTransport transport;

    public static WebServiceBean getSoapData(SoapRequestBean bean) {
        // 1.实例化SoapObject对象
        SoapObject request = new SoapObject(bean.getSpaceName(), bean.getMethodName());

        // 添加参数
        List<SoapParamsBean> params = bean.getParams();
        if (bean.getParams() != null) {
            for (SoapParamsBean param : params) {
                request.addProperty(param.getKey(), param.getValue());
            }
        }

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        //
        envelope.bodyOut = request;
        envelope.dotNet = false;
        // 4.构建传输对象
        // AndroidHttpTransport transport = new
        // AndroidHttpTransport(Const.URL);//原来
        transport = new MAndroidHttpTransport(bean.getUrl(),
                1000);// Const.TIMEOUT
        //
        transport.debug = true;
        Object result = null;
        try {
            // 5.访问WebService,第一个参数为命名空间 + 方法名,第二个参数为Envelope对象
            transport.call(bean.getSpaceName() + bean.getMethodName(), envelope);
            // r = (SoapObject) envelope.bodyIn;
            result = envelope.getResponse();
//            Log.i("result------------------->"+result);
            return new WebServiceBean(1, String.valueOf(result));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new WebServiceBean(-1, e.getMessage());
        }
        // 6.解析返回的数据
    }

    public static void cancleCall() {
        if (transport != null) {
            transport.reset();
        }
    }
   /* private String CallFun(String ProcName,HashMap<String, String> HM){

        List<StringBuffer> list = MySort(HM);

        StringBuffer crc16 = list.get(1).append("&Ip="+P_ip);
        System.out.println(P_ip);
        String CRC_16_Modbus = CRC16.calcCrc16(crc16.toString().getBytes())+"";
        String Data=list.get(0).toString();
        //String  Key="506FE329-4EE2-4898-8870-4D1615DBE852";
        String xmlStr="";
        try{
            String service_url = "http://120.26.73.230/GPSCAR/GPSAPI.asmx?wsdl";
            Provider.Service service = new Provider.Service();
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(new java.net.URL(service_url));
// 设置要调用的方法
// http://intelink.net/是wsdl中definitions根节点的targetNamespace属性值
            call.setOperationName(new QName("http://chlbs.com/","CallAPI")); //这个就是命名空间
// 该方法需要的参数
            call.addParameter("ProcName",org.apache.axis.encoding.XMLType.XSD_STRING,javax.xml.rpc.ParameterMode.IN);
            call.addParameter("Key",org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
            call.addParameter("Data", org.apache.axis.encoding.XMLType.XSD_STRING,javax.xml.rpc.ParameterMode.IN);
            call.addParameter("CRC_16_Modbus", org.apache.axis.encoding.XMLType.XSD_STRING,javax.xml.rpc.ParameterMode.IN);
// 方法的返回值类型
            call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
            xmlStr = call.invoke(new Object[] { ProcName, Key, Data,CRC_16_Modbus}).toString();
        } catch (Exception e) {
            e.printStackTrace();
            xmlStr=e.getMessage();
        }
        return xmlStr;
    }*/

}
