<%@include file="cabecalho.jsp" %>
<%@page session = "false" %>
<title>Tela de Login</title>
</head>

<!-- se��o de corpo do documento -->
<body>
    <div class="jumbotron">
        <div align="center" class ="container">

            <h1>Informe Usu�rio e Senha</h1>
            <form action = "Controller" method = "post">

                <div>
                    <label>Usu�rio:&nbsp;&nbsp;&nbsp;</label>
                    <div><input type = "text" name = "usuario"/></div>
                </div>

                <div>
                    <label>Senha:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                    <div><input type = "password" name = "senha"/></div>
                </div>

                <% // inicia scriptlet                                   
                    String msg = (String) request.getAttribute("mensagem");

                    if (msg != null && msg.equals("erro")) {
                %> <%-- fim de scriptlet para inserir de dados de template fixa --%> 

                <div class="alert alert-warning" role="alert">
                    Usu�rio ou senha inv�lidos!
                </div>

                <% // continua scriptlet                                

                    } // fim do if

                %> <%-- fim de scriptlet para inserir de dados de template fixa --%> 

                <button name='name' value='Login' type='submit' class="btn btn-dark">Enviar</button>
            </form>
        </div>
    </div>

    <%@ include file="rodape.jsp"%>