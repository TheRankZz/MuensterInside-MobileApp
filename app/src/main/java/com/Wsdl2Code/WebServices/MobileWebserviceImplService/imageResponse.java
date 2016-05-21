package com.Wsdl2Code.WebServices.MobileWebserviceImplService;

//------------------------------------------------------------------------------
// <wsdl2code-generated>
//    This code was generated by http://www.wsdl2code.com version  2.6
//
// Date Of Creation: 5/21/2016 11:56:15 AM
//    Please dont change this code, regeneration will override your changes
//</wsdl2code-generated>
//
//------------------------------------------------------------------------------
//
//This source code was auto-generated by Wsdl2Code  Version
//
import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import java.util.Hashtable;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

public class imageResponse implements KvmSerializable {
    
    public String imageDataBase64;
    public String mimeType;
    public String message;
    public int returnCodeField;
    
    public imageResponse(){}
    
    public imageResponse(SoapObject soapObject)
    {
        if (soapObject == null)
            return;
        if (soapObject.hasProperty("imageDataBase64"))
        {
            Object obj = soapObject.getProperty("imageDataBase64");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                SoapPrimitive j =(SoapPrimitive) obj;
                imageDataBase64 = j.toString();
            }else if (obj!= null && obj instanceof String){
                imageDataBase64 = (String) obj;
            }
        }
        if (soapObject.hasProperty("mimeType"))
        {
            Object obj = soapObject.getProperty("mimeType");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                SoapPrimitive j =(SoapPrimitive) obj;
                mimeType = j.toString();
            }else if (obj!= null && obj instanceof String){
                mimeType = (String) obj;
            }
        }
        if (soapObject.hasProperty("message"))
        {
            Object obj = soapObject.getProperty("message");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                SoapPrimitive j =(SoapPrimitive) obj;
                message = j.toString();
            }else if (obj!= null && obj instanceof String){
                message = (String) obj;
            }
        }
        if (soapObject.hasProperty("returnCode"))
        {
            Object obj = soapObject.getProperty("returnCode");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                SoapPrimitive j =(SoapPrimitive) obj;
                returnCodeField = Integer.parseInt(j.toString());
            }else if (obj!= null && obj instanceof Number){
                returnCodeField = (Integer) obj;
            }
        }
    }
    @Override
    public Object getProperty(int arg0) {
        switch(arg0){
            case 0:
                return imageDataBase64;
            case 1:
                return mimeType;
            case 2:
                return message;
            case 3:
                return returnCodeField;
        }
        return null;
    }
    
    @Override
    public int getPropertyCount() {
        return 4;
    }
    
    @Override
    public void getPropertyInfo(int index, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        switch(index){
            case 0:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "imageDataBase64";
                break;
            case 1:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "mimeType";
                break;
            case 2:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "message";
                break;
            case 3:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "returnCode";
                break;
        }
    }

    
    @Override
    public void setProperty(int arg0, Object arg1) {
    }
    
}
