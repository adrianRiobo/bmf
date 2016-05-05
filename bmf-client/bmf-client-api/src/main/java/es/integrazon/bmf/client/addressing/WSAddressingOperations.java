/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.integrazon.bmf.client.addressing;

import es.integrazon.bmf.client.WSClientException;
import javax.xml.namespace.QName;

/**
 * 
 * @author adrian
 * @param <Request>
 * @param <Response>
 * @param <Callback> 
 */
public interface WSAddressingOperations<Request extends Object, Response extends Object, Callback extends Object> {

    public abstract Response invoke(String action, QName operation, Request request) throws WSClientException;

    public abstract void invokeAsync(String action, QName operation, Request payload, Callback callback) throws WSClientException;

}
