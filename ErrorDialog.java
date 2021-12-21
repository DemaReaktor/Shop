import javax.swing.*;
import java.awt.*;

public class ErrorDialog extends JDialog {
    public ErrorDialog(String message){
        setVisible(true);
        int sizeX = message.length()*10;
        setSize(sizeX,100);
        setLocation(new Point((Toolkit.getDefaultToolkit().getScreenSize().width-sizeX)/2,
                Toolkit.getDefaultToolkit().getScreenSize().height/2-50));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel messageLabel = new JLabel(message);
        messageLabel.setVisible(true);
        add(messageLabel);
    }
}
