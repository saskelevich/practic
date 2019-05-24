package by.practic.ui.commands.edit.model;

import by.practic.ui.commands.AbstractCmd;
import by.practic.ui.commands.Command;

@Command(name = "model", description = "edit model")
public class CmdEditModel extends AbstractCmd {

    public CmdEditModel() {
        super(CmdAddModel.class, CmdDeleteModel.class, CmdListModel.class, CmdUpdateModel.class);
    }
}
