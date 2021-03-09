import ejb.CourseEntity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FetchCoursesListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // pregatire EntityManager
        EntityManagerFactory factory =   Persistence.createEntityManagerFactory("bazaDeDateSQLite");
        EntityManager em = factory.createEntityManager();

        StringBuilder responseText = new StringBuilder();
        responseText.append("<h2>Lista cursuri</h2>");
        responseText.append("<table border='1'><thead><tr><th>ID</th><th>Nume</th><th>Nr_Credite</th><th>Semestru</th></thead>");
        responseText.append("<tbody>");

        // preluare date coursei din baza de date
        TypedQuery<CourseEntity> query = em.createQuery("select course from CourseEntity course", CourseEntity.class);
        List<CourseEntity> results = query.getResultList();
        for (CourseEntity course : results) {
            // se creeaza cate un rand de tabel HTML pentru fiecare course gasit
            responseText.append("<tr><td>" + course.getId() + "</td><td>" +
                    course.getNume() + "</td><td>" + course.getCredits() +
                    "</td><td>" + course.getSemester_number() + "</td></tr>");
        }

        responseText.append("</tbody></table><br /><br /><a href='./'>Inapoi la meniul principal</a>");

        // inchidere EntityManager
        em.close();
        factory.close();

        // trimitere raspuns la client
        response.setContentType("text/html");
        response.getWriter().print(responseText.toString());
    }
}
