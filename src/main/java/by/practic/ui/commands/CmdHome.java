package by.practic.ui.commands;

import by.practic.ui.commands.edit.CmdEditDB;

@Command(name = "home", description = "return to main menu")
public class CmdHome extends AbstractCmd {

    public CmdHome() {
        super(CmdEditDB.class, CmdSearch.class, CmdExit.class);
    }
}