<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false"%>

<fmt:setLocale value="${param.lang}" />
<fmt:setBundle basename="/localisation/messages" />

<html lang="${param.lang}">
<body>

        <ul>
                <li><a href="?lang=en"><fmt:message key="label.lang.en" /></a></li>
                <li><a href="?lang=de"><fmt:message key="label.lang.de" /></a></li>
        </ul>
        <h1><fmt:message key="message.title"/></h1>
        <fmt:message key="message.description"/>

        <p><fmt:message key="endpoints.message" /></p>
        <ul>
                <li><fmt:message key="skillEndPoint.message" /></li>
                <li><fmt:message key="accountEndPoint.message" /></li>
                <li><fmt:message key="developerEndPoint.message" /></li>
        </ul>
        <br>
        <p><fmt:message key="diagramS.message" /></p>
        <p><img src="img/sequenceJdbcDeveloperRepository.png" alt="sequence diagram for JdbcDeveloperRepository requests"></p>
        <br>

</body>
</html>