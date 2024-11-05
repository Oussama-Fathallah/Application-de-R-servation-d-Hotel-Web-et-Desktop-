package com.hotel.models;

public class Room {
    private int roomNumber;
    private String roomType;
    private int roomAvailability;

    
    public Room(int roomNumber, String roomType, int roomAvailability) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.roomAvailability = roomAvailability;
    }

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

	public int getRoomAvailability() {
		return roomAvailability;
	}

	public void setRoomAvailability(int roomAvailability) {
		this.roomAvailability = roomAvailability;
	}



}
