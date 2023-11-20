package exp01;
class SortRecord {
    String sorter;
    int arraySize;
    long duration;
    String order;

    public SortRecord(String sorter, int arraySize, long duration, String order) {
        this.sorter = sorter;
        this.arraySize = arraySize;
        this.duration = duration;
        this.order = order;
    }

    @Override
    public String toString() {
        return sorter + "," + arraySize + "," + duration + "," + order;
    }
}

