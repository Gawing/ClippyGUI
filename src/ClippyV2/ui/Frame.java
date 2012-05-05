/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ClippyV2.ui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author GavinC
 */
public abstract class Frame extends JFrame {
    private static final Color backgroundColor = Color.lightGray;

    public Frame(){
        super();
        this.setPreferredSize(new Dimension(300,300));
        this.setUndecorated(true);
        this.setBackground(backgroundColor);
        this.setOpacity(0.6f);
        this.setLayout(null);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
    }
    
}
