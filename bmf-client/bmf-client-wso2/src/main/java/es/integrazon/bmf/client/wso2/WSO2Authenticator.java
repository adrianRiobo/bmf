/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.integrazon.bmf.client.wso2;

import es.integrazon.bmf.client.WSClientException;
import java.rmi.RemoteException;
import org.apache.axis2.AxisFault;
import org.apache.axis2.context.ServiceContext;
import org.apache.axis2.transport.http.HTTPConstants;
import org.wso2.carbon.authenticator.stub.AuthenticationAdminStub;
import org.wso2.carbon.authenticator.stub.LoginAuthenticationExceptionException;
import org.wso2.carbon.authenticator.stub.LogoutAuthenticationExceptionException;

/**
 *
 * @author adrian
 */
public class WSO2Authenticator {

    private final String AUTH_SERVICE_NAME = "AuthenticationAdmin";
    private String endPoint;
    private AuthenticationAdminStub authenticationAdminStub;
   

    public WSO2Authenticator(String backEndUrl, String user, String password, String serviceName) throws WSClientException {
        try {
            this.endPoint = backEndUrl + "/services/" + AUTH_SERVICE_NAME;
            this.authenticationAdminStub = new AuthenticationAdminStub(endPoint);
        } catch (AxisFault ex) {
            throw new WSClientException(ex);
        }
    }

    public String authenticate(String userName, String password) throws WSClientException {
        try {
            authenticationAdminStub.login(userName, password, "localhost");
            ServiceContext serviceContext = authenticationAdminStub.
                    _getServiceClient().getLastOperationContext().getServiceContext();
            return (String) serviceContext.getProperty(HTTPConstants.COOKIE_STRING);
        } catch (LoginAuthenticationExceptionException | RemoteException ex) {
            throw new WSClientException(ex);
        }
    }

    public void logOut() throws WSClientException {
        try {
            authenticationAdminStub.logout();
        } catch (RemoteException | LogoutAuthenticationExceptionException ex) {
            throw new WSClientException(ex);
        }
    }

}
