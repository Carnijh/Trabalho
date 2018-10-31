<%
    session = request.getSession(false);
    if(session.getAttribute("loggedIn") != null) {
%>
<%@include file="cabecalho.jsp"%>

<jsp:useBean id = "rotator" scope = "session" class = "UFJF.Rotator" />
<% rotator.nextAd(); %>
<meta http-equiv = "refresh" content = "15" />
<title>Menu Principal</title>
</head>
<body>
<%
    String cont = (String)getServletContext().getAttribute("usuarios");
%>
    <p>Usu�rios: <%=cont%></p>
    <div align="center">
        <h1> Menu Principal </h1>
        <form action="Controller" method="POST">
            <button name='name' value='Usuarios' type='submit'>Lista de Usu�rios</button>
        </form>
        <p> 
            <a href = "<jsp:getProperty name = "rotator" property = "link" />">
                <img src = "<jsp:getProperty name = "rotator" property = "image" />" alt = "advertisement" />
            </a>
        </p>
        <form action="Controller" method="POST">
            <button name='name' value='Sair' type='submit'>Sair</button>
        </form>
        <p><%=request.getRemoteAddr()%></p>
    </div>
<%@include file="rodape.jsp"%>

<%
    } else {
        response.sendRedirect("./index.jsp");
    }
%>