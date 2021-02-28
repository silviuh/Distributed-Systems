import beans.StudentBean;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Year;

public class ProcessStudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
// se citesc parametrii din cererea de tip POST
        String nume = request.getParameter("nume");
        String prenume = request.getParameter("prenume");
        String initialaTatalui = request.getParameter("initialaTatalui");
        String adresa = request.getParameter("adresa");
        String hobby = request.getParameter("hobby");

        int varsta = Integer.parseInt(request.getParameter("varsta"));
        int anCurent = Year.now().getValue();
        int anNastere = anCurent - varsta;


        XmlMapper mapper = new XmlMapper();
        StudentBean bean = new StudentBean();

        bean.setNume(nume);
        bean.setPrenume(prenume);
        bean.setVarsta(varsta);
        bean.setAdresa(adresa);
        bean.setInitialaTatalui(initialaTatalui);
        bean.setHobby(hobby);

        try // appending new line after each student in the xml file
        {
            String currentStudentEntry = mapper.writeValueAsString(bean);
            String filename = "/Users/silviuh1/workspace/dev/facultate/ANUL_3/SD/laborator_1/student.xml";
            FileWriter fw = new FileWriter(filename, true); //the true will append the new data
            fw.write(currentStudentEntry);
            fw.write("\n");//appends the string to the file
            fw.close();
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }

        request.setAttribute("nume", nume);
        request.setAttribute("prenume", prenume);
        request.setAttribute("varsta", varsta);
        request.setAttribute("adresa", adresa);
        request.setAttribute("initialaTatalui", initialaTatalui);
        request.setAttribute("hobby", hobby);
        request.getRequestDispatcher("./info-student.jsp").forward(request, response);
    }
}