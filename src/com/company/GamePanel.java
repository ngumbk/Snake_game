package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Vector;


public class GamePanel extends JPanel implements KeyListener, ActionListener {

    public int rand_num() {
        Random r = new Random();
        return r.nextInt(2, 26) * 20;
    }

    private class stored_object {
        int x, y;
        public stored_object(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public int x, y, w, h;
    public int DirectionCode = 0; // a, w, d, s
    boolean apple_eaten = false;
    Vector<stored_object> snake_body = new Vector<>(0);
    stored_object apple = new stored_object(rand_num(), rand_num());


    public GamePanel(int x, int y, int w, int h){
        super();

        setFocusable(true);
        this.x = x;
        this.y = y;
        snake_body.add(new stored_object(x, y));
        this.w = w;
        this.h = h;
        addKeyListener(this);

        Timer timer = new Timer(250, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //drawing borders
        g.drawRect(40, 40, 500, 500);

        //drawing snake
        g.setColor(Color.green);
        for (int i = 0; i < snake_body.size(); i++) {
            g.fillRect(snake_body.get(i).x, snake_body.get(i).y, w, h);
        }

        //drawing apple
        g.setColor(Color.red);
        g.fillOval(apple.x, apple.y, 20, 20);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int _code = e.getKeyCode();
        if(_code == 65 || _code == 87 || _code == 68 || _code == 83) {
            if (_code == 65 && DirectionCode != 68 || _code == 68 && DirectionCode != 65 ||
                    _code == 87 && DirectionCode != 83 || _code == 83 && DirectionCode != 87) {
                DirectionCode = _code;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //to = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (DirectionCode) {
            case 65 -> x -= 20;
            // left
            case 87 -> y -= 20;
            // up
            case 68 -> x += 20;
            // right
            case 83 -> y += 20;
            // down
        }
        if (x > 520) {x = 40;}
        if (y > 520) {y = 40;}
        if (x < 40) {x = 520;}
        if (y < 40) {y = 520;}
        if (!apple_eaten) {
            snake_body.remove(0);
        }
        else {
            apple_eaten = false;
        }
        if (x == apple.x && y == apple.y) {
            apple.x = rand_num();
            apple.y = rand_num();
            apple_eaten = true;
        }
        for (int i = 0; i < snake_body.size() - 2; i++) {
            if (x == snake_body.get(i).x && y == snake_body.get(i).y) {
                snake_body.removeAll(snake_body.subList(0, i));
                break;
            }
        }
        snake_body.add(new stored_object(x, y));
        System.out.println(x + "; " + y);
        repaint();
    }
}

