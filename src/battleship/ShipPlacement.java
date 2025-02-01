
package battleship;

//import jdk.swing.interop.SwingInterOpUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ShipPlacement extends JFrame {
    private Player player1,player2;
    private JPanel shipPanel,boardPanel;
    private JButton rotateShip, helpbtn;
    public static JButton startGame;
    private JLabel title;
    public static Ship ship5,ship4,ship3a,ship3b,ship2;
    //private Ship[] shipMatrix;
   // public String[] isPlaced;
    public static int shipPlaced;
   // private int[][] placementBoard;
    private Board board;
    public static Ship currentShip;

    ShipPlacement(String playerName) {
        super("BattleShip - Ships' Placement");
        this.setResizable(false);
        //placementBoard = new int[10][10];
        currentShip = null;
        shipPlaced = 0;
        //isPlaced = new String[5];
        player1 = new Player(playerName);
        player2 = new Player("Computer"); 
        title = new JLabel("Please select ships from the left and place them in your board. Press \"start\" game when you are ready!");
        add(title, BorderLayout.NORTH);
        shipPanel = new JPanel(new GridLayout(5, 1));
        board = new Board();
        add(board,BorderLayout.EAST);
        initializeShips();
        buttonsPlaced();
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initializeShips(){
        ship5 = new Ship(5);
        ship4 = new Ship(4);
        ship3a = new Ship(3);
        ship3b = new Ship(3);
        ship2 = new Ship(2);

        shipPanel.add(ship5);
        shipPanel.add(ship4);
        shipPanel.add(ship3a);
        shipPanel.add(ship3b);
        shipPanel.add(ship2);
        add(shipPanel, BorderLayout.WEST);


    }

    void buttonsPlaced(){
        JPanel buttonPanel = new JPanel();
        rotateShip = new JButton("Rotate");
        rotateShip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Board.vertical == false){
                    Board.vertical = true;
                }
                else{
                    Board.vertical = false;
                }

            }
        });

        buttonPanel.add(rotateShip, BorderLayout.EAST);
        startGame = new JButton("Start Game");
        startGame.setEnabled(false);
        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player1.setPlayerBoard(board);
                Board computerBoard = new Board();
                computerBoard.getRandomBoard();
                player2.setPlayerBoard(computerBoard);
                new Gameplay(player1,player2);
                dispose();
            }
        });
        buttonPanel.add(startGame, BorderLayout.CENTER);

        helpbtn = new JButton("Help");
        helpbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"On one grid the player arranges ships and records the shots by the opponent. On the other grid the player records their own shots.\n" +
                        "Before play begins, each player secretly arranges their ships on their primary grid. Each ship occupies a number of consecutive squares on the grid, arranged either horizontally or vertically. \nThe number of squares for each ship is determined by the type of the ship.\n The ships cannot overlap (i.e., only one ship can occupy any given square in the grid).\n The types and numbers of ships allowed are the same for each player");
            }
        });
        buttonPanel.add(helpbtn,BorderLayout.WEST);
        add(buttonPanel,BorderLayout.SOUTH);


    }


}
