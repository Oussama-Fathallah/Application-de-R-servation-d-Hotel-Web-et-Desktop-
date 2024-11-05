package com.hotel.models;

import java.util.Date;

public class Reservation {
    private int reservationID;
    private String firstname;
    private String lastname;
    private String email;
    private int number;
    private Date checkInDate;
    private Date checkOutDate;
    private int numberOfGuests;
    private String roomType;
    



    

	public Reservation(int reservationID, String firstname, String lastname, String email, int number,
			Date checkInDate, Date checkOutDate, int numberOfGuests, String roomType) {
        this.reservationID = reservationID;
        this.setFirstname(firstname);
        this.setLastname(lastname);
        this.setEmail(email);
        this.setNumber(number);
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.setNumberOfGuests(numberOfGuests);
        this.setRoomType(roomType);
	}



	public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }



    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }



	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}



	public String getLastname() {
		return lastname;
	}



	public void setLastname(String lastname) {
		this.lastname = lastname;
	}



	public int getNumber() {
		return number;
	}



	public void setNumber(int number) {
		this.number = number;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public int getNumberOfGuests() {
		return numberOfGuests;
	}



	public void setNumberOfGuests(int numberOfGuests) {
		this.numberOfGuests = numberOfGuests;
	}



    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }



	public String getFirstname() {
		return firstname;
	}
}
