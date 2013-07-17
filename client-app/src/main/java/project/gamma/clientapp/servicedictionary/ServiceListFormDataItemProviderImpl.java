package project.gamma.clientapp.servicedictionary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.gamma.MainFacade;
import project.gamma.data.Service;
import project.gamma.forms.servicelist.ServiceListFormDataItem;
import project.gamma.forms.servicelist.ServiceListFormDataItemProvider;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceListFormDataItemProviderImpl implements ServiceListFormDataItemProvider {
    MainFacade mainFacade;

    public List<ServiceListFormDataItem> getAll() {
        ArrayList<ServiceListFormDataItem> items = new ArrayList<ServiceListFormDataItem>();
        List<Service> services = mainFacade.service().getAll();

        for (Service service : services) {
            ServiceListFormDataItem item = new ServiceListFormDataItem();

            int hours = service.getDuration() / 60;
            int mins = service.getDuration() % 60;

            item.setName(service.getName());
            item.setPrice(service.getPrice());
            item.setDuration(String.format("%02d:%02d", hours, mins));

            item.setTag(service);
            items.add(item);
        }

        return items;
    }

    @Autowired
    public void setMainFacade(MainFacade mainFacade) {
        this.mainFacade = mainFacade;
    }
}
