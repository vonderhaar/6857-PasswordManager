package sixeightfiveseven;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

public class GetPacket {

    private JPanel panel1;
    private JButton ubuntuButton;
    private JButton myMacButton;
    private JTextArea proof;
    private JTextField proofCopyAndPasteTextField;
    private JButton AddNewServer;
    private JLabel Servers;

    private BigInteger ubuntuUserId = new BigInteger("050997");
    private BigInteger macUserId = new BigInteger("082096");


    public GetPacket() {
        proof.setLineWrap(true);
        try {
            ubuntuButton.addActionListener(new getPacket("Ubuntu"));
            myMacButton.addActionListener(new getPacket("Mac"));
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("GetPacket");
        frame.setContentPane(new GetPacket().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private class getPacket  implements ActionListener {

        private Prover prover;

        public getPacket(String form) throws NoSuchAlgorithmException {
            if (form.equals("Ubuntu")) {
                this.prover = new Prover(ubuntuUserId);
            }
            else if (form.equals("Mac")) {
                this.prover = new Prover(macUserId);
            }
            else {
                throw new RuntimeException("Something went wrong, check hardcoded user IDS");
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            proof.setText(prover.getPacket().toSSH());
        }
    }
}
