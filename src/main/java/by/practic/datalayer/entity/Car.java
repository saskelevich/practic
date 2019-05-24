package by.practic.datalayer.entity;

import java.util.Date;

public class Car {

    private Integer id;
    private String vin;
    private Integer modelId;
    private Date created;
    private Date updated;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(final String vin) {
        this.vin = vin;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(final Integer modelId) {
        this.modelId = modelId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(final Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(final Date updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "Car [id=" + id + ", vin=" + vin + ", modelId=" + modelId + "]";
    }

}
