package com.jpeppol.client16;

import com.jpeppol.frontendws.service.ws.FrontendWs;
import com.jpeppol.frontendws.service.ws.FrontendWsService;
import com.jpeppol.frontendws.service.ws.FrontendWsServiceLocator;

import org.apache.axis.AxisProperties;
import org.apache.axis.client.Call;
import org.apache.axis.client.Stub;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Test;

import java.io.RandomAccessFile;
import java.lang.management.ManagementFactory;
import java.security.Security;

/**
 * Created by fiorenzo on 10/06/16.
 */
public class TestWs
{

   String senderID = "IT12345678";
   String recipientID = "IT12345679";
   String documentType = "SDI";
   String relatedId = "PROVA JDK16";
   String fileName = "docs/file.xml";

   @Test
   public void test() throws Exception
   {

      System.setProperty("https.protocols", "TLSv1.2");
      Security.addProvider(new BouncyCastleProvider());

      AxisProperties.setProperty("axis.socketSecureFactory", MyTLSSocketSecureFactory.class.getName());

      System.out.println("java.version=" + System.getProperty("java.version"));

      System.out.println("vm.version=" + ManagementFactory.getRuntimeMXBean().getVmVersion());

      FrontendWsService frontendWsService = new FrontendWsServiceLocator();
      FrontendWs frontendWs = frontendWsService.getFrontendWsPort();
      ((Stub) frontendWs)._setProperty(Call.USERNAME_PROPERTY, "helpdesk");
      ((Stub) frontendWs)._setProperty(Call.PASSWORD_PROPERTY, "admin");
      frontendWs.upload(senderID, recipientID, documentType, relatedId, readFile(fileName));
   }

   private byte[] readFile(String fileName) throws Exception
   {
      RandomAccessFile f = new RandomAccessFile(fileName, "r");
      byte[] b = new byte[(int) f.length()];
      f.read(b);
      return b;
   }

}
