/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.integrazon.bmf.eventing.client.action;

import es.integrazon.bmf.eventing.EventingConstants;
import javax.xml.namespace.QName;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.soap.SOAP11Constants;
import org.apache.axiom.soap.SOAP12Constants;
import org.apache.axiom.soap.SOAPBody;
import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axiom.soap.SOAPFactory;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;

/**
 *
 * @author adrian
 */
public class GetSubscriptionStatusAction extends BaseEventingAction {

    public GetSubscriptionStatusAction(ServiceClient getServiceClient) {
        super(getServiceClient);
    }

//    public SubscriptionStatus getSubscriptionStatus(SubscriptionResponseData data) throws Exception {
//
//        EndpointReference managerEPR = data.getSubscriptionManager();
//        if (managerEPR == null) {
//            throw new Exception("Manager EPR is not set");
//        }
//
//        Options options = getServiceClient().getOptions();
//        if (options == null) {
//            options = new Options();
//            getServiceClient().setOptions(options);
//        }
//
//        String SOAPVersion = options.getSoapVersionURI();
//        if (SOAPVersion == null) {
//            SOAPVersion = SOAP11Constants.SOAP_ENVELOPE_NAMESPACE_URI;
//        }
//
//        SOAPEnvelope envelope = createGetStatusEnvelope(SOAPVersion);
//
//        String oldAction = options.getAction();
//        String action = EventingConstants.Actions.GetStatus;
//        options.setAction(action);
//
//        EndpointReference oldTo = getServiceClient().getOptions().getTo();
//        options.setTo(managerEPR);
//
//        OMElement getStatusResponse
//                = getServiceClient().sendReceive(envelope.getBody().getFirstElement());
//        SubscriptionStatus subscriptionStatus = getSubscriptionStatus(getStatusResponse);
//
//        options.setAction(oldAction);
//        options.setTo(oldTo);
//
//        return subscriptionStatus;
//    }
//
//    private SubscriptionStatus getSubscriptionStatus(OMElement getStatusResponseElement)
//            throws Exception {
//        SubscriptionStatus subscriptionStatus = new SubscriptionStatus();
//
//        OMElement expiresElementElement = getStatusResponseElement.getFirstChildWithName(new QName(
//                EventingConstants.EVENTING_NAMESPACE, EventingConstants.ElementNames.Expires));
//        if (expiresElementElement != null) {
//            String valueStr = expiresElementElement.getText();
////			long expires = Long.parseLong(valueStr);
//            subscriptionStatus.setExpirationValue(valueStr);
//        }
//
//        return subscriptionStatus;
//    }
//
//    private SOAPEnvelope createGetStatusEnvelope(String SOAPVersion) throws Exception {
//        SOAPFactory factory = null;
//
//        if (SOAP11Constants.SOAP_ENVELOPE_NAMESPACE_URI.equals(SOAPVersion)) {
//            factory = OMAbstractFactory.getSOAP11Factory();
//        } else if (SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI.equals(SOAPVersion)) {
//            factory = OMAbstractFactory.getSOAP12Factory();
//        } else {
//            throw new Exception("Unknown SOAP version");
//        }
//
//        SOAPEnvelope envelope = factory.getDefaultEnvelope();
//        SOAPBody body = envelope.getBody();
//
//        OMNamespace ens = factory.createOMNamespace(EventingConstants.EVENTING_NAMESPACE,
//                EventingConstants.EVENTING_PREFIX);
//        OMElement getStatusElement
//                = factory.createOMElement(EventingConstants.ElementNames.GetStatus, ens);
//        body.addChild(getStatusElement);
//
//        return envelope;
//    }

}
