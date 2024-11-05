<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="icon" href="imgs/favicon-black.png" type="image/png">
    <link rel="stylesheet" href="styles/register.css">
    <title>Larana Hotel | Sign Up</title>
</head>
<body>

    <div class="form-container">
        <img src="imgs/fullLogo.png" alt="Larana Hotel Logo" class="logo" width="180">
        <div class="divider"></div>
        <form action="<%= request.getContextPath()%>/register" method="post" class="form">
            <div class="name-container">
                <input type="text" class="input" placeholder="First Name" name="FirstName" >
                <input type="text" class="input" placeholder="Last Name" name="LastName">
            </div>
            <input type="email" class="input" placeholder="Email" name="Email">
            <input type="password" class="input" placeholder="Password" name="Password">
            <input type="password" class="input" placeholder="Confirm Password">
            <button class="form-btn" type="submit">Sign Up</button>
        </form>
        <p class="sign-in-label">
            Already have an account? <a href="login.jsp" class="sign-in-link">Sign in</a>
        </p>
        
    </div>
</body>
</html>
