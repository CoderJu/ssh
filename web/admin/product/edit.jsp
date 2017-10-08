<%@ taglib prefix="S" uri="/struts-tags" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<HTML>
<HEAD>
	<meta http-equiv="Content-Language" content="zh-cn">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<LINK href="${pageContext.request.contextPath}/css/Style1.css" type="text/css" rel="stylesheet">
</HEAD>

<body>
<form id="userAction_save_do" name="Form1" action="${pageContext.request.contextPath}/adminProduct_update.action" method="post" enctype="multipart/form-data">
	&nbsp;
	<input type="hidden" name="pid" value="<S:property value="model.pid"/>"/>
	<input type="hidden" name="image" value="<S:property value="model.image"/>"/>
	<table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
		<tr>
			<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
				height="26">
				<strong><STRONG>编辑商品</STRONG>
				</strong>
			</td>
		</tr>

		<tr>
			<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
				商品名称：
			</td>
			<td class="ta_01" bgColor="#ffffff" colspan="3">
				<input type="text" name="pname" value="<S:property value="model.pname"/>" id="userAction_save_do_logonName" class="bg"/>
			</td>
		</tr>

		<tr>
			<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
				二级分类名称：
			</td>
			<td class="ta_01" bgColor="#ffffff" colspan="3">
				<select name="categorySecond.csid">
					<S:iterator value="csList" var="cs">
						<option value="<S:property value="#cs.csid"/>" <S:if test="#cs.csid == model.categorySecond.csid">selected</S:if>   ><S:property value="#cs.csname"/> </option>
					</S:iterator>
				</select>
			</td>
		</tr>
		<tr>
			<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
				市场价格：

			</td>
			<td class="ta_01" bgColor="#ffffff" colspan="3">
				<input type="text" name="market_price" value="<S:property value="model.market_price"/>" id="market_price" class="bg"/>
			</td>
		</tr>
		<tr>
			<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
				商城价格：
			</td>
			<td class="ta_01" bgColor="#ffffff" colspan="3">
				<input type="text" name="shop_price" value="<S:property value="model.shop_price"/>" id="shop_price" class="bg"/>
			</td>
		</tr>

		<tr>
			<td width="18%" align="center" bgColor="#f5fafe" class="ta_01" colspan="2">
				是否热门：
			</td>
			<td class="ta_01" bgColor="#ffffff" colspan="3">
				<select name="is_hot">
					<option value="1" <S:if test="model.is_hot == 1">selected</S:if> >是</option>
					<option value="0" <S:if test="model.is_hot == 0">selected</S:if>>否</option>
				</select>
			</td>
		</tr>

		<tr>
			<td width="18%" align="center" bgColor="#f5fafe" class="ta_01" colspan="2">
				商品描述：
			</td>
			<td>
				<textarea rows="5" cols="20" name="pdesc"  ><S:property value="model.pdesc"/></textarea>
			</td>
		</tr>
		<tr>
			<td width="18%" align="center" bgColor="#f5fafe" class="ta_01" colspan="2">
				商品图片：
			</td>
			<td>
				<input type="file" name="upload"/>
			</td>
		</tr>
		<tr>
			<td class="ta_01" style="WIDTH: 100%" align="center"
				bgColor="#f5fafe" colSpan="4">
				<button type="submit" id="userAction_save_do_submit" value="确定" class="button_ok">
					&#30830;&#23450;
				</button>

				<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
				<button type="reset" value="重置" class="button_cancel">&#37325;&#32622;</button>

				<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
				<INPUT class="button_ok" type="button" onclick="history.go(-1)" value="返回"/>
				<span id="Label1"></span>
			</td>
		</tr>
	</table>
</form>
</body>
</HTML>