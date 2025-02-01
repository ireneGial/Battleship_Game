package battleship;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartFrame extends JFrame {
    private JTextField nameTxt;
    private JPanel mainPanel;
    private JButton btn;
    private ShipPlacement shipPlacement;

    StartFrame(){
        super("BattleShip - Player's Details");
        setSize(250,130);
        nameTxt = new JTextField(20);
        btn = new JButton("OK");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nameTxt.getText().equals("")){
                   JOptionPane.showMessageDialog(null,"please enter your name");
                }else {
                     shipPlacement = new ShipPlacement(nameTxt.getText());
                    dispose();
                }
            }
        });

        mainPanel = new JPanel();
        mainPanel.add(new JLabel("Please insert your name and press OK"),BorderLayout.NORTH);
        mainPanel.add(nameTxt,BorderLayout.CENTER);
        mainPanel.add(btn,BorderLayout.SOUTH);
        add(mainPanel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


}
