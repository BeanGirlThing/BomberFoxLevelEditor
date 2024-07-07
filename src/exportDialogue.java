import javax.swing.*;
import java.awt.event.*;

public class exportDialogue extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonClose;

    public JTextPane exportText;

    public exportDialogue(String exportTextString) {
        exportText.setText(exportTextString);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onClose();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onClose();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onClose();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onClose() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        exportDialogue dialog = new exportDialogue("Example");
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
