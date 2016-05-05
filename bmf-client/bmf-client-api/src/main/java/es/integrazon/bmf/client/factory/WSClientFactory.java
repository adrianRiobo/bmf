/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.integrazon.bmf.client.factory;

import es.integrazon.bmf.client.WSClient;
import es.integrazon.bmf.client.WSClientException;

/**
 * 
 * @author adrian
 * @param <T> 
 */
public interface WSClientFactory<T extends WSClient> {

    T getClient() throws WSClientException;
    
    String getSOAPVersion();

}
