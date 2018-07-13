<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s"  uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>我的帖子</title>
        <style type="text/css">
           table{ border-collapse: collapse; text-align: center; width: 98%; }
            th{ width: 150px;}
            tr,td{  border: 1px solid silver; }
            th,tr, td{ border-left: none; border-right: none; padding-bottom:5px;}
            th:hover,td:hover,tr:hover{ background-color: #F0F0F0;}
        </style>
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
        <h3 style="padding-left:20px;"><s:property value="#session.student.nickName" />您好，欢迎查看您发布过的帖子：</h3>
    <center>
       
         <!-- 帖子列表 -->
             <table>
                 <tr style="background-color:#E7EFEF;">
                     <th style="width:10px;"></th><th style="text-align:left;">帖子标题</th>
                     <th>作者</th>
                     <th>查看</th>
                     <th>发布时间</th>
                 </tr> 
                 <s:iterator value="myPosts" status="st" id="row">
                <tr>
                    <td><img src="<%=request.getContextPath() %>/images/folder_new.gif" style="display: inline-block; margin:4px 5px 0px 0px;" /></td>
                    <td style="text-align: left;" ><a href="post!viewDetail.action?pid=<s:property value="id" />"><s:property value="name" /></a></td>
                    <s:if test="#row.getStudent()!=null">
                    	<td><s:property value="#row.getStudent().getNickName()" /></td>
                    </s:if>
                    <s:elseif test="#row.getAdmin()!=null">
                    	<td><s:property value="#row.getAdmin().getNickName()" /></td>
                    </s:elseif>
                    <td><s:property value="count" /></td>
                     <td>
                       <!-- <s:property value="publishTime" /> -->  
                         <s:date format="yyyy年MM月dd日 HH时MM分" name="publishTime" />
                     </td>
                </tr>
                </s:iterator>
            </table>     
     </center>    
     <!-- 生成分页 -->
   
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
