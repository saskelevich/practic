package by.practic.ui.commands.edit.car;

import by.practic.ui.commands.AbstractCmd;
import by.practic.ui.commands.Command;

@Command(name = "car", description = "edit car")
public class CmdEditCar extends AbstractCmd {

    public CmdEditCar() {
        super(CmdAddCar.class, CmdDeleteCar.class, CmdListCar.class, CmdUpdateCar.class);
    }
}
