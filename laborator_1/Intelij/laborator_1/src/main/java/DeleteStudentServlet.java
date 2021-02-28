import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class DeleteStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        httpServletResponse.getWriter().print("Delete the student.xml file");

        String outputResult = new String();
        File file = new File("/Users/silviuh1/workspace/dev/facultate/ANUL_3/SD/laborator_1/student.xml");
        boolean result = Files.deleteIfExists(file.toPath()); //surround it in try catch block

        if(result == true){
            outputResult = "student.xml was deleted";
            httpServletResponse.sendRedirect("info-student-deleted.jsp");

        }
        else{
            outputResult = "student.xml does not exists on disk";
            httpServletResponse.sendRedirect("file-does-not-exists.jsp");
        }

        httpServletRequest.setAttribute("response", outputResult);

// redirectionare date catre pagina de afisare a informatiilor studentului
        // httpServletRequest.getRequestDispatcher("./info-student-deleted.jsp").forward(httpServletRequest, httpServletResponse);
        // httpServletResponse.sendRedirect("/index.jsp?error=something");
        // httpServletResponse.sendRedirect("info-student-deleted.jsp");
    }
}