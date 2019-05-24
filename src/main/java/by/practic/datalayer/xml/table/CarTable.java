package by.practic.datalayer.xml.table;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import by.practic.datalayer.entity.Car;

@XmlRootElement
public class CarTable {

    private Integer nextId = 1;
    private List<Car> cars;

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(final List<Car> cars) {
        this.cars = cars;
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
