/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bangunruang;

import bangundatar.Persegi;
import java.io.File;
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
public class LimasSegiEmpat extends Persegi implements Runnable{
    OutputView outputView;
    private int dataLenght;
    private Integer[] luasPersegi;
    protected Integer[] volumeLimasSegiEmpat;
    private Integer[] tinggi;
    private int data;
    private int dataLuas;
    int j;
    int k;
    int l;
    int index;
    int row;
    RandomAccessFile fileRAFData = null;
    RandomAccessFile fileRAFPersegi = null;
    RandomAccessFile RAFLenght = null;
    public LimasSegiEmpat(OutputView outputView) {
        super(outputView);
        this.outputView = outputView;
    }
    @Override
    public void run(){
        try {
        Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Kubus.class.getName()).log(Level.SEVERE, null, ex);
        }
        hitungVolume();
    }
    public synchronized void hitungVolume(){
        
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
            luasPersegi = new Integer[dataLenght/8];
            volumeLimasSegiEmpat= new Integer[dataLenght/8];
            //-----------------------------------------------------------------------------------------------
            j = (int) fileRAFData.getFilePointer();
            fileRAFData.setLength(dataLenght);
            
            k = (int) fileRAFPersegi.getFilePointer();
            fileRAFPersegi.setLength(dataLenght/8);
            index = 0;
            row = 0;
            while (j < fileRAFData.length()){
                j+=6;
                fileRAFData.seek(j);
                System.out.print("Pointer File " + fileRAFData.getFilePointer() + "= ");
                data = fileRAFData.read();
                tinggi[index] = data;
                System.out.println(tinggi[index] + "; ");
                
                fileRAFPersegi.seek(k);
                System.out.print("Pointer File " + fileRAFPersegi.getFilePointer() + "= ");
                dataLuas = fileRAFPersegi.read();
                luasPersegi[index] = dataLuas;

                volumeLimasSegiEmpat[row] = (int)(luasPersegi[index]*tinggi[index] * 0.333333333);
                System.out.println("Volume Limas Segi Empat adalah : "+volumeLimasSegiEmpat[row]);
                
                outputView.tableVolumeLimasSegiEmpat.insertRow(outputView.tableVolumeLimasSegiEmpat.getRowCount(), new Object[]{
                volumeLimasSegiEmpat[row]
                });
                
                j+=2;
                k++;
                index++;
                row++;
                Thread.sleep(500);
            }
            fileRAFData.close();
            fileRAFPersegi.close();
            System.out.println("--------------------------END PROCESS OF LIMAS 4--------------------------");
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
