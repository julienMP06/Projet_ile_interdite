package IG;

import javax.swing.*;
import java.awt.*;

public abstract class Zone extends JPanel {

    public Zone(int x, int y) {
        setPreferredSize(new Dimension(x,y));
        setBackground(Color.WHITE);
    }
}
