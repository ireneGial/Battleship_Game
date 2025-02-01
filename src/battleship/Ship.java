package battleship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Ship extends JPanel {
    private boolean isSelected;
    private int sizeOfShip; //if it is 5 box ship etc depends the size will turn gray.
    private JPanel[] shipMatrix;


    Ship(int size) {
        this.isSelected = false;
        this.sizeOfShip = size;
        shipMatrix = new JPanel[5];
        shipCreate();
    }

    public void shipCreate() {
        int i;

        for (i = 0; i < 5; i++) {
            shipMatrix[i] = new JPanel();
            shipMatrix[i].setPreferredSize(new Dimension(50, 50));
            shipMatrix[i].setBorder(BorderFactory.createLineBorder(Color.black));
            if (i < sizeOfShip) {
                shipMatrix[i].setBackground(Color.gray);
                shipMatrix[i].addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        setShipState();

                    }

                    @Override
                    public void mousePressed(MouseEvent me) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent me) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent me) {
                    }

                    @Override
                    public void mouseExited(MouseEvent me) {
                    }

                });
            }
            add(shipMatrix[i]);
        }

    }

    public void setShipState() {
        int i;
        if (ShipPlacement.currentShip != null) {
            ShipPlacement.currentShip.deactivateShip();
        }
        if (isSelected == false) {
            isSelected = true;
            System.out.println("Ship with size " + sizeOfShip + " is selected");
            for (i = 0; i < sizeOfShip; i++) {
                shipMatrix[i].setBackground(Color.yellow);
            }
            ShipPlacement.currentShip = this;
        }
    }

    public boolean getIsSelected() {
        return isSelected;
    }

    public void deactivateShip() {
        this.isSelected = false;
        int i;
        for (i = 0; i < this.sizeOfShip; i++) {
            this.shipMatrix[i].setBackground(Color.gray);
        }
    }

    public int getSizeOfShip() {
        return sizeOfShip;
    }

    public void placedOnce() {
        for (int i = 0; i < 5; i++) {
            if (i < sizeOfShip) {
                this.shipMatrix[i].removeMouseListener(this.shipMatrix[i].getMouseListeners()[0]);
            }
        }


    }
}
