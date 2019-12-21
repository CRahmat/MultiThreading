/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bangunruang;

import bangundatar.PersegiPanjang;
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
public class Balok extends PersegiPanjang implements Runnable{
    OutputView outputView;
    RandomAccessFile fileRAFData = null;
    RandomAccessFile fileRAFLPermukaanBalok = null;
    RandomAccessFile fileRAFVolumeBalok = null;
    RandomAccessFile RAFLenght = null;
    private int dataLenght;
    private Integer[] luasPermukaanBalok;
    protected Integer[] volumeBalok;
    protected Integer[] tinggi;
    private int data;
    int j;
    int k;
    int l;
    int index;
    int row;
    public Balok(OutputView outputView) {//Constructor dari Balok Dengan Parameter OutputView 
        super(outputView); //Mengirimkan Parameter Output View ke Super Class
        this.outputView = outputView;    //Digunakan Untuk Input Hasil Perhitungan Ke View
    }
    //Override method run dikarenakan extends Threads
    @Override
        //Badan Threads
    public void run(){
        try {
        Thread.sleep(3000);
        hitungVolume();
        } catch (InterruptedException ex) {//Exception Threads Terinterupsi
        JOptionPane.showMessageDialog(null, ex.getMessage());        
        }
    }
    public synchronized void hitungVolume(){
        try {
            //Instansiasi Obyek Random Access File
            fileRAFData = new RandomAccessFile("src\\saveData\\Data-Bangun.dat", "rw");
            fileRAFLPermukaanBalok = new RandomAccessFile("src\\saveData\\3D\\Luas-Permukaan-Balok.dat", "rw");
            fileRAFVolumeBalok = new RandomAccessFile("src\\saveData\\3D\\Volume-Balok.dat", "rw");
            RAFLenght = new RandomAccessFile("src\\saveData\\Data-Lenght.dat", "rw");
            
            RAFLenght.seek(0);//File Selalu Berada Pada File Pointer 0 (Hanya 1 Data)
            dataLenght = RAFLenght.readInt();//Baca Lenght Dari File
            RAFLenght.close();//Close File Lenght    
            //Instansiasi Obyek untuk Perhitungan
            tinggi = new Integer[dataLenght];
            volumeBalok = new Integer[dataLenght];
            luasPermukaanBalok = new Integer[dataLenght];
            //SetLenght and Get Pointer dari File
            j = (int) fileRAFData.getFilePointer();
            fileRAFData.setLength(dataLenght);
            //SetLenght and Get Pointer dari File
            k = (int) fileRAFVolumeBalok.getFilePointer();
            fileRAFVolumeBalok.setLength(dataLenght);
            //SetLenght and Get Pointer dari File
            l = (int) fileRAFLPermukaanBalok.getFilePointer();
            fileRAFLPermukaanBalok.setLength(dataLenght);
            
            index = 0;
            row = 0;
            while (j < fileRAFData.length()){
                j+=6;
                fileRAFData.seek(j);//Penyesesuaian Pointer untuk Baca File
                data = fileRAFData.read();//Membaca Tinggi dari File
                tinggi[index] = data;
                //PERHITUNGAN LUAS PERMUKAAN DAN VOLUME
                volumeBalok[row] = PersegiPanjang.luasPersegiPanjang[index]*tinggi[index];
                luasPermukaanBalok[row] = (PersegiPanjang.luasPersegiPanjang[index]*4)+(PersegiPanjang.lebar[index] * tinggi[index] * 2);
                //Penyesesuaian Pointer untuk Baca File
                fileRAFVolumeBalok.seek(k);//Penyesesuaian Pointer agar Data Tidak Tertimpa
                fileRAFVolumeBalok.writeInt(volumeBalok[row]);//Menuliskan Hasil Perhitungan Volume Ke File
                fileRAFLPermukaanBalok.seek(l);//Penyesesuaian Pointer agar Data Tidak Tertimpa
                fileRAFLPermukaanBalok.writeInt(luasPermukaanBalok[row]);//Menuliskan Hasil Perhitungan Luas Ke File
                //OUTPUT KE MONITOR TANPA SWING
                System.out.print("Tinggi Balok         " + fileRAFData.getFilePointer() + ": ");
                System.out.println(tinggi[index] + "; ");
                System.out.println("Volume Balok      : "+volumeBalok[row]);
                System.out.println("L.Permukaan Balok : "+luasPermukaanBalok[row]);
                //INPUT KE JTABLE
                outputView.tableVolumeBalok.insertRow(outputView.tableVolumeBalok.getRowCount(), new Object[]{
                luasPermukaanBalok[row],volumeBalok[row]
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
            fileRAFLPermukaanBalok.close();
            fileRAFVolumeBalok.close();
            System.out.println("--------------------------END PROCESS OF BALOK--------------------------");
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
