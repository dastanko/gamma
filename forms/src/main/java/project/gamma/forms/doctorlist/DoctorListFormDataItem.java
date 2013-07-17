package project.gamma.forms.doctorlist;

import com.jgoodies.binding.beans.Model;

/**
 * Created by IntelliJ IDEA.
 * User: Администратор
 * Date: 03.03.11
 * Time: 20:25
 * To change this template use File | Settings | File Templates.
 */
public class DoctorListFormDataItem extends Model {
    private String fio;
    private String title;
    private String speciality;
    private String workinghours;

       private Object tag;

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
                String oldValue = this.fio;
        this.fio = fio;
        firePropertyChange("fio",oldValue,fio);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
              String oldValue = this.title;
        this.title = title;
        firePropertyChange("title",oldValue,title);
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
               String oldValue = this.speciality;
        this.speciality = speciality;
        firePropertyChange("speciality",oldValue,speciality);
    }

    public String getWorkinghours() {
        return workinghours;
    }

    public void setWorkinghours(String workinghours) {
               String oldValue = this.workinghours;
        this.workinghours = workinghours;
        firePropertyChange("workinghours",oldValue,workinghours);
    }

}
