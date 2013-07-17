package project.gamma.clientapp.actions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.gamma.MainFacade;
import project.gamma.clientapp.ClientAppScreenManager;
import project.gamma.forms.doctorlist.DoctorListForm;

import javax.annotation.PostConstruct;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IntelliJ IDEA.
 * User: Администратор
 * Date: 03.03.11
 * Time: 21:10
 * To change this template use File | Settings | File Templates.
 */
@Component
public class DoctorListActionListener implements ActionListener{
    private static final String DOCTOR_LIST = "doctorList";

      ClientAppScreenManager screenManager;
      DoctorListForm doctorListForm;
      MainFacade mainFacade;
      public void actionPerformed(ActionEvent e)
      {
          doctorListForm.refresh();

      screenManager.show(DOCTOR_LIST);
      }
       @PostConstruct
      public void init(){
          screenManager.register(DOCTOR_LIST, doctorListForm.getContentPanel());
      }
       @Autowired
      public void setScreenManager(ClientAppScreenManager screenManager) {
          this.screenManager = screenManager;
      }
         @Autowired
    public void setDoctorListForm(DoctorListForm doctorListForm) {
        this.doctorListForm = doctorListForm;
    }

             @Autowired
      public void setMainFacade(MainFacade mainFacade) {
          this.mainFacade = mainFacade;
      }
  }
