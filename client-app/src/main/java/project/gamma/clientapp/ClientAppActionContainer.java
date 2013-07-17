package project.gamma.clientapp;

import org.jdesktop.swingx.action.BoundAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.gamma.clientapp.actions.*;

import javax.annotation.PostConstruct;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class ClientAppActionContainer {
    BoundAction newAttendance;
    BoundAction log;
    BoundAction exit;
    BoundAction doctors;
    BoundAction services;
    BoundAction findClient;
    BoundAction newClient;
    BoundAction about;

    ServiceListActionListener serviceListActionListener;
    DoctorListActionListener doctorListActionListener;
    ClientListActionListener clientListActionListener;
    NewClientActionListener newClientActionListener;

    public ClientAppActionContainer() {
        newAttendance = createDummyAction("Новое посещение");
        log = createDummyAction("Журнал");
        exit = createDummyAction("Выход");
        doctors = createDummyAction("Доктора");
        services = createDummyAction("Услуги");
        findClient = createDummyAction("Поиск клиента");
        newClient = createDummyAction("Новый клиент");
        about = createDummyAction("О программе");

        exit.addActionListener(new ExitActionListener());
        about.addActionListener(new AboutActionListener());
    }

    @PostConstruct
    public void init() {
        services.addActionListener(serviceListActionListener);
        doctors.addActionListener(doctorListActionListener);
        findClient.addActionListener(clientListActionListener);
        newClient.addActionListener(newClientActionListener);
    }

    private BoundAction createDummyAction(final String name) {
        BoundAction boundAction = new BoundAction(name);
        boundAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("ClientAppActionContainer.actionPerformed - " + name);
            }
        });
        return boundAction;
    }

    public BoundAction getNewAttendance() {
        return newAttendance;
    }

    public BoundAction getLog() {
        return log;
    }

    public BoundAction getExit() {
        return exit;
    }

    public BoundAction getDoctors() {
        return doctors;
    }

    public BoundAction getServices() {
        return services;
    }

    public BoundAction getFindClient() {
        return findClient;
    }

    public BoundAction getNewClient() {
        return newClient;
    }

    public BoundAction getAbout() {
        return about;
    }

    @Autowired
    public void setServiceListActionListener(ServiceListActionListener serviceListActionListener) {
        this.serviceListActionListener = serviceListActionListener;
    }

    @Autowired
    public void setDoctorListActionListener(DoctorListActionListener doctorListActionListener) {
        this.doctorListActionListener = doctorListActionListener;
    }

    @Autowired
    public void setClientListActionListener(ClientListActionListener clientListActionListener) {
        this.clientListActionListener = clientListActionListener;
    }

    @Autowired
    public void setNewClientActionListener(NewClientActionListener newClientActionListener) {
        this.newClientActionListener = newClientActionListener;
    }
}
