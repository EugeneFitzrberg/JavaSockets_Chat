package GUI;

import Utils.Constants;

import javax.swing.*;
import java.awt.*;

public class MainClient implements ISetText {

    String name;
    JTextArea jTextArea;

    public MainClient(){
        JFrame frame = new JFrame("Beta Client");
        JPanel mainPanel = new JPanel();
        jTextArea = new JTextArea();

        mainPanel.add(jTextArea);

        frame.add(mainPanel);

        frame.setSize(Constants.WINDOW_SIZE);
        frame.setVisible(true);

    }

    @Override
    public void setDate(String text) {
        jTextArea.setText(text);
    }
}
