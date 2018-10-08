
package com.tikie.file.service.impl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.tikie.file.service.impl package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _QueryWeather_QNAME = new QName("http://impl.service.file.tikie.com/", "queryWeather");
    private final static QName _QueryWeatherResponse_QNAME = new QName("http://impl.service.file.tikie.com/", "queryWeatherResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.tikie.file.service.impl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link QueryWeather }
     * 
     */
    public QueryWeather createQueryWeather() {
        return new QueryWeather();
    }

    /**
     * Create an instance of {@link QueryWeatherResponse }
     * 
     */
    public QueryWeatherResponse createQueryWeatherResponse() {
        return new QueryWeatherResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryWeather }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.file.tikie.com/", name = "queryWeather")
    public JAXBElement<QueryWeather> createQueryWeather(QueryWeather value) {
        return new JAXBElement<QueryWeather>(_QueryWeather_QNAME, QueryWeather.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryWeatherResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.file.tikie.com/", name = "queryWeatherResponse")
    public JAXBElement<QueryWeatherResponse> createQueryWeatherResponse(QueryWeatherResponse value) {
        return new JAXBElement<QueryWeatherResponse>(_QueryWeatherResponse_QNAME, QueryWeatherResponse.class, null, value);
    }

}
