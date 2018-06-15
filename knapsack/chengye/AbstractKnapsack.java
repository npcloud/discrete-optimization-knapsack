package chengye;

public abstract class AbstractKnapsack {

    /**
     *  number of items;
     **/
    protected int items;

    /**
     * weights of items
     */
    protected int[] weights;

    /**
     * values of items
     */
    protected int[] values;

    /**
     * total capacity of bag
     */
    protected int capacity;

    /**
     * optimized result
     */
    protected int[] selected;

    /**
     * optimized objective
     */
    protected int objective;

    /**
     * is optimal result found
     */
    protected  int optimal;

    /**
     * calc result
     */
    public abstract void run();

    public AbstractKnapsack(int items, int[] weights, int[] values, int capacity) {
        this.items = items;
        this.weights = weights;
        this.values = values;
        this.capacity = capacity;
    }

    public int getItems() {
        return items;
    }

    public void setItems(int items) {
        this.items = items;
    }

    public int[] getWeights() {
        return weights;
    }

    public void setWeights(int[] weights) {
        this.weights = weights;
    }

    public int[] getValues() {
        return values;
    }

    public void setValues(int[] values) {
        this.values = values;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int[] getSelected() {
        return selected;
    }

    public void setSelected(int[] selected) {
        this.selected = selected;
    }

    public int getObjective() {
        return objective;
    }

    public void setObjective(int objective) {
        this.objective = objective;
    }

    public int isOptimal() {
        return optimal;
    }

}
