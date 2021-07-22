package com.domo.training.gmailapi;

import java.io.IOException;

public class Main {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		GmailApi api = new GmailApi();
		
		//List all messages
		int pageSize = 5;
		String nextPageToken = "-1";
		
		String messages = api.listMessages(pageSize, nextPageToken);
		System.out.println(messages);
		
		//List all labels
		String labels = api.listLabels();
		System.out.println(labels);
		
		//Writing labels to csv file
		CsvWriter csvWriter = new CsvWriter();
		csvWriter.writeToCsv(labels);
		
	}

}
