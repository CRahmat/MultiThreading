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
public class PrismaSegitiga extends Segitiga implements Runnable{
    OutputView outputView;
    RandomAccessFile fileRAFData = null;
    RandomAccessFile fileRAFLuasPermukaanPrisma = null;
    RandomAccessFile fileRAFVolumePrisma = null;
    RandomAccessFile RAFLenght = null;
    private int dataLenght;
    private Integer[] luasPermukaanPrisma;
    protected Integer[] volumePrisma;
    private Integer[] tinggi;
    private Integer[] panjang;
    private int data;
    int j;
    int k;
    int l;
    int index;
    int row;
    public PrismaSegitiga(OutputView outputView) { //Constructor dari Perisma Dengan Parameter OutputView
        super(outputView);                         //Mengirimkan Parameter Output View ke Super Class
        this.outputView = outputView;              //Digunakan Untuk Input Hasil Perhitungan Ke View
    }
    //Override method run dikarenakan extends Threads
    @Override
        //Badan Threads 
    public void run(){
        try {
        Thread.sleep(13000);
        hitungVolume();
        } catch (InterruptedException ex) {//Exception Threads Terinterupsi
         JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    public synchronized void hitungVolume(){
        
        try {
            //Instansiasi Obyek Random Access File
            fileRAFData = new RandomAccessFile("src\\saveData\\Data-Bangun.dat", "rw");
            fileRAFVolumePrisma = new RandomAccessFile("src\\saveData\\3D\\Volume-Prisma-Segitiga.dat", "rw");
            fileRAFLuasPermukaanPrisma = new RandomAccessFile("src\\saveData\\3D\\Luas-Permukaan-Prisma-Segitiga.dat", "rw");
            RAFLenght = new RandomAccessFile("src\\saveData\\Data-Lenght.dat", "rw");
            
            RAFLenght.seek(0);//File Selalu Berada Pada File Pointer 0 (Hanya 1 Data)
            dataLenght = RAFLenght.readInt();//Baca Lenght Dari File
            RAFLenght.close();//Close File Lenght    
            //Instansiasi Obyek untuk Perhitungan
            tinggi = new Integer[dataLenght];
            panjang = new Integer[dataLenght];
            luasPermukaanPrisma = new Integer[dataLenght];
            volumePrisma= new Integer[dataLenght];
            //SetLenght and Get Pointer dari File
            j = (int) fileRAFData.getFilePointer();
            fileRAFData.setLength(dataLenght);
            //SetLenght and Get Pointer dari File
            k = (int) fileRAFVolumePrisma.getFilePointer();
            fileRAFVolumePrisma.setLength(dataLenght);
            //SetLenght and Get Pointer dari File
            l = (int) fileRAFLuasPermukaanPrisma.getFilePointer();
            fileRAFLuasPermukaanPrisma.setLength(dataLenght);
            index = 0;
            row = 0;
            while (j < fileRAFData.length()){
                fileRAFData.seek(j);//Penyesesuaian Pointer untuk Baca File
                data = fileRAFData.read();//Membaca Panjang Prisma Dari File
                panjang[index] = data;
                j+=6;
                fileRAFData.seek(j);//Penyesesuaian Pointer untuk Baca File
                data = fileRAFData.read();//Membaca Tinggi Prisma Dari File
                tinggi[index] = data;
                //PERHITUNGAN LUAS PERMUKAAN DAN VOLUME
                volumePrisma[row] = (int)(Segitiga.luasSegitiga[index]*tinggi[index] * 0.333333333);
                luasPermukaanPrisma[row] = (int)((Segitiga.luasSegitiga[index]*panjang[index]) + (4*Segitiga.alas[index]*tinggi[index]));
                //Menulis Hasil Perhitungan Ke File
                fileRAFVolumePrisma.seek(k);//Penyesesuaian Pointer agar Data Tidak Tertimpa
                fileRAFVolumePrisma.write(volumePrisma[row]);//Menuliskan Hasil Perhitungan Volume Ke File
                fileRAFLuasPermukaanPrisma.seek(l);//Penyesesuaian Pointer agar Data Tidak Tertimpa
                fileRAFLuasPermukaanPrisma.write(luasPermukaanPrisma[row]);//Menuliskan Hasil Perhitungan Luas Ke File
                //OUTPUT KE MONITOR TANPA SWING
                System.out.print("Tinggi Prisma                 " + fileRAFData.getFilePointer() + ": ");
                System.out.println(tinggi[index] + "; ");
                System.out.print("Panjang Prisma                " + fileRAFData.getFilePointer() + ": ");
                System.out.println(panjang[index] + "; ");
                System.out.println("Volume Limas Segi Tiga      : "+volumePrisma[row]);
                System.out.println("L.Permukaan Limas Segi Tiga : "+luasPermukaanPrisma[row]);
                //INPUT KE JTABLE
                outputView.tableVolumePrisma.insertRow(outputView.tableVolumePrisma.getRowCount(), new Object[]{
                luasPermukaanPrisma[row],volumePrisma[row]
                });
                j+=2;
                k++;
                l++;
                index++;
                row++;
            }
             //MENUTUP FILE
            fileRAFData.close();
            fileRAFLuasPermukaanPrisma.close();
            fileRAFVolumePrisma.close();
            System.out.println("--------------------------END PROCESS OF PRISMA 3--------------------------");
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
