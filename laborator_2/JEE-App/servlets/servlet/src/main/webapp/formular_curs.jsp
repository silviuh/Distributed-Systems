<html xmlns:jsp="http://java.sun.com/JSP/Page">
	<head>
		<title>Formular student</title>
		<meta charset="UTF-8" />
	</head>
	<body>
		<h3>Formular curs</h3>
		Introduceti datele despre curs:
		<form action="./process-course" method="post">
			Nume: <input type="text" name="nume" />
			<br />
			Numar_Credite: <input type="number" name="credite" />
			<br />
			Numar_Semestru: <input type="number" name="numar_semestru" />
            <br />
			<br/>
			<button type="submit" name="submit">Trimite</button>
		</form>
	</body>
</html>