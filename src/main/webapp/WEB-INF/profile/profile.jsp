<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

   <div id="main_container">
		<section class="b_inner">            
            <div class="hori_cont">
                    <span class="profile_img">                    	
                        <c:if test="${ not empty profile.uPropic }">
                        	<img src="propic/${ profile.uPropic }" alt="사진">
                        </c:if>
                    </span>
                    <span class="top">
                       <input onclick="location.href='profileUpdateForm.mvc' " type="button" value="프로필편집">
                       </span>
                    <div class="profile-id">안녕하세요 ${ sessionScope.uId } 님!</div>
            </div>
         		<div class="Mtitle">
         			<div>
						<h3 style="display: inline-block; width: 45%;">My Page </h3>
						<h3 style="display: inline-block; width: 45%; text-align: right; "><a href="postWriteForm.mvc">포스트 쓰기</a></h3>
					</div>
					<hr>
				</div>
				<div class="thumbnails">
         					<div class="box"> 
						<a  class="image fit"><img class ="boxImg"  src="images/pic01.jpg" alt="" /></a>
						<div class="inner">
							<p>test</p>
						</div>
					</div>
				</div>	

           		

        </section>
    </div>
