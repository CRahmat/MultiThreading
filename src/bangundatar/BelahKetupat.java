package bangundatar;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import output.OutputView;

public class BelahKetupat extends Thread{
    OutputView outputView;
    RandomAccessFile fileRAFData = null;
    RandomAccessFile fileRAFLuasBelahKetupat = null;
    RandomAccessFile fileRAFKelilingBelahKetupat = null;
    RandomAccessFile RAFLenght = null;
    protected int dataLenght;
    protected static Integer[] luasBelahKetupat;
    protected static Integer[] kelilingBelahKetupat;
    protected Integer[] diagonal1;
    protected Integer[] diagonal2;
    protected int sisiMiring;
    private int data;
    int j;
    int k;
    int l;
    int index;
    int row;
    public BelahKetupat(OutputView outputView){//Constructor dari Belah Ketupat Dengan Parameter OutputView 
       this.outputView = outputView;           //Digunakan Untuk Input Hasil Perhitungan Ke View
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
        //Perhitungan Luas
        public synchronized void hitungLuas(){
                    try {
            //Instansiasi Obyek dari Random Access File
            fileRAFData = new RandomAccessFile("src\\saveData\\Data-Bangun.dat", "rw");
            fileRAFLuasBelahKetupat = new RandomAccessFile("src\\saveData\\2D\\Luas-BelahKetupat.dat", "rw");
            fileRAFKelilingBelahKetupat = new RandomAccessFile("src\\saveData\\2D\\Keliling-BelahKetupat.dat", "rw");
            RAFLenght = new RandomAccessFile("src\\saveData\\Data-Lenght.dat", "rw");

            RAFLenght.seek(0);//File Selalu Berada Pada File Pointer 0 (Hanya 1 Data)
            dataLenght = RAFLenght.readInt();//Baca Lenght Dari File
            RAFLenght.close();//Close File Lenght
            //Instansiasi Obyek
            diagonal1 = new Integer[dataLenght];
            diagonal2 = new Integer[dataLenght];
            luasBelahKetupat = new Integer[dataLenght];
            kelilingBelahKetupat = new Integer[dataLenght];
            //SetLenght and Get Pointer dari File
            j = (int) fileRAFData.getFilePointer();
            fileRAFData.setLength(dataLenght);
            //SetLenght and Get Pointer dari File
            k = (int) fileRAFLuasBelahKetupat.getFilePointer();
            fileRAFLuasBelahKetupat.setLength(dataLenght);
            //SetLenght and Get Pointer dari File
            l = (int) fileRAFKelilingBelahKetupat.getFilePointer();
            //Setting Lenght dari File
            fileRAFKelilingBelahKetupat.setLength(dataLenght);
            index = 0;
            row = 0;
            while (j < fileRAFData.length()){
                j+=4;//Penyesesuaian Pointer
                fileRAFData.seek(j);
                data = fileRAFData.read();//Membaca Data Diagonal 1Dari File
                diagonal1[index] = data;
                j++;//Penyesesuaian Pointer
                fileRAFData.seek(j);
                data = fileRAFData.read();//Membaca Data Diagonal 2 Dari File
                diagonal2[index] = data;
                //Perhitungan Luas Dan Volume
                luasBelahKetupat[row] = ((int)(diagonal1[index] * diagonal2[index] / 2)) ;
                //Hitung Sisi Miring Dengan Pythagoras
                sisiMiring = 0;
                sisiMiring = (int) Math.sqrt(((Math.pow(diagonal1[index]/2, 2))+(Math.pow(diagonal2[index]/2, 2))));
                kelilingBelahKetupat[row] = ((int)(4*sisiMiring)) ;
                //Input Ke JTable Output View
                outputView.tableLuasBelahKetupat.insertRow(outputView.tableLuasBelahKetupat.getRowCount(), new Object[]{
                kelilingBelahKetupat[row],luasBelahKetupat[row]
                });
                //Output
                System.out.print("Diagonal 1" + fileRAFData.getFilePointer() + "= ");
                System.out.println(diagonal1[index] + "; ");
                System.out.print("Diagonal 2 " + fileRAFData.getFilePointer() + "= ");
                System.out.println(diagonal2[index] + "; ");
                System.out.println("Luas BelahKetupat     : "+luasBelahKetupat[row]);
                System.out.println("Keliling BelahKetupat : "+kelilingBelahKetupat[row]);
                fileRAFLuasBelahKetupat.seek(k);
                fileRAFLuasBelahKetupat.write(luasBelahKetupat[row]);//Menulis Hasil Luas Ke File
                fileRAFLuasBelahKetupat.seek(l);
                fileRAFKelilingBelahKetupat.write(luasBelahKetupat[row]);//Menulis Hasil Volume Ke File
                j+=3;
                k++;
                l++;
                index++;
                row++;
            }
            //Menutup File
            fileRAFData.close();
            fileRAFLuasBelahKetupat.close();
            fileRAFKelilingBelahKetupat.close();
            System.out.println("-------------------------END PROCESS OF BELAH KETUPAT------------------------");

        } catch (ArithmeticException arithmeticException) {
            JOptionPane.showMessageDialog(null, "Terdapat Pembagian Dengan Bilangan (NOL)!!!");
        } catch(Throwable throwable){
            JOptionPane.showMessageDialog(null, throwable.getMessage());
        }
        
        }
    
}
