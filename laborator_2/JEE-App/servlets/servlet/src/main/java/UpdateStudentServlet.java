import ejb.StudentEntity;

import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateStudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nume = request.getParameter("nume");
        String prenume = request.getParameter("prenume");
        String nume_nou = request.getParameter("nume_nou");
        String prenume_nou = request.getParameter("prenume_nou");
        int varsta_noua = Integer.parseInt(request.getParameter("varsta_noua"));
        StudentEntity student;

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("bazaDeDateSQLite");
        EntityManager em = factory.createEntityManager();

        try {
            student = em.createQuery(
                    "SELECT student from StudentEntity student WHERE student.nume = :nume and student.prenume = :prenume", StudentEntity.class)
                    .setParameter("nume", nume)
                    .setParameter("prenume", prenume)
                    .getSingleResult();
        } catch (NoResultException e) {
            response.setContentType("text/html");
            response.getWriter().println("Studentul nu a fost gasit in db." +
                    "<br /><br /><a href='./'>Inapoi la meniul principal</a>");
            return;
        }

        StudentEntity studentEntity = em.find(StudentEntity.class, student.getId());
        em.getTransaction().begin();
        studentEntity.setNume(nume_nou);
        studentEntity.setPrenume(prenume_nou);
        studentEntity.setVarsta(varsta_noua);
        em.getTransaction().commit();

        em.close();
        factory.close();

        response.setContentType("text/html");
        response.getWriter().println("Studentul a fost actualizat." +
                "<br /><br /><a href='./'>Inapoi la meniul principal</a>");
    }
}
