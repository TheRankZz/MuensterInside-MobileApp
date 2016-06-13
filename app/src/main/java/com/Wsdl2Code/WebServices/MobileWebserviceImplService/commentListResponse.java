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

public class commentListResponse implements KvmSerializable {
    
    public VectorcommentTO commentList;
    public String message;
    public int returnCode;
    
    public commentListResponse(){}
    
    public commentListResponse(SoapObject soapObject)
    {
        if (soapObject == null)
            return;
        if (soapObject.hasProperty("commentList"))
        {
            //SoapObject j = (SoapObject)soapObject.getProperty("commentList");
            commentList = new VectorcommentTO(soapObject);
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
                returnCode = Integer.parseInt(j.toString());
            }else if (obj!= null && obj instanceof Number){
                returnCode = (Integer) obj;
            }
        }
    }
    @Override
    public Object getProperty(int arg0) {
        switch(arg0){
            case 0:
                return commentList;
            case 1:
                return message;
            case 2:
                return returnCode;
        }
        return null;
    }
    
    @Override
    public int getPropertyCount() {
        return 3;
    }
    
    @Override
    public void getPropertyInfo(int index, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        switch(index){
            case 0:
                info.type = PropertyInfo.VECTOR_CLASS;
                info.name = "commentList";
                break;
            case 1:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "message";
                break;
            case 2:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "returnCode";
                break;
        }
    }

    
    
    @Override
    public void setProperty(int arg0, Object arg1) {
    }
    
}
