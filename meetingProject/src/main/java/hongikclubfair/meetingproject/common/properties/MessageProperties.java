package hongikclubfair.meetingproject.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cool-sms")
public record MessageProperties(
	API api,
	String phoneNumber
) {
	public record API(
		String key,
		String secret
	) {
	}
}
