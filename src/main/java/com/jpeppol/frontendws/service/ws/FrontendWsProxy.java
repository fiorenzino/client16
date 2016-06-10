package com.jpeppol.frontendws.service.ws;

public class FrontendWsProxy implements FrontendWs {
  private String _endpoint = null;
  private FrontendWs frontendWs = null;
  
  public FrontendWsProxy() {
    _initFrontendWsProxy();
  }
  
  public FrontendWsProxy(String endpoint) {
    _endpoint = endpoint;
    _initFrontendWsProxy();
  }
  
  private void _initFrontendWsProxy() {
    try {
      frontendWs = (new com.jpeppol.frontendws.service.ws.FrontendWsServiceLocator()).getFrontendWsPort();
      if (frontendWs != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)frontendWs)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)frontendWs)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (frontendWs != null)
      ((javax.xml.rpc.Stub)frontendWs)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public FrontendWs getFrontendWs() {
    if (frontendWs == null)
      _initFrontendWsProxy();
    return frontendWs;
  }
  
  public String upload(String senderID, String recipientID, String documentType, String relatedId, byte[] document) throws java.rmi.RemoteException, Exception{
    if (frontendWs == null)
      _initFrontendWsProxy();
    return frontendWs.upload(senderID, recipientID, documentType, relatedId, document);
  }
  
  
}