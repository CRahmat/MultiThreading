package input;

import MainMenu.VCMainMenu;
import bangundatar.Persegi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class InputController {
    InputView inputView;
    int dataLenght;
    int i;
    int j;
    int index;
    int record;
    RandomAccessFile RAFile = null;
    RandomAccessFile RAFLenght = null;
    public InputController(InputView inputView) {
        this.inputView = inputView;
        inputView.lback.addMouseListener(new MouseAdapter() {//Action Listener Tombol Back
            @Override
            public void mouseClicked(MouseEvent me) {//Event Handler  Ketika Icon Back Di Klik
                inputView.frame.setVisible(false);//Menyembunyikan Tampilan Input Dari Monitor
                new VCMainMenu();//Instansiasi Obyek VCMain Menu Digunakan Untuk Memanggil Tampilan Utama Dan controller Tampilan Utama
            }
        });
        inputView.linput.addActionListener(new ActionListener() {//Action Listerner Ketika User Selesai Menginput Banyak Row
            @Override
            public void actionPerformed(ActionEvent ae) {//Event Handler
                int rowCount = inputView.list.getRowCount();
                //Menghapus Data Dari JTable Sebelumnya Jika Di Input Lagi Adar Tidak Bertambah Dengan Data Sebelumnya
                if(rowCount > 0 ){
                for (int i = rowCount - 1; i >=0 ; i--){
                    inputView.dataInput.removeRow(i);//Fungsi Untuk Menghapus Data Dalam JTabel
                }
                }
                try {
                    //Instansiasi Obyek Random Access File Untuk Simpan Data Lenght dan Data untuk Perhitungan
                    //Data Lenght Disimpan Dalam File Agar Saat User Membuka Aplikasi(Bukan Pertama Kali) Tanpa Menginputkan Jumlah Row Terlebih Dahulu
                    //Perhitungan Tetap Dapat Dilakukan mengingat Di Dalam File Masih Terdapat Data Sebelumnya
                    //Jika Data Lenght Tidak Disimpan Dalam File maka perhitungan Tidak dapat di lakukan, 
                    //Sedangkan Di Dalam File Masih Terdapat Data Panjang, Lebar...dll
                    RAFile = new RandomAccessFile("src\\saveData\\Data-Bangun.dat", "rw");
                    RAFLenght = new RandomAccessFile("src\\saveData\\Data-Lenght.dat", "rw");
                    //Convert Data ke Integer daikarenakan inputan Dari View berjenis String
                    record = Integer.parseInt(inputView.linput.getText());
                    dataLenght = record * 8;//DataLenght = record dikali 8 karena jumlah data untuk perhitungan ada 8
                    RAFLenght.seek(0);//Menempatkan Pointer pada posisi 0
                    RAFLenght.writeInt(dataLenght);//Menulis Data Lenght Ke File
                    RAFLenght.close();//Menutup File
                } catch (FileNotFoundException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                //Instansiasi Obyek untuk Data Perhitungan
                Integer[] P1 = new Integer[dataLenght];
                Integer[] P2 = new Integer[dataLenght];
                Integer[] L1 = new Integer[dataLenght];
                Integer[] L2 = new Integer[dataLenght];
                Integer[] D1 = new Integer[dataLenght];
                Integer[] D2 = new Integer[dataLenght];
                Integer[] T1 = new Integer[dataLenght];
                Integer[] R1 = new Integer[dataLenght];
                //----------------------------------------------------------------------------------------
                index = 0;
                try {
                    i = (int) RAFile.getFilePointer();
                    RAFile.setLength(dataLenght);//Set Lenght dari RAFile untuk menyimpan data Math Random
                    while (i < dataLenght) {
                        //=================GET P1==================
                        RAFile.seek(i);//Menempatkan pointer agar data tidak tertimpa
                        P1[index] = (int) Math.round(10 * Math.random());
                        if (P1[index] == 0) {
                            P1[index] += 2;
                        }
                        RAFile.write(P1[index]);//Menulis Data Ke File dalam Bentuk Byte
                        i = i + 1;
                        //=================GET P2==================
                        RAFile.seek(i);//Menempatkan pointer agar data tidak tertimpa
                        P2[index] = (int) Math.round(10 * Math.random());
                        if (P2[index] == 0) {
                            P2[index] += 2;
                        }
                        RAFile.write(P2[index]);//Menulis Data Ke File dalam Bentuk Byte
                        i = i + 1;
                        //=================GET L1==================
                        RAFile.seek(i);//Menempatkan pointer agar data tidak tertimpa
                        L1[index] = (int) Math.round(10 * Math.random());
                        if (L1[index] == 0) {
                            L1[index] += 2;
                        }
                        RAFile.write(L1[index]);//Menulis Data Ke File dalam Bentuk Byte
                        i = i + 1;
                        //=================GET L2==================
                        RAFile.seek(i);//Menempatkan pointer agar data tidak tertimpa
                        L2[index] = (int) Math.round(10 * Math.random());
                        if (L2[index] == 0) {
                            L2[index] += 2;
                        }
                        RAFile.write(L2[index]);//Menulis Data Ke File dalam Bentuk Byte
                        i = i + 1;
                        //=================GET D1==================
                        RAFile.seek(i);//Menempatkan pointer agar data tidak tertimpa
                        D1[index] = (int) Math.round(10 * Math.random());
                        if (D1[index] == 0) {
                            D1[index] += 2;
                        }
                        RAFile.write(D1[index]);//Menulis Data Ke File dalam Bentuk Byte
                        i = i + 1;
                        //=================GET D2==================
                        RAFile.seek(i);//Menempatkan pointer agar data tidak tertimpa
                        D2[index] = (int) Math.round(10 * Math.random());
                        if (D2[index] == 0) {
                            D2[index] += 2;
                        }
                        RAFile.write(D2[index]);//Menulis Data Ke File dalam Bentuk Byte
                        i = i + 1;
                        //=================GET T1==================
                        RAFile.seek(i);//Menempatkan pointer agar data tidak tertimpa
                        T1[index] = (int) Math.round(10 * Math.random());
                        if (T1[index] == 0) {
                            T1[index] += 2;
                        }
                        RAFile.write(T1[index]);//Menulis Data Ke File dalam Bentuk Byte
                        i = i + 1;
                        //=================GET R1==================
                        RAFile.seek(i);//Menempatkan pointer agar data tidak tertimpa
                        R1[index] = (int) Math.round(10 * Math.random());
                        if (R1[index] == 0) {
                            R1[index] += 2;
                        }
                        RAFile.write(R1[index]);//Menulis Data Ke File dalam Bentuk Byte
                        //MEMASSUKAN DATA DALAM JTable VIEW
                        inputView.dataInput.insertRow(inputView.dataInput.getRowCount(), new Object[]{
                        index+1,P1[index], P2[index], L1[index], L2[index], D1[index], D2[index], T1[index], R1[index]
                        });
                        i = i + 1;
                        index++;
                    }
                    RAFile.close();//Menutup File
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

    }
}

