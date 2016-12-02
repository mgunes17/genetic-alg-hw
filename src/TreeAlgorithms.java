/**
 * Created by mgunes on 01.12.2016.
 */
public class TreeAlgorithms {

    //ağaçtaki işlemleri sırasıyla yazdır
    //başlangıç olarak root u alır
    public void inOrderTraversal(Node node) { //parantezleri kontrol et
        if(node.getLeftChild().getType() == Node.NodeType.LEAF) {
           System.out.print("(" + node.getLeftChild().getValue() + " ");
        } else {
            inOrderTraversal(node.getLeftChild());
        }

        System.out.print(node.getOperator() + " ");

        if(node.getRightChild().getType() == Node.NodeType.LEAF) {
            System.out.print(node.getRightChild().getValue() + ") ");
        } else {
            inOrderTraversal(node.getRightChild() );
        }
    }

}
