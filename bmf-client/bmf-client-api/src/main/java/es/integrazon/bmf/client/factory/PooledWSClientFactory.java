/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.integrazon.bmf.client.factory;

import es.integrazon.bmf.client.WSClient;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * 
 * @author adrian
 * @param <T> 
 */
public class PooledWSClientFactory<T extends WSClient> extends BasePooledObjectFactory {

    private final WSClientFactory<T> wsClientFactory;

    public PooledWSClientFactory(WSClientFactory wsClientFactory) {
        this.wsClientFactory = wsClientFactory;
    }

    @Override
    public T create() throws Exception {
        return wsClientFactory.getClient();
    }

    @Override
    public PooledObject wrap(Object t) {
        return new DefaultPooledObject<>(t);
    }

}
