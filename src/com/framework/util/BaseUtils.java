package com.framework.util;

import org.ksoap2.HeaderProperty;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class BaseUtils {

    public static String callWebService(String serviceUrl, String nameSpace, String soapAction,
                                        String methodName, LinkedHashMap<String, Object>
												parameter) {
        String result = "fail";
        SoapObject request = new SoapObject(nameSpace,
                methodName);
        Iterator<Entry<String, Object>> iterator = parameter.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, Object> entry = (Entry<String, Object>) iterator.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            request.addProperty(key.toString(), val);
        }
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.bodyOut = request;
        //是否是.net
        envelope.dotNet = true;
        HttpTransportSE ht = new HttpTransportSE(serviceUrl, 15000);
        try {
            ArrayList<HeaderProperty> headerPropertyArrayList = new ArrayList<HeaderProperty>();
            headerPropertyArrayList.add(new HeaderProperty("Connection", "close"));
            ht.call(soapAction, envelope, headerPropertyArrayList);
            if (envelope.getResponse() != null) {
                Object object = (Object) envelope.getResponse();
                result = object.toString();
                return result;
            } else {
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
