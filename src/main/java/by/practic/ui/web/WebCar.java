package by.practic.ui.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.practic.datalayer.DAOException;
import by.practic.datalayer.IDao;
import by.practic.datalayer.db.CarDBDaoImpl;
import by.practic.datalayer.entity.Car;

public class WebCar extends HttpServlet {

    private static final String ERROR_PAGE = "/practic7/error.jsp?error=";
    private static final String CAR_JSP_PATH = "/practic7/update/car/cars.jsp";
    private final IDao<Car, List<Car>> dao = CarDBDaoImpl.getInstance();

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {

        final String carName = req.getParameter("car");
        if (carName != null) {
            try {
                dao.findByName(carName);
            } catch (final DAOException e) {
                Util.redirectToList(resp, ERROR_PAGE + e.getMessage());
            }
            Util.redirectToList(resp, "/practic7/update/car/cars.jsp?name=" + carName);
        } else {
            Util.doGet(req, resp, dao);
            Util.redirectToList(resp, CAR_JSP_PATH);
        }
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        final String vin = req.getParameter("vin");
        Integer modelId = null;
        try {
            modelId = Integer.valueOf(req.getParameter("modelId"));
        } catch (final NumberFormatException e) {
            Util.redirectToList(resp, ERROR_PAGE + "Car id cannot be empty: " + e.getMessage());
        }
        Integer id = null;
        try {
            id = Integer.valueOf(req.getParameter("id"));
        } catch (final NumberFormatException e) {
            // nothing to do
        }
        if (id == null) {
            final Car car = new Car();
            car.setVin(vin);
            car.setModelId(modelId);
            try {
                dao.insert(car);
            } catch (final DAOException e) {
                Util.redirectToList(resp, ERROR_PAGE + e.getMessage());
            }
        } else {
            final Car car = new Car();
            car.setVin(vin);
            car.setModelId(modelId);
            car.setId(id);
            try {
                dao.update(car);
            } catch (final DAOException e) {
                Util.redirectToList(resp, ERROR_PAGE + e.getMessage());
            }
        }
        Util.redirectToList(resp, CAR_JSP_PATH);
    }
}