<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Kiểm tra xem người dùng đã đăng nhập hay chưa
    String username = null;

    if (session != null) { // session là biến mặc định của JSP
        username = (String) session.getAttribute("username");
    }

    if (username == null) {
        // Chưa đăng nhập, chuyển hướng đến trang đăng nhập
        response.sendRedirect("Login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome, <%= username %>!</title>
    <link rel="stylesheet" href="css/homepage.css">
</head>
<body>
    <!-- Header -->
    <div class="header">
        <h1>Welcome to Your Dashboard</h1>
    </div>

    <!-- Welcome Section -->
    <div class="welcome">
        <h2>Hello, <%= username %>!</h2>
        <p>You have successfully logged in. Feel free to explore your personalized dashboard.</p>
        <a href="Logout.jsp" class="logout-button">Log Out</a>
    </div>

    
</body>
</html>
