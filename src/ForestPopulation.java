import sun.security.util.BitArray;

import java.util.*;

/**
 * Created by mgunes on 01.12.2016.
 *
 * Ağaçlar üzerinde crosssover algoritmalarının çalışması için
 * kullanılan sınıf
 */
public class ForestPopulation {
    public static int POPULATION_COUNT = 200;
    List<BinaryTree> trees;
    int goal;

    public ForestPopulation(List<BinaryTree> tree, int goal) {
        super();
        trees = new ArrayList<BinaryTree>();
        this.trees = tree;
        this.goal = goal;

        /*for(int i = 0; i < trees.size(); i++) {
            System.out.println(trees.get(i).getResult() + " ");
        }*/
    }


    public BinaryTree findTree() {
        boolean found = false;
        int generationCount = 0;

        //doğru sonunu veren ağaç bulunana kadar devam eder
        while(!found) {
            generationCount ++;
            //ağaçarı sonuca yakınlık derecesine göre sırala
            Collections.sort(trees, new AccuracyComparator());
            System.out.println("Nesil sayısı:" + generationCount + " En yakın:" + trees.get(0).getAccuracy());

            if(trees.get(0).getAccuracy() == 0) { //sonuca en yakın olanın değeri 0sa bulunmuştur
                found  = true;
            } else {
                trees = generateChild();
            }
        }

        System.out.println("Nesil sayısı:" + generationCount);
        trees.get(0).getNodeList().get(0).setGeneration(generationCount);
        return  trees.get(0);
    }

    private List<BinaryTree> generateChild() {
        List<BinaryTree> newGeneration = new ArrayList<>();

        /*her parent tan iki child üretilir
            her üretme işlemi sonucu, populasyona ait ağaç listesi güncellenir
         */
        for(int i = 0; i < trees.size(); i += 2) {
            List<Character> firstOperators = findOperators(trees.get(i));
            List<Integer> firstNumbers = findValues(trees.get(i));
            List<Character> secondOperators = findOperators(trees.get(i+1));
            List<Integer> secondNumbers = findValues(trees.get(i+1));

            Crossover crossover = new Crossover();
            crossover.uniformCrossoverForOperators(firstOperators, secondOperators);
            crossover.basedOrderCrossoverForNumbers(firstNumbers, secondNumbers);

            List<Character> firstChildOperators = crossover.getFirstChildOperators();
            List<Character> secondChildOperators = crossover.getSecondChildOperators();
            List<Integer> firstChildNumbers = crossover.getFirstChildNumbers();
            List<Integer> secondChildNumbers = crossover.getSecondChildNumbers();

            BinaryTree firstChild = new BinaryTree(firstChildOperators, firstChildNumbers);
            BinaryTree secondChild = new BinaryTree(secondChildOperators, secondChildNumbers);
            newGeneration.add(firstChild);
            newGeneration.add(secondChild);
        }

        return newGeneration;
    }

    //verilen ağacın değerlerini döner
    private List<Character> findOperators(BinaryTree tree) {
        List<Character> operators = new ArrayList<>();

        for(Integer id: tree.getNodeList().keySet()) {
            if(tree.getNodeList().get(id).getType() == Node.NodeType.BRANCH) {
                operators.add(tree.getNodeList().get(id).getOperator());
            }
        }

        return operators;
    }

    //verilen ağacın yaprak değerlerini döner
    private List<Integer> findValues(BinaryTree tree) {
        List<Integer> values = new ArrayList<>();

        for(Integer id: tree.getNodeList().keySet()) {
            if(tree.getNodeList().get(id).getType() == Node.NodeType.LEAF) {
                values.add(tree.getNodeList().get(id).getValue());
            }
        }

        return values;
    }

    private static class AccuracyComparator implements Comparator<BinaryTree> {
        public int compare(BinaryTree bt1, BinaryTree bt2) {
            return bt1.compareTo(bt2);
        }
    }
}
