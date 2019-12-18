package dataList;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Catur Rahmat
 */
public class DataListView {
    JFrame frame = new JFrame("DATA LIST");
    JLabel lheader = new JLabel("DATA LIST");
    JPanel background = new JPanel();
    Container content = frame.getContentPane();
    JLabel lbackground = new JLabel();
    JLabel lback = new JLabel();
    JSeparator jsinput = new JSeparator();
    ImageIcon iback = new ImageIcon(getClass().getResource("/img/back.png"));
    ImageIcon ibackground = new ImageIcon(getClass().getResource("/img/backgroundinput.png"));
    JTable list;
    JScrollPane pList;
    DefaultTableModel dataInput;
    String colom[] = {"NO","Panjang 1", "Panjang 2", "Lebar 1", "Lebar 2", "Diagonal 1", "Diagonal 2", "Tinggi", "Jari-Jari"};

    public DataListView() {
        frame.setLayout(null);
        frame.setSize(900, 525);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        initComponents();


    }

    public void initComponents() {
        lback.setBounds(10, 10, 20, 20);
        lback.setIcon(iback);
        content.add(lback);
        
        lheader.setBounds(350, 10, 200, 25);
        lheader.setFont(new Font("Cooper Black", Font.CENTER_BASELINE, 20));
  
        lheader.setForeground(Color.WHITE);
        content.add(lheader);

        jsinput.setBounds(200, 40, 500, 10);
        content.add(jsinput);
        
        dataInput = new DefaultTableModel(colom, 0);
        list = new JTable(dataInput);
        pList = new JScrollPane(list);
        pList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        pList.setBounds(50, 60, 795, 390);
        int i = 0;
        while(i < 8){
            TableColumn tableColumn = list.getColumnModel().getColumn(i);
            if(i == 0){
            tableColumn.setPreferredWidth(20);
            }else{
                list.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
            }
        i++;
        }
        content.add(pList);
        
        lbackground.setBounds(0, 0, 900, 500);
        lbackground.setIcon(ibackground);
        content.add(lbackground);

    }
    
}
