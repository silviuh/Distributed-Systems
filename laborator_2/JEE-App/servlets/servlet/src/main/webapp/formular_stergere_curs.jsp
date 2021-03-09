<html xmlns:jsp="http://java.sun.com/JSP/Page">
	<head>
		<title>Formular student</title>
		<meta charset="UTF-8" />
	</head>
	<body>
		<h3>Formular student</h3>
		Introduceti datele despre curs:
		<form action="./delete-course" method="post">
			Nume: <input type="text" name="nume" />
			<br />
			<button type="submit" name="submit">Sterge</button>
		</form>
	</body>
</html>