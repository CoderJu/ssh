<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2017/10/8
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<table>
    <tr>
        <td> 序号 </td>
        <td> 商品名称  </td>
        <td> 商品图片  </td>
        <td>小计</td>
        <td>数量</td>
    </tr>
    <s:iterator value="list" var="oi" status="status">
    <tr>
        <td> <s:property value="#status.count"/>  </td>
        <td> <s:property value="#oi.product.pname"/>  </td>
        <td> <img src=" ${pageContext.request.contextPath}/<s:property value="#oi.product.image"/>">  </td>
        <td><s:property value="#oi.subtotal"/></td>
        <td><s:property value="#oi.count"/></td>
    </tr>
    </s:iterator>
</table>
