/*
 * Copyright  1999-2004 The Apache Software Foundation.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package es.integrazon.bmf.eventing.client.operation;

import es.integrazon.bmf.client.manager.WSAddressingClientManager;
import javax.xml.namespace.QName;

/**
 *
 * @author adrian
 * @param <Input>
 * @param <Output>
 * @param <Request>
 * @param <Response>
 */
public abstract class BaseWSEventingOperation<Input extends Object, Output extends Object, Request extends Object, Response extends Object> {

    private final WSAddressingClientManager client;
    private final String action;
    private final QName operation;

    public BaseWSEventingOperation(WSAddressingClientManager client, String action, QName operation) {
        this.client = client;
        this.action = action;
        this.operation = operation;

    }

    protected final WSAddressingClientManager getClient() {
        return client;
    }

    public Output doOperation(Input source) throws Exception {
        Request request = createRequest(source);
        return extractResponse((Response) getClient().invoke(action, operation, request));
    }

    protected abstract Request createRequest(Input source) throws Exception;

    protected abstract Output extractResponse(Response source) throws Exception;

}
