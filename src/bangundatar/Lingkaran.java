package bangundatar;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import output.OutputView;

public class Lingkaran extends Thread {
    OutputView outputView;
    protected int dataLenght;
    protected static Integer[] luasLingkaran;
    protected static Integer[] kelilingLingkaran;
    protected static Integer[] jarijari;
    private int data;
    int j;
    int k;
    int l;
    int index;
    int row;
    RandomAccessFile fileRAFData = null;
    RandomAccessFile fileRAFLuasLingkaran = null;
    RandomAccessFile fileRAFKelilingLingkaran = null;
    RandomAccessFile RAFLenght = null;
    
    public Lingkaran(OutputView outputView){//Constructor dari Lingkaran Dengan Parameter OutputView 
       this.outputView = outputView;        //Digunakan Untuk Input Hasil Perhitungan Ke View
    }
    //Override method run dikarenakan extends Threads
    @Override
        //Badan Threads
    public void run() {
        try {
            Thread.sleep(3000);
            hitungLuas();
        } catch (InterruptedException ex) {//Exception Threads Terinterupsi
            JOptionPane.showMessageDialog(null, ex.getMessage());        
        }
    }
    public synchronized void hitungLuas(){
                try {
            //Instansiasi Obyek Random Access File
            fileRAFData = new RandomAccessFile("src\\saveData\\Data-Bangun.dat", "rw");
            fileRAFLuasLingkaran = new RandomAccessFile("src\\saveData\\2D\\Luas-Lingkaran.dat", "rw");
            fileRAFKelilingLingkaran = new RandomAccessFile("src\\saveData\\2D\\Keliling-Lingkaran.dat", "rw");
            RAFLenght = new RandomAccessFile("src\\saveData\\Data-Lenght.dat", "rw");

            RAFLenght.seek(0);//File Selalu Berada Pada File Pointer 0 (Hanya 1 Data)
            dataLenght = RAFLenght.readInt();//Baca Lenght Dari File
            RAFLenght.close();//Close File Lenght 
            //Instansiasi Obyek Untuk Perhitungan Luas
            jarijari = new Integer[dataLenght];
            luasLingkaran = new Integer[dataLenght];
            kelilingLingkaran = new Integer[dataLenght];
            //SetLenght and Get Pointer dari File
            j = (int) fileRAFData.getFilePointer();
            fileRAFData.setLength(dataLenght);
            //SetLenght and Get Pointer dari File
            k = (int) fileRAFLuasLingkaran.getFilePointer();
            fileRAFLuasLingkaran.setLength(dataLenght);
            //SetLenght and Get Pointer dari File
            l = (int) fileRAFKelilingLingkaran.getFilePointer();
            fileRAFKelilingLingkaran.setLength(dataLenght);
            fileRAFKelilingLingkaran.setLength(dataLenght);
            index = 0;
            row = 0;
            while (j < fileRAFData.length()){
                j+=7;
                fileRAFData.seek(j);//Penyesesuaian Pointer
                data = fileRAFData.read();//Membaca Data Jari Jari Dari File
                jarijari[index] = data;
                //Perhitungan Luas Dan Keliling
                luasLingkaran[row] = ((int)(7 * jarijari[index] * jarijari[index]/22)) ;
                kelilingLingkaran[row] = (int) (3.14*jarijari[index]*2);
                //Input Ke JTable Output View
                outputView.tableLuasLingkaran.insertRow(outputView.tableLuasLingkaran.getRowCount(), new Object[]{
                kelilingLingkaran[row],luasLingkaran[row]
                });
                //OUTPUT
                System.out.print("Jari Jari " + fileRAFData.getFilePointer() + ": ");
                System.out.println(jarijari[index] + "; ");
                System.out.println("Luas Lingkaran     : "+luasLingkaran[row]);
                System.out.println("Keliling Lingkaran : "+kelilingLingkaran[row]);
                fileRAFLuasLingkaran.seek(k);//Penyesesuaian Pointer
                fileRAFLuasLingkaran.write(luasLingkaran[row]);//Menulis Hasil Luas Ke File
                fileRAFKelilingLingkaran.seek(l);//Penyesesuaian Pointer
                fileRAFKelilingLingkaran.write(kelilingLingkaran[row]);//Menulis Hasil Keliling Ke File
                j+=1;
                k++;
                l++;
                index++;
                row++;
            }
            //Menutup File
            fileRAFData.close();
            fileRAFLuasLingkaran.close();
            fileRAFKelilingLingkaran.close();
            System.out.println("-------------------------END PROCESS OF LINGKARAN------------------------");

        } catch (ArithmeticException arithmeticException) {
            JOptionPane.showMessageDialog(null, "Terdapat Pembagian Dengan Bilangan (NOL)!!!");
        } catch(Throwable throwable){
            JOptionPane.showMessageDialog(null, throwable.getMessage());
        }
    }
    
}
