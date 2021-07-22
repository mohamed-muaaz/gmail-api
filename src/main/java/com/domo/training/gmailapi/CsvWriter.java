package com.domo.training.gmailapi;

import java.io.FileWriter;
import java.io.IOException;

import com.domo.training.gmailapi.LabelResponse.Label;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CsvWriter {
	public void writeToCsv(String labelResponse) throws IOException {
//		System.out.println(labelResponse);
		
		Gson gson = new GsonBuilder().serializeNulls().create();
		LabelResponse labels = gson.fromJson(labelResponse, LabelResponse.class);
		
		FileWriter file = new FileWriter(Properties.RESOURCE_DIR + "\\labels.csv");
		
		//Writing headers
		file.append("id");
		file.append(",");
		file.append("name");
		file.append(",");
		file.append("type");
		file.append(",");
		file.append("messageListVisibility");
		file.append(",");
		file.append("labelListVisibility");
		file.append("\n");
		
		//writing values
		for(Label label:labels.labels) {
//			System.out.println(label.toString());
			
			file.append(label.id);
			file.append(",");
			file.append(label.name);
			file.append(",");
			file.append(label.type);
			file.append(",");
			file.append(label.messageListVisibility);
			file.append(",");
			file.append(label.labelListVisibility);
			file.append("\n");
		}
		
		file.flush();
		file.close();
		
		return;
	}
}
