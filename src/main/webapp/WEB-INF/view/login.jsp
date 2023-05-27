<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		
    <h1>Login</h1>
     <form>
        <label for="username">Username:</label>
        <input type="text" id="username" name="username"><br><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password"><br><br>
        <input type="submit" value="Entrar">
    </form>
    
    <script type="text/javascript">
    const url = '/login/entrar';
    const options = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      body: 'username=oscar&password=123456'
    }

    document.querySelector('form').addEventListener('submit', (e) => {
    	  e.preventDefault();
    	  
    	  const url = '/login/entrar';
    	  const options = {
    	    method: 'POST',
    	    headers: {
    	      'Content-Type': 'application/x-www-form-urlencoded'
    	    },
    	    body: 'username=oscar&password=123456'
    	  };
    	  
    	  fetch(url, options)
    	    .then(response => {
    	      if (response.ok) {
    	        // La solicitud se completó con éxito
    	        console.log('La solicitud POST se realizó correctamente');
    	      } else {
    	        // Hubo un error en la solicitud
    	        console.error('Error en la solicitud POST');
    	      }
    	    })
    	    .catch(error => {
    	      // Hubo un error en la solicitud
    	      console.error('Error en la solicitud POST', error);
    	    });
    	});

    
    </script>
</body>
</html>