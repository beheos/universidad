var nombre = document.getElementById('nombre');
var paterno = document.getElementById('paterno');
var materno = document.getElementById('materno');
var correo = document.getElementById('correo');
var licenciatura = document.getElementById('licenciatura');
var form = document.querySelector('form');

form.addEventListener('submit', (e) => {
    e.preventDefault();
    
    var url = '/registrarse';
    if(validarFormulario()){

        var alumno = {"nombre": nombre.value,
        "paterno": paterno.value,
        "materno": materno.value,
        "correo": correo.value,
        "licenciatura": {
        		"id": licenciatura.value
        	}
        }

        fetch(url, {
            method: "POST",
            body: JSON.stringify(alumno),
            headers:{
    		'Content-Type': 'application/json'
    		}
        }).then(result => result.json())
        	.then(res => {
        	limpiarCampos();
            alert(res.mensaje);
        });
    }
        
});

const validarFormulario = () => {
    if(nombre.value == ''){
        nombre.focus();
        return false;
    }else{
        nombre.blur();
    }
    if(paterno.value == ''){
        paterno.focus();
        return false;
    }else{
        paterno.blur();
    }
    if(correo.value == ''){
        correo.focus();
        return false;
    }else{
        correo.blur();
    }
    if(licenciatura.value == '-1'){
        licenciatura.focus();
        return false;
    }else{
        licenciatura.blur();
    }

    return true;
}

const limpiarCampos = () => {
	nombre.value = '';
	paterno.value = '';
	materno.value = '';
	correo.value = '';
	licenciatura.value = '-1';
}
