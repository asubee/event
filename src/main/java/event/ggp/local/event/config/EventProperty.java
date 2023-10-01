package event.ggp.local.event.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.Map;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix="event")
public class EventProperty {
    private Map<String,String> init;
    private Map<String, String> event;
}