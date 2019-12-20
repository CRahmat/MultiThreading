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
    
    public DataListController(DataListView dataListView) {//Constructor dari Data List
        this.dataListView = dataListView;
        getListData();
        this.dataListView.lback.addMouseListener(new MouseAdapter() {//Event Handling tombol back pada Data List
            @Override
            public void mouseClicked(MouseEvent me) {
                dataListView.frame.setVisible(false);
                //Ketika Tombol back Di Klik Maka Tampilan Dari Data List akan Di tutup
            }
        });
    }
    public void getListData(){
        try {
            fileRAFData = new RandomAccessFile("src\\saveData\\Data-Bangun.dat", "rw");
            RAFLenght = new RandomAccessFile("src\\saveData\\Data-Lenght.dat", "rw");
            RAFLenght.seek(0);//File Selalu Berada Pada File Pointer 0 (Hanya 1 Data)
            dataLenght = RAFLenght.readInt();//Baca Lenght Dari File
            RAFLenght.close();//Close File Lenght   

            Integer[] panjang1 = new Integer[dataLenght];
            Integer[] panjang2 = new Integer[dataLenght];
            Integer[] lebar1 = new Integer[dataLenght];
            Integer[] lebar2 = new Integer[dataLenght];
            Integer[] diagonal1 = new Integer[dataLenght];
            Integer[] diagonal2 = new Integer[dataLenght];
            Integer[] tinggi = new Integer[dataLenght];
            Integer[] jariJari = new Integer[dataLenght];
            //-----------------------------------------------------------------------------------------------
            j = (int)fileRAFData.getFilePointer();
            fileRAFData.setLength(dataLenght);//Mengatur File Lenght
            index = 0;
            while (j < fileRAFData.length()) {
                fileRAFData.seek(j);//Penyesesuaian Pointer untuk Baca File
                panjang1[index] = fileRAFData.read();//Membaca data Panjang 1 dari File 
                j++;
                fileRAFData.seek(j);//Penyesesuaian Pointer untuk Baca File
                panjang2[index] = fileRAFData.read();//Membaca data Panjang 2 dari File
                j++;
                fileRAFData.seek(j);//Penyesesuaian Pointer untuk Baca File
                lebar1[index] = fileRAFData.read();//Membaca data Lebar 1 dari File
                j++;
                fileRAFData.seek(j);//Penyesesuaian Pointer untuk Baca File
                lebar2[index] = fileRAFData.read();//Membaca data Lebar 2 dari File
                j++;
                fileRAFData.seek(j);//Penyesesuaian Pointer untuk Baca File
                diagonal1[index] = fileRAFData.read();//Membaca data Diagonal 1 dari File
                j++;
                fileRAFData.seek(j);//Penyesesuaian Pointer untuk Baca File
                diagonal2[index] = fileRAFData.read();//Membaca data Diagonal 2 1 dari File
                j++;
                fileRAFData.seek(j);//Penyesesuaian Pointer untuk Baca File
                tinggi[index] = fileRAFData.read();//Membaca data Tinggi dari File
                j++;
                fileRAFData.seek(j);//Penyesesuaian Pointer untuk Baca File
                jariJari[index] = fileRAFData.read();//Membaca data Jari Jari 1 dari File
                j++;
                //INPUT DATA KE DALAM JTABLE
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
