
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bangunruang;

import bangundatar.Segitiga;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import output.OutputView;

/**
 *
 * @author Catur Rahmat
 */
public class LimasSegiTiga extends Segitiga implements Runnable{
    OutputView outputView;
    RandomAccessFile fileRAFData = null;
    RandomAccessFile fileRAFLuasPermukaanLimas = null;
    RandomAccessFile fileRAFVolumeLimas = null;
    RandomAccessFile RAFLenght = null;
    private int dataLenght;
    protected Integer[] volumeLimasSegiTiga;
    protected Integer[] luasPermukaanLimasSegiTiga;
    private Integer[] tinggi;
    private int data;
    int j;
    int k;
    int l;
    int index;
    int row;
    public LimasSegiTiga(OutputView outputView) {//Constructor dari Limas Dengan Parameter OutputView
        super(outputView);                        //Mengirimkan Parameter Output View ke Super Class
        this.outputView = outputView;             //Digunakan Untuk Input Hasil Perhitungan Ke View
    }
   //Override method run dikarenakan extends Threads
    @Override
        //Badan Threads
    public void run(){
        try {
        Thread.sleep(4000);
        } catch (InterruptedException ex) {//Exception Threads Terinterupsi
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        hitungVolume();
    }
    public synchronized void hitungVolume(){
        
        try {
            //Instansiasi Obyek Random Access File
            fileRAFData = new RandomAccessFile("src\\saveData\\Data-Bangun.dat", "rw");
            fileRAFLuasPermukaanLimas = new RandomAccessFile("src\\saveData\\3D\\Luas-Permukaan-Limas.dat", "rw");
            fileRAFVolumeLimas = new RandomAccessFile("src\\saveData\\3D\\Volume-Limas-Segitiga.dat", "rw");
            RAFLenght = new RandomAccessFile("src\\saveData\\Data-Lenght.dat", "rw");
            
            RAFLenght.seek(0);//File Selalu Berada Pada File Pointer 0 (Hanya 1 Data)
            dataLenght = RAFLenght.readInt();//Baca Lenght Dari File
            RAFLenght.close();//Close File Lenght    
            //Instansiasi Obyek untuk Perhitungan
            tinggi = new Integer[dataLenght];
            volumeLimasSegiTiga = new Integer[dataLenght];
            luasPermukaanLimasSegiTiga = new Integer[dataLenght];
            //SetLenght and Get Pointer dari File
            j = (int) fileRAFData.getFilePointer();
            fileRAFData.setLength(dataLenght);
            //SetLenght and Get Pointer dari File
            k = (int) fileRAFVolumeLimas.getFilePointer();
            fileRAFVolumeLimas.setLength(dataLenght);
            //SetLenght and Get Pointer dari File
            l = (int) fileRAFLuasPermukaanLimas.getFilePointer();
            fileRAFLuasPermukaanLimas.setLength(dataLenght);
            
            index = 0;
            row = 0;
            while (j < fileRAFData.length()){
                j+=6;
                fileRAFData.seek(j);//Penyesesuaian Pointer untuk Baca File
                data = fileRAFData.read();//Membaca Tinggi Dari File
                tinggi[index] = data;
                //PERHITUNGAN LUAS PERMUKAAN DAN VOLUME
                volumeLimasSegiTiga[row] = (int)(Segitiga.luasSegitiga[index]*tinggi[index] * 0.166666667);
                luasPermukaanLimasSegiTiga[row] = (int)(Segitiga.luasSegitiga[index]*4);
                //Menulis Hasil Perhitungan Ke File
                fileRAFVolumeLimas.seek(k);//Penyesesuaian Pointer agar Data Tidak Tertimpa
                fileRAFVolumeLimas.write(volumeLimasSegiTiga[row]);//Menuliskan Hasil Perhitungan Volume Ke File
                fileRAFLuasPermukaanLimas.seek(l);//Penyesesuaian Pointer agar Data Tidak Tertimpa
                fileRAFLuasPermukaanLimas.write(luasPermukaanLimasSegiTiga[row]);//Menuliskan Hasil Perhitungan Luas Ke File
                //OUTPUT KE MONITOR TANPA SWING
                System.out.print("Tinggi Limas                  " + fileRAFData.getFilePointer() + ": ");
                System.out.println(tinggi[index] + "; ");
                System.out.println("Volume Limas Segi Tiga      : "+volumeLimasSegiTiga[row]);
                System.out.println("L.Permukaan Limas Segi Tiga : "+luasPermukaanLimasSegiTiga[row]);
                //INPUT KE JTABLE
                outputView.tableVolumeLimasSegiTiga.insertRow(outputView.tableVolumeLimasSegiTiga.getRowCount(), new Object[]{
                luasPermukaanLimasSegiTiga[row],volumeLimasSegiTiga[row]
                });
                j+=2;
                k++;
                l++;
                index++;
                row++;
                Thread.sleep(200);
            }
             //MENUTUP FILE
            fileRAFData.close();
            fileRAFLuasPermukaanLimas.close();
            fileRAFVolumeLimas.close();
            System.out.println("--------------------------END PROCESS OF LIMAS 3--------------------------");
        } catch (ArithmeticException arithmeticException) {
            JOptionPane.showMessageDialog(null, "Terdapat Pembagian Dengan Bilangan (NOL)!!!");
        } catch(Exception exception){
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
        catch(Throwable throwable){
            JOptionPane.showMessageDialog(null, throwable.getMessage());
        }
        
    }
    
}