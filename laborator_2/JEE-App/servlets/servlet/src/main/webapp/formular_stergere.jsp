<html xmlns:jsp="http://java.sun.com/JSP/Page">
	<head>
		<title>Formular student</title>
		<meta charset="UTF-8" />
	</head>
	<body>
		<h3>Formular curs</h3>
		Introduceti datele despre student:
		<form action="./delete-student" method="post">
			Nume: <input type="text" name="nume" />
			<br />
			Prenume: <input type="text" name="prenume" />
			<br />
			<br />
			<button type="submit" name="submit">Sterge</button>
		</form>
	</body>
</html>