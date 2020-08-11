package me.will.websocket.controller;

import lombok.RequiredArgsConstructor;
import me.will.websocket.service.ChatService;
import me.will.websocket.service.dto.request.CreatRoomRequest;
import me.will.websocket.service.dto.response.ChatRoomResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ChatController {

	private final ChatService chatService;

	@PostMapping("/api/v1/chat")
	public ChatRoomResponse createRoom(@RequestBody CreatRoomRequest request) {
		return chatService.createRoom(request);
	}

	@GetMapping("/api/v1/chat")
	public List<ChatRoomResponse> findAllRooms() {
		return chatService.findAllRooms();
	}
}
