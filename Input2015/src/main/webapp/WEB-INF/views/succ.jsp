<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">  
<title>home</title>  
</head> 
<body>
<font color="red">登陆成功！！！</font><br>  
   
Hello,${username}。<br>
您的访问时间是${time}.<br>
您是第${num}位访问者，您前面的访问者是：
<table cellpadding="5" cellspacing="0" border="1">
<tr>
<th width="300">姓名</th>
<th width="300">访问时间</th>
</tr>
<c:forEach var="a" items="${map}">
<tr align="center">
<td>${a.key }</td>
<td>${a.value }</td>
</tr>
</c:forEach>
</table>  
  
   
</body>
</html>