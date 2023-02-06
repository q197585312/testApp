package nanyang.com.dig88.Base;

import android.content.Context;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.util.HashMap;

import gaming178.com.mylibrary.base.quick.QuickBaseXmlThreadHandler;
import gaming178.com.mylibrary.base.quick.QuickRequestBean;


/**
 * Created by Administrator on 2015/10/21.
 */
public abstract class NyVolleyXmlThreadHandler extends QuickBaseXmlThreadHandler<XmlResponse> {

    public NyVolleyXmlThreadHandler(Context context) {
        super(context);
    }

    @Override
    protected QuickRequestBean getRequestBean() {
        HashMap<String, String> params = new HashMap<>();
        return getNyRequestBean(params);
    }
    @Override
    protected QuickRequestBean handlePageParams(QuickRequestBean bean, Integer obj, int pageSize) {
        return bean;
    }
    protected abstract QuickRequestBean getNyRequestBean(HashMap<String, String> params);

    @Override
    protected XmlResponse baseParseXml(String xml) {
        XStream xs=new XStream(new DomDriver());
        xml=  xml.replaceAll("&", "&amp;").trim();
        xs.alias("response", XmlResponse.class);
        xs.useAttributeFor(XmlResponse.class, "errcode");
        xs.useAttributeFor(XmlResponse.class, "errtext");
        xs.useAttributeFor(XmlResponse.class, "result");
        XmlResponse t=  (XmlResponse) xs.fromXML(xml);
        return t;
    }

    @Override
    public void successEnd(XmlResponse obj) {
        super.successEnd(obj);
        if (obj.getErrcode().trim().equals("0")||obj.getErrcode().trim().equals("1")) {
            successEndT(obj.getResult());
        } else {
            errorEnd(obj.getErrtext());
        }
    }

    protected abstract void successEndT(String data);
}
