package hongikclubfair.meetingproject.common.service;

import org.springframework.stereotype.Service;

import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.service.DefaultMessageService;

import hongikclubfair.meetingproject.common.vo.MessageInfoVo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {
	private final DefaultMessageService messageService;
	private final Message message;

	private static final String MESSAGE_FORMAT =
		"""
			인스타 : %s
			자기소개 : %s

			Hicc에 많은 관심 부탁드립니다.
			가입을 원할 경우 이 번호로 연락바랍니다.""";

	public void sendSMS(MessageInfoVo messageInfo, String receiver) {
		message.setTo(receiver);
		message.setText(createText(messageInfo));

		messageService.sendOne(new SingleMessageSendingRequest(message));
	}

	private String createText(MessageInfoVo messageInfoVo) {
		return MESSAGE_FORMAT.formatted(messageInfoVo.instagramId(), messageInfoVo.introduction());
	}
}
