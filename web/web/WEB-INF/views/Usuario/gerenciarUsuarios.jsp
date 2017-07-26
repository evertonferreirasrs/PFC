<%-- 
    Document   : painel
    Author     : LocalizaÊ - Sistema de Posicionamento Interno FAITEC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <jsp:include page="../Componentes/cabecalhoAdministrador.jsp"/> 
    </head>

    <!-- MODAL VISUALIZAR -->
    <div id="myModalVisualizar" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content" style="background: #3c8dbc;">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title" style="color: #fff;">Everton Ferreira</h4>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">
                                <div class="row">
                                    <form>
                                        <div class="col-md-6">
                                            <h4>Nome: <small>Everton Ferreira</small></h4>
                                            <h4>Email: <small>everton@gmail.com</small></h4>
                                            
                                        </div>
                                        <div class="col-md-6">
                                            <h4>Tipo: <small>Visitante</small></h4>
                                            <br>
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
    </div>

    <!-- MODAL EDITAR -->
    <div id="myModalEditar" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content" style="background: #3c8dbc;">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title" style="color: #fff;">Everton Ferreira</h4>
                </div>

                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">
                                <div class="row">
                                    <form>
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
    </div>

    <body class="sidebar-mini fixed">
        <div class="wrapper">

            <jsp:include page="../Componentes/menuAdministrador.jsp"/> 

            <div class="content-wrapper">
                <div class="page-title">
                    <div>
                        <h1><i class="fa fa-users"></i> Usuários</h1>
                        <p> LocalizaÊ - Sistema de Posicionamento Interno FAITEC</p>
                    </div>
                    <div>
                        <ul class="breadcrumb">
                            <li><i class="fa fa-home fa-lg"></i></li>
                            <li>Gerenciar Usuários</li>
                            <li>Usuários</li>
                        </ul>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body" id="table-users">
                                
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
        <jsp:include page="import-scripts-user.jsp"/> 
        <script>
            usuarioController.readAll();
        </script>
    </body>
</html>
