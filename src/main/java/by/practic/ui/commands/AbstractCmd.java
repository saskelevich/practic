package by.practic.ui.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import by.practic.ui.UIException;

public abstract class AbstractCmd {
    private static final BufferedReader CONSOLE_READER = new BufferedReader(new InputStreamReader(System.in));

    private Class<? extends AbstractCmd>[] subCommands;

    @SafeVarargs
    public AbstractCmd(final Class<? extends AbstractCmd>... cmd) {

        if (this.getClass() != CmdHome.class) {

            @SuppressWarnings("unchecked")
            final Class<? extends AbstractCmd>[] allSubcommands = new Class[cmd.length + 1];
            for (int i = 0; i < cmd.length; i++) {
                allSubcommands[i] = cmd[i];
            }

            allSubcommands[cmd.length] = CmdHome.class; // add default
            subCommands = allSubcommands;

        } else {
            subCommands = cmd;
        }
    }

    /**
     * Contains code to be executed when user selected current command and also
     * returns the next command
     */
    public AbstractCmd execute() {
        final Class<? extends AbstractCmd> selectNextSubCommand = selectNextSubCommand();
        try {
            return selectNextSubCommand.newInstance();
        } catch (InstantiationException | IllegalAccessException exc) {
            throw new UIException(exc);
        }
    }

    protected Class<? extends AbstractCmd> selectNextSubCommand() {
        showSubCommands();
        Class<? extends AbstractCmd> selectedCmd = selectCommand();
        while (selectedCmd == null) {
            System.out.println("неверный ввод, попробуйте еще раз...");
            showSubCommands();
            selectedCmd = selectCommand();
        }
        return selectedCmd;
    }

    protected String readInput() {
        try {
            return CONSOLE_READER.readLine();
        } catch (final IOException exc) {
            throw new UIException(exc);
        }
    }

    private String getDescription(final Class<? extends AbstractCmd> clazz) {
        return getMetadata(clazz).description();
    }

    private String getCommandName(final Class<? extends AbstractCmd> clazz) {
        return getMetadata(clazz).name();
    }

    private Command getMetadata(final Class<? extends AbstractCmd> clazz) {
        final Command annotation = clazz.getAnnotation(Command.class);
        if (annotation == null) {
            throw new UIException("Class should be annotated with metadata. Class:" + clazz,
                    new IllegalArgumentException());
        }
        return annotation;
    }

    private void showSubCommands() {
        if (subCommands.length != 0) {
            System.out.println("-----------выберите действие-----------");
            for (final Class<? extends AbstractCmd> cmdClass : subCommands) {
                System.out.printf("%s - %s\n", getCommandName(cmdClass), getDescription(cmdClass));
            }
        } else {
            System.out.println("нет подкоманд для показа");
            System.exit(1);
        }
    }

    private Class<? extends AbstractCmd> selectCommand() {
        final String cmdName = readInput();

        for (final Class<? extends AbstractCmd> cmd : subCommands) {
            if (getCommandName(cmd).equalsIgnoreCase(cmdName)) {
                return cmd;
            }
        }
        return null;
    }

}
