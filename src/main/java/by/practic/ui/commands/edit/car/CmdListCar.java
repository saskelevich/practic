package by.practic.ui.commands.edit.car;

import java.util.List;

import by.practic.datalayer.IDao;
import by.practic.datalayer.entity.Car;
import by.practic.datalayer.xml.CarXMLDaoImpl;
import by.practic.ui.commands.AbstractCmd;
import by.practic.ui.commands.Command;

@Command(name = "list", description = "list cars")
public class CmdListCar extends AbstractCmd {

    // DB
//	private IDao<Car, List<Car>> dao = CarDBDaoImpl.getInstance();

    // XML
    private final IDao<Car, List<Car>> dao = CarXMLDaoImpl.getInstance();

    @Override
    public AbstractCmd execute() {

        final List<Car> cars = dao.getAll();

        if (cars != null) {
            cars.stream().forEach(car -> System.out.println(car));
        } else {
            System.out.println("List is empty");
        }

        return new CmdEditCar();
    }
}