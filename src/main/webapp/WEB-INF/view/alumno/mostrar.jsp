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
			
			<c:forEach items="${alumnos}" var="alumno">
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
	
	<div id="modal" class="modal">
        <div class="modal-content">
          <span class="close">&times;</span>
          <h2>Alumno</h2>
	<form class="formulario">
		<label for="nombre">Nombre:</label>
			<input type="text" name="nombre" id="nombre">
		<label for="paterno">Ape. Paterno::</label>
			<input class="form_input" type="text" name="paterno" id="paterno">
		<label for="materno">Ape. Materno:</label>
			<input class="form_input" type="text" name="materno" id="materno">
		<label for="correo">Correo:</label>
			<input class="form_input" type="text" name="correo" id="correo">
		<label for="licenciatura">Curso:</label>
			<select class="form_input" name="licenciatura" id="licenciatura">
				<option value="-1">--Seleccionar--</option>
				<c:forEach var="curso" items="${licenciaturas}">
					<option value="${curso.id}">${curso.nombre}</option>
				</c:forEach>
			</select>
		<input type="submit" class="btn btnAgregar" value="Registro" id="btnRegistro"/>
	</form>
	</div>
	</div>
	
	
	<script type="text/javascript">
	var id = document.getElementById('id');
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
				nombre.value = data.nombre;
				paterno.value = data.paterno;
				materno.value = data.materno;
				correo.value = data.correo;
				licenciatura.value = data.licenciatura.id;
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

	form.addEventListener('submit', (e) => {
			e.preventDefault();
			datosCompletos.nombre = nombre.value;
			datosCompletos.paterno = paterno.value;
			datosCompletos.materno = materno.value;
			datosCompletos.correo = correo.value;
			datosCompletos.licenciatura.id = licenciatura.value;
			
			fetch('/alumnos/', {
				method: "POST",
	            body: JSON.stringify(datosCompletos),
	            headers:{
	    		'Content-Type': 'application/json'
	    		}
			}).then(result => result.json())
        	  .then(res => {
            	limpiarCampos();
                alert(res.mensaje);
                location.reload();
            })
		});
		
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