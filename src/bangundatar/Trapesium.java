package bangundatar;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import output.OutputView;

public class Trapesium extends Thread{
        OutputView outputView;
    protected int dataLenght;
    protected static Integer[] luasTrapesium;
    protected static Integer[] kelilingTrapesium;
    protected Integer[] panjang1;
    protected Integer[] panjang2;
    protected Integer[] tinggi;
    protected int sisiMiring;
    private int data;
    int j;
    int k;
    int l;
    int index;
    int row;
    RandomAccessFile fileRAFData = null;
    RandomAccessFile fileRAFLuasTrapesium = null;
    RandomAccessFile fileRAFKelilingTrapesium = null;
    RandomAccessFile RAFLenght = null;
    public Trapesium(OutputView outputView){//Constructor dari Trapesium Dengan Parameter OutputView 
       this.outputView = outputView;        //Digunakan Untuk Input Hasil Perhitungan Ke View
    }
    //Override method run dikarenakan extends Threads
    @Override
        //Badan Threads
    public void run() {
        try {
            Thread.sleep(6000);
            hitungLuas();
        } catch (InterruptedException ex) {//Exception Threads Terinterupsi
            JOptionPane.showMessageDialog(null, ex.getMessage());                   }
    }
    public synchronized void hitungLuas(){
        try {
            fileRAFData = new RandomAccessFile("src\\saveData\\Data-Bangun.dat", "rw");
            fileRAFLuasTrapesium = new RandomAccessFile("src\\saveData\\2D\\Luas-Trapesium.dat", "rw");
            fileRAFKelilingTrapesium = new RandomAccessFile("src\\saveData\\2D\\Keliling-Trapesium.dat", "rw");
            RAFLenght = new RandomAccessFile("src\\saveData\\Data-Lenght.dat", "rw");
            //Baca File Data Lenght
            RAFLenght.seek(0);//File Selalu Berada Pada File Pointer 0 (Hanya 1 Data)
            dataLenght = RAFLenght.readInt();//Baca Lenght Dari File
            RAFLenght.close();   

            panjang1 = new Integer[dataLenght];
            panjang2 = new Integer[dataLenght];
            tinggi = new Integer[dataLenght];
            luasTrapesium = new Integer[dataLenght];
            kelilingTrapesium = new Integer[dataLenght];
            //Perhitungan Luas Dan Keliling
            j = (int) fileRAFData.getFilePointer();
            fileRAFData.setLength(dataLenght);
            //Perhitungan Luas Dan Keliling
            k = (int) fileRAFLuasTrapesium.getFilePointer();
            fileRAFLuasTrapesium.setLength(dataLenght);
            //Perhitungan Luas Dan Keliling
            l = (int) fileRAFKelilingTrapesium.getFilePointer();
            fileRAFKelilingTrapesium.setLength(dataLenght);
            index = 0;
            row = 0;
            while (j < fileRAFData.length()){
                fileRAFData.seek(j);//Penyesesuaian Pointer
                data = fileRAFData.read();//Membaca Data Panjang1 Dari File
                panjang1[index] = data;
                j++;
                fileRAFData.seek(j);//Penyesesuaian Pointer
                data = fileRAFData.read();//Membaca Data Panjang2 Dari File
                panjang2[index] = data;
                j+=5;
                fileRAFData.seek(j);//Penyesesuaian Pointer
                data = fileRAFData.read();//Membaca Data Tinggi Dari File
                tinggi[index] = data;
                
                luasTrapesium[row] = ((int) ((panjang1[index] + panjang2[index]) * tinggi[index] / 2));
                sisiMiring = 0;
                sisiMiring = (int) Math.sqrt((int)((int) Math.pow(((panjang1[index] - panjang2[index])/2), 2)+ (Math.pow(tinggi[index], 2))));
                kelilingTrapesium[row] = ((int) ((2 * sisiMiring) + panjang1[index] + panjang2[index]));
                //Input Ke JTable Output View
                outputView.tableLuasTrapesium.insertRow(outputView.tableLuasTrapesium.getRowCount(), new Object[]{
                kelilingTrapesium[row],luasTrapesium[row]
                });
                //OUTPUT
                System.out.print("Panjnag 1 " + fileRAFData.getFilePointer() + ": ");
                System.out.println(panjang1[index] + "; ");
                System.out.print("Panjang 2 " + fileRAFData.getFilePointer() + ": ");
                System.out.println(panjang2[index] + "; ");
                System.out.print("Tinggi    " + fileRAFData.getFilePointer() + ": ");
                System.out.println(tinggi[index] + "; ");
                System.out.println("Luas Trapesium     : "+luasTrapesium[row]);
                System.out.println("Keliling Trapesium : "+kelilingTrapesium[row]);
                fileRAFLuasTrapesium.seek(k);//Penempatan Pointer Agar Data Tidak Tertimpa
                fileRAFLuasTrapesium.write(luasTrapesium[row]);//Menulis Hasil Perhitungan Luas Ke File
                fileRAFKelilingTrapesium.seek(l);//Penempatan Pointer Agar Data Tidak Tertimpa
                fileRAFKelilingTrapesium.write(luasTrapesium[row]);//Menulis Hasil Perhitungan Keliling Ke File
                j+=2;
                k++;
                l++;
                index++;
                row++;
            }
            //Menutup File
            fileRAFData.close();
            fileRAFLuasTrapesium.close();
            fileRAFKelilingTrapesium.close();
            System.out.println("-----------------------END PROCESS OF PERSEGI TRAPESIUM----------------------");

        } catch (ArithmeticException arithmeticException) {
            JOptionPane.showMessageDialog(null, "Terdapat Pembagian Dengan Bilangan (NOL)!!!");
        } catch(Throwable throwable){
            JOptionPane.showMessageDialog(null, throwable.getMessage());
        }
    }
    
}
