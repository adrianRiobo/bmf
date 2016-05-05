/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.integrazon.bmf.client.manager;

import es.integrazon.bmf.client.WSClient;
import es.integrazon.bmf.client.addressing.WSAddressingOperations;
import es.integrazon.bmf.client.WSClientException;
import javax.xml.namespace.QName;
import org.apache.commons.pool2.ObjectPool;

/**
 *
 * @author adrian
 * @param <Request>
 * @param <Response>
 * @param <Callback>
 * @param <WSAddressingClient>
 */
public abstract class WSAddressingClientManager<Request extends Object, Response extends Object, Callback extends Object, WSAddressingClient extends WSAddressingOperations>
        implements WSAddressingOperations<Request, Response, Callback> {

    private ObjectPool<WSAddressingClient> pool;
    private String soapVersion;

    public WSAddressingClientManager(ObjectPool<WSAddressingClient> pool) {
        this.pool = pool;
    }

    @Override
    public Response invoke(String action, QName operation, Request request) throws WSClientException {
        try {
            WSAddressingClient client = pool.borrowObject();
            Response result = (Response) client.invoke(action, operation, request);
            pool.returnObject(client);
            return result;
        } catch (Exception ex) {
            throw new WSClientException(ex);
        }
    }

    @Override
    public void invokeAsync(String action, QName operation, Request payload, Callback callback) throws WSClientException {
        try {
            WSAddressingClient client = pool.borrowObject();
            client.invokeAsync(action, operation, payload, callback);
            pool.returnObject(client);
        } catch (Exception ex) {
            throw new WSClientException(ex);
        }
    }

    public final String getSoapVersion() throws Exception {
        if (soapVersion == null) {
            WSAddressingClient client = pool.borrowObject();
            soapVersion =  ((WSClient) client).getSOAPVersion();
            pool.returnObject(client);
        }
        return soapVersion;
    }

    
}
