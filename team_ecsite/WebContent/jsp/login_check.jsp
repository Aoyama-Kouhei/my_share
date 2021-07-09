<%
HttpSession ss_login = request.getSession();

if(ss_login.getAttribute("loginUserId") == null){
	response.sendRedirect(request.getContextPath() + "/jsp/index.jsp");
}
%>