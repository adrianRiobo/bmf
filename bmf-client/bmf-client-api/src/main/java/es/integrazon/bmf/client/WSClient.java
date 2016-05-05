/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.integrazon.bmf.client;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;

/**
 *
 * @author adrian
 */
public abstract class WSClient {

    private final URL wsdlUrl;
    private final QName wsdlServiceName;
    private final String portName;

    public WSClient(String wsdlUrl, String targetNamespace,
            String serviceName, String portName) throws WSClientException {
        try {
            this.wsdlUrl = new URL(wsdlUrl);
            this.wsdlServiceName = new QName(
                    targetNamespace, serviceName);
            this.portName = portName;
        } catch (MalformedURLException ex) {
            throw new WSClientException(ex);
        }
    }

    protected final URL getWsdlUrl() {
        return wsdlUrl;
    }

    protected final QName getWsdlServiceName() {
        return wsdlServiceName;
    }

    protected final String getPortName() {
        return portName;
    }
    
    public abstract String getSOAPVersion();

}
