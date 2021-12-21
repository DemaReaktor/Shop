import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class MainJFrame extends JFrame {
    public String activeSeller;
    public JPanel activePanel;

    public MainJFrame() {
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

    public void enter() {
        new EnterJFrame(this);
    }

    public void setActiveSeller(String name) {
        activeSeller = name;
        this.getJMenuBar().remove(0);

        JLabel sellerName = new JLabel(activeSeller);
        sellerName.setVisible(true);
        this.getJMenuBar().add(sellerName);

        JButton orderButton = new JButton("нове замовлення");
        orderButton.setVisible(true);
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                order();
            }
        });
        this.getJMenuBar().add(orderButton);

        JButton undoneButton = new JButton("повернути товар");
        undoneButton.setVisible(true);
        undoneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
returnGood();
            }
        });
        this.getJMenuBar().add(undoneButton);

        this.getJMenuBar().updateUI();
    }
    public void returnGood() {
        ReturningJPanel returnJPanel = new ReturningJPanel(this);
        add("West", returnJPanel);
        returnJPanel.updateUI();
        if(activePanel!=null)
            activePanel.setVisible(false);
        activePanel = returnJPanel;
    }


    public void order() {
        OrderJPanel order = new OrderJPanel(this);
        add("West", order);
        order.updateUI();
        if(activePanel!=null)
            activePanel.setVisible(false);
        activePanel= order;
    }

    public void setBasket(HashMap<String, Integer> list) {
        JPanel basketJPanel = new JPanel();
        basketJPanel.setVisible(true);

        String[] basketArray = new String[list.entrySet().size()];
        int index = 0;
        for (Map.Entry<String, Integer> element : list.entrySet()) {
            basketArray[index] = element.getKey() + " : " + element.getValue().toString();
            index += 1;
        }
        JScrollPane basketPane = new JScrollPane(new JList<String>(basketArray));
        basketPane.setVisible(true);
        basketJPanel.add(basketPane);

        add("East", basketJPanel);
        basketJPanel.updateUI();
    }

    public static void main(String[] args) {
        new MainJFrame();
    }

}
