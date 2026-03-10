package ua.voloschenko.theme8;

import java.util.ArrayList;
import java.util.List;

public class task1 {

    public static void main(String[] args) {
        List glist = new ArrayList();
        glist.add("Message");
        glist.add(42);
        System.out.println("Raw list: ");
        for(Object o : glist){
            try {
                String text = (String) o;
                System.out.println(text);
            } catch (ClassCastException e) {
                System.out.println("ClassCastException");
            }
        }

        List<String> realGlist = new ArrayList<>();
        realGlist.add("Message");
//        realGlist.add(42);
        System.out.println("Parametrized list: ");
        for(String s : realGlist){
            System.out.println(s);
        }
    }
}
