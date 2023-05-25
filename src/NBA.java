import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


    public class NBA implements ActionListener{
        private JFrame mainFrame;
        private JLabel label1;
        private JLabel label2;
        private JTextArea ta;
        private JPanel Panel1;
        private int WIDTH=1200;
        private int HEIGHT=1200;
        public ReadJson readme;
        public String hold;


        public NBA() {
//        readme = new ReadJson();
//        try {
//            readme.pull();
//            hold= readme.abilityName;
//            System.out.println(hold);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        prepareGUI();
            readme = new ReadJson();
        }

        public void POKEgame(){


            try {
                readme.pull();
                hold = readme.abilityName;
                System.out.println("hold: "+hold);
                ta.append(hold);


            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        public static void main(String[] args) {
            PokeGame PokeGame = new PokeGame();
            PokeGame.prepareGUI();
        }

        private void prepareGUI(){
            mainFrame = new JFrame("Poke Game");
            mainFrame.setSize(WIDTH, HEIGHT);
            Panel1 = new JPanel();

            mainFrame.setLayout(new BorderLayout());
            Panel1.setLayout(new GridLayout(1,1));



            label1 = new JLabel("LABEL 1", JLabel.CENTER);
            label2 = new JLabel("LABEL 1", JLabel.CENTER);
//        statusLabel.setSize(350, 100);

            mainFrame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent windowEvent) {
                    System.exit(0);
                }
            });

            JButton button1 = new JButton ("PREVIOUS ");
            JButton button2 = new JButton ("NEXT ");
            JButton button3  = new JButton ("CARD INFORMATION: ");

            ta = new JTextArea();
            ta.setBounds(50, 5, WIDTH-100, HEIGHT-50);


            button1.setActionCommand("PREVIOUS");
            button2.setActionCommand("NEXT");
            button3.setActionCommand("CARD INFORMATION: ");


            button1.addActionListener(new ButtonClickListener());
            button2.addActionListener(new ButtonClickListener());
            button3.addActionListener(new ButtonClickListener());



            mainFrame.add(button1, BorderLayout.WEST);
            mainFrame.add(button2,BorderLayout.EAST );
            mainFrame.add(button3, BorderLayout.NORTH);
            Panel1.add(ta);
            mainFrame.add(Panel1,BorderLayout.CENTER);

            mainFrame.setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {

        }
        private class ButtonClickListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                if (command.equals("CARD INFORMATION: ")){
                    POKEgame();
                    System.out.println("\n");
                }
                if (command.equals ("NEXT") && readme.pokeNUM <= 3){
                    readme.pokeNUM ++;
                }
                else{
                    readme.pokeNUM = 0;
                }
            }
        }
    }

}
