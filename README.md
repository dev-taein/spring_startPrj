# Spring  댓글 게시판 프로젝트 (개인 프로젝트)
## 목적 ##
+ 웹 개발자의 필수요소 Spring
+ Spring프레임워크 사용 경험을 극대화
+ Spring프레임워크의 기술 습득
+ 웹 개발자로써 한 단계 더 성장하기 위한 프레임워크에 대한 이해와 기술 연마
------------
# 개발 환경 및 사용한 언어
> Front-End
+ Bootstrap v3.2.0
+ jQuery v1.10.2
+ CSS 3
+ HTML 5
> Back-End
+ Spring v4.3.1.RELEASE
+ Maven v2.9
+ MyBatis v3.2.8
+ JDK v1.8
+ Tomcat v9.0
+ MySQL v8.0
------------
# 개발 기간 - 2021.07.20 ~ 2021.08.18총 22일 (주말 제외)
- 07.20~07.21 개발 환경 셋팅 : jUnit, MySQL, Tomcat, MyBatis, Bootstrap
- 07.21~07.23 동작 테스트 : jUnit Mysql 연결, Tomcat없이 Controller 테스트, DAO 동작 테스트
- 07.26~07.30 DB 생성 : 테이블 생성 및 XML Mapper 작성, SQLSessionTemplate설정
- 08.02~08.06 반응형 웹페이지 뷰 구현 하기 : 게시글 리스트, 등록, 수정, 삭제 구현
- 08.09~08.11 페이징 처리 : 페이징 처리 계산하기, 객체 만들기, 컨트롤러 구현
- 08.12~08.13 검색 기능 구현 : 검색 처리 jUnit, XML Mapper, 수정, 삭제 처리
- 08.16~08.18 REST 방식의 댓글 구현 : REST + Ajax를 이용한 댓글 수정, 삭제, 등록
------------
# 설계구조 및 구축 흐름도
![github3](https://user-images.githubusercontent.com/77142806/114081913-1f4e6c80-98e8-11eb-8ba8-10227ecb7f05.PNG)
![github4](https://user-images.githubusercontent.com/77142806/114081914-1f4e6c80-98e8-11eb-9cab-6e90e503b431.PNG)
------------
# Front-End 작동 화면 미리 보기
------------
#### 상단 헤더 메뉴바 기능
> 일반 사용자가 로그인 했을 경우와 어드민 관리자 계정으로 로그인 했을 경우 상단에 상품 등록, 수정, 삭제 메뉴를 추가함(JSTL태그)
+ 관리자 계정 
![menu1](https://user-images.githubusercontent.com/77142806/130346429-61d7eb0b-e512-41c8-8361-f0c811937a42.PNG)
+ 일반 사용자 계정
![menu2](https://user-images.githubusercontent.com/77142806/130346430-b8d036d8-6553-4eee-bac3-8a032b8b0078.PNG)
```
<c:choose>
	<c:when test="${empty sessionId}">
	<li class="nav-item"><a class="nav-link" href="<c:url value="/members/loginMember.jsp" />">로그인</a></li>
	<li class="nav-item"><a class="nav-link" href="<c:url value="/members/addMember.jsp" />">회원가입</a></li>
	</c:when>
	<c:otherwise>
		<li style="padding-top: 7px; color: white"><%= sessionId %>[님]</li>
		<li class="nav-item"><a class="nav-link" href="<c:url value="/members/logoutMember.jsp" />">로그아웃</a></li>
		<li class="nav-item"><a class="nav-link" href="<c:url value="/members/updateMember.jsp" />">회원 수정</a></li>
	</c:otherwise>
</c:choose>
				
<c:choose>
	<c:when test="${sessionId ne 'admin'}">
		<li class="nav-item"><a href="${pageContext.request.contextPath}/product/products.jsp" class="nav-link">상품 목록</a></li>
	</c:when>
	<c:otherwise>
		<li class="nav-item"><a href="${pageContext.request.contextPath}/product/products.jsp" class="nav-link">상품 목록</a></li>
		<li class="nav-item"><a href="${pageContext.request.contextPath}/product/addProduct.jsp" class="nav-link">상품 등록</a></li>
		<li class="nav-item"><a href="${pageContext.request.contextPath}/product/editProduct.jsp?edit=update" class="nav-link">상품 수정</a></li>
		li class="nav-item"><a href="${pageContext.request.contextPath}/product/editProduct.jsp?edit=delete" class="nav-link">상품 삭제</a></li>
	</c:otherwise>
</c:choose>
<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/BoardListAction.do?pageNum=1">게시판</a></li>
```
#### index 페이지, Login 및 회원가입 페이지
![front1](https://user-images.githubusercontent.com/77142806/130345353-dcf90fd6-6325-4c22-bfc9-24efd3b22eac.gif)
#### 상품 목록, 주문 페이지
![front2](https://user-images.githubusercontent.com/77142806/130345564-ff0965e9-7a26-4ca2-9643-373b247c33f5.gif)
#### 게시판 목록 페이지
> 로그인을 하지 않았다면 게시글을 읽지 못하도록 설정
```
<script type="text/javascript">
	function checkForm() {
		if(${sessionId == null}){
			alert("로그인을 하셔야 작성 할 수 있습니다.");
			return false;
		}
		//로그인이 되었다면
		location.href="./BoardWriteForm.do?id=<%= sessionId %>";
	}
	
	function loginForm() {
		if(${sessionId == null}){
			alert("로그인을 해야 게시글을 볼 수 있습니다.");
			return false;
		}
	}
</script>
```
![front3](https://user-images.githubusercontent.com/77142806/130345571-1c26de71-e8fa-422e-b3b2-f0942a91cd36.gif)  
#### 로그인, 로그아웃 페이지
![front4](https://user-images.githubusercontent.com/77142806/130345885-9a623c97-2db0-4d48-bdc0-483b617b4ac8.gif)
#### 회원 정보, 상품 등록 페이지
> 상품을 등록 유효성 검사
```
/* 상품 등록 유효성 검사 */
function checkAddProduct(){
	var productId = document.getElementById("productId");
	var pname = document.getElementById("pname");
	var unitPrice = document.getElementById("unitPrice");
	var unitsInStock = document.getElementById("unitsInStock");
	
	//상품 ID check
	if(!check(/^P[0-9]{4,11}$/, productId, 
		"[상품 코드]\nP와 숫자를 조합하여 5~12자까지 입력하세요.\n" + "반드시 첫 글자는 P로 시작해주세요.")){
			return false;
		}
		
	//상품명 check
	if(pname.value.length < 4 || pname.value.length > 12){
		alert("[상품명]\n최소 4자에서 최대 11자까지 입력해주세요.");
		name.select();
		name.focus();
		return false;
	}
 ```
![front5](https://user-images.githubusercontent.com/77142806/130346109-dd85fc23-bcb9-42ca-84f9-e6123f281261.gif)
#### 상품 수정, 삭제 페이지
![front6](https://user-images.githubusercontent.com/77142806/130345881-938b247f-3ace-4df9-b317-6423097e2232.gif)
#### 로그인 성공 시 게시글  읽기 페이지
![front7](https://user-images.githubusercontent.com/77142806/130345882-bf8e19f7-b501-44ab-b98f-195602ee088b.gif)
------------
# Back-End 기능
------------
### Product (상품)
#### 상품 등록  
![상품등록](https://user-images.githubusercontent.com/77142806/130356397-9fcaa448-ff7f-4e94-9bf9-ad4e071fdaac.gif)
#### 상품 주문
![상품주문](https://user-images.githubusercontent.com/77142806/130356394-f3a1d409-c420-467f-b10b-8bd5f170f4d3.gif)
#### 상품 수정
![상품수정](https://user-images.githubusercontent.com/77142806/130356404-8154eb75-9eff-4474-8f15-729af1cf52f3.gif)
#### 상품 삭제
![상품삭제](https://user-images.githubusercontent.com/77142806/130356399-cf113c26-743a-4c99-9fbb-b2cdb33ac3ee.gif)

###Board (게시판)
#### 게시글 등록
> Command
```
public class BWriteCommand implements BCommand {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		BoardDAO bDao = BoardDAO.getInstance();
		
		BoardDTO board = new BoardDTO();
		board.setId(request.getParameter("id"));
		board.setName(request.getParameter("name"));
		board.setSubject(request.getParameter("subject"));
		board.setContent(request.getParameter("content"));
		
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy/MM/dd(HH:mm:ss");
		String regist_day = sFormat.format(new Date());
		board.setRegist_day(regist_day);
		board.setHit(0);
		board.setIp(request.getRemoteAddr());
		
		//DB에 저장하는 메서드 호출
		bDao.insertBoard(board);
	}
}

```
> Controller
```
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("actionDo");
		
		BCommand com = null;
		String viewPage = null;
		
		//getRequestURI()는 요청된 전체 uri를 가져온다.
		String uri = request.getRequestURI();
		System.out.println("URI : " + uri);
		
		//getContextPath()는 프로젝트명이 리턴된다.
		String contextPath = request.getContextPath();
		System.out.println("contextPath : " + contextPath);
		
		//직접 실행되어야할 파일의 이름을 얻어내는 것이다.
		String command = uri.substring(contextPath.length());
		System.out.println("command : " + command);
		
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		
		//command패턴에 따라서 분기를 하는 코드
		//DB에 저장되어 있는 모든 게시글을 출력하는 부분
		if(command.equals("/BoardListAction.do")) {    
			System.out.println("------------------------------");
			System.out.println("/BoardListAction.do페이지 호출");
			System.out.println("------------------------------");
			com = new BListCommand();
			com.execute(request, response);
			System.out.println("BoardListAction의 execute() 실행 완료");
			viewPage = "./board/list.jsp";
		}
		
		//회원의 로그인 정보를 가져오는 부분
		else if (command.equals("/BoardWriteForm.do")) { 
			System.out.println("------------------------------");
			System.out.println("/BoardWriteForm.do페이지 호출");
			System.out.println("------------------------------");
			com = new BWriteFormCommand();
			com.execute(request, response);
			System.out.println("BoardWriteForm의 execute() 실행 완료");
			viewPage = "./board/writeForm.jsp";
		}
		
		//게시글을 쓰고 db에 저장하기
		else if (command.equals("/BoardWriteAction.do")) { 
			System.out.println("------------------------------");
			System.out.println("/BoardWriteAction.do페이지 호출");
			System.out.println("------------------------------");
			com = new BWriteCommand();
			com.execute(request, response);
			System.out.println("BoardWriteForm의 execute() 실행 완료");
			viewPage = "/BoardListAction.do";
		}
		
```
![게시글등록](https://user-images.githubusercontent.com/77142806/130357137-fc9e96ad-463c-440a-ba14-be4a42e501cf.gif)
#### 게시글 수정
![게시글 수정](https://user-images.githubusercontent.com/77142806/130357134-3472afdd-1b23-4ef8-b198-0396c053c6ff.gif)
#### 게시글 삭제
![게시글 삭제](https://user-images.githubusercontent.com/77142806/130357133-26204da4-3323-4db9-8b85-4fa1fdb95b0c.gif)
------------
# 주요 이슈
* MySQL TimeZone 예외 발생 MySQL의 TimeZone시간이 한국 시간으로 설정이 되어 있지 않을 경우에 발생하는 문제입니다. Mysql의 TimeZone의 시간 값을 한국 시각으로 설정하면 됩니다.
* 상품 등록 시 Image 업로드에 관한 예외 발생 이유는 이미지 업로드 시 이미지 저장 위치의 주소가 상대주소일 경우 발생하는 예외였습니다. 이미지 업로드 주소를 절대 주소로 수정하였습니다.
* 제일 자주 발생한 예외는 역시 오타로 인한 예외였습니다. 제일 해결하기 쉬우면서도 제일 어려운 부분입니다.
------------
# 느낀점
1. MVC패턴을 적용하기가 스프링프레임워크에 비해 어렵다.
2. JSP는 웹 페이지 영역 안에서 환경을 구성하는 느낌이 굉장히 강했지만 간단한 웹사이트를 구축하기엔 좋다고 생각했습니다.
------------

 
