import MainMenu.VCMainMenu;

import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To chang this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Catur Rahmat
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws IOException {
        /*
        Scanner input = new Scanner(System.in);
        System.out.println("+===========================+");
        System.out.println("|         MAIN MENU         |");
        System.out.println("+===========================+");
        System.out.println("| 1. INPUT                  |");
        System.out.println("| 2. PROCESS                |");
        System.out.println("| 3. OUTPUT                 |");
        System.out.println("+===========================+");
        System.out.print("  PILIH = ");
        int pilih = input.nextInt();
                        RandomAccessFile RAFile = null;
                try{
                RAFile = new RandomAccessFile("C:\\Users\\Catur Rahmat\\Documents\\NetBeansProjects\\ProjectPBOTeori\\dataBangun2D3D.dat", "rw");
                
                }catch(FileNotFoundException ex){
                    System.out.println(ex.getMessage());
                }catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
        switch(pilih){
            case 1 : {
                System.out.print("Massukan Banyak Record : ");
                int record = input.nextInt();
                int allData = record*8;
                Integer[] P1 = new Integer[allData];
                Integer[] P2 = new Integer[allData];
                Integer[] L1 = new Integer[allData];
                Integer[] L2 = new Integer[allData];
                Integer[] D1 = new Integer[allData];
                Integer[] D2 = new Integer[allData];
                Integer[] T1 = new Integer[allData];
                Integer[] R1 = new Integer[allData];
                //THE RECORD
                //+======+======+======+======+======+======+=====+=====+
                //|  P1  |  P2  |  L1  |  L2  |  D1  |  D2  |  T  |  R  |
                //+======+======+======+======+======+======+=====+=====+
                //|      |      |      |      |      |      |     |     |//record  1
                //|      |      |      |      |      |      |     |     |//record  2
                //|      |      |      |      |      |      |     |     |//record ...
                //|      |      |      |      |      |      |     |     |//record  n
                //+======+======+======+======+======+======+=====+=====+
                int i = 0;
                int recordRow = 0;
                
                while(i < allData){
                    try{
                        RAFile.setLength(allData);
                //=================GET P1==================
                RAFile.seek(i);
                P1[i] = (int) Math.round(10*Math.random());
                if(P1[i] == 0){
                P1[i]+=2;
                }
                RAFile.write(P1[i]);
                System.out.print("Panjang 1["+recordRow+"]= " + P1[i] + "; ");
                i = i+1;
                
                //=================GET P2==================
                RAFile.seek(i);
                P2[i] = (int) Math.round(10*Math.random());
                if(P2[i] == 0){
                P2[i]+=2;
                }
                RAFile.write(P2[i]);
                System.out.print("Panjang 2["+recordRow+"]= " + P2[i] + "; ");
                i = i+1;
                
                //=================GET L1==================
                RAFile.seek(i);
                L1[i] = (int) Math.round(10*Math.random());
                if(L1[i] == 0){
                L1[i]+=2;
                }
                RAFile.write(L1[i]);
                System.out.print("Lebar 1["+recordRow+"]= " + L1[i] + "; ");
                i = i+1;
                
                //=================GET L2==================
                RAFile.seek(i);
                L2[i] = (int) Math.round(10*Math.random());
                if(L2[i] == 0){
                L2[i]+=2;
                }
                RAFile.write(L2[i]);
                System.out.print("Lebar 2["+recordRow+"]= " + L2[i] + "; ");
                i = i+1;
                
                //=================GET D1==================
                RAFile.seek(i);
                D1[i] = (int) Math.round(10*Math.random());
                if(D1[i] == 0){
                D1[i]+=2;
                }
                RAFile.write(D1[i]);
                System.out.print("Diagonal 1["+recordRow+"]= " + D1[i] + "; ");
                i = i+1;
                
                //=================GET D2==================
                RAFile.seek(i);
                D2[i] = (int) Math.round(10*Math.random());
                if(D2[i] == 0){
                D2[i]+=2;
                }
                RAFile.write(D2[i]);
                System.out.print("Diagonal 2["+recordRow+"]= " + D2[i] + "; ");
                i = i+1;
                
                //=================GET T1==================
                RAFile.seek(i);
                T1[i] = (int) Math.round(10*Math.random());
                if(T1[i] == 0){
                T1[i]+=2;
                }
                RAFile.write(T1[i]);
                System.out.print("Tinggi 1["+recordRow+"]= " + T1[i] + "; ");
                i = i+1;
                
                //=================GET R1==================
                RAFile.seek(i);
                R1[i] = (int) Math.round(10*Math.random());
                if(R1[i] == 0){
                R1[i]+=2;
                }
                RAFile.write(R1[i]);
                System.out.print("Jari-Jari 1["+recordRow+"]= " + R1[i] + "; ");
                i = i+1;
                recordRow++;
                        System.out.println();
                }catch(IOException ex){
                        System.out.println(ex.getMessage());
                }
                }
                break;
            }
            case 2 : {
                System.out.println(RAFile.readInt());
                
                System.out.println(RAFile.getFilePointer());
                /*try{
                    
                while(i < RAFile.getFilePointer()){
                    System.out.println("Saya");
                }
                }catch(FileNotFoundException ex){
                    System.out.println(ex.getMessage());
                }catch(IOException ex){
                    System.out.println(ex.getMessage());
                }
                
            }
            case 3 : {
                
            }
        }*/
        new VCMainMenu();
    }

}
