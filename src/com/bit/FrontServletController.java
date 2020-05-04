package com.bit;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(description = "이 녀석은 설명 입니다^^", urlPatterns = { "/action.do" })
public class FrontServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public FrontServletController() {
        super();
        
    }

	//doProcess 와 같아요. 하나에서 처리 다 하겠다.
    //doGet과 doPost는 호출되지 않아요.
 //	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("service실행");
//	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		System.out.println("doget");
		//http://192.168.0.12:8090/WebServlet_1/action.do?cmd=greeting
		
		request.setCharacterEncoding("UTF-8");
		String cmd= request.getParameter("cmd");
		String msg="";
		if(cmd.equals("greeting")) {
			Message m = new Message();
			msg= m.getMessage(cmd);
		}
		request.setAttribute("msg", msg);
		RequestDispatcher dis = request.getRequestDispatcher("/greeting.jsp"); //뷰지정
		dis.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("dopst");
	}

}
