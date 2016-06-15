package Soap;

import com.Wsdl2Code.WebServices.MobileWebserviceImplService.categoryListResponse;
import com.Wsdl2Code.WebServices.MobileWebserviceImplService.commentListResponse;
import com.Wsdl2Code.WebServices.MobileWebserviceImplService.deviceResponse;
import com.Wsdl2Code.WebServices.MobileWebserviceImplService.imageResponse;
import com.Wsdl2Code.WebServices.MobileWebserviceImplService.isVotedResponse;
import com.Wsdl2Code.WebServices.MobileWebserviceImplService.locationListResponse;
import com.Wsdl2Code.WebServices.MobileWebserviceImplService.locationResponse;
import com.Wsdl2Code.WebServices.MobileWebserviceImplService.returncodeResponse;

import org.ksoap2.HeaderProperty;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.List;

/**
 * Created by Julia Bracht and Nicolas Burchert
 */
public class KMobileWebserviceImplService {


    /**
     * Namespace is the targetNamespace in the WSDL.
     */
    private static final String NAMESPACE = "http://webservices.muensterinside.de/";

    /**
     * The WSDL URL.
     */
    private static final String URL = "http://10.60.64.35:8080/muensterinside/mobile"; //http://10.60.64.35/muensterinside/mobile


    public deviceResponse register(String arg0,String arg1) throws Exception{
        deviceResponse result = null;
        String METHOD_NAME = "register";
        SoapObject response = null;

        try {
            response = executeSoapAction(METHOD_NAME, arg0, arg1);
        } catch (Exception ex) {
            throw new Exception("Registrierungs Fehler");
        }

        result = new deviceResponse(response);
        return result;
    }

    public deviceResponse login(String arg0) throws Exception {
        deviceResponse result = null;
        String METHOD_NAME = "login";
        SoapObject response = null;

        try {
            response = executeSoapAction(METHOD_NAME, arg0);
        } catch (Exception ex) {
            throw new Exception("Login Fehler");
        }

        result = new deviceResponse(response);
        return result;
    }

    public returncodeResponse saveComment(String arg0, int arg1, int arg2) throws Exception{
        returncodeResponse result = null;
        String METHOD_NAME = "saveComment";
        SoapObject response = null;

        try{
            response = executeSoapAction(METHOD_NAME, arg0, arg1, arg2);
        }
        catch (Exception e) {
            throw new Exception("Kommentar speichern Fehler");
        }
        result = new returncodeResponse(response);
        return result;
    }

    public categoryListResponse getCategories() throws Exception{
        categoryListResponse result = null;
        String METHOD_NAME = "getCategories";
        SoapObject response = null;

        try {
            response = executeSoapAction(METHOD_NAME);
        }
        catch (Exception ex) {
            throw new Exception("Kategorien auslesen Fehler");
        }

        result = new categoryListResponse(response);
        return result;
    }

    public returncodeResponse uploadImage(int arg0,String arg1,String arg2) throws Exception{
        returncodeResponse result = null;
        String METHOD_NAME = "uploadImage";
        SoapObject response = null;

        try {
            response = executeSoapAction(METHOD_NAME, arg0, arg1, arg2);
        }
        catch (Exception ex) {
            throw new Exception("Bild Upload Fehler");
        }

        result = new returncodeResponse(response);
        return result;
    }

    public locationListResponse getMyVotes(int arg0) throws Exception{
        locationListResponse result = null;
        String METHOD_NAME = "getMyVotes";
        SoapObject response = null;

        try{
            response = executeSoapAction(METHOD_NAME, arg0);
        }
        catch (Exception e) {
            throw new Exception("Meine Votes Fehler");
        }
        result = new locationListResponse(response);
        return result;
    }

    public imageResponse downloadImage(int arg0) throws Exception{
        imageResponse result = null;
        String METHOD_NAME = "downloadImage";
        SoapObject response = null;

        try{
            response = executeSoapAction(METHOD_NAME, arg0);
        }
        catch (Exception e) {
            throw new Exception("Bild Download Fehler");
        }
        result = new imageResponse(response);
        return result;
    }

    public locationListResponse getMyLocations(int arg0) throws Exception{
        locationListResponse result = null;
        String METHOD_NAME = "getMyLocations";
        SoapObject response = null;

        try{
            response = executeSoapAction(METHOD_NAME, arg0);
        }
        catch (Exception e) {
            throw new Exception("Meine Locations Fehler");
        }
        result = new locationListResponse(response);
        return result;
    }

    public returncodeResponse upVote(int arg0,int arg1) throws Exception{
        returncodeResponse result = null;
        String METHOD_NAME = "upVote";
        SoapObject response = null;

        try{
            response = executeSoapAction(METHOD_NAME, arg0, arg1);
        }
        catch (Exception e) {
            throw new Exception("UpVote Fehler");
        }
        result = new returncodeResponse(response);
        return result;
    }

    public returncodeResponse downVote(int arg0,int arg1) throws Exception{
        returncodeResponse result = null;
        String METHOD_NAME = "downVote";
        SoapObject response = null;

        try{
            response = executeSoapAction(METHOD_NAME, arg0, arg1);
        }
        catch (Exception e) {
            throw new Exception("DownVote Fehler");
        }
        result = new returncodeResponse(response);
        return result;
    }

    public locationResponse getLocation(int arg0) throws Exception{
        locationResponse result = null;
        String METHOD_NAME = "getLocation";
        SoapObject response = null;

        try{
            response = executeSoapAction(METHOD_NAME, arg0);
        }
        catch (Exception e) {
            throw new Exception("Location auslesen Fehler");
        }
        result = new locationResponse(response);
        return result;
    }

    public commentListResponse getCommentsByLocation(int arg0) throws Exception{
        commentListResponse result = null;
        String METHOD_NAME = "getCommentsByLocation";
        SoapObject response = null;

        try{
            response = executeSoapAction(METHOD_NAME, arg0);
        }
        catch (Exception e) {
            throw new Exception("Kommentare pro Location auslesen Fehler");
        }
        result = new commentListResponse(response);
        return result;
    }

    public commentListResponse getMyComments(int arg0) throws Exception{
        commentListResponse result = null;
        String METHOD_NAME = "getMyComments";
        SoapObject response = null;

        try{
            response = executeSoapAction(METHOD_NAME, arg0);
        }
        catch (Exception e) {
            throw new Exception(" Meine Kommentare auslesen Fehler");
        }
        result = new commentListResponse(response);
        return result;
    }

    public returncodeResponse deleteComment(int arg0) throws Exception{
        returncodeResponse result = null;
        String METHOD_NAME = "deleteComment";
        SoapObject response = null;

        try{
            response = executeSoapAction(METHOD_NAME, arg0);
        }
        catch (Exception e) {
            throw new Exception("Kommentar löschen Fehler");
        }
        result = new returncodeResponse(response);
        return result;
    }

    public returncodeResponse saveLocation(String arg0,String arg1,String arg2,int arg3,int arg4) throws Exception{
        returncodeResponse result = null;
        String METHOD_NAME = "saveLocation";
        SoapObject response = null;

        try{
            response = executeSoapAction(METHOD_NAME, arg0, arg1, arg2, arg3, arg4);
        }
        catch (Exception e) {
            throw new Exception(" Speicher Location Fehler");
        }
        result = new returncodeResponse(response);
        return result;
    }

    public isVotedResponse isVoted(int arg0, int arg1) throws Exception{
        isVotedResponse result = null;
        String METHOD_NAME = "isVoted";
        SoapObject response = null;

        try{
            response = executeSoapAction(METHOD_NAME, arg0, arg1);
        }
        catch (Exception e) {
            throw new Exception("Schon gevoted Fehler");
        }
        result = new isVotedResponse(response);
        return result;
    }

    public locationListResponse getLocationsByCategory(int arg0) throws Exception{
        locationListResponse result = null;
        String METHOD_NAME = "getLocationsByCategory";
        SoapObject response = null;

        try{
            response = executeSoapAction(METHOD_NAME, arg0);
        }
        catch (Exception e) {
            throw new Exception("Locations pro Kategorie auslesen Fehler");
        }
        result = new locationListResponse(response);
        return result;
    }

    /**
     * This method can be used to call the specified web service operation.
     * @param methodName
     * @return
     */
    private SoapObject executeSoapAction(String methodName, Object... args) throws SoapFault {

        Object result = null;

	    /* Create a org.ksoap2.serialization.SoapObject object to build a SOAP request. Specify the namespace of the SOAP object and method
	     * name to be invoked in the SoapObject constructor.
	     */
        SoapObject request = new SoapObject(NAMESPACE, methodName);

	    /* The array of arguments is copied into properties of the SOAP request using the addProperty method. */
        for (int i=0; i<args.length; i++) {
            request.addProperty("arg" + i, args[i]);
        }

	    /* Next create a SOAP envelop. Use the SoapSerializationEnvelope class, which extends the SoapEnvelop class, with support for SOAP
	     * Serialization format, which represents the structure of a SOAP serialized message. The main advantage of SOAP serialization is portability.
	     * The constant SoapEnvelope.VER11 indicates SOAP Version 1.1, which is default for a JAX-WS webservice endpoint under JBoss.
	     */
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

	    /* Assign the SoapObject request object to the envelop as the outbound message for the SOAP method call. */
        envelope.setOutputSoapObject(request);

	    /* Create a org.ksoap2.transport.HttpTransportSE object that represents a J2SE based HttpTransport layer. HttpTransportSE extends
	     * the org.ksoap2.transport.Transport class, which encapsulates the serialization and deserialization of SOAP messages.
	     */
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        try {
	        /* Make the soap call using the SOAP_ACTION and the soap envelop. */
            List<HeaderProperty> reqHeaders = null;

            @SuppressWarnings({"unused", "unchecked"})
            List<HeaderProperty> respHeaders = androidHttpTransport.call("", envelope, reqHeaders);

	        /* Get the web service response using the getResponse method of the SoapSerializationEnvelope object.
	         * The result has to be cast to SoapPrimitive, the class used to encapsulate primitive types, or to SoapObject.
	         */
            result = envelope.getResponse();

            if (result instanceof SoapFault) {
                throw (SoapFault) result;
            }
        }
        catch (SoapFault e) {
            e.printStackTrace();
            throw e;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return (SoapObject) result;
    }

}
