package me.will.websocket.service.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.will.websocket.ChatRoom;

@Getter
@RequiredArgsConstructor
public class ChatRoomResponse {

	private final String roomId;

	private final String name;

	public static ChatRoomResponse of(ChatRoom chatRoom) {
		return new ChatRoomResponse(chatRoom.getRoomId(), chatRoom.getName());
	}

}
