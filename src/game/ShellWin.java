package game;

import java.awt.Color;

import java.awt.Dimension;

import java.awt.FlowLayout;

import java.awt.Graphics;

import java.awt.event.*;

import javax.swing.Box;

import javax.swing.JButton;

import javax.swing.JOptionPane;

import javax.swing.JPanel;

@SuppressWarnings("serial")

public class ShellWin extends JPanel implements Runnable { //实现了键盘与鼠标两个接口

    int direction;

    int length;

    int score = 0;

    boolean control = false;

    int[] xs = new int[400]; //蛇的个数，xy,坐标控制

    int[] ys = new int[400];

    int douX;
    int douY;

    Thread thread;

    public ShellWin() {
        setPreferredSize(new Dimension(600, 600));

        setLocation(100, 100);

        setBackground(Color.GREEN);

        thread = new Thread(this); //为这个类添加一个线程

        create_snake();

        create_bean();

    }

    public void paintComponent(Graphics g) //绘制

    {
        super.paintComponent(g); //Jpanel内的方法，起到清屏的作用

        g.drawString("分数:" + score, 50, 50);

        g.drawRect(0, 0, 600, 600);

        show_snake(g);

        show_bean(g);

    }

//能不能吃豆，能不能死

    void die() {
        JOptionPane.showMessageDialog(null, "game over", "游戏结束", JOptionPane.ERROR_MESSAGE);

    }

    void eat() {
        length++;

        this.score += 100;

        create_bean();

    }

    boolean out_bounds() {
        if (xs[0] == -10 || xs[0] == 600 || ys[0] == -10 || ys[0] == 600) //出界即死

        {
            return true;

        }

        return false;

    }

    void change_direction(int new_direction) //控制方向

    {
        if (direction % 2 != new_direction % 2 || direction == 0) // 2 4 3 1 上下左右

        {
            direction = new_direction;

        }

    }

    boolean eat_self() {
        for (int i = 1; i < length; i++) {
            if (xs[0] == xs[i] && ys[0] == ys[i]) {
                return true;

            }

        }

        return false;

    }

    boolean can_eat() {
        if (xs[0] == douX && ys[0] == douY) {
            return true;

        }

        return false;

    }

    void create_snake() {
        direction = 0;
        length = 5;

        for (int i = 0; i < length; i++) {
            xs[i] = 400;

            ys[i] = 400 + 10 * i;

        }

    }

    void create_bean() {
        douX = 10 * (int) (Math.random() * 60);

        douY = 10 * (int) (Math.random() * 60);

        for (int i = 0; i < length; i++) {
            if (xs[i] == douX && ys[i] == douY) {
                create_bean();

                return;

            }

        }

    }

    void show_bean(Graphics g) //仍然得要写入到paintComponent方法内

    {
        g.setColor(Color.RED);

        g.fillOval(douX, douY, 10, 10);

    }

    void crawl() {
        if (direction != 0)

            for (int i = length - 1; i > 0; i--) { // 2 4 3 1 上下左右

                xs[i] = xs[i - 1]; //后一个替代前一个 4=3；

                ys[i] = ys[i - 1];

            }

        switch (direction) {
            case 1:

                xs[0] = xs[0] + 10;

                break;

            case 2:

                ys[0] = ys[0] - 10;

                break;

            case 3:

                xs[0] = xs[0] - 10;

                break;

            case 4:

                ys[0] = ys[0] + 10;

                break;

            default:

                break;

        }

    }

    void show_snake(Graphics g) {
        for (int i = 0; i < length; i++) {
            g.setColor(Color.BLUE);

            if (i == 0) {
                g.drawOval(xs[i], ys[i], 10, 10);

                continue;

            }

            g.drawRect(xs[i], ys[i], 10, 10); //蛇的长度与大小

        }

    }

    @Override

    public void run() {
        while (control) {
            try {
                if (can_eat()) {
                    eat();

                }

                if (out_bounds()) {
                    die();

                    return;

                }

                if (eat_self()) {
                    die();

                    return;

                }

                crawl();

                Thread.sleep(200);

                repaint();

            } catch (InterruptedException e) {
                e.printStackTrace();

            }

        }

    }

    class panel extends JPanel implements ActionListener, KeyListener {
        JButton startGame = new JButton("开始");

        JButton stopGame = new JButton("停止");

        Box box1; //盒式布局

        panel() {
            setLayout(new FlowLayout());

            box1 = Box.createHorizontalBox();

            box1.add(startGame);

            box1.add(Box.createHorizontalStrut(2));

            box1.add(stopGame);

            setSize(800, 800);

            setBackground(Color.black);

            addKeyListener(this); //为ShellWin 对象注册一个侦听器

            startGame.addActionListener(this); //为开始按钮添加侦听器，this指代的是Actionistener这个类所创建的对象

            stopGame.addActionListener(this); //为结束按钮添加侦听器，this指代的是Actionistener这个类所创建的对象

        }

        @Override

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == startGame) {
                this.requestFocus(); //将光标添加到该控件中

                control = true;

                thread.start(); //线程开始

//***********************************

                this.repaint(100, 100, 600, 600);

            }

            if (e.getSource() == stopGame) {
                this.requestFocus(); //将光标添加到该控件中

                control = false; //线程结束

            }

        }

        @Override

        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:

                    change_direction(2);

                    break;

                case KeyEvent.VK_DOWN:

                    if (direction != 0)

                        change_direction(4);

                    break;

                case KeyEvent.VK_LEFT:

                    change_direction(3);

                    break;

                case KeyEvent.VK_RIGHT:

                    change_direction(1);

                    break;

                default:

                    break;

            }

        }

        @Override

        public void keyReleased(KeyEvent e) {
// TODO Auto-generated method stub

        }

        @Override

        public void keyTyped(KeyEvent e) {
// TODO Auto-generated method stub

        }

    }

}