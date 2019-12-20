package bangundatar;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import output.OutputView;

public class LayangLayang extends Thread {
        OutputView outputView;
    protected int dataLenght;
    protected static Integer[] luasLayangLayang;
    protected static Integer[] kelilingLayangLayang;
    protected Integer[] diagonal1;
    protected Integer[] diagonal2;
    private int data;
    int j;
    int k;
    int l;
    int index;
    int row;
    RandomAccessFile fileRAFData = null;
    RandomAccessFile fileRAFLuasLayangLayang = null;
    RandomAccessFile fileRAFKelilingLayangLayang = null;
    RandomAccessFile RAFLenght = null;
    
    public LayangLayang(OutputView outputView){//Constructor dari Layang Layang Dengan Parameter OutputView 
       this.outputView = outputView;           //Digunakan Untuk Input Hasil Perhitungan Ke View
    }
    //Override method run dikarenakan extends Threads
    @Override
        //Badan Threads
    public void run() {
        try {
            Thread.sleep(5000);
            hitungLuas();
        } catch (InterruptedException ex) {//Exception Threads Terinterupsi
           JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    public synchronized void hitungLuas(){
                try {
            //Instansiasi Obyek Random Access File
            fileRAFData = new RandomAccessFile("src\\saveData\\Data-Bangun.dat", "rw");
            fileRAFLuasLayangLayang = new RandomAccessFile("src\\saveData\\2D\\Luas-LayangLayang.dat", "rw");
            fileRAFKelilingLayangLayang = new RandomAccessFile("src\\saveData\\2D\\Keliling-LayangLayang.dat", "rw");
            RAFLenght = new RandomAccessFile("src\\saveData\\Data-Lenght.dat", "rw");

            RAFLenght.seek(0);//File Selalu Berada Pada File Pointer 0 (Hanya 1 Data)
            dataLenght = RAFLenght.readInt();//Baca Lenght Dari File
            RAFLenght.close();//Close File Lenght    
            //Instansiasi Obyek yang Di Gunakan Untuk Perhitungan
            diagonal1 = new Integer[dataLenght];
            diagonal2 = new Integer[dataLenght];
            luasLayangLayang = new Integer[dataLenght];
            kelilingLayangLayang = new Integer[dataLenght];
            //SetLenght and Get Pointer dari File
            j = (int) fileRAFData.getFilePointer();
            fileRAFData.setLength(dataLenght);
            //SetLenght and Get Pointer dari File
            k = (int) fileRAFLuasLayangLayang.getFilePointer();
            fileRAFLuasLayangLayang.setLength(dataLenght);
            //SetLenght and Get Pointer dari File
            l = (int) fileRAFKelilingLayangLayang.getFilePointer();
            fileRAFKelilingLayangLayang.setLength(dataLenght);
            index = 0;
            row = 0;
            while (j < fileRAFData.length()){
                j+=4;
                fileRAFData.seek(j);//Penyesesuaian Pointer
                data = fileRAFData.read();//Membaca Data Diagonal 1 dari File
                diagonal1[index] = data;
                j++;
                fileRAFData.seek(j);//Penyesesuaian Pointer
                data = fileRAFData.read();//Membaca Data Diagonal 2 dari File
                diagonal2[index] = data;
                //Perhitungan Luas Dan Keliling
                luasLayangLayang[row] = ((int)(diagonal1[index] * diagonal2[index] / 2)) ;
                kelilingLayangLayang[row] = ((int) (2*diagonal1[index]) + (2 * diagonal2[index])) ;
                //Input Ke JTable Output View
                outputView.tableLuasLayangLayang.insertRow(outputView.tableLuasLayangLayang.getRowCount(), new Object[]{
                kelilingLayangLayang[row],luasLayangLayang[row]
                });
                System.out.print("Diagonal 1 " + fileRAFData.getFilePointer() + ": ");
                System.out.println(diagonal1[index] + "; ");
                System.out.print("Diagonal 2  " + fileRAFData.getFilePointer() + ": ");
                System.out.println(diagonal2[index] + "; ");
                System.out.print("Luas LayangLayang : "+luasLayangLayang[row]);
                System.out.print("Keliling LayangLayang : "+kelilingLayangLayang[row]);
                fileRAFLuasLayangLayang.seek(k);//Penyesesuaian Pointer
                fileRAFLuasLayangLayang.write(luasLayangLayang[row]);//Menulis Hasil Luas Ke File
                fileRAFKelilingLayangLayang.seek(l);//Penyesesuaian Pointer
                fileRAFKelilingLayangLayang.write(kelilingLayangLayang[row]);//Menulis Hasil Luas Ke File
                j+=3;
                k++;
                l++;
                index++;
                row++;
            }
            //Menutup File
            fileRAFData.close();
            fileRAFLuasLayangLayang.close();
            fileRAFKelilingLayangLayang.close();
            System.out.println("-------------------------END PROCESS OF LAYANG LAYANG------------------------");

        } catch (ArithmeticException arithmeticException) {
            JOptionPane.showMessageDialog(null, "Terdapat Pembagian Dengan Bilangan (NOL)!!!");
        } catch(Throwable throwable){
            JOptionPane.showMessageDialog(null, throwable.getMessage());
        }
    }
    
}
