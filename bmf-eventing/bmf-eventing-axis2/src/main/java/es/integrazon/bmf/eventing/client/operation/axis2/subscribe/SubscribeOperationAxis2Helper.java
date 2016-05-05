/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.integrazon.bmf.eventing.client.operation.axis2.subscribe;

import es.integrazon.bmf.eventing.EventingConstants;
import es.integrazon.bmf.eventing.client.operation.axis2.BaseOperationAxis2Helper;
import es.integrazon.bmf.eventing.client.operation.subscribe.SubscribeOperationHelper;
import javax.xml.namespace.QName;
import org.apache.axiom.om.OMAttribute;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axiom.soap.SOAPFactory;
import org.apache.axis2.addressing.AddressingConstants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.addressing.EndpointReferenceHelper;
import org.w3.ws.addressing.EndpointReferenceType;
import org.w3.ws.eventing.DeliveryType;
import org.w3.ws.eventing.ExpirationType;
import org.w3.ws.eventing.FilterType;
import org.w3.ws.eventing.MiniExpirationType;
import org.w3.ws.eventing.Subscribe;
import org.w3.ws.eventing.SubscribeResponse;

/**
 *
 * @author adrian
 */
public class SubscribeOperationAxis2Helper extends BaseOperationAxis2Helper implements SubscribeOperationHelper<SOAPEnvelope, OMElement> {

    @Override
    public SOAPEnvelope createSubscriptionEnvelope(Subscribe bean, String SOAPVersion)
            throws Exception {
        SOAPFactory factory = getSOAPFactory(SOAPVersion);
        OMNamespace eventingNamespace = factory.createOMNamespace(EventingConstants.EVENTING_NAMESPACE,
                EventingConstants.EVENTING_PREFIX);
        SOAPEnvelope subscriptionElement = createRequestContent(bean, factory, eventingNamespace);
        return subscriptionElement;
//        return createSOAPEnvelope(factory, subscriptionElement);
    }

    private SOAPEnvelope getSubscriptionElement(SOAPFactory factory, OMNamespace eventingNamespace) {
        return factory.createSOAPEnvelope(eventingNamespace);
    }

    private void addEndTo(Subscribe bean, OMElement subscriptionElement, SOAPFactory factory, OMNamespace eventingNamespace) throws Exception {
        EndpointReferenceType endToEPR = bean.getEndTo();
        if (endToEPR != null) {
            OMElement addressElement = factory.createOMElement(AddressingConstants.Final.WSA_ADDRESS);
            addressElement.setText(endToEPR.getAddress().getValue());
            OMElement endToElement = factory.createOMElement(
                    EventingConstants.ElementNames.EndTo, eventingNamespace);
            endToElement.addChild(addressElement);
            subscriptionElement.addChild(endToElement);
        }
    }

    //TODO add n deliveries
    private void addDelivery(DeliveryType delivery, OMElement subscriptionElement, SOAPFactory factory, OMNamespace eventingNamespace)
            throws Exception {
        if (delivery != null) {
            OMElement addressElement = factory.createOMElement(AddressingConstants.Final.WSA_ADDRESS);
            addressElement.setText((String) delivery.getContent().get(0));
            OMElement notifyToElement = factory.createOMElement(
                    EventingConstants.ElementNames.NotifyTo, eventingNamespace);
            notifyToElement.addChild(addressElement);
            OMElement deliveryElement = factory.createOMElement(
                    EventingConstants.ElementNames.Delivery, eventingNamespace);
            deliveryElement.addChild(notifyToElement);
            subscriptionElement.addChild(deliveryElement);
        } else {
            throw new Exception("Delivery EPR is not set");
        }
    }

    private void addExpiration(ExpirationType expirationTime, OMElement subscriptionElement, SOAPFactory factory, OMNamespace eventingNamespace) {
        if (expirationTime != null && expirationTime.getValue() != null) {
            OMElement expiresElement = factory.createOMElement(
                    EventingConstants.ElementNames.Expires, eventingNamespace);
            expiresElement.setText(expirationTime.getValue());
            subscriptionElement.addChild(expiresElement);
        }
    }

    //TODO add n filters
    private void addFilter(FilterType filter, OMElement subscriptionElement, SOAPFactory factory, OMNamespace eventingNamespace) {
        if (filter != null) {
            OMElement filterElement
                    = factory.createOMElement(EventingConstants.ElementNames.Filter, eventingNamespace);
            OMAttribute dialectAttr = factory.createOMAttribute(
                    EventingConstants.ElementNames.Dialect, null, filter.getDialect());
            filterElement.addAttribute(dialectAttr);
            filterElement.setText((String) filter.getContent().get(0));
            subscriptionElement.addChild(filterElement);
        }
    }

    private SOAPEnvelope createRequestContent(Subscribe bean, SOAPFactory factory, OMNamespace eventingNamespace) throws Exception {
        SOAPEnvelope subscription = getSubscriptionElement(factory, eventingNamespace);
        factory.createSOAPBody(subscription);
        OMElement subscriptionElement
                = factory.createOMElement(EventingConstants.ElementNames.Subscribe, eventingNamespace);
        addEndTo(bean, subscriptionElement, factory, eventingNamespace);
        addDelivery(bean.getDelivery(), subscriptionElement, factory, eventingNamespace);
        addExpiration(bean.getExpires(), subscriptionElement, factory, eventingNamespace);
        addFilter(bean.getFilter(), subscriptionElement, factory, eventingNamespace);
        subscription.getBody().addChild(subscriptionElement);
        return subscription;
    }

    @Override
    public SubscribeResponse getSubscriptionResponseData(OMElement bodyContent) throws Exception {
        SubscribeResponse result = new SubscribeResponse();
        OMElement subscriberManagerElement = bodyContent.getFirstChildWithName(new QName(
                EventingConstants.EVENTING_NAMESPACE,
                EventingConstants.ElementNames.SubscriptionManager));
        EndpointReference managerEPR = EndpointReferenceHelper.fromOM(subscriberManagerElement);
        result.setSubscriptionManager(convert(managerEPR));
        OMElement expiresElement = bodyContent.getFirstChildWithName(EventingConstants.EXPIRES_QNAME);
        if (expiresElement != null) {
            MiniExpirationType miniExpirationType = new MiniExpirationType();
            miniExpirationType.setValue(expiresElement.getText().trim());
            result.setGrantedExpires(miniExpirationType);
        }
        return result;
    }

}
