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
import by.practic.datalayer.entity.Brand;

public final class BrandDBDaoImpl extends AbstractDBDao implements IDao<Brand, Brand> {

    private static final String INSERT_SQL = "insert into brands (name) values (?)";
    private static final String UPDATE_SQL = "update brands set name=?, updated=? where id=?";
    private static final String DELETE_SQL = "delete from brands where id=%s";
    private static final String GET_SQL = "select * from brands where id=%s";
    private static final String GET_ALL_SQL = "select * from brands";
    private static final String FIND_BY_NAME = "select * from brands where name=\'%s\'";
    private static final String NAME = "name";
    private static IDao<Brand, Brand> instance;

    private BrandDBDaoImpl() {
    }

    public static IDao<Brand, Brand> getInstance() {
        if (instance == null) {
            instance = new BrandDBDaoImpl();
        }
        return instance;
    }

    @Override
    public Brand get(final Integer id) {

        try (Connection conn = createConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(String.format(GET_SQL, id));) {
            if (rs.next()) {

                final Brand brand = new Brand();

                brand.setId(rs.getInt(ID));
                brand.setName(rs.getString(NAME));
                brand.setCreated(rs.getDate(CREATED));
                brand.setUpdated(rs.getDate(UPDATED));
                return brand;
            }
        } catch (final SQLException exc) {
            throw new DAOException("Cannot get Brand: " + exc.getMessage(), exc);
        }
        return null;
    }

    @Override
    public Brand insert(final Brand entity) {
        try (Connection conn = createConnection();
                PreparedStatement stmt = conn.prepareStatement(INSERT_SQL,
                        Statement.RETURN_GENERATED_KEYS);) {

            stmt.setString(1, entity.getName());
            stmt.executeUpdate();

            final ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                final int generatedId = rs.getInt(1);
                entity.setId(generatedId);
            }
        } catch (final SQLException exc) {
            throw new DAOException("Cannot insert Brand: " + exc.getMessage(), exc);
        }
        return entity;
    }

    @Override
    public void update(final Brand entity) {
        try (Connection conn = createConnection();
                PreparedStatement stmt = conn.prepareStatement(UPDATE_SQL);) {

            stmt.setString(1, entity.getName());
            stmt.setObject(2, new Date(), Types.TIMESTAMP);
            stmt.setInt(3, entity.getId());

            stmt.executeUpdate();

        } catch (final SQLException exc) {
            throw new DAOException("Cannot update Brand: " + exc.getMessage(), exc);
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
            throw new DAOException("Cannot delete Brand: " + exc.getMessage(), exc);
        }
    }

    @Override
    public List<Brand> getAll() {

        final List<Brand> list = new ArrayList<>();

        try (Connection conn = createConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(GET_ALL_SQL);) {

            while (rs.next()) {

                final Brand brand = new Brand();

                brand.setId(rs.getInt(ID));
                brand.setName(rs.getString(NAME));
                brand.setCreated(rs.getDate(CREATED));
                brand.setUpdated(rs.getDate(UPDATED));
                list.add(brand);

            }
        } catch (final SQLException exc) {
            throw new DAOException("Cannot getAll Brand: " + exc.getMessage(), exc);
        }
        return list;
    }

    @Override
    public Brand find(final Integer id) {
        return get(id);
    }

    @Override
    public Brand findByName(final String name) {
        try (Connection conn = createConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(String.format(FIND_BY_NAME, name));) {

            if (rs.next()) {
                final Brand brand = new Brand();

                brand.setId(rs.getInt(ID));
                brand.setName(rs.getString(NAME));
                brand.setCreated(rs.getDate(CREATED));
                brand.setUpdated(rs.getDate(UPDATED));
                return brand;
            }
        } catch (final SQLException e) {
            throw new DAOException(e);
        }
        return null;
    }

}
