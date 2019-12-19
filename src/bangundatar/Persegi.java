package bangundatar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import output.OutputView;

public class Persegi extends Thread{
    OutputView outputView;
    RandomAccessFile fileRAFData = null;
    RandomAccessFile fileRAFLuasPersegi = null;
    RandomAccessFile fileRAFKelilingPersegi = null;
    RandomAccessFile RAFLenght = null;
    protected int dataLenght;
    protected static Integer[] luasPersegi;
    protected static Integer[] kelilingPersegi;
    protected Integer[] sisi;
    private int data;
    int j;
    int k;
    int l;
    int index;
    int row;
    public Persegi(OutputView outputView){//Constructor dari Persegi Dengan Parameter OutputView 
       this.outputView = outputView;      //Digunakan Untuk Input Hasil Perhitungan Ke View
    }
    //Override method run dikarenakan extends Threads
    @Override
        //Badan Threads
    public void run() {
        hitungLuas();
    }
    public synchronized void hitungLuas(){
        try {
            //Instansiasi Obyek Random Access File
            fileRAFData = new RandomAccessFile("src\\saveData\\Data-Bangun.dat", "rw");
            fileRAFLuasPersegi = new RandomAccessFile("src\\saveData\\2D\\Luas-Persegi.dat", "rw");
            fileRAFKelilingPersegi = new RandomAccessFile("src\\saveData\\2D\\Keliling-Persegi.dat", "rw");
            RAFLenght = new RandomAccessFile("src\\saveData\\Data-Lenght.dat", "rw");
            RAFLenght.seek(0);//File Selalu Berada Pada File Pointer 0 (Hanya 1 Data)
            dataLenght = RAFLenght.readInt();//Baca Lenght Dari File
            RAFLenght.close();   
            //Instansiasi Obyek Untuk Perhitungan
            sisi = new Integer[dataLenght];
            luasPersegi = new Integer[dataLenght];
            kelilingPersegi = new Integer[dataLenght];
            //SetLenght and Get Pointer dari File
            j = (int) fileRAFData.getFilePointer();
            fileRAFData.setLength(dataLenght);
            //SetLenght and Get Pointer dari File
            k = (int) fileRAFLuasPersegi.getFilePointer();
            fileRAFLuasPersegi.setLength(dataLenght);
            //SetLenght and Get Pointer dari File
            l = (int) fileRAFLuasPersegi.getFilePointer();
            //Setting Lenght dari File
            fileRAFLuasPersegi.setLength(dataLenght);
            fileRAFKelilingPersegi.setLength(dataLenght);
            index = 0;
            row = 0;
            while (j < fileRAFData.length()){
                fileRAFData.seek(j);
                data = fileRAFData.read();//Membaca Data Sisi dari File
                sisi[index] = data;
                //Perhitungan Luas Dan Keliling
                luasPersegi[row] = sisi[index] * sisi[index];
                kelilingPersegi[row] = 4 * sisi[index];
                //Output Hasil
                System.out.print("Sisi " + fileRAFData.getFilePointer() + ": ");
                System.out.println(sisi[index] + "; ");
                System.out.println("Luas Persegi     : "+luasPersegi[row]);
                System.out.println("Keliling Persegi : "+kelilingPersegi[row]);
                //Input Ke JTable Output View
                outputView.tableLuasPersegi.insertRow(outputView.tableLuasPersegi.getRowCount(), new Object[]{
                kelilingPersegi[row],luasPersegi[row]
                });//Input ke JTable
                fileRAFLuasPersegi.seek(k);//Penempatan Pointer Agar Data Tidak Tertimpa
                fileRAFLuasPersegi.write(luasPersegi[row]);//Proses Menulis data Luas Ke File
                fileRAFLuasPersegi.seek(l);//Penempatan Pointer Agar Data Tidak Tertimpa
                fileRAFLuasPersegi.write(kelilingPersegi[row]);//Proses Menulis Keliling data Ke File
                j+=8;
                k++;
                l++;
                index++;
                row++;
                Thread.sleep(100);
            }
            //Menutup File
            fileRAFData.close();//Close File Data
            fileRAFLuasPersegi.close();//Close File Luas Persegi
            fileRAFKelilingPersegi.close();//Close File Volume Persegi
            System.out.println("--------------------------END PROCESS OF PERSEGI--------------------------");
        } catch (ArithmeticException arithmeticException) {
            JOptionPane.showMessageDialog(null, "Terdapat Pembagian Dengan Bilangan (NOL)!!!");
        } catch(Throwable throwable){
            JOptionPane.showMessageDialog(null, throwable.getMessage());
        }
    }
    
}
