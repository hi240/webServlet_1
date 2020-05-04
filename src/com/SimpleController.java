package com;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 * 서블릿
 * ㅈㅏ바로 만든 파일로 web서비스를 할 목적으로 만들어졌다.
 * 서블릿 파일 조건 : extends HttpServlet >> 웹 request,response 객체 사용가능
 * 1.extends httpServlet 반드시상속(웹환경에서 요청과 응답 처리)
 * 2.SimpleController 서블릿 
 * 3.서블릿은 event동작에 기반(특정 함수들이 특정 상황에 사건이 발생하면 자동 호출된다.)
 * ex)페이지가 로드 나면.. 함수 호출
 * protected void doGet
 * protected void doPost 2개의 함수가 자동호출
 * 클라이언트가 SimpleController 요청하면
 *  요청방식이(form action태그에서 method)
 *  get방식이면 doGet
 *            <from method="GET"
 *            <a href="login.jsp?num=1000">게시판</a>
 *  post방식이면 doPost
 *            <form method="POST"
 *  doGET,doPOST : request.getParameter()
 *  
 *  M1 > http://192.168.0.12:8090/WebServlet_1/index.jsp  > jsp요청방식
 *  M2 > http://192.168.0.12:8090/WebServlet_1/SimpleController.java  > 
 *  위는 web.xml로 가서 <url-pattern>/simple</url-pattern>
 *  http://192.168.0.12:8090/WebServlet_1/simple 요청이 오면
 *  실행과정:
 *  서블릿 > 컴파일 > class파일 생성 > 실행(doGet,doPost) > 결과를 return
 *  
 *  12시 서버 오픈 > 서버(SimpleControler> 자바파일
 *  >12시 10분 철수 : 최초요청(http://192.168.0.12:8090/WebServlet_1/simple) >>요청이 들어오면 그때 compile (class생성)
 *        >> 실행 >> 생성자 >>doGet,doPost 실행 > 그 결과를 Client에게 return
 *  >12시 15분 순이 : http://192.168.0.12:8090/WebServlet_1/simple >> 기존 실행파일 존재 >>
 *        >> 실행 >>doGet,doPost 실행 > 그 결과를 Client에게 return
 *  >SimpleController 자바 파일 다시 컴파일은 언제 될까요 : 서버가 리부팅 됐을 때, 개발자가 코드를 수정했을 때
 *  
 *  4.Model1 방식
 *  4.1 JSP 요청과 응답을 처리 (DAO,DTO) +JSP
 *  4.2 클라이언트의 모든 요청을 JSP 처리
 *  4.3 UI + 업무(로직) : JSP 처리
 *  
 *  5.Model2 방식 (MVC)
 *  5.1 MVC(Model, View, Controller)
 *  Model(순수한 다바 파일) : DAO(DeptDao.java),DTO(VO,Domain)(Dept.java), Service(처리)
 *  View : JSP(서버쪽에서 받은 데이터를 화면에 출력: EL&JSTL), html
 *  Controller : Servlet (중앙통제관리)
 *    1. 클라이언트 요청 파악 (로그인, 게시판 글쓰기, 게시판 상세보기) 파악
 *    2. 요청 처리 (화면만 제공(로그인 화면), (로그인처리)DB연결, 로직 처리 - 결과 return)
 *    
 */
//@WebServlet("/SimpleController")
public class SimpleController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SimpleController() {
        super();
        System.out.println("생성자호출");
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());		
		System.out.println("클라이언트 요청");
		
		//1.jsp가 클라이언트 요청할 때 젤먼저 한글처리
		//2.데이터 받기
		//3.업무처리 순으로 했었던 것 처럼 , servlet 페이지도 순서가 같다!
		
		//1.한글처리
		//2.사용자의 요청파악(요청값받기)
		String type=request.getParameter("type");
		//3.요청에 따른 업무처리
		Object resultobj = null;
	    if(type==null||type.equals("greeting")) {
	    	resultobj= "hello world";
	    }else if(type.equals("date")){
	        resultobj= new Date();
	   
	    }else {
	    	resultobj="invalid String type";
	    }
	    
	    //4.요청 완료에 따른 결과를 저장!
	    //저장방법: request, session, application객체
	    request.setAttribute("result", resultobj);
	    
	    //5.저장한 결과를 Client전달(화면 JSP) 어떤 jsp사용할지 지정!
	    //출력한 page를 정하고 > 출력한 data를 page에 넘겨주고 > forward방식
	    //request객체는 어디서 공유된다? include된 page또는 forward된 page에서 공유된다*****
	    RequestDispatcher dis= request.getRequestDispatcher("/simpleview.jsp");
	    //출력 페이지 지정 (dispatcher)
	    //화면지정
	    //지정한 화면에 데이터전달(forward방식으로)
	    dis.forward(request,response);
	    //현재 페이지가 가지고 있는 requesst , response 객체의 주소를 넘겨준다.
	    
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//doGet(request, response);
	}

}
