package com.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet("/FrontBoardController")
public class FrontBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public FrontBoardController() {
        super();
        
    }
    //get과 post 두가지 요청방식에 대해서 동작하는 함수
    //doGet(),doPost() 자동 호출된다.
    //1.servlet 제공하는 함수
    //2.별도의 함수를 하나 만들어서, doget dopost 두개 다 처리하도록.
    //두개 코드가 같다면 뭐든 받아서 처리하는 함수를 만들자.
    //doProcess() 

    private void doProcess(HttpServletRequest request, HttpServletResponse response,String method) throws ServletException, IOException {
    	//이 함수가 get,post요청을 둘 다 처리하겠다.
    	System.out.println("Client 요청 : "+method);
    	//아래 6단계를 외우세요.(한글>data받기>data판단>결과저장>view지정>view전달)
    	//1.한글처리
    	//2.요청받기(request)
    	//3.요청판단(판단의 기준: parameter) : 1.command 방식 2.url방식
    	//3-1.parameter 기준으로 판단
    	//3-2.http://192.168.0.12:8090/WebServlet_1/board?cmd=login&userid=kglim
    
    	//1.command방식
    	//저 cmd의 파라미터에 따라 처리하는 내용이 달라지게 됨.
    	//cmd인경우
    	//String str = request.getParameter("cmd");
    	//if(str.equals("login") {로그인처리} )
    	
    	//board?cmd=boardlist 인 경우
     	//String str = request.getParameter("cmd");
    	//if(str.equals("boardlist") {게시판조회처리} )
    	
    	//2.url방식(URL 주소 판단)
    	//http://192.168.0.12:8090/WebServlet_1/board/boardlist
    	//http://192.168.0.12:8090/WebServlet_1/board/boardwrite?title=hello&content=
    	//마지막 주소 값으로 판단: /boardlist  목록보기
    	                 // /boardwrite 글쓰기
    	
    	//4. 결과 저장 ( scope : request(현재page), session(모든page), application ) 보통은 request.setAttribute .....
    	
    	//5. view 지정
    	//view page : jsp 를 지정.
    	//WebContent > board > boardlist.jsp 를 화면으로 쓸건지.
    	//WebContent > error > error404.jsp 를 화면으로 쓸건지...
    	//webcontent에 jsp를 만들면, 보안상 노출되는 단점이 있다.
    	//http://192.168.0.12:8090/WebServlet_1/board/boardEditOk.jsp
    	//client가 직접 url을 명시할 수 있으면, 안좋다...
    	//실제 프로젝트에서는 jsp파일을 보안폴더(WEB-INF)에 views 폴더에 만들어서 해결
    	//1. WEB-INF >> views >> board >> boardlist.jsp
    	//2. WEB-INF >> views >> error >> error404.jsp
    	//WAS 내부에서 서버 접근 가능 : forward 처리 (서버 코드를 read)
    	
    	//6. view에 저장된 객체를 전달 (forward)
    	///////////////////////////////////
    	request.setCharacterEncoding("UTF-8");
    	//1.요청받기 (command)
    	//http://192.168.0.12:8090/WebServlet_1/board?cmd=list
    	String cmd = request.getParameter("cmd");
    	//2.요청판단 (업무정의)
    	String viewpage = null;
    	//cmd > null > error.jsp 뷰
    	//cmd > boardlist > list.jsp 뷰
    	//cmd > boardwrite > write.jsp 뷰
    	if(cmd == null) {
    		viewpage="/error/error.jsp";
    	}else if(cmd.equals("boardlist")) {
    		viewpage="/board/boardlist.jsp";
    		//실제 업무 처리
    		//DB 연결.. select해서 결과 가져오고,
    		//가져온결과를 객체에 담고, 저장하고...
    		//boardDao dao = new boardDao(); 객체만들고
    		//List<board> boardlist = dao.selectBoardList();
    		//request.setAttribute("list",boardlist) 저장하고
    		//forward > view 전달 > boardlist.jsp > 
    		//EL & JSTL
    		//<c:set var ="list" value="<%= request.getAttribute("list")%>"
    		//<c:forEach 로 화면 출력..
    	}else if(cmd.equals("boardwrite")) {
    		viewpage="/board/boardwrite.jsp";
    	}else if(cmd.equals("login")) { //실개발에서는 보안폴더
    		viewpage="/WEB-INF/views/login/login.jsp"; //실개발에서 이렇게 사용..
    	}else {
    		viewpage="/error/error.jsp";
    	}
    	//3.결과저장
        //List<Emp>list = new ArrayList<>();
    	//list.add (new Emp(2000,"김유신"));
    	//request.setAttribute("emplist",list);
    	
    	//4.view지정
    	RequestDispatcher dis = request.getRequestDispatcher(viewpage);
    	
    	//5.data전달. (forward)
    	dis.forward(request, response);
    	
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doProcess(request,response,"GET");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		doProcess(request,response,"POST");
	}

}
