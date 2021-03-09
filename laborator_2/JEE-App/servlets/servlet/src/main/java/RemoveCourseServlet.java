import ejb.CourseEntity;
import ejb.StudentEntity;

import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveCourseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nume = request.getParameter("nume");
        CourseEntity courseEntity;

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("bazaDeDateSQLite");
        EntityManager em = factory.createEntityManager();

        try {
            courseEntity = em.createQuery(
                    "SELECT course from CourseEntity course WHERE course.nume = :nume", CourseEntity.class)
                    .setParameter("nume", nume)
                    .getSingleResult();
        } catch (NoResultException e) {
            response.setContentType("text/html");
            response.getWriter().println("Cursul nu a fost gasit in db." +
                    "<br /><br /><a href='./'>Inapoi la meniul principal</a>");
            return;
        }

        CourseEntity course = em.find(CourseEntity.class, courseEntity.getId());
        em.getTransaction().begin();
        em.remove(course);
        em.getTransaction().commit();

        em.close();
        factory.close();

        response.setContentType("text/html");
        response.getWriter().println("Cursul a fost sters." +
                "<br /><br /><a href='./'>Inapoi la meniul principal</a>");
    }
}
