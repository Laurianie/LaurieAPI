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
        private JScrollPane scrollBar;



        public NBA() {
            readme = new ReadJson();
        }

        public void nba(){
            try {
                readme.pull();
                hold = readme.dataName;
                ta.append(hold);

            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        public static void main(String[] args) {
            NBA nba = new NBA();
            nba.prepareGUI();
        }

        private void prepareGUI(){
            mainFrame = new JFrame("FIND AN NBA PLAYER !");
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
            JButton button2 = new JButton ("START ");
            JButton button3  = new JButton ("PLAYER INFORMATION: ");

            ta = new JTextArea();
            ta.setBounds(50, 5, WIDTH-100, HEIGHT-50);
            ta.setBackground(Color.black);
            ta.setForeground(Color.white);
            ta.setFont(new Font("Trebuchet MS", Font.BOLD, 35));
            button1.setBackground(Color.black);
            button1.setForeground(Color.blue);
            button1.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
            button2.setBackground(Color.black);
            button2.setForeground(Color.blue);
            button2.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
            button3.setBackground(Color.black);
            button3.setForeground(Color.magenta);
            button3.setFont(new Font("Trebuchet MS", Font.BOLD, 15));


            button1.setActionCommand("PREVIOUS");
            button2.setActionCommand("START");
            button3.setActionCommand("PLAYER INFORMATION: ");


            button1.addActionListener(new ButtonClickListener());
            button2.addActionListener(new ButtonClickListener());
            button3.addActionListener(new ButtonClickListener());



            mainFrame.add(button1, BorderLayout.WEST);
            mainFrame.add(button2,BorderLayout.EAST );
            mainFrame.add(button3, BorderLayout.NORTH);
            Panel1.add(ta);
            mainFrame.add(Panel1,BorderLayout.CENTER);
            scrollBar = new JScrollPane(ta);
            scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            mainFrame.add(scrollBar,BorderLayout.CENTER);

            mainFrame.setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {

        }
        private class ButtonClickListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                if (command.equals("PLAYER INFORMATION: ")){
                    nba();
                    System.out.println("\n");
                }
                if (command.equals ("START") && readme.playerNUM <= 25){
                    readme.playerNUM ++;
                }
                if (command.equals("PREVIOUS")){
                    readme.playerNUM = readme.playerNUM - 1;
                }
            }
        }
    }


