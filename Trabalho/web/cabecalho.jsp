<%@page import="java.text.ParseException" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Date" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<?xml version = "1.0"?>
               
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
        <%
            String format = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
            int contador = (Integer)getServletContext().getAttribute("usuarios");
        %>
    
        <p>Data: <%=format%> &nbsp;&nbsp;Usuários: <%=contador%></p>