package project.gamma.forms.doctorform;

import com.jgoodies.binding.beans.Model;

public class DoctorFormData extends Model {
    private String name;
    private String title;
    private String speciality;
    private String workingHours;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldValue = this.name;
        this.name = name;
        firePropertyChange("name", oldValue, name);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        String oldValue = this.title;
        this.title = title;
        firePropertyChange("title", oldValue, title);
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        String oldValue = this.speciality;
        this.speciality = speciality;
        firePropertyChange("speciality", oldValue, speciality);
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        String oldValue = this.workingHours;
        this.workingHours = workingHours;
        firePropertyChange("workingHours", oldValue, workingHours);
    }

    @Override
    public String toString() {
        return "DoctorFormData{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", speciality='" + speciality + '\'' +
                ", workingHours='" + workingHours + '\'' +
                '}';
    }
}
