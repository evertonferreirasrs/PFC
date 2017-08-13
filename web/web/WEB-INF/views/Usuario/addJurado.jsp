<%-- 
    Document   : addJurado
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
                        <h1><i class="fa fa-user"></i> Adicionar Jurado</h1>
                        <p> LocalizaÊ - Sistema de Posicionamento Interno FAITEC</p>
                    </div>
                    <div>
                        <ul class="breadcrumb">
                            <li><i class="fa fa-home fa-lg"></i></li>
                            <li>Gerenciar Usuários</li>
                            <li>Adicionar Jurado</li>
                        </ul>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">
                                <form onsubmit="usuarioController.addJurado(event)">
                                    <input type="hidden" id="inputTipoUsuario" value="4">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label" for="inputNome">Nome</label>
                                                <input class="form-control" id="inputNome" type="text">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label" for="inputEmail">Email</label>
                                                <input class="form-control" id="inputEmail" type="text">
                                            </div>
                                        </div>
                                        <div class="col-md-6">

                                            <div class="form-group">
                                                <label class="control-label" for="inputPassword">Senha</label>
                                                <input class="form-control" id="inputPassword" type="password">
                                            </div>

                                            <br><br>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <h4>Critérios para avaliação</h4>
                                        </div>
                                    </div>

                                    <div id="criterio">
                                        <!--<div class="row criterio">
                                            <div class="col-md-6 form-group">
                                                <label class="control-label" for="inputCriterio">Critério:<small> (será avaliado)</small></label>
                                                <select class="form-control inputCriterio">
                                                    <option disabled selected>Selecione...</option>
                                                </select>
                                            </div>
                                            <div class="col-md-6 form-group">
                                                <label class="control-label" for="inputEstandeCriterio">Estande:<small> (será avaliado)</small></label>
                                                <select class="form-control inputEstandeCriterio">
                                                    <option disabled selected>Selecione...</option>
                                                </select>
                                            </div>
                                        </div>-->
                                    </div>

                                    <div class="row">
                                        <div class="col-md-2">
                                            <button class="btn" onclick="usuarioController.addCriterio(event)">
                                                <span class="fa fa-plus-circle fa-lg"></span> Adicionar critério
                                            </button>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <button class="btn btn-primary pull-right" id="demoSwal" type="submit">Confirmar</button>
                                        </div> 
                                    </div>
                                </form>
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
        <script type="text/javascript">
                                                $('#demoSwal').click(function () {
//                                                    swal("Confirmado!", "Foi adicionado um Jurado!", "success")
                                                });
        </script> 
        
        <jsp:include page="import-scripts-user.jsp"/> 
        <script src="<c:url value="/resources/js/app/model/CriterioAvaliacao.js"/>"></script>
        <script src="<c:url value="/resources/js/app/model/Estande.js"/>"></script>
        <script src="<c:url value="/resources/js/app/service/EstandeService.js"/>"></script>
        <script src="<c:url value="/resources/js/app/service/CriterioAvaliacaoService.js"/>"></script>
        <script src="<c:url value="/resources/js/app/model/CriterioJurado.js"/>"></script>
        


        <script>
            usuarioController.addCriterio()
        </script>

    </body>
</html>
