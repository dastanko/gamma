package project.gamma.forms.clientform;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.adapter.SpinnerAdapterFactory;
import com.jgoodies.validation.view.ValidationResultViewFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;

public class ClientForm {
    private JPanel contentPanel;
    private JTextField nameField;
    private JSpinner yearField;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JTextArea addressField;
    private JTextArea errorArea;
    private JButton saveButton;
    private JButton cancelButton;

    ClientFormActionListener actionListener;

    public ClientForm() {
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (actionListener != null)
                    actionListener.save();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (actionListener != null)
                    actionListener.cancel();
            }
        });
    }

    ClientFormData data;
    ClientFormDataPresentationModel dataModel;

    private void createUIComponents() throws ParseException {
        data = new ClientFormData();
        dataModel = new ClientFormDataPresentationModel(data);

        nameField = BasicComponentFactory.createTextField(dataModel.getModel("name"), false);

        SpinnerNumberModel yearModel = SpinnerAdapterFactory.
                createNumberAdapter(dataModel.getModel("year"), 1950, 1850, 2011, 1);
        yearField = new JSpinner(yearModel);

        JSpinner.NumberEditor editor = (JSpinner.NumberEditor) yearField.getEditor();
        editor.getFormat().setGroupingUsed(false);

        maleRadioButton = BasicComponentFactory.
                createRadioButton(dataModel.getModel("gender"), "М", "М");

        femaleRadioButton = BasicComponentFactory.
                createRadioButton(dataModel.getModel("gender"), "Ж", "Ж");

        addressField = BasicComponentFactory.createTextArea(dataModel.getModel("address") ,false);

        errorArea = ValidationResultViewFactory.createReportTextArea(dataModel.getValidationResultModel());

        dataModel.getValidationResultModel().addPropertyChangeListener("result", new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                saveButton.setEnabled(!dataModel.getValidationResultModel().getResult().hasMessages());
            }
        });

    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public void setActionListener(ClientFormActionListener actionListener) {
        this.actionListener = actionListener;
    }

    public ClientFormData getData() {
        return data;
    }

    public void setData(ClientFormData data) {
        this.data = data;
        dataModel.setBean(data);
    }

}
