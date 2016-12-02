/**
 * Created by mgunes on 01.12.2016.
 *
 * Ağaç üzerinde in-order dolaşmayı sağlar
 * Dolaşma sırasına göre değerleri-operatörleri saklar
 */
public class TreeAlgorithms {
    private String resultOrder = " ";

    //ağaçtaki işlemleri sırasıyla yazdır
    //başlangıç olarak root u alır
    public void inOrderTraversal(Node node) { //parantezleri kontrol et
        System.out.print(" ( ");
        resultOrder += " (";
        if(node.getLeftChild().getType() == Node.NodeType.LEAF) {
           System.out.print(node.getLeftChild().getValue() + " ");
            resultOrder += node.getLeftChild().getValue() + " ";
        } else {
            inOrderTraversal(node.getLeftChild());
        }

        System.out.print(node.getOperator() + " ");
        resultOrder += node.getOperator() + " ";

        if(node.getRightChild().getType() == Node.NodeType.LEAF) {
            resultOrder += node.getRightChild().getValue() + " ";
           System.out.print(node.getRightChild().getValue() + " ");
        } else {
            inOrderTraversal(node.getRightChild() );
        }

        resultOrder += " ) ";
        System.out.print(" ) ");
    }

    public String getResultOrder() {
        return resultOrder;
    }

}
