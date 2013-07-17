package project.gamma.forms.clientlistform;

import com.jgoodies.binding.beans.Model;

public class ClientListFormDataItem extends Model {
    private String name, address = "Адрес: __________\nТелефон: __________";
    private String gender = "М";
    private int year = 1950;
    private Object tag;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldValue = this.name;
        this.name = name;
        firePropertyChange("name", oldValue, name);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        String oldValue = this.address;
        this.address = address;
        firePropertyChange("address", oldValue, address);
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        String oldValue = this.gender;
        this.gender = gender;
        firePropertyChange("gender", oldValue, gender);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        int oldValue = this.year;
        this.year = year;
        firePropertyChange("year", oldValue, year);
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "ClientFormData{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                ", year=" + year +
                '}';
    }

}
