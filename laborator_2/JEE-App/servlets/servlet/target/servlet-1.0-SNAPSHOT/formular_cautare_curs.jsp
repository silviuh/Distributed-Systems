<html xmlns:jsp="http://java.sun.com/JSP/Page">
	<head>
		<title>Formular student</title>
		<meta charset="UTF-8" />
	</head>
	<body>
		<h3>Formular curs</h3>
		Introduceti datele despre curs:
		<form action="./update-course" method="post">
			Nume: <input type="text" name="nume" />
			<br/>
            Nume_Nou: <input type="text" name="nume_nou" />
            <br/>
			Numar_Credite_Nou: <input type="number" name="credite_nou" />
            <br />
            Numar_Semestru_Nou: <input type="number" name="numar_semestru_nou" />
            <br />
			<button type="submit" name="submit">Actualizare</button>
		</form>
	</body>
</html>