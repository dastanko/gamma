package project.gamma;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import project.gamma.data.*;

import java.util.Date;
import java.util.List;

/**
 * User: sergey
 * Date: 04.02.11
 */
public class MainFacadeTest {
    private static MainFacade mainFacade;

    @BeforeClass
    public static void init() {
        MainFacade.setTestMode();
        MainFacade.init();
        mainFacade = MainFacade.getInstance();
    }

    @Test
    public void crudForClient() {
        String name = "client #1";
        String address = "address for client #1, phone: +996(xxx)yyy-zzz";
        Gender gender = Gender.male;
        int year = 1980;

        Client client = new Client();
        client.setName(name);
        client.setAddress(address);
        client.setGender(gender);
        client.setYear(year);

        mainFacade.client().create(client);

        List<Client> clients = mainFacade.client().getAll();
        Assert.assertEquals(1, clients.size());
        Client readClient = clients.get(0);

        Assert.assertEquals(name, readClient.getName());
        Assert.assertEquals(address, readClient.getAddress());
        Assert.assertEquals(gender, readClient.getGender());
        Assert.assertEquals(year, readClient.getYear());

// обновление записи
        String otherName = "other name";
        readClient.setName(otherName);
        mainFacade.client().update(readClient);

        clients = mainFacade.client().getAll();
        Assert.assertEquals(1, clients.size());
        readClient = clients.get(0);

        Assert.assertEquals(otherName, readClient.getName());
        Assert.assertEquals(address, readClient.getAddress());
        Assert.assertEquals(gender, readClient.getGender());
        Assert.assertEquals(year, readClient.getYear());

        mainFacade.client().delete(client);
        Assert.assertEquals(0, mainFacade.client().getAll().size());

    }

    @Test
    public void createClientServiceDoctorAndLog() throws InterruptedException {
        Client client = new Client();
        client.setName("Just client");
        mainFacade.client().create(client);

        Doctor doctor = new Doctor();
        doctor.setName("Just doctor");
        mainFacade.doctor().create(doctor);

        Service service = new Service();
        service.setName("Just service");
        mainFacade.service().create(service);

        LogRecord record = new LogRecord();
        record.setClient(client);
        record.setDoctor(doctor);
        record.setService(service);
        record.setVisitTime(new Date());
        mainFacade.logRecord().create(record);
    }

    @AfterClass
    public static void tearDown() {

    }
}
