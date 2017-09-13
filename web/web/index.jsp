<%-- 
    Document   : index
    Author     : LocalizaÊ - Sistema de Posicionamento Interno FAITEC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>LocalizaÊ - Sistema de Posicionamento Interno Para Eventos</title>

        <link rel="shortcut icon" href="<c:url value="/resources/img/icon.ico"/>">
        
        <!-- Bootstrap Core CSS -->
        <link href="<c:url value="/resources/lib/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">
        
        <!-- Custom Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Catamaran:100,200,300,400,500,600,700,800,900" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Muli" rel="stylesheet">

        <!-- Plugin CSS -->
        <link rel="stylesheet" href="<c:url value="/resources/lib/font-awesome/css/font-awesome.min.css"/>">
        <link rel="stylesheet" href="<c:url value="/resources/lib/simple-line-icons/css/simple-line-icons.css"/>">
        <link rel="stylesheet" href="<c:url value="/resources/lib/device-mockups/device-mockups.min.css"/>">

        <!-- Theme CSS -->
        <link href="<c:url value="/resources/css/new-age.min.css"/>" rel="stylesheet">
        
        <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="<c:url value="/resources/js/html5shiv.min.js"/>"></script>
        <script src="<c:url value="/resources/js/respond.min.js"/>"></script>
        <![endif]-->
    </head>
    <body id="page-top">

        <nav id="mainNav" class="navbar navbar-default navbar-fixed-top">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span> Menu <i class="fa fa-bars"></i>
                    </button>
                    <a class="page-scroll" href="#page-top"><img src="<c:url value="/resources/img/logos/logo.png"/>" alt="LocalizaÊ" class="img-responsive"></a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a class="page-scroll" href="#page-top">Home</a>
                        </li>
                        <li>
                            <a class="page-scroll" href="#download">Download</a>
                        </li>
                        <li>
                            <a class="page-scroll" href="#features">Quem somos</a>
                        </li>
                        <li>
                            <a class="page-scroll" href="#contact">Contato</a>
                        </li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container-fluid -->
        </nav>

        <header>
            <div class="container">
                <div class="row">
                    <div class="col-sm-7">
                        <div class="header-content">
                            <div class="header-content-inner">
                                <h2 align="justify">O LocalizaÊ quer fazer o melhor para você. Por isso criamos um aplicativo para 
                                    que você tenha um melhor acesso e poder comentar sobre os projetos da feira de tecnologia 
                                    FAITEC 2017 !</h2>
                                <a href="#download" class="btn btn-outline btn-xl page-scroll">Baixe agora !</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-5">
                        <div class="device-container">
                            <div class="device-mockup galaxy_s5 portrait white">
                                <div class="device">
                                    <div class="screen">
                                        <!-- Demo image for screen mockup, you can put an image here, some HTML, an animation, video, or anything else! -->
                                        <img src="<c:url value="/resources/img/fundoCelular1.jpg"/>" class="img-responsive" alt="">
                                    </div>
                                    <div class="button">
                                        <!-- You can hook the "home button" to some JavaScript events or just remove it -->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>

        <section id="download" class="download text-center">
            <div class="container">
                <div class="row">
                    <div class="col-md-8 col-md-offset-2">
                        <h2 class="section-heading">Baixe agora nosso Aplicativo!</h2>
                        <p>Nosso aplicativo está disponível em qualquer dispositivo móvel! Faça o download agora para começar!</p>
                        <div class="badges">
                            <a class="badge-link" href="#"><img src="<c:url value="/resources/img/google-play-badge.svg"/>" alt=""></a>
                            <!--<a class="badge-link" href="#"><img src="img/app-store-badge.svg" alt=""></a>-->
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section id="features" class="features">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <div class="section-heading">

                            <center><img src="<c:url value="/resources/img/logos/logo4.png"/>" class="img-responsive" alt=""></center>
                            <hr>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4">
                        <div class="device-container">
                            <div class="device-mockup iphone6_plus portrait white">
                                <div class="device">
                                    <div class="screen">
                                        <!-- Demo image for screen mockup, you can put an image here, some HTML, an animation, video, or anything else! -->
                                        <img src="<c:url value="/resources/img/fundoCelular2.jpg"/>" class="img-responsive" alt=""> </div>
                                    <div class="button">
                                        <!-- You can hook the "home button" to some JavaScript events or just remove it -->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-8">
                        <div class="container-fluid">
                            <div class="row">
                                <h3 class="text-muted" align="justify">A LocalizaÊ visa atender aos visitantes, administradores e expositores da FAITEC 2017, disponibilizando 
                                    para eles uma aplicação móvel, pela qual seja possível que os interessados se posicionem dentro do espaço geográfico 
                                    da feira, facilitando a busca por projetos de seu interesse, podendo encontrá-los e também avaliá-los. Ao coletar os 
                                    dados fornecidos pela aplicação, os administradores terão acesso a outra aplicação web, que apresentará as estatísticas 
                                    para que possam auxiliar na tomada de decisões. Como uma terceira visão do projeto, os expositores terão acesso à uma 
                                    aplicação móvel que possibilitará que eles possam descrever seus projetos e criar promoções para os visitantes. Ainda 
                                    para facilitar e agilizar a avaliação e divulgação das notas dos trabalhos apresentados na feira, os jurados, poderão, 
                                    também, dar suas respectivas notas via aplicação, reduzindo assim o tempo de conferência de notas e reduzindo também o 
                                    consumo de papéis, canetas e pranchetas, o que, naturalmente, contribui para nossa sustentabilidade ambiental.</h3>

                                <h3 class="text-center"><b>Equipe LocalizaÊ</b></h3>
                                <div class="col-xs-6 col-sm-3">
                                    <div class="thumbnail panel-default">
                                        <br>                     
                                        <img src="<c:url value="/resources/img/equipe/marcos.jpg"/>" alt="Marcos Antônio" class="img-rounded">
                                        <div class="caption text-center">
                                            <h5 style="color:#3c8dbc"><b>Marcos Antônio</b></h5>
                                            <a href="#"><i class="fa fa-facebook" style="font-size:20px;color:#3c8dbc"></i></a>
                                            <a href="#"><i class="fa fa-twitter" style="font-size:20px;color:#3c8dbc"></i></a>
                                            <a href="#"><i class="fa fa-linkedin" style="font-size:20px;color:#3c8dbc"></i></a> 
                                            <a href="#"><i class="fa fa-skype" style="font-size:20px;color:#3c8dbc"></i></a>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-6 col-sm-3">
                                    <div class="thumbnail panel-default">
                                        <br>                     
                                        <img src="<c:url value="/resources/img/equipe/roberto.jpg"/>" alt="Roberto Júnior" class="img-rounded">
                                        <div class="caption text-center">
                                            <h5 style="color:#3c8dbc"><b>Roberto Júnior</b></h5>
                                            <a href="#"><i class="fa fa-facebook" style="font-size:20px;color:#3c8dbc"></i></a>
                                            <a href="#"><i class="fa fa-twitter" style="font-size:20px;color:#3c8dbc"></i></a>
                                            <a href="#"><i class="fa fa-linkedin" style="font-size:20px;color:#3c8dbc"></i></a> 
                                            <a href="#"><i class="fa fa-skype" style="font-size:20px;color:#3c8dbc"></i></a>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-6 col-sm-3">
                                    <div class="thumbnail panel-default">
                                        <br>                     
                                        <img src="<c:url value="/resources/img/equipe/lyan.jpg"/>" alt="Lyan Masterson" class="img-rounded">
                                        <div class="caption text-center">
                                            <h5 style="color:#3c8dbc"><b>Lyan Masterson</b></h5>
                                            <a href="#"><i class="fa fa-facebook" style="font-size:20px;color:#3c8dbc"></i></a>
                                            <a href="#"><i class="fa fa-twitter" style="font-size:20px;color:#3c8dbc"></i></a>
                                            <a href="#"><i class="fa fa-linkedin" style="font-size:20px;color:#3c8dbc"></i></a> 
                                            <a href="#"><i class="fa fa-skype" style="font-size:20px;color:#3c8dbc"></i></a>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-6 col-sm-3">
                                    <div class="thumbnail panel-default">
                                        <br>       
                                        
                                        <img src="<c:url value="/resources/img/equipe/everton.jpg"/>" alt="Everton Ferreira" class="img-rounded">
                                        <div class="caption text-center">
                                            <h5 style="color:#3c8dbc"><b>Everton Ferreira</b></h5>
                                            <a href="#"><i class="fa fa-facebook" style="font-size:20px;color:#3c8dbc"></i></a>
                                            <a href="#"><i class="fa fa-twitter" style="font-size:20px;color:#3c8dbc"></i></a>
                                            <a href="#"><i class="fa fa-linkedin" style="font-size:20px;color:#3c8dbc"></i></a> 
                                            <a href="#"><i class="fa fa-skype" style="font-size:20px;color:#3c8dbc"></i></a>
                                        </div>
                                    </div>
                                </div>

                            </div>                       
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section id="contact" class="contact bg-primary">
            <div class="container">
                <h2>Contato</h2>
                <h3>Para mais informações entre em contato conosco!</h3>
                <br>
                <div class="col-lg-6">
                    <div class="col-lg-12">
                        <input type="text" class="form-control" placeholder="Nome">
                    </div>
                    <br><br>
                    <div class="col-lg-12">
                        <input type="text" class="form-control" placeholder="Email">
                    </div>	
                    <br><br>
                    <div class="col-lg-12">
                        <textarea class="form-control" rows="3" placeholder="Mensagem"></textarea>
                    </div>
                    <br><br><br>
                    <p>
                    <div class="col-lg-12">
                        <!--<a href="" class="btn btn-outline btn-xl page-scroll">Enviar</a>-->
                        <button type="button" class="btn btn-default">Enviar</button>
                    </div>                           

                </div>	
                <div class="col-lg-6">
                    <div class="col-lg-12">
                        <div class="fb-page" data-href="https://www.facebook.com/tagpointbeacons/?fref=ts" data-small-header="false" data-adapt-container-width="true" data-hide-cover="false" data-show-facepile="true"><blockquote cite="https://www.facebook.com/PaginaEncontreFacil/?fref=ts" class="fb-xfbml-parse-ignore"><a href="https://www.facebook.com/PaginaEncontreFacil/?fref=ts">Encontre Fácil</a></blockquote></div>
                    </div>
                </div>
        </section>

        <footer>
            <div class="container">
                <!--<p>&copy; 2016 Start Bootstrap. All Rights Reserved.</p>-->
                <div class="col-lg-6">
                    <p><b>LocalizaÊ - Sistema de Posicionamento Interno para Eventos</b></p>
                                </div>
                                <div class="col-lg-6">
                                    <a href="<c:url value="/entrar"/>">Entrar como Administrador</a>
                                </div>

                                </div>
                                </footer>

                                <!-- jQuery -->
                                <script src="<c:url value="/resources/lib/jquery/jquery.min.js"/>"></script>

                                <!-- Bootstrap Core JavaScript -->
                                <script src="<c:url value="/resources/lib/bootstrap/js/bootstrap.min.js"/>"></script>

                                <!-- Plugin JavaScript -->
                                <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>

                                <!-- Theme JavaScript -->
                                <script src="<c:url value="/resources/js/new-age.min.js"/>"></script>

                                <div id="fb-root"></div>
                                <script>(function (d, s, id) {
                                        var js, fjs = d.getElementsByTagName(s)[0];
                                        if (d.getElementById(id))
                                            return;
                                        js = d.createElement(s);
                                        js.id = id;
                                        js.src = "//connect.facebook.net/pt_BR/sdk.js#xfbml=1&version=v2.8";
                                        fjs.parentNode.insertBefore(js, fjs);
                                    }(document, 'script', 'facebook-jssdk'));</script>


                                </body>
                                </html>
