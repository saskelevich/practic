package by.practic.datalayer.xml.table;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import by.practic.datalayer.entity.Brand;

@XmlRootElement
public class BrandTable {

    private Integer nextId = 1;
    private List<Brand> brands;

    public List<Brand> getBrands() {
        return brands;
    }

    public void setBrands(final List<Brand> brands) {
        this.brands = brands;
    }

    public Integer getNextId() {
        return nextId;
    }

    public void setNextId(final Integer nextId) {
        this.nextId = nextId;
    }

    public Integer nextId() {
        nextId++;
        return nextId;
    }
}