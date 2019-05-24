package by.practic.ui.commands.edit.model;

import java.util.List;

import by.practic.datalayer.IDao;
import by.practic.datalayer.entity.Model;
import by.practic.datalayer.xml.ModelXMLDaoImpl;
import by.practic.ui.commands.AbstractCmd;
import by.practic.ui.commands.Command;
import by.practic.ui.commands.edit.brand.CmdListBrand;

@Command(name = "add", description = "add model")
public class CmdAddModel extends AbstractCmd {

    // DB
//	private IDao<Model, List<Model>> dao = ModelDBDaoImpl.getInstance();

    // XML
    private final IDao<Model, List<Model>> dao = ModelXMLDaoImpl.getInstance();

    @Override
    public AbstractCmd execute() {

        System.out.println("input new model name");
        final String newModelName = readInput();

        final Model model = new Model();
        model.setName(newModelName);

        new CmdListBrand();
        System.out.println("input brand id:");

        Integer id = 0;
        try {
            id = Integer.valueOf(readInput());
        } catch (final NumberFormatException e) {
            System.err.println("Model id must have integer value");
            return new CmdEditModel();
        }

        model.setBrandId(id);

        final Model newModel = dao.insert(model);

        System.out.println("New model saved:" + newModel);

        return new CmdEditModel();
    }
}
