package by.practic.ui.commands.edit;

import by.practic.ui.commands.AbstractCmd;
import by.practic.ui.commands.Command;
import by.practic.ui.commands.edit.brand.CmdEditBrand;
import by.practic.ui.commands.edit.car.CmdEditCar;
import by.practic.ui.commands.edit.model.CmdEditModel;

@Command(name = "edit", description = "edit data base")
public class CmdEditDB extends AbstractCmd {

    public CmdEditDB() {
        super(CmdEditBrand.class, CmdEditModel.class, CmdEditCar.class);
    }
}