package kr.seoulmaas.ieye.service.utill;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

public class JsonMapperTest {

    private JsonMapper jsonMapper = new JsonMapper();

    @Test
    public void parseJsonObject() throws IOException {
        String json = "{ \"id\" : \"thisIsId\" , \"value\" : 1 }";
        TestObject testObject = jsonMapper.parseJsonObject(json, TestObject.class);

        ObjectMapper objectMapper = new ObjectMapper();
        TestObject object = objectMapper.readValue(json,TestObject.class);
        System.out.println(testObject.toString());
    }
}