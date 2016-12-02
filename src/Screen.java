import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by mgunes on 02.12.2016.
 *
 * Kullanıcı arayzünün sağlandığı sınıf
 */
public class Screen extends JFrame {
    private JLabel resultTree;
    private JLabel generationCount;
    private JTextField n1;
    private JTextField n2;
    private JTextField n3;
    private JTextField n4;
    private JTextField n5;
    private JTextField n6;
    private JTextField goalNumber;
    private JButton start;

    public static int goal;

    public Screen() {
        super("Bir İşlem");
        Container container = getContentPane();
        setLayout(new FlowLayout());

        generationCount = new JLabel("Nesil Sayısı");
        resultTree = new JLabel("Sonuca götüren işlem");

        n1 = new JTextField(20);
        n2 = new JTextField(20);
        n3 = new JTextField(20);
        n4 = new JTextField(20);
        n5 = new JTextField(20);
        n6 = new JTextField(20);
        goalNumber = new JTextField(10);

        container.add(new JLabel("1. Sayı"));
        container.add(n1);
        container.add(new JLabel("2. Sayı"));
        container.add(n2);
        container.add(new JLabel("3. Sayı"));
        container.add(n3);
        container.add(new JLabel("4. Sayı"));
        container.add(n4);
        container.add(new JLabel("5. Sayı"));
        container.add(n5);
        container.add(new JLabel("6. Sayı"));
        container.add(n6);
        container.add(new JLabel("Hedef  "));
        container.add(goalNumber);

        start = new JButton("Sonuca götüren işlemi bul");
        container.add(start);
        container.add(generationCount);
        container.add(resultTree);



        setSize(300, 400);
        setVisible(true);

    }

    public void showWindow() {
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.util.List<Integer>  numberList = new ArrayList<>();
                numberList.clear();

                int number1 = Integer.parseInt(n1.getText());
                int number2 = Integer.parseInt(n2.getText());
                int number3 = Integer.parseInt(n3.getText());
                int number4 = Integer.parseInt(n4.getText());
                int number5 = Integer.parseInt(n5.getText());
                int number6 = Integer.parseInt(n6.getText());
                Screen.goal = Integer.parseInt(goalNumber.getText());

                numberList.add(number1);
                numberList.add(number2);
                numberList.add(number3);
                numberList.add(number4);
                numberList.add(number5);
                numberList.add(number6);

                /*numberList.add(4);
                numberList.add(5);
                numberList.add(2);
                numberList.add(1);
                numberList.add(8);
                numberList.add(5);
                Screen.goal = 20;*/

                Collections.shuffle(numberList);

                List<BinaryTree> trees = new ArrayList<>();

                //başlangıç populasyonunu oluştur
                for(int i = 0; i < 20; i++) {
                    trees.add(new BinaryTree(numberList));
                }

                try {
                    ForestPopulation forest = new ForestPopulation(trees, goal);
                    BinaryTree correctTree = forest.findTree();


                    TreeAlgorithms treeAlg = new TreeAlgorithms();
                    treeAlg.inOrderTraversal(correctTree.getNodeList().get(0));

                    resultTree.setText(treeAlg.getResultOrder());
                    generationCount.setText("Nesil Sayısı: " + correctTree.getNodeList().get(0).getGeneration());
                } catch (Exception ex) {
                    generationCount.setText("Bir hata meydana geldi.\nFarklı giriş değerleriyle deneyebilirsiniz.");
                }

            }
        });
    }


}
