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
                        <h1><i class="fa fa-plus-square"></i> Criar Estande</h1>
                        <p> LocalizaÊ - Sistema de Posicionamento Interno FAITEC</p>
                    </div>
                    <div>
                        <ul class="breadcrumb">
                            <li><i class="fa fa-home fa-lg"></i></li>
                            <li><a href="#">Criar Estande</a></li>
                        </ul>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">
                                <div class="row">
                                    <form>
                                        <div class="col-lg-6">
                                            <div class="row">
                                                <div class="col-lg-6">
                                                    <div class="form-group">
                                                        <label class="control-label">Número do Estande</label>
                                                        <input class="form-control" type="text">
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="form-group">
                                                        <label class="control-label" for="select">Área Temática</label>
                                                        <select class="form-control" id="select">
                                                            <option>Marketing</option>
                                                            <option>Gestão</option>
                                                            <option>Educação</option>
                                                            <option>Sustentabilidade</option>
                                                        </select>  
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Nome do Projeto</label>
                                                <input class="form-control" type="text">
                                            </div>

                                            <div class="form-group">
                                                <label class="control-label">Descrição</label>
                                                <textarea class="form-control" rows="2"></textarea>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label" for="select">Curso</label>
                                                <select class="form-control" id="select">
                                                    <option>Sistemas de Informação</option>
                                                    <option>Administração</option>
                                                    <option>Pedagogia</option>
                                                    <option>Engenharia de Produção</option>
                                                    <option>Ciências Contábeis</option>
                                                </select>  
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label" for="select">Período</label>
                                                <select class="form-control" id="select">
                                                    <option>1º ano</option>
                                                    <option>2º ano</option>
                                                    <option>3º ano</option>
                                                    <option>4º ano</option>
                                                    <option>1º período</option>
                                                    <option>2º período</option>
                                                    <option>3º período</option>
                                                    <option>4º período</option>
                                                    <option>5º período</option>
                                                    <option>6º período</option>
                                                    <option>7º período</option>
                                                    <option>8º período</option>
                                                    <option>9º período</option>
                                                    <option>10º período</option>
                                                </select>  
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label class="control-label">Integrantes da Equipe</label>
                                                <input class="form-control" type="text">
                                                <br>
                                                <input class="form-control" type="text">
                                                <br>
                                                <input class="form-control" type="text">
                                                <br>
                                                <input class="form-control" type="text">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Expositor Responsável</label>
                                                <input class="form-control" type="text">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Email Responsável</label>
                                                <input class="form-control" type="text">
                                            </div>
                                        </div>      
                                    </form>

                                    <div class="col-lg-12">
                                        <button class="btn btn-primary pull-right" id="demoSwal" type="submit">Confirmar</button>
                                    </div>

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
        <script type="text/javascript">
            $('#demoSwal').click(function () {
                swal("Confirmado!", "Foi adicionado um Estande!", "success")
            });
        </script>   
    </body>
</html>
