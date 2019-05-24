package by.practic.datalayer.xml;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import by.practic.datalayer.IDao;
import by.practic.datalayer.entity.Brand;
import by.practic.datalayer.xml.table.BrandTable;

public final class BrandXMLDaoImpl extends AbstractXMLDao<BrandTable> implements IDao<Brand, Brand> {

    private static final String FILE_NAME = "brands-table.xml";
    private static IDao<Brand, Brand> instance;

    private BrandXMLDaoImpl() {
    }

    public static IDao<Brand, Brand> getInstance() {

        if (instance == null) {
            instance = new BrandXMLDaoImpl();
        }
        return instance;
    }

    @Override
    public Brand insert(final Brand entity) {

        final BrandTable table = read();

        final Integer id = table.nextId();

        final Date created = new Date();
        entity.setCreated(created);
        entity.setUpdated(created);
        entity.setId(id);
        table.getBrands().add(entity);

        write(table);

        return entity;
    }

    @Override
    public void update(final Brand entity) {

        final BrandTable table = read();

        table.getBrands().stream().filter(brand -> (brand.getId().equals(entity.getId()))).forEach(brand -> {
            brand.setName(entity.getName());
            brand.setId(entity.getId());
            brand.setUpdated(new Date());
        });
        write(table);
    }

    @Override
    public void delete(final Integer id) {

        final BrandTable table = read();
        final Iterator<Brand> iterator = table.getBrands().iterator();

        while (iterator.hasNext()) {
            final Brand brand = iterator.next();
            if (brand.getId().equals(id)) {
                iterator.remove();
            }
        }
        write(table);
    }

    @Override
    public List<Brand> getAll() {

        final BrandTable table = read();

        final List<Brand> brands = table.getBrands();

        if (brands != null) {
            return brands;
        }
        return null;
    }

    @Override
    String getFileName() {
        return FILE_NAME;
    }

    @Override
    Class<BrandTable> getTableClass() {
        return BrandTable.class;
    }

    @Override
    public Brand get(final Integer id) {

        for (final Brand brand : getAll()) {
            if (brand.getId().equals(id)) {
                return brand;
            }
        }
        return null;
    }

    @Override
    public Brand find(final Integer id) {

        final Brand brand = new Brand();

        for (final Brand brand1 : getAll()) {
            if (brand1.getId().equals(id)) {

                brand.setCreated(brand1.getCreated());
                brand.setId(brand1.getId());
                brand.setName(brand1.getName());
                brand.setUpdated(brand1.getUpdated());

                return brand;
            }
        }
        return null;
    }

    @Override
    public Brand findByName(final String name) {
        // TODO Auto-generated method stub
        return null;
    }

}