import java.util.*;
public class Main5{
    public static void main(String[] args){
        Scanner obj = new Scanner(System.in);
        System.out.print("Enter maximum limit for universe from 0: ");
        int univ_max = obj.nextInt();

        System.out.print("Enter no of elements in set a: ");
        int a = obj.nextInt();
        System.out.print("Enter no of elements in set b: ");
        int b = obj.nextInt();

        ArrayList<Integer> arr1 = new ArrayList<>(), arr2 = new ArrayList<>();

        System.out.println("Give elements to add in sets (outside universe replaced by 0): ");
        ArrayList<Integer> universe = new ArrayList<>();
        for(int i=0;i<=univ_max;i++){
            System.out.print(i+" ");
            universe.add(i);
        }
        System.out.println();

        System.out.print("Enter elements of set a: ");
        int temp;
        boolean present;
        for(int i=0;i<a;i++){
            temp = obj.nextInt();
            if(temp<0 || temp>univ_max){
                temp = 0;
            }
            present = false;
            for (Integer integer : arr1)
                if (integer == temp) {
                    present = true;
                    break;
                }
            if(!present){
                arr1.add(temp);
            }
        }

        System.out.print("Enter elements of set b: ");
        for(int i=0;i<b;i++){
            temp = obj.nextInt();
            if(temp<0 || temp>univ_max){
                temp = 0;
            }
            present = false;
            for (Integer integer : arr2) {
                if (integer == temp) {
                    present = true;
                    break;
                }
            }
            if(!present){
                arr2.add(temp);
            }
        }
        System.out.println("\nUsing arrays");
        long startTime = System.nanoTime();
        ArrayList<Integer> union = new ArrayList<>();
        for (Integer element : arr1) {
            present = false;
            for (Integer integer : union) {
                if (Objects.equals(element, integer)) {
                    present = true;
                    break;
                }
            }
            if (!present) {
                union.add(element);
            }
        }
        for (Integer element : arr2) {
            present = false;
            for (Integer integer : union) {
                if (element.equals(integer)) {
                    present = true;
                    break;
                }
            }
            if (!present) {
                union.add(element);
            }
        }
        System.out.print("Union of a and b: ");
        for (Integer element : union) {
            System.out.print(element + " ");
        }
        System.out.println();
        long duration = System.nanoTime() - startTime;
        System.out.println("Time for union with arrays: " + duration);
        startTime = System.nanoTime();
        ArrayList<Integer> intersection = new ArrayList<>();
        for (Integer item : arr1) {
            present = false;
            for (Integer integer : arr2) {
                if (item.equals(integer)) {
                    present = true;
                    break;
                }
            }
            if (present) {
                intersection.add(item);
            }
        }
        System.out.print("\nIntersection of a and b: ");
        for (Integer value : intersection) {
            System.out.print(value + " ");
        }
        System.out.println();
        duration = System.nanoTime() - startTime;
        System.out.println("Time for intersection with arrays: " + duration);
        startTime = System.nanoTime();
        ArrayList<Integer> diff1 = new ArrayList<>();
        for (Integer value : universe) {
            present = false;
            for (Integer integer : arr1) {
                if (integer.equals(value)) {
                    present = true;
                    break;
                }
            }
            if (!present) {
                diff1.add(value);
            }
        }
        System.out.print("\nSet difference of a: ");
        for (Integer value : diff1) {
            System.out.print(value + " ");
        }
        System.out.println();

        ArrayList<Integer> diff2 = new ArrayList<>();
        for (Integer value : universe) {
            present = false;
            for (Integer integer : arr2) {
                if (integer.equals(value)) {
                    present = true;
                    break;
                }
            }
            if (!present) {
                diff2.add(value);
            }
        }
        System.out.print("Set difference of b: ");
        for (Integer integer : diff2) {
            System.out.print(integer + " ");
        }
        System.out.println();
        duration = System.nanoTime() - startTime;
        System.out.println("Time for union with arrays: " + duration);


        //Sets
        System.out.println("\n\nUsing sets");
        Set<Integer> universe_s, s1, s2;
        universe_s = new HashSet<>(universe);
        s1 = new HashSet<>(arr1);
        s2 = new HashSet<>(arr2);

        startTime = System.nanoTime();
        Set<Integer> union_s = new HashSet<>(a);
        union_s.addAll(s2);
        System.out.print("\nUnion of a and b: ");
        System.out.println(union);
        duration = System.nanoTime() - startTime;
        System.out.println("Time for union with sets: " + duration);


        startTime = System.nanoTime();
        Set<Integer> intersection_s = new HashSet<>(a);
        union_s.retainAll(s2);
        System.out.print("\nIntersection of a and b: ");
        System.out.println(intersection);
        duration = System.nanoTime() - startTime;
        System.out.println("Time for intersection with sets: " + duration);

        startTime = System.nanoTime();
        Set<Integer> diff1_s = new HashSet<>(universe_s), diff2_s = new HashSet<>(universe_s);
        diff1_s.removeAll(s1);
        System.out.print("\nSet difference of a");
        System.out.println(diff1_s);

        diff2_s.removeAll(s2);
        System.out.print("Set difference of b");
        System.out.println(diff2_s);
        duration = System.nanoTime() - startTime;
        System.out.println("Time for set difference with sets: " + duration);

        System.out.println("Time Complexity of sets < arrays");
    }
}
