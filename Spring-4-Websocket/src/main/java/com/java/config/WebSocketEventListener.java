package com.java.config;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.java.chat.ChatMessage;
import com.java.chat.MessageType;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WebSocketEventListener {
	
	private final SimpMessageSendingOperations messageOperations;
	
	@EventListener
	 public void handleWebsocketDisconnectListener (
			SessionDisconnectEvent event ) {
		
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(
			 event.getMessage()	);
		
		String username = (String) headerAccessor.getSessionAttributes().get("useraname");
		
		if(username !=null) {
			var chatMessage = ChatMessage.builder()
			.type(MessageType.LEAVE)
			.sender(username)
			.build(); 
			
		messageOperations.convertAndSend("/topic/public",chatMessage);	
		}
				
		 
	 }
	 

}
