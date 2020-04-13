package across.gui;

import java.awt.*;
import javax.swing.*;

public class EditFont {

    // intercambia entre bold y no bold
    public static JLabel bold(JLabel label){
        label.setFont(label.getFont().deriveFont(label.getFont().getStyle() ^ Font.BOLD));
        return label;
    }

    public static JLabel setSize(JLabel label, int size){
        label.setFont(label.getFont().deriveFont((float)size));
        return label;
    }
}