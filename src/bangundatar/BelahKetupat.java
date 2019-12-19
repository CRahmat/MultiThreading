package bangundatar;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import output.OutputView;

public class BelahKetupat extends Thread{
    OutputView outputView;
    protected int dataLenght;
    protected static Integer[] luasBelahKetupat;
    protected Integer[] diagonal1;
    protected Integer[] diagonal2;
    RandomAccessFile fileRAFData = null;
    RandomAccessFile fileRAFBelahKetupat = null;
    RandomAccessFile RAFLenght = null;
    private int data;
    int j;
    int k;
    int index;
    int row;
    
    public BelahKetupat(OutputView outputView){
       this.outputView = outputView;
    }
    
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            hitungLuas();
        } catch (InterruptedException ex) {
            Logger.getLogger(BelahKetupat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        public synchronized void hitungLuas(){
                    try {
            fileRAFData = new RandomAccessFile("src\\saveData\\Data-Bangun.dat", "rw");
            fileRAFBelahKetupat = new RandomAccessFile("src\\saveData\\Luas-BelahKetupat.dat", "rw");
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
            luasBelahKetupat = new Integer[dataLenght];
            //-----------------------------------------------------------------------------------------------
            j = (int) fileRAFData.getFilePointer();
            fileRAFData.setLength(dataLenght);
            
            k = (int) fileRAFBelahKetupat.getFilePointer();
            fileRAFBelahKetupat.setLength(dataLenght);
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
                luasBelahKetupat[row] = ((int)(diagonal1[index] * diagonal2[index] / 2)) ;
                System.out.println("Luas BelahKetupat adalah : "+luasBelahKetupat[row]);
                
                outputView.tableLuasBelahKetupat.insertRow(outputView.tableLuasBelahKetupat.getRowCount(), new Object[]{
                luasBelahKetupat[row]
                });
                
                fileRAFBelahKetupat.seek(k);
                fileRAFBelahKetupat.write(luasBelahKetupat[row]);
                j+=3;
                k++;
                index++;
                row++;
                Thread.sleep(500);
            }
            fileRAFData.close();
            fileRAFBelahKetupat.close();
            System.out.println("-------------------------END PROCESS OF BELAH KETUPAT------------------------");

        } catch (ArithmeticException arithmeticException) {
            JOptionPane.showMessageDialog(null, "Terdapat Pembagian Dengan Bilangan (NOL)!!!");
        } catch(Throwable throwable){
            JOptionPane.showMessageDialog(null, throwable.getMessage());
        }
        
        }
    
}
