<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s"  uri="/struts-tags" %> %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>修改帖子</title>
        <script type="text/javascript" src="<%=request.getContextPath() %>/ckeditor_3.6.2/ckeditor/ckeditor.js"></script>      
        <style type="text/css">
          th:hover,td:hover,tr:hover{ background-color: #F0F0F0;}
        </style>
    </head>
    <body>
    <center>
        <s:url id="modifyPost" action="post!modifyPost.action" >
            <s:param name="pid"><%=request.getParameter("pid") %></s:param>
        </s:url>
        <s:form action="%{modifyPost}">
        <table>
            <tr>
                <td><b>帖子标题：</b></td><td> <input type="text" name="post.name" value="${post.name}" size="80" /></td>
            </tr>
            <tr>
                <td valign="top"><b>帖子内容：</b></td><td> <textarea id="context" cols="70" rows="25" name="post.content"><s:property value="post.content"/> </textarea>  </td>
            </tr>
            <tr>
                <td></td><td><input type="submit"  value="发表" /></td>
            </tr>
        </table>
        </s:form>
         
       </center>
    </body>
</html>
