<%@page import="cl.beans.PersonaBeanLocal"%>
<%@page import="javax.naming.InitialContext"%>
<%@include file="template/header.jsp" %>


<c:if test="${not empty sessionScope.admin}">
    <%@include file="template/menu.jsp" %>

    <div class="row">
        <div class="col s6 offset-s3 z-depth-3">
            <h4 class="center-align">Gestion Personas</h4>
            <form action="control.do" method="post">
                <table class="bordered">
                    <thead>
                        <tr>
                            <th>Rut</th>
                            <th>Nombre</th>
                            <th>Mail</th>
                            <th>Perfil</th>
                            <th>Activo</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%! private PersonaBeanLocal service;%>
                        <%
                            InitialContext ctx=new InitialContext();
                            service = (PersonaBeanLocal) ctx.lookup("java:global/EjercicioMartes/PersonaBean!cl.beans.PersonaBeanLocal");
                            //Payara lo toma así: java:global/Proyecto/Bean!ruta.Interface  - otro servidor puede ser distinto
                            
                        %>
                        <c:set var="personas" scope="page" value="<%=service.getPersonaList()%>" />
                        
                        <c:forEach items="${pageScope.personas}" var="p">
                            <tr>
                                <td>${p.rut}</td>
                                <td>${p.nombre}</td>
                                <td>${p.mail}</td>
                                <td>${p.perfil}</td>
                                <td>${p.activo?"Si":"No"}</td>
                                <td>
                                    <button name="boton" value="${p.rut}"
                                            class="btn-floating blue">
                                        <i class="material-icons">
                                            edit
                                        </i>
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

</c:if>

<c:if test="${empty sessionScope.admin}">
    No tienes permisos para estar aqui.
    <br> <a href="index.jsp">Iniciar Session</a>
</c:if>    


<%@include file="template/footer.jsp" %>