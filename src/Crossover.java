import java.util.*;

/**
 * Created by mgunes on 02.12.2016.
 *
 * Crossover işlemlerine ait bilgi ve metotlara sahip olan sınıf
 */

public class Crossover {
    private List<Character> firstChildOperators;
    private List<Character> secondChildOperators;
    private List<Integer> firstChildNumbers;
    private List<Integer> secondChildNumbers;

    public Crossover() {
        firstChildOperators = new ArrayList<>();
        secondChildOperators = new ArrayList<>();
        firstChildNumbers = new ArrayList<>();
        secondChildNumbers = new ArrayList<>();
    }

    //uniform cross over algoritmasının implement edilmesi
    public void uniformCrossoverForOperators( List<Character> firstOperators,  List<Character> secondOperators  ) {
        Random random = new Random();

        for(int i  = 0; i < firstOperators.size(); i++) {
            if(random.nextInt(2) == 0) { //aynen al
                firstChildOperators.add(firstOperators.get(i));
                secondChildOperators.add(secondOperators.get(i));
            } else { //yer değiştir
                firstChildOperators.add(secondOperators.get(i));
                secondChildOperators.add(firstOperators.get(i));
            }
        }
    }

    //Sıra tabanlı crossover algoritmasının implement edilmesi
    public void basedOrderCrossoverForNumbers( List<Integer> firstNumbers,  List<Integer> secondNumbers) {
        List<Integer> template = new ArrayList<>();
        Map<Integer, Integer> change = new HashMap<Integer, Integer>();
        List<Integer> changeOrder = new ArrayList<>();
        Random r = new Random();

        //template oluştur
        for(int i = 0; i < firstNumbers.size(); i++) {
            template.add(r.nextInt(2));
        }

        //child1 için sırası değişecek olan sayıları al
        for(int i = 0; i < template.size(); i++) {
            if(template.get(i) == 0) {
                change.put(firstNumbers.get(i), firstNumbers.get(i));
            }
        }

        //değişecek sayıları sırayla elde et
        for(int i = 0; i < template.size(); i++) {
            if(change.containsKey(secondNumbers.get(i))) {
                changeOrder.add(secondNumbers.get(i));
            }
        }

        //child1 için values u oluştur
        int j = 0;
        for(int i = 0; i < template.size(); i++) {
            if(template.get(i) == 1) {
                firstChildNumbers.add(firstNumbers.get(i));
            } else {
                firstChildNumbers.add(changeOrder.get(j));
                j++;
            }
        }

        change = new HashMap<Integer, Integer>();
        changeOrder = new ArrayList<>();

        //child2 için sırası değişecek sayıları al
        for(int i = 0; i < template.size(); i++) {
            if(template.get(i) == 0) {
                change.put(secondNumbers.get(i), secondNumbers.get(i));
            }
        }

        //değişecek sayıları sırayla elde et
        for(int i = 0; i < template.size(); i++) {
            if(change.containsKey(firstNumbers.get(i))) {
                changeOrder.add(firstNumbers.get(i));
            }
        }

        //child2 için values u oluştur
        j = 0;
        for(int i = 0; i < template.size(); i++) {
            if(template.get(i) == 1) {
                secondChildNumbers.add(secondNumbers.get(i));
            } else {
                secondChildNumbers.add(changeOrder.get(j));
                j++;
            }
        }
    }

    //getter-setter
    public List<Character> getFirstChildOperators() {
        return firstChildOperators;
    }

    public void setFirstChildOperators(List<Character> firstChildOperators) {
        this.firstChildOperators = firstChildOperators;
    }

    public List<Character> getSecondChildOperators() {
        return secondChildOperators;
    }

    public void setSecondChildOperators(List<Character> secondChildOperators) {
        this.secondChildOperators = secondChildOperators;
    }

    public List<Integer> getFirstChildNumbers() {
        return firstChildNumbers;
    }

    public void setFirstChildValues(List<Integer> firstChildValues) {
        this.firstChildNumbers = firstChildValues;
    }

    public List<Integer> getSecondChildNumbers() {
        return secondChildNumbers;
    }

    public void setSecondChildValues(List<Integer> secondChildValues) {
        this.secondChildNumbers = secondChildValues;
    }
}