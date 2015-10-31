<%--@elvariable id="components" type="java.util.Collection"--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://magnolia-cms.com/taglib/templating-components/cms" prefix="cms" %>
<%@ taglib uri="http://magnolia-cms.com/taglib/templating-components/cmsfn" prefix="cmsfn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="blossom-taglib" prefix="blossom" %>

<div id="contentArea">
  <c:forEach items="${components}" var="component">
    <cms:component content="${component}" />
  </c:forEach>
</div>
