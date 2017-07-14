<%-- 
    Document   : login
    Author     : LocalizaÊ - Sistema de Posicionamento Interno FAITEC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>Logar - LocalizaÊ</title>

        <link rel="shortcut icon" href="<c:url value="/resources/img/icon.ico"/>">
        <link href='http://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>

        <!-- Bootstrap Core CSS -->
        <link href="<c:url value="/resources/lib/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Catamaran:100,200,300,400,500,600,700,800,900" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Muli" rel="stylesheet">

        <!-- Plugin CSS -->
        <link rel="stylesheet" href="<c:url value="/resources/lib/font-awesome/css/font-awesome.min.css"/>">
        <link rel="stylesheet" href="<c:url value="/resources/lib/simple-line-icons/css/simple-line-icons.css"/>">
        <link rel="stylesheet" href="<c:url value="/resources/lib/device-mockups/device-mockups.min.css"/>">

        <!-- Theme CSS -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/mainn.css"/>">
        <link href="<c:url value="/resources/css/logar.css"/>" rel="stylesheet">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="<c:url value="/resources/js/html5shiv.min.js"/>"></script>
        <script src="<c:url value="/resources/js/respond.min.js"/>"></script>
        <![endif]-->

    </head>
    <body> 
        <br>
    <center>
        <a href="<c:url value="/"/>"><img src="<c:url value="/resources/img/logos/LogoGrande.png"/>" class="img-responsive" alt=""></a>
    </center>
    <br>
    <div class="login-block">
        <a href="<c:url value="/"/>" class="pull-left">Voltar</a>
        <h1>Login</h1>
        <input type="text" value="" placeholder="Email" id="username" />
        <input type="password" value="" placeholder="Senha" id="password" />
        <a id="demoSwal" href="#" class="pull-right" >Recuperar Senha</a>
        <br><br><br>
        <a href="<c:url value="/gerenciador"/>"><button>Entrar</button></a>
    </div>

    <!-- Javascripts-->
    <script src="<c:url value="/resources/js/jquery-2.1.4.min.js"/>"></script>
    <script src="<c:url value="/resources/js/essential-plugins.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/resources/js/plugins/pace.min.js"/>"></script>
    <script src="<c:url value="/resources/js/main.js"/>"></script>

    <script type="text/javascript" src="<c:url value="/resources/js/plugins/bootstrap-notify.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/plugins/sweetalert.min.js"/>"></script>
    <script type="text/javascript">
        $('#demoSwal').click(function () {
            swal({
                title: "Recuperar Senha",
                text: "Digite seu email:",
                type: "input",
                showCancelButton: true,
                closeOnConfirm: false,
                animation: "slide-from-top",
                //inputPlaceholder: "Email"
            },
                    function (inputValue) {
                        if (inputValue === false)
                            return false;

                        if (inputValue === "") {
                            swal.showInputError("Você precisa escrever seu email!");
                            return false
                        }

                        swal("Senha enviada!", "Acesse seu email: " + inputValue, "success");
                    });
        });
    </script>

</body>
</html>
