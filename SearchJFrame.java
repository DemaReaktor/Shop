import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchJFrame extends JFrame {
    private JFrame baseJFrame;
    private String name;
    private ActionListener actionListener;

    public SearchJFrame(JFrame baseJFrame) {
        this.baseJFrame = baseJFrame;
        this.baseJFrame.setEnabled(false);

        //frame
        setVisible(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(Toolkit.getDefaultToolkit().getScreenSize().width / 2,
                Toolkit.getDefaultToolkit().getScreenSize().height / 2);
        setLocation(new Point(Toolkit.getDefaultToolkit().getScreenSize().width / 4,
                Toolkit.getDefaultToolkit().getScreenSize().height / 4));
        setTitle("пошук");

        JPanel panel = new JPanel();
        panel.setVisible(true);

        //general box
        Box generalBox = Box.createVerticalBox();
        generalBox.setVisible(true);

        //code box
        Box codeBox = Box.createHorizontalBox();
        codeBox.setVisible(true);

        JLabel goodCode = new JLabel("код");
        goodCode.setVisible(true);
        codeBox.add(goodCode);

        JTextField codeText = new JTextField(10);
        codeText.setText("---");
        codeText.setVisible(true);
        codeBox.add(codeText);

        generalBox.add(codeBox);

        //name box
        Box nameBox = Box.createHorizontalBox();
        nameBox.setVisible(true);

        JLabel goodName = new JLabel("назва");
        goodName.setVisible(true);
        nameBox.add(goodName);

        JTextField nameText = new JTextField(10);
        nameText.setText("---");
        nameText.setVisible(true);
        nameBox.add(nameText);

        generalBox.add(nameBox);

        //film or music box
        Box fmBox = Box.createHorizontalBox();
        fmBox.setVisible(true);

        JLabel fmLabel = new JLabel("фільм чи музика");
        fmLabel.setVisible(true);
        fmBox.add(fmLabel);

        String[] fmArray = new String[]{"невідомо", "фільм", "музика"};
        JSpinner fmSpinner = new JSpinner(new SpinnerListModel(fmArray));
        fmSpinner.setVisible(true);
        fmBox.add(fmSpinner);

        generalBox.add(fmBox);

        //year box
        Box yearBox = Box.createHorizontalBox();
        yearBox.setVisible(true);

        JLabel yearLabel = new JLabel("рік випуску");
        yearLabel.setVisible(true);
        yearBox.add(yearLabel);

        JTextField year1Text = new JTextField(4);
        year1Text.setText("0");
        year1Text.setVisible(true);
        yearBox.add(year1Text);

        JLabel betweenYearsLabel = new JLabel("-");
        betweenYearsLabel.setVisible(true);
        yearBox.add(betweenYearsLabel);

        JTextField year2Text = new JTextField(4);
        year2Text.setText("9999");
        year2Text.setVisible(true);
        yearBox.add(year2Text);

        generalBox.add(yearBox);

        //media type box
        Box mediaTypeBox = Box.createHorizontalBox();
        mediaTypeBox.setVisible(true);

        JLabel typeLabel = new JLabel("тип носія");
        typeLabel.setVisible(true);
        mediaTypeBox.add(typeLabel);

        String[] mediaTypeArray = new String[]{"1", "2", "3"};
        JSpinner mediaTypeSpinner = new JSpinner(new SpinnerListModel(mediaTypeArray));
        mediaTypeSpinner.setVisible(true);
        mediaTypeBox.add(mediaTypeSpinner);

        generalBox.add(mediaTypeBox);

        //buttons box
        Box buttonsBox = Box.createHorizontalBox();
        buttonsBox.setVisible(true);

        JButton searchButton = new JButton("пошук");
        searchButton.setVisible(true);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int code, year1, year2;
                try {
                    code = Integer.parseInt(codeText.getText());
                    year1 = Integer.parseInt(year1Text.getText());
                    year2 = Integer.parseInt(year2Text.getText());
                } catch (Exception exception) {
                    new ErrorDialog("роки повинні бути додатніми числами.");
                    return;
                }
                try {
                    code = Integer.parseInt(codeText.getText());
                } catch (Exception exception) {
                    new ErrorDialog("код повинен бути додатнім числом.");
                    return;
                }
                if (year1 < 0 || year2 < 0) {
                    new ErrorDialog("роки повинні бути додатніми числами.");
                    return;
                }
                if (code < 0) {
                    new ErrorDialog("код повинен бути додатнім числом.");
                    return;
                }
            }
        });
        buttonsBox.add(searchButton);

        JButton chooseButton = new JButton("вибрати");
        chooseButton.setVisible(true);
        chooseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameText.getText();
                if (name.compareTo("---") == 0)
                    new ErrorDialog("товар не вибрано.");
                else {
//                    orderJPanel.chooseGood(name);
                    setGood(name);
                    exit();
                }
            }
        });
        buttonsBox.add(chooseButton);

        JButton undoneButton = new JButton("скасувати");
        undoneButton.setVisible(true);
        undoneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });
        buttonsBox.add(undoneButton);

        generalBox.add(buttonsBox);

        panel.add(generalBox);
        add(panel);
    }

    public void exit() {
        baseJFrame.setEnabled(true);
        setVisible(false);
    }
    public String getGood(){
        return name;
    }
    public void setGood(String name){
        this.name = name;
        actionListener.actionPerformed(null);
    }
    public void addActionListener(ActionListener actionListener){
        this.actionListener = actionListener;
    }
}
