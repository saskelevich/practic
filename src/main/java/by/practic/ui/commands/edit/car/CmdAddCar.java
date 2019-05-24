package by.practic.ui.commands.edit.car;

import java.util.List;

import by.practic.datalayer.IDao;
import by.practic.datalayer.entity.Car;
import by.practic.datalayer.xml.CarXMLDaoImpl;
import by.practic.ui.commands.AbstractCmd;
import by.practic.ui.commands.Command;
import by.practic.ui.commands.edit.model.CmdListModel;

@Command(name = "add", description = "add car")
public class CmdAddCar extends AbstractCmd {

    // DB
//	private IDao<Car, List<Car>> dao = CarDBDaoImpl.getInstance();

    // XML
    private final IDao<Car, List<Car>> dao = CarXMLDaoImpl.getInstance();

    @Override
    public AbstractCmd execute() {

        System.out.println("input new car vin:");
        final String newCarVin = readInput();

        final Car car = new Car();
        car.setVin(newCarVin);

        new CmdListModel().execute();
        System.out.println("input model id");
        Integer id = 0;
        try {
            id = Integer.valueOf(readInput());
        } catch (final NumberFormatException e) {
            System.err.println("car model_id must have integer value");
            return new CmdEditCar();
        }
        car.setModelId(id);

        final Car newEntity = dao.insert(car);
        System.out.println("New car saved: " + newEntity);

        return new CmdEditCar();
    }
}
