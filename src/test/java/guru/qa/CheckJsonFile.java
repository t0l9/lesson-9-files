package guru.qa;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.qa.model.Payment;
import guru.qa.model.Receiver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


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
            Receiver receiver = payment.getReceiver();

            Assertions.assertEquals(63, payment.getId());
            Assertions.assertEquals("19", receiver.getCode());
            Assertions.assertEquals("KYRTDHBN", receiver.getBankIdentifierCode());
        }
    }


}
