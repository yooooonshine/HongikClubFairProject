package hongikclubfair.meetingproject.common.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import hongikclubfair.meetingproject.common.properties.MessageProperties;

@Configuration
@EnableConfigurationProperties({
	MessageProperties.class
})
public class PropertiesConfig {
}
