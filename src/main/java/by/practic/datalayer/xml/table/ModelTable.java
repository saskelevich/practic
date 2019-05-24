package by.practic.datalayer.xml.table;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import by.practic.datalayer.entity.Model;

@XmlRootElement
public class ModelTable {

    private Integer nextId = 1;
    private List<Model> models;

    public Integer getNextId() {
        return nextId;
    }

    public void setNextId(final Integer nextId) {
        this.nextId = nextId;
    }

    public List<Model> getModels() {
        return models;
    }

    public void setModels(final List<Model> models) {
        this.models = models;
    }

    public Integer nextId() {
        nextId++;
        return nextId;
    }

}
