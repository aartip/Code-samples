<%@ page language="java" import="java.util.*" pageEncoding="US-ASCII"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
  <body>
  <div class="container">
  <div class="row">
			<!-- Article main content -->
			<article class="col-xs-12 maincontent">
				<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
					<div class="panel panel-default">
						<div class="panel-body">
							<h3 class="thin text-center">Please Fill the Withdraw Form</h3>
							<hr>
							 <form action="WithdrawAction" method="post"> 
								<div class="top-margin">
									<label>Transaction Id <span class="text-danger">*</span></label>
									<input type="text" class="form-control" name="t_id" required autofocus>
								</div>
								<div class="top-margin">
									<label>Account No <span class="text-danger">*</span></label>
									<input type="text" class="form-control ac_no" name="ac_no" required>
								</div>
								
								<div class="top-margin">
									<label>Withdraw Amount <span class="text-danger">*</span></label>
									 <div class="input-group">
					                    <input type="text" class="form-control" placeholder="Amount" name="w_amount" required autofocus>
                    					<span class="input-group-addon">.00</span>
					                </div>
								</div>
								<div class="top-margin">
									<label>Memo <span class="text-danger"></span></label>
									<input type="text" class="form-control" name="memo">
								</div>

								<hr>

								<div class="row">
									<div class="col-lg-12 text-right">
										<button class="btn btn-action" type="submit">Withdraw</button>
									</div>
								</div>
							</form> 
						</div>
					</div>
				</div>
			</article>
   </div>
  </body>
