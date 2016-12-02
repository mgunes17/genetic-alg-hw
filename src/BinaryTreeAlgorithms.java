import java.util.*;

/**
 * Created by mgunes on 01.12.2016.
 *
 * Binary Tree için gerekli algoritmalar bu sınıf üzerinden yürütüğlmektedir
 */
public class BinaryTreeAlgorithms {
    public static int LEAF_COUNT = 6;
    private List<Integer> number;

    public BinaryTreeAlgorithms( List<Integer> number) {
        super();
        this.number = number;
    }

    public Map<Integer ,Node> generateNodeMap() {
        Map<Integer, Node> nodes = new HashMap<Integer, Node>();
        Random r = new Random();
        int selectedNode;
        int i = 1;

        List<Integer> leafList = new ArrayList<>();

        Node root = new Node(Node.NodeType.BRANCH, 0);
        leafList.add(root.getId());
        nodes.put(root.getId(), root);

        /* her oluşturulan node önce yaprak listesine atılır
            Eğer ordan çıkarılırsa tipi branch olarak değiştirilir
            Bu node a bağlı iki çocuk üretilip yaprak listesine eklenir
            Bu işlem 6 yaprak elde edene kadar devam eder
         */
        while(leafList.size() < LEAF_COUNT) {
            selectedNode = r.nextInt(leafList.size());

            int id = leafList.get(selectedNode);
            leafList.remove(selectedNode);

            Node n1 = new Node(Node.NodeType.LEAF, i);
            i++;
            Node n2 = new Node(Node.NodeType.LEAF,i);
            i++;

            leafList.add(n1.getId());
            leafList.add(n2.getId());

            n1.setParent(nodes.get(new Integer(id)));
            n2.setParent(nodes.get(new Integer(id)));

            nodes.put(n1.getId(), n1);
            nodes.put(n2.getId(), n2);

            nodes.get(new Integer(id)).setLeftChild(n1);
            nodes.get(new Integer(id)).setRightChild(n2);
            nodes.get(new Integer(id)).setType(Node.NodeType.BRANCH);
        }

       return nodes;
    }

    /*eğer node dalsa rastgele bir operatör atar
        yapraksa sayıları sırayla atar
        kullanıcıdan alınan sayılar arrayliste random yerleştirildi (shuffle metodu)
     */

    public void placeNodeValues( Map<Integer, Node> nodes) {
        int i = 0;
        for(Integer key: nodes.keySet()) {
            if(nodes.get(key).getType() == Node.NodeType.BRANCH) { //dalsa
                nodes.get(key).setOperator(getRandomOperator());
            } else { //yapraksa sıradaki sayıyı ata
                nodes.get(key).setValue(number.get(i));
                i++;
            }
        }
    }

    private char getRandomOperator() {
        Random random = new Random();
        int r = random.nextInt(4);
        char operator = 'N';

        switch (r) {
            case 0 : operator = '+'; break;
            case 1 : operator = '-'; break;
            case 2 : operator = '/'; break;
            case 3 : operator = '*'; break;
        }

        return operator;
    }

    //ağacın sonucunu hesaplar
    public int computeResult(Node node) {
        int leftValue;
        int rightValue;
        int result;

        //o dala ait sol ağacın değerini hesapla
        if(node.getLeftChild().getType() == Node.NodeType.LEAF) {
            leftValue = node.getLeftChild().getValue();
        } else {
            leftValue = computeResult(node.getLeftChild());
        }

        //o dala ait sağ ağacın değerini hesapla
        if(node.getRightChild().getType() == Node.NodeType.LEAF) {
            rightValue = node.getRightChild().getValue();
        } else {
            rightValue = computeResult(node.getRightChild());
        }

        result = computeWithOperator(leftValue, rightValue, node.getOperator());

        return result;
    }

    //verilen operatöre göre işlem yapar
    private int computeWithOperator(int left, int right, char operator) {
        int result = 0;

        try {
            switch (operator) {
                case '+': result = left + right; break;
                case '-': result = left - right; break;
                case '*': result = left * right; break;
                case '/': result =  left / right; break;
            }

        } catch(ArithmeticException ex) {
            result = 99999; //0 a bölme hatası varsa işleme alınmaması için büyük bir değer atandı
        }

        return  result;
    }

}
