<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Navbar-->
<header class="main-header hidden-print"><a class="logo" href="<c:url value="/gerenciador"/>"><img src="<c:url value="/resources/img/logos/logo5.png"/>" alt="Localizaï¿½" class="img-responsive"></a>

    <nav class="navbar navbar-static-top">
        <!-- Sidebar toggle button--><a class="sidebar-toggle" href="#" data-toggle="offcanvas"></a>
        <!-- Navbar Right Menu-->
        <div class="navbar-custom-menu">
            <ul class="top-nav">
                <!--Notification Menu-->
                <!-- <li class="dropdown notification-menu"><a class="dropdown-toggle" href="#" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-bell-o fa-lg"></i></a>
                    <ul class="dropdown-menu">
                        <li class="not-head">You have 4 new notifications.</li>
                        <li><a class="media" href="javascript:;"><span class="media-left media-icon"><span class="fa-stack fa-lg"><i class="fa fa-circle fa-stack-2x text-primary"></i><i class="fa fa-envelope fa-stack-1x fa-inverse"></i></span></span>
                                <div class="media-body"><span class="block">Lisa sent you a mail</span><span class="text-muted block">2min ago</span></div></a></li>
                        <li><a class="media" href="javascript:;"><span class="media-left media-icon"><span class="fa-stack fa-lg"><i class="fa fa-circle fa-stack-2x text-danger"></i><i class="fa fa-hdd-o fa-stack-1x fa-inverse"></i></span></span>
                                <div class="media-body"><span class="block">Server Not Working</span><span class="text-muted block">2min ago</span></div></a></li>
                        <li><a class="media" href="javascript:;"><span class="media-left media-icon"><span class="fa-stack fa-lg"><i class="fa fa-circle fa-stack-2x text-success"></i><i class="fa fa-money fa-stack-1x fa-inverse"></i></span></span>
                                <div class="media-body"><span class="block">Transaction xyz complete</span><span class="text-muted block">2min ago</span></div></a></li>
                        <li class="not-footer"><a href="#">See all notifications.</a></li>
                    </ul>
                </li> -->
                <!-- User Menu-->
                <li class="dropdown"><a class="dropdown-toggle" href="#" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><i class="fa fa-user fa-lg"></i></a>
                    <ul class="dropdown-menu settings-menu">
                        <li><a href="page-user.html"><i class="fa fa-user fa-lg"></i> Alterar Senha</a></li>
                        <li><a href="#" onclick="loginController.logout(event)"><i class="fa fa-sign-out fa-lg"></i> Sair</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
</header>
<!-- Side-Nav-->
<aside class="main-sidebar hidden-print">
    <section class="sidebar">
        <div class="user-panel">
            <div class="pull-left image"><img src="<c:url value="/resources/img/icon.ico"/>" alt="Localizaï¿½" class="img-responsive img-circle"></div>
            <div class="pull-left info">
                <!-- <p>Roberto Júnior</p> -->
                <p class="designation">Administrador</p>
            </div>
        </div>
        <!-- Sidebar Menu-->
        <ul class="sidebar-menu">
            <li class="treeview <c:if test="${aba eq 'evento'}">active</c:if>"><a href="<c:url value="/evento/gerenciar"/>"><i class="fa fa-university"></i><span>Gerenciar Eventos</span></a></li>
            <li class="treeview <c:if test="${aba eq 'beacon'}">active</c:if>"><a href="<c:url value="/beacon/gerenciar"/>"><i class="fa fa-map-marker"></i><span>Gerenciar Beacons</span></a></li>
            <li class="treeview <c:if test="${aba eq 'estande'}">active</c:if>"><a href="<c:url value="/estande/gerenciar"/>"><i class="fa fa-graduation-cap"></i><span>Gerenciar Estandes</span></a></li>
            <li class="treeview <c:if test="${aba eq 'estatisticas'}">active</c:if>"><a href="<c:url value="/estatisticas"/>"><i class="fa fa-bar-chart-o"></i><span>Estatísticas</span></a></li>
            <li class="treeview <c:if test="${aba eq 'avaliacaoVisitantes'}">active</c:if>"><a href="<c:url value="/avaliacao/visitantes"/>"><i class="fa fa-commenting-o"></i><span>Avaliações de Visitantes</span></a></li>
            <li class="treeview <c:if test="${aba eq 'avaliacaoJurados'}">active</c:if>"><a href="<c:url value="/jurado/avaliacoes"/>"><i class="fa fa-commenting"></i><span>Avaliações de Jurados</span></a></li>
            <li class="treeview <c:if test="${aba eq 'addExpositor'}">active</c:if> <c:if test="${aba eq 'addJurado'}">active</c:if><c:if test="${aba eq 'addAdministrador'}">active</c:if> <c:if test="${aba eq 'gerenciarUsuarios'}">active</c:if>"><a href="#"><i class="fa fa-laptop"></i><span>Gerenciar Usuários</span><i class="fa fa-angle-right"></i></a>
                <ul class="treeview-menu">
                    <li <c:if test="${aba eq 'gerenciarUsuarios'}"> class="sub-menu-active"</c:if>><a href="<c:url value="/usuario/gerenciar"/>"><i class="fa fa-circle-o"></i> Usuários</a></li>
                    <li <c:if test="${aba eq 'addAdministrador'}"> class="sub-menu-active"</c:if>><a href="<c:url value="/usuario/adicionar/administrador"/>"><i class="fa fa-circle-o"></i> Adicionar Administrador</a></li>
                    <li <c:if test="${aba eq 'addExpositor'}"> class="sub-menu-active"</c:if>><a href="<c:url value="/usuario/adicionar/expositor"/>"><i class="fa fa-circle-o"></i> Adicionar Expositor</a></li>
                    <li <c:if test="${aba eq 'addJurado'}"> class="sub-menu-active"</c:if>><a href="<c:url value="/usuario/adicionar/jurado"/>"><i class="fa fa-circle-o"></i> Adicionar Jurado</a></li>
                </ul>
            </li>
            <li class="treeview <c:if test="${aba eq 'porVisitantes'}">active</c:if> <c:if test="${aba eq 'avaliacoesEstandes'}">active</c:if>"><a href="#"><i class="fa fa-laptop"></i><span>Gerar Relatórios</span><i class="fa fa-angle-right"></i></a>
                <ul class="treeview-menu">
                    <li <c:if test="${aba eq 'porVisitantes'}"> class="sub-menu-active"</c:if>><a href="<c:url value="/relatorio/visitantePorEstande"/>"><i class="fa fa-circle-o"></i> Visitantes por Estande</a></li>
                    <li <c:if test="${aba eq 'avaliacoesEstandes'}"> class="sub-menu-active"</c:if>><a href="<c:url value="/relatorio/avaliacoesEstandes"/>"><i class="fa fa-circle-o"></i> Avaliações dos Visitantes</a></li>
                </ul>
            </li>
        </ul>
    </section>
</aside>
