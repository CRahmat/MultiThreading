/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataList;

import MainMenu.VCMainMenu;
import input.InputView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import javafx.scene.control.ListView;
import javax.swing.JOptionPane;

public class DataListController{
    DataListView dataListView;
    int dataLenght;
    int i;
    int j;
    int index;
    int row;
    RandomAccessFile fileRAFData = null;
    RandomAccessFile RAFLenght = null;
    
    public DataListController(DataListView dataListView) {
        this.dataListView = dataListView;
        getListData();
        this.dataListView.lback.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                dataListView.frame.setVisible(false);
            }
        });
    }
    public void getListData(){
        try {
            fileRAFData = new RandomAccessFile("src\\saveData\\Data-Bangun.dat", "rw");
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
            Integer[] panjang1 = new Integer[dataLenght];//kalau trapesium ini sisi sejajar kedua
            Integer[] panjang2 = new Integer[dataLenght];//kalau trapesium ini sisi sejajar kedua
            Integer[] lebar1 = new Integer[dataLenght];
            Integer[] lebar2 = new Integer[dataLenght];
            Integer[] diagonal1 = new Integer[dataLenght];
            Integer[] diagonal2 = new Integer[dataLenght];
            Integer[] tinggi = new Integer[dataLenght];
            Integer[] jariJari = new Integer[dataLenght];
            //-----------------------------------------------------------------------------------------------
            j = (int)fileRAFData.getFilePointer();
            fileRAFData.setLength(dataLenght);
            
            index = 0;
            while (j < fileRAFData.length()) {
                fileRAFData.seek(j);
                panjang1[index] = fileRAFData.read();//ini yang paling penting untuk baca data dari RAF 
                j++;
                fileRAFData.seek(j);
                panjang2[index] = fileRAFData.read();//ini yang paling penting untuk baca data dari RAF
                j++;
                fileRAFData.seek(j);
                lebar1[index] = fileRAFData.read();//ini yang paling penting untuk baca data dari RAF
                j++;
                fileRAFData.seek(j);
                lebar2[index] = fileRAFData.read();//ini yang paling penting untuk baca data dari RAF
                j++;
                fileRAFData.seek(j);
                diagonal1[index] = fileRAFData.read();//ini yang paling penting untuk baca data dari RAF
                j++;
                fileRAFData.seek(j);
                diagonal2[index] = fileRAFData.read();//ini yang paling penting untuk baca data dari RAF
                j++;
                fileRAFData.seek(j);
                tinggi[index] = fileRAFData.read();//ini yang paling penting untuk baca data dari RAF
                j++;
                fileRAFData.seek(j);
                jariJari[index] = fileRAFData.read();//ini yang paling penting untuk baca data dari RAF
                j++;
                dataListView.dataInput.insertRow(dataListView.dataInput.getRowCount(), new Object[]{
                index+1,panjang1[index], panjang2[index], lebar1[index], lebar2[index], diagonal1[index], diagonal2[index], tinggi[index], jariJari[index]
                });
                index++;
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
    
}
