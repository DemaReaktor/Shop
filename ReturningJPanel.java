import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class ReturningJPanel extends JPanel {
    private HashMap<String, Integer> Goods;
    private MainJFrame mainJFrame;
    private JButton nameButton;

    public ReturningJPanel(MainJFrame mainJFrame) {
        this.mainJFrame = mainJFrame;

        Goods = new HashMap<String, Integer>();
        setLayout(new BorderLayout());

        Box generalBox = Box.createVerticalBox();
        generalBox.setVisible(true);

        Box checkBox = Box.createHorizontalBox();

        JLabel checkLabel = new JLabel("код чеку");
        checkLabel.setVisible(true);
        checkBox.add(checkLabel);

        JTextField checkText = new JTextField(10);
        checkText.setText("1");
        checkText.setVisible(true);
        checkBox.add(checkText);

        checkBox.setVisible(true);
        generalBox.add(checkBox);

        Box goodBox = Box.createHorizontalBox();

        JLabel goodName = new JLabel("товар");
        goodName.setVisible(true);
        goodBox.add(goodName);

        nameButton = new JButton("---");
        nameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        nameButton.setVisible(true);
        goodBox.add(nameButton);

        JButton searchButton = new JButton("пошук");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search();
            }
        });
        searchButton.setVisible(true);
        goodBox.add(searchButton);

        goodBox.setVisible(true);
        generalBox.add(goodBox);

        Box countBox = Box.createHorizontalBox();

        JLabel goodCount = new JLabel("кількість");
        goodCount.setVisible(true);
        countBox.add(goodCount);

        JTextField countText = new JTextField(10);
        countText.setText("1");
        countText.setVisible(true);
        countBox.add(countText);

        countBox.setVisible(true);
        generalBox.add(countBox);

        JButton addButton = new JButton("додати у кошик");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameButton.getText();
                if (name.compareTo("---") == 0) {
                    new ErrorDialog("не введено товар.");
                    return;
                }

                int count;
                try {
                    count = Integer.parseInt(countText.getText());
                } catch (Exception exception) {
                    new ErrorDialog("введені дані у поле 'кількість' не є додатнім числом.");
                    return;
                }
                if (count <= 0) {
                    new ErrorDialog("введені дані у поле 'кількість' не є додатнім числом.");
                    return;
                }
                if (Goods.containsKey(name)) {
                    int c = Goods.get(name);
                    Goods.remove(name);
                    Goods.put(name, count + c);
                    updateList();
                } else {
                    Goods.put(name, count);
                    updateList();
                }


            }
        });
        addButton.setVisible(true);
        generalBox.add(addButton);

        JButton returnButton = new JButton("повернути");
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int checkCode;
                try {
                    checkCode = Integer.parseInt(checkText.getText());
                } catch (Exception exception) {
                    new ErrorDialog("введені дані у полі 'код чеку' не є додатнім числом.");
                    return;
                }
                if (checkCode <= 0) {
                    new ErrorDialog("введені дані у полі 'код чеку' не є додатнім числом.");
                    return;
                }
            }
        });
        returnButton.setVisible(true);
        generalBox.add(returnButton);

        add("North", generalBox);
        setVisible(true);
    }

    public void updateList() {
        mainJFrame.setBasket(Goods);
    }

    public HashMap<String, Integer> getGoods() {
        return Goods;
    }

    public void search() {
        SearchJFrame s= new SearchJFrame(mainJFrame);
        s.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseGood(s.getGood());
            }
        });
    }

    public void chooseGood(String name) {
        nameButton.setText(name);
    }
}


