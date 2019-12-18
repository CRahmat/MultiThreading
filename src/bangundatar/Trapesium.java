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
public class Trapesium extends Thread{
        OutputView outputView;
    protected int dataLenght;
    protected Integer[] luasTrapesium;
    protected Integer[] panjang1;
    protected Integer[] panjang2;
    protected Integer[] tinggi;
    private int data;
    int j;
    int k;
    int index;
    int row;
    RandomAccessFile fileRAFData = null;
    RandomAccessFile fileRAFTrapesium = null;
    RandomAccessFile RAFLenght = null;
    public Trapesium(OutputView outputView){
       this.outputView = outputView;
    }
    
    @Override
    public void run() {
        try {
            Thread.sleep(3000);
            hitungLuas();
        } catch (InterruptedException ex) {
            Logger.getLogger(Trapesium.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public synchronized void hitungLuas(){
        try {
            fileRAFData = new RandomAccessFile("src\\saveData\\Data-Bangun.dat", "rw");
            fileRAFTrapesium = new RandomAccessFile("src\\saveData\\Luas-Trapesium.dat", "rw");
            RAFLenght = new RandomAccessFile("src\\saveData\\Data-Lenght.dat", "rw");

        }catch(FileNotFoundException fileNotFoundException){
            JOptionPane.showMessageDialog(null, "File Tidak Ditemukan!!!");
        }catch(Throwable throwable){
            JOptionPane.showMessageDialog(null, throwable.getMessage());
        }
        try {
            RAFLenght.seek(0);
            dataLenght = RAFLenght.read();
            RAFLenght.close();   
        }catch (IOException iOException) {
            JOptionPane.showMessageDialog(null, iOException.getMessage());
        }catch (Throwable throwable) {
            JOptionPane.showMessageDialog(null, throwable.getMessage());
        }
        try {
            panjang1 = new Integer[dataLenght];
            panjang2 = new Integer[dataLenght];
            tinggi = new Integer[dataLenght];
            //-----------------------------------------------------------------------------------------------
            luasTrapesium = new Integer[dataLenght];
            //-----------------------------------------------------------------------------------------------
            j = (int) fileRAFData.getFilePointer();
            fileRAFData.setLength(dataLenght);
            k = (int) fileRAFTrapesium.getFilePointer();
            fileRAFTrapesium.setLength(dataLenght/8);
            index = 0;
            row = 0;
            while (j < fileRAFData.length()){
                fileRAFData.seek(j);
                System.out.print("Pointer File " + fileRAFData.getFilePointer() + "= ");
                data = fileRAFData.read();
                panjang1[index] = data;
                System.out.println(panjang1[index] + "; ");
                j++;
                fileRAFData.seek(j);
                System.out.print("Pointer File " + fileRAFData.getFilePointer() + "= ");
                data = fileRAFData.read();
                panjang2[index] = data;
                System.out.println(panjang2[index] + "; ");
                j+=5;
                fileRAFData.seek(j);
                System.out.print("Pointer File " + fileRAFData.getFilePointer() + "= ");
                data = fileRAFData.read();
                tinggi[index] = data;
                System.out.println(tinggi[index] + "; ");
                luasTrapesium[row] = ((int) ((panjang1[index] + panjang2[index]) * tinggi[index] / 2));
                System.out.println("Luas Trapesium adalah : "+luasTrapesium[row]);
                
                outputView.tableLuasTrapesium.insertRow(outputView.tableLuasTrapesium.getRowCount(), new Object[]{
                luasTrapesium[row]
                });
                fileRAFTrapesium.seek(k);
                fileRAFTrapesium.write(luasTrapesium[row]);
                j+=2;
                k++;
                index++;
                row++;
                Thread.sleep(500);
            }
            fileRAFData.close();
            fileRAFTrapesium.close();
            System.out.println("-----------------------END PROCESS OF PERSEGI TRAPESIUM----------------------");

        } catch (ArithmeticException arithmeticException) {
            JOptionPane.showMessageDialog(null, "Terdapat Pembagian Dengan Bilangan (NOL)!!!");
        } catch(Throwable throwable){
            JOptionPane.showMessageDialog(null, "Error 404!!!");
        }
    }
    
}
