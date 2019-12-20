/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package output;

import MainMenu.VCMainMenu;
import bangundatar.BelahKetupat;
import bangundatar.JajarGenjang;
import bangundatar.LayangLayang;
import bangundatar.Lingkaran;
import bangundatar.Persegi;
import bangundatar.PersegiPanjang;
import bangundatar.Segitiga;
import bangundatar.Trapesium;
import bangunruang.Balok;
import bangunruang.Bola;
import bangunruang.Kerucut;
import bangunruang.Kubus;
import bangunruang.LimasSegiEmpat;
import bangunruang.LimasSegiTiga;
import bangunruang.PrismaSegitiga;
import bangunruang.Tabung;
import dataList.VCDataList;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

/**
 * @author Catur Rahmat
 */
public class OutputController {
    OutputView outputView;
    Persegi persegi;
    PersegiPanjang persegiPanjang;
    Segitiga segitiga;
    Lingkaran lingkaran;
    BelahKetupat belahKetupat;
    Trapesium trapesium;
    JajarGenjang jajarGenjang;
    LayangLayang layangLayang;
    Kubus kubus;
    Balok balok;
    Tabung tabung;
    Kerucut kerucut;
    Bola bola;
    PrismaSegitiga prismaSegitiga;
    LimasSegiTiga limasSegiTiga;
    LimasSegiEmpat limasSegiEmpat;

    public OutputController(OutputView outputView,Persegi persegi, PersegiPanjang persegiPanjang, Segitiga segitiga, Lingkaran lingkaran, BelahKetupat belahKetupat, JajarGenjang jajarGenjang, Trapesium trapesium, LayangLayang layangLayang, Kubus kubus,Balok balok,Tabung tabung,Kerucut kerucut,Bola bola,PrismaSegitiga prismaSegitiga, LimasSegiTiga limasSegiTiga, LimasSegiEmpat limasSegiEmpat) throws InterruptedException {
        this.outputView = outputView;
        this.persegi = persegi;
        this.persegiPanjang = persegiPanjang;
        this.segitiga = segitiga;
        this.lingkaran = lingkaran;
        this.belahKetupat = belahKetupat;
        this.trapesium = trapesium;
        this.jajarGenjang = jajarGenjang;
        this.trapesium = trapesium;
        this.kubus = kubus;
        this.balok = balok;
        this.tabung = tabung;
        this.kerucut = kerucut;
        this.bola = bola;
        this.prismaSegitiga = prismaSegitiga;
        this.limasSegiTiga = limasSegiTiga;
        this.limasSegiEmpat = limasSegiEmpat;
        
        persegi.start();
        persegiPanjang.start();
        segitiga.start();
        lingkaran.start();
        belahKetupat.start();
        layangLayang.start();
        trapesium.start();
        jajarGenjang.start();
        kubus.start();
        balok.start();
        tabung.start();
        kerucut.start();
        bola.start();
        prismaSegitiga.start();
        limasSegiTiga.start();
        limasSegiEmpat.start();
        
        outputView.pback.addMouseListener(new MouseAdapter() {//Action Listener Tombol Back
            @Override
            public void mouseClicked(MouseEvent me) {
                outputView.frame.setVisible(false);//Menyembunyikan Tampilan Input Dari Monitor
                new VCMainMenu();//Instansiasi Obyek VCMain Menu Digunakan Untuk Memanggil Tampilan Utama Dan controller Tampilan Utama
            }
        });
        outputView.pdataList.addMouseListener(new MouseAdapter() {//Action Listener Tombol Back
            @Override
            public void mouseClicked(MouseEvent me) {//Event Handler dari Mouse
                new VCDataList();//Instansiasi Obyek VCMain Menu Digunakan Untuk Memanggil Tampilan List Data Dan controller List Data
                //List Data Digunakan Untuk Menampilkan Data Random Yang Tersimpan
                //Ketika Menampilkan List Data Maka Threads Akan Tetap Di Jalankan
            }
        });

    }


}
