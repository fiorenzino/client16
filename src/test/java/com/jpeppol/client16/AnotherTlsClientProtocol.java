package com.jpeppol.client16;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;

import org.bouncycastle.crypto.tls.TlsClientProtocol;

public class AnotherTlsClientProtocol extends TlsClientProtocol
{

   public AnotherTlsClientProtocol(InputStream arg0, OutputStream arg1, SecureRandom arg2)
   {
      super(arg0, arg1, arg2);
      // TODO Auto-generated constructor stub
   }

   @Override
   protected int readApplicationData(byte[] arg0, int arg1, int arg2) throws IOException
   {
      try
      {
         return super.readApplicationData(arg0, arg1, arg2);
      }
      catch (IOException e)
      {
         throw e;
         // return 0;
      }
   }

   @Override
   protected void safeReadRecord() throws IOException
   {
      try
      {
         super.safeReadRecord();
      }
      catch (IOException e)
      {
         throw e;
      }
   }
}
