package me.will.websocket.component.chat.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChatMessageDto {

	private MessageType type;

	private String roomId;

	private String sender;

	private String message;

	public boolean isEnterType() {
		return this.type.equals(MessageType.ENTER);
	}

}
