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
                        <p> LocalizaÊ - Sistema de Posicionamento Interno Para Eventos</p>
                    </div>
                    <div>
                        <ul class="breadcrumb">
                            <li><i class="fa fa-home fa-lg"></i></li>
                            <li><a href="#">Estatísticas</a></li>
                        </ul>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="row">
                                <div class="col-md-6 col-md-offset-3">
                                    <div class="card">
                                        <!-- <h3 class="card-title">Número de visitantes</h3> -->
                                        <div class="text-center">
                                            <img class="img-stat" src="<c:url value="/resources/img/multi-user.svg" />" alt="Multi User">
                                            <h3 id="number-visitors"></h3>
                                        </div>
                                    </div>
                                </div>
                                <!-- <div class="col-md-6">
                                    <div class="card">
                                        <h3 class="card-title">Bar Chart</h3>
                                        <div class="embed-responsive embed-responsive-16by9">
                                            <canvas class="embed-responsive-item" id="barChartDemo"></canvas>
                                        </div>
                                    </div>
                                </div> -->
                                <!-- <div class="clearfix"></div> -->
                                <div class="col-md-6">
                                    <div class="card">
                                        <h3 class="card-title">Quantidade de Visitantes por Hora</h3>
                                        <div class="embed-responsive embed-responsive-16by9">
                                            <div id="visitantesHora"></div>
                                            <div class="row">
                                                <div class="col-xs-12 text-center">
                                                    <button class="btn btn-primary" id="btn-day1">Dia 1</button>
                                                    <button class="btn btn-primary" id="btn-day2">Dia 2</button>
                                                    <button class="btn btn-primary" id="btn-day3">Dia 3</button>
                                                </div>
                                            </div>
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
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
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

            // var ctxb = $("#barChartDemo").get(0).getContext("2d");
            // var barChart = new Chart(ctxb).Bar(data);

            // var ctxr = $("#radarChartDemo").get(0).getContext("2d");
            // var barChart = new Chart(ctxr).Radar(data);

            var ctxpo = $("#polarChartDemo").get(0).getContext("2d");
            var barChart = new Chart(ctxpo).PolarArea(pdata);

            var ctxp = $("#pieChartDemo").get(0).getContext("2d");
            var barChart = new Chart(ctxp).Pie(pdata);

            var ctxd = $("#doughnutChartDemo").get(0).getContext("2d");
            var barChart = new Chart(ctxd).Doughnut(pdata);
        </script>
        <script src="<c:url value="/resources/js/app/controller/StatisticController.js"/>"></script>
        <script src="<c:url value="/resources/js/app/config/Configuration.js"/>"></script>
        <script src="<c:url value="/resources/js/app/service/HttpService.js"/>"></script>
        <script src="<c:url value="/resources/js/app/service/StatisticService.js"/>"></script>
        
        <script>
            let controller = new StatisticController()
            controller.loadNumberVisitors()
            drawChartNumberVisitors(18)

            $("#btn-day1").click((event) => {
                event.preventDefault()
                
                drawChartNumberVisitors(18)
            })

            $("#btn-day2").click((event) => {
                event.preventDefault()

                drawChartNumberVisitors(19)
            })

            $("#btn-day3").click((event) => {
                event.preventDefault()

                drawChartNumberVisitors(20)
            })
            
            function drawChartNumberVisitors(day){
                google.charts.load('current', {packages: ['corechart', 'bar']});
                google.charts.setOnLoadCallback(drawBasic);

                async function drawBasic(){
                    let dataChart = await controller.loadDataBarChart(day)

                    var data = google.visualization.arrayToDataTable(dataChart)

                    var options = {
                        title: 'Quantidade de visitantes por hora/dia',
                        chartArea: {width: '80%'},
                        hAxis: {
                        title: 'Número de Visitantes',
                        minValue: 0
                        },
                        vAxis: {
                        title: 'Hora'
                        }
                    }

                    var chart = new google.visualization.BarChart(document.getElementById('visitantesHora'))

                    chart.draw(data, options)

                    // console.log(teste)
                }
            }
        </script>
    </body>
</html>
