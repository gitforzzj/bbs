<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>用户管理</title>
        <script type="text/javascript">
            function confirmDel()
            {
                if (!confirm("您确认要删除吗？此删除为级联删除？删除操作不可恢复！")) {
                    return false;
                }
                return true;
            }
        </script>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pagination.css" />
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/pagination.js"></script>
        <style type="text/css">
            table{ border-collapse: collapse; text-align: center; width: 98%; }
            th{ width: 150px;}
            tr,td{  border: 1px solid silver; line-height:20px; }
            th,tr, td{ border-left: none; border-right: none; padding-bottom:5px;}
            th:hover,td:hover,tr:hover{ background-color: #F0F0F0;}
            #banner{  background: url('<%=request.getContextPath()%>/images/banner.jpg') no-repeat; width: 100%; height:180px; position:relative; left: 15px; top: -5px;  margin-bottom:0;}
            #displayPagination{ margin-top:20px;}
            #displayPagination > div { display:inline-block; padding:10px; margin-top:10px; }
            #nav{ margin:30px;}
             #warp{ width:980px; margin:0 auto; }
        </style>
    </head>
    <body>
    
        <center>
         <h1><a href="<%=request.getContextPath() %>/UserManagerment/addUser.jsp"><font size="10" color="blue">添加用户</font></a></h1>
            <s:if test="#request.result=='Mok'">
                <h4 style="color:red;"><em>修改成功,您可以继续操作...</em></h4>
            </s:if>
            <s:elseif test="#request.result=='Dok' ">
                  <h4 style="color:red;"><em>删除成功,您可以继续操作...</em></h4>
            </s:elseif>
       	 <div>
            <!-- 帖子列表 -->
            <table style="width:98%;">
                <tr style="background-color:#E7EFEF;">
                    <th style="width:10px;"></th><th style="text-align:left;">账号</th>
                    <th>昵称</th>
                    <th>密码</th>
                    <th>姓名</th>
                    <th>QQ</th>
                    <th>email邮箱</th>
                    <th>专业</th>
                    <th>班级</th>
                    <th>操作</th>
                </tr> 
                
                <s:iterator value="user" status="st" id="row">
                    <tr>
                    <s:if test="#row.photoPath!=null">
                    	<td><img width="30" high="30" src="<%=request.getContextPath()%>/upload/<s:property value="photoPath"/>" style="display: inline-block; margin:4px 5px 0px 0px;" /></td>
                    </s:if>
                    <s:else>
                    	<td><img width="30" high="30" src="<%=request.getContextPath()%>/images/bbsPhoto.jpg"/>" style="display: inline-block; margin:4px 5px 0px 0px;" /></td>
                    </s:else>
                        <td style="text-align: left;" ><a href="admin!modifyUserPage.action?sid=<s:property value="id" />"><s:property value="nickName" /></a></td>
                        	<td><s:property value="stuNum" /> </td>
                      		<td><s:property value="password" /> </td>
                    		<td><s:property value="realName" /></td>
                    		<td><s:property value="qq" /></td>
                    		<td><s:property value="email" /></td>
                        	<td><s:property value="major" /> </td>
                       		<td><s:property value="className" /> </td>
                       		<td>
                            	<a href="admin!modifyUserPage.action?sid=<s:property value="#row.id"/>">修改</a>
                            	<a href="admin!delUser.action?sid=<s:property value="#row.id"/>" onclick="return confirmDel()">删除</a>
                        </td>
                        <td>
                            <!-- <s:property value="publishTime" /> -->  
                            <s:date format="yyyy-MM-dd hh:mm:ss" name="publishTime" />
                        </td>
                        
                    </tr>
                </s:iterator>
            </table>     
    </center>
    </div>
    <!-- 生成分页 -->
    <div style="padding-left:30px; margin-top: 20px;">
        <img src="<%=request.getContextPath()%>/images/pn_post.png" style="cursor: pointer;" 
       		 onclick="javascript:location.href='<%=request.getContextPath()%>/post!preparePost.action?bid=<s:property value="%{#request.bid}" /> ' " />
    </div>
    <center>
        <div id="displayPagination">    
            <script type="text/javascript">
                var pg = new showPages('pg');
                var total = '<s:property value="count" />';
                var pageSize = '<s:property value="pageSize" />';
                if (total%pageSize==0) {
                    pg.pageCount= total/pageSiz;
                }
                pg.pageCount = total / pageSize + 1 ;  // 定义总页数(必要)
                //pg.argName = 'p';  // 定义参数名(可选,默认为page)
     
                pg.printHtml(2);
                pg.printHtml(5);
            </script>
        </div>
       </center>
    </body>
</html>
