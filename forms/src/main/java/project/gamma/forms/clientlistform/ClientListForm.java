package project.gamma.forms.clientlistform;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.swing.EventJXTableModel;
import org.jdesktop.swingx.JXTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClientListForm {
    private JPanel contentPanel;
    private JTable clientListTable;

    private ClientListFormDataItemProvider provider;
    private BasicEventList<ClientListFormDataItem> eventList;

    JPopupMenu popupMenu = new JPopupMenu();
    ClientListFormDataItem popupElement;
    private JMenuItem editMenuItem;
    private JMenuItem newMenuItem;
    private JMenuItem deleteMenuItem;

    private ClientListFormActionListener actionListener;

    public ClientListForm() {
        editMenuItem = new JMenuItem("Редактировать");
        newMenuItem = new JMenuItem("Новый клиент");
        deleteMenuItem = new JMenuItem("Удалить");

        editMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (actionListener != null)
                    actionListener.edit(popupElement);
            }
        });

        newMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (actionListener != null)
                    actionListener.create();
            }
        });

        deleteMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (actionListener != null)
                    actionListener.delete(popupElement);
            }
        });

        popupMenu.add(editMenuItem);
        popupMenu.add(new JSeparator(SwingConstants.HORIZONTAL));
        popupMenu.add(newMenuItem);
        popupMenu.add(new JSeparator(SwingConstants.HORIZONTAL));
        popupMenu.add(deleteMenuItem);

        clientListTable.addMouseListener(new PopupMenuListener());
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public void setProvider(ClientListFormDataItemProvider provider) {
        this.provider = provider;
    }

    private void createUIComponents() {
        clientListTable = new JXTable();
        eventList = new BasicEventList<ClientListFormDataItem>();
        EventJXTableModel<ClientListFormDataItem> tableModel = new EventJXTableModel<ClientListFormDataItem>(
                eventList,
                new String[]{"name", "year", "gender", "address"},
                new String[]{"ФИО", "Год рождения", "Пол", "Адрес"},
                new boolean[]{false, false, false, false}
        );
        clientListTable.setModel(tableModel);
    }

    public void refresh() {
        eventList.clear();
        if (provider != null)
            eventList.addAll(provider.getAll());
    }

    private class PopupMenuListener extends MouseAdapter {
        private void processEvent(MouseEvent e) {
            if (e.isPopupTrigger()) {
                int row = clientListTable.rowAtPoint(e.getPoint());
                if (row != -1) {
                    popupElement = eventList.get(row);
                } else
                    popupElement = null;

                editMenuItem.setEnabled(popupElement != null);
                deleteMenuItem.setEnabled(popupElement != null);

                popupMenu.setInvoker(clientListTable);
                popupMenu.setLocation(e.getXOnScreen(), e.getYOnScreen());
                popupMenu.setVisible(true);
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            processEvent(e);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            processEvent(e);
        }
    }

    public void setActionListener(ClientListFormActionListener actionListener) {
        this.actionListener = actionListener;
    }
}
