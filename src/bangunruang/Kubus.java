/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bangunruang;

import bangundatar.Persegi;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import output.OutputView;

/**
 *
 * @author Catur Rahmat
 */
public class Kubus extends Persegi implements Runnable{
    RandomAccessFile fileRAFLuasPermukaan = null;
    RandomAccessFile fileRAFVolumeKubus = null;
    RandomAccessFile RAFLenght = null;
    OutputView outputView;
    private int dataLenght;
    private Integer[] luasPermukaanKubus;
    protected Integer[] volumeKubus;
    int j;
    int k;
    int l;
    int index;
    int row;
    public Kubus(OutputView outputView) { //Constructor dari Kubus Dengan Parameter OutputView
        super(outputView);                //Mengirimkan Parameter Output View ke Super Class
        this.outputView = outputView;     //Digunakan Untuk Input Hasil Perhitungan Ke View
    }
    //Override method run dikarenakan extends Threads
    @Override
        //Badan Threads
    public void run(){
        try {
        Thread.sleep(2000);
        hitungVolume();
        } catch (InterruptedException ex) {//Exception Threads Terinterupsi
        JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    public synchronized void hitungVolume(){
        
        try {
            //Instansiasi Obyek Random Access File
            fileRAFLuasPermukaan = new RandomAccessFile("src\\saveData\\3D\\Luas-Permukaaan-Kubus.dat", "rw");
            fileRAFVolumeKubus = new RandomAccessFile("src\\saveData\\3D\\Volume-Permukaaan-Kubus.dat", "rw");
            RAFLenght = new RandomAccessFile("src\\saveData\\Data-Lenght.dat", "rw");
            
            RAFLenght.seek(0);//File Selalu Berada Pada File Pointer 0 (Hanya 1 Data)
            dataLenght = RAFLenght.readInt();//Baca Lenght Dari File
            RAFLenght.close();//Close File Lenght    
            //Instansiasi Obyek untuk Perhitungan
            luasPermukaanKubus = new Integer[dataLenght];
            volumeKubus = new Integer[dataLenght];
            //SetLenght and Get Pointer dari File
            k = (int) fileRAFVolumeKubus.getFilePointer();
            fileRAFVolumeKubus.setLength(dataLenght);
            //SetLenght and Get Pointer dari File
            l = (int) fileRAFLuasPermukaan.getFilePointer();
            fileRAFLuasPermukaan.setLength(dataLenght);
            
            index = 0;
            row = 0;
            j = 0;
            while (j < dataLenght){
                //PERHITUNGAN LUAS PERMUKAAN DAN VOLUME
                luasPermukaanKubus[row] = (int)(6 * Persegi.luasPersegi[index]);
                volumeKubus[row] = (int)(Persegi.luasPersegi[index]* Persegi.sisi[index]);
                //Menulis Hasil Perhitungan Ke File
                fileRAFVolumeKubus.seek(k);//Penyesesuaian Pointer agar Data Tidak Tertimpa
                fileRAFVolumeKubus.write(volumeKubus[row]);//Menuliskan Hasil Perhitungan Volume Ke File
                fileRAFLuasPermukaan.seek(l);//Penyesesuaian Pointer agar Data Tidak Tertimpa
                fileRAFLuasPermukaan.write(luasPermukaanKubus[row]);//Menuliskan Hasil Perhitungan Luas Ke File
                //INPUT KE JTABLE
                outputView.tableVolumeKubus.insertRow(outputView.tableVolumeKubus.getRowCount(), new Object[]{
                luasPermukaanKubus[row],volumeKubus[row]
                });
                //OUTPUT KE MONITOR TANPA SWING
                System.out.print("Sisi Kubus          :" );
                System.out.println(super.sisi[index] + "; ");
                System.out.println("Volume Kubus      : "+volumeKubus[row]);
                System.out.println("L.Permukaan Kubus : "+luasPermukaanKubus[row]);
                j+=8;
                k++;
                l++;
                index++;
                row++;
                Thread.sleep(200);
            }
             //MENUTUP FILE
            fileRAFLuasPermukaan.close();
            fileRAFVolumeKubus.close();
            System.out.println("--------------------------END PROCESS OF KUBUS--------------------------");
        } catch (ArithmeticException arithmeticException) {
            JOptionPane.showMessageDialog(null, "Terdapat Pembagian Dengan Bilangan (NOL)!!!");
        }catch(Exception exception){
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
        catch(Throwable throwable){
            JOptionPane.showMessageDialog(null, throwable.getMessage());
        }
        
    }
   
}