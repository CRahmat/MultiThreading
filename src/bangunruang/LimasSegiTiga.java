
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bangunruang;

import bangundatar.Segitiga;
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
public class LimasSegiTiga extends Segitiga implements Runnable{
    OutputView outputView;
    private int dataLenght;
    protected Integer[] volumeLimasSegiTiga;
    private Integer[] tinggi;
    private int data;
    private int dataLuas;
    int j;
    int k;
    int l;
    int index;
    int row;
    RandomAccessFile fileRAFData = null;
    RandomAccessFile fileRAFSegitiga = null;
    RandomAccessFile RAFLenght = null;
    public LimasSegiTiga(OutputView outputView) {
        super(outputView);
        this.outputView = outputView;
    }
    @Override
    public void run(){
        try {
        Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Kubus.class.getName()).log(Level.SEVERE, null, ex);
        }
        hitungVolume();
    }
    public synchronized void hitungVolume(){
        
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
            index = 0;
            tinggi = new Integer[dataLenght];
            volumeLimasSegiTiga= new Integer[dataLenght];
            //-----------------------------------------------------------------------------------------------
            j = (int) fileRAFData.getFilePointer();
            fileRAFData.setLength(dataLenght);
            
            k = (int) fileRAFSegitiga.getFilePointer();
            fileRAFSegitiga.setLength(dataLenght);
            index = 0;
            row = 0;
            
            while (j < fileRAFData.length()){
                j+=6;
                fileRAFData.seek(j);
                System.out.print("Pointer File " + fileRAFData.getFilePointer() + "= ");
                data = fileRAFData.read();
                tinggi[index] = data;
                System.out.println(tinggi[index] + "; ");
                
                fileRAFSegitiga.seek(k);
                System.out.print("Pointer File " + fileRAFSegitiga.getFilePointer() + "= ");
                dataLuas = fileRAFSegitiga.read();
                luasSegitiga[index] = dataLuas;

                volumeLimasSegiTiga[row] = (int)(super.luasSegitiga[index]*tinggi[index] * 0.166666667);
                System.out.println("Volume Limas Segi Tiga adalah : "+volumeLimasSegiTiga[row]);
                
                outputView.tableVolumeLimasSegiTiga.insertRow(outputView.tableVolumeLimasSegiTiga.getRowCount(), new Object[]{
                volumeLimasSegiTiga[row]
                });
                
                j+=2;
                k++;
                index++;
                row++;
                Thread.sleep(500);
            }
            fileRAFData.close();
            fileRAFSegitiga.close();
            System.out.println("--------------------------END PROCESS OF LIMAS 3--------------------------");
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