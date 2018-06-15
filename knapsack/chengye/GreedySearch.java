package chengye;

import java.util.*;
import java.util.stream.IntStream;

public class GreedySearch extends AbstractKnapsack {


    public GreedySearch(int items, int[] weights, int[] values, int capacity) {
        super(items, weights, values, capacity);
    }

    @Override
    public void run() {
        this.optimal = 0;
        this.objective = 0;
        this.selected = new int[items];
        int weight = 0;

        //take item with max value/weight ratio
        double[] ratios = new double[items];
        IntStream.range(0, items).forEach(i -> {
            ratios[i] = (double) values[i] / (double) weights[i];
        });

        List<Integer> indexes = new ArrayList<>(items);
        for(int i = 0; i < items; i++){
            indexes.add(i);
        }
        //sort indexes array by ratios
        Collections.sort(indexes, (o1, o2) -> (ratios[o1] > ratios[o2])?-1:1);

        for(int i=0; i < items; i++){
            int item2select = indexes.get(i);
            if(weight + weights[item2select] <= capacity){
                this.selected[item2select] = 1;
                objective += values[item2select];
                weight += weights[item2select];
            } else {
                this.selected[item2select] = 0;
            }
        }


    }
}
