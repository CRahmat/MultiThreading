/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package output;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 * @author Catur Rahmat
 */
public class OutputView {

    JFrame frame = new JFrame("OUTPUT VIEW");
    JPanel background = new JPanel();
    JPanel pbackgroundHeader = new JPanel();
    JPanel pback = new JPanel();
    JPanel pdataList = new JPanel();
    Container content = frame.getContentPane();
    JLabel lbackground = new JLabel();
    JLabel lback = new JLabel();
    JLabel ldataList = new JLabel();
    JLabel lheader = new JLabel("HASIL PERHITUNGAN LUAS DAN VOLUME");
    ImageIcon iback = new ImageIcon(getClass().getResource("/img/back.png"));
    ImageIcon ibackground = new ImageIcon(getClass().getResource("/img/backgroundInput.png"));
    ImageIcon idataList = new ImageIcon(getClass().getResource("/img/data.png"));
    public JTable listLuasPersegi;
    public JScrollPane pListLuasPersegi;
    public DefaultTableModel tableLuasPersegi;
    public String colomLuasPersegi[] = {"Keliling","Luas"};

    public JTable listLuasPersegiPanjang;
    public JScrollPane pListLuasPersegiPanjang;
    public DefaultTableModel tableLuasPersegiPanjang;
    public String colomLuasPersegiPanjang[] = {"Keliling","Luas"};
    
    public JTable listLuasSegitiga;
    public JScrollPane pListLuasSegitiga;
    public DefaultTableModel tableLuasSegitiga;
    public String colomLuasSegitiga[] = {"Keliling","Luas"};
    
    public JTable listLuasLingkaran;
    public JScrollPane pListLuasLingkaran;
    public DefaultTableModel tableLuasLingkaran;
    public String colomLuasLingkaran[] = {"Keliling","Luas"};
    
    public JTable listLuasBelahKetupat;
    public JScrollPane pListLuasBelahKetupat;
    public DefaultTableModel tableLuasBelahKetupat;
    public String colomLuasBelahKetupat[] = {"Keliling","Luas"};
    
    public JTable listLuasLayangLayang;
    public JScrollPane pListLuasLayangLayang;
    public DefaultTableModel tableLuasLayangLayang;
    public String colomLuasLayangLayang[] = {"Keliling","Luas"};
    
    public JTable listLuasTrapesium;
    public JScrollPane pListLuasTrapesium;
    public DefaultTableModel tableLuasTrapesium;
    public String colomLuasTrapesium[] = {"Keliling","Luas"};
    
    public JTable listLuasJajarGenjang;
    public JScrollPane pListLuasJajarGenjang;
    public DefaultTableModel tableLuasJajarGenjang;
    public String colomLuasJajarGenjang[] = {"Keliling","Luas"};
    
    
    public JTable listVolumeKubus;
    public JScrollPane pListVolumeKubus;
    public DefaultTableModel tableVolumeKubus;
    public String colomVolumeKubus[] = {"L.Permukaan", "Volume"};
    
    public JTable listVolumeBalok;
    public JScrollPane pListVolumeBalok;
    public DefaultTableModel tableVolumeBalok;
    public String colomVolumeBalok[] = {"L.Permukaan", "Volume"};
    
    public JTable listVolumeTabung;
    public JScrollPane pListVolumeTabung;
    public DefaultTableModel tableVolumeTabung;
    public String colomVolumeTabung[] = {"L.Permukaan", "Volume"};
    
    public JTable listVolumeKerucut;
    public JScrollPane pListVolumeKerucut;
    public DefaultTableModel tableVolumeKerucut;
    public String colomVolumeKerucut[] = {"L.Permukaan", "Volume"};
    
    public JTable listVolumeBola;
    public JScrollPane pListVolumeBola;
    public DefaultTableModel tableVolumeBola;
    public String colomVolumeBola[] = {"L.Permukaan", "Volume"};
    
    public JTable listVolumePrisma;
    public JScrollPane pListVolumePrisma;
    public DefaultTableModel tableVolumePrisma;
    public String colomVolumePrisma[] = {"L.Permukaan", "Volume"};
    
    public JTable listVolumeLimasSegiEmpat;
    public JScrollPane pListVolumeLimasSegiEmpat;
    public DefaultTableModel tableVolumeLimasSegiEmpat;
    public String colomVolumeLimasSegiEmpat[] = {"L.Permukaan", "Volume"};
    
    public JTable listVolumeLimasSegiTiga;
    public JScrollPane pListVolumeLimasSegiTiga;
    public DefaultTableModel tableVolumeLimasSegiTiga;
    public String colomVolumeLimasSegiTiga[] = {"L.Permukaan", "Volume"};
    

    public OutputView() {
        frame.setLayout(null);
        frame.setSize(924, 560);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        initComponents();


    }

    public void initComponents() {
        lback.setBounds(10, 10, 20, 18);
        lback.setIcon(iback);
        content.add(lback);
        
        ldataList.setBounds(884, 5, 35, 24);
        ldataList.setIcon(idataList);
        content.add(ldataList);

        pback.setBounds(0, 0, 40, 35);
        pback.setBackground(Color.WHITE);
        content.add(pback);
        
        pdataList.setBounds(874, 0, 50, 35);
        pdataList.setBackground(Color.WHITE);
        content.add(pdataList);

        lheader.setBounds(212, 10, 750, 20);
        lheader.setFont(new Font("Cooper Black", Font.CENTER_BASELINE, 20));
        ;
        lheader.setForeground(Color.WHITE);
        content.add(lheader);

        pbackgroundHeader.setBounds(0, 0, 900, 35);
        pbackgroundHeader.setBackground(new Color(206, 227, 255));
        content.add(pbackgroundHeader);

        tableLuasPersegi = new DefaultTableModel(colomLuasPersegi, 0);
        listLuasPersegi = new JTable(tableLuasPersegi);
        pListLuasPersegi = new JScrollPane(listLuasPersegi);
        pListLuasPersegi.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        pListLuasPersegi.setBounds(24, 70, 100, 200);
        content.add(pListLuasPersegi);
        
        tableLuasPersegiPanjang = new DefaultTableModel(colomLuasPersegiPanjang, 0);
        listLuasPersegiPanjang = new JTable(tableLuasPersegiPanjang);
        pListLuasPersegiPanjang = new JScrollPane(listLuasPersegiPanjang);
        pListLuasPersegiPanjang.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        pListLuasPersegiPanjang.setBounds(134, 70, 100, 200);
        content.add(pListLuasPersegiPanjang);
        

        tableLuasSegitiga = new DefaultTableModel(colomLuasSegitiga, 0);
        listLuasSegitiga = new JTable(tableLuasSegitiga);
        pListLuasSegitiga = new JScrollPane(listLuasSegitiga);
        pListLuasSegitiga.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        pListLuasSegitiga.setBounds(244, 70, 100, 200);
        content.add(pListLuasSegitiga);
        
        tableLuasLingkaran = new DefaultTableModel(colomLuasLingkaran, 0);
        listLuasLingkaran = new JTable(tableLuasLingkaran);
        pListLuasLingkaran = new JScrollPane(listLuasLingkaran);
        pListLuasLingkaran.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        pListLuasLingkaran.setBounds(354, 70, 100, 200);
        content.add(pListLuasLingkaran);
        
        tableLuasBelahKetupat = new DefaultTableModel(colomLuasBelahKetupat, 0);
        listLuasBelahKetupat = new JTable(tableLuasBelahKetupat);
        pListLuasBelahKetupat = new JScrollPane(listLuasBelahKetupat);
        pListLuasBelahKetupat.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        pListLuasBelahKetupat.setBounds(464, 70, 100, 200);
        content.add(pListLuasBelahKetupat);
        
        tableLuasLayangLayang = new DefaultTableModel(colomLuasLayangLayang, 0);
        listLuasLayangLayang = new JTable(tableLuasLayangLayang);
        pListLuasLayangLayang = new JScrollPane(listLuasLayangLayang);
        pListLuasLayangLayang.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        pListLuasLayangLayang.setBounds(574, 70, 100, 200);
        content.add(pListLuasLayangLayang);
        
        tableLuasTrapesium = new DefaultTableModel(colomLuasTrapesium , 0);
        listLuasTrapesium  = new JTable(tableLuasTrapesium);
        pListLuasTrapesium  = new JScrollPane(listLuasTrapesium);
        pListLuasTrapesium.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        pListLuasTrapesium.setBounds(684, 70, 100, 200);
        content.add(pListLuasTrapesium);
        
        tableLuasJajarGenjang = new DefaultTableModel(colomLuasJajarGenjang , 0);
        listLuasJajarGenjang  = new JTable(tableLuasJajarGenjang);
        pListLuasJajarGenjang  = new JScrollPane(listLuasJajarGenjang);
        pListLuasJajarGenjang.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        pListLuasJajarGenjang.setBounds(794, 70, 100, 200);
        content.add(pListLuasJajarGenjang);
        
        tableVolumeKubus = new DefaultTableModel(colomVolumeKubus, 0);
        listVolumeKubus = new JTable(tableVolumeKubus);
        pListVolumeKubus = new JScrollPane(listVolumeKubus);
        pListVolumeKubus.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        pListVolumeKubus.setBounds(24, 310, 100, 200);
        content.add(pListVolumeKubus);
        
        tableVolumeBalok = new DefaultTableModel(colomVolumeBalok, 0);
        listVolumeBalok = new JTable(tableVolumeBalok);
        pListVolumeBalok = new JScrollPane(listVolumeBalok);
        pListVolumeBalok.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        pListVolumeBalok.setBounds(134, 310, 100, 200);
        content.add(pListVolumeBalok);

        tableVolumeTabung = new DefaultTableModel(colomVolumeTabung, 0);
        listVolumeTabung = new JTable(tableVolumeTabung);
        pListVolumeTabung = new JScrollPane(listVolumeTabung);
        pListVolumeTabung.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        pListVolumeTabung.setBounds(244, 310, 100, 200);
        content.add(pListVolumeTabung);
        
        tableVolumeKerucut = new DefaultTableModel(colomVolumeKerucut, 0);
        listVolumeKerucut = new JTable(tableVolumeKerucut);
        pListVolumeKerucut = new JScrollPane(listVolumeKerucut);
        pListVolumeKerucut.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        pListVolumeKerucut.setBounds(354, 310, 100, 200);
        content.add(pListVolumeKerucut);
        
        tableVolumeBola = new DefaultTableModel(colomVolumeBola, 0);
        listVolumeBola = new JTable(tableVolumeBola);
        pListVolumeBola = new JScrollPane(listVolumeBola);
        pListVolumeBola.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        pListVolumeBola.setBounds(464, 310, 100, 200);
        content.add(pListVolumeBola);
        
        tableVolumePrisma = new DefaultTableModel(colomVolumePrisma , 0);
        listVolumePrisma = new JTable(tableVolumePrisma);
        pListVolumePrisma = new JScrollPane(listVolumePrisma);
        pListVolumePrisma .setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        pListVolumePrisma .setBounds(574, 310, 100, 200);
        content.add(pListVolumePrisma );
        
        tableVolumeLimasSegiTiga = new DefaultTableModel(colomVolumeLimasSegiTiga , 0);
        listVolumeLimasSegiTiga  = new JTable(tableVolumeLimasSegiTiga);
        pListVolumeLimasSegiTiga  = new JScrollPane(listVolumeLimasSegiTiga);
        pListVolumeLimasSegiTiga.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        pListVolumeLimasSegiTiga.setBounds(684, 310, 100, 200);
        content.add(pListVolumeLimasSegiTiga);
        
        tableVolumeLimasSegiEmpat = new DefaultTableModel(colomVolumeLimasSegiEmpat , 0);
        listVolumeLimasSegiEmpat  = new JTable(tableVolumeLimasSegiEmpat);
        pListVolumeLimasSegiEmpat  = new JScrollPane(listVolumeLimasSegiEmpat);
        pListVolumeLimasSegiEmpat.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        pListVolumeLimasSegiEmpat.setBounds(794, 310, 100, 200);
        content.add(pListVolumeLimasSegiEmpat);

        lbackground.setBounds(0, 0, 924, 565);
        lbackground.setIcon(ibackground);
        content.add(lbackground);

    }

}
