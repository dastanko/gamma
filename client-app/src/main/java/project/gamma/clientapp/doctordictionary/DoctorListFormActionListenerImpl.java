package project.gamma.clientapp.doctordictionary;

import org.springframework.beans.factory.annotation.Autowired;
import project.gamma.MainFacade;
import project.gamma.data.Doctor;
import project.gamma.forms.doctorform.DoctorForm;
import project.gamma.forms.doctorform.DoctorFormActionListener;
import project.gamma.forms.doctorform.DoctorFormData;
import project.gamma.forms.doctorlist.DoctorListForm;
import project.gamma.forms.doctorlist.DoctorListFormActionListener;
import project.gamma.forms.doctorlist.DoctorListFormDataItem;

import javax.swing.*;
import java.awt.*;

@org.springframework.stereotype.Component
public class DoctorListFormActionListenerImpl implements DoctorListFormActionListener {

    DoctorForm doctorForm;
    MainFacade mainFacade;
    DoctorListForm doctorListForm;


    public void edit(DoctorListFormDataItem item) {

        final JDialog dialog = new JDialog(null, Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setTitle("Редактирование врача");
        dialog.add(doctorForm.getContentPanel());

        final Doctor doctor = (Doctor) item.getTag();

        DoctorFormData formData = new DoctorFormData();
        formData.setName(doctor.getName());
        formData.setTitle(doctor.getTitle());
        formData.setSpeciality(doctor.getSpeciality());
        formData.setWorkingHours(doctor.getSchedule());
        doctorForm.setData(formData);


        doctorForm.setActionListener(new DoctorFormActionListener() {
            public void savePressed() {
                DoctorFormData doctorFormData = doctorForm.getData();
                doctor.setName(doctorFormData.getName());
                doctor.setTitle(doctorFormData.getTitle());
                doctor.setSpeciality(doctorFormData.getSpeciality());
                doctor.setSchedule(doctorFormData.getWorkingHours());
                mainFacade.doctor().update(doctor);
                dialog.setVisible(false);
                doctorListForm.refresh();
            }

            public void cancelPressed() {
                dialog.setVisible(false);
            }
        });
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    public void newItem() {
        final JDialog dialog = new JDialog(null, Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setTitle("Новый врач");
        dialog.add(doctorForm.getContentPanel());
        doctorForm.setActionListener(new MyServiceFormActionListener(dialog));
        doctorForm.setData(new DoctorFormData());
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }


    @Autowired
    public void setDoctorForm(DoctorForm doctorForm) {
        this.doctorForm = doctorForm;
    }

    @Autowired
    public void setDoctorListForm(DoctorListForm doctorListForm) {
        this.doctorListForm = doctorListForm;
        doctorListForm.setActionListener(this);
    }

    @Autowired
    public void setMainFacade(MainFacade mainFacade) {
        this.mainFacade = mainFacade;
    }

    public void deleteItem(DoctorListFormDataItem item) {
        mainFacade.doctor().delete((Doctor) item.getTag());
        doctorListForm.refresh();
    }

    private class MyServiceFormActionListener implements DoctorFormActionListener {
        private final JDialog dialog;

        public MyServiceFormActionListener(JDialog dialog) {
            this.dialog = dialog;
        }

        public void savePressed() {
            DoctorFormData formData = doctorForm.getData();
            Doctor doctor = new Doctor();

            doctor.setName(formData.getName());
            doctor.setTitle(formData.getTitle());
            doctor.setSpeciality(formData.getSpeciality());
            doctor.setSchedule(formData.getWorkingHours());

            mainFacade.doctor().create(doctor);
            dialog.setVisible(false);
            doctorListForm.refresh();
        }

        public void cancelPressed() {
            dialog.setVisible(false);
        }
    }


}
