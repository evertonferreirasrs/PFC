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
                        <h1><i class="fa fa-plus-square"></i> Alterar Estande</h1>
                        <p> LocalizaÊ - Sistema de Posicionamento Interno Para Eventos</p>
                    </div>
                    <div>
                        <ul class="breadcrumb">
                            <li><i class="fa fa-home fa-lg"></i></li>
                            <li><a href="#">Alterar Estande</a></li>
                        </ul>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">
                                <div class="row">
                                    <form onsubmit="estandeController.altera(event)">
                                        <input type="hidden" id="inputId" value="${id}">
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label class="control-label">Título do Projeto</label>
                                                <input class="form-control" id="inputTitulo" type="text">
                                            </div>
                                            <div class="row">
                                                <div class="col-lg-6">
                                                    <div class="form-group">
                                                        <label class="control-label">Número do Estande</label>
                                                        <input class="form-control" id="inputNumero" type="text">
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="form-group">
                                                        <label class="control-label" for="select">Área Temática</label>
                                                        <select class="form-control" id="inputAreaTematica">
                                                            <option>Marketing</option>
                                                            <option>Gestão</option>
                                                            <option>Educação</option>
                                                            <option>Sustentabilidade</option>
                                                        </select>  
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Evento:</label>
                                                <select class="form-control" id="inputEvento">
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label class="control-label" for="select">Curso</label>
                                                <select class="form-control" id="inputCurso">
                                                    <option>Sistemas de Informação</option>
                                                    <option>Administração</option>
                                                    <option>Pedagogia</option>
                                                    <option>Engenharia de Produção</option>
                                                    <option>Ciências Contábeis</option>
                                                </select>  
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label" for="select">Período</label>
                                                <select class="form-control" id="inputPeriodo">
                                                    <option value="1">1º período</option>
                                                    <option value="2">2º período</option>
                                                    <option value="3">3º período</option>
                                                    <option value="4">4º período</option>
                                                    <option value="5">5º período</option>
                                                    <option value="6">6º período</option>
                                                    <option value="7">7º período</option>
                                                    <option value="8">8º período</option>
                                                    <option value="9">9º período</option>
                                                    <option value="10">10º período</option>
                                                </select>  
                                            </div>
                                            <div class="row">
                                                <div class="col-xs-6">
                                                    <div class="form-group">
                                                        <label for="inputPosicaoX">Posição X:</label>
                                                        <input type="text" id="inputPosicaoX" class="form-control">
                                                    </div>
                                                </div>
                                                <div class="col-xs-6">
                                                    <div class="form-group">
                                                        <label for="inputPosicaoY">Posição Y:</label>
                                                        <input type="text" id="inputPosicaoY" class="form-control">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-xs-12">
                                            <div class="form-group">
                                                <label class="control-label">Descrição</label>
                                                <textarea class="form-control" rows="5" id="inputDescricao"></textarea>
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
        
        
        <jsp:include page="import-scripts-estande.jsp"/>
        
        <script>
            let estandeController = new EstandeController()
            estandeController.loadEstande()
        </script>
    </body>
</html>
