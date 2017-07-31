<%-- 
    Document   : addExpositor
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
                        <h1><i class="fa fa-user"></i> Adicionar Expositor</h1>
                        <p> LocalizaÊ - Sistema de Posicionamento Interno FAITEC</p>
                    </div>
                    <div>
                        <ul class="breadcrumb">
                            <li><i class="fa fa-home fa-lg"></i></li>
                            <li>Gerenciar Usuários</li>
                            <li>Adicionar Expositor</li>
                        </ul>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">
                                <form onsubmit="usuarioController.altera(event)">
                                    <div class="row">
                                        <input type="hidden" id="inputTipoUsuario" value="3">
                                        <input type="hidden" id="inputId" value="${id}">
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
                                                <label class="control-label" for="inputEstande">Estande</label>
                                                <select class="form-control" id="inputEstande">
                                                    
                                                </select>
                                            </div>
                                            <br><br>
                                        </div>
                                        <div class="col-md-12">
                                            <input type="checkbox" id="inputResponsavel"> Responsável pelo estande<br>
                                        </div>
                                        <div class="col-md-12">
                                            <button class="btn btn-primary pull-right" id="demoSwal"  type="submit">Confirmar</button>
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
                swal("Confirmado!", "Foi adicionado um Expositor!", "success")
            });
        </script> 
        <script src="<c:url value="/resources/js/app/model/Estande.js"/>"></script>
        <script src="<c:url value="/resources/js/app/model/IntegranteEquipe.js"/>"></script>
        <script src="<c:url value="/resources/js/app/service/EstandeService.js"/>"></script>
        <jsp:include page="import-scripts-user.jsp"/> 
        
        <script>
            
            usuarioController.readUser();
        </script>
    </body>
</html>
