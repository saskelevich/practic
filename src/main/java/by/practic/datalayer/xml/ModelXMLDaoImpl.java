package by.practic.datalayer.xml;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import by.practic.datalayer.IDao;
import by.practic.datalayer.entity.Model;
import by.practic.datalayer.xml.table.ModelTable;

public final class ModelXMLDaoImpl extends AbstractXMLDao<ModelTable> implements IDao<Model, List<Model>> {

    private static final String FILE_NAME = "models-table.xml";
    private static IDao<Model, List<Model>> instance;

    private ModelXMLDaoImpl() {
    }

    public static IDao<Model, List<Model>> getInstance() {

        if (instance == null) {
            instance = new ModelXMLDaoImpl();
        }
        return instance;
    }

    @Override
    public Model insert(final Model entity) {

        final ModelTable modelTable = read();
        final Integer id = modelTable.nextId();

        entity.setId(id);
        final Date created = new Date();
        entity.setCreated(created);
        entity.setUpdated(created);
        modelTable.getModels().add(entity);

        write(modelTable);

        return entity;
    }

    @Override
    public void update(final Model entity) {

        final ModelTable table = read();

        table.getModels().stream().forEach(str -> {
            if (str.getId().equals(entity.getId())) {
                str.setName(entity.getName());
                str.setBrandId(entity.getBrandId());
                str.setUpdated(new Date());
            }
        });
        write(table);
    }

    @Override
    public void delete(final Integer id) {

        final ModelTable modelTable = read();
        final Iterator<Model> iterator = modelTable.getModels().iterator();

        while (iterator.hasNext()) {
            final Model model = iterator.next();
            if (model.getId().equals(id)) {
                iterator.remove();
            }
        }
        write(modelTable);
    }

    @Override
    public List<Model> getAll() {

        final ModelTable modelTable = read();
        return modelTable.getModels();
    }

    @Override
    public Model get(final Integer id) {

        for (final Model model : getAll()) {
            if (model.getId().equals(id)) {
                return model;
            }
        }
        return null;
    }

    @Override
    protected String getFileName() {
        return FILE_NAME;
    }

    @Override
    protected Class<ModelTable> getTableClass() {
        return ModelTable.class;
    }

    @Override
    public List<Model> find(final Integer id) {

        final List<Model> models = new ArrayList<>();

        getAll().stream().filter(model -> model.getBrandId().equals(id)).forEach(model -> models.add(model));

        return models;
    }

    @Override
    public List<Model> findByName(final String name) {
        // TODO Auto-generated method stub
        return null;
    }

}
