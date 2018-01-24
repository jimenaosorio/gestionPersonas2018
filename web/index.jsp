<%@include file="template/header.jsp" %>

<div class="row">
    <div class="col s4 offset-s4 z-depth-3">
        <h4 class="center-align">Ingreso</h4>
        <form action="control.do" method="post">
            <label>Rut</label>
            <input type="text" name="rut"/>
            <label>Clave</label>
            <input type="password" name="clave"/>
            <button class="btn right" name="boton" value="login">Entrar</button>
            <br><br><br>
            <p class="center-align">
                <a href="registro.jsp">Si no tienes cuenta registrate aqui</a>
            </p>
        </form>
        <br><br>
        <p class="red-text">${requestScope.msg}</p>
    </div>
</div>

<%@include file="template/footer.jsp" %>
