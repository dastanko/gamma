package project.gamma.forms.clientform;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.validation.ValidationResultModel;
import com.jgoodies.validation.util.DefaultValidationResultModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ClientFormDataPresentationModel extends PresentationModel<ClientFormData> {

    ClientFormDataValidator validator = new ClientFormDataValidator();
    ValidationResultModel validationResultModel = new DefaultValidationResultModel();

    public ClientFormDataPresentationModel(ClientFormData bean) {
        super(bean);
        addPropertyChangeListener("bean", new MyPropertyChangeListener());
        addBeanPropertyChangeListener(new MyPropertyChangeListener());
    }

    private class MyPropertyChangeListener implements PropertyChangeListener {
        public void propertyChange(PropertyChangeEvent evt) {
            validationResultModel.setResult(validator.validate(getBean()));
        }
    }

    public ValidationResultModel getValidationResultModel() {
        return validationResultModel;
    }
}
