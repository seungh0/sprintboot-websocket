package me.will.websocket;

import lombok.Getter;
import me.will.websocket.component.chat.dto.ChatMessageDto;
import me.will.websocket.service.ChatService;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
public class ChatRoom {

	private String roomId;

	private String name;

	private Set<WebSocketSession> sessions = new HashSet<>();

	private ChatRoom(String name) {
		this.roomId = UUID.randomUUID().toString();
		this.name = name;
	}

	public static ChatRoom newInstance(String name) {
		return new ChatRoom(name);
	}

	public void handleActions(WebSocketSession session, ChatMessageDto chatMessage, ChatService chatService) {
		if (chatMessage.isEnterType()) {
			sessions.add(session);
			chatMessage.setMessage(chatMessage.getSender() + " 님이 입장했습니다.");
		}
		sendMessage(chatMessage, chatService);
	}

	private <T> void sendMessage(T message, ChatService chatService) {
		sessions.parallelStream().forEach(
				session -> chatService.sendMessage(session, message)
		);

	}

}
