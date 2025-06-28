package com.petcare.adapter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

public class JsonToXmlAdapter {

    public static <T> String convertToXml(T objeto) {
        try {
            JAXBContext context = JAXBContext.newInstance(objeto.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter sw = new StringWriter();
            marshaller.marshal(objeto, sw);

            return sw.toString();
        } catch (JAXBException e) {
            throw new RuntimeException("Erro ao converter para XML", e);
        }
    }
}