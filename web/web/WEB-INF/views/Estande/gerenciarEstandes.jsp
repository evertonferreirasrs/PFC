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

    <!-- MODAL -->
    <div id="myModal" class="modal fade" role="dialog">
        <div class="modal-dialog modal-lg">
            <div class="modal-content" style="background: #3c8dbc;">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title" style="color: #fff;">LocalizaÊ - Sistema de Posicionamento Interno FAITEC</h4>
                </div>

                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="row">
                                <div class="col-lg-7">
                                    <h4>Número do Estande: <small>10</small></h4>
                                    <h4>Área Temática: <small>Gestão</small></h4>
                                    <h4>Nome do Projeto: <small>LocalizaÊ - Sistema de Posicionamento Interno FAITEC</small></h4>
                                    <h4>Expositor responsável: <small>Roberto de Souza Porto Júnior</small></h4>
                                    <h4>Email do responsável: <small>rjunior13@gmail.com</small></h4>
                                </div>
                                <div class="col-lg-5">
                                    <h4>Curso: <small>Sistemas de Informação</small></h4> 
                                    <h4>Período: <small>4º ano</small></h4>
                                    <h4>Integrantes: <small>Roberto de Souza Porto Júnior</small></h4>
                                    <h4 class="text-center"><small>Marcos Antônio dos Santos</small></h4>
                                    <h4 class="text-center"><small>Lyan Masterson Pereira Miguel</small></h4>
                                    <h4 class="text-center"><small>Everton Aparecido Ferreira</small></h4>

                                </div>
                            </div>
                            <hr>
                            <h4 align="justify">Descrição: <small>Sistema de localização interno para feiras de diversos setores, incluindo 
                                    feiras acadêmicas, que é o primeiro foco do projeto. Como na cidade não há um software 
                                    capaz de realizar as funcionalidades de localização interna, esse projeto pode ajudar a 
                                    alavancar e expandir as feiras realizadas em Santa Rita do Sapucaí.</small>
                            </h4>
                            <button type="button" class="btn btn-default pull-right" style="background: #000;" data-dismiss="modal">Fechar</button>
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
                        <h1><i class="fa fa-university"></i> Estandes</h1>
                        <p> LocalizaÊ - Sistema de Posicionamento Interno FAITEC</p>
                    </div>
                    <div>
                        <ul class="breadcrumb">
                            <li><i class="fa fa-home fa-lg"></i></li>
                            <li><a href="#">Estandes</a></li>
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
                                            <th>Número</th>
                                            <th>Projeto</th>
                                            <th>Área Temática</th>
                                            <th>Ação</th>

                                            <!--
                                            <th>Descrição</th>
                                            <th>Curso</th>
                                            <th>Período do Curso</th>
                                            <th>Expositor responsável</th>
                                            <th>Imagem</th>
                                            -->
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>10</td>
                                            <td>LocalizaÊ</td>
                                            <td>Gestão</td>
                                            <td><center><a class="btn btn-info" data-toggle="modal" data-target="#myModal"><i class="fa fa-lg fa-plus"></i></a>&nbsp;
                                        <a class="btn btn-warning" href="#"><i class="fa fa-edit"></i></a>&nbsp;
                                        <a class="btn btn-danger" id="demoSwal" href="#"><i class="fa fa-lg fa-trash"></i></a></center></td>
                                    </tr>
                                    <tr>
                                        <td>54</td>
                                        <td>Encontre Fácil</td>
                                        <td>Marketing</td>
                                        <td><center><a class="btn btn-info" href="#"><i class="fa fa-lg fa-plus"></i></a>&nbsp;
                                        <a class="btn btn-warning" href="#"><i class="fa fa-edit"></i></a>&nbsp;
                                        <a class="btn btn-danger" id="demoSwal" href="#"><i class="fa fa-lg fa-trash"></i></a></center></td>
                                    </tr>
                                    <tr>
                                        <td>22</td>
                                        <td>Pare Fácil</td>
                                        <td>Gestão</td>
                                        <td><center><a class="btn btn-info" href="#"><i class="fa fa-lg fa-plus"></i></a>&nbsp;
                                        <a class="btn btn-warning" href="#"><i class="fa fa-edit"></i></a>&nbsp;
                                        <a class="btn btn-danger" id="demoSwal" href="#"><i class="fa fa-lg fa-trash"></i></a></center></td>
                                    </tr>
                                    <tr>
                                        <td>72</td>
                                        <td>Learn Automaton</td>
                                        <td>Educação</td>
                                        <td><center><a class="btn btn-info" href="#"><i class="fa fa-lg fa-plus"></i></a>&nbsp;
                                        <a class="btn btn-warning" href="#"><i class="fa fa-edit"></i></a>&nbsp;
                                        <a class="btn btn-danger" id="demoSwal" href="#"><i class="fa fa-lg fa-trash"></i></a></center></td>
                                    </tr>
                                    <tr>
                                        <td>35</td>
                                        <td>TecEdu Esportes</td>
                                        <td>Educação</td>
                                        <td><center><a class="btn btn-info" href="#"><i class="fa fa-lg fa-plus"></i></a>&nbsp;
                                        <a class="btn btn-warning" href="#"><i class="fa fa-edit"></i></a>&nbsp;
                                        <a class="btn btn-danger" id="demoSwal" href="#"><i class="fa fa-lg fa-trash"></i></a></center></td>
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
            $('#demoNotify').click(function () {
                $.notify({
                    title: "Update Complete : ",
                    message: "Something cool is just updated!",
                    icon: 'fa fa-check'
                }, {
                    type: "info"
                });
            });
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
