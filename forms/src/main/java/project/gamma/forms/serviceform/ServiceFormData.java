package project.gamma.forms.serviceform;

import com.jgoodies.binding.beans.Model;

public class ServiceFormData extends Model {
    private String name;
    private int price;
    private int duration;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldValue = this.name;
        this.name = name;
        firePropertyChange("name", oldValue, name);
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        int oldValue = this.price;
        this.price = price;
        firePropertyChange("price", oldValue, price);
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        int oldValue = this.duration;
        this.duration = duration;
        firePropertyChange("duration", oldValue, duration);
    }
}
