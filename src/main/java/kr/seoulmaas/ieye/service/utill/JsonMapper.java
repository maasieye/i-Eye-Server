package kr.seoulmaas.ieye.service.utill;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JsonMapper {

    private final ObjectMapper objectMapper;

    public Object getJsonObject(String json, Object object) {
        if (json.isEmpty()) {
            throw new RuntimeException();
        }
        Object dest = null;
        try {
            dest = objectMapper.readValue(json, object.getClass());

        } catch (Exception e) {

        }
        return dest;
    }

}
