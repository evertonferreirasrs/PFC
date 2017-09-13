<%-- 
    Document   : addEstande
    Author     : LocalizaÊ - Sistema de Posicionamento Interno FAITEC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <jsp:include page="../Componentes/cabecalhoAdministrador.jsp"/>
    </head>

    <body class="sidebar-mini fixed">
        <div class="wrapper">

            <jsp:include page="../Componentes/menuAdministrador.jsp"/> 

            <div class="content-wrapper">
                <div class="page-title">
                    <div>
                        <h1><i class="fa fa-plus-square"></i> Criar Evento</h1>
                        <p> LocalizaÊ - Sistema de Posicionamento Interno Para Eventos</p>
                    </div>
                    <div>
                        <ul class="breadcrumb">
                            <li><i class="fa fa-home fa-lg"></i></li>
                            <li><a href="<c:url value="/evento/gerenciar" />">Eventos</a></li>
                            <li><a href="<c:url value="/evento/adicionar" />">Criar Evento</a></li>
                        </ul>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">
                                <div class="row">
                                    <form onsubmit="eventoController.adiciona(event)">
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label class="control-label">Nome do Evento</label>
                                                <input class="form-control" id="inputNome" type="text">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Endereço do Evento</label>
                                                <input class="form-control" id="inputEndereco" type="text">
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label class="control-label" for="select">Data/Hora de início do evento:</label>
                                                <input type="text" id="inputDataHoraEventoInicio" class="form-control">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label" for="select">Data/Hora de término do vento:</label>
                                                <input type="text" id="inputDataHoraEventoFim" class="form-control">  
                                            </div>
                                        </div>
                                        <div class="col-lg-12">
                                            <button class="btn btn-primary pull-right" type="submit">Confirmar</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
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
        <script type="text/javascript" src="<c:url value="/resources/js/plugins/bootstrap-notify.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/plugins/sweetalert.min.js"/>"></script>

        <!-- Importa os scripts necessários para as fucnionalidades relacionadas à entidade evento -->
        <jsp:include page="import-scripts-evento.jsp"/>
        
        <script>
            let eventoController = new EventoController()
        </script>

    </body>
</html>
