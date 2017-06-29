<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of Tasks</title>

    <style>
        .my_button {
            width:150px;
            height: 30px;
        }


        .tab th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #9999FF;
        }

        .tab td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color:#CCCCFF
        }
        .inp{
            width:350px;
            height: 30px;
        }
        ,rad{
             padding: 5px 5px;
         }
        . rad2{
            padding: 15px 5px;
        }




    </style>

</head>
<body>

<a href="/">Main Page</a>

<br><br>
<form action="/tasks">
    <table width="300">
        <tr><td>
            Status selection: &nbsp; <select name="status">
                <option selected value="all">all</option>
                <option value="done">done</option>
                <option value="not done">not done</option>
            </select>
      &nbsp;  <input class="status" type='submit' value='Search'/>
        </td></tr>
    </table>
</form>

<br>




<h2>Add a Task</h2>

<c:url var="addAction" value="/tasks/add"/>
<form:form action="${addAction}" method="post" modelAttribute="task">
    <table>

        <c:if test="${!empty task.task}">
            <tr>
                <td>
                    <form:label path="id">
                        <spring:message text="ID"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="id" readonly="true" size="8" disabled="true"/>
                    <form:hidden path="id"/>

                </td>
            </tr>
        </c:if>


        <tr>
            <td>
                <form:label path="task">
                    <spring:message text="Task:"/>
                </form:label>
            </td>
            <td>
                <form:input path="task" cssClass="inp"/>
            </td>
        </tr>


        <tr><td>



            Done: &nbsp; &nbsp;   <input id="status" name="status" type="radio" value="yes"
                <c:if test="${task.status=='done'}"> checked="checked"</c:if> />
        </td><td>
            &nbsp; &nbsp; &nbsp; Not Done: &nbsp; &nbsp;   <input id="status2" name="status" type="radio" value="no"
            <c:choose>
                <c:when test="${task.status=='not done'}">
                    checked="checked"
                </c:when>
                <c:when test="${empty task.status}">
                    checked="checked"
                </c:when>
            </c:choose>


        </td></tr>




        <tr><td colspan="2"> <input class="my_button" type="submit" value="<spring:message text="Add Task"/>"/></td></tr>




    </table>
</form:form>











<br>
<h3>List of Tasks</h3>
<c:if test="${!empty holder}">

    <table class="tab" cellspacing="0">
        <thead> <tr>
            <th width="50">ID</th>
            <th width="250">Task</th>
            <th width="100">Status</th>
            <th width="50">Edit</th>
            <th width="60">Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="u" items="${holder.pageList}">

            <tr>
                <td width="50">${u.id}</td>
                <td width="250">${u.task}</td>
                <td width="100">${u.status}</td>
                <td width="50"><a href="<c:url value='/edit/${u.id}'/>">edit</a></td>
                <td width="60"><a href="<c:url value='/remove/${u.id}'/>">delete</a></td>

            </tr>



        </c:forEach>
        </tbody>
    </table>

</c:if>



<br>


<div id="pagination">

    <c:url value="/tasks" var="prev">
        <c:param name="page" value="${page-1}"/>
        <c:param name="status" value="${status}"/>
    </c:url>
    <c:if test="${page > 1}">
        <a href="<c:out value="${prev}" />" class="pn prev">Prev</a>
    </c:if>

    <c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
        <c:choose>
            <c:when test="${page == i.index}">
                <span>${i.index}</span>
            </c:when>
            <c:otherwise>
                <c:url value="/tasks" var="url">
                    <c:param name="page" value="${i.index}"/>
                    <c:param name="status" value="${status}"/>
                </c:url>
                <a href='<c:out value="${url}" />'>${i.index}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <c:url value="/tasks" var="next">
        <c:param name="page" value="${page + 1}"/>
        <c:param name="status" value="${status}"/>
    </c:url>
    <c:if test="${page + 1 <= maxPages}">
        <a href='<c:out value="${next}" />' class="pn next">Next</a>
    </c:if>
</div>






















<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>








</body>
</html>
