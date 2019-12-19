package bangundatar;

import static bangundatar.BelahKetupat.luasBelahKetupat;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import output.OutputView;

public class JajarGenjang extends Thread {
    OutputView outputView;
    protected int dataLenght;
    protected static Integer[] luasJajarGenjang;
    protected static Integer[] kelilingJajarGenjang;
    protected Integer[] alas;
    protected Integer[] tinggi;
    protected int sisiMiring;
    private int data;
    int j;
    int k;
    int l;
    int index;
    int row;
    RandomAccessFile fileRAFData = null;
    RandomAccessFile fileRAFKelilingJajarGenjang = null;
    RandomAccessFile fileRAFLuasJajarGenjang = null;
    RandomAccessFile RAFLenght = null;
    
    public JajarGenjang(OutputView outputView){//Constructor dari Jajar Genjang Dengan Parameter OutputView 
       this.outputView = outputView;           //Digunakan Untuk Input Hasil Perhitungan Ke View

    }
        //Override method run dikarenakan extends Threads
    @Override
       //Badan Threads
    public void run() {
        try {
            Thread.sleep(2000);
            hitungLuas();
        } catch (InterruptedException ex) {//Exception Threads Terinterupsi
            JOptionPane.showMessageDialog(null, ex.getMessage());;
        }
    }
    public synchronized void hitungLuas(){
                try {
            //Instansiasi Obyek Random Access File
            fileRAFData = new RandomAccessFile("src\\saveData\\Data-Bangun.dat", "rw");
            fileRAFLuasJajarGenjang = new RandomAccessFile("src\\saveData\\2D\\Luas-JajarGenjang.dat", "rw");
            fileRAFKelilingJajarGenjang = new RandomAccessFile("src\\saveData\\2D\\Keliling-JajarGenjang.dat", "rw");
            RAFLenght = new RandomAccessFile("src\\saveData\\Data-Lenght.dat", "rw");

            RAFLenght.seek(0);//File Selalu Berada Pada File Pointer 0 (Hanya 1 Data)
            dataLenght = RAFLenght.readInt();//Baca Lenght Dari File
            RAFLenght.close();//Close File Lenght   
            //Instansiasi Obyek Untuk Perhitungan Luas
            alas = new Integer[dataLenght];
            tinggi = new Integer[dataLenght];
            luasJajarGenjang = new Integer[dataLenght];
            kelilingJajarGenjang = new Integer[dataLenght];
            //SetLenght and Get Pointer dari File
            j = (int) fileRAFData.getFilePointer();
            fileRAFData.setLength(dataLenght);
            //SetLenght and Get Pointer dari File
            k = (int) fileRAFLuasJajarGenjang.getFilePointer();
            fileRAFLuasJajarGenjang.setLength(dataLenght);
            //SetLenght and Get Pointer dari File
            l = (int) fileRAFKelilingJajarGenjang.getFilePointer();
            fileRAFKelilingJajarGenjang.setLength(dataLenght);
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
                //Perhitungan Luas Dan Volume
                luasJajarGenjang[row] = ((int) (alas[index] * tinggi[index]));
                //Mencari Sisi Miring Dengan Pytagoras
                sisiMiring = 0;
                sisiMiring = (int) Math.sqrt(((Math.pow(tinggi[index], 2))+(Math.pow((alas[index]/2), 2))));
                kelilingJajarGenjang[row] = ((int) ((2 * alas[index]) + (2 * sisiMiring)));
                //Input Ke JTable Output View
                outputView.tableLuasJajarGenjang.insertRow(outputView.tableLuasJajarGenjang.getRowCount(), new Object[]{
                kelilingJajarGenjang[row],luasJajarGenjang[row]
                });
                //Output
                System.out.print("Diagonal 1 " + fileRAFData.getFilePointer() + ": ");
                System.out.println(alas[index] + "; ");
                System.out.print("Diagonal 2 " + fileRAFData.getFilePointer() + ": ");
                System.out.println(tinggi[index] + "; ");
                System.out.println("Luas JajarGenjang     : "+luasJajarGenjang[row]);
                System.out.println("Keliling JajarGenjang : "+kelilingJajarGenjang[row]);
                fileRAFLuasJajarGenjang.seek(k);//Penyesesuaian Pointer
                fileRAFLuasJajarGenjang.write(luasJajarGenjang[row]);//Menulis Hasil Luas Ke File
                fileRAFKelilingJajarGenjang.seek(k);//Penyesesuaian Pointer
                fileRAFKelilingJajarGenjang.write(luasJajarGenjang[row]);//Menulis Hasil Volume Ke File
                j+=2;
                k++;
                l++;
                index++;
                row++;
                Thread.sleep(500);
            }
            //Menutup File
            fileRAFData.close();
            fileRAFLuasJajarGenjang.close();
            fileRAFKelilingJajarGenjang.close();
            System.out.println("-----------------------END PROCESS OF JAJAR GENJANG----------------------");

        } catch (ArithmeticException arithmeticException) {
            JOptionPane.showMessageDialog(null, "Terdapat Pembagian Dengan Bilangan (NOL)!!!");
        } catch(Throwable throwable){
            JOptionPane.showMessageDialog(null, throwable.getMessage());
        }
    }
    
}
