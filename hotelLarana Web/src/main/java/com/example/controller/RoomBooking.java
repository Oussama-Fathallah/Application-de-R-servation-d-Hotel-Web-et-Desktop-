package com.example.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.ReservationDao;
import com.example.model.Reservation;

@WebServlet("/book")
public class RoomBooking extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ReservationDao reservationDao;

    public void init() {
        reservationDao = new ReservationDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("FirstName");
        String lastName = request.getParameter("LastName");
        String email = request.getParameter("Email");
        String phoneNumber = request.getParameter("Number");
        Date checkInDate = parseDate(request.getParameter("CheckInDate"));
        Date checkOutDate = parseDate(request.getParameter("CheckOutDate"));
        int numberOfGuests = Integer.parseInt(request.getParameter("NumberOfGuests"));
        String roomType = request.getParameter("RoomType");

        Reservation reservation = new Reservation();
        reservation.setFirstName(firstName);
        reservation.setLastName(lastName);
        reservation.setEmail(email);
        reservation.setPhoneNumber(phoneNumber);
        reservation.setCheckInDate(checkInDate);
        reservation.setCheckOutDate(checkOutDate);
        reservation.setNumberOfGuests(numberOfGuests);
        reservation.setRoomType(roomType);

        try {
            int result = reservationDao.addReservation(reservation);

            if (result > 0) {
                // Send email confirmation
                sendEmailConfirmation(reservation);

                response.sendRedirect("reservationsuccess.jsp"); // Redirect to a success page
            } else {
                response.sendRedirect("error.jsp"); // Redirect to an error page
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Redirect to an error page
        }
    }

    private Date parseDate(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void sendEmailConfirmation(Reservation reservation) throws MessagingException {
        
        String host = "smtp.gmail.com";
        String username = "hotellarana7@gmail.com";
        String password = "zszkuuowtjuteltw";
        String toAddress = reservation.getEmail();

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
        message.setSubject("Reservation Confirmation");
        message.setText("Dear " + reservation.getFirstName() + " "+ reservation.getLastName()+",\n\n"
                + "Your reservation has been confirmed. \nDetails:\n\n" + "Check-in Date: "
                + reservation.getCheckInDate() + "\n" + "Check-out Date: " + reservation.getCheckOutDate() + "\n"
                + "Number of Guests: " + reservation.getNumberOfGuests() + "\n" + "Room Type: "
                + reservation.getRoomType() + "\n\n" + "Thank you for choosing our hotel.\n\n" + "Best Regards,\n"
                + "LARANA Hotel");

        Transport.send(message);
    }

}
