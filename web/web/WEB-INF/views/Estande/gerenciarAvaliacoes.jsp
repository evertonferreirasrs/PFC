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
                        <p> LocalizaÊ - Sistema de Posicionamento Interno FAITEC</p>
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
                            <div class="card-body">
                                <table class="table table-hover table-bordered" id="sampleTable">
                                    <thead>
                                        <tr>
                                            <th>Projeto</th>
                                            <th style="width: 2px;">Avaliação</th>
                                            <th>Comentário</th>
                                            <th>Ação</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>LocalizaÊ</td>
                                            <td><center><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i></center></td>
                                            <td>Excelente projeto! Parabéns!</td>
                                            <td><center><a class="btn btn-danger" href="#"><i class="fa fa-lg fa-trash"></i></a></center></td>
                                    </tr>
                                    <tr>
                                            <td>Pare Fácil</td>
                                            <td><center><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i></center></td>
                                            <td>Excelente projeto! Parabéns!</td>
                                            <td><center><a class="btn btn-danger" href="#"><i class="fa fa-lg fa-trash"></i></a></center></td>
                                    </tr>
                                    <tr>
                                            <td>Encontre Fácil</td>
                                            <td><center><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i></center></td>
                                            <td>Excelente projeto! Parabéns!</td>
                                            <td><center><a class="btn btn-danger" id="demoSwal" href="#"><i class="fa fa-lg fa-trash"></i></a></center></td>
                                    </tr>
                                    <tr>
                                            <td>TecEdu Esportes</td>
                                            <td><center><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i></center></td>
                                            <td>Excelente projeto! Parabéns!</td>
                                            <td><center><a class="btn btn-danger" href="#"><i class="fa fa-lg fa-trash"></i></a></center></td>
                                    </tr>
                                    <tr>
                                            <td>Learn Automaton</td>
                                            <td><center><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i></center></td>
                                            <td>Excelente projeto! Parabéns!</td>
                                            <td><center><a class="btn btn-danger" href="#"><i class="fa fa-lg fa-trash"></i></a></center></td>
                                    </tr>
                                    </tbody>
                                </table>
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
        <script type="text/javascript">
            $('#demoSwal').click(function () {
                swal({
                    title: "Você tem certeza?",
                    text: "Você não será capaz de recuperar esses dados!",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonText: "Sim, deletar!",
                    cancelButtonText: "Não, cancelar!",
                    closeOnConfirm: false,
                    closeOnCancel: false
                }, function (isConfirm) {
                    if (isConfirm) {
                        swal("Deletado!", "Esses dados foram deletados.", "success");
                    } else {
                        swal("Cancelado!", "Seus dados estão seguros!", "error");
                    }
                });
            });
        </script>
    </body>
</html>
