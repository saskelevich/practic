package by.practic.ui.commands.edit.brand;

import by.practic.ui.commands.AbstractCmd;
import by.practic.ui.commands.Command;

@Command(name = "brand", description = "edit brand")
public class CmdEditBrand extends AbstractCmd {

    public CmdEditBrand() {
        super(CmdAddBrand.class, CmdDeleteBrand.class, CmdListBrand.class, CmdUpdateBrand.class);
    }

}