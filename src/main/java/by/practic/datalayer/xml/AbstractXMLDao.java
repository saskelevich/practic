package by.practic.datalayer.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import by.practic.datalayer.DAOException;

public abstract class AbstractXMLDao<T> {
    private static final String ROOT_FOLDER = "./xmldb";

    abstract String getFileName();

    abstract Class<T> getTableClass();

    static {

        final File root = new File(ROOT_FOLDER);
        if (!root.exists()) {
            root.mkdirs();
        }
    }

    protected AbstractXMLDao() {

        super();
        final File file = getFile();

        if (!file.exists()) {
            try {
                file.createNewFile();
                write(getTableClass().newInstance());
            } catch (IOException | InstantiationException | IllegalAccessException exc) {
                throw new DAOException("Constructor AbstractXMLDao", exc);
            }
        }
    }

    protected File getFile() {
        return new File(ROOT_FOLDER + "/" + getFileName());
    }

    @SuppressWarnings("unchecked")
    protected T read() {

        final File file = getFile();

        try {
            final JAXBContext jaxbContext = JAXBContext.newInstance(getTableClass());
            final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            return (T) jaxbUnmarshaller.unmarshal(file);
        } catch (final JAXBException e) {
            throw new DAOException("Cannot read", e);
        }
    }

    protected void write(final T table) {

        JAXBContext jaxbContext;

        try (FileOutputStream os = new FileOutputStream(getFile());) {

            jaxbContext = JAXBContext.newInstance(getTableClass());
            final Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(table, os);

        } catch (JAXBException | IOException e) {
            throw new DAOException("Cannot write", e);
        }
    }
}
