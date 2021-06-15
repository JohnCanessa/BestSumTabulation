import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 
 */
public class BestSumTabulation {


    /**
     * Write a function `bestSum(targetSum, numbers)` that takes in a
     * targetSum and an array of numbers as arguments.
     * 
     * The function should return an array containing the shortest
     * combination of numbers that add up exactly the targetSum.
     * 
     * If there is a tie for the shortest combination, you may return any
     * one of the shortest.
     * 
     * m = targetSum
     * n = numbers.length
     * 
     * Time: O(m^2 * n)  -  Space: O(m^2)
     */
    static int[] bestSum(int targetSum, int[] numbers) {

        // **** sanity check(s) ****
        if (targetSum == 0) return null;

        // **** initialization ****
        List<List<Integer>> table = new ArrayList<List<Integer>>();
        List<Integer> lst = new ArrayList<>();

        table.add(lst);
        for (int i = 1; i <= targetSum; i++)
            table.add(null);

        // ???? ????
        // System.out.println("<<< table.size: " + table.size());
        // for (int i = 0; i < table.size(); i++) {
        //     if (table.get(i) == null)
        //         System.out.println("<<< table[" + i + "]: null");
        //     else 
        //         System.out.println("<<< table[" + i + "]: " + table.get(i).toString());
        // }

        // **** iterate through the table ****
        for (int i = 0; i <= targetSum; i++) {

            // **** skip this entry (if needed) ****
            if (table.get(i) == null)
                continue;

            // **** loop through the numbers[] array **** 
            for (int j = 0; j < numbers.length; j++) {

                // **** for ease of use ****
                int num = numbers[j];

                // **** compute target index (for ease of use) ****
                int ndx = i + num;

                // **** skip (if ndx is out of bounds) ****
                if (ndx > targetSum)
                    continue;

                // **** initialize this list (if needed) ****
                lst = table.get(ndx);
                if (lst == null)
                    lst = new ArrayList<>();

                // **** copy all elements from table[i] to table[ndx] ****
                List<Integer> src = table.get(i);
                List<Integer> dst = table.get(ndx);

                // **** ****
                dst = new ArrayList<Integer>();
                dst.addAll(src);

                // **** add current element to dst list ****
                dst.add(num);

                // **** replace list at ndx (if shorter) ****
                if (table.get(ndx) == null || dst.size() < table.get(ndx).size()) {
                    table.remove(ndx);
                    table.add(ndx, dst);
                }
            }

            // ???? ????
            // for (int k = 0; k < table.size(); k++) {
            //     if (table.get(k) == null)
            //         System.out.println("<<< table[" + k + "]: null");
            //     else 
            //         System.out.println("<<< table[" + k + "]: " + table.get(k).toString());
            // }

        }

        // ???? ????
        // for (int k = 0; k < table.size(); k++) {
        //     if (table.get(k) == null)
        //         System.out.println("<<< table[" + k + "]: null");
        //     else 
        //         System.out.println("<<< table[" + k + "]: " + table.get(k).toString());
        // }

        // **** return array at table[targetSum] ****
        if (table.get(targetSum) == null)
            return null;
        else
            return table.get(targetSum).stream().mapToInt(x -> x).toArray();
    }


    /**
     * Test scaffold
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** read target sum ****
        int targetSum = Integer.parseInt(br.readLine().trim());

        // **** read int[] numbers ****
        int[] numbers = Arrays.stream(br.readLine().trim().split(","))
                            .mapToInt(Integer::parseInt)
                            .toArray();

        // **** close buffered reader ****
        br.close();

        // ???? ????
        System.out.println("main <<< targetSum: " + targetSum);
        System.out.println("main <<<   numbers: " + Arrays.toString(numbers));

        // **** generate and display result ****
        int[] arr = bestSum(targetSum, numbers);
        if (arr == null)
            System.out.println("main <<<       ans: null");
        else 
            System.out.println("main <<<       ans: " + Arrays.toString(arr));
    }
}