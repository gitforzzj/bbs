<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>添加用户</title>
    </head>
    <body>
	<center>
		<h1><font color="blue">添加用户</font></h1><p>
		<s:actionmessage/>
		<s:form action="admin!addUser.action" method="post" enctype="multipart/form-data">
			
				<s:textfield name="student.stuNum" label="用户账号"/>
				<s:password name="student.password" label="用户密码"/>
				<s:password name="password1" label="确认密码"/>
				<s:textfield name="student.realName" label="姓名"/>
				<s:textfield name="student.nickName" label="昵称"/>
				<s:textfield name="student.qq" label="QQ"/>
				<s:textfield name="student.email" label="email"/>
				<s:textfield name="student.major" label="专业"/>
				<s:textfield name="student.className" label="班级"/>
				<s:file name="file" label="头像图标上传" />
				<s:submit value="确定"></s:submit>
			
		</s:form>
	</center>
</body>
</html>
