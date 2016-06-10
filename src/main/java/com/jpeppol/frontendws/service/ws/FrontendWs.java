/**
 * FrontendWs.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.jpeppol.frontendws.service.ws;

public interface FrontendWs extends java.rmi.Remote {
    public String upload(String senderID, String recipientID, String documentType, String relatedId, byte[] document) throws java.rmi.RemoteException, Exception;
}
