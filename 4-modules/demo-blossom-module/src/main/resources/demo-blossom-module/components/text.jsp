<%--@elvariable id="content" type="info.magnolia.jcr.util.ContentMap"--%>
<%--@elvariable id="damfn" type="info.magnolia.dam.templating.functions.DamTemplatingFunctions"--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://magnolia-cms.com/taglib/templating-components/cms" prefix="cms" %>
<%@ taglib uri="http://magnolia-cms.com/taglib/templating-components/cmsfn" prefix="cmsfn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="blossom-taglib" prefix="blossom" %>

<h1>${content.heading}</h1>
<p>${cmsfn:decode(content).body}</p>