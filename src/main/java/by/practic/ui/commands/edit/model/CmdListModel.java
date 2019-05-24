package by.practic.ui.commands.edit.model;

import java.util.List;

import by.practic.datalayer.IDao;
import by.practic.datalayer.entity.Model;
import by.practic.datalayer.xml.ModelXMLDaoImpl;
import by.practic.ui.commands.AbstractCmd;
import by.practic.ui.commands.Command;

@Command(name = "list", description = "list models")
public class CmdListModel extends AbstractCmd {

    // DB
//	private IDao<Model, List<Model>> dao = ModelDBDaoImpl.getInstance();

    // XML
    private final IDao<Model, List<Model>> dao = ModelXMLDaoImpl.getInstance();

    @Override
    public AbstractCmd execute() {

        final List<Model> models = dao.getAll();

        if (models != null) {
            models.stream().forEach(model -> System.out.println(model));
        } else {
            System.out.println("List is empty");
        }

        return new CmdEditModel();
    }
}
