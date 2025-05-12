package guru.qa;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.qa.model.Payment;
import guru.qa.model.Receiver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.io.File;
import java.io.InputStream;



public class CheckJsonFile {


    private ClassLoader cl = CheckJsonFile.class.getClassLoader();


    @Test
    void checkJsonFileTest() throws Exception {

        try (InputStream jsonStream = cl.getResourceAsStream("pt.json")) {
            if (jsonStream == null) {
                throw new IllegalArgumentException("JSON file not found");
            }

            ObjectMapper mapper = new ObjectMapper();
            JsonParser parser = mapper.getFactory().createParser(jsonStream);


            Payment payment = mapper.readValue(parser, Payment.class);
            Receiver receiver = mapper.readValue(parser, Receiver.class);

            Assertions.assertEquals(63, payment.getId());
            Assertions.assertEquals("19", receiver.getCode());
            Assertions.assertEquals("KYRTDHBN", receiver.getBankIdentifierCode());
        }
    }

    @Test
    void checkJsonFile()  throws Exception{
        File jsonFile = new File("src/test/resources/pt.json");
        ObjectMapper mapper = new ObjectMapper();

        Payment payment = mapper.readValue(jsonFile, Payment.class);
        Receiver receiver = mapper.readValue(jsonFile, Receiver.class);

        Assertions.assertEquals(63, payment.getId());
        Assertions.assertEquals("19", receiver.getCode());
        Assertions.assertEquals("KYRTDHBN", receiver.getBankIdentifierCode());
    }


}
