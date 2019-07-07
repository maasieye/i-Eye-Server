package kr.seoulmaas.ieye.service.utill;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class JsonMapper {

    private final ObjectMapper objectMapper;

    public <T> T parseJsonObject(String json, Class<T> valueType) {
        validateJson(json);

        try {
            return objectMapper.readValue(json, valueType);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("JSON 파싱 실패.");
        }
    }

    private void validateJson(String json) {
        if (json.isEmpty()) {
            throw new RuntimeException("JSON 값이 없습니다.");
        }
    }

}
