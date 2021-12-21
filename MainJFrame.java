import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainJFrame extends JFrame{
    public String activeSeller;

public MainJFrame(){
    activeSeller = null;
    this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    JMenuBar mainMenu = new JMenuBar();
    mainMenu.setVisible(true);

    JButton enter = new JButton("зайти");
    enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enter();
            }
            });
    mainMenu.add(enter);

    this.setJMenuBar(mainMenu);
    this.setVisible(true);
}
public void enter(){
    new EnterJFrame(this);
}
public void setActiveSeller(String name){
activeSeller = name;
this.getJMenuBar().remove(0);

JLabel sellerName= new JLabel(activeSeller);
    sellerName.setVisible(true);
this.getJMenuBar().add(sellerName);

    JButton orderButton = new JButton("нове замовлення");
    orderButton.setVisible(true);
    orderButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            OrderJPanel order = new OrderJPanel();
            add("West",order);
            order.updateUI();
        }
    });
    this.getJMenuBar().add(orderButton);

    JButton undoneButton = new JButton("повернути товар");
    undoneButton.setVisible(true);
    undoneButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    });
    this.getJMenuBar().add(undoneButton);

this.getJMenuBar().updateUI();
}
    public static void main(String[] args) {
        new MainJFrame();
    }
}
