package com.jpeppol.client16;

import org.apache.axis.components.net.BooleanHolder;
import org.apache.axis.components.net.JSSESocketFactory;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;

import java.net.Socket;
import java.util.Hashtable;

import static javax.net.ssl.SSLContext.getInstance;

/**
 * Created by fiorenzo on 10/06/16.
 */
public class AnotherTlsSocketSecureFactory extends JSSESocketFactory
{

   public AnotherTlsSocketSecureFactory(Hashtable attributes)
   {
      super(attributes);
      // TODO Auto-generated constructor stub
   }

   // @Override
   // public Socket create(String host, int port, StringBuffer otherHeaders, BooleanHolder useFullURL)
   // throws Exception {
   // Socket s = super.create(host, port, otherHeaders, useFullURL);
   // ((SSLSocket) s).setEnabledProtocols(new String[]{ "TLSv1", "TLSv1.1", "TLSv1.2"});
   // return s;
   // }

   @Override
   protected void initFactory()
   {
      try
      {
//         SSLContext context = getInstance("TLSv1.2", new BouncyCastleProvider());
//         context.init(null, null, null);

         sslFactory = new AnotherTlsSocketConnectionFactory();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }

   }
}
