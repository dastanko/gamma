package project.gamma.data;

import javax.persistence.*;
import java.util.Date;

/**
 * User: sergey
 * Date: 04.02.11
 */
@Entity
public class LogRecord implements WithId {
    private int id;
    private Date visitTime;
    private Doctor doctor;
    private Client client;

    AbstractService service;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    @ManyToOne
    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @ManyToOne
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Embedded
    public AbstractService getService() {
        return service;
    }

    public void setService(AbstractService service) {
        this.service = service;
    }
}
