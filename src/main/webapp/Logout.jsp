<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    session.invalidate(); // Hủy session hiện tại
    response.sendRedirect("Login.jsp"); // Quay về trang đăng nhập
%>
