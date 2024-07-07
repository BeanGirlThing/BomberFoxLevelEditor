import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainForm {
    private JPanel basePanel;
    private JButton exportButton;
    private JRadioButton foxSpawnRadioButton;
    private JRadioButton wolfSpawnRadioButton;
    private JRadioButton wallRadioButton;
    private JPanel levelEditorPanel;

    private int width = 16;
    private int height = 7;

    private int brush = 1;

    private levelElement[][] checkSheet;

    static JFrame frame;

    public mainForm() {
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder export = new StringBuilder();
                export.append("uint8_t levelX[128] = {");
                for (int w = 0; w < width; w++) {
                    for (int h = 0; h < height; h++) {
                        export.append(checkSheet[w][h].getElementType());
                        export.append(",");
                    }
                }
                export.deleteCharAt(export.length() - 1);
                export.append("};");
                JDialog dialog = new exportDialogue(export.toString());
                dialog.pack();
                dialog.setTitle("Export");
                dialog.setVisible(true);
            }
        });

        foxSpawnRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wolfSpawnRadioButton.setSelected(false);
                wallRadioButton.setSelected(false);
                brush = 1;

            }
        });
        wolfSpawnRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                foxSpawnRadioButton.setSelected(false);
                wallRadioButton.setSelected(false);
                brush = 2;
            }
        });
        wallRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                foxSpawnRadioButton.setSelected(false);
                wolfSpawnRadioButton.setSelected(false);
                brush = 3;
            }
        });
    }

    private void createUIComponents() {

        levelEditorPanel = new JPanel(new GridLayout(height,width));
        checkSheet = new levelElement[width][height];

        for (int i = 0; i < width; i++) {
            for (int a = 0; a < height; a++) {
                checkSheet[i][a] = new levelElement();
                checkSheet[i][a].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        levelElement pressedBox = (levelElement) e.getSource();
                        if(!pressedBox.isSelected()) {
                            pressedBox.setBackground(new Color(255, 255, 255));
                            pressedBox.setElementType(0);
                        } else {
                            switch (brush) {
                                case 1:
                                    pressedBox.setBackground(new Color(255, 194, 38));
                                    pressedBox.setElementType(3);
                                    break;
                                case 2:
                                    pressedBox.setBackground(new Color(32,80,255));
                                    pressedBox.setElementType(4);
                                    break;
                                case 3:
                                    pressedBox.setBackground(new Color(165,76,68));
                                    pressedBox.setElementType(1);
                            }
                        }
                    }
                });
            }
        }
        for(levelElement[] item : checkSheet) {
            for(levelElement singleItem : item) {
                singleItem.setFocusable(false);
                levelEditorPanel.add(singleItem);
            }
        }
    }

    public static void main(String[] args) {
        frame = new JFrame("BomberFox Level Editor");
        mainForm form = new mainForm();
        frame.setContentPane(form.basePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        form.foxSpawnRadioButton.setSelected(true);

        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
