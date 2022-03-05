package com.company;

import javax.swing.*;

public class GameFrame extends JFrame {

    public static GamePanel gamePanel = new GamePanel(200, 200, 20, 20);

    public GameFrame(int x, int y, int w, int h) {
        super();
        setBounds(x, y, w, h);

        add(gamePanel);
        setVisible(true);
    }

}
