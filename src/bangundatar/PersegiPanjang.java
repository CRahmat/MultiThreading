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
    protected int dataLenght;
    protected static Integer[] luasPersegiPanjang;
    protected Integer[] panjang;
    protected Integer[] lebar;
    private int data;
    int j;
    int k;
    int index;
    int row;
    RandomAccessFile fileRAFData = null;
    RandomAccessFile fileRAFPersegiPanjang = null;
    RandomAccessFile RAFLenght = null;
    
    public PersegiPanjang(OutputView outputView){
       this.outputView = outputView;
    }
    
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            hitungLuas();
        } catch (InterruptedException ex) {
            Logger.getLogger(PersegiPanjang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public synchronized void hitungLuas(){

        try {
            fileRAFData = new RandomAccessFile("src\\saveData\\Data-Bangun.dat", "rw");
            fileRAFPersegiPanjang = new RandomAccessFile("src\\saveData\\Luas-PersegiPanjang.dat", "rw");
            RAFLenght = new RandomAccessFile("src\\saveData\\Data-Lenght.dat", "rw");

        }catch(FileNotFoundException fileNotFoundException){
            JOptionPane.showMessageDialog(null, "File Tidak Ditemukan!!!");
        }catch(Throwable throwable){
            JOptionPane.showMessageDialog(null, throwable.getMessage());
        }
        try {
            RAFLenght.seek(0);
            dataLenght = RAFLenght.readInt();
            RAFLenght.close();   
        }catch (IOException iOException) {
            JOptionPane.showMessageDialog(null, iOException.getMessage());
        }catch (Throwable throwable) {
            JOptionPane.showMessageDialog(null, throwable.getMessage());
        }
        try {
            
            panjang = new Integer[dataLenght];
            lebar = new Integer[dataLenght];
            //-----------------------------------------------------------------------------------------------
            luasPersegiPanjang = new Integer[dataLenght];
            //-----------------------------------------------------------------------------------------------
            j = (int) fileRAFData.getFilePointer();
            fileRAFData.setLength(dataLenght);
            k = (int) fileRAFPersegiPanjang.getFilePointer();
            fileRAFPersegiPanjang.setLength(dataLenght);
            index = 0;
            row = 0;
            while (j < fileRAFData.length()){
                fileRAFData.seek(j);
                System.out.print("Pointer File " + fileRAFData.getFilePointer() + "= ");
                data = fileRAFData.read();
                panjang[index] = data;
                System.out.println(panjang[index] + "; ");
                j++;
                fileRAFData.seek(j);
                System.out.print("Pointer File " + fileRAFData.getFilePointer() + "= ");
                data = fileRAFData.read();
                lebar[index] = data;
                System.out.println(lebar[index] + "; ");
                luasPersegiPanjang[row] = panjang[index] * lebar[index];
                System.out.println("Luas PersegiPanjang adalah : "+luasPersegiPanjang[row]);
                outputView.tableLuasPersegiPanjang.insertRow(outputView.tableLuasPersegiPanjang.getRowCount(), new Object[]{
                luasPersegiPanjang[row]
                });
                fileRAFPersegiPanjang.seek(k);
                fileRAFPersegiPanjang.write(luasPersegiPanjang[row]);
                j+=7;
                k++;
                index++;
                row++;
                Thread.sleep(500);
            }
            fileRAFData.close();
            fileRAFPersegiPanjang.close();
            System.out.println("-----------------------END PROCESS OF PERSEGI PANJANG----------------------");

        } catch (ArithmeticException arithmeticException) {
            JOptionPane.showMessageDialog(null, "Terdapat Pembagian Dengan Bilangan (NOL)!!!");
        } catch(Throwable throwable){
            JOptionPane.showMessageDialog(null, "Error 404!!!");
        }
    }
}
