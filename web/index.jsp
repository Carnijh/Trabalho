<%@include file="cabecalho.jsp" %>
<%@page session = "false" %>
<title>Tela de Login</title>
</head>

<!-- seção de corpo do documento -->
<body>
    <div align="center">

        <h1>Informe Usuário e Senha</h1>
        <% // inicia scriptlet                                   

            String msg = (String) request.getAttribute("mensagem");

            if (msg != null && msg.equals("erro")) {
        %> <%-- fim de scriptlet para inserir de dados de template fixa --%> 

        <p>
            Usuário ou senha inválidos!
        </p>

        <% // continua scriptlet                                

            } // fim do if

        %> <%-- fim de scriptlet para inserir de dados de template fixa --%> 
        <form action = "Controller" method = "post">
            <p>
                <label>Usuário:</label>
                <input type = "text" name = "usuario" />
            </p>

            <p>
                <label>Senha::&nbsp;&nbsp;&nbsp;</label>
                <input type = "password" name = "senha" />
            </p>

            <button name='name' value='Login' type='submit'>Enviar</button>
        </form>
    </div>

<%@ include file="rodape.jsp"%>