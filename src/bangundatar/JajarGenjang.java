package bangundatar;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import output.OutputView;

public class JajarGenjang extends Thread {
    OutputView outputView;
    protected int dataLenght;
    protected static Integer[] luasJajarGenjang;
    protected Integer[] alas;
    protected Integer[] tinggi;
    private int data;
    int j;
    int k;
    int index;
    int row;
    RandomAccessFile fileRAFData = null;
    RandomAccessFile fileRAFJajarGenjang = null;
    RandomAccessFile RAFLenght = null;
    
    public JajarGenjang(OutputView outputView){
       this.outputView = outputView;
    }
    
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            hitungLuas();
        } catch (InterruptedException ex) {
            Logger.getLogger(JajarGenjang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public synchronized void hitungLuas(){
                try {
            fileRAFData = new RandomAccessFile("src\\saveData\\Data-Bangun.dat", "rw");
            fileRAFJajarGenjang = new RandomAccessFile("src\\saveData\\Luas-JajarGenjang.dat", "rw");
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
            luasJajarGenjang = new Integer[dataLenght];
            //-----------------------------------------------------------------------------------------------
            j = (int) fileRAFData.getFilePointer();
            fileRAFData.setLength(dataLenght);
            k = (int) fileRAFJajarGenjang.getFilePointer();
            fileRAFJajarGenjang.setLength(dataLenght);
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
                luasJajarGenjang[row] = ((int) (alas[index] * tinggi[index]));
                System.out.println("Luas Trapesium adalah : "+luasJajarGenjang[row]);
                
                outputView.tableLuasJajarGenjang.insertRow(outputView.tableLuasJajarGenjang.getRowCount(), new Object[]{
                luasJajarGenjang[row]
                });
                
                fileRAFJajarGenjang.seek(k);
                fileRAFJajarGenjang.write(luasJajarGenjang[row]);
                j+=2;
                k++;
                index++;
                row++;
                Thread.sleep(500);
            }
            fileRAFData.close();
            fileRAFJajarGenjang.close();
            System.out.println("-----------------------END PROCESS OF JAJAR GENJANG----------------------");

        } catch (ArithmeticException arithmeticException) {
            JOptionPane.showMessageDialog(null, "Terdapat Pembagian Dengan Bilangan (NOL)!!!");
        } catch(Throwable throwable){
            JOptionPane.showMessageDialog(null, "Error 404!!!");
        }
    }
    
}
