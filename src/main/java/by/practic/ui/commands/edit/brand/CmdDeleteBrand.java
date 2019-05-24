package by.practic.ui.commands.edit.brand;

import by.practic.datalayer.IDao;
import by.practic.datalayer.entity.Brand;
import by.practic.datalayer.xml.BrandXMLDaoImpl;
import by.practic.ui.commands.AbstractCmd;
import by.practic.ui.commands.Command;

@Command(name = "delete", description = "delete brand")
public class CmdDeleteBrand extends AbstractCmd {

    // DB
//	private IDao<Brand, Brand> dao = BrandDBDaoImpl.getInstance();

    // XML
    private final IDao<Brand, Brand> dao = BrandXMLDaoImpl.getInstance();

    @Override
    public AbstractCmd execute() {

        System.out.println("input brand id for deleting");

        Integer id = 0;

        try {
            id = Integer.valueOf(readInput());
        } catch (final NumberFormatException e) {
            System.err.println("Brand id must have integer value");
            return new CmdEditBrand();
        }

        dao.delete(id);

        System.out.println("brand deleted");
        return new CmdEditBrand();
    }
}
