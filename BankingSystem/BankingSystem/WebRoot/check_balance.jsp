<%@ page language="java" import="java.util.*" pageEncoding="US-ASCII"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String customer_id=(String)session.getAttribute("customer_id");
String account_no=(String)session.getAttribute("acc_no");
float curr_bal=(Float)session.getAttribute("curr_bal");
%>
  
  <body>  
<div id="content">
  
    <table cellspacing="0">
    <tr>
    	<th>Customer Id</th>
    	<th>Account No</th>
    	<th>Current Balance</th>
    </tr>
    <tr>
    	<td><%= customer_id %></td>
    	<td><%= account_no %></td>
    	<td><%= curr_bal %></td>
    </tr>
    </table>
 </div>
  </body>
  
	<script type="text/javascript">
	 $(function() {
			/* For zebra striping */
	        $("table tr:nth-child(odd)").addClass("odd-row");
			/* For cell text alignment */
			$("table td:first-child, table th:first-child").addClass("first");
			/* For removing the last border */
			$("table td:last-child, table th:last-child").addClass("last");
	});
</script>
</html>
