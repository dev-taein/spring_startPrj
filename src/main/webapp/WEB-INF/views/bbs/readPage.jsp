<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
    
<%@ include file="../include/header.jsp" %>

    <!-- main -->
     <!--main content start-->
      <section>
          <section class="wrapper">
          	<h3><i class="fa fa-angle-right"></i> 게시글 조회 페이지</h3>
          	<!-- role 속성: 
          		.HTML5에서 새롭게 추가된 속성 
          		.ARIA(Accessible Rich Internet Application)라는 HTML5의 상세 규격 중 하나
          		.시각장애인을 위해 만들어진 기능(시각장애인이 컴퓨터의 리더기를 사용해서 웹 페이지를 읽을 때 "해당 부분이 form이다"라고 정의해주는 것
          		.role은 필수적인 요소는 아니지만 화면용 리더기를 이용해야하는 사람들에게도 불편함이 없는 사이트를 제공하고자 이 속성을 이용함
          		-->
          	<form role="form" method="post">
          		<input type="hidden" name="bid" value="${bbsVO.bid}" />
          		<input type="hidden" name="page" value="${pCri.page}" />
          		<input type="hidden" name="numPerPage" value="${pCri.numPerPage }" />
          	</form>
          	<div class="row mt">
          		<div class="col-lg-12">
                  <div class="form-panel">
                  <form class="form-horizontal style-form" method="post">
                  	  <h4 class="mb"><i class="fa fa-angle-right"></i>조회 내용</h4>
                          <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">제목</label>
                              <div class="col-sm-10">
                                  <input type="text" class="form-control" name="subject" value="${bbsVO.subject }" readonly>
                              </div>
                          </div>
                          <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">내용</label>
                              <div class="col-sm-10">
                                      <textarea class="form-control" name="content" rows="4" readonly>${bbsVO.content }</textarea>                              
                              </div>
                          </div>
                          <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">작성자</label>
                              <div class="col-sm-10">
                                  <input type="text" name="writer" class="form-control" value="${bbsVO.writer }" readonly>
                              </div>
                          </div>     
	          	  	</form>
                  </div><!-- form - pannel --> 
                   <div class="form-group">
           			<div class="col-sm-12" align="center"> 
		          		<button type="submit" id="btn_modify" class="btn btn-primary">수정하기</button>&nbsp;&nbsp;
		          		<button type="submit" class="btn btn-danger">삭제하기</button>&nbsp;&nbsp;
		          		<button type="submit" class="btn btn-info">목록으로</button>
	          		</div>
	          	  </div>  
	          	  <script type="text/javascript">
	          	  	//$는 jQuery의 의미
	          	  	//현재 이 문서가 준비 되면 다음 function을 실행한다.라는 뜻
	          	  	$(document).ready(function(){
	          	  		var frmObj = $("form[role='form']"); //form를 선택하는데 role이 form인 폼만 선택하겠다
	          	  		console.log("폼태그입니다.");
	          	  		
	          	  		/*$("#btn_modify").on("click", function(){ //id가 btn_modify인것을 on(이벤트처리) 하겠다.
	          	  			frmObj.attr("action", "/start/bbs/modify"); //frmObj.attr에 action을 /bbs/modify로 하겠다.
	          	  			frmObj.attr("method", "get");
	          	  			frmObj.submit();
	          	  		});*/
	          	  		
	          	  		
	          	 	 	$("#btn_modify").on("click", function(){ //id가 btn_modify인것을 on(이벤트처리) 하겠다.
	         	  			frmObj.attr("action", "/start/bbs/modifyPage"); //frmObj.attr에 action을 /bbs/modify로 하겠다.
	         	  			frmObj.attr("method", "get");
	         	  			frmObj.submit();
	         	  		});
	          	  		
	          	  		/*$(".btn-danger").on("click", function(){
	          	  			frmObj.attr("action", "/start/bbs/delete"); 
	          	  		//method선언을 안하면 post방식이 됨 왜냐면 컨트롤러에서 post를 지정하였고 form에서 method방식이 post이기때문
	          	  			frmObj.submit();
	          	  		});*/
	         		 
	          	  		$(".btn-danger").on("click", function(){
	          	  		//위에서 폼태그에서 hidden으로 page,numPerPage, bid를 넘겨 줘야하므로 method방식을 get으로 바꿔야함
	        	  			frmObj.attr("method","get");
	          	  			frmObj.attr("action", "/start/bbs/delPage"); 
	        	  			frmObj.submit();
	        	  		});
	          	  		
	          	  		$(".btn-info").on("click", function(){
	          	  			//self.location="/start/bbs/list";
	          	  			frmObj.attr("method","get");
	          	  			frmObj.attr("action", "/start/bbs/pageList");
	          	  			frmObj.submit();
	          	  		});
	          	  	});
	          	  </script> 
          		</div><!-- col-lg-12-->      	
          	</div><!-- /row -->          	
		</section><! --/wrapper -->
      </section><!-- /MAIN CONTENT -->
    
<%@include file="../include/footer.jsp" %>