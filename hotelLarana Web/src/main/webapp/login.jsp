<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="icon" href="imgs/favicon-black.png" type="image/png">
    <link rel="stylesheet" href="styles/login.css">
    <title>Larana Hotel | Login</title>

</head>
<body>
    <div class="form-container">
        <img src="imgs/fullLogo.png" alt="Larana Hotel Logo" class="logo" width="180">
        <div class="divider"></div>
        <form action="<%= request.getContextPath()%>/login" method="post"  class="form" >
          <input type="email" class="input" placeholder="Email" name="Email">
          <input type="password" class="input" placeholder="Password" name="Password">
          <button class="form-btn" type="submit">Log in</button>
        </form>
        <p class="sign-up-label">
          
          Don't have an account? <a href="userregister.jsp" class="sign-up-link">Sign up</a>
        </p>
      </div>
</body>
</html>