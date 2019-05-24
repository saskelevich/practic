package by.practic.ui.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.practic.datalayer.DAOException;
import by.practic.datalayer.IDao;
import by.practic.datalayer.db.ModelDBDaoImpl;
import by.practic.datalayer.entity.Model;

public class WebModel extends HttpServlet {

    private static final String ERROR_PAGE = "/practic7/error.jsp?error=";
    private static final String MODEL_JSP_PATH = "/practic7/update/model/models.jsp";
    private final IDao<Model, List<Model>> dao = ModelDBDaoImpl.getInstance();

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {

        final String modelName = req.getParameter("model");
        if (modelName != null) {
            try {
                dao.findByName(modelName);
            } catch (final DAOException e) {
                Util.redirectToList(resp, ERROR_PAGE + e.getMessage());
            }
            Util.redirectToList(resp, "/practic7/update/model/models.jsp?name=" + modelName);
        } else {
            Util.doGet(req, resp, dao);
            Util.redirectToList(resp, MODEL_JSP_PATH);
        }
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        final String modelName = req.getParameter("name");
        Integer brandId = null;
        try {
            brandId = Integer.valueOf(req.getParameter("brandId"));
        } catch (final NumberFormatException e) {
            Util.redirectToList(resp, ERROR_PAGE + "Model id cannot be empty: " + e.getMessage());
        }
        Integer id = null;
        try {
            id = Integer.valueOf(req.getParameter("id"));
        } catch (final NumberFormatException e) {
            // nothing to do
        }
        if (id == null) {
            final Model model = new Model();
            model.setName(modelName);
            model.setBrandId(brandId);
            try {
                dao.insert(model);
            } catch (final DAOException e) {
                Util.redirectToList(resp, ERROR_PAGE + e.getMessage());
            }
        } else {
            final Model model = new Model();
            model.setName(modelName);
            model.setBrandId(brandId);
            model.setId(id);
            try {
                dao.update(model);
            } catch (final DAOException e) {
                Util.redirectToList(resp, ERROR_PAGE + e.getMessage());
            }
        }
        Util.redirectToList(resp, MODEL_JSP_PATH);
    }

}
