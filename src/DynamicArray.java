import java.lang.reflect.Array;

public class DynamicArray<a> {
    private a[] arr;
    private int size;

    public DynamicArray(Class<a> type) {
        arr = (a[]) Array.newInstance(type, 1);
        size = 0;
    }
    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }

    public a get(int idx) {
        if (idx < 0 || idx >= size) {return null;}
        return arr[idx];
    }

    public boolean contains(a element) {
        for (int i = 0; i < size; ++i) {
            if (element.equals(arr[i])) return true;
        }
        return false;
    }

    public void add(a element) {
        if (size == arr.length) {
            resizeArray();
        }
        arr[size++] = element;
    }

    public void add(int idx, a element) {
        if (idx < 0 || idx > size) {
            throw new IllegalArgumentException("Array Index out of Bounds!");
        }
        if (size == arr.length) {
            resizeArray();
        }
        System.arraycopy(arr, idx, arr, idx + 1, size++ - idx);
        arr[idx] = element;
    }

    public void set(int idx, a element) {
        if (idx < 0 || idx >= size) {
            throw new IllegalArgumentException("Array Index out of Bounds!");
        }
        arr[idx] = element;
    }

    public a remove(a element) {
        int idx = -1;
        for (int i = 0; i < size; ++i) {
            if (element.equals(arr[i])) {
                idx = i;
                break;
            }
        }
        if (idx != -1) {
            remove(idx);
        }
        return element;
    }

    public a remove(int idx) {
        if (idx < 0 || idx >= size) {
            throw new IllegalArgumentException("Array Index out of Bounds!");
        }
        a removedElement = arr[idx];
        System.arraycopy(arr, idx+1, arr, idx, size-idx-1);
        arr[--size] = null;
        return removedElement;
    }

    public a removeAll(a element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(arr[i])) {
                remove(i);
                i--;
            }
        }
        return element;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            arr[i] = null;
        }
        size = 0;
    }

    private void resizeArray() {
        int newCapacity = arr.length*2;
        a[] newArray = (a[]) Array.newInstance((Class<a>) arr.getClass().getComponentType(), newCapacity);
        System.arraycopy(arr, 0, newArray, 0, size);
        arr = newArray;
    }
}
