 <%@page contentType="text/html" pageEncoding="UTF-8"%>
 <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>修改密码</title>
    </head>
    <body>
    <center>
         <h1><font size="5" color="green">温馨提示：请定期维护您的账户信息</font></h1>
         <br/>
        <h4><font size="5" color="red"><s:actionmessage/></font></h4> 
        <s:form action="student!ModifyPsw.action">
            <s:password name="psw" label="原始密码"></s:password>
            <s:textfield name="newPsw" label="新密码" ></s:textfield>
            <s:textfield name="confirmPsw" label="确认密码"></s:textfield>
            <s:submit value="确认"></s:submit>
        </s:form>
     </center>
    </body>
</html>
