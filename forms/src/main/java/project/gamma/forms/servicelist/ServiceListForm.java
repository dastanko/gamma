package project.gamma.forms.servicelist;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.swing.EventJXTableModel;
import org.jdesktop.swingx.JXTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ServiceListForm {
    private JPanel contentPanel;
    private JTable serviceTable;
    private BasicEventList<ServiceListFormDataItem> eventList;
    private ServiceListFormDataItemProvider provider;
    private ServiceListFormActionListener actionListener;

    private ServiceListFormDataItem popupElement;

    JPopupMenu popupMenu = new JPopupMenu();
    private JMenuItem editItem;
    private JMenuItem newServiceItem;
    private JMenuItem deleteItem;

    public ServiceListForm() {
        editItem = new JMenuItem("Редактировать");
        newServiceItem = new JMenuItem("Новая услуга");
        deleteItem = new JMenuItem("Удалить");

        popupMenu.add(editItem);
//        popupMenu.add(new JSeparator());
        popupMenu.add(newServiceItem);
//        popupMenu.add(new JSeparator());
        popupMenu.add(deleteItem);

        serviceTable.addMouseListener(new MyMouseListener());

        editItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (actionListener != null)
                    actionListener.edit(popupElement);
            }
        });

        newServiceItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (actionListener != null)
                    actionListener.newItem();
            }
        });

        deleteItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (actionListener != null)
                    actionListener.delete(popupElement);
            }
        });
    }

    private void createUIComponents() {
        serviceTable = new JXTable();
        eventList = new BasicEventList<ServiceListFormDataItem>();

        EventJXTableModel<ServiceListFormDataItem> model =
                new EventJXTableModel<ServiceListFormDataItem>(
                        eventList,
                        new String[]{"name", "price", "duration"},
                        new String[]{"Название услуги", "Стоимость", "Обычная длительность"},
                        new boolean[]{false, false, false}
                );
        serviceTable.setModel(model);
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public void refresh() {
        eventList.clear();
        if (provider != null)
            eventList.addAll(provider.getAll());
    }

    public void setProvider(ServiceListFormDataItemProvider provider) {
        this.provider = provider;
    }

    private class MyMouseListener implements MouseListener {
        public void mouseClicked(MouseEvent e) {
        }

        public void mousePressed(MouseEvent e) {
            processEvent(e);
        }

        private void processEvent(MouseEvent e) {
            if (e.isPopupTrigger()) {
                int row = serviceTable.rowAtPoint(e.getPoint());
                if (row != -1) {
                    popupElement = eventList.get(row);
                } else
                    popupElement = null;

                editItem.setEnabled(popupElement != null);
                deleteItem.setEnabled(popupElement != null);

                popupMenu.setInvoker(serviceTable);
                popupMenu.setLocation(e.getXOnScreen(), e.getYOnScreen());
                popupMenu.setVisible(true);

//                popupMenu.show(serviceTable, e.getX(), e.getY());
            }
        }

        public void mouseReleased(MouseEvent e) {
            processEvent(e);
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }
    }

    public void setActionListener(ServiceListFormActionListener actionListener) {
        this.actionListener = actionListener;
    }
}
