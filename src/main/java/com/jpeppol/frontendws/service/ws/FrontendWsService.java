/**
 * FrontendWsService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.jpeppol.frontendws.service.ws;

public interface FrontendWsService extends javax.xml.rpc.Service {
    public String getFrontendWsPortAddress();

    public FrontendWs getFrontendWsPort() throws javax.xml.rpc.ServiceException;

    public FrontendWs getFrontendWsPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
