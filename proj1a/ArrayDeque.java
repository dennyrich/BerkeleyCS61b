public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int pointerF; //first pointer
    private int pointerL; //last pointer
    /* pointerf first points to the item where the first item is. PointerL
        points at the place where addLast would fill next
     */

    public ArrayDeque() {
        size = 0;
        items = (T[]) new Object[8];
        for (T i: items) {
            i = null;
        }
        pointerF = 1;
        pointerL = 1;
    }
    public ArrayDeque(ArrayDeque other) {
        size = other.size;
        pointerL = other.pointerL;
        pointerF = other.pointerF;
        items = (T[]) other.items;
    }

    /* using circular implementation */
    public void addFirst(T item) {
        size += 1;
        if (size > items.length) {
            resizeUp();
        }
        if (pointerF == 0) {
            pointerF = items.length - 1;
        } else {
            pointerF -= 1;
        }
        items[pointerF] = item;
    }
    public void addLast(T item) {
        size += 1;
        if (size > items.length) {
            resizeUp();
        }
        items[pointerL] = item;
        /* this moves the pointer to the begining */
        if (pointerL == items.length - 1) {
            pointerL = 0;
        } else {
            pointerL += 1;
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        T item = items[pointerF];
        /* this moves pointer back to 0 if at end */
        if (pointerF == items.length - 1) {
            pointerF = 0;
        } else {
            pointerF += 1;
        }
        if (size < items.length / 4) {
            resizeDown();
        }
        return item;
    }
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        T item = items[pointerL - 1];
        /* this moves pointer back to end if at 0 */
        if (pointerL == 0) {
            pointerL = items.length - 1;
        } else {
            pointerL -= 1;
        }
        if (size < items.length / 4) {
            resizeDown();
        }
        return item;
    }

    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public T get(int index) {
        int i = pointerF + index;
        if (i > items.length - 1) {
            i = i - items.length;
        }
        return items[i];
    }
    public void printDeque() {
        for (int i = pointerF; i < items.length && i < (pointerF + size); i++) {
            System.out.print(items[i] + " ");
        }
        if (!(pointerF < pointerL)) {  // this means that the array is "circular"
            for (int i = 0; i < pointerL; i++) {
                System.out.print(items[i] + " ");
            }
        }
        System.out.println();
    }

    private void resizeUp() {
        T[] temp = (T[]) new Object[items.length + items.length / 2];
        int offset = temp.length - items.length;
        for (int i = 0; i < pointerL; i++) {
            temp[i] = items[i];
        }
        for (int i = pointerL; i < pointerL + offset; i++) {
            temp[i] = null;
        }
        for (int i = pointerL + offset; i < temp.length; i++) {
            temp[i] = items[i - offset];
        }
        items = temp;
        pointerF += offset;
    }

    private void resizeDown() {
        T[] temp = (T[]) new Object[items.length / 3];
        int counter = 0;
        if (pointerF > pointerL) {
            for (int i = 0; i < pointerL; i++) {
                temp[counter] = items[i];
                counter++;
            }
            for (int i = pointerF; i < items.length; i++) {
                temp[counter] = items[i];
                counter++;
            }
            pointerF = 0;
            pointerL = counter;
        } else {
            for (int i = pointerF; i < pointerL; i++) {
                temp[counter] = items[i];
                counter++;
            }
            pointerF = 0;
            pointerL = counter;
        }

        items = temp;
    }

    private void printRawItems() { // for testing only
        System.out.print("Items (for testing only):");
        for (T item: items) {
            System.out.print(item + " ");
        }
        System.out.println();
        System.out.println("pointerF: " + pointerF + " pointerL: " + pointerL);
    }
}
