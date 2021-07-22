package com.domo.training.gmailapi;

public class MessageResponse {
	Message[] messages;
	String nextPageToken;
	int resultSizeEstimate;
	
	public class Message{
		String id;
		String threadId;
	}
	
}
