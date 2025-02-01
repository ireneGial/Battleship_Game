package battleship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class Gameplay extends JFrame {
    private JPanel[][] computerBoard,playerBoard;
    private Player player;
    private Player computer;
    private JPanel titlesPanel , boardsPanel;

    Gameplay(Player player, Player computer){
        this.player = player;
        this.computer = computer;
        setSize(1200,550);
        titlesPanel = new JPanel(new GridLayout(1,2));
        titlesPanel.add(new JLabel("Player Board"));
        titlesPanel.add(new JLabel("Computer Board"));
        add(titlesPanel,BorderLayout.NORTH);
        boardsPanel = new JPanel(new GridLayout(1,2));
        boardsPanel.add(createPlayerPanel());
        boardsPanel.add(hideComputerBoard());
        add(boardsPanel,BorderLayout.SOUTH);
        //pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JPanel hideComputerBoard(){
       JPanel hiddenBoard = new JPanel(new GridLayout(10,10));
       hiddenBoard.setSize(500,500);
       computerBoard = computer.getPlayerBoard().getBoard();
        for(int i = 0 ; i<10; i++){
            for(int k = 0 ; k<10; k++){
                computerBoard[i][k].removeMouseListener(this.computerBoard[i][k].getMouseListeners()[0]);
                int finalI = i;
                int finalK = k;
                computerBoard[i][k].addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        if(computer.getPlayerBoard().getPlacementBoard()[finalI][finalK]==0){
                            System.out.println("["+(finalI)+" , "+finalK+"] Sorry, no ship here");
                            computerBoard[finalI][finalK].setBackground(Color.white);
                            removeListener(finalI,finalK);
                            computerPlay();
                        }
                        else{
                            computerBoard[finalI][finalK].setBackground(Color.red);
                            System.out.println("["+finalI+" , "+finalK+"] Ship was attacked");
                            player.updateScore();
                            removeListener(finalI,finalK);
                            computerPlay();
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

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }


                });
               // computerBoard[i][k].removeMouseListener(this.computerBoard[i][k].getMouseListeners()[2]);

                hiddenBoard.add(computerBoard[i][k]);

            }
        }
       return hiddenBoard;



    }

    public JPanel createPlayerPanel(){
       JPanel panel = new JPanel(new GridLayout(10,10));
       playerBoard = player.getPlayerBoard().getBoard();
       panel.setSize(500,500);
        for(int i = 0 ; i<10; i++) {
            for (int k = 0; k < 10; k++) {
                if(player.getPlayerBoard().getPlacementBoard()[i][k]==1){
                    playerBoard[i][k].setBackground(Color.gray);
                }
                panel.add(playerBoard[i][k]);
                playerBoard[i][k].removeMouseListener(this.playerBoard[i][k].getMouseListeners()[0]);
                int finalI = i;
                int finalK = k;


            }
        }
        return panel;
    }

    public void removeListener(int i, int k){
        computerBoard[i][k].removeMouseListener(this.computerBoard[i][k].getMouseListeners()[0]);
    }


    public void computerPlay(){
        if(player.getScore() ==17){
            System.out.println("winner is "+player.getUsername());
            JOptionPane.showMessageDialog(null,"winner is "+player.getUsername()+"!");
            
            return;
        }

        Random randomRow = new Random();
        Random randomColumn = new Random();
        int row = randomRow.nextInt(10);
        int column = randomColumn.nextInt(10);
        while(player.getPlayerBoard().getPlacementBoard()[row][column]==3){
            row = randomRow.nextInt(10);
            column = randomColumn.nextInt(10);
        }

        if(player.getPlayerBoard().getPlacementBoard()[row][column]==1){
            player.getPlayerBoard().setBoardValue(row,column,3);
            playerBoard[row][column].setBackground(Color.red);
            computer.updateScore();
            System.out.println("["+row+" , "+column+"] Ship was attacked");
            if(computer.getScore()==17){
                System.out.println("winner is computer");
                JOptionPane.showMessageDialog(null,"winner is computer!");
                return;
            }
        }else{
            player.getPlayerBoard().setBoardValue(row,column,3);
            playerBoard[row][column].setBackground(Color.white);
            System.out.println("["+row+" , "+column+"] Sorry, no ship here");

        }

    }


}
