<%-- 
    Document   : painel
    Author     : LocalizaÊ - Sistema de Posicionamento Interno FAITEC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <jsp:include page="../../Componentes/cabecalhoAdministrador.jsp"/>
    </head>
    
    <body class="sidebar-mini fixed">
        <div class="wrapper">
            
            <jsp:include page="../../Componentes/menuAdministrador.jsp"/> 
            
            <div class="content-wrapper">
                <div class="page-title">
                    <div>
                        <h1><i class="fa fa-dashboard"></i> Painel de Controle</h1>
                        <p> LocalizaÊ - Sistema de Posicionamento Interno Para Eventos</p>
                    </div>
                    <div>
                        <ul class="breadcrumb">
                            <li><i class="fa fa-home fa-lg"></i></li>
                            <li><a href="#">Painel de Controle</a></li>
                        </ul>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <!-- <img src="<c:url value="/resources/img/logos/logo.png"/>" alt="LocalizaÊ" class="img-responsive pull-right"> -->
                            <h3 class="card-title">Olá <span class="user-name"></span></h3>                    
                            <p style="font-size: 16px;">Bem vindo(a) ao Sistema de Gerenciamento do LocalizaÊ. <br><br> Utilze a barra de menus para ter acesso as funcionalidades do sistema.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Javascripts-->
        <script src="<c:url value="/resources/js/jquery-2.1.4.min.js"/>"></script>
        <script src="<c:url value="/resources/js/essential-plugins.js"/>"></script>
        <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
        <script src="<c:url value="/resources/js/plugins/pace.min.js"/>"></script>
        <script src="<c:url value="/resources/js/main.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/plugins/chart.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/plugins/jquery.vmap.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/plugins/jquery.vmap.world.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/plugins/jquery.vmap.sampledata.js"/>"></script>

         <script>
            $(".user-name").text(ControladorDeDados.getUserLogged().nome)
        </script> 
    </body>
</html>
