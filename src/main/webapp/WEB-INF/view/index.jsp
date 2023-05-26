<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../../resources/css/formulario.css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
  <div class="container">
    <div class="left">
      <h1>¡INSCRIPCIÓN GRATIS!</h1>
      <p>Con nosotros tu puedes estudiar para que te conviertas en un desarrollador de aplicaciones web y trabajes desde cualquier parte del mundo no dejes pasar esta oportunidad inscribete ahora.</p>
    </div>
    <div class="right">
      <!--<div class="image"></div>-->
      <form class="formulario">
      <h2>REGISTRATE</h2>
            Nombre: <input class="form_input" type="text" name="nombre" id="nombre">
            Ape.Paterno: <input class="form_input" type="text" name="paterno" id="paterno">
            Ape.Materno: <input class="form_input" type="text" name="materno" id="materno">
            Correo: <input class="form_input" type="text" name="correo" id="correo">
            Curso: <select class="form_input" name="licenciatura" id="licenciatura">
                        <option value="-1">--Seleccionar--</option>
                        <c:forEach var="curso" items="${licenciaturas}">
                            <option value="${curso.id}">${curso.nombre}</option>
                        </c:forEach>
                    </select>
            <input type="submit" value="Registro" id="btnRegistro" class="btnRegistro"/>
        </form>
    </div>
  </div>
  <script type="text/javascript" src="../../resources/js/formulario.js"></script>
</body>
</html>