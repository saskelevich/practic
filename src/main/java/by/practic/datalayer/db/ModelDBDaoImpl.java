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
import by.practic.datalayer.entity.Model;

public final class ModelDBDaoImpl extends AbstractDBDao implements IDao<Model, List<Model>> {

    private static final String FIND_SQL = "select * from models where brand_id=%s";
    private static final String FIND_BY_NAME = "select * from models where name=\'%s\'";
    private static final String GET_ALL_SQL = "select * from models";
    private static final String DELETE_SQL = "delete from models where id=%s";
    private static final String UPDATE_SQL = "update models set name=?, updated=?, brand_id=? where id=?";
    private static final String INSERT_SQL = "insert into models (name, brand_id) values (?, ?)";
    private static final String GET_SQL = "select * from models where id=%s";
    private static final String BRAND_ID = "brand_id";
    private static final String NAME = "name";
    private static IDao<Model, List<Model>> instance;

    private ModelDBDaoImpl() {
    }

    public static IDao<Model, List<Model>> getInstance() {
        if (instance == null) {
            instance = new ModelDBDaoImpl();
        }
        return instance;
    }

    @Override
    public Model get(final Integer id) {
        try (Connection conn = createConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(String.format(GET_SQL, id));) {

            while (rs.next()) {
                final Model model = new Model();

                model.setId(rs.getInt(ID));
                model.setName(rs.getString(NAME));
                model.setCreated(rs.getDate(CREATED));
                model.setUpdated(rs.getDate(UPDATED));
                model.setBrandId(rs.getInt(BRAND_ID));

                return model;
            }
        } catch (final SQLException exc) {
            throw new DAOException("Cannot get Model: " + exc.getMessage(), exc);
        }
        return null;
    }

    @Override
    public Model insert(final Model entity) {
        try (Connection conn = createConnection();
                PreparedStatement stmt = conn.prepareStatement(INSERT_SQL,
                        Statement.RETURN_GENERATED_KEYS);) {

            stmt.setString(1, entity.getName());
            stmt.setInt(2, entity.getBrandId());
            stmt.executeUpdate();

            final ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                final int generatedId = rs.getInt(1);
                entity.setId(generatedId);
            }
        } catch (final SQLException exc) {
            throw new DAOException("Cannot insert Model: " + exc.getMessage(), exc);
        }
        return entity;
    }

    @Override
    public void update(final Model entity) {
        try (Connection conn = createConnection();
                PreparedStatement stmt = conn.prepareStatement(UPDATE_SQL);) {

            stmt.setString(1, entity.getName());
            stmt.setObject(2, new Date(), Types.TIMESTAMP);
            stmt.setInt(3, entity.getBrandId());
            stmt.setInt(4, entity.getId());

            stmt.executeUpdate();
        } catch (final SQLException exc) {
            throw new DAOException("Cannot update Model: " + exc.getMessage(), exc);
        }
    }

    @Override
    public void delete(final Integer id) {
        try (Connection conn = createConnection(); Statement stmt = conn.createStatement();) {

            final int rowsAffected = stmt.executeUpdate(String.format(DELETE_SQL, id));

            if (rowsAffected != 1) {
                throw new IllegalArgumentException("unexpected deleted rows count:" + rowsAffected);
            }
        } catch (

        final SQLException exc) {
            throw new DAOException("Cannot delete Model: " + exc.getMessage(), exc);
        }
    }

    @Override
    public List<Model> getAll() {
        final List<Model> list = new ArrayList<>();

        try (Connection conn = createConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(GET_ALL_SQL);) {

            while (rs.next()) {
                final Model model = new Model();

                model.setId(rs.getInt(ID));
                model.setName(rs.getString(NAME));
                model.setCreated(rs.getDate(CREATED));
                model.setUpdated(rs.getDate(UPDATED));
                model.setBrandId(rs.getInt(BRAND_ID));
                list.add(model);
            }
        } catch (final SQLException exc) {
            throw new DAOException("Cannot get all Model: " + exc.getMessage(), exc);
        }
        return list;
    }

    @Override
    public List<Model> find(final Integer id) {

        final List<Model> list = new ArrayList<>();

        try (Connection connection = createConnection();
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(String.format(FIND_SQL, id));) {

            while (rs.next()) {
                final Model model = new Model();

                model.setId(rs.getInt(ID));
                model.setName(rs.getString(NAME));
                model.setCreated(rs.getDate(CREATED));
                model.setUpdated(rs.getDate(UPDATED));
                model.setBrandId(rs.getInt(BRAND_ID));

                list.add(model);
            }
        } catch (final SQLException exc) {
            throw new DAOException("Cannot search Model: " + exc.getMessage(), exc);
        }
        return list;
    }

    @Override
    public List<Model> findByName(final String name) {
        final List<Model> list = new ArrayList<>();

        try (Connection connection = createConnection();
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(String.format(FIND_BY_NAME, name));) {

            while (rs.next()) {
                final Model model = new Model();

                model.setId(rs.getInt(ID));
                model.setName(rs.getString(NAME));
                model.setCreated(rs.getDate(CREATED));
                model.setUpdated(rs.getDate(UPDATED));
                model.setBrandId(rs.getInt(BRAND_ID));

                list.add(model);
            }
        } catch (final SQLException exc) {
            throw new DAOException("Cannot search Model: " + exc.getMessage(), exc);
        }
        return list;
    }

}
