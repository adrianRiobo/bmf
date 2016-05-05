/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.integrazon.bmf.eventing.client.action;

import org.apache.axis2.client.ServiceClient;

/**
 *
 * @author adrian
 */
public abstract class BaseEventingAction {

    private ServiceClient serviceClient;

    public BaseEventingAction(ServiceClient getServiceClient) {
        this.serviceClient = getServiceClient;
    }

    protected final ServiceClient getServiceClient() {
        return serviceClient;
    }

}
