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
public class LayangLayang extends Thread {
        OutputView outputView;
    protected int dataLenght;
    protected Integer[] luasLayangLayang;
    protected Integer[] diagonal1;
    protected Integer[] diagonal2;
    private int data;
    int j;
    int k;
    int index;
    int row;
    RandomAccessFile fileRAFData = null;
    RandomAccessFile fileRAFLayangLayang = null;
    RandomAccessFile RAFLenght = null;
    
    public LayangLayang(OutputView outputView){
       this.outputView = outputView;
    }
    
    @Override
    public void run() {
        try {
            Thread.sleep(4000);
            hitungLuas();
        } catch (InterruptedException ex) {
            Logger.getLogger(LayangLayang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public synchronized void hitungLuas(){
                try {
            fileRAFData = new RandomAccessFile("src\\saveData\\Data-Bangun.dat", "rw");
            fileRAFLayangLayang = new RandomAccessFile("src\\saveData\\Luas-LayangLayang.dat", "rw");
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
            diagonal1 = new Integer[dataLenght];
            diagonal2 = new Integer[dataLenght];
            //-----------------------------------------------------------------------------------------------
            luasLayangLayang = new Integer[dataLenght];
            //-----------------------------------------------------------------------------------------------
            j = (int) fileRAFData.getFilePointer();
            fileRAFData.setLength(dataLenght);
            
            k = (int) fileRAFLayangLayang.getFilePointer();
            fileRAFLayangLayang.setLength(dataLenght/8);
            index = 0;
            row = 0;
            while (j < fileRAFData.length()){
                j+=4;
                fileRAFData.seek(j);
                System.out.print("Pointer File " + fileRAFData.getFilePointer() + "= ");
                data = fileRAFData.read();
                diagonal1[index] = data;
                System.out.println(diagonal1[index] + "; ");
                j++;
                fileRAFData.seek(j);
                System.out.print("Pointer File " + fileRAFData.getFilePointer() + "= ");
                data = fileRAFData.read();
                diagonal2[index] = data;
                System.out.println(diagonal2[index] + "; ");
                luasLayangLayang[row] = ((int)(diagonal1[index] * diagonal2[index] / 2)) ;
                System.out.println("Luas LayangLayang adalah : "+luasLayangLayang[row]);
                
                outputView.tableLuasLayangLayang.insertRow(outputView.tableLuasLayangLayang.getRowCount(), new Object[]{
                luasLayangLayang[row]
                });
                
                fileRAFLayangLayang.seek(k);
                fileRAFLayangLayang.write(luasLayangLayang[row]);
                j+=3;
                k++;
                index++;
                row++;
                Thread.sleep(500);
            }
            fileRAFData.close();
            fileRAFLayangLayang.close();
            System.out.println("-------------------------END PROCESS OF LAYANG LAYANG------------------------");

        } catch (ArithmeticException arithmeticException) {
            JOptionPane.showMessageDialog(null, "Terdapat Pembagian Dengan Bilangan (NOL)!!!");
        } catch(Throwable throwable){
            JOptionPane.showMessageDialog(null, throwable.getMessage());
        }
    }
    
}
