package com.project.BusTicketBooking.ai.memory;

import java.util.List;

import com.project.BusTicketBooking.dto.bus.BusResponseDTO;

public class ConversationMemory {

    private String currentIntent;

    private String source;

    private String destination;

    private List<BusResponseDTO> buses;

    private Integer selectedBusIndex;

    private Long selectedBusId;

    private String travelDate;

    private Integer numberOfSeats;

    private List<String> selectedSeats;

    public ConversationMemory() {
    }

    public String getCurrentIntent() {
        return currentIntent;
    }

    public void setCurrentIntent(String currentIntent) {
        this.currentIntent = currentIntent;
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

    public List<BusResponseDTO> getBuses() {
        return buses;
    }

    public void setBuses(List<BusResponseDTO> buses) {
        this.buses = buses;
    }

    public Integer getSelectedBusIndex() {
        return selectedBusIndex;
    }

    public void setSelectedBusIndex(Integer selectedBusIndex) {
        this.selectedBusIndex = selectedBusIndex;
    }

    public Long getSelectedBusId() {
        return selectedBusId;
    }

    public void setSelectedBusId(Long selectedBusId) {
        this.selectedBusId = selectedBusId;
    }

    public String getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(String travelDate) {
        this.travelDate = travelDate;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public List<String> getSelectedSeats() {
        return selectedSeats;
    }

    public void setSelectedSeats(List<String> selectedSeats) {
        this.selectedSeats = selectedSeats;
    }
}