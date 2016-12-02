import java.util.*;

/**
 * Created by mgunes on 30.11.2016.
 *
 * İkili ağaca ait bilgileri tutar
 * İkili ağacın oluşturulmasına ilişkin metotları içerir
 */
public class BinaryTree implements Comparable<BinaryTree> {
    private Map<Integer ,Node> nodes; //ağacın düğümlerini tutar
    private List<Integer> number; //kullanıcının girdiği sayılar
    private int result; //ağaçtaki işlemler yapılınca elde edilen sonuç
    private int fitness; //sonuca ne kadar yakın? seçilim için kullanılacak
    private int goal = Screen.goal;

    //Başlangıçta oluşturulan rastgele çözümler için
    public BinaryTree(List<Integer> number) {
        nodes = new HashMap<Integer, Node>();
        this.number = number;

        BinaryTreeAlgorithms bta = new BinaryTreeAlgorithms(number);
        nodes = bta.generateNodeMap();
        bta.placeNodeValues(nodes);
        result = bta.computeResult(nodes.get(0));
        fitness = goal - result;

        //hedef 10 sa, 20 -10, 9 +1 olur, küçükten büyüğe sıralayınca 20 daha
        //yakınmış gibi gözükecek. Bu sorunu engellemek için mutlak değeri alındı
        if(fitness < 0)
            fitness = -fitness;
    }

    //Crossover dan gelen, operatör ve value ları belli ağaçlar için
    public BinaryTree(List<Character> operators, List<Integer> values) {

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
        fitness = goal - result;

        if(fitness < 0)
            fitness = -fitness;
    }

    //accuracy e göre sıralama
    public int compareTo(BinaryTree tree) {
        int nearer = tree.getAccuracy();
        return this.fitness - nearer;
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
        return fitness;
    }

    public void setAccuracy(int accuracy) {
        this.fitness = accuracy;
    }
}
