<%@ page language="java" import="java.util.*" pageEncoding="US-ASCII"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String customer_name=(String)session.getAttribute("customer_name");
String customer_id=(String)session.getAttribute("customer_id");
String ac_no=(String)session.getAttribute("ac_no");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>welcome</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="shortcut icon" href="assets/images/gt_favicon.jpeg">
	
	<link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
	<link rel="stylesheet" href="assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets/css/font-awesome.min.css">

	<!-- Custom styles for our template -->
	<link rel="stylesheet" type="text/css" href="table.css">
	<link rel="stylesheet" href="assets/css/bootstrap-theme.css" media="screen" >
	<link rel="stylesheet" href="assets/css/main.css">
	<!-- <script type="text/javascript">
        function noBack()
         {	
             window.history.forward();
         }
        noBack();
        window.onload = noBack;
        window.onpageshow = function(evt) { if (evt.persisted) noBack() }
        window.onunload = function() { void (0) }
    </script> -->
  </head>
  
  <body class="home" onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">
	<jsp:include page="logout_navbar.jsp"></jsp:include>	
	<!-- Header -->
	<header id="head">
		<div class="container">
			<div class="row">
				<h1 class="lead">Welcome <%= customer_name %></h1>
				<p class="tagline">Commerce traces its roots back to 1865 and today operates in five states</p>
				<p><a href="https://www.commercebank.com/pdfs/forbes-2014.pdf?ref=bannerAd" class="btn btn-default btn-lg" role="button">MORE INFO</a></p>
			</div>
		</div>
	</header>
	<!-- /Header -->
	<!-- container -->
	<div class="container">
		<div class="row">
		<article class="col-xs-12 maincontent">
					<header class="page-header">
						<h1 class="page-title">Online Banking</h1>
					</header>
					
					<div class="col-md-10 col-md-offset-1 col-sm-2 col-sm-offset-2">
						<div class="panel panel-default">
							<div class="panel-body">
								<h3 class="thin text-center">Customer Operations</h3>
								<hr>
								<div class="row-fluid">
									<div class="col-lg-3">
								  			<input type="hidden" id="customer_id" name="customer_id" value="<%= customer_id %>"/>
								  			<button class="btn btn-action btn_check_bal" type="submit">Account Summary</button>
									</div>
									<div class="col-lg-3 text-right">
										<!-- <form action='withdraw.jsp' method='post'> -->
								  			<button class="btn btn-action btn_withdraw" type="submit" name="withdraw">Withdraw Amount</button>
								  		<!-- </form> -->
									</div>
								</div>
								<div class="col-lg-3 text-right">
									<!-- <form action='deposit.jsp' method='post'> -->
							  			<button class="btn btn-action btn_deposit" type="submit" name="deposit">Deposit Amount</button>
							  		<!-- </form> -->
								</div>
								<div class="col-lg-2 text-right">
									<!-- <form action='transfer.jsp' method='post'> -->
							  			<button class="btn btn-action btn_transfer" type="submit" name="transfer">Transfer Funds</button>
							  		<!-- </form> -->
								</div>
							</div>
						</div>
					</div>
	
					</div>
					
				</article>
				<!-- /Article -->
				<div id="check_balance">
				</div>
				<div id="withdraw">
				</div>
				<div id="deposit">
				</div>
				<div id="transfer">
				</div>
				
  		</div>
  		</div>

  <jsp:include page="footer.jsp"></jsp:include>		

	<!-- JavaScript libs are placed at the end of the document so the pages load faster -->
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
	<script src="assets/js/headroom.min.js"></script>
	<script src="assets/js/jQuery.headroom.min.js"></script>
	<script src="assets/js/template.js"></script>
	<script>
		$(document).ready(function(){
		 var customer_id = $("#customer_id").val();	 
			$(".btn_check_bal").click(function(){
				$("#deposit").hide();
				$("#transfer").hide();
				$("#withdraw").hide();
				$("#check_balance").show();
				$( "#check_balance" ).load( "CheckBalanceAction", { customer_id: customer_id }, function() {
				});
			});
			
			$(".btn_withdraw").click(function(){
				$("#check_balance").hide();
				$("#deposit").hide();
				$("#trasfer").hide();
				$("#withdraw").show();
				$( "#withdraw" ).load( "withdraw.jsp",function() {
					$(".ac_no").val("<%= ac_no %>");
					$(".ac_no").attr("readonly", true);
				});
			});
			
			$(".btn_deposit").click(function(){
				$("#check_balance").hide();
				$("#withdraw").hide();
				$("#transfer").hide();
				$("#deposit").show();
				$( "#deposit" ).load( "deposit.jsp", function() {
					$(".ac_no").val("<%= ac_no %>");
					$(".ac_no").attr("readonly", true);
				});
			});
			
			$(".btn_transfer").click(function(){
				$("#check_balance").hide();
				$("#withdraw").hide();
				$("#deposit").hide();
				$("#transfer").show();
				$( "#transfer" ).load( "transfer.jsp", function() {
					$(".ac_no").val("<%= ac_no %>");
					$(".ac_no").attr("readonly", true);
				});
			});
		});
	</script>
  </body>
</html>
