package by.practic.ui.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.practic.datalayer.DAOException;
import by.practic.datalayer.IDao;
import by.practic.ui.UIException;

public class Util {

    private static final String ERROR_PAGE = "/practic7/error.jsp?error=";

    static <T, Y> void doGet(final HttpServletRequest req, final HttpServletResponse resp,
            final IDao<T, Y> dao) throws ServletException, IOException {
        final Integer id = Integer.valueOf(req.getParameter("id"));
        final String action = req.getParameter("action");

        if ("delete".equals(action)) {
            try {
                dao.delete(id);
            } catch (final DAOException e) {
                redirectToList(resp, ERROR_PAGE + e.getMessage());
            }
        } else {
            throw new UIException("operation unsupported");
        }
    }

    static void redirectToList(final HttpServletResponse resp, final String path) throws IOException {
        try {
            resp.sendRedirect(path);
        } catch (final IOException e) {
            throw new UIException(path, e);
        }
    }

}
