package part01.lesson02.task03;

import java.util.ArrayList;

public class ThirdSort implements Sorting {
    @Override
    public void sort(ArrayList<Person> personArrayList) {
        int size = personArrayList.size();
        int i,e;
        for (i = 1; i < size; i++){
            Person tmp = personArrayList.get(i);
            e = i;
            while(e > 0 && (personArrayList.get(e-1).compareTo(tmp)) > 0){
                personArrayList.set(e,personArrayList.get(e-1));
                --e;
            }
            personArrayList.set(e,tmp);
        }
    }
}
