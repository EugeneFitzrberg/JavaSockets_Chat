package GUI;

import Utils.Constants;

import javax.swing.*;

public class GUICLient implements ISetText {

    String name;
    JTextArea jTextArea;
    JFrame mainFrame;

    public GUICLient(){
        mainFrame = new JFrame("Beta Client");
        JPanel mainPanel = new JPanel();
        jTextArea = new JTextArea();

        mainPanel.add(jTextArea);

        mainFrame.add(mainPanel);

        mainFrame.setSize(Constants.WINDOW_SIZE);
        mainFrame.setVisible(true);


    }

    @Override
    public void setDate(String text) {

        jTextArea.setText(jTextArea.getText() + text + "\n");
    }

    @Override
    public void setTitle(String text) {
        mainFrame.setTitle(text);
    }
}
