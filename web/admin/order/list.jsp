<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script language="javascript" src="${pageContext.request.contextPath}/js/register.js"></script>
		<script type="text/javascript">

			function showDetial(oid){
                var but =  document.getElementById("but"+oid);
                var div1 =  document.getElementById("div"+oid);
			if (but.value == "订单详情"){
				//创建
                var xhr = createXmlHttp();
                xhr.onreadystatechange = function () {
                    if (xhr.readyState == 4){
                        if (xhr.status == 200){
							div1.innerHTML = xhr.responseText;
                        }
                    }
                }
                xhr.open("GET","${pageContext.request.contextPath}/adminOrder_findOrderItem?oid="+oid+"&time="+new Date().getTime(),true);
                xhr.send(null);
                but.value ="关闭";
			}else{
                but.value="订单详情";
                div1.innerHTML = "";
			}
			}
		</script>
	</HEAD>
	<body>
		<br>
		<form id="Form1" name="Form1" action="${pageContext.request.contextPath}/user/list.jsp" method="post">
			<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>订单 列 表</strong>
						</TD>
					</tr>
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<table cellspacing="0" cellpadding="1" rules="all"
								bordercolor="gray" border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								<tr
									style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

									<td align="center" width="10%">
										序号
									</td>
									<td align="center" width="10%">
										订单编号
									</td>
									<td align="center" width="10%">
										总金额
									</td>
									<td align="center" width="10%">
										下单时间
									</td>
									<td align="center" width="10%">
										订单状态
									</td>
									<td align="center" width="10%">
										顾客姓名
									</td>
									<td align="center" width="10%">
										联系电话
									</td>
									<td align="center" width="10%">
										地址
									</td>
									<td width="10%" align="center">
										订单详情
									</td>
								</tr>
								<s:iterator var="o" value="pageBean.list" status="status">
										<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="10%">
												<s:property value="#status.count"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="10%">
												<s:property value="#o.oid"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="10%">
												<s:property value="#o.total"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="10%">
												<s:property value="#o.ordertime"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="10%">
												<s:if test="#o.state == 1 ">
													未付款
												</s:if>
												<s:if test="#o.state == 2">
												<a href="${pageContext.request.contextPath}/adminOrder_updateState.action?oid=<s:property value="#o.oid"/>">
													已付款待发货
												</a>
												</s:if>
												<s:if test="#o.state == 3 ">
													已发货
												</s:if>
												<s:if test="#o.state == 4">
													交易完成
												</s:if>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="10%">
												<s:property value="#o.name"/>
											</td><td style="CURSOR: hand; HEIGHT: 22px" align="center"
													 width="10%">
											<s:property value="#o.phone"/>
											</td><td style="CURSOR: hand; HEIGHT: 22px" align="center"
													 width="10%">
												<s:property value="#o.addr"/>
											</td>
											<td align="center" style="HEIGHT: 22px">
												<%--<a href="${pageContext.request.contextPath}/adminOrder_edit.action?pid=<s:property value="#p.pid"/>">
													<img src="${pageContext.request.contextPath}/images/i_edit.gif" border="0" style="CURSOR: hand">
												</a>--%>
													<input type="button" value="订单详情" id="but<s:property value="#o.oid"/>" onclick="showDetial(<s:property value="#o.oid"/>)"/>
													<div id="div<s:property value="#o.oid"/>"></div>
											</td>
										</tr>
									</s:iterator>
									<tr>
										<th colspan="5">
											<div class="pagination">
											<span>第<s:property value="pageBean.page" />/<s:property
										value="pageBean.totalPage" />页 </span>
												<s:if test="pageBean.page != 1">
													<a
															href="${ pageContext.request.contextPath }/adminOrder_findAllByPage.action?page=1"
															class="firstPage">&nbsp;</a>
													<a
															href="${ pageContext.request.contextPath }/adminOrder_findAllByPage.action?page=<s:property value="pageBean.page-1"/>"
															class="previousPage">&nbsp;</a>
												</s:if>
												<s:if test="pageBean.page !=1">
												<a class="lastPage"
													   href="${ pageContext.request.contextPath }/adminOrder_findAllByPage.action?page=1">首页</a>
												<a class="nextPage"
												   href="${ pageContext.request.contextPath }/adminOrder_findAllByPage.action?page=<s:property value="pageBean.page-1"/>">上一页</a>
												</s:if>
												<s:if test="pageBean.page != pageBean.totalPage">
												<a class="nextPage"
												   href="${ pageContext.request.contextPath }/adminOrder_findAllByPage.action?page=<s:property value="pageBean.page+1"/>">下一页</a>
												<a class="lastPage"
												   href="${ pageContext.request.contextPath }/adminOrder_findAllByPage.action?page=<s:property value="pageBean.totalPage"/>">尾页</a>
												</s:if>
											</div>
										</th>
									</tr>
							</table>
						</td>
					</tr>
				</TBODY>
			</table>
		</form>
	</body>
</HTML>

