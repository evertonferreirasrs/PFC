<%-- 
    Document   : estatisticaEstande
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
                        <h1><i class="fa fa-bar-chart-o"></i> Estatísticas Estandes</h1>
                        <p> LocalizaÊ - Sistema de Posicionamento Interno FAITEC</p>
                    </div>
                    <div>
                        <ul class="breadcrumb">
                            <li><i class="fa fa-home fa-lg"></i></li>
                            <li><a href="#">Estatísticas Estandes</a></li>
                        </ul>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="card">
                                        <h3 class="card-title">Line Chart</h3>
                                        <div class="embed-responsive embed-responsive-16by9">
                                            <canvas class="embed-responsive-item" id="lineChartDemo"></canvas>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="card">
                                        <h3 class="card-title">Bar Chart</h3>
                                        <div class="embed-responsive embed-responsive-16by9">
                                            <canvas class="embed-responsive-item" id="barChartDemo"></canvas>
                                        </div>
                                    </div>
                                </div>
                                <div class="clearfix"></div>
                                <div class="col-md-6">
                                    <div class="card">
                                        <h3 class="card-title">Radar Chart</h3>
                                        <div class="embed-responsive embed-responsive-16by9">
                                            <canvas class="embed-responsive-item" id="radarChartDemo"></canvas>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="card">
                                        <h3 class="card-title">Polar Chart</h3>
                                        <div class="embed-responsive embed-responsive-16by9">
                                            <canvas class="embed-responsive-item" id="polarChartDemo"></canvas>
                                        </div>
                                    </div>
                                </div>
                                <div class="clearfix"></div>
                                <div class="col-md-6">
                                    <div class="card">
                                        <h3 class="card-title">Pie Chart</h3>
                                        <div class="embed-responsive embed-responsive-16by9">
                                            <canvas class="embed-responsive-item" id="pieChartDemo"></canvas>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="card">
                                        <h3 class="card-title">Doughnut Chart</h3>
                                        <div class="embed-responsive embed-responsive-16by9">
                                            <canvas class="embed-responsive-item" id="doughnutChartDemo"></canvas>
                                        </div>
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
        <script type="text/javascript">
            var data = {
                labels: ["January", "February", "March", "April", "May"],
                datasets: [
                    {
                        label: "My First dataset",
                        fillColor: "rgba(220,220,220,0.2)",
                        strokeColor: "rgba(220,220,220,1)",
                        pointColor: "rgba(220,220,220,1)",
                        pointStrokeColor: "#fff",
                        pointHighlightFill: "#fff",
                        pointHighlightStroke: "rgba(220,220,220,1)",
                        data: [65, 59, 80, 81, 56]
                    },
                    {
                        label: "My Second dataset",
                        fillColor: "rgba(151,187,205,0.2)",
                        strokeColor: "rgba(151,187,205,1)",
                        pointColor: "rgba(151,187,205,1)",
                        pointStrokeColor: "#fff",
                        pointHighlightFill: "#fff",
                        pointHighlightStroke: "rgba(151,187,205,1)",
                        data: [28, 48, 40, 19, 86]
                    }
                ]
            };
            var pdata = [
                {
                    value: 300,
                    color: "#F7464A",
                    highlight: "#FF5A5E",
                    label: "Red"
                },
                {
                    value: 50,
                    color: "#46BFBD",
                    highlight: "#5AD3D1",
                    label: "Green"
                },
                {
                    value: 100,
                    color: "#FDB45C",
                    highlight: "#FFC870",
                    label: "Yellow"
                }
            ]

            var ctxl = $("#lineChartDemo").get(0).getContext("2d");
            var lineChart = new Chart(ctxl).Line(data);

            var ctxb = $("#barChartDemo").get(0).getContext("2d");
            var barChart = new Chart(ctxb).Bar(data);

            var ctxr = $("#radarChartDemo").get(0).getContext("2d");
            var barChart = new Chart(ctxr).Radar(data);

            var ctxpo = $("#polarChartDemo").get(0).getContext("2d");
            var barChart = new Chart(ctxpo).PolarArea(pdata);

            var ctxp = $("#pieChartDemo").get(0).getContext("2d");
            var barChart = new Chart(ctxp).Pie(pdata);

            var ctxd = $("#doughnutChartDemo").get(0).getContext("2d");
            var barChart = new Chart(ctxd).Doughnut(pdata);
        </script>

    </body>
</html>
