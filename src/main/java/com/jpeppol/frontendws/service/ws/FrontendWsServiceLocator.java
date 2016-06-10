/**
 * FrontendWsServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.jpeppol.frontendws.service.ws;

public class FrontendWsServiceLocator extends org.apache.axis.client.Service implements FrontendWsService {

    public FrontendWsServiceLocator() {
    }


    public FrontendWsServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public FrontendWsServiceLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for FrontendWsPort
    private String FrontendWsPort_address = "https://test.jpeppol.com/ws/v1/upload";

    public String getFrontendWsPortAddress() {
        return FrontendWsPort_address;
    }

    // The WSDD service name defaults to the port name.
    private String FrontendWsPortWSDDServiceName = "FrontendWsPort";

    public String getFrontendWsPortWSDDServiceName() {
        return FrontendWsPortWSDDServiceName;
    }

    public void setFrontendWsPortWSDDServiceName(String name) {
        FrontendWsPortWSDDServiceName = name;
    }

    public FrontendWs getFrontendWsPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(FrontendWsPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getFrontendWsPort(endpoint);
    }

    public FrontendWs getFrontendWsPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.jpeppol.frontendws.service.ws.FrontendWsServiceSoapBindingStub _stub = new com.jpeppol.frontendws.service.ws.FrontendWsServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getFrontendWsPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setFrontendWsPortEndpointAddress(String address) {
        FrontendWsPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (FrontendWs.class.isAssignableFrom(serviceEndpointInterface)) {
                com.jpeppol.frontendws.service.ws.FrontendWsServiceSoapBindingStub _stub = new com.jpeppol.frontendws.service.ws.FrontendWsServiceSoapBindingStub(new java.net.URL(FrontendWsPort_address), this);
                _stub.setPortName(getFrontendWsPortWSDDServiceName());
                return _stub;
            }
        }
        catch (Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        String inputPortName = portName.getLocalPart();
        if ("FrontendWsPort".equals(inputPortName)) {
            return getFrontendWsPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://ws.service.frontendws.jpeppol.com/", "FrontendWsService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://ws.service.frontendws.jpeppol.com/", "FrontendWsPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {
        
if ("FrontendWsPort".equals(portName)) {
            setFrontendWsPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
