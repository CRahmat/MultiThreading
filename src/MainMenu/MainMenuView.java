package MainMenu;


import javax.swing.*;
import java.awt.*;

import static javax.swing.JFrame.EXIT_ON_CLOSE;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Catur Rahmat
 */
public class MainMenuView {
    JFrame frame = new JFrame("MAIN MENU");
    JPanel background = new JPanel();
    Container content = frame.getContentPane();
    JLabel lbackground = new JLabel();
    JLabel linput = new JLabel("INPUT DATA");
    JLabel loutput = new JLabel("OUTPUT&PROCESS");
    JLabel lversion = new JLabel("Version 1.0.0");
    JPanel pinput = new JPanel();
    JPanel poutput = new JPanel();
    ImageIcon ibackground = new ImageIcon(getClass().getResource("/img/background.png"));

    public MainMenuView() {
        frame.setLayout(null);
        frame.setSize(900, 525);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        initComponents();


    }

    public void initComponents() {
        linput.setBounds(305, 230, 300, 50);
        linput.setFont(new Font("Cooper Black", Font.CENTER_BASELINE, 40));
        linput.setForeground(Color.WHITE);
        content.add(linput);
        pinput.setBounds(280, 219, 340, 70);
        pinput.setBackground(new Color(255, 77, 125));
        content.add(pinput);
        loutput.setBounds(280, 350, 400, 50);
        loutput.setFont(new Font("Cooper Black", Font.CENTER_BASELINE, 30));
        loutput.setForeground(Color.WHITE);
        content.add(loutput);
        poutput.setBounds(280, 339, 340, 70);
        poutput.setBackground(new Color(199, 230, 64));
        content.add(poutput);
        lversion.setFont(new Font("Arial Rounded MT Bold", Font.CENTER_BASELINE, 12));
        lversion.setBounds(405, 460, 90, 20);
        lversion.setForeground(new Color(255, 77, 125));
        content.add(lversion);


        lbackground.setBounds(0, 0, 900, 500);
        lbackground.setIcon(ibackground);
        content.add(lbackground);

    }

}
