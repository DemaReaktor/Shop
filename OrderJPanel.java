import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class OrderJPanel extends JPanel {
    private HashMap<String,Integer> Goods;
    private JTextArea nameList;
    private JTextArea countList;

    public OrderJPanel(){
        Goods = new HashMap<String, Integer>();
        setLayout(new BorderLayout());

        Box generalBox =Box.createVerticalBox();
        generalBox.setVisible(true);

        Box goodBox = Box.createHorizontalBox();

        JLabel goodName = new JLabel("товар");
        goodName.setVisible(true);
        goodBox.add(goodName);

        JButton nameButton = new JButton("---");
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
                if(name.compareTo("---")==0)
                {
                    new ErrorDialog("не введено товар.");
                    return;
                }

                int count;
                try {
                    count = Integer.parseInt(countText.getText());
                }
                catch (Exception exception){
                    new ErrorDialog("введені дані у поле 'кількість' не є додатнім числом.");
                    return;
                }
                    if(count<=0)
                    {
                        new ErrorDialog("введені дані у поле 'кількість' не є додатнім числом.");
                        return;
                    }
                    if(Goods.containsKey(name)){
                        int c = Goods.get(name);
                        Goods.remove(name);
                        Goods.put(name, count+c);
                        updateList();
                    }
                        else{
                    Goods.put(name, count);
                        updateList();
                    }


            }
        });
        addButton.setVisible(true);
        generalBox.add(addButton);

        JButton orderButton = new JButton("замовити");
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        orderButton.setVisible(true);
        generalBox.add(orderButton);

//        nameList = new JTextArea();
//        nameList.setVisible(true);
//        add(nameList);

//        countList = new JTextArea();
//        countList.setVisible(true);
//        add(countList);
        add("North",generalBox);
        this.setVisible(true);
    }
    public void updateList(){
        String names= "товари:\n";
        String counts= "кількість:\n";
                for(Map.Entry<String,Integer> element : Goods.entrySet()){
                    names+=element.getKey()+"\n";
                    counts+=element.getValue()+"\n";
                }
        nameList.setText(names);
        countList.setText(counts);
    }
    public HashMap<String,Integer> getGoods(){
        return Goods;
    }
}
