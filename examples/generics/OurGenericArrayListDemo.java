class OurGenericArrayList<T> {
    T[] items;
    int itemCount;

    public OurGenericArrayList() {        
        items = (T[])new Object[10];
        itemCount = 0;
    }

    public T get(int index) {
        return items[index];
    }

    public void add(T item) {
        items[itemCount++] = item;
    }

    public int size() {
        return itemCount;
    }
}
