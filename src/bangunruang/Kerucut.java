/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bangunruang;

import bangundatar.Lingkaran;
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
public class Kerucut extends Lingkaran implements Runnable{
    OutputView outputView;
    RandomAccessFile fileRAFData = null;
    RandomAccessFile fileRAFLuasPermukaanKerucut = null;
    RandomAccessFile fileRAFVolumeKerucut = null;
    RandomAccessFile RAFLenght = null;
    private int dataLenght;
    private Integer[] luasPermukaanKerucut;
    protected Integer[] volumeKerucut;
    private Integer[] tinggi;
    protected Integer[] panjangSelimut;
    private int data;
    int j;
    int k;
    int l;
    int index;
    int row;
    public Kerucut(OutputView outputView) {//Constructor dari Kerucut Dengan Parameter OutputView
        super(outputView);                 //Mengirimkan Parameter Output View ke Super Class
        this.outputView = outputView;      //Digunakan Untuk Input Hasil Perhitungan Ke View
    }
    //Override method run dikarenakan extends Threads
    @Override
        //Badan Threads
    public void run(){
        try {
        Thread.sleep(11000);
        hitungVolume();
        } catch (InterruptedException ex) {//Exception Threads Terinterupsi
        JOptionPane.showMessageDialog(null, ex.getMessage());        
        }
    }
    public synchronized void hitungVolume(){
        
        try {
            //Instansiasi Obyek Random Access File
            fileRAFData = new RandomAccessFile("src\\saveData\\Data-Bangun.dat", "rw");
            fileRAFLuasPermukaanKerucut = new RandomAccessFile("src\\saveData\\3D\\Luas-Permukaan-Kerucut.dat", "rw");
            fileRAFVolumeKerucut = new RandomAccessFile("src\\saveData\\3D\\Volume-Kerucut.dat", "rw");
            RAFLenght = new RandomAccessFile("src\\saveData\\Data-Lenght.dat", "rw");
            
            RAFLenght.seek(0);//File Selalu Berada Pada File Pointer 0 (Hanya 1 Data)
            dataLenght = RAFLenght.readInt();//Baca Lenght Dari File
            RAFLenght.close();//Close File Lenght         
            //Instansiasi Obyek untuk Perhitungan
            tinggi = new Integer[dataLenght];
            panjangSelimut = new Integer[dataLenght];
            volumeKerucut = new Integer[dataLenght];
            luasPermukaanKerucut = new Integer[dataLenght];
            //SetLenght and Get Pointer dari File
            j = (int) fileRAFData.getFilePointer();
            fileRAFData.setLength(dataLenght);
            //SetLenght and Get Pointer dari File
            k = (int) fileRAFVolumeKerucut.getFilePointer();
            fileRAFVolumeKerucut.setLength(dataLenght);
            //SetLenght and Get Pointer dari File
            l = (int) fileRAFLuasPermukaanKerucut.getFilePointer();
            fileRAFLuasPermukaanKerucut.setLength(dataLenght);
            
            index = 0;
            row = 0;
            while (j < fileRAFData.length()){
                fileRAFData.seek(j);
                data = fileRAFData.read();
                panjangSelimut[index] = data;
                
                j+=6;
                fileRAFData.seek(j);//Penyesesuaian Pointer untuk Baca File
                data = fileRAFData.read();//Membaca Tinggi Dari File
                tinggi[index] = data;
                //PERHITUNGAN LUAS PERMUKAAN DAN VOLUME
                volumeKerucut[row] = (int)(Lingkaran.luasLingkaran[index]*tinggi[index] * (0.333333333));
                luasPermukaanKerucut[row] = (int)(Lingkaran.luasLingkaran[index] + (3.14 * Lingkaran.jarijari[index] * panjangSelimut[index]));
                //Menulis Hasil Perhitungan Ke File
                fileRAFVolumeKerucut.seek(k);//Penyesesuaian Pointer agar Data Tidak Tertimpa
                fileRAFVolumeKerucut.write(volumeKerucut[row]);//Menuliskan Hasil Perhitungan Volume Ke File
                fileRAFLuasPermukaanKerucut.seek(l);//Penyesesuaian Pointer agar Data Tidak Tertimpa
                fileRAFLuasPermukaanKerucut.write(luasPermukaanKerucut[row]);//Menuliskan Hasil Perhitungan Luas Ke File
                //INPUT KE JTABLE
                outputView.tableVolumeKerucut.insertRow(outputView.tableVolumeKerucut.getRowCount(), new Object[]{
                luasPermukaanKerucut[row],volumeKerucut[row]
                });
                //OUTPUT KE MONITOR TANPA SWING
                System.out.print("Panjang Selimut Kerucut  " + fileRAFData.getFilePointer() + ": ");
                System.out.println(panjangSelimut[index] + "; ");
                System.out.print("Tinggi Kerucut           " + fileRAFData.getFilePointer() + ": ");
                System.out.println(tinggi[index] + "; ");
                System.out.println("Volume Kerucut         : "+volumeKerucut[row]);
                System.out.println("Luas Permukaan Kerucut : "+luasPermukaanKerucut[row]);
                j+=2;
                k++;
                l++;
                index++;
                row++;
            }
             //MENUTUP FILE
            fileRAFData.close();
            fileRAFLuasPermukaanKerucut.close();
            fileRAFVolumeKerucut.close();
            System.out.println("--------------------------END PROCESS OF KERUCUT--------------------------");
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
