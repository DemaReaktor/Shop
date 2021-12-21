import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnterJFrame extends JFrame{
    private JTextField nameText;
    private JTextField PasswordText;
    private MainJFrame mainWindow;

    public EnterJFrame(MainJFrame mainWindow){
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.mainWindow = mainWindow;
        setSize(550,100);
        setLocation(new Point(Toolkit.getDefaultToolkit().getScreenSize().width/2-275,
                Toolkit.getDefaultToolkit().getScreenSize().height/2-50));
        JPanel panel= new JPanel();
        add(panel);

        panel.add(new JLabel("Ім'я"));
        nameText = new JTextField(10);
        nameText.setVisible(true);
        panel.add(nameText);
        panel.add(new JLabel("Пароль"));
        PasswordText = new JTextField(10);
        PasswordText.setVisible(true);
        panel.add(PasswordText);

         JButton signUpButton = new JButton("увійти");
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enter();
            }
        });
        signUpButton.setVisible(true);
         panel.add(signUpButton);

        JButton exitButton = new JButton("скасувати");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });
        exitButton.setVisible(true);
        panel.add(exitButton);

        panel.updateUI();
        setVisible(true);
        mainWindow.setEnabled(false);
    }

public void enter(){
//if password == name.password
    mainWindow.setActiveSeller(nameText.getText());
    exit();
    }
    public void exit(){
        mainWindow.setEnabled(true);
        this.setVisible(false);
    }
}
