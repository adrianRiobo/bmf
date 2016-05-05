/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.integrazon.bmf.client.axis2;

import es.integrazon.bmf.client.addressing.WSAddressingOperations;
import es.integrazon.bmf.client.WSClient;
import es.integrazon.bmf.client.WSClientException;
import java.net.URL;
import javax.xml.namespace.QName;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.soap.SOAPBody;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.client.async.AxisCallback;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;

/**
 *
 * @author adrian
 */
public class Axis2WSAddressingClient extends WSClient
        implements WSAddressingOperations<SOAPBody, OMElement, AxisCallback> {

    private final static String ADDRESSING_MODULE = "addressing";
    private final ServiceClient serviceClient;

    public Axis2WSAddressingClient(String wsdlUrl, String targetNamespace,
            String serviceName, String portName) throws WSClientException {

        super(wsdlUrl, targetNamespace, serviceName, portName);
        try {
            serviceClient = new ServiceClient((ConfigurationContext) null,
                    (URL) getWsdlUrl(), (QName) getWsdlServiceName(),
                    (String) getPortName());
            serviceClient.engageModule(ADDRESSING_MODULE);
            serviceClient.getOptions().setCallTransportCleanup(true);
        } catch (AxisFault ex) {
            throw new WSClientException(ex);
        }
    }

    public Axis2WSAddressingClient(String wsdlUrl, String targetNamespace,
            String serviceName, String portName, String user, String password, String address) throws WSClientException {
        this(wsdlUrl, targetNamespace, serviceName, portName);
        HttpTransportProperties.Authenticator basicAuthentication = new HttpTransportProperties.Authenticator();
        basicAuthentication.setUsername("admin");
        basicAuthentication.setPassword("admin");
        serviceClient.getOptions().setProperty(HTTPConstants.AUTHENTICATE, basicAuthentication);
        serviceClient.getOptions().setTo(new EndpointReference(address));
    }

    @Override
    public OMElement invoke(String action, QName operation, SOAPBody payload) throws WSClientException {
        try {
            //Set action for addressing
            serviceClient.getOptions().setAction(action);
            //Just need to send the body content which is the payload
            //the return should be the response payload as well
            //If client is created dynamically it needs to use the sendReceive with
            //operation
            return serviceClient.sendReceive(operation, payload.getFirstElement());
        } catch (AxisFault ex) {
            throw new WSClientException(ex);
        }
    }

    @Override
    public void invokeAsync(String action, QName operation, SOAPBody payload, AxisCallback callback) throws WSClientException {
        try {
            //Set action for addressing
            serviceClient.getOptions().setAction(action);
            //Just need to send the body content which is the payload
            //the return should be the response payload as well
            //If client is created dynamically it needs to use the sendReceive with
            //operation
            serviceClient.sendReceiveNonBlocking(operation, payload.getFirstElement(), callback);
        } catch (AxisFault ex) {
            throw new WSClientException(ex);
        }
    }

    @Override
    public String getSOAPVersion() {
        return serviceClient.getOptions().getSoapVersionURI();
    }

}
