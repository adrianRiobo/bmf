/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.integrazon.bmf.eventing.client.operation.subscribe;

import org.w3.ws.eventing.Subscribe;
import org.w3.ws.eventing.SubscribeResponse;

/**
 *
 * @author adrian
 * @param <Request>
 * @param <Response>
 */
public interface SubscribeOperationHelper<Request extends Object, Response extends Object> {

    Request createSubscriptionEnvelope(Subscribe bean, String SOAPVersion)
            throws Exception;

    SubscribeResponse getSubscriptionResponseData(Response response)
            throws Exception;

}
