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
public class Tabung extends Lingkaran implements Runnable{
    OutputView outputView;
    RandomAccessFile fileRAFData = null;
    RandomAccessFile fileRAFLuasPermukaanTabung = null;
    RandomAccessFile fileRAFVolumeTabung = null;
    RandomAccessFile RAFLenght = null;
    private int dataLenght;
    private Integer[] luasPermukaanTabung;
    protected Integer[] volumeTabung;
    private Integer[] tinggi;
    private int data;
    int j;
    int k;
    int l;
    int index;
    int row;
    public Tabung(OutputView outputView) { //Constructor dari Tabung Dengan Parameter OutputView
        super(outputView);                 //Mengirimkan Parameter Output View ke Super Class
        this.outputView = outputView;      //Digunakan Untuk Input Hasil Perhitungan Ke View
    }
    //Override method run dikarenakan extends Threads
    @Override
        //Badan Threads
    public void run(){
        try {
        Thread.sleep(10000);
        hitungVolume();
        } catch (InterruptedException ex) {//Exception Threads Terinterupsi
        JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    public synchronized void hitungVolume(){
        try {
            //Instansiasi Obyek Random Access File
            fileRAFData = new RandomAccessFile("src\\saveData\\Data-Bangun.dat", "rw");
            fileRAFLuasPermukaanTabung = new RandomAccessFile("src\\saveData\\3D\\Luas-Permukaan-Tabung.dat", "rw");
            fileRAFVolumeTabung = new RandomAccessFile("src\\saveData\\3D\\Volume-Tabung.dat", "rw");
            RAFLenght = new RandomAccessFile("src\\saveData\\Data-Lenght.dat", "rw");
            
            RAFLenght.seek(0);//File Selalu Berada Pada File Pointer 0 (Hanya 1 Data)
            dataLenght = RAFLenght.readInt();//Baca Lenght Dari File
            RAFLenght.close();//Close File Lenght    
            //Instansiasi Obyek untuk Perhitungan
            tinggi = new Integer[dataLenght];
            luasPermukaanTabung = new Integer[dataLenght];
            volumeTabung = new Integer[dataLenght];
            //SetLenght and Get Pointer dari File
            j = (int) fileRAFData.getFilePointer();
            fileRAFData.setLength(dataLenght);
            //SetLenght and Get Pointer dari File
            k = (int) fileRAFVolumeTabung.getFilePointer();
            fileRAFVolumeTabung.setLength(dataLenght);
            //SetLenght and Get Pointer dari File
            l = (int) fileRAFLuasPermukaanTabung.getFilePointer();
            fileRAFLuasPermukaanTabung.setLength(dataLenght);
            index = 0;
            row = 0;
            while (j < fileRAFData.length()){
                j+=6;
                fileRAFData.seek(j);//Penyesesuaian Pointer untuk Baca File
                data = fileRAFData.read();//Membaca Tinggi Dari File
                tinggi[index] = data;
                //PERHITUNGAN LUAS PERMUKAAN DAN VOLUME
                volumeTabung[row] = (int)(Lingkaran.luasLingkaran[index]*tinggi[index]);
                luasPermukaanTabung[row] = (int)((2 * Lingkaran.luasLingkaran[index])+ (2 * 22 / 7 * Lingkaran.jarijari[index]*tinggi[index]));
                //Menulis Hasil Perhitungan Ke File
                fileRAFVolumeTabung.seek(k);//Penyesesuaian Pointer agar Data Tidak Tertimpa
                fileRAFVolumeTabung.write(volumeTabung[row]);//Menuliskan Hasil Perhitungan Volume Ke File
                fileRAFLuasPermukaanTabung.seek(l);//Penyesesuaian Pointer agar Data Tidak Tertimpa
                fileRAFLuasPermukaanTabung.write(luasPermukaanTabung[row]);//Menuliskan Hasil Perhitungan Luas Ke File
                //OUTPUT KE MONITOR TANPA SWING
                System.out.print("Tinggi Tabung         : " + fileRAFData.getFilePointer() + ": ");
                System.out.println(tinggi[index] + "; ");
                System.out.println("Volume Tabung       : "+volumeTabung[row]);
                System.out.println("L.Permukaan Tabung  : "+luasPermukaanTabung[row]);
                //INPUT KE JTABLE
                outputView.tableVolumeTabung.insertRow(outputView.tableVolumeTabung.getRowCount(), new Object[]{
                luasPermukaanTabung[row],volumeTabung[row]
                });
                j+=2;
                k++;
                l++;
                index++;
                row++;
            }
            //MENUTUP FILE
            fileRAFData.close();
            fileRAFLuasPermukaanTabung.close();
            fileRAFVolumeTabung.close();
            System.out.println("--------------------------END PROCESS OF TABUNG--------------------------");
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
