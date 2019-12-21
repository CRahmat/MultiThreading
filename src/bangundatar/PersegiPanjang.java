package bangundatar;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import output.OutputView;

public class PersegiPanjang extends Thread{
    OutputView outputView;
    RandomAccessFile fileRAFData = null;
    RandomAccessFile fileRAFKelilingPersegiPanjang = null;
    RandomAccessFile fileRAFLuasPersegiPanjang = null;
    RandomAccessFile RAFLenght = null;
    protected int dataLenght;
    protected static Integer[] luasPersegiPanjang;
    protected static Integer[] kelilingPersegiPanjang;
    protected static Integer[] panjang;
    protected static Integer[] lebar;
    private int data;
    int j;
    int k;
    int l;
    int index;
    int row;
    
    public PersegiPanjang(OutputView outputView){//Constructor dari Persegi Panjang Dengan Parameter OutputView 
       this.outputView = outputView;           //Digunakan Untuk Input Hasil Perhitungan Ke View
    }
    //Override method run dikarenakan extends Threads
    @Override
        //Badan Threads
    public void run() {
        try {
            Thread.sleep(1000);
            hitungLuas();
        } catch (InterruptedException ex) {//Exception Threads Terinterupsi
           JOptionPane.showMessageDialog(null, ex.getMessage());        
        }
    }
    public synchronized void hitungLuas(){

        try {
            fileRAFData = new RandomAccessFile("src\\saveData\\Data-Bangun.dat", "rw");
            fileRAFLuasPersegiPanjang = new RandomAccessFile("src\\saveData\\2D\\Luas-PersegiPanjang.dat", "rw");
            fileRAFKelilingPersegiPanjang = new RandomAccessFile("src\\saveData\\2D\\Luas-PersegiPanjang.dat", "rw");
            RAFLenght = new RandomAccessFile("src\\saveData\\Data-Lenght.dat", "rw");

            RAFLenght.seek(0);//File Selalu Berada Pada File Pointer 0 (Hanya 1 Data)
            dataLenght = RAFLenght.readInt();//Baca Lenght Dari File
            RAFLenght.close();   
            
            panjang = new Integer[dataLenght];
            lebar = new Integer[dataLenght];
            //Perhitungan Luas Dan Keliling
            luasPersegiPanjang = new Integer[dataLenght];
            kelilingPersegiPanjang = new Integer[dataLenght];
            //SetLenght and Get Pointer dari File
            j = (int) fileRAFData.getFilePointer();
            fileRAFData.setLength(dataLenght);
            //SetLenght and Get Pointer dari File
            k = (int) fileRAFLuasPersegiPanjang.getFilePointer();
            fileRAFLuasPersegiPanjang.setLength(dataLenght);
            //SetLenght and Get Pointer dari File
            l = (int) fileRAFKelilingPersegiPanjang.getFilePointer();
            fileRAFKelilingPersegiPanjang.setLength(dataLenght);
            index = 0;
            row = 0;
            while (j < fileRAFData.length()){
                fileRAFData.seek(j);//Penyesesuaian Pointer
                data = fileRAFData.read();//Membaca Data Panjang Dari File
                panjang[index] = data;
                j++;
                fileRAFData.seek(j);
                data = fileRAFData.read();//Membaca Data Lebar Dari File
                lebar[index] = data;
                
                luasPersegiPanjang[row] = panjang[index] * lebar[index];
                kelilingPersegiPanjang[row] = (2 * panjang[index]) + (2 * lebar[index]);
                //Input Ke JTable Output View
                outputView.tableLuasPersegiPanjang.insertRow(outputView.tableLuasPersegiPanjang.getRowCount(), new Object[]{
                kelilingPersegiPanjang[row],luasPersegiPanjang[row]
                });//Input Ke JTable
                //OUTPUT
                System.out.print("Panjang " + fileRAFData.getFilePointer() + ": ");
                System.out.println(panjang[index] + "; ");
                System.out.print("Lebar   " + fileRAFData.getFilePointer() + ": ");
                System.out.println(lebar[index] + "; ");
                System.out.println("Luas PersegiPanjang     : "+kelilingPersegiPanjang[row]);
                System.out.println("Keliling PersegiPanjang : "+kelilingPersegiPanjang[row]);
                
                fileRAFLuasPersegiPanjang.seek(k);//Penempatan Pointer Agar Data Tidak Tertimpa
                fileRAFLuasPersegiPanjang.write(luasPersegiPanjang[row]);//Menulis Hasil Luas Ke File
                fileRAFKelilingPersegiPanjang.seek(l);//Penempatan Pointer Agar Data Tidak Tertimpa
                fileRAFKelilingPersegiPanjang.write(kelilingPersegiPanjang[row]);//Menulis Hasil Volume Ke File
                j+=7;
                k++;
                l++;
                index++;
                row++;
                Thread.sleep(200);
            }
            fileRAFData.close();
            fileRAFLuasPersegiPanjang.close();
            fileRAFKelilingPersegiPanjang.close();
            System.out.println("-----------------------END PROCESS OF PERSEGI PANJANG----------------------");

        } catch (ArithmeticException arithmeticException) {
            JOptionPane.showMessageDialog(null, "Terdapat Pembagian Dengan Bilangan (NOL)!!!");
        } catch(Throwable throwable){
            JOptionPane.showMessageDialog(null,throwable.getMessage());
        }
    }
}
