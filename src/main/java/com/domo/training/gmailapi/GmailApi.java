package com.domo.training.gmailapi;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GmailApi {

	private static HttpURLConnection connection;
	
	public String listMessages(int pageSize, String nextPageToken) throws IOException{
		BufferedReader reader;
		String line;
		StringBuffer response = new StringBuffer();
				
		//Setting params for pagination
		String params = "maxResults=" + pageSize;
		if (!nextPageToken.equals("-1")) {
			params = params + "&pageToken=" + nextPageToken;
		}
		
		URL url = new URL(Properties.BASE_URL + "messages?" + params);
		connection = (HttpURLConnection) url.openConnection();
		
		//Setup request
		connection.setRequestMethod("GET");
		connection.setConnectTimeout(5000);
		connection.setReadTimeout(5000);
		connection.setRequestProperty("Authorization", "Bearer " + Properties.ACCESS_TOKEN);
		
		int status = connection.getResponseCode();
//		System.out.println(status);
		
		if(status > 299) {
			reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
			while((line = reader.readLine()) != null) {
				response.append(line);
			}
		} 
		else {
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while((line = reader.readLine()) != null) {
				response.append(line);
			}
		}
		
		// Parsing message response
		Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
		MessageResponse res = gson.fromJson(response.toString(), MessageResponse.class);
		String messages = gson.toJson(res);
		writeToFile(Properties.RESOURCE_DIR + "\\messages.json", messages);
		return messages;
	}
	
	public String listLabels() throws IOException{
		BufferedReader reader;
		String line;
		StringBuffer response = new StringBuffer();
		
		URL url = new URL(Properties.BASE_URL + "labels");
		connection = (HttpURLConnection) url.openConnection();
		
		//Setup request
		connection.setRequestMethod("GET");
		connection.setConnectTimeout(5000);
		connection.setReadTimeout(5000);
		connection.setRequestProperty("Authorization", "Bearer " + Properties.ACCESS_TOKEN);
		
		int status = connection.getResponseCode();
//		System.out.println(status);
		
		if(status > 299) {
			reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
			while((line = reader.readLine()) != null) {
				response.append(line);
			}
		} 
		else {
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while((line = reader.readLine()) != null) {
				response.append(line);
			}
		}
		
		Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
		LabelResponse res = gson.fromJson(response.toString(), LabelResponse.class);
		String labels = gson.toJson(res);
		writeToFile(Properties.RESOURCE_DIR + "\\labels.json", labels);
		return labels;
	}
	
	private void writeToFile(String path, String content) {
		try {
			FileWriter file = new FileWriter(path);
			file.write(content);
			file.flush();
			System.out.println("Response successfully written into the file!");
			System.out.println("path: "+path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
