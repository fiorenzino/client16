package com.jpeppol.client16;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.security.Principal;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSessionContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.security.cert.X509Certificate;

import org.bouncycastle.crypto.tls.Certificate;
import org.bouncycastle.crypto.tls.CertificateRequest;
import org.bouncycastle.crypto.tls.DefaultTlsClient;
import org.bouncycastle.crypto.tls.ExtensionType;
import org.bouncycastle.crypto.tls.TlsAuthentication;
import org.bouncycastle.crypto.tls.TlsClientProtocol;
import org.bouncycastle.crypto.tls.TlsCredentials;
import org.bouncycastle.crypto.tls.TlsECCUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class AnotherTlsSocketConnectionFactory

         extends SSLSocketFactory

{

   // ////////////////////////////////////////////////////////////////////////////////////////////////////////////
   // Adding Custom BouncyCastleProvider
   // /////////////////////////////////////////////////////////////////////////////////////////////////////////////

   static
   {
      if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null)
      {
         Security.addProvider(new BouncyCastleProvider());
      }
   }

   // ////////////////////////////////////////////////////////////////////////////////////////////////////////////
   // SECURE RANDOM
   // ////////////////////////////////////////////////////////////////////////////////////////////////////////////

   private SecureRandom _secureRandom = new SecureRandom();

   // ////////////////////////////////////////////////////////////////////////////////////////////////////////////
   // Adding Custom BouncyCastleProvider
   // /////////////////////////////////////////////////////////////////////////////////////////////////////////////

   @Override
   public Socket createSocket(Socket socket, final String host, int port, boolean arg3)
            throws IOException
   {
      if (socket == null)
      {
         socket = new Socket();
      }
      if (!socket.isConnected())
      {
         socket.connect(new InetSocketAddress(host, port));
      }

      final TlsClientProtocol tlsClientProtocol = new AnotherTlsClientProtocol(socket.getInputStream(),
               socket.getOutputStream(), _secureRandom);

      return _createSSLSocket(host, tlsClientProtocol);

   }

   // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   // SOCKET FACTORY METHODS
   // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

   @Override
   public String[] getDefaultCipherSuites()
   {
      return null;
   }

   @Override
   public String[] getSupportedCipherSuites()
   {
      return null;
   }

   @Override
   public Socket createSocket(String host,
            int port) throws IOException, UnknownHostException
   {
      Socket socket = new Socket();
      socket.connect(new InetSocketAddress(host, port));

      final TlsClientProtocol tlsClientProtocol = new AnotherTlsClientProtocol(socket.getInputStream(),
               socket.getOutputStream(), _secureRandom);

      return _createSSLSocket(host, tlsClientProtocol);
   }

   @Override
   public Socket createSocket(InetAddress host,
            int port) throws IOException
   {
      return null;
   }

   @Override
   public Socket createSocket(String host,
            int port,
            InetAddress localHost,
            int localPort) throws IOException, UnknownHostException
   {
      return null;
   }

   @Override
   public Socket createSocket(InetAddress address,
            int port,
            InetAddress localAddress,
            int localPort) throws IOException
   {
      return null;
   }

   // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   // SOCKET CREATION
   // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

   private SSLSocket _createSSLSocket(final String host, final TlsClientProtocol tlsClientProtocol)
   {
      SSLSocket sslsocket = new SSLSocket()
      {
         private java.security.cert.Certificate[] peertCerts;

         @Override
         public InputStream getInputStream() throws IOException
         {
            return tlsClientProtocol.getInputStream();
         }

         @Override
         public OutputStream getOutputStream() throws IOException
         {
            return tlsClientProtocol.getOutputStream();
         }

         @Override
         public synchronized void close() throws IOException
         {
            // Log.to("util").info("\\\n::::::Close Socket");
            tlsClientProtocol.close();
         }

         @Override
         public void addHandshakeCompletedListener(HandshakeCompletedListener arg0)
         {

         }

         @Override
         public boolean getEnableSessionCreation()
         {
            return false;
         }

         @Override
         public String[] getEnabledCipherSuites()
         {
            return null;
         }

         @Override
         public String[] getEnabledProtocols()
         {
            return null;
         }

         @Override
         public boolean getNeedClientAuth()
         {
            return false;
         }

         @Override
         public SSLSession getSession()
         {
            return new SSLSession()
            {

               @Override
               public int getApplicationBufferSize()
               {
                  return 0;
               }

               @Override
               public String getCipherSuite()
               {
                  throw new UnsupportedOperationException();
               }

               @Override
               public long getCreationTime()
               {
                  throw new UnsupportedOperationException();
               }

               @Override
               public byte[] getId()
               {
                  throw new UnsupportedOperationException();
               }

               @Override
               public long getLastAccessedTime()
               {
                  throw new UnsupportedOperationException();
               }

               @Override
               public java.security.cert.Certificate[] getLocalCertificates()
               {
                  throw new UnsupportedOperationException();
               }

               @Override
               public Principal getLocalPrincipal()
               {
                  throw new UnsupportedOperationException();
               }

               @Override
               public int getPacketBufferSize()
               {
                  throw new UnsupportedOperationException();
               }

               @Override
               public X509Certificate[] getPeerCertificateChain()
                        throws SSLPeerUnverifiedException
               {
                  return null;
               }

               @Override
               public java.security.cert.Certificate[] getPeerCertificates() throws SSLPeerUnverifiedException
               {
                  return peertCerts;
               }

               @Override
               public String getPeerHost()
               {
                  throw new UnsupportedOperationException();
               }

               @Override
               public int getPeerPort()
               {
                  return 0;
               }

               @Override
               public Principal getPeerPrincipal() throws SSLPeerUnverifiedException
               {
                  return null;
                  // throw new UnsupportedOperationException();
               }

               @Override
               public String getProtocol()
               {
                  throw new UnsupportedOperationException();
               }

               @Override
               public SSLSessionContext getSessionContext()
               {
                  throw new UnsupportedOperationException();
               }

               @Override
               public Object getValue(String arg0)
               {
                  throw new UnsupportedOperationException();
               }

               @Override
               public String[] getValueNames()
               {
                  throw new UnsupportedOperationException();
               }

               @Override
               public void invalidate()
               {
                  throw new UnsupportedOperationException();
               }

               @Override
               public boolean isValid()
               {
                  throw new UnsupportedOperationException();
               }

               @Override
               public void putValue(String arg0, Object arg1)
               {
                  throw new UnsupportedOperationException();
               }

               @Override
               public void removeValue(String arg0)
               {
                  throw new UnsupportedOperationException();
               }

            };
         }

         @Override
         public String[] getSupportedProtocols()
         {
            return null;
         }

         @Override
         public boolean getUseClientMode()
         {
            return false;
         }

         @Override
         public boolean getWantClientAuth()
         {
            return false;
         }

         @Override
         public void removeHandshakeCompletedListener(HandshakeCompletedListener arg0)
         {

         }

         @Override
         public void setEnableSessionCreation(boolean arg0)
         {

         }

         @Override
         public void setEnabledCipherSuites(String[] arg0)
         {

         }

         @Override
         public void setEnabledProtocols(String[] arg0)
         {

         }

         @Override
         public void setNeedClientAuth(boolean arg0)
         {

         }

         @Override
         public void setUseClientMode(boolean arg0)
         {

         }

         @Override
         public void setWantClientAuth(boolean arg0)
         {

         }

         @Override
         public String[] getSupportedCipherSuites()
         {
            return null;
         }

         @Override
         public void startHandshake() throws IOException
         {

            // Log.to("util").info("TSLSocketConnectionFactory:startHandshake()");
            tlsClientProtocol.connect(new DefaultTlsClient()
            {
               @Override
               public void notifyAlertRaised(short arg0, short arg1, String arg2, Throwable arg3)
               {
                  super.notifyAlertRaised(arg0, arg1, arg2, arg3);
               }

               @Override
               public void notifyAlertReceived(short arg0, short arg1)
               {
                  // TODO Auto-generated method stub
                  super.notifyAlertReceived(arg0, arg1);
               }

               protected boolean allowUnexpectedServerExtension(Integer extensionType, byte[] extensionData)
                        throws IOException
               {
                  switch (extensionType.intValue())
                  {
                  case ExtensionType.ec_point_formats:
                     /*
                      * Exception added based on field reports that some servers send Supported Point Format Extension
                      * even when not negotiating an ECC cipher suite. If present, we still require that it is a valid
                      * ECPointFormatList.
                      */
                     TlsECCUtils.readSupportedPointFormatsExtension(extensionData);
                     return true;
                  default:
                     return super.allowUnexpectedServerExtension(extensionType, extensionData);
                  }
               }

               @SuppressWarnings("unchecked")
               @Override
               public Hashtable<Integer, byte[]> getClientExtensions() throws IOException
               {
                  Hashtable<Integer, byte[]> clientExtensions = super.getClientExtensions();
                  if (clientExtensions == null)
                  {
                     clientExtensions = new Hashtable<Integer, byte[]>();
                  }

                  // Add host_name
                  byte[] host_name = host.getBytes();

                  final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                  final DataOutputStream dos = new DataOutputStream(baos);
                  dos.writeShort(host_name.length + 3);
                  dos.writeByte(0); //
                  dos.writeShort(host_name.length);
                  dos.write(host_name);
                  dos.close();
                  clientExtensions.put(ExtensionType.server_name, baos.toByteArray());
                  return clientExtensions;
               }

               @Override
               public TlsAuthentication getAuthentication()
                        throws IOException
               {
                  return new TlsAuthentication()
                  {

                     @Override
                     public void notifyServerCertificate(Certificate serverCertificate) throws IOException
                     {

                        try
                        {
                           KeyStore ks = _loadKeyStore();
                           // Log.to("util").info(">>>>>>>> KeyStore : " + ks.size());

                           CertificateFactory cf = CertificateFactory.getInstance("X.509");
                           List<java.security.cert.Certificate> certs = new LinkedList<java.security.cert.Certificate>();
                           boolean trustedCertificate = false;
                           for (org.bouncycastle.asn1.x509.Certificate c : serverCertificate.getCertificateList())
                           {
                              java.security.cert.Certificate cert = cf.generateCertificate(new ByteArrayInputStream(c
                                       .getEncoded()));
                              certs.add(cert);

                              String alias = ks.getCertificateAlias(cert);
                              if (alias != null)
                              {
                                 // Log.to("util").info(">>> Trusted cert\n" + c.getSubject().toString());
                                 if (cert instanceof java.security.cert.X509Certificate)
                                 {
                                    try
                                    {
                                       ((java.security.cert.X509Certificate) cert).checkValidity();
                                       trustedCertificate = true;
                                       // Log.to("util").info("Certificate is active for current date\n" + cert);
                                    }
                                    catch (CertificateExpiredException cee)
                                    {
                                       // R01FLog.to("r01f.util").info("Certificate is expired...");
                                    }
                                 }
                              }
                              else
                              {
                                 // Log.to("util").info(">>> Unknown cert " + c.getSubject().toString());
                                 // Log.to("util").fine("" + cert);
                              }

                           }
                           if (!trustedCertificate)
                           {
                              throw new CertificateException("Unknown cert " + serverCertificate);
                           }
                           peertCerts = certs.toArray(new java.security.cert.Certificate[0]);
                        }
                        catch (Exception ex)
                        {
                           ex.printStackTrace();
                           throw new IOException(ex);
                        }

                     }

                     @Override
                     public TlsCredentials getClientCredentials(CertificateRequest arg0)
                              throws IOException
                     {
                        return null;
                     }

                     /**
                      * Private method to load keyStore with system or default properties.
                      * 
                      * @return
                      * @throws Exception
                      */
                     private KeyStore _loadKeyStore() throws Exception
                     {
                        FileInputStream trustStoreFis = null;
                        try
                        {
                           String sysTrustStore = null;
                           File trustStoreFile = null;

                           KeyStore localKeyStore = null;

                           sysTrustStore = System.getProperty("javax.net.ssl.trustStore");
                           String javaHome;
                           if (!"NONE".equals(sysTrustStore))
                           {
                              if (sysTrustStore != null)
                              {
                                 trustStoreFile = new File(sysTrustStore);
                                 trustStoreFis = _getFileInputStream(trustStoreFile);
                              }
                              else
                              {
                                 javaHome = System.getProperty("java.home");
                                 trustStoreFile = new File(javaHome + File.separator + "lib" + File.separator
                                          + "security" + File.separator + "jssecacerts");

                                 if ((trustStoreFis = _getFileInputStream(trustStoreFile)) == null)
                                 {
                                    trustStoreFile = new File(javaHome + File.separator + "lib" + File.separator
                                             + "security" + File.separator + "cacerts");
                                    trustStoreFis = _getFileInputStream(trustStoreFile);
                                 }
                              }

                              if (trustStoreFis != null)
                              {
                                 sysTrustStore = trustStoreFile.getPath();
                              }
                              else
                              {
                                 sysTrustStore = "No File Available, using empty keystore.";
                              }
                           }

                           String trustStoreType = System.getProperty("javax.net.ssl.trustStoreType") != null ? System
                                    .getProperty("javax.net.ssl.trustStoreType") : KeyStore.getDefaultType();
                           String trustStoreProvider = System.getProperty("javax.net.ssl.trustStoreProvider") != null ? System
                                    .getProperty("javax.net.ssl.trustStoreProvider")
                                    : "";

                           if (trustStoreType.length() != 0)
                           {
                              if (trustStoreProvider.length() == 0)
                              {
                                 localKeyStore = KeyStore.getInstance(trustStoreType);
                              }
                              else
                              {
                                 localKeyStore = KeyStore.getInstance(trustStoreType, trustStoreProvider);
                              }

                              char[] keyStorePass = null;
                              String str5 = System.getProperty("javax.net.ssl.trustStorePassword") != null ? System
                                       .getProperty("javax.net.ssl.trustStorePassword") : "";

                              if (str5.length() != 0)
                              {
                                 keyStorePass = str5.toCharArray();
                              }

                              localKeyStore.load(trustStoreFis, (char[]) keyStorePass);

                              if (keyStorePass != null)
                              {
                                 for (int i = 0; i < keyStorePass.length; i++)
                                 {
                                    keyStorePass[i] = 0;
                                 }
                              }
                           }
                           return (KeyStore) localKeyStore;
                        }
                        finally
                        {
                           if (trustStoreFis != null)
                           {
                              trustStoreFis.close();
                           }
                        }
                     }

                     private FileInputStream _getFileInputStream(File paramFile) throws Exception
                     {
                        if (paramFile.exists())
                        {
                           return new FileInputStream(paramFile);
                        }
                        return null;
                     }

                  };

               }

            });

         }

      };// Socket

      return sslsocket;

   }

}
