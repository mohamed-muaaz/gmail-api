package com.domo.training.gmailapi;

public class LabelResponse {
	Label[] labels;
	
	public class Label{
		String id;
		String name;
		String type;
		String messageListVisibility;
		String labelListVisibility;
		
		@Override
		public String toString() {
		  return getClass().getSimpleName() + "[id=" + id + 
				  ", name="+name+
				  ", type="+type+
				  ", messageListVisibility="+messageListVisibility+
				  ", labelListVisibility="+labelListVisibility+"]";
		}
	}
}
