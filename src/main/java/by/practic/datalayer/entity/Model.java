package by.practic.datalayer.entity;

import java.util.Date;

public class Model {

    private Integer id;
    private String name;
    private Integer brandId;
    private Date created;
    private Date updated;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(final Integer brandId) {
        this.brandId = brandId;
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
        return "Model [id=" + id + ", name=" + name + ", brandId=" + brandId + "]";
    }

}
