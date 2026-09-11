package com.project.BusTicketBooking.ai.memory;

import java.util.List;

import com.project.BusTicketBooking.dto.ai.BusSearchResponse;

public class ConversationMemory {

    private Long userId;

    private String currentIntent;
    private String step;

    private String source;
    private String destination;

    private List<BusSearchResponse> buses;

    private Integer selectedBusIndex;
    private Long selectedBusId;

    private String travelDate;
    private Integer numberOfSeats;

    private List<String> selectedSeats;
    private List<Long> selectedSeatIds;

    public ConversationMemory() {
    }

    public void reset() {

        currentIntent = null;
        step = null;

        source = null;
        destination = null;

        buses = null;

        selectedBusIndex = null;
        selectedBusId = null;

        travelDate = null;
        numberOfSeats = null;

        selectedSeats = null;
        selectedSeatIds = null;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCurrentIntent() {
        return currentIntent;
    }

    public void setCurrentIntent(String currentIntent) {
        this.currentIntent = currentIntent;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
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

    public List<BusSearchResponse> getBuses() {
        return buses;
    }

    public void setBuses(List<BusSearchResponse> buses) {
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

    public List<Long> getSelectedSeatIds() {
        return selectedSeatIds;
    }

    public void setSelectedSeatIds(List<Long> selectedSeatIds) {
        this.selectedSeatIds = selectedSeatIds;
    }
}