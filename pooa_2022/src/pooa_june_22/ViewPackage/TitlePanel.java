package pooa_june_22.ViewPackage;

import javax.swing.*;
import java.awt.*;

public class TitlePanel extends JPanel {
    JLabel setTitle;

    public TitlePanel(String title) {
        this.setLayout(new FlowLayout());
        setTitle = new JLabel(
                "<html>" +
                        "<p></p>" +
                        "<p></p>" +
                        "<p>" + title +"</p>" +
                        "<p></p>" +
                        "<p></p>" +
                        "<p></p>" +
                        "</html>"
        );
        setTitle.setFont(new Font("Arial",Font.TRUETYPE_FONT,20));

        this.add(setTitle);
    }
}
