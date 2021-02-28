import beans.StudentBean;
import beans.StudentFinderBean;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class StudentFinderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        File file = new File("/Users/silviuh1/workspace/dev/facultate/ANUL_3/SD/laborator_1/student.xml");
        if (!file.exists()) {
            response.sendError(404, "nu exista studenti in db");
            return;
        }

        XmlMapper xmlMapper = new XmlMapper();
        BufferedReader r = new BufferedReader(new FileReader(file));
        String studentEntry = r.readLine();

        String debugFileName = "/Users/silviuh1/workspace/dev/facultate/ANUL_3/SD/laborator_1/debug";
        FileWriter fwDebug = new FileWriter(debugFileName);

        while (studentEntry != null) {
            if (studentEntry.contains(request.getParameter("nume")) && studentEntry.contains(request.getParameter("prenume"))) {
                try {
                    String filename = "/Users/silviuh1/workspace/dev/facultate/ANUL_3/SD/laborator_1/current_entry.xml";
                    FileWriter fw = new FileWriter(filename);
                    fw.write(studentEntry);
                    fw.close();
                } catch (IOException ioe) {
                    System.err.println("IOException: " + ioe.getMessage());
                }

                File currentEntry = new File("/Users/silviuh1/workspace/dev/facultate/ANUL_3/SD/laborator_1/current_entry.xml");
                StudentBean foundStudentEntry = xmlMapper.readValue(currentEntry,
                        StudentBean.class);

                request.setAttribute("nume", request.getParameter("nume"));
                request.setAttribute("prenume", request.getParameter("prenume"));
                request.setAttribute("adresa", foundStudentEntry.getAdresa());
                request.setAttribute("hobby", foundStudentEntry.getHobby());
                request.setAttribute("initialaTatalui", foundStudentEntry.getInitialaTatalui());
                request.setAttribute("varsta", foundStudentEntry.getVarsta());

                request.getRequestDispatcher("./info-student.jsp").forward(request, response);
                return;
            }
            studentEntry = r.readLine();
        }
        r.close();

        request.getRequestDispatcher("./student-not-found.jsp").forward(request, response);
    }
}