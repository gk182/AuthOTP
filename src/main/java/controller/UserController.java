package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.UserDAO;

import java.io.IOException;

import org.apache.tomcat.util.descriptor.web.LoginConfig;
import util.*;
/**
 * Servlet implementation class UserController
 */
@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String action = request.getParameter("action");
		System.out.print(action);
		if (action.equals("login")) {
			Login(request,response);
		}
		else if (action.equals("register")) {
			Register(request,response);
		}
		else if (action.equals("resend-otp")) {
			String username = (String) request.getSession().getAttribute("username");
			String email = UserDAO.getEmailByUsername(username);
			
			String otp = OTPGenerator.generateOTP();
        	
            String subject = "Xác thực OTP";

			// Generate new OTP
			
			// Send new OTP
			EmailSender.sendEmail(email, subject, otp);
			
			// Save new OTP
			UserDAO.saveOTP(username, otp);
			
			// Redirect back to VerifyOTP page
			response.sendRedirect("VerifyOTP.jsp");
		}
	}

	private void Register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		
		if (UserDAO.checkUserExists(username)) {
            request.setAttribute("errorMessage", "Username already exists. Please choose another one.");
            request.getRequestDispatcher("Register.jsp").forward(request, response);
        } else {
        	UserDAO.register(username, email, pass);
            response.sendRedirect("Login.jsp"); 
        }
	}

	private void Login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String pass = request.getParameter("password");
		String userAgent = request.getHeader("User-Agent");
		
		int loginStatus = UserDAO.login(username, pass, userAgent);
		if (loginStatus == 1) {
			request.getSession().setAttribute("username", username);
			response.sendRedirect("HomePage.jsp");
		} else if (loginStatus == 2) {
			String email = UserDAO.getEmailByUsername(username);
			String otp = OTPGenerator.generateOTP();
            String subject = "Xác thực OTP";
			EmailSender.sendEmail(email, subject, otp);
			UserDAO.saveOTP(username, otp);
			request.getSession().setAttribute("username", username);
			response.sendRedirect("VerifyOTP.jsp");
		} else {
			request.setAttribute("errorMessage", "Incorrect username or password!");
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}
	}

}
