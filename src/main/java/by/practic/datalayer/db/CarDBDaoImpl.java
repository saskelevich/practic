package by.practic.datalayer.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import by.practic.datalayer.DAOException;
import by.practic.datalayer.IDao;
import by.practic.datalayer.entity.Car;

public final class CarDBDaoImpl extends AbstractDBDao implements IDao<Car, List<Car>> {

    private static final String FIND_SQL = "select * from cars where model_id=%s";
    private static final String FIND_BY_VIN = "select * from cars where vin=\'%s\'";
    private static final String GET_ALL_SQL = "select * from cars";
    private static final String DELETE_SQL = "delete from cars where id=%s";
    private static final String UPDATE_SQL = "update cars set vin=?, updated=?, model_id=? where id=?";
    private static final String INSERT_SQL = "insert into cars (vin, model_id) values (?, ?)";
    private static final String GET_SQL = "select * from cars where id=%s";
    private static final String VIN = "vin";
    private static final String MODEL_ID = "model_id";
    private static IDao<Car, List<Car>> instance;

    private CarDBDaoImpl() {
    }

    public static IDao<Car, List<Car>> getInstance() {
        if (instance == null) {
            instance = new CarDBDaoImpl();
        }
        return instance;
    }

    @Override
    public Car get(final Integer id) {
        try (Connection conn = createConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(String.format(GET_SQL, id));) {

            if (rs.next()) {
                final Car car = new Car();

                car.setId(rs.getInt(ID));
                car.setVin(rs.getString(VIN));
                car.setCreated(rs.getDate(CREATED));
                car.setUpdated(rs.getDate(UPDATED));
                car.setModelId(rs.getInt(MODEL_ID));

                return car;
            }
        } catch (final SQLException exc) {
            throw new DAOException("Cannot get Car: " + exc.getMessage(), exc);
        }
        return null;
    }

    @Override
    public Car insert(final Car entity) {
        try (Connection conn = createConnection();
                PreparedStatement stmt = conn.prepareStatement(INSERT_SQL,
                        Statement.RETURN_GENERATED_KEYS);) {

            stmt.setString(1, entity.getVin());
            stmt.setInt(2, entity.getModelId());
            stmt.executeUpdate();

            final ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                final int generatedId = rs.getInt(1);
                entity.setId(generatedId);
            }
        } catch (final SQLException exc) {
            throw new DAOException("Cannot insert Car: " + exc.getMessage(), exc);
        }
        return entity;
    }

    @Override
    public void update(final Car entity) {
        try (Connection conn = createConnection();
                PreparedStatement stmt = conn.prepareStatement(UPDATE_SQL);) {

            stmt.setString(1, entity.getVin());
            stmt.setObject(2, new Date(), Types.TIMESTAMP);
            stmt.setInt(3, entity.getModelId());
            stmt.setInt(4, entity.getId());

            stmt.executeUpdate();
        } catch (final SQLException exc) {
            throw new DAOException("Cannot update Car: " + exc.getMessage(), exc);
        }
    }

    @Override
    public void delete(final Integer id) {
        try (Connection conn = createConnection(); Statement stmt = conn.createStatement();) {

            final int rowsAffected = stmt.executeUpdate(String.format(DELETE_SQL, id));

            if (rowsAffected != 1) {
                throw new IllegalArgumentException("unexpected deleted rows count:" + rowsAffected);
            }
        } catch (final SQLException exc) {
            throw new DAOException("Cannot delete Car: " + exc.getMessage(), exc);
        }
    }

    @Override
    public List<Car> getAll() {
        final List<Car> list = new ArrayList<>();
        try (Connection conn = createConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(GET_ALL_SQL);) {

            while (rs.next()) {
                final Car car = new Car();

                car.setId(rs.getInt(ID));
                car.setVin(rs.getString(VIN));
                car.setCreated(rs.getDate(CREATED));
                car.setUpdated(rs.getDate(UPDATED));
                car.setModelId(rs.getInt(MODEL_ID));
                list.add(car);
            }
        } catch (final SQLException exc) {
            throw new DAOException("Cannot getAll Car: " + exc.getMessage(), exc);
        }
        return list;
    }

    @Override
    public List<Car> find(final Integer id) {

        final List<Car> list = new ArrayList<>();

        try (Connection connection = createConnection();
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(String.format(FIND_SQL, id));) {

            while (rs.next()) {
                final Car car = new Car();

                car.setId(rs.getInt(ID));
                car.setVin(rs.getString(VIN));
                car.setCreated(rs.getDate(CREATED));
                car.setUpdated(rs.getDate(UPDATED));
                car.setModelId(rs.getInt(MODEL_ID));

                list.add(car);
            }
        } catch (final SQLException exc) {
            throw new DAOException("Cannot search Car: " + exc.getMessage(), exc);
        }
        return list;
    }

    @Override
    public List<Car> findByName(final String name) {
        final List<Car> list = new ArrayList<>();

        try (Connection connection = createConnection();
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(String.format(FIND_BY_VIN, name));) {

            while (rs.next()) {
                final Car car = new Car();

                car.setId(rs.getInt(ID));
                car.setVin(rs.getString(VIN));
                car.setCreated(rs.getDate(CREATED));
                car.setUpdated(rs.getDate(UPDATED));
                car.setModelId(rs.getInt(MODEL_ID));

                list.add(car);
            }
        } catch (final SQLException exc) {
            throw new DAOException("Cannot search Car: " + exc.getMessage(), exc);
        }
        return list;
    }

}
