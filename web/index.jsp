<%@include file="cabecalho.jsp" %>
<%@page session = "false" %>
<title>Tela de Login</title>
</head>

<!-- se��o de corpo do documento -->
<body>
    <div align="center">

        <h1>Informe Usu�rio e Senha</h1>
        <% // inicia scriptlet                                   

            String msg = (String) request.getAttribute("mensagem");

            if (msg != null && msg.equals("erro")) {
        %> <%-- fim de scriptlet para inserir de dados de template fixa --%> 

        <p>
            Usu�rio ou senha inv�lidos!
        </p>

        <% // continua scriptlet                                

            } // fim do if

        %> <%-- fim de scriptlet para inserir de dados de template fixa --%> 
        <form action = "Controller" method = "post">
            <p>
                <label>Usu�rio:</label>
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