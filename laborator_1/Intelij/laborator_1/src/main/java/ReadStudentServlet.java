import beans.StudentBean;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
public class ReadStudentServlet extends HttpServlet {
    @Override
    // this method does not work in the second case where we have multiple
    // xml entries for students in the same xml file
    // the method uses xmlMapper as a deserializer, therefore it expects one entry for the bean (1 : 1 mapping)
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
// deserializare student din fisierul XML de pe disc
        File file = new File("/Users/silviuh1/workspace/dev/facultate/ANUL_3/SD/laborator_1/student.xml");
        if (!file.exists()) {
            response.sendError(404, "Nu a fost gasit niciun student serializat pe disc!");
            return;
        }
        XmlMapper xmlMapper = new XmlMapper();
        StudentBean bean = xmlMapper.readValue(file,
                StudentBean.class);

        request.setAttribute("adresa", bean.getAdresa());
        request.setAttribute("hobby", bean.getHobby());
        request.setAttribute("initialaTatalui", bean.getInitialaTatalui());
        request.setAttribute("nume", bean.getNume());
        request.setAttribute("prenume", bean.getPrenume());
        request.setAttribute("varsta", bean.getVarsta());
// redirectionare date catre pagina de afisare a informatiilor studentului
        request.getRequestDispatcher("./info-student.jsp").forward(request, response);
    }
}