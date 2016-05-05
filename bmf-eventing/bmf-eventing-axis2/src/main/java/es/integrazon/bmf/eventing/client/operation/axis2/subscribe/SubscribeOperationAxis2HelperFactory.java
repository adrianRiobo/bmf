/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.integrazon.bmf.eventing.client.operation.axis2.subscribe;

import es.integrazon.bmf.eventing.client.operation.subscribe.SubscribeOperationHelper;
import es.integrazon.bmf.eventing.client.operation.subscribe.SubscribeOperationHelperFactory;

/**
 *
 * @author adrian
 */
public final class SubscribeOperationAxis2HelperFactory implements SubscribeOperationHelperFactory{
    
    @Override
    public SubscribeOperationHelper getSubscribeOperationHelper() {
        return new SubscribeOperationAxis2Helper();
    }

}
