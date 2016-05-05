/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.integrazon.bmf.client.axis2;

import es.integrazon.bmf.client.factory.PooledWSClientFactory;
import es.integrazon.bmf.client.manager.WSAddressingClientManager;
import org.apache.axiom.om.OMElement;
import org.apache.axis2.client.async.AxisCallback;
import org.apache.commons.pool2.impl.GenericObjectPool;

/**
 *
 * @author adrian
 */
public class Axis2WSAddresingClientManager extends WSAddressingClientManager<OMElement, OMElement, AxisCallback, Axis2WSAddressingClient> {

    public Axis2WSAddresingClientManager(Axis2WSAddresingClientFactory factory) {
        super(new GenericObjectPool<Axis2WSAddressingClient>(
                new PooledWSClientFactory<Axis2WSAddressingClient>(factory)));
    }

    public Axis2WSAddresingClientManager(String wsdlUrl, String targetNamespace,
            String serviceName, String portName) {
        this(new Axis2WSAddresingClientFactory(wsdlUrl, targetNamespace,
                serviceName, portName));
    }

    public Axis2WSAddresingClientManager(String wsdlUrl, String targetNamespace,
            String serviceName, String portName, String user, String password, String address) {
        this(new Axis2WSAddresingClientFactory(wsdlUrl, targetNamespace,
                serviceName, portName, user, password, address));
    }
}
