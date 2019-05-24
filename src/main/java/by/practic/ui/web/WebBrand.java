package by.practic.ui.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.practic.datalayer.DAOException;
import by.practic.datalayer.IDao;
import by.practic.datalayer.db.BrandDBDaoImpl;
import by.practic.datalayer.entity.Brand;

public class WebBrand extends HttpServlet {

    private static final String ERROR_PAGE = "/practic7/error.jsp?error=";
    private static final String BRANDS_JSP_PATH = "/practic7/update/brand/brands.jsp";
    private final IDao<Brand, Brand> dao = BrandDBDaoImpl.getInstance();

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {

        final String brandName = req.getParameter("brand");
        if (brandName != null) {
            try {
                dao.findByName(brandName);
            } catch (final DAOException e) {
                Util.redirectToList(resp, ERROR_PAGE + e.getMessage());
            }
            Util.redirectToList(resp, "/practic7/update/brand/brands.jsp?name=" + brandName);
        } else {
            Util.doGet(req, resp, dao);
            Util.redirectToList(resp, BRANDS_JSP_PATH);
        }
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        final String brandName = req.getParameter("name");
        Integer id = null;
        try {
            id = Integer.valueOf(req.getParameter("id"));
        } catch (final NumberFormatException e) {
            // nothing to do
        }
        if (id == null) {
            final Brand brand = new Brand();
            brand.setName(brandName);
            try {
                dao.insert(brand);
            } catch (final DAOException e) {
                Util.redirectToList(resp, ERROR_PAGE + e.getMessage());
            }
        } else {
            final Brand brand = new Brand();
            brand.setName(brandName);
            brand.setId(id);
            dao.update(brand);
        }
        Util.redirectToList(resp, BRANDS_JSP_PATH);
    }
}
