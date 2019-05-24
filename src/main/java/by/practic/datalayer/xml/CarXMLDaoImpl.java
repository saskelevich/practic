package by.practic.datalayer.xml;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import by.practic.datalayer.IDao;
import by.practic.datalayer.entity.Car;
import by.practic.datalayer.xml.table.CarTable;

public final class CarXMLDaoImpl extends AbstractXMLDao<CarTable> implements IDao<Car, List<Car>> {

    private static final String FILE_NAME = "cars-table.xml";

    private static IDao<Car, List<Car>> instance;

    private CarXMLDaoImpl() {
    }

    public static IDao<Car, List<Car>> getInstance() {

        if (instance == null) {
            instance = new CarXMLDaoImpl();
        }
        return instance;
    }

    @Override
    public Car insert(final Car entity) {

        final CarTable carTable = read();
        final Integer id = carTable.nextId();

        entity.setId(id);
        final Date created = new Date();
        entity.setCreated(created);
        entity.setUpdated(created);
        carTable.getCars().add(entity);

        write(carTable);

        return entity;
    }

    @Override
    public void update(final Car entity) {

        final CarTable table = read();

        table.getCars().stream().forEach(str -> {
            if (str.getId().equals(entity.getId())) {
                str.setVin(entity.getVin());
                str.setModelId(entity.getModelId());
                str.setUpdated(new Date());
            }
        });
        write(table);
    }

    @Override
    public void delete(final Integer id) {

        final CarTable carTable = read();
        final Iterator<Car> iterator = carTable.getCars().iterator();

        while (iterator.hasNext()) {
            final Car car = iterator.next();
            if (car.getId().equals(id)) {
                iterator.remove();
            }
        }
        write(carTable);
    }

    @Override
    public List<Car> getAll() {

        final CarTable carTable = read();
        return carTable.getCars();
    }

    @Override
    public Car get(final Integer id) {

        for (final Car car : getAll()) {
            if (car.getId().equals(id)) {
                return car;
            }
        }
        return null;
    }

    @Override
    protected String getFileName() {
        return FILE_NAME;
    }

    @Override
    protected Class<CarTable> getTableClass() {
        return CarTable.class;
    }

    @Override
    public List<Car> find(final Integer id) {

        final List<Car> cars = new ArrayList<>();

        getAll().stream().filter(car -> car.getModelId().equals(id)).forEach(str -> cars.add(str));

        return cars;
    }

    @Override
    public List<Car> findByName(final String name) {
        // TODO Auto-generated method stub
        return null;
    }

}
