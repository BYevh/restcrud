        <%@ page contentType="text/html;charset=UTF-8" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
        <%@ page isELIgnored="false"%>

        <fmt:setLocale value="${param.lang}" />
        <fmt:setBundle basename="messages" />

        <html lang="${param.lang}">
        <head>
                <meta charset="UTF-8">
                <title><fmt:message key="message.title"/></title>
        </head>
        <body>

        <ul>
        <li><a href="?lang=en"><fmt:message key="label.lang.en" /></a></li>
        <li><a href="?lang=de"><fmt:message key="label.lang.de" /></a></li>
        </ul>

        <h2><fmt:message key="message.title"/></h2>
        <fmt:message key="message.description"/>


        </body>
        </html>