package com.petcare.adapter;

import com.petcare.dto.PetDTO;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

public class PetAdapter {

    public String toXML(PetDTO dto) {
        try {
            JAXBContext context = JAXBContext.newInstance(PetDTO.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter sw = new StringWriter();
            marshaller.marshal(dto, sw);
            return sw.toString();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter para XML", e);
        }
    }
}