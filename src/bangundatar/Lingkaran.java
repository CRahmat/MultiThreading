package bangundatar;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import output.OutputView;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Catur Rahmat
 */
public class Lingkaran extends Thread {
        OutputView outputView;
    protected int dataLenght;
    protected Integer[] luasLingkaran;
    protected Integer[] jarijari;
    private int data;
    int j;
    int k;
    int index;
    int row;
    RandomAccessFile fileRAFData = null;
    RandomAccessFile fileRAFLingkaran = null;
    RandomAccessFile RAFLenght = null;
    
    public Lingkaran(OutputView outputView){
       this.outputView = outputView;
    }
    
    @Override
    public void run() {
        try {
            Thread.sleep(6000);
            hitungLuas();
        } catch (InterruptedException ex) {
            Logger.getLogger(Lingkaran.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public synchronized void hitungLuas(){
                try {
            fileRAFData = new RandomAccessFile("src\\saveData\\Data-Bangun.dat", "rw");
            fileRAFLingkaran = new RandomAccessFile("src\\saveData\\Luas-Lingkaran.dat", "rw");
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
            jarijari = new Integer[dataLenght];
            //-----------------------------------------------------------------------------------------------
            luasLingkaran = new Integer[dataLenght/8];
            //-----------------------------------------------------------------------------------------------
            j = (int) fileRAFData.getFilePointer();
            fileRAFData.setLength(dataLenght);
            
            k = (int) fileRAFLingkaran.getFilePointer();
            fileRAFLingkaran.setLength(dataLenght/8);
            index = 0;
            row = 0;
            while (j < fileRAFData.length()){
                j+=7;
                fileRAFData.seek(j);
                System.out.print("Pointer File " + fileRAFData.getFilePointer() + "= ");
                data = fileRAFData.read();
                jarijari[index] = data;
                System.out.println(jarijari[index] + "; ");
                luasLingkaran[row] = ((int)(7 * jarijari[index] * jarijari[index]/22)) ;
                System.out.println("Luas Lingkaran adalah : "+luasLingkaran[row]);
                
                outputView.tableLuasLingkaran.insertRow(outputView.tableLuasLingkaran.getRowCount(), new Object[]{
                luasLingkaran[row]
                });
                
                fileRAFLingkaran.seek(k);
                fileRAFLingkaran.write(luasLingkaran[row]);
                j+=1;
                k++;
                index++;
                row++;
                Thread.sleep(500);
            }
            fileRAFData.close();
            fileRAFLingkaran.close();
            System.out.println("-------------------------END PROCESS OF LINGKARAN------------------------");

        } catch (ArithmeticException arithmeticException) {
            JOptionPane.showMessageDialog(null, "Terdapat Pembagian Dengan Bilangan (NOL)!!!");
        } catch(Throwable throwable){
            JOptionPane.showMessageDialog(null, throwable.getMessage());
        }
    }
    
}
