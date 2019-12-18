package bangundatar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
public class Persegi extends Thread{
    OutputView outputView;
    protected int dataLenght;
    protected Integer[] luasPersegi;
    protected Integer[] sisi;
    private int data;
    int j;
    int k;
    int index;
    int row;
    RandomAccessFile fileRAFData = null;
    RandomAccessFile fileRAFPersegi = null;
    RandomAccessFile RAFLenght = null;
    public Persegi(OutputView outputView){
       this.outputView = outputView;
    }
    
    @Override
    public void run() {
        hitungLuas();
    }
    public synchronized void hitungLuas(){
        try {
            fileRAFData = new RandomAccessFile("src\\saveData\\Data-Bangun.dat", "rw");
            fileRAFPersegi = new RandomAccessFile("src\\saveData\\Luas-Persegi.dat", "rw");
            RAFLenght = new RandomAccessFile("src\\saveData\\Data-Lenght.dat", "rw");

        }catch(FileNotFoundException fileNotFoundException){
            JOptionPane.showMessageDialog(null, "File Tidak Ditemukan!!!");
        }catch(Throwable throwable){
            JOptionPane.showMessageDialog(null, throwable.getMessage());
        }
        try {
            RAFLenght.seek(0);
            this.dataLenght = RAFLenght.read();
            RAFLenght.close();   
        }catch (IOException iOException) {
            JOptionPane.showMessageDialog(null, iOException.getMessage());
        }catch (Throwable throwable) {
            JOptionPane.showMessageDialog(null, throwable.getMessage());
        }
        try {
            sisi = new Integer[dataLenght/8];
            luasPersegi = new Integer[dataLenght/8];
            //-----------------------------------------------------------------------------------------------
            j = (int) fileRAFData.getFilePointer();
            fileRAFData.setLength(dataLenght);
            k = (int) fileRAFPersegi.getFilePointer();
            fileRAFPersegi.setLength(dataLenght/8);
            index = 0;
            row = 0;
            while (j < fileRAFData.length()){
                fileRAFData.seek(j);
                System.out.print("Pointer File " + fileRAFData.getFilePointer() + "= ");
                data = fileRAFData.read();
                sisi[index] = data;
                System.out.println(sisi[index] + "; ");
                luasPersegi[row] = sisi[index] * sisi[index];
                System.out.println("Luas Persegi adalah : "+luasPersegi[row]);
                outputView.tableLuasPersegi.insertRow(outputView.tableLuasPersegi.getRowCount(), new Object[]{
                luasPersegi[row]
                });
                fileRAFPersegi.seek(k);
                fileRAFPersegi.write(luasPersegi[row]);
                j+=8;
                k++;
                index++;
                row++;
                Thread.sleep(500);
            }
            fileRAFData.close();
            fileRAFPersegi.close();
            System.out.println("--------------------------END PROCESS OF PERSEGI--------------------------");
        } catch (ArithmeticException arithmeticException) {
            JOptionPane.showMessageDialog(null, "Terdapat Pembagian Dengan Bilangan (NOL)!!!");
        } catch(Throwable throwable){
            JOptionPane.showMessageDialog(null, throwable.getMessage());
        }
    }
    
}
