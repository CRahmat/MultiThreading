/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package output;

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
/**
 * @author Catur Rahmat
 */
public class MVCOutput {
    public MVCOutput() throws InterruptedException {
        OutputView outputView = new OutputView();
        Persegi persegi = new Persegi(outputView);
        PersegiPanjang persegiPanjang = new PersegiPanjang(outputView);
        Segitiga segitiga = new Segitiga(outputView);
        Lingkaran lingkaran = new Lingkaran(outputView);
        BelahKetupat belahKetupat = new BelahKetupat(outputView);
        JajarGenjang jajarGenjang = new JajarGenjang(outputView);
        Trapesium trapesium = new Trapesium(outputView);
        LayangLayang layangLayang = new LayangLayang(outputView);
        Kubus kubus = new Kubus(outputView);
        Balok balok = new Balok(outputView);
        Tabung tabung = new Tabung(outputView);
        Kerucut kerucut = new Kerucut(outputView);
        Bola bola = new Bola(outputView);
        PrismaSegitiga prismaSegitiga = new PrismaSegitiga(outputView);
        LimasSegiTiga limasSegiTiga = new LimasSegiTiga(outputView);
        LimasSegiEmpat limasSegiEmpat = new LimasSegiEmpat(outputView);
        OutputController outputController = new OutputController(outputView, persegi,persegiPanjang,segitiga,lingkaran,belahKetupat,jajarGenjang,trapesium,layangLayang,kubus,balok,tabung,kerucut,bola,prismaSegitiga,limasSegiTiga,limasSegiEmpat);
    }
}
