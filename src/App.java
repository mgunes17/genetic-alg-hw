import java.util.*;

/**
 * Created by mgunes on 30.11.2016.
 */
public class App {
    public static void main(String[] args) {
        /*
        1-2 3-4 5-6 çaprazla
        1 in operatörlerini bir arrayliste al
        2 nin operatörlerini bir arrayliste al
        unifrom crossoverla 2 farklı arraylist oluştur

        1in sayılarını al arayyyliste
        2nin sayılarını al arrayliste
        sıra tabanlı çaprazlama yap 2 arraylist oluştur

        rastgele bir ağaç üret arraylsit 1 leri sırayla ata SIRAYLA!! RANDOM oluşmasın
        2. ağaç için de aynı şeyi yap

        bu iki ağacı yeni array liste ekle

        tüm ikililer için yapınca tekrar sırala, ilk eleman 0 sa ...

        çaprazlama sonucu oluşan ağaçları bir arrayliste at,
        işlem bitince o arraylisti forest a ata


        MAX iterasyon sayısı al,
        sonunda en yakın olanı döndürsün

         */
        ArrayList<Integer> number = new ArrayList<>();
        number.add(4);
        number.add(2);
        number.add(6);
        number.add(14);
        number.add(1);
        number.add(9);

        int goal = 17;

        App app = new App();

        //listeyi karıştır
        //Collections.shuffle(number);

        List<BinaryTree> trees = new ArrayList<>();

        //başlangıç populasyonunu oluştur
       for(int i = 0; i < 50; i++) {
            trees.add(new BinaryTree(number, goal));
        }

        ForestPopulation forest = new ForestPopulation(trees, goal);
        BinaryTree correctTree = forest.findTree();


        TreeAlgorithms treeAlg = new TreeAlgorithms();
        treeAlg.inOrderTraversal(correctTree.getNodeList().get(0));

        /*BinaryTree binaryTree = new BinaryTree(number, goal);

        TreeAlgorithms treeAlg = new TreeAlgorithms();
        treeAlg.inOrderTraversal(binaryTree.getNodeList().get(0));

        System.out.println("\nresult:" + binaryTree.getResult());*/

    }

    public void generateNodeValues( Map<Integer, Node> nodeList) {

    }
}
