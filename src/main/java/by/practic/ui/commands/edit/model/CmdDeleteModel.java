package by.practic.ui.commands.edit.model;

import java.util.List;

import by.practic.datalayer.IDao;
import by.practic.datalayer.entity.Model;
import by.practic.datalayer.xml.ModelXMLDaoImpl;
import by.practic.ui.commands.AbstractCmd;
import by.practic.ui.commands.Command;

@Command(name = "delete", description = "delete model")
public class CmdDeleteModel extends AbstractCmd {

    // DB
//	private IDao<Model, List<Model>> dao = ModelDBDaoImpl.getInstance();

    // XML
    private final IDao<Model, List<Model>> dao = ModelXMLDaoImpl.getInstance();

    @Override
    public AbstractCmd execute() {

        System.out.println("input model id for deleting");

        Integer id = 0;
        try {
            id = Integer.valueOf(readInput());
        } catch (final NumberFormatException e) {
            System.err.println("Model id must have integer value");
            return new CmdEditModel();
        }

        dao.delete(id);

        System.out.println("model deleted");
        return new CmdEditModel();
    }
}
