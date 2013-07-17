package project.gamma.forms.doctorlist;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.swing.EventJXTableModel;
import org.jdesktop.swingx.JXTable;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by IntelliJ IDEA.
 * User: Администратор
 * Date: 03.03.11
 * Time: 20:17
 * To change this template use File | Settings | File Templates.
 */
public class DoctorListForm {
    private JPanel contentPanel;
    private JTable doctorTable;

    private BasicEventList<DoctorListFormDataItem> eventList;

    private DoctorListFormDataItemProvider provider;
    private DoctorListFormActionListener actionListener;
    private JMenuItem editItem;
    private JMenuItem newDoctorItem;
    private JMenuItem deleteItem;

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public void setActionListener(DoctorListFormActionListener actionListener) {
        this.actionListener = actionListener;
    }

    public void setProvider(DoctorListFormDataItemProvider provider) {
        this.provider = provider;
    }

    private DoctorListFormDataItem popupElement;

    JPopupMenu popupMenu = new JPopupMenu();



    public DoctorListForm() {
        editItem = new JMenuItem("Редактировать");
        newDoctorItem = new JMenuItem("Новый врач");
        deleteItem = new JMenuItem("Удалить");

        popupMenu.add(editItem);
        //popupMenu.add(new JSeparator());
        popupMenu.add(newDoctorItem);
       // popupMenu.add(new JSeparator());
        popupMenu.add(deleteItem);

        doctorTable.addMouseListener(new MyMouseListener());

        editItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (actionListener!=null)
                    actionListener.edit(popupElement);
            }
        });



        newDoctorItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                         if (actionListener!=null)
                    actionListener.newItem();

            }
        });

         deleteItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                         if (actionListener!=null)
                    actionListener.deleteItem(popupElement);

            }
        });

    }

    private void createUIComponents() {
         doctorTable = new JXTable();
                eventList = new BasicEventList<DoctorListFormDataItem>();

        EventJXTableModel<DoctorListFormDataItem> model =
                new EventJXTableModel<DoctorListFormDataItem>(
                        eventList,
                        new String[]{"fio","title", "speciality", "workinghours"},
                         new String[]{"ФИО","Звание", "Специальность", "График"},
                        new boolean[]{false,false,false,false}
                );
        doctorTable.setModel(model);
        doctorTable.getColumnModel().getColumn(3).setCellRenderer(new TableCellRenderer() {
            JTextArea textArea = new JTextArea();
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                textArea.setText(value == null ? null : value.toString());


                if(isSelected)  {
                    textArea.setBackground(Color.cyan);
                }
            if  (!hasFocus) textArea.setBackground(Color.WHITE); //upd
                return textArea;
            }



        });
        doctorTable.setRowHeight(60);
    }





     public void refresh() {
        eventList.clear();
        if (provider!=null)
            eventList.addAll(provider.getAll());
         JXTable t = (JXTable) doctorTable;
         t.packAll();
    }

    private class MyMouseListener implements MouseListener {
        public void mouseClicked(MouseEvent e) {
            processEvent(e);
        }

        private void processEvent(MouseEvent e) {
            if (e.isPopupTrigger()){
                int row = doctorTable.rowAtPoint(e.getPoint());
                if(row != -1) {
                    popupElement = eventList.get(row);
                } else
                popupElement = null;

                editItem.setEnabled(popupElement != null);
                deleteItem.setEnabled(popupElement != null);

                popupMenu.setInvoker(doctorTable);
                popupMenu.setLocation(e.getXOnScreen(),e.getYOnScreen());
                popupMenu.setVisible(true);

                //popupMenu.show(serviceTable,e.getX(),e.getY());
            }
        }

        public void mousePressed(MouseEvent e) {

        }

        public void mouseReleased(MouseEvent e) {
              processEvent(e);
        }

        public void mouseEntered(MouseEvent e) {

        }

        public void mouseExited(MouseEvent e) {

        }
    }


}
