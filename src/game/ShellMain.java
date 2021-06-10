package game;

import javax.swing.Box;

import javax.swing.JFrame;

@SuppressWarnings("serial")

public class ShellMain extends JFrame {
    ShellWin win=new ShellWin();

    ShellWin.panel pan=win.new panel();

    Box box,box2;

    ShellMain(){
        box2=Box.createHorizontalBox();

        box2.add(win);

        box=Box.createVerticalBox();

        box.add(pan.box1);

        box.add(Box.createVerticalStrut(8));

        box.add(box2);

        pan.add(box);

        setTitle("贪吃蛇");

        setSize(800,800);

        setVisible(true);

        setLayout(null);

        add(pan);

        setLocation(0,0);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        new ShellMain();

    }

}