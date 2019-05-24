package by.practic.datalayer;

public class DAOException extends RuntimeException {

    private static final long serialVersionUID = -6471520950889878784L;

    public DAOException() {
    }

    public DAOException(final String string) {
        super(string);
    }

    public DAOException(final String string, final Throwable cause) {
        super(string, cause);
    }

    public DAOException(final Throwable cause) {
        super(cause);
    }
}
