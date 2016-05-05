/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.integrazon.bmf.eventing.client.operation;

import org.apache.axis2.addressing.EndpointReference;
import org.w3.ws.addressing.AttributedURIType;
import org.w3.ws.addressing.EndpointReferenceType;
import org.w3.ws.eventing.DeliveryType;

/**
 *
 * @author adrian
 */
public class BaseOperationHelper {

    protected final EndpointReference convert(EndpointReferenceType source) {
        return new EndpointReference(source.getAddress().getValue());
    }

    //TODO convertir resto de la info
    protected final EndpointReferenceType convert(EndpointReference source) {
        EndpointReferenceType endpointReferenceType = new EndpointReferenceType();
        AttributedURIType address = new AttributedURIType();
        address.setValue(source.getAddress());
        endpointReferenceType.setAddress(address);
        return endpointReferenceType;
    }

    protected final EndpointReference convert(DeliveryType source) {
        return new EndpointReference((String) source.getContent().get(0));
    }

}
