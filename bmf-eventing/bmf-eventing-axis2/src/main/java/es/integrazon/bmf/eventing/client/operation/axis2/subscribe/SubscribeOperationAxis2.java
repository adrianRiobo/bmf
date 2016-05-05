/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.integrazon.bmf.eventing.client.operation.axis2.subscribe;

import es.integrazon.bmf.client.manager.WSAddressingClientManager;
import es.integrazon.bmf.eventing.EventingConstants;
import es.integrazon.bmf.eventing.client.operation.BaseWSEventingOperation;
import es.integrazon.bmf.eventing.client.operation.subscribe.SubscribeOperationHelper;
import es.integrazon.bmf.eventing.client.operation.subscribe.SubscribeOperationHelperFactory;
import javax.xml.namespace.QName;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.soap.SOAPBody;
import org.apache.axiom.soap.SOAPEnvelope;
import org.w3.ws.eventing.Subscribe;
import org.w3.ws.eventing.SubscribeResponse;

/**
 *
 * @author adrian
 */
public class SubscribeOperationAxis2
        extends BaseWSEventingOperation<Subscribe, SubscribeResponse, SOAPBody, OMElement> {

    private final SubscribeOperationHelper<SOAPEnvelope, Object> subscribeOperationHelper;

    public SubscribeOperationAxis2(WSAddressingClientManager client, SubscribeOperationHelperFactory fatory) {
        super(client, EventingConstants.Actions.Subscribe, new QName("http://ws.apache.org/axis2", "subscribe"));
        subscribeOperationHelper = fatory.getSubscribeOperationHelper();
    }

    @Override
    protected SOAPBody createRequest(Subscribe source) throws Exception {
        //TODO revisar si el el getFirstElement
        return subscribeOperationHelper.createSubscriptionEnvelope(source, getClient().getSoapVersion()).getBody();
    }

    @Override
    protected SubscribeResponse extractResponse(OMElement source) throws Exception {
        return subscribeOperationHelper.getSubscriptionResponseData(source);
    }

}
