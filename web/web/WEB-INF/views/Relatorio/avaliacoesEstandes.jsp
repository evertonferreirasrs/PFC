<%-- 
    Document   : avaliacoesEstandes
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
                        <h1><i class="fa fa-file-text"></i> Relatório das Avaliações dos Visitantes</h1>
                        <p> LocalizaÊ - Sistema de Posicionamento Interno FAITEC</p>
                    </div>
                    <div>
                        <ul class="breadcrumb">
                            <li><i class="fa fa-home fa-lg"></i></li>
                            <li>Gerar Relatórios</li>
                            <li><a href="#">Relatório das Avaliações dos Visitantes</a></li>
                        </ul>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <section class="invoice">
                                <div class="row">
                                    <div class="col-xs-12">
                                        <h2 class="page-header"><img src="<c:url value="/resources/img/logos/logoRelatorio.png"/>"><small class="pull-right">Date: 01/01/2016</small></h2>

                                    </div>
                                </div>
                                <div class="row invoice-info">
                                    <div class="col-xs-4">From
                                        <address><strong>Vali Ltd.</strong><br>518 Akshar Avenue<br>Gandhi Marg<br>New Delhi<br>Email: hello@vali.com</address>
                                    </div>
                                    <div class="col-xs-4">To
                                        <address><strong>John Doe</strong><br>            795 Folsom Ave, Suite 600<br>            San Francisco, CA 94107<br>            Phone: (555) 539-1037<br>            Email: john.doe@example.com</address>
                                    </div>
                                    <div class="col-xs-4"><b>Invoice #007612</b><br><br><b>Order ID:</b> 4F3S8J<br><b>Payment Due:</b> 2/22/2014<br><b>Account:</b> 968-34567</div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-12 table-responsive">
                                        <table class="table table-striped">
                                            <thead>
                                                <tr>
                                                    <th>Qty</th>
                                                    <th>Product</th>
                                                    <th>Serial #</th>
                                                    <th>Description</th>
                                                    <th>Subtotal</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>1</td>
                                                    <td>Call of Duty</td>
                                                    <td>455-981-221</td>
                                                    <td>El snort testosterone trophy driving gloves handsome</td>
                                                    <td>$64.50</td>
                                                </tr>
                                                <tr>
                                                    <td>1</td>
                                                    <td>Need for Speed IV</td>
                                                    <td>247-925-726</td>
                                                    <td>Wes Anderson umami biodiesel</td>
                                                    <td>$50.00</td>
                                                </tr>
                                                <tr>
                                                    <td>1</td>
                                                    <td>Monsters DVD</td>
                                                    <td>735-845-642</td>
                                                    <td>Terry Richardson helvetica tousled street art master</td>
                                                    <td>$10.70</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <div class="row hidden-print mt-20">
                                    <div class="col-xs-12 text-right"><a class="btn btn-primary" href="javascript:window.print();" target="_blank"><i class="fa fa-print"></i> Print</a></div>
                                </div>
                            </section>
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
        <!--<script type="text/javascript">$('body').removeClass("sidebar-mini").addClass("sidebar-collapse");</script>-->
        
    </body>
</html>
