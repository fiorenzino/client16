package com.jpeppol.client16;

import org.apache.axis.components.net.BooleanHolder;
import org.apache.axis.components.net.JSSESocketFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import java.net.Socket;
import java.util.Hashtable;

import static javax.net.ssl.SSLContext.getInstance;

/**
 * Created by fiorenzo on 10/06/16.
 */
public class MyTLSSocketSecureFactory extends JSSESocketFactory {

    public MyTLSSocketSecureFactory(Hashtable attributes) {
        super(attributes);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Socket create(String host, int port, StringBuffer otherHeaders, BooleanHolder useFullURL)
            throws Exception {
        Socket s = super.create(host, port, otherHeaders, useFullURL);
        ((SSLSocket) s).setEnabledProtocols(new String[]{ "TLSv1", "TLSv1.1", "TLSv1.2"});
        return s;
    }

    @Override
    protected void initFactory() {
        try {
            SSLContext context = getInstance("TLSv1.2");
            context.init(null, null, null);
            sslFactory = context.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}