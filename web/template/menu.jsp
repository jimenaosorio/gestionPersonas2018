
<nav>
    <div class="nav-wrapper">
        <a href="#" class="brand-logo">
            <c:if test="${not empty sessionScope.admin}">
                Bienvenido ${sessionScope.admin.nombre}
            </c:if>
            <c:if test="${not empty sessionScope.person}">
                Bienvenido ${sessionScope.person.nombre}
            </c:if>    
                
        </a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">

            <c:if test="${not empty sessionScope.admin}">
                <li><a href="misDatos.jsp">Mis Datos</a></li>
                <li><a href="personas.jsp">Gestion Personas</a></li>
                <li><a href="salir.jsp">Cerrar sesion</a></li>
                </c:if>

            <c:if test="${not empty sessionScope.person}">
                <li><a href="misDatos.jsp">Mis Datos</a></li>
                <li><a href="salir.jsp">Cerrar sesion</a></li>
            </c:if>



        </ul>
    </div>
</nav>
