package hongikclubfair.meetingproject.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;

import hongikclubfair.meetingproject.common.properties.MessageProperties;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class MessageConfig {
	private final MessageProperties messageProperties;
	private static final String DOMAIN = "https://api.coolsms.co.kr";

	@Bean
	public DefaultMessageService defaultMessageService() {
		return NurigoApp.INSTANCE.initialize(
			messageProperties.api().key(),
			messageProperties.api().secret(),
			DOMAIN);
	}

	@Bean
	public Message message() {
		Message message = new Message();
		message.setFrom(messageProperties.phoneNumber());
		return message;
	}
}
