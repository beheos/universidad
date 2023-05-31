<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../../resources/css/login.css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
	  <div class="login-container">
    <h2>Login</h2>
    <form action="/login" method="POST">
      <label for="username">Usuario:</label>
      <input type="text" id="username" name="username" placeholder="Usuario" required>

      <label for="password">Contraseña:</label>
      <input type="password" id="password" name="password" placeholder="Contraseña" required>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      <button type="submit">Iniciar sesión</button>
    </form>
  </div>
</body>
</html>