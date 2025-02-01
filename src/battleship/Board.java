package battleship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

//this class is the board that user puts ships

public class Board extends JPanel {
    private int[][] placementBoard;
    public static boolean vertical;
    public  JPanel[][] boardPanel = new JPanel[10][10];
    private JPanel finalBoard;

    Board() {

        placementBoard = new int[10][10];
        vertical = false;
        finalBoard = new JPanel(new GridLayout(10,10));
        finalBoard.setPreferredSize(new Dimension(500, 500));
        int i, j;
        for (i = 0; i < 10; i++) {
            for (j = 0; j < 10; j++) {
                boardPanel[i][j] = new JPanel();
                boardPanel[i][j].setPreferredSize(new Dimension(50, 50));
                boardPanel[i][j].setBackground(Color.cyan);
                boardPanel[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                placementBoard[i][j] = 0;
                finalBoard.add(boardPanel[i][j]);

                int finalI = i;
                int finalJ = j;
                boardPanel[i][j].addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if(ShipPlacement.currentShip ==null && placementBoard[finalI][finalJ]!=1) {
                            boardPanel[finalI][finalJ].setBackground(Color.cyan);
                            System.out.println("empty cell");
                        }else if(ShipPlacement.currentShip ==null && placementBoard[finalI][finalJ]==1){
                            System.out.println("Ship already placed");
                        }else if  (outOfBoundsRight(ShipPlacement.currentShip.getSizeOfShip(), finalI,finalJ) && vertical == false) {
                            for (int k = 0; k < ShipPlacement.currentShip.getSizeOfShip(); k++) {
                                placementBoard[finalI][finalJ + k] = 1;
                                boardPanel[finalI][finalJ + k].setBackground(Color.gray);
                            }
                            ShipPlacement.currentShip.deactivateShip();
                            ShipPlacement.currentShip.placedOnce();
                            ShipPlacement.currentShip = null;
                            ShipPlacement.shipPlaced++;
                            System.out.println(ShipPlacement.shipPlaced);
                            if(ShipPlacement.shipPlaced==5){
                                ShipPlacement.startGame.setEnabled(true);
                            }


                        }else if  (outOfBoundsDown(ShipPlacement.currentShip.getSizeOfShip(), finalI,finalJ) && vertical == true) {
                                for (int k = 0; k <ShipPlacement.currentShip.getSizeOfShip(); k++){
                                    placementBoard[finalI + k][finalJ] = 1;
                                    boardPanel[finalI + k][finalJ].setBackground(Color.gray);
                                }
                            ShipPlacement.currentShip.deactivateShip();
                            ShipPlacement.currentShip.placedOnce();
                            ShipPlacement.currentShip = null;
                            ShipPlacement.shipPlaced++;
                            System.out.println(ShipPlacement.shipPlaced);
                            if(ShipPlacement.shipPlaced==5){
                                ShipPlacement.startGame.setEnabled(true);
                            }

                        }else{
                            System.out.println("Empty");
                        }



                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        if(placementBoard[finalI][finalJ]==1){
                            boardPanel[finalI][finalJ].setBackground(Color.gray);
                        }else if(ShipPlacement.currentShip ==null){
                            boardPanel[finalI][finalJ].setBackground(Color.yellow);
                        }
                        else if(outOfBoundsRight(ShipPlacement.currentShip.getSizeOfShip(), finalI, finalJ) && vertical==false) {
                            for (int k = 0; k < ShipPlacement.currentShip.getSizeOfShip(); k++)
                                boardPanel[finalI][finalJ + k].setBackground(Color.green);

                        }
                        else if(outOfBoundsDown(ShipPlacement.currentShip.getSizeOfShip(), finalI, finalJ) && vertical==true) {
                            for (int k = 0; k < ShipPlacement.currentShip.getSizeOfShip(); k++)
                                boardPanel[finalI + k][finalJ].setBackground(Color.green);

                        }else
                            boardPanel[finalI][finalJ].setBackground(Color.red);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                        if (placementBoard[finalI][finalJ] == 1) {
                            boardPanel[finalI][finalJ].setBackground(Color.gray);
                         }else if(ShipPlacement.currentShip ==null){
                        boardPanel[finalI][finalJ].setBackground(Color.cyan);
                    }
                        else if (outOfBoundsRight(ShipPlacement.currentShip.getSizeOfShip(),finalI, finalJ) && vertical == false ) {
                            for (int k = 0; k < ShipPlacement.currentShip.getSizeOfShip(); k++) {
                                boardPanel[finalI][finalJ + k].setBackground(Color.cyan);
                            }
                        } else if (outOfBoundsDown(ShipPlacement.currentShip.getSizeOfShip(), finalI, finalJ) && vertical == true) {
                            for (int k = 0; k < ShipPlacement.currentShip.getSizeOfShip(); k++) {
                                boardPanel[finalI + k][finalJ].setBackground(Color.cyan);
                            }
                        } else {
                            boardPanel[finalI][finalJ].setBackground(Color.cyan);
                        }
                    }

                });
            }
        }
        add(finalBoard);
    }

    boolean outOfBoundsRight(int size,  int numOfRow, int numOfColumn){
        if(numOfColumn+size>10) {
            return false;
        }
        else {
            for (int i = numOfColumn; i < numOfColumn + size; i++) {
                if(placementBoard[numOfRow][i]==1){
                    return false;
                }

            }
        }
        return true;
    }
    boolean outOfBoundsDown(int size, int numOfRow, int numOfColumn){
        if(numOfRow+size>10){
            return false;
        }

        else {
            for (int i = numOfRow; i < numOfRow + size; i++) {
                if(placementBoard[i][numOfColumn]==1){
                    return false;
                }
            }
        }
        return true;
    }
public void getRandomBoard(){
    int [] sizes  = {5,4,3,3,2};
        int placed =0;
        int row,column;
    Random randomVertical = new Random();
    Random randomCol = new Random();
    Random randomRow = new Random();

        while(placed<5){
            vertical = randomVertical.nextBoolean();
            column = randomCol.nextInt(10);
            row = randomRow.nextInt(10);
            System.out.println("vertical is "+vertical);
            System.out.println("size of ship is: "+sizes[placed]);
            if(outOfBoundsRight(sizes[placed], row,column) && vertical == false) {
                for (int k = 0; k < sizes[placed]; k++) {
                    placementBoard[row][column + k] = 1;
                    System.out.print("["+row+" , "+(column+k)+"]");
                }
                System.out.println();
                placed++;
                continue;
            }
                else if(outOfBoundsDown(sizes[placed], row,column) && vertical == true){
                for (int k = 0; k <sizes[placed]; k++){
                    placementBoard[row + k][column] = 1;
                    System.out.print("["+(row+k)+" , "+column+"]");
                }
                System.out.println();
                placed++;
                continue;
                }else{
                System.out.println("cannot be placed");
                continue;
            }


        }
        for(int i = 0 ; i<10;i++){
            for(int k =0 ; k<10;k++){
                System.out.print(placementBoard[i][k]+"\t");
            }
            System.out.println();
        }

}

public JPanel[][] getBoard(){
     return boardPanel;
}

public int[][] getPlacementBoard(){
        return placementBoard;
    }

    public void setBoardValue(int i,int k, int value){
        placementBoard[i][k] = value;
    }


}