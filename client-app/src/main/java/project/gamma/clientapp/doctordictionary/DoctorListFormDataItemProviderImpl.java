package project.gamma.clientapp.doctordictionary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.gamma.MainFacade;
import project.gamma.data.Doctor;
import project.gamma.forms.doctorlist.DoctorListFormDataItem;
import project.gamma.forms.doctorlist.DoctorListFormDataItemProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Администратор
 * Date: 03.03.11
 * Time: 21:18
 * To change this template use File | Settings | File Templates.
 */
@Component
public class DoctorListFormDataItemProviderImpl implements DoctorListFormDataItemProvider {
        MainFacade mainFacade;

    public List<DoctorListFormDataItem> getAll() {
        ArrayList<DoctorListFormDataItem> items = new ArrayList<DoctorListFormDataItem>();
        List<Doctor> doctors = mainFacade.doctor().getAll();

        for (Doctor doctor : doctors) {
            DoctorListFormDataItem item = new DoctorListFormDataItem();


            item.setFio(doctor.getName());
            item.setTitle(doctor.getTitle());
            item.setSpeciality(doctor.getSpeciality());
            item.setWorkinghours(doctor.getSchedule());
            item.setTag(doctor);
            items.add(item);
        }

        return items;
    }

    @Autowired
    public void setMainFacade(MainFacade mainFacade) {
        this.mainFacade = mainFacade;
    }
}
