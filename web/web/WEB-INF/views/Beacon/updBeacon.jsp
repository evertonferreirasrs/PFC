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
                        <h1><i class="fa fa-plus-square"></i> Alterar Beacon</h1>
                        <p> LocalizaÊ - Sistema de Posicionamento Interno Para Eventos</p>
                    </div>
                    <div>
                        <ul class="breadcrumb">
                            <li><i class="fa fa-home fa-lg"></i></li>
                            <li><a href="<c:url value="/beacon/gerenciar" />">Beacons</a></li>
                            <li><a href="<c:url value="/beacon/alterar/" />">Alterar Beacon</a></li>
                        </ul>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">
                                <div class="row">
                                    <form onsubmit="beaconController.altera(event)">
                                        <input type="hidden" id="inputId" value="${id}" />
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label class="control-label">Mac do Beacon</label>
                                                <input class="form-control" id="inputMac" type="text">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">X Coordinate</label>
                                                <input class="form-control" id="inputXCoordinate" type="text">
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label class="control-label">Y Coordinate</label>
                                                <input class="form-control" id="inputYCoordinate" type="text">
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

        
        <jsp:include page="import-scripts-beacon.jsp"/> 
        
        <script>
            let beaconController = new BeaconController()
            beaconController.loadBeacon()
        </script>
    </body>
</html>
