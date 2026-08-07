package com.project.BusTicketBooking.dto.ai;

public class BusSearchRequest {
	
	 private String source;
	    private String destination;

	    public BusSearchRequest() {}

	    public BusSearchRequest(String source, String destination) {
	        this.source = source;
	        this.destination = destination;
	    }

	    public String getSource() {
	        return source;
	    }

	    public void setSource(String source) {
	        this.source = source;
	    }

	    public String getDestination() {
	        return destination;
	    }

	    public void setDestination(String destination) {
	        this.destination = destination;
	    }

}
