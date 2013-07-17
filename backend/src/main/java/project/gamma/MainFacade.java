package project.gamma;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import project.gamma.dao.DAO;
import project.gamma.data.Client;
import project.gamma.data.Doctor;
import project.gamma.data.LogRecord;
import project.gamma.data.Service;

/**
 * User: sergey
 * Date: 04.02.11
 */
public class MainFacade {
    private static MainFacade instance;

    private DAO<Client> clientDAO;
    private DAO<Service> serviceDAO;
    private DAO<Doctor> doctorDAO;
    private DAO<LogRecord> logRecordDAO;

    private MainFacade() {
    }

    public static synchronized void init() {
        if (instance == null) {
            if (System.getProperty("unit.name") == null)
                System.setProperty("unit.name", "production");
            ApplicationContext context = new FileSystemXmlApplicationContext("classpath:/mainfacade-context.xml");
            instance = context.getBean(MainFacade.class);
        }
    }

    public static MainFacade getInstance() {
        if (instance == null)
            throw new IllegalStateException("MainFacade ещё не инициализирован.");
        return instance;
    }

    @Required
    public void setClientDAO(DAO<Client> clientDAO) {
        this.clientDAO = clientDAO;
    }

    @Required
    public void setServiceDAO(DAO<Service> serviceDAO) {
        this.serviceDAO = serviceDAO;
    }

    @Required
    public void setDoctorDAO(DAO<Doctor> doctorDAO) {
        this.doctorDAO = doctorDAO;
    }

    @Required
    public void setLogRecordDAO(DAO<LogRecord> logRecordDAO) {
        this.logRecordDAO = logRecordDAO;
    }

    public DAO<Client> client() {
        return clientDAO;
    }

    public DAO<Service> service() {
        return serviceDAO;
    }

    public DAO<Doctor> doctor() {
        return doctorDAO;
    }

    public DAO<LogRecord> logRecord() {
        return logRecordDAO;
    }

    public static void setTestMode() {
        System.setProperty("unit.name", "test");
    }
}
