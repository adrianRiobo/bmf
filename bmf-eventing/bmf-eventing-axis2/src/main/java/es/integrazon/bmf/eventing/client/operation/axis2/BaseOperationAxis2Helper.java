/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.integrazon.bmf.eventing.client.operation.axis2;

import es.integrazon.bmf.eventing.client.operation.BaseOperationHelper;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.llom.factory.OMLinkedListMetaFactory;
import org.apache.axiom.soap.SOAP11Constants;
import org.apache.axiom.soap.SOAP12Constants;
import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axiom.soap.SOAPFactory;
import org.apache.axiom.soap.SOAPHeader;
import org.apache.axiom.soap.impl.llom.soap11.SOAP11Factory;

/**
 *
 * @author adrian
 */
public class BaseOperationAxis2Helper extends BaseOperationHelper {

    protected final SOAPFactory getSOAPFactory(String SOAPVersion) throws Exception {
        SOAPFactory factory = null;
        if (null != SOAPVersion) {
            switch (SOAPVersion) {
                case SOAP11Constants.SOAP_ENVELOPE_NAMESPACE_URI:
                    factory = OMAbstractFactory.getSOAP12Factory();
                    break;
                case SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI:
                    factory = OMAbstractFactory.getSOAP12Factory();
            }
        }
        if (factory != null) {
            return factory;
        } else {
            throw new Exception("Unknown SOAP version");
        }
    }
    
    protected final SOAPHeader createSOAPHeader(SOAPFactory factory, OMElement content) {
        SOAPHeader result = factory.createSOAPHeader();
        result.addChild(content);
        return result;
    }

    protected final SOAPEnvelope createSOAPEnvelope(SOAPFactory factory, OMElement bodyContent) {
        SOAPEnvelope envelope = factory.getDefaultEnvelope();
        envelope.getBody().addChild(bodyContent);
        return envelope;
    }

}
