import java.util.*;

/**
 * Created by mgunes on 30.11.2016.
 */
public class BinaryTree implements Comparable<BinaryTree> {
    private Map<Integer ,Node> nodes;
    private List<Integer> number;
    private int result; //ağaçtaki işlemler yapılınca elde edilen sonuç
    private int accuracy; //sonuca ne kadar yakın? seçilim için kullanılacak
    private int goal;

    //Başlangıçta oluşturulan rastgele çözümler için
    public BinaryTree(List<Integer> number, int goal) {
        nodes = new HashMap<Integer, Node>();
        this.number = number;
        this.goal = goal;

        BinaryTreeAlgorithms bta = new BinaryTreeAlgorithms(number);
        nodes = bta.generateNodeMap();
        bta.placeNodeValues(nodes);
        result = bta.computeResult(nodes.get(0));
        accuracy = goal - result;

        //hedef 10 sa, 20 -10, 9 +1 olur, küçükten büyüğe sıralayınca 20 daha
        //yakınmış gibi gözükecek. Bu sorunu engellemek için mutlak değeri alındı
        if(accuracy < 0)
            accuracy = -accuracy;
    }

    //Crossover dan gelen, operatör ve value ları belli ağaçlar için
    public BinaryTree(List<Character> operators, List<Integer> values, int goal) {
        this.goal = goal;

        BinaryTreeAlgorithms bta = new BinaryTreeAlgorithms(number);
        nodes = bta.generateNodeMap();

        int valuesIndex = 0;
        int operatorsIndex = 0;

        for(Integer id: nodes.keySet()) {
            if(nodes.get(id).getType() == Node.NodeType.BRANCH)  {
                nodes.get(id).setOperator(operators.get(operatorsIndex));
                operatorsIndex++;
            } else {
                nodes.get(id).setValue(values.get(valuesIndex));
                valuesIndex++;
            }
        }

        result = bta.computeResult(nodes.get(0));
        accuracy = goal - result;

        if(accuracy < 0)
            accuracy = -accuracy;
    }

    //accuracy e göre sıralama
    public int compareTo(BinaryTree tree) {
        int nearer = tree.getAccuracy();
        return this.accuracy - nearer;
    }

    //getter-setter
    public Map<Integer, Node> getNodeList() {
        return nodes;
    }

    public void setNodeList(Map<Integer, Node> nodeList) {
        this.nodes = nodeList;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }
}
