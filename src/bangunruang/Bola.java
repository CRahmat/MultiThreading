/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class Bola extends Lingkaran implements Runnable{
    OutputView outputView;
    private int dataLenght;
    protected Integer[] volumeBola;
    private Integer[] jarijari;
    private int data;
    private int dataLuas;
    int j;
    int k;
    int l;
    int index;
    int row;
    RandomAccessFile fileRAFData = null;
    RandomAccessFile fileRAFLingkaran = null;
    RandomAccessFile RAFLenght = null;
    public Bola(OutputView outputView) {
        super(outputView);
        this.outputView = outputView;
    }
    @Override
    public void run(){
        try {
        Thread.sleep(7000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Kubus.class.getName()).log(Level.SEVERE, null, ex);
        }
        hitungVolume();
    }
    public synchronized void hitungVolume(){
        
        try {
            fileRAFData = new RandomAccessFile("src\\saveData\\Data-Bangun.dat", "rw");
            fileRAFLingkaran = new RandomAccessFile("src\\saveData\\Luas-Lingkaran.dat", "rw");
            RAFLenght = new RandomAccessFile("src\\saveData\\Data-Lenght.dat", "rw");

            RAFLenght.seek(0);
            dataLenght = RAFLenght.readInt();
            RAFLenght.close();   

            index = 0;
            jarijari = new Integer[dataLenght];
            volumeBola = new Integer[dataLenght];
  
            j = (int) fileRAFData.getFilePointer();
            fileRAFData.setLength(dataLenght);
            
            k = (int) fileRAFLingkaran.getFilePointer();
            fileRAFLingkaran.setLength(dataLenght);
            index = 0;
            row = 0;
            
            while (j < fileRAFData.length()){
                j+=7;
                fileRAFData.seek(j);
                System.out.print("Pointer File " + fileRAFData.getFilePointer() + "= ");
                data = fileRAFData.read();
                jarijari[index] = data;
                System.out.println(jarijari[index] + "; ");
                
                fileRAFLingkaran.seek(k);
                System.out.print("Pointer File " + fileRAFLingkaran.getFilePointer() + "= ");
                dataLuas = fileRAFLingkaran.read();
                luasLingkaran[index] = dataLuas;

                volumeBola[row] = (int)(Lingkaran.luasLingkaran[index]*jarijari[index] * (0.75));
                System.out.println("Volume Bola adalah : "+volumeBola[row]);
                
                outputView.tableVolumeBola.insertRow(outputView.tableVolumeBola.getRowCount(), new Object[]{
                volumeBola[row]
                });
                
                j+=1;
                k++;
                index++;
                row++;
                Thread.sleep(500);
            }
            fileRAFData.close();
            fileRAFLingkaran.close();
            System.out.println("--------------------------END PROCESS OF BOLA--------------------------");
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
