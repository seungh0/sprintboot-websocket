package me.will.websocket.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import me.will.websocket.ChatRoom;
import me.will.websocket.service.dto.request.CreatRoomRequest;
import me.will.websocket.service.dto.response.ChatRoomResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ChatService {

	private final ObjectMapper objectMapper;

	private Map<String, ChatRoom> chatRooms = new LinkedHashMap<>();

	public List<ChatRoomResponse> findAllRooms() {
		return chatRooms.values().stream()
				.map(ChatRoomResponse::of)
				.collect(Collectors.toList());
	}

	public ChatRoom findRoomById(String roomId) {
		return chatRooms.get(roomId);
	}

	public ChatRoomResponse createRoom(CreatRoomRequest request) {
		ChatRoom chatRoom = ChatRoom.newInstance(request.getName());
		chatRooms.put(chatRoom.getRoomId(), chatRoom);
		return ChatRoomResponse.of(chatRoom);
	}

	public <T> void sendMessage(WebSocketSession session, T message) {
		try {
			session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
		} catch (IOException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

}
