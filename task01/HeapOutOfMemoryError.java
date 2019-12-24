package part01.lesson12.task01;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * -XX:+UseSerialGC -Xmx16m
 * return OutOfMemoryError Heap
 *
 * @author Oleg_Chapurin
 */
public class HeapOutOfMemoryError {
    private Integer in = 20;

    public void setIn(Integer i) {
        in += i;
    }

    /**
     * -XX:+UseSerialGC -Xmx16m
     * return OutOfMemoryError Heap
     */
    public static void main(String[] args) {
        LinkedList queue = new LinkedList();
        List<HeapOutOfMemoryError> list = new ArrayList<>();
        for (int i = 0; i < 10000000; i++) {
            HeapOutOfMemoryError tt = new HeapOutOfMemoryError();
            HeapOutOfMemoryError tt2 = new HeapOutOfMemoryError();
            tt.setIn(i);
            queue.add(tt);
            tt2.setIn(i);
            list.add(tt2);
            if ((i % 5) == 0) {
                queue.poll();
                queue.poll();
                if (list.size() > 2) {
                    list.remove(1);
                }
                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
