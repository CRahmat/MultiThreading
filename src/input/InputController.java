/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 * @author Catur Rahmat
 */
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
        inputView.lback.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                inputView.frame.setVisible(false);
                new VCMainMenu();
            }
        });
        inputView.linput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int rowCount = inputView.list.getRowCount();
                //Deleted All Data In JTable when JTable Not Empty
                if(rowCount > 0 ){
                for (int i = rowCount - 1; i >=0 ; i--){
                    inputView.dataInput.removeRow(i);
                }
                }
                try {
                    RAFile = new RandomAccessFile("src\\saveData\\Data-Bangun.dat", "rw");
                    RAFLenght = new RandomAccessFile("src\\saveData\\Data-Lenght.dat", "rw");
                } catch (FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                //DEKLARASI--------------------------------------------------------------------------------
                try {
                    record = Integer.parseInt(inputView.linput.getText()); //Get From Input View
                    dataLenght = record * 8;
                    j = 0;
                    RAFLenght.seek(j);
                    RAFLenght.writeInt(dataLenght);
                    
                } catch (Exception e) {
                }
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
                    RAFile.setLength(dataLenght);
                    while (i < dataLenght) {
                        //=================GET P1==================
                        RAFile.seek(i);
                        P1[index] = (int) Math.round(10 * Math.random());
                        if (P1[index] == 0) {
                            P1[index] += 2;
                        }
                        RAFile.write(P1[index]);
                        i = i + 1;

                        //=================GET P2==================
                        RAFile.seek(i);
                        P2[index] = (int) Math.round(10 * Math.random());
                        if (P2[index] == 0) {
                            P2[index] += 2;
                        }
                        RAFile.write(P2[index]);
                        i = i + 1;

                        //=================GET L1==================
                        RAFile.seek(i);
                        L1[index] = (int) Math.round(10 * Math.random());
                        if (L1[index] == 0) {
                            L1[index] += 2;
                        }
                        RAFile.write(L1[index]);
                        i = i + 1;

                        //=================GET L2==================
                        RAFile.seek(i);
                        L2[index] = (int) Math.round(10 * Math.random());
                        if (L2[index] == 0) {
                            L2[index] += 2;
                        }
                        RAFile.write(L2[index]);
                        i = i + 1;

                        //=================GET D1==================
                        RAFile.seek(i);
                        D1[index] = (int) Math.round(10 * Math.random());
                        if (D1[index] == 0) {
                            D1[index] += 2;
                        }
                        RAFile.write(D1[index]);
                        i = i + 1;

                        //=================GET D2==================
                        RAFile.seek(i);
                        D2[index] = (int) Math.round(10 * Math.random());
                        if (D2[index] == 0) {
                            D2[index] += 2;
                        }
                        RAFile.write(D2[index]);
                        i = i + 1;

                        //=================GET T1==================
                        RAFile.seek(i);
                        T1[index] = (int) Math.round(10 * Math.random());
                        if (T1[index] == 0) {
                            T1[index] += 2;
                        }
                        RAFile.write(T1[index]);
                        i = i + 1;

                        //=================GET R1==================
                        RAFile.seek(i);
                        R1[index] = (int) Math.round(10 * Math.random());
                        if (R1[index] == 0) {
                            R1[index] += 2;
                        }
                        RAFile.write(R1[index]);
                        inputView.dataInput.insertRow(inputView.dataInput.getRowCount(), new Object[]{
                        index+1,P1[index], P2[index], L1[index], L2[index], D1[index], D2[index], T1[index], R1[index]
                        });
                        
                        i = i + 1;
                        index++;
                    }
                    RAFile.close();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

    }
}

