package project.gamma.data;

import javax.persistence.Embeddable;

/**
 * User: sergey
 * Date: 04.02.11
 */
@Embeddable
public class AbstractService {
    private String name;
    private int price;
    private int duration;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

}
