package kr.seoulmaas.ieye.service.utill;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Slf4j
public class JsonMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public <T> T parseJsonObject(String json, Class<T> valueType) {
        validateJson(json);

        T dest = null;
        try {
            dest = objectMapper.readValue(json, valueType);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return dest;
    }

    private void validateJson(String json) {
        if (json.isEmpty()) {
            throw new RuntimeException();
        }
    }

}
