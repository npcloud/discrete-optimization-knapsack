package chengye;

import java.util.*;
import java.util.stream.IntStream;

public class BBSearch extends AbstractKnapsack {


    public BBSearch(int items, int[] weights, int[] values, int capacity) {
        super(items, weights, values, capacity);
    }

    public static class Node  {
        /**
         * current search point
         */
        protected  int nodeIndex;

        /**
         * Height of node
         */
        protected int nodeHeight;

        /**
         * used capacity
         */
        protected int nodeCapacity;

        /**
         * node accumulated value
         */
        protected int nodeValue;

        /**
         * is selected
         */
        protected int selected;

        /**
         * parent node
         */
        protected  Node prev;

        public Node(int nodeHeight,  int nodeIndex, int selected, int nodeCapacity, int nodeValue, Node prev) {
            this.nodeHeight = nodeHeight;
            this.selected = selected;
            this.nodeIndex = nodeIndex;
            this.nodeCapacity = nodeCapacity;
            this.nodeValue = nodeValue;
            this.prev = prev;
        }

    }

    private double calcRelax(Node node){
        double objective = node.nodeValue;
        double weight = node.nodeCapacity;


        for(int i=node.nodeHeight + 1; i < items; i++){
            if(weight + weights[i] <= capacity){
                objective += values[i];
                weight += weights[i];
            } else if(weight < capacity) {
                double avlCap = capacity - weight;
                objective += (values[i] * avlCap / weights[i]);
                weight += avlCap;
            }
        }
        return objective;
    }

    @Override
    public void run() {
        this.optimal = 0;
        this.objective = 0;
        this.selected = new int[items];
        Node bestNode = null;

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


        List<Node> queue = new LinkedList<>();
        queue.add(new Node(-1, -1, 0 ,0, 0, null));

        while(!queue.isEmpty()){
            //get node with max depth
            Node node = queue.remove(0);

            int nextHeight = node.nodeHeight + 1;
            //node is expand fully
            if(nextHeight >= items){
                continue;
            }

            int nextIndex = indexes.get(nextHeight);

            //save optimal solution
            if(node.nodeValue > this.objective){
                this.objective = node.nodeValue;
                bestNode = node;
            }



            int nextCap = weights[nextIndex];
            int nextValue = values[nextIndex];


            //use this item
            if(nextCap + node.nodeCapacity <= capacity) {
                Node left = new Node(nextHeight, nextIndex, 1, node.nodeCapacity + nextCap,
                        node.nodeValue + nextValue, node);

                //calc relax objective for node
                double relaxOjb = calcRelax(left);
                if(relaxOjb >= objective) {
                    queue.add(left);
                }
            }

            //do not use this item
            Node right = new Node(nextHeight, nextIndex, 0, node.nodeCapacity, node.nodeValue, node);
            double relaxOjb = calcRelax(right);
            if(relaxOjb >= objective) {
                queue.add(right);
            }

        }

        //collect selected
        while(bestNode != null){
            if(bestNode.nodeIndex >= 0 && bestNode.selected == 1) {
                this.selected[bestNode.nodeIndex] = 1;
            }
            bestNode = bestNode.prev;
        }


    }
}
