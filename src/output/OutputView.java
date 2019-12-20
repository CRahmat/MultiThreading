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

    JFrame frame = new JFrame("OUTPUT VIEW");// Instansiasi JFrame
    // Instansiasi komponen JPanel
    JPanel background = new JPanel();
    JPanel pbackgroundHeader = new JPanel();
    JPanel pback = new JPanel();
    JPanel pdataList = new JPanel();
    Container content = frame.getContentPane();// Instansiasi Kontainer
    //instansiasi komponen JFrame
    JLabel lbackground = new JLabel();
    JLabel lback = new JLabel();
    JLabel ldataList = new JLabel();
    JLabel lheader = new JLabel("HASIL PERHITUNGAN LUAS DAN VOLUME");
    ImageIcon iback = new ImageIcon(getClass().getResource("/img/back.png"));
    ImageIcon ibackground = new ImageIcon(getClass().getResource("/img/backgroundInput.png"));
    ImageIcon idataList = new ImageIcon(getClass().getResource("/img/data.png"));
   
    
    JLabel lPersegi = new JLabel("Persegi");
    public JTable listLuasPersegi;
    public JScrollPane pListLuasPersegi;
    public DefaultTableModel tableLuasPersegi;
    public String colomLuasPersegi[] = {"Keliling","Luas"};

    JLabel lPersegipanjang = new JLabel("Persegi Panjang");
    public JTable listLuasPersegiPanjang;
    public JScrollPane pListLuasPersegiPanjang;
    public DefaultTableModel tableLuasPersegiPanjang;
    public String colomLuasPersegiPanjang[] = {"Keliling","Luas"};
    
    JLabel lSegitiga = new JLabel("Segitiga");
    public JTable listLuasSegitiga;
    public JScrollPane pListLuasSegitiga;
    public DefaultTableModel tableLuasSegitiga;
    public String colomLuasSegitiga[] = {"Keliling","Luas"};
    
    JLabel lLingkaran = new JLabel("Lingkaran");
    public JTable listLuasLingkaran;
    public JScrollPane pListLuasLingkaran;
    public DefaultTableModel tableLuasLingkaran;
    public String colomLuasLingkaran[] = {"Keliling","Luas"};
    
    JLabel lBelahketupat = new JLabel("Belah Ketupat");
    public JTable listLuasBelahKetupat;
    public JScrollPane pListLuasBelahKetupat;
    public DefaultTableModel tableLuasBelahKetupat;
    public String colomLuasBelahKetupat[] = {"Keliling","Luas"};
    
    JLabel lLayang = new JLabel("Layang - Layang");
    public JTable listLuasLayangLayang;
    public JScrollPane pListLuasLayangLayang;
    public DefaultTableModel tableLuasLayangLayang;
    public String colomLuasLayangLayang[] = {"Keliling","Luas"};
    
    JLabel lTrapesium = new JLabel("Trapesium");
    public JTable listLuasTrapesium;
    public JScrollPane pListLuasTrapesium;
    public DefaultTableModel tableLuasTrapesium;
    public String colomLuasTrapesium[] = {"Keliling","Luas"};
    
    JLabel lJajargenjang = new JLabel("Jajargenjang");
    public JTable listLuasJajarGenjang;
    public JScrollPane pListLuasJajarGenjang;
    public DefaultTableModel tableLuasJajarGenjang;
    public String colomLuasJajarGenjang[] = {"Keliling","Luas"};
    
    JLabel lKubus = new JLabel("Kubus");
    public JTable listVolumeKubus;
    public JScrollPane pListVolumeKubus;
    public DefaultTableModel tableVolumeKubus;
    public String colomVolumeKubus[] = {"L.Permukaan", "Volume"};
    
    JLabel lBalok = new JLabel("Balok");
    public JTable listVolumeBalok;
    public JScrollPane pListVolumeBalok;
    public DefaultTableModel tableVolumeBalok;
    public String colomVolumeBalok[] = {"L.Permukaan", "Volume"};
    
    JLabel lTabung = new JLabel("Tabung");
    public JTable listVolumeTabung;
    public JScrollPane pListVolumeTabung;
    public DefaultTableModel tableVolumeTabung;
    public String colomVolumeTabung[] = {"L.Permukaan", "Volume"};
    
    JLabel lKerucut = new JLabel("Kerucut");
    public JTable listVolumeKerucut;
    public JScrollPane pListVolumeKerucut;
    public DefaultTableModel tableVolumeKerucut;
    public String colomVolumeKerucut[] = {"L.Permukaan", "Volume"};
    
    JLabel lBola = new JLabel("Bola");
    public JTable listVolumeBola;
    public JScrollPane pListVolumeBola;
    public DefaultTableModel tableVolumeBola;
    public String colomVolumeBola[] = {"L.Permukaan", "Volume"};
    
    JLabel lPrisma = new JLabel("Prisma");
    public JTable listVolumePrisma;
    public JScrollPane pListVolumePrisma;
    public DefaultTableModel tableVolumePrisma;
    public String colomVolumePrisma[] = {"L.Permukaan", "Volume"};
    
    JLabel lLSegiempat = new JLabel("Limas Segiempat");
    public JTable listVolumeLimasSegiEmpat;
    public JScrollPane pListVolumeLimasSegiEmpat;
    public DefaultTableModel tableVolumeLimasSegiEmpat;
    public String colomVolumeLimasSegiEmpat[] = {"L.Permukaan", "Volume"};
    
    JLabel lLSegitiga = new JLabel("Limas Segitiga");
    public JTable listVolumeLimasSegiTiga;
    public JScrollPane pListVolumeLimasSegiTiga;
    public DefaultTableModel tableVolumeLimasSegiTiga;
    public String colomVolumeLimasSegiTiga[] = {"L.Permukaan", "Volume"};
    
    

    public OutputView() {
        // Setting Komponen JFrame
        frame.setLayout(null);
        frame.setSize(1200, 618);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        initComponents();


    }

    public void initComponents() {
        // Setting dan add komponen ke kontainer
        lback.setBounds(10, 10, 20, 18);
        lback.setIcon(iback);
        content.add(lback);
        
        ldataList.setBounds(1157, 5, 35, 24);
        ldataList.setIcon(idataList);
        content.add(ldataList);

        pback.setBounds(0, 0, 40, 35);
        pback.setBackground(Color.WHITE);
        content.add(pback);
        
        pdataList.setBounds(1150, 0, 50, 35);
        pdataList.setBackground(Color.WHITE);
        content.add(pdataList);

        lheader.setBounds(350, 10, 750, 20);
        lheader.setFont(new Font("Cooper Black", Font.CENTER_BASELINE, 20));
        lheader.setForeground(Color.WHITE);
        content.add(lheader);

        pbackgroundHeader.setBounds(0, 0, 1200, 35);
        pbackgroundHeader.setBackground(new Color(206, 227, 255));
        content.add(pbackgroundHeader);
        
        
        content.add(lPersegi);
        lPersegi.setBounds(75, 75, 133, 20);
        tableLuasPersegi = new DefaultTableModel(colomLuasPersegi, 0);
        listLuasPersegi = new JTable(tableLuasPersegi);
        pListLuasPersegi = new JScrollPane(listLuasPersegi);
        pListLuasPersegi.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        pListLuasPersegi.setBounds(30, 95, 133, 200);
        content.add(pListLuasPersegi);
        
        content.add(lPersegipanjang);
        lPersegipanjang.setBounds(193, 75, 133, 20);
        tableLuasPersegiPanjang = new DefaultTableModel(colomLuasPersegiPanjang, 0);
        listLuasPersegiPanjang = new JTable(tableLuasPersegiPanjang);
        pListLuasPersegiPanjang = new JScrollPane(listLuasPersegiPanjang);
        pListLuasPersegiPanjang.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        pListLuasPersegiPanjang.setBounds(174, 95, 133, 200);
        content.add(pListLuasPersegiPanjang);
        
        content.add(lSegitiga);
        lSegitiga.setBounds(358, 75, 133, 20);
        tableLuasSegitiga = new DefaultTableModel(colomLuasSegitiga, 0);
        listLuasSegitiga = new JTable(tableLuasSegitiga);
        pListLuasSegitiga = new JScrollPane(listLuasSegitiga);
        pListLuasSegitiga.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        pListLuasSegitiga.setBounds(317, 95, 133, 200);
        content.add(pListLuasSegitiga);
        
        content.add(lLingkaran);
        lLingkaran.setBounds(495, 75, 133, 20);
        tableLuasLingkaran = new DefaultTableModel(colomLuasLingkaran, 0);
        listLuasLingkaran = new JTable(tableLuasLingkaran);
        pListLuasLingkaran = new JScrollPane(listLuasLingkaran);
        pListLuasLingkaran.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        pListLuasLingkaran.setBounds(460, 95, 133, 200);
        content.add(pListLuasLingkaran);
        
        content.add(lBelahketupat);
        lBelahketupat.setBounds(630, 75, 133, 20);
        tableLuasBelahKetupat = new DefaultTableModel(colomLuasBelahKetupat, 0);
        listLuasBelahKetupat = new JTable(tableLuasBelahKetupat);
        pListLuasBelahKetupat = new JScrollPane(listLuasBelahKetupat);
        pListLuasBelahKetupat.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        pListLuasBelahKetupat.setBounds(603, 95, 133, 200);
        content.add(pListLuasBelahKetupat);
        
        content.add(lLayang);
        lLayang.setBounds(765, 75, 133, 20);
        tableLuasLayangLayang = new DefaultTableModel(colomLuasLayangLayang, 0);
        listLuasLayangLayang = new JTable(tableLuasLayangLayang);
        pListLuasLayangLayang = new JScrollPane(listLuasLayangLayang);
        pListLuasLayangLayang.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        pListLuasLayangLayang.setBounds(746, 95, 133, 200);
        content.add(pListLuasLayangLayang);
        
        content.add(lTrapesium);
        lTrapesium.setBounds(925, 75, 133, 20);
        tableLuasTrapesium = new DefaultTableModel(colomLuasTrapesium , 0);
        listLuasTrapesium  = new JTable(tableLuasTrapesium);
        pListLuasTrapesium  = new JScrollPane(listLuasTrapesium);
        pListLuasTrapesium.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        pListLuasTrapesium.setBounds(889, 95, 133, 200);
        content.add(pListLuasTrapesium);
        
        content.add(lJajargenjang);
        lJajargenjang.setBounds(1060, 75, 133, 20);
        tableLuasJajarGenjang = new DefaultTableModel(colomLuasJajarGenjang , 0);
        listLuasJajarGenjang  = new JTable(tableLuasJajarGenjang);
        pListLuasJajarGenjang  = new JScrollPane(listLuasJajarGenjang);
        pListLuasJajarGenjang.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        pListLuasJajarGenjang.setBounds(1032, 95, 133, 200);
        content.add(pListLuasJajarGenjang);
        
        content.add(lKubus);
        lKubus.setBounds(75, 330, 133, 20);
        tableVolumeKubus = new DefaultTableModel(colomVolumeKubus, 0);
        listVolumeKubus = new JTable(tableVolumeKubus);
        pListVolumeKubus = new JScrollPane(listVolumeKubus);
        pListVolumeKubus.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        pListVolumeKubus.setBounds(30, 350, 133, 200);
        content.add(pListVolumeKubus);
        
        content.add(lBalok);
        lBalok.setBounds(225, 330, 133, 20);
        tableVolumeBalok = new DefaultTableModel(colomVolumeBalok, 0);
        listVolumeBalok = new JTable(tableVolumeBalok);
        pListVolumeBalok = new JScrollPane(listVolumeBalok);
        pListVolumeBalok.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        pListVolumeBalok.setBounds(174, 350, 133, 200);
        content.add(pListVolumeBalok);

        content.add(lTabung);
        lTabung.setBounds(360, 330, 133, 20);
        tableVolumeTabung = new DefaultTableModel(colomVolumeTabung, 0);
        listVolumeTabung = new JTable(tableVolumeTabung);
        pListVolumeTabung = new JScrollPane(listVolumeTabung);
        pListVolumeTabung.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        pListVolumeTabung.setBounds(317, 350, 133, 200);
        content.add(pListVolumeTabung);
        
        content.add(lKerucut);
        lKerucut.setBounds(503, 330, 133, 20);
        tableVolumeKerucut = new DefaultTableModel(colomVolumeKerucut, 0);
        listVolumeKerucut = new JTable(tableVolumeKerucut);
        pListVolumeKerucut = new JScrollPane(listVolumeKerucut);
        pListVolumeKerucut.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        pListVolumeKerucut.setBounds(460, 350, 133, 200);
        content.add(pListVolumeKerucut);
        
        content.add(lBola);
        lBola.setBounds(655, 330, 133, 20);
        tableVolumeBola = new DefaultTableModel(colomVolumeBola, 0);
        listVolumeBola = new JTable(tableVolumeBola);
        pListVolumeBola = new JScrollPane(listVolumeBola);
        pListVolumeBola.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        pListVolumeBola.setBounds(603, 350, 133, 200);
        content.add(pListVolumeBola);
        
        content.add(lPrisma);
        lPrisma.setBounds(787, 330, 133, 20);
        tableVolumePrisma = new DefaultTableModel(colomVolumePrisma , 0);
        listVolumePrisma = new JTable(tableVolumePrisma);
        pListVolumePrisma = new JScrollPane(listVolumePrisma);
        pListVolumePrisma .setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        pListVolumePrisma .setBounds(746, 350, 133, 200);
        content.add(pListVolumePrisma );
        
        content.add(lLSegitiga);
        lLSegitiga.setBounds(913, 330, 133, 20);
        tableVolumeLimasSegiTiga = new DefaultTableModel(colomVolumeLimasSegiTiga , 0);
        listVolumeLimasSegiTiga  = new JTable(tableVolumeLimasSegiTiga);
        pListVolumeLimasSegiTiga  = new JScrollPane(listVolumeLimasSegiTiga);
        pListVolumeLimasSegiTiga.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        pListVolumeLimasSegiTiga.setBounds(889, 350, 133, 200);
        content.add(pListVolumeLimasSegiTiga);
        
        content.add(lLSegiempat);
        lLSegiempat.setBounds(1045, 330, 133, 20);
        tableVolumeLimasSegiEmpat = new DefaultTableModel(colomVolumeLimasSegiEmpat , 0);
        listVolumeLimasSegiEmpat  = new JTable(tableVolumeLimasSegiEmpat);
        pListVolumeLimasSegiEmpat  = new JScrollPane(listVolumeLimasSegiEmpat);
        pListVolumeLimasSegiEmpat.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        pListVolumeLimasSegiEmpat.setBounds(1032, 350, 133, 200);
        content.add(pListVolumeLimasSegiEmpat);

        lbackground.setBounds(0, 0, 1200, 618);
        lbackground.setIcon(ibackground);
        content.add(lbackground);

    }

}

