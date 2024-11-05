<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="imgs/favicon-black.png" type="image/png">
<script>
        function setMinDate(element) {
            var today = new Date();
            var dd = String(today.getDate()).padStart(2, '0');
            var mm = String(today.getMonth() + 1).padStart(2, '0'); // January is 0!
            var yyyy = today.getFullYear();
            today = yyyy + '-' + mm + '-' + dd;

            element.setAttribute('min', today);
        }

        function updateMinCheckoutDate() {
            var checkInDate = document.getElementById('checkInDate');
            var checkOutDate = document.getElementById('checkOutDate');

            checkOutDate.setAttribute('min', checkInDate.value);
        }
    </script>
<title>Larana Hotel | Booking</title>
<link rel="stylesheet" href="styles/booking.css">
</head>
<body>
<div class="form-container">
        <img src="imgs/fullLogo.png" alt="Larana Hotel Logo" class="logo" width="180">
	<form action="<%= request.getContextPath()%>/book" method="post" class="form">
    <div class="name-container">
        <input type="text" class="input" placeholder="First Name" name="FirstName" required>
        <input type="text" class="input" placeholder="Last Name" name="LastName" required>
    </div>
    <input type="email" class="input" placeholder="Email" name="Email" required>
    <input type="tel" class="input" placeholder="Number" name="Number" pattern="[0-9]{10}" required>
    
     <input type="text" id="checkInDate" name="CheckInDate" class="input" placeholder="Check-in date"
                   onfocus="(this.type='date'); setMinDate(this);" onchange="updateMinCheckoutDate();" required>
            <input type="text" id="checkOutDate" name="CheckOutDate" class="input" placeholder="Check-out date"
                   onfocus="(this.type='date'); setMinDate(this);" onchange="updateMinCheckoutDate();" required>

    <input type="number" class="input" placeholder="Number of Guests" name="NumberOfGuests" required>
    
    <select class="input" name="RoomType" required>
        <option value="standard">Standard</option>
        <option value="suite">Suite</option>
    </select>
    <div class="name-container">
	    <input type="checkbox" id="terms" name="TermsAndConditions" required>
	    <label for="terms">I agree to terms and conditions</label>
    </div>
    <button class="form-btn" type="submit">Book Now</button>
</form>

</div>
</body>
</html>