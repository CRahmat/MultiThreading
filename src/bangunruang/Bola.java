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
public class Bola extends Lingkaran implements Runnable{
    OutputView outputView;
    RandomAccessFile fileRAFData = null;
    RandomAccessFile fileRAFLuasPermukaanBola = null;
    RandomAccessFile fileRAFVolumeBola = null;
    RandomAccessFile RAFLenght = null;
    private int dataLenght;
    protected Integer[] volumeBola;
    protected Integer[] luasPermukaanBola;
    private int data;
    int j;
    int k;
    int l;
    int index;
    int row;
    public Bola(OutputView outputView) {//Constructor Dari Bola dengan Parameter Output View
        super(outputView);             //Mengirimkan Parameter Output View ke Super Class
        this.outputView = outputView;   //Digunakan Untuk Input Hasil Perhitungan Ke View
    }
    //Override method run dikarenakan extends Threads
    @Override
        //Badan Threads
    public void run(){
        try {
        Thread.sleep(12000);
        hitungVolume();
        } catch (InterruptedException ex) {//Exception Threads Terinterupsi
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    public synchronized void hitungVolume(){
        
        try {
            //Instansiasi Obyek Random Access File
            fileRAFData = new RandomAccessFile("src\\saveData\\Data-Bangun.dat", "rw");
            fileRAFLuasPermukaanBola = new RandomAccessFile("src\\saveData\\3D\\Luas-Permukaan-Bola.dat", "rw");
            fileRAFVolumeBola = new RandomAccessFile("src\\saveData\\3D\\Volume-Bola.dat", "rw");
            RAFLenght = new RandomAccessFile("src\\saveData\\Data-Lenght.dat", "rw");
            
            RAFLenght.seek(0);//File Selalu Berada Pada File Pointer 0 (Hanya 1 Data)
            dataLenght = RAFLenght.readInt();//Baca Lenght Dari File
            RAFLenght.close();//Close File Lenght    
            //Instansiasi Obyek untuk Perhitungan
            volumeBola = new Integer[dataLenght];
            luasPermukaanBola = new Integer[dataLenght];
            //SetLenght and Get Pointer dari File
            j = (int) fileRAFData.getFilePointer();
            fileRAFData.setLength(dataLenght);
            //SetLenght and Get Pointer dari File
            k = (int) fileRAFVolumeBola.getFilePointer();
            fileRAFVolumeBola.setLength(dataLenght);
            //SetLenght and Get Pointer dari File
            l = (int) fileRAFLuasPermukaanBola.getFilePointer();
            fileRAFLuasPermukaanBola.setLength(dataLenght);
            index = 0;
            row = 0;
            while (j < fileRAFData.length()){
                //PERHITUNGAN LUAS PERMUKAAN DAN VOLUME
                volumeBola[row] = (int)(Lingkaran.luasLingkaran[index]*Lingkaran.jarijari[index] * (0.75));
                luasPermukaanBola[row] = (int)(Lingkaran.luasLingkaran[index]*4);
                //Menulis Hasil Perhitungan Ke File
                fileRAFVolumeBola.seek(k);//Penyesesuaian Pointer agar Data Tidak Tertimpa
                fileRAFVolumeBola.write(volumeBola[row]);//Menuliskan Hasil Perhitungan Volume Ke File
                fileRAFLuasPermukaanBola.seek(k);//Penyesesuaian Pointer agar Data Tidak Tertimpa
                fileRAFLuasPermukaanBola.write(luasPermukaanBola[row]);//Menuliskan Hasil Perhitungan Luas Ke File
                //INPUT KE JTABLE
                outputView.tableVolumeBola.insertRow(outputView.tableVolumeBola.getRowCount(), new Object[]{
                luasPermukaanBola[row],volumeBola[row]
                });
                //OUTPUT KE MONITOR TANPA SWING
                System.out.print("Jari Jari Lingkaran   : ");
                System.out.println(Lingkaran.jarijari[index] + "; ");
                System.out.println("Volume Bola         : "+volumeBola[row]);
                System.out.println("Luas Permukaan Bola : "+volumeBola[row]);
                j+=8;
                k++;
                l++;
                index++;
                row++;
            }
             //MENUTUP FILE
            fileRAFData.close();
            fileRAFLuasPermukaanBola.close();
            fileRAFVolumeBola.close();
            System.out.println("--------------------------END PROCESS OF BOLA--------------------------");
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
