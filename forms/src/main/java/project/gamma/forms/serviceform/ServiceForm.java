package project.gamma.forms.serviceform;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;

import javax.swing.*;
import javax.swing.text.DefaultFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class ServiceForm {
    private JPanel contentPanel;
    private JTextField nameField;
    private JFormattedTextField priceField;
    private JFormattedTextField durationField;
    private JButton saveRecordButton;
    private JButton cancelButton;

    private ServiceFormActionListener actionListener;

    public ServiceForm() {
        saveRecordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (actionListener != null)
                    actionListener.savePressed();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (actionListener != null)
                    actionListener.cancelPressed();
            }
        });
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public void setActionListener(ServiceFormActionListener actionListener) {
        this.actionListener = actionListener;
    }

    PresentationModel<ServiceFormData> dataModel;
    ServiceFormData data;

    public ServiceFormData getData() {
        return data;
    }

    public void setData(ServiceFormData data) {
        this.data = data;
        dataModel.setBean(data);
    }

    private void createUIComponents() {
        data = new ServiceFormData();
        dataModel =  new PresentationModel<ServiceFormData>(data);

        nameField = BasicComponentFactory.createTextField(dataModel.getModel("name"));
        priceField = BasicComponentFactory.createIntegerField(dataModel.getModel("price"));
        durationField = BasicComponentFactory.createIntegerField(dataModel.getModel("duration"));

        DefaultFormatter formatter = (DefaultFormatter) priceField.getFormatter();
        formatter.setCommitsOnValidEdit(true);

        formatter = (DefaultFormatter) durationField.getFormatter();
        formatter.setCommitsOnValidEdit(true);

    }
}
