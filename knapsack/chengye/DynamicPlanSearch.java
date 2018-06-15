package chengye;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class DynamicPlanSearch extends AbstractKnapsack {


    public DynamicPlanSearch(int items, int[] weights, int[] values, int capacity) {
        super(items, weights, values, capacity);
    }

    @Override
    public void run() {
        this.optimal = 0;
        this.objective = 0;
        this.selected = new int[items];

        /**
         * for j from 0 to W do:

            m[0, j] := 0


         for i from 1 to n do:

             for j from 0 to W do:

                if w[i] > j then:

                     m[i, j] := m[i-1, j]

                 else:

                    m[i, j] := max(m[i-1, j], m[i-1, j-w[i]] + v[i])
         */

        int[][] m = new int[items + 1][capacity + 1];
        for(int i = 0; i < items + 1; i++){
            m[i] = new int[capacity + 1];
        }

        for(int j = 0; j < capacity + 1; j++){
            m[0][j] = 0;
        }

        for(int i = 1; i < items + 1; i++){
            for(int j = 0; j < capacity + 1; j++){
                if(weights[i - 1] > j){
                    m[i][j] = m[i - 1][j];
                }else{
                    if(i == items){

                    }
                    m[i][j] = Math.max(m[i -1][j], m[i -1][j-weights[i - 1]] + values[i - 1]);
                }
            }
        }

        int i = items;
        int j = capacity;
        while(true){
            if(i == 0 || j == 0){
                break;
            }
            if(m[i][j] == m[i - 1][j]){
                i = i - 1;
            }else{
                selected[i - 1] = 1;
                j = j - weights[i - 1];
                i = i - 1;
            }
        }

        this.objective = m[items][capacity];

    }
}
