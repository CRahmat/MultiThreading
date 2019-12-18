/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package input;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.table.TableColumn;


/**
 * @author Catur Rahmat
 */
public class InputView {

    JFrame frame = new JFrame("INPUT VIEW");
    JLabel lheader = new JLabel("JUMLAH ROWS");
    JPanel background = new JPanel();
    Container content = frame.getContentPane();
    JLabel lbackground = new JLabel();
    JLabel lback = new JLabel();
    JTextField linput = new JTextField();
    JSeparator jsinput = new JSeparator();
    ImageIcon iback = new ImageIcon(getClass().getResource("/img/back.png"));
    ImageIcon ibackground = new ImageIcon(getClass().getResource("/img/backgroundinput.png"));
    JTable list;
    JScrollPane pList;
    DefaultTableModel dataInput;
    String colom[] = {"NO","Panjang 1", "Panjang 2", "Lebar 1", "Lebar 2", "Diagonal 1", "Diagonal 2", "Tinggi", "Jari-Jari"};

    public InputView() {
        frame.setLayout(null);
        frame.setSize(900, 525);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
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

        linput.setBounds(225, 40, 450, 40);
        linput.setFont(new Font("Cooper Black", Font.CENTER_BASELINE, 30));
        linput.setBackground(new Color(230, 230, 230));
        linput.setForeground(Color.BLACK);
        linput.setHorizontalAlignment(JTextField.CENTER);
        linput.getFocusListeners();
        content.add(linput);

        jsinput.setBounds(200, 85, 500, 10);
        content.add(jsinput);
        
        dataInput = new DefaultTableModel(colom, 0);
        list = new JTable(dataInput);
        pList = new JScrollPane(list);
        pList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        pList.setBounds(50, 100, 795, 350);
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
