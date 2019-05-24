package by.practic.ui.commands;

import java.util.List;

import by.practic.datalayer.IDao;
import by.practic.datalayer.entity.Brand;
import by.practic.datalayer.entity.Car;
import by.practic.datalayer.entity.Model;
import by.practic.datalayer.xml.BrandXMLDaoImpl;
import by.practic.datalayer.xml.CarXMLDaoImpl;
import by.practic.datalayer.xml.ModelXMLDaoImpl;
import by.practic.ui.UIException;

@Command(name = "search", description = "look for car")
public class CmdSearch extends AbstractCmd {

    // DB
//	private IDao<Brand, Brand> searchBrand = BrandDBDaoImpl.getInstance();
//	private IDao<Model, List<Model>> searchModel = ModelDBDaoImpl.getInstance();
//	private IDao<Car, List<Car>> searchCar = CarDBDaoImpl.getInstance();

    // XML
    private final IDao<Brand, Brand> searchBrand = BrandXMLDaoImpl.getInstance();
    private final IDao<Model, List<Model>> searchModel = ModelXMLDaoImpl.getInstance();
    private final IDao<Car, List<Car>> searchCar = CarXMLDaoImpl.getInstance();

    @Override
    public AbstractCmd execute() {

        Brand brand = new Brand();
        try {
            brand = selectBrand();
        } catch (final NumberFormatException e) {
            System.err.println("Brand id must have integer value");
            return new CmdHome();
        } catch (final NullPointerException e) {
            return new CmdHome();
        }

        Model model = new Model();
        try {
            model = selectModel(brand);
        } catch (UIException | NullPointerException e) {
            return new CmdHome();
        } catch (final NumberFormatException e) {
            System.err.println("Model id must have integer value");
            return new CmdHome();
        }

        System.out.println("your cars:");
        searchCar.find(model.getId()).stream()
                .forEach(car -> System.out.printf("%s[%d]\n", car.getVin().toUpperCase(), car.getId()));

        return new CmdHome();
    }

    private Model selectModel(final Brand brand) {

        final List<Model> models = searchModel.find(brand.getId());
        if (!models.isEmpty()) {
            models.stream().forEach(mdl -> System.out.printf("%s[%d]\n", mdl.getName(), mdl.getId()));

            System.out.println("select model id:");
            final Integer modelId = Integer.valueOf(readInput());

            final Model model = searchModel.get(modelId);
            if (model != null) {
                return model;
            } else {
                System.out.println("Model with id=" + modelId + " not found");
                throw new NullPointerException();
            }
        } else {
            System.out.println("List models is empty");
            throw new UIException();
        }
    }

    private Brand selectBrand() {

        final List<Brand> brands = searchBrand.getAll();

        if (brands != null) {
            brands.stream().forEach(brd -> System.out.printf("%s[%d]\n", brd.getName(), brd.getId()));

            System.out.println("select brand id:");

            final Integer brandId = Integer.valueOf(readInput());

            final Brand brand = searchBrand.find(brandId);
            if (brand != null) {
                return brand;
            } else {
                System.out.println("Brand with id=" + brandId + " not found");
                throw new NullPointerException();
            }
        } else {
            System.out.println("List brands is empty");
            throw new NullPointerException();
        }
    }
}
