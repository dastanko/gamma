package project.gamma.forms.servicelist;

import com.jgoodies.binding.beans.Model;

public class ServiceListFormDataItem extends Model {
    private String name;
    private int price;
    private String duration;
    private Object tag;

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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        String oldValue = this.duration;
        this.duration = duration;
        firePropertyChange("duration", oldValue, duration);
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }
}
