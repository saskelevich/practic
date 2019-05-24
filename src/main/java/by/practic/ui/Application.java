package by.practic.ui;

import by.practic.ui.commands.AbstractCmd;
import by.practic.ui.commands.CmdHome;

public class Application {

    public static void main(final String[] args) {
        System.out.println("Start Application");

        AbstractCmd nextCommand = new CmdHome();
        while (nextCommand != null) {
            nextCommand = nextCommand.execute();
        }
        System.out.println("Exit");
        System.exit(0);
    }
}
