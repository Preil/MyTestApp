package com.eurochemix.webapp.util;

import com.eurochemix.webapp.WebAppException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.Reader;
import java.io.Writer;

/**
 * Created by Ilya on 09.03.2016.
 */
public class XmlParser {
    private final Marshaller marshaller;
    private final Unmarshaller unmarshaller;

    public XmlParser(Class... classesToBeBounded) {
        try {
            JAXBContext ctx = JAXBContext.newInstance(classesToBeBounded);

            marshaller = ctx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING,"UTF-8");
//            marshaller.setProperty(Marshaller.JAXB_FRAGMENT,true);

            unmarshaller = ctx.createUnmarshaller();

        } catch (JAXBException e){
            throw new WebAppException("JAXB init failed", e);
        }
    }
    public <T> T unmarshall(Reader reader){
        try{
            return (T) unmarshaller.unmarshal(reader);

        } catch (JAXBException e){
            throw new WebAppException("JAXB unmarshall failed",e);
        }
    }
    public void marshall(Object instance,Writer writer){
        try{
            marshaller.marshal(instance, writer);

        } catch (JAXBException e) {
            throw new WebAppException("JAXB marshal failed", e);

        }
    }
}
