<%-- 
    Document   : gerenciarEstandes
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
                        <h1><i class="fa fa-university"></i> Eventos</h1>
                        <p> LocalizaÊ - Sistema de Posicionamento Interno Para Eventos</p>
                    </div>
                    <div>
                        <ul class="breadcrumb">
                            <li><i class="fa fa-home fa-lg"></i></li>
                            <li><a href="<c:url value="/evento/gerenciar" />">Eventos</a></li>
                        </ul>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 text-right">
                        <a href="<c:url value="/evento/adicionar" />" class="btn btn-primary">Adicionar Evento</a><br><br>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body" id="table-eventos">
                                
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
        <script type="text/javascript" src="<c:url value="/resources/js/plugins/jquery.dataTables.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/plugins/dataTables.bootstrap.min.js"/>"></script>
        <script type="text/javascript">$('#sampleTable').DataTable();</script>

        <script type="text/javascript" src="<c:url value="/resources/js/plugins/bootstrap-notify.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/plugins/sweetalert.min.js"/>"></script>
        
        
        <jsp:include page="import-scripts-evento.jsp"/>
        
        <script>
            let eventoController = new EventoController()
            eventoController.loadEventos()
        </script>

    </body>
</html>
