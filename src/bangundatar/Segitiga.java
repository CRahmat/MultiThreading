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
public class Segitiga extends Thread {
    OutputView outputView;
    protected int dataLenght;
    protected Integer[] luasSegitiga;
    protected Integer[] alas;
    protected Integer[] tinggi;
    private int data;
    int j;
    int k;
    int index;
    int row;
    RandomAccessFile fileRAFData = null;
    RandomAccessFile fileRAFSegitiga = null;
    RandomAccessFile RAFLenght = null;
    public Segitiga(OutputView outputView){
       this.outputView = outputView;
    }
    
    @Override
    public void run() {
        try {
            Thread.sleep(4000);
            hitungLuas();
        } catch (InterruptedException ex) {
            Logger.getLogger(Segitiga.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public synchronized void hitungLuas(){
        try {
            fileRAFData = new RandomAccessFile("src\\saveData\\Data-Bangun.dat", "rw");
            fileRAFSegitiga = new RandomAccessFile("src\\saveData\\Luas-Segitiga.dat", "rw");
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
            alas = new Integer[dataLenght];
            tinggi = new Integer[dataLenght];
            //-----------------------------------------------------------------------------------------------
            luasSegitiga = new Integer[dataLenght];
            //-----------------------------------------------------------------------------------------------
            j = (int) fileRAFData.getFilePointer();
            fileRAFData.setLength(dataLenght);
            
            k = (int) fileRAFSegitiga.getFilePointer();
            fileRAFSegitiga.setLength(dataLenght/8);
            index = 0;
            row = 0;
            while (j < fileRAFData.length()){
                fileRAFData.seek(j);
                System.out.print("Pointer File " + fileRAFData.getFilePointer() + "= ");
                data = fileRAFData.read();
                alas[index] = data;
                System.out.println(alas[index] + "; ");
                j+=6;
                fileRAFData.seek(j);
                System.out.print("Pointer File " + fileRAFData.getFilePointer() + "= ");
                data = fileRAFData.read();
                tinggi[index] = data;
                System.out.println(tinggi[index] + "; ");
                luasSegitiga[row] = (int)(alas[index] * tinggi[index] / 2);
                System.out.println("Luas Segitiga adalah : "+luasSegitiga[row]);
                outputView.tableLuasSegitiga.insertRow(outputView.tableLuasSegitiga.getRowCount(), new Object[]{
                luasSegitiga[row]
                });
                fileRAFSegitiga.seek(k);
                fileRAFSegitiga.write(luasSegitiga[row]);
                j+=2;
                k++;
                index++;
                row++;
                Thread.sleep(500);
            }
            fileRAFData.close();
            fileRAFSegitiga.close();
            System.out.println("-------------------------END PROCESS OF SEGITIGA------------------------");
            
        } catch (ArithmeticException arithmeticException) {
            JOptionPane.showMessageDialog(null, "Terdapat Pembagian Dengan Bilangan (NOL)!!!");
        } catch(Throwable throwable){
            JOptionPane.showMessageDialog(null, throwable.getMessage());
        }
    }
    
}
