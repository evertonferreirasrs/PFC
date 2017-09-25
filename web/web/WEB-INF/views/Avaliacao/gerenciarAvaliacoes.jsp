<%-- 
    Document   : gerenciarAvaliacoes
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
                        <h1><i class="fa fa-commenting-o"></i> Gerenciar Avaliações</h1>
                        <p> LocalizaÊ - Sistema de Posicionamento Interno para Eventos</p>
                    </div>
                    <div>
                        <ul class="breadcrumb">
                            <li><i class="fa fa-home fa-lg"></i></li>
                            <li><a href="#">Gerenciar Avaliações</a></li>
                        </ul>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body" id="table-avaliacoes">
                                
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
        
        <jsp:include page="import-script-avaliacao-visitante.jsp"/> 
        
        
        <script>
            let avaliacaoVisitanteController = new AvaliacaoVisitanteController()
            avaliacaoVisitanteController.loadAvaliacoes()
        </script>
    </body>
</html>
