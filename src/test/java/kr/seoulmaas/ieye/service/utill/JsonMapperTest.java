package kr.seoulmaas.ieye.service.utill;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@TestPropertySource("classpath:openapi.properties")
@RunWith(SpringRunner.class)
@SpringBootTest
public class JsonMapperTest {

    @Autowired
    private JsonMapper jsonMapper;

    @Test
    public void parseJsonObject() {
        String json = "{ \"id\" : \"thisIsId\" , \"value\" : 1 }";
        TestObject testObject = jsonMapper.parseJsonObject(json, TestObject.class);

        assertThat(testObject.getId()).isEqualTo("thisIsId");
        assertThat(testObject.getValue()).isEqualTo(1);
    }
}