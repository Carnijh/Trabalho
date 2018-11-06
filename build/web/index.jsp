<%@include file="cabecalho.jsp" %>
<%@page session = "false" %>
<title>Tela de Login</title>
</head>

<!-- seção de corpo do documento -->
<body>
    <div class="jumbotron">
        <div align="center" class ="container">

            <h1>Informe Usuário e Senha</h1>
            <form action = "Controller" method = "post">

                <div>
                    <label>Usuário:&nbsp;&nbsp;&nbsp;</label>
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
                    Usuário ou senha inválidos!
                </div>

                <% // continua scriptlet                                

                    } // fim do if

                %> <%-- fim de scriptlet para inserir de dados de template fixa --%> 

                <button name='name' value='Login' type='submit' class="btn btn-dark">Enviar</button>
            </form>
        </div>
    </div>

    <%@ include file="rodape.jsp"%>