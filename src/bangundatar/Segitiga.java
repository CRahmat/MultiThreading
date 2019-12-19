package bangundatar;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import output.OutputView;

public class Segitiga extends Thread {
    RandomAccessFile fileRAFData = null;
    RandomAccessFile fileRAFLuasSegitiga = null;
    RandomAccessFile fileRAFKelilingSegitiga = null;
    RandomAccessFile RAFLenght = null;
    OutputView outputView;
    protected int dataLenght;
    protected static Integer[] luasSegitiga;
    protected static Integer[] kelilingSegitiga;
    protected Integer[] alas;
    protected Integer[] tinggi;
    private int data;
    int j;
    int k;
    int l;
    int index;
    int row;
    public Segitiga(OutputView outputView){//Constructor dari Segitiga Dengan Parameter OutputView 
       this.outputView = outputView;       //Digunakan Untuk Input Hasil Perhitungan Ke View
    }
    //Override method run dikarenakan extends Threads
    @Override
        //Badan Threads
    public void run() {
        try {
            Thread.sleep(4000);
            hitungLuas();
        } catch (InterruptedException ex) {//Exception Threads Terinterupsi
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    public synchronized void hitungLuas(){
        try {
            fileRAFData = new RandomAccessFile("src\\saveData\\Data-Bangun.dat", "rw");
            fileRAFLuasSegitiga = new RandomAccessFile("src\\saveData\\2D\\Luas-Segitiga.dat", "rw");
            fileRAFKelilingSegitiga = new RandomAccessFile("src\\saveData\\2D\\Keliling-Segitiga.dat", "rw");
            RAFLenght = new RandomAccessFile("src\\saveData\\Data-Lenght.dat", "rw");

            RAFLenght.seek(0);//File Selalu Berada Pada File Pointer 0 (Hanya 1 Data)
            dataLenght = RAFLenght.readInt();//Baca Lenght Dari File
            RAFLenght.close();   
            alas = new Integer[dataLenght];
            tinggi = new Integer[dataLenght];
            //Perhitungan Luas Dan Keliling
            luasSegitiga = new Integer[dataLenght];
            kelilingSegitiga = new Integer[dataLenght];
            //SetLenght and Get Pointer dari File
            j = (int) fileRAFData.getFilePointer();
            fileRAFData.setLength(dataLenght);
            //SetLenght and Get Pointer dari File
            k = (int) fileRAFLuasSegitiga.getFilePointer();
            fileRAFLuasSegitiga.setLength(dataLenght);
            //SetLenght and Get Pointer dari File
            l = (int) fileRAFKelilingSegitiga.getFilePointer();
            fileRAFKelilingSegitiga.setLength(dataLenght);
            index = 0;
            row = 0;
            while (j < fileRAFData.length()){
                fileRAFData.seek(j);//Penyesesuaian Pointer
                data = fileRAFData.read();//Membaca Data Alas Dari File
                alas[index] = data;
                j+=6;
                fileRAFData.seek(j);//Penyesesuaian Pointer
                data = fileRAFData.read();//Membaca Data Tinggi Dari File
                tinggi[index] = data;
                luasSegitiga[row] = (int)(alas[index] * tinggi[index] / 2);
                kelilingSegitiga[row] = (int)(2 * (Math.sqrt(((Math.pow((alas[index]/2), 2))+(Math.pow(tinggi[index], 2))))) + alas[index]);
                //Input Ke JTable Output View
                outputView.tableLuasSegitiga.insertRow(outputView.tableLuasSegitiga.getRowCount(), new Object[]{
                kelilingSegitiga[row],luasSegitiga[row]
                });
                //OUTPUT
                System.out.print("Pointer File " + fileRAFData.getFilePointer() + "= ");
                System.out.println(alas[index] + "; ");
                System.out.print("Pointer File " + fileRAFData.getFilePointer() + "= ");
                System.out.println(tinggi[index] + "; ");
                System.out.println("Luas Segitiga     : "+luasSegitiga[row]);
                System.out.println("Keliling Segitiga : "+kelilingSegitiga[row]);
                fileRAFLuasSegitiga.seek(k);//Penempatan Pointer Agar Data Tidak Tertimpa
                fileRAFLuasSegitiga.write(luasSegitiga[row]);//Input Hasil Luas Segitiga Ke File
                fileRAFKelilingSegitiga.seek(l);//Penempatan Pointer Agar Data Tidak Tertimpa
                fileRAFKelilingSegitiga.write(kelilingSegitiga[row]);//Input Hasil Keliling Segitiga Ke File
                j+=2;
                k++;
                l++;
                index++;
                row++;
                Thread.sleep(500);
            }
            fileRAFData.close();
            fileRAFLuasSegitiga.close();
            fileRAFKelilingSegitiga.close();
            System.out.println("-------------------------END PROCESS OF SEGITIGA------------------------");
            
        } catch (ArithmeticException arithmeticException) {
            JOptionPane.showMessageDialog(null, "Terdapat Pembagian Dengan Bilangan (NOL)!!!");
        } catch(Throwable throwable){
            JOptionPane.showMessageDialog(null, throwable.getMessage());
        }
    }
    
}
