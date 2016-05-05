/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.integrazon.bmf.client.axis2;

import es.integrazon.bmf.client.WSClientException;
import es.integrazon.bmf.client.factory.*;

/**
 *
 * @author adrian
 */
public class Axis2WSAddresingClientFactory implements WSClientFactory<Axis2WSAddressingClient> {

    private final String wsdlUrl;
    private final String targetNamespace;
    private final String serviceName;
    private final String portName;
    private String user;
    private String password;
    private String address;
    private String soapVersion = null;

    Axis2WSAddresingClientFactory(String wsdlUrl, String targetNamespace,
            String serviceName, String portName) {
        this.wsdlUrl = wsdlUrl;
        this.targetNamespace = targetNamespace;
        this.serviceName = serviceName;
        this.portName = portName;
    }

    Axis2WSAddresingClientFactory(String wsdlUrl, String targetNamespace,
            String serviceName, String portName, String user, String password, String address) {
        this(wsdlUrl, targetNamespace, serviceName, portName);
        this.user = user;
        this.password = password;
        this.address = address;
    }
    
    

    @Override
    public Axis2WSAddressingClient getClient() throws WSClientException {
        Axis2WSAddressingClient client;
        if (user != null && password != null && address != null) {
            client = new Axis2WSAddressingClient(wsdlUrl, targetNamespace, serviceName, portName, user, password, address);
        } else {
            client = new Axis2WSAddressingClient(wsdlUrl, targetNamespace, serviceName, portName);
        }
        soapVersion = client.getSOAPVersion();
        return client;
    }

    @Override
    public String getSOAPVersion() {
        return soapVersion;
    }

}
