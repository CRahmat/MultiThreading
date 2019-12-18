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
public class Kerucut extends Lingkaran implements Runnable{
    OutputView outputView;
    private int dataLenght;
    private Integer[] luasLingkaran;
    protected Integer[] volumeKerucut;
    private Integer[] tinggi;
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
    public Kerucut(OutputView outputView) {
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
            index = 0;
            tinggi = new Integer[dataLenght/8];
            luasLingkaran = new Integer[dataLenght/8];
            volumeKerucut = new Integer[dataLenght/8];
            //-----------------------------------------------------------------------------------------------
            j = (int) fileRAFData.getFilePointer();
            fileRAFData.setLength(dataLenght);
            
            k = (int) fileRAFLingkaran.getFilePointer();
            fileRAFLingkaran.setLength(dataLenght/8);
            index = 0;
            row = 0;
            
            while (j < fileRAFData.length()){
                j+=6;
                fileRAFData.seek(j);
                System.out.print("Pointer File " + fileRAFData.getFilePointer() + "= ");
                data = fileRAFData.read();
                tinggi[index] = data;
                System.out.println(tinggi[index] + "; ");
                
                fileRAFLingkaran.seek(k);
                System.out.print("Pointer File " + fileRAFLingkaran.getFilePointer() + "= ");
                dataLuas = fileRAFLingkaran.read();
                luasLingkaran[index] = dataLuas;

                volumeKerucut[row] = (int)(luasLingkaran[index]*tinggi[index] * (0.333333333));
                System.out.println("Volume Kerucut adalah : "+volumeKerucut[row]);
                
                outputView.tableVolumeKerucut.insertRow(outputView.tableVolumeKerucut.getRowCount(), new Object[]{
                volumeKerucut[row]
                });
                
                j+=2;
                k++;
                index++;
                row++;
                Thread.sleep(500);
            }
            fileRAFData.close();
            fileRAFLingkaran.close();
            System.out.println("--------------------------END PROCESS OF KERUCUT--------------------------");
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