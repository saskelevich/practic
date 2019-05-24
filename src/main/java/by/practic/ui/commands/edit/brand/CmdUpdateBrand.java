package by.practic.ui.commands.edit.brand;

import by.practic.datalayer.IDao;
import by.practic.datalayer.entity.Brand;
import by.practic.datalayer.xml.BrandXMLDaoImpl;
import by.practic.ui.commands.AbstractCmd;
import by.practic.ui.commands.Command;

@Command(name = "update", description = "update brand")
public class CmdUpdateBrand extends AbstractCmd {

    // DB
//	private IDao<Brand, Brand> dao = BrandDBDaoImpl.getInstance();

    // XML
    private final IDao<Brand, Brand> dao = BrandXMLDaoImpl.getInstance();

    @Override
    public AbstractCmd execute() {

        System.out.println("input brand id for edit:");
        Integer id = 0;

        try {
            id = Integer.valueOf(readInput());
        } catch (final NumberFormatException e) {
            System.err.println("Brand id must have integer value");
            return new CmdEditBrand();
        }

        final Brand brand = dao.get(id);
        if (brand != null) {

            System.out.println(brand);

            System.out.println("input new brand name:");
            brand.setName(readInput());
            dao.update(brand);

            System.out.println("Brand updated:" + dao.get(id));
            return new CmdEditBrand();
        } else {
            System.out.println("Brand with id=" + id + " not found");
            return new CmdEditBrand();
        }
    }
}