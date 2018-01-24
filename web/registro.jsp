<%@include file="template/header.jsp" %>

<div class="row">
    <div class="col s4 offset-s4 z-depth-3">
        <h4 class="center-align">Registro</h4>
        <form action="control.do" method="post">

            <div class="input-field">
                <input id="rut" type="text" name="rut" >
                <label for="rut">Rut</label>
            </div>
            
            <div class="input-field">
                <input id="nombre" type="text" name="nombre" >
                <label for="nombre">Nombre</label>
            </div>
            
            <div class="input-field">
                <input id="mail" type="text" name="mail" >
                <label for="mail">Mail</label>
            </div>
            
            <div class="input-field">
                <input id="clave1" type="password" name="clave1" >
                <label for="clave1">Clave</label>
            </div>
            
            <div class="input-field">
                <input id="clave2" type="password" name="clave2" >
                <label for="clave2">Confirmar Clave</label>
            </div>

            
            


            <button class="btn right" name="boton" value="registro">Registrar</button>
            <br><br><br>
            <p class="center-align">
                <a href="index.jsp">Volver</a>
            </p>
        </form>
        <br><br>
    </div>
</div>

<%@include file="template/footer.jsp" %>
