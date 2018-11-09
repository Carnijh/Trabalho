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
    <div class="jumbotron">
        <div align="center" class="container">
            <h1> Menu Principal </h1>
            <p> 
                <a href = "<jsp:getProperty name = "rotator" property = "link" />">
                    <img src = "<jsp:getProperty name = "rotator" property = "image" />" alt = "advertisement" />
                </a>
            </p>
            <form action="Controller" method="POST">
                <button name='name' value='Usuarios' type='submit' class="btn btn-dark">Lista de Usuários</button>
            </form><br />
            <form action="Controller" method="POST">
                <button name='name' value='Sair' type='submit' class="btn btn-dark">Sair</button>
            </form><br />
            <p><%=request.getRemoteAddr()%></p>
        </div>
    </div>
<%@include file="rodape.jsp"%>

<%
    } else {
        response.sendRedirect("./index.jsp");
    }
%>