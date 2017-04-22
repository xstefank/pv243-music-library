package cz.muni.fi.pv243.musiclib.websocket.endpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pv243.musiclib.entity.Recommendation;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.util.List;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
public class RecommendationSerializer implements Encoder.Text<List<Recommendation.Aggregate>> {
    private ObjectMapper objectMapper;

    @Override
    public String encode(List<Recommendation.Aggregate> object) throws EncodeException {

        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new EncodeException(object, e.getMessage(), e);
        }
    }

    @Override
    public void init(EndpointConfig config) {
        objectMapper = new ObjectMapper();
    }

    @Override
    public void destroy() {

    }
}
