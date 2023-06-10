<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../../resources/css/alumnos.css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
<header>
	<div class="logo">
	</div>
	<div class="parametros">
	<a href="/logout">
		<img id="image-1" alt="logout" width="50px" src="../../../resources/img/salida.png"/>
	</a>
	</div>
	
</header>
 
	<table id="table-1">
		<thead>
			<tr>
				<th>Nombre</th>
				<th>Ape. Paterno</th>
				<th>Ape. Materno</th>
				<th>Matricula</th>
				<th>Curso</th>
				<th>Opciones</th>
			</tr>
		</thead>
		<tbody>
			
			<c:forEach items="${page.content}" var="alumno">
				<tr>
					<td>${alumno.nombre}</td>
					<td>${alumno.paterno}</td>
					<td>${alumno.materno}</td>
					<td>${alumno.matricula}</td>
					<td>${alumno.licenciatura.nombre}</td>
					<td>
						<input class="btn" type="button" value="Editar" onclick="editar(${alumno.id})"/>
						<input class="btn btnEliminar" type="button" value="Eliminar" onclick="eliminar(${alumno.id})"/>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	 <c:if test="${page.totalPages > 1}">
        <div>
            <span>Página:</span>
            <c:forEach begin="0" end="${page.totalPages - 1}" var="i">
                <a href="${pageContext.request.contextPath}/alumnos/?page=${i}">${i + 1}</a>
            </c:forEach>
        </div>
    </c:if>
	
	<div id="modal" class="modal">
        <div class="modal-content">
          <span class="close">&times;</span>
          <h2>Alumno</h2>
	<form action="/alumnos/modificar" method="post" class="formulario">
		<input type="hidden" name="id" id="id">
		<input type="hidden" name="matricula" id="matricula">
		<input type="hidden" name="fecha_ingreso" id="fecha_ingreso">
		<input type="hidden" name="usuarioIngreso" id="usuarioIngreso">
		<input type="hidden" name="eliminado" id="eliminado">
		<label for="nombre">Nombre:</label>
			<input type="text" name="nombre" id="nombre">
		<label for="paterno">Ape. Paterno::</label>
			<input class="form_input" type="text" name="paterno" id="paterno">
		<label for="materno">Ape. Materno:</label>
			<input class="form_input" type="text" name="materno" id="materno">
		<label for="correo">Correo:</label>
			<input class="form_input" type="text" name="correo" id="correo">
		<label for="licenciatura">Curso:</label>
			<select class="form_input" name="licenciatura.id" id="licenciatura">
				<option value="-1">--Seleccionar--</option>
				<c:forEach var="licenciatura" items="${licenciaturas}">
					<option value="${licenciatura.id}">${licenciatura.nombre}</option>
				</c:forEach>
			</select>
			<input type="hidden" name="${_csrf.parameterName}" id="token" value="${_csrf.token}" />
		<input type="submit" class="btn btnAgregar" value="Registro" id="btnRegistro"/>
	</form>
	</div>
	</div>

	<script type="text/javascript">
	var id = document.getElementById('id');
	var fecha_ingreso = document.getElementById('fecha_ingreso');
	var usuarioIngreso = document.getElementById('usuarioIngreso');
	var nombre = document.getElementById('nombre');
	var paterno = document.getElementById('paterno');
	var materno = document.getElementById('materno');
	var correo = document.getElementById('correo');
	var licenciatura = document.getElementById('licenciatura');
	var matricula = document.getElementById('matricula');
	var form = document.querySelector('form');
	var datosCompletos = null;

	
		const editar = id => {
			fetch('/alumnos/editar/' + id)
			.then(respuesta => respuesta.json())
			.then(data => {
				this.id.value = data.id;
				nombre.value = data.nombre;
				paterno.value = data.paterno;
				materno.value = data.materno;
				correo.value = data.correo;
				licenciatura.value = data.licenciatura.id;
				fecha_ingreso.value = data.fecha_ingreso
				usuarioIngreso.value = data.usuarioIngreso
				matricula.value = data.matricula;
				datosCompletos = data;
				abrirModal();
			});
		}

		const eliminar = id => {
			fetch('/alumnos/eliminar/' + id)
			.then(respuesta => respuesta.json())
			.then(resp => {
				alert(resp.mensaje);
				location.reload();
			})
		}
		
	const limpiarCampos = () => {
		nombre.value = '';
		paterno.value = '';
		materno.value = '';
		correo.value = '';
		licenciatura.value = '-1';
	}

	const modal = document.getElementById("modal");
	const closeModalBtn = document.getElementsByClassName("close")[0];

	function abrirModal() {
	  modal.style.display = "block";
	}

	closeModalBtn.addEventListener("click", function() {
	  modal.style.display = "none";
	});

	window.addEventListener("click", function(event) {
	  if (event.target === modal) {
	    modal.style.display = "none";
	  }
	});

	</script>
</body>
</html>