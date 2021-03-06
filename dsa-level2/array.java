
/**
 * array
 */
import java.util.*;

import javax.sound.midi.Sequence;

public class array {

    // Faulty keyboard question
    public boolean isLongPressedName(String name, String typed) {
        if (name.length() > typed.length())
            return false;
        int i = 0; // name
        int j = 0; // typed
        while (i < name.length() && j < typed.length()) {
            if (name.charAt(i) == typed.charAt(j)) {
                i++;
                j++;

            } else if (i - 1 >= 0 && name.charAt(i - 1) == typed.charAt(j)) {
                j++;
            } else {
                return false;

            }
        }

        while (j < typed.length()) {
            if (typed.charAt(j) != name.charAt(i - 1))
                return false;
            j++;
        }

        return i < name.length() ? false : true;

    }

    // Container with most water
    public static int mostWater(int[] heights) {
        // write your code here
        int max = 0;
        int i = 0;
        int j = heights.length - 1;
        while (i < j) {
            if (heights[i] < heights[j]) {

                int l = j - i;
                max = Math.max(max, l * heights[i]);
                i++;

            } else {
                int l = j - i;
                max = Math.max(max, l * heights[j]);
                j--;

            }
        }
        return max;
    }

    // sort Squares of sorted array in O(N)
    public int[] sortedSquares(int[] nums) {
        int res[] = new int[nums.length];
        int i = 0;
        int j = nums.length - 1;
        for (int k = nums.length - 1; k >= 0; k--) {
            int val1 = nums[i];
            int val2 = nums[j];
            if (val1 * val1 > val2 * val2) {
                res[k] = val1 * val1;
                i++;
            } else {
                res[k] = val2 * val2;
                j--;
            }
        }
        return res;

    }

    // majority elemnt 1
    public static void printMajorityElement(int[] arr) {
        // write your code here
        int count = 0;
        int me = findpotential(arr);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == me)
                count++;
        }
        if (count > arr.length / 2)
            System.out.println(me);
        else
            System.out.println("No Majority Element exist");
    }

    public static int findpotential(int[] arr) {
        int count = 1;
        int val = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == val)
                count++;
            else {
                if (count > 0)
                    count--;
                else {
                    val = arr[i];
                    count = 1;
                }
            }
        }
        return val;

    }

    public static ArrayList<Integer> majorityElement2(int[] arr) {
        // find valid canidate
        int val1 = arr[0];
        int count1 = 1;

        int val2 = arr[0];
        int count2 = 0;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == val1) {
                count1++;
            } else if (arr[i] == val2)
                count2++;
            else {
                if (count1 == 0) {
                    val1 = arr[i];
                    count1 = 1;
                } else if (count2 == 0) {
                    val2 = arr[i];
                    count2 = 1;
                } else {
                    count1--;
                    count2--;
                }
            }
        }

        // check freq>n/3
        ArrayList<Integer> list = new ArrayList<>();
        if (count1 > 0 && count1 > arr.length / 3)
            list.add(count1);
        if (count2 > 0 && count2 > arr.length / 3)
            list.add(count2);
        return list;

    }

    // next greater permutation or next greater element 3
    public static String nextGreaterElement(String str) {
        // write your code here
        char[] arr = str.toCharArray();
        int dipidx = dipIdx(arr);
        if (dipidx == -1)
            return "-1";

        int ceilidx = ceilIndx(arr, dipidx);
        swap(arr, dipidx, ceilidx);
        reverse(arr, dipidx + 1);
        return String.valueOf(arr);
    }

    static int dipIdx(char arr[]) {
        int idx = arr.length - 2;
        while (idx >= 0 && arr[idx] >= arr[idx + 1])
            idx--;

        return idx;

    }

    static int ceilIndx(char[] arr, int idx) {
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] > arr[idx])
                return i;
        }
        return -1;
    }

    static void reverse(char arr[], int idx) {
        int l = idx;
        int r = arr.length - 1;
        while (l < r) {
            swap(arr, l, r);
            l++;
            r--;
        }

    }

    static void swap(char arr[], int idx1, int idx2) {
        char temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;

    }

    // max product of three numbers
    public int maximumProduct(int[] arr) {

        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for (int val : arr) {
            if (val >= max1) {
                max3 = max2;
                max2 = max1;
                max1 = val;
            } else if (val >= max2) {
                max3 = max2;
                max2 = val;
            } else if (val >= max3) {
                max3 = val;
            }

            if (val <= min1) {
                min2 = min1;
                min1 = val;
            } else if (val <= min2) {
                min2 = val;
            }
        }

        return Math.max(max1 * max2 * max3, min1 * min2 * max1);

    }

    // leetcode 769. Max Chunks To Make Sorted->guaranteed all are distinct elements
    public int maxChunksToSorted1(int[] arr) {
        int chunks = 0;
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            if (max == i)
                chunks++;
        }

        return chunks;

    }

    // leetcode768. Max Chunks To Make Sorted II
    public int maxChunksToSorted2(int[] arr) {
        int lmax = Integer.MIN_VALUE;
        int rmin[] = new int[arr.length + 1];
        rmin[arr.length] = Integer.MAX_VALUE;
        int chunks = 0;
        for (int i = arr.length - 1; i >= 0; i--)
            rmin[i] = Math.min(rmin[i + 1], arr[i]);

        for (int i = 0; i < arr.length; i++) {
            lmax = Math.max(lmax, arr[i]);
            if (lmax <= rmin[i + 1])
                chunks++;
        }
        return chunks;

    }

    // Reverse vowels of string-leetcode 345
    public String reverseVowels(String s) {
        char[] arr = s.toCharArray();
        int l = 0;
        int r = arr.length - 1;
        while (l < r) {
            while (l < r && !isvowel(arr[l])) {
                l++;
            }
            while (l < r && !isvowel(arr[r])) {
                r--;
            }
            char temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            l++;
            r--;

        }
        return String.valueOf(arr);

    }

    // Leetcode->238. Product of Array Except Self
    public int[] productExceptSelf(int[] nums) {
        int lmul[] = new int[nums.length];
        int rmul[] = new int[nums.length];
        lmul[0] = 1;

        rmul[nums.length - 1] = 1;
        for (int i = 1; i < nums.length; i++) {
            lmul[i] = lmul[i - 1] * nums[i - 1];
        }

        for (int i = nums.length - 2; i >= 0; i--) {
            rmul[i] = rmul[i + 1] * nums[i + 1];
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = lmul[i] * rmul[i];
        }
        return nums;
    }

    static boolean isvowel(char ch) {
        if (ch == 'a' || ch == 'A' || ch == 'e' || ch == 'E' || ch == 'i' || ch == 'I' || ch == 'o' || ch == 'O'
                || ch == 'u' || ch == 'U')
            return true;
        else
            return false;
    }

    // Wiggel sort-1
    public static void wiggleSort(int[] arr) {
        // write your code here
        for (int i = 0; i < arr.length - 1; i++) {
            if (i % 2 == 0) {
                // even idx;
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);

                }
            } else {
                if (arr[i + 1] > arr[i])
                    swap(arr, i, i + 1);
            }
        }
    }

    public static void wiggleSort2(int[] nums) {
        // write your code here
        int n = nums.length;
        int arr[] = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = nums[i];
        Arrays.sort(arr);
        int j = n - 1;
        int i = 1;
        // Fill odd indexes
        while (i < n) {
            nums[i] = arr[j];
            j--;
            i += 2;
        }
        i = 0;
        while (i < n) {
            nums[i] = arr[j];
            j--;
            i = i + 2;
        }

    }

    // leetcode 75->overall sub array
    public static int numSubarrayBoundedMax(int[] arr, int left, int right) {
        // write your code here
        int prev_count = 0;
        int overall_count = 0;
        int i = 0;
        int j = 0;
        while (j < arr.length) {
            if (left <= arr[j] && arr[j] <= right) {
                prev_count = j - i + 1;
                overall_count += prev_count;
            } else if (arr[j] < left)
                overall_count += prev_count;
            else {
                prev_count = 0;
                i = j + 1;
            }
            j++;
        }
        return overall_count;
    }

    static void swap(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public int maxDistToClosest(int[] seats) {
        int dist = 0;
        int zeros = 0;

        int idx = 0;
        // left sub part
        while (seats[idx] != 1) {
            idx++;
            zeros++;
        }
        dist = zeros;
        idx++;

        // segment calculation
        while (idx < seats.length) {
            zeros = 0;
            while (idx < seats.length && seats[idx] != 1) {
                zeros++;
                idx++;
            }

            if (idx == seats.length)
                break;

            idx++;
            dist = Math.max(dist, (zeros + 1) / 2);
        }

        // right sub part
        return Math.max(zeros, dist);

    }

    // First Missing positive
    public int firstMissingPositive(int[] nums) {
        // Find occurence of one and mark out of range
        boolean one = false;
        int n = nums.length;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1)
                one = true;

            if (nums[i] < 1 || nums[i] > nums.length)
                nums[i] = 1;
        }

        if (one == false)
            return 1;

        // mark element in array
        for (int i = 0; i < n; i++) {
            int val = Math.abs(nums[i]);
            int idx = val - 1;
            nums[idx] = -Math.abs(nums[idx]);
        }

        // check missing postive
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0)
                return i + 1;
        }
        return n + 1;

    }

    // Seggregate odd Even in arr
    // Leetcode905. Sort Array By Parity
    public int[] sortArrayByParity(int[] nums) {
        int i = 0;
        int j = 0;
        while (i < nums.length) {
            if (nums[i] % 2 == 0) {
                swap(i, j, nums);
                i++;
                j++;
            } else {
                i++;
            }
        }
        return nums;

    }

    static void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // Range addition
    public static int[] getModifiedArray(int length, int[][] queries) {
        // write your code here
        int res[] = new int[length];
        for (int arr[] : queries) {
            int st = arr[0];
            int end = arr[1];
            for (int i = st; i <= end; i++) {
                res[i] += arr[2];
            }
        }
        return res;
    }

    // Optimised
    public static int[] getModifiedArrayop(int length, int[][] queries) {
        // write your code here
        int res[] = new int[length];
        // make impact of query on res
        for (int arr[] : queries) {
            int st = arr[0];
            int end = arr[1];
            int incre = arr[2];
            res[st] += incre;
            if (end != length - 1) {
                res[end + 1] -= incre;
            }
        }

        // prefix sum
        for (int i = 1; i < res.length; i++) {
            res[i] += res[i - 1];
        }
        return res;
    }

    // Meeting Point Minimal Distance
    public static int minTotalDistance(int[][] grid) {
        // Write your code here
        // Fill x coordinates in sorted manner
        ArrayList<Integer> xcord = new ArrayList<>();
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1)
                    xcord.add(r);
            }
        }
        ArrayList<Integer> ycord = new ArrayList<>();
        // Fill y coordinate in sorted manner
        for (int c = 0; c < grid[0].length; c++) {
            for (int r = 0; r < grid.length; r++) {
                if (grid[r][c] == 1)
                    ycord.add(c);
            }
        }
        // find meeting point
        int x = xcord.get(xcord.size() / 2);
        int y = ycord.get(ycord.size() / 2);
        int dist = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {
                    dist += Math.abs(x - r) + Math.abs(y - c);
                }
            }
        }
        return dist;

    }

    // Max. swaps required to get greatest number
    // leetcode 670
    public int maximumSwap(int n) {
        // convert number to sstring
        String num = n + "";
        char[] arr = num.toCharArray();
        int[] lastidx = new int[10];
        // Travel and Fill Last Index of digits
        for (int i = 0; i < arr.length; i++) {
            lastidx[arr[i] - '0'] = i;
        }

        // Travel and find swapping point
        for (int i = 0; i < arr.length; i++) {
            int digit = arr[i] - '0';
            int idx = i;
            for (int j = 9; j > digit; j--) {
                if (lastidx[j] > i) {
                    idx = lastidx[j];
                    break;

                }
            }
            if (idx != i) {
                swap(arr, i, idx);
                break;
            }

        }
        String res = String.valueOf(arr);
        return Integer.parseInt(res);

    }

    // Leetcode 537->Commplex number multiplication
    public static String complexNumberMultiply(String num1, String num2) {
        // write your code here
        int a1 = Integer.parseInt(num1.substring(0, num1.indexOf("+")));
        int b1 = Integer.parseInt(num1.substring(num1.indexOf("+") + 1, num1.length() - 1));

        int a2 = Integer.parseInt(num2.substring(0, num2.indexOf("+")));
        int b2 = Integer.parseInt(num2.substring(num2.indexOf("+") + 1, num2.length() - 1));

        int a = a1 * a2 - b1 * b2;
        int b = a1 * b2 + a2 * b1;
        return a + "+" + b + "i";

    }

    // Two sum pairs
    public static List<List<Integer>> twoSum(int[] arr, int target) {
        // write your code here
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(arr);
        int l = 0;
        int r = arr.length - 1;
        while (l < r) {
            if (l != 0 && arr[l] == arr[l - 1]) {
                l++;
                continue;
            }
            int sum = arr[l] + arr[r];
            if (sum < target) {
                l++;
            } else if (sum > target)
                r--;
            else {
                List<Integer> list = new ArrayList<>();
                list.add(arr[l]);
                list.add(arr[r]);
                res.add(list);
                l++;
                r--;
            }
        }

        return res;
    }

    // 3 sum
    public static List<List<Integer>> threeSum(int[] nums, int tar) {
        // write your code here
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i <= nums.length - 3; i++) {
            if (i != 0 && nums[i] == nums[i - 1])
                continue;
            int val = nums[i];
            int target = tar - val;
            List<List<Integer>> subres = twosum(nums, i + 1, nums.length - 1, target);
            for (List<Integer> list : subres) {
                list.add(0, val);
                res.add(list);
            }
        }
        return res;

    }

    public static List<List<Integer>> twosum(int[] nums, int lo, int hi, int tar) {
        int l = lo;
        int r = hi;
        List<List<Integer>> res = new ArrayList<>();
        while (l < r) {
            if (l != lo && nums[l] == nums[l - 1]) {
                l++;
                continue;
            }
            int sum = nums[l] + nums[r];
            if (sum == tar) {
                List<Integer> list = new ArrayList<>();
                list.add(nums[l]);
                list.add(nums[r]);
                res.add(list);
                l++;
                r--;
            } else if (sum > tar)
                r--;
            else
                l++;
        }
        return res;
    }

    // K sum-Target
    public static List<List<Integer>> kSum(int[] arr, int target, int k) {
        // write your code here
        Arrays.sort(arr);
        List<List<Integer>> res = ksum_(arr, target, 0, k);
        return res;
    }

    private static List<List<Integer>> ksum_(int arr[], int target, int si, int k) {
        if (k == 2) {
            return twosum(arr, si, arr.length - 1, target);
        }
        int n = arr.length;
        List<List<Integer>> res = new ArrayList<>();
        for (int i = si; i < n - (k - 1); i++) {
            if (i != si && arr[i] == arr[i - 1])
                continue;
            int val1 = arr[i];
            int targ = target - val1;
            List<List<Integer>> subres = ksum_(arr, targ, i + 1, k - 1);
            for (List<Integer> list : subres) {
                list.add(val1);
                res.add(list);
            }
        }
        return res;
    }

    // sieve of eranthosis
    public static void printPrimeUsingSieve(int n) {
        // write your code here
        boolean prime[] = new boolean[n + 1];
        Arrays.fill(prime, true);
        prime[1] = false;

        for (int i = 2; i * i <= n; i++) {
            if (prime[i] == true) {
                for (int j = i * 2; j <= n; j = j + i) {
                    prime[j] = false;
                }
            }
        }

        for (int i = 2; i <= n; i++) {
            if (prime[i] == true)
                System.out.print(i + " ");
        }
    }

    // Segmented sieve

    public static void segmentedSieveAlgo(int a, int b) {
        // write your code here
        int rootb = (int) Math.sqrt(b);
        ArrayList<Integer> primes = sieve(rootb);
        // prime[i]=false,then i is prime
        boolean[] isprime = new boolean[b - a + 1];
        for (int prime : primes) {
            int mutliple = (int) Math.ceil((a * 1.0) / prime);
            if (mutliple == 1)
                mutliple++;

            int si = mutliple * prime - a;
            for (int j = si; j < isprime.length; j += prime) {
                isprime[j] = true;
            }

        }
        for (int i = 0; i < isprime.length; i++) {
            if (isprime[i] == false && i + a != 1) {
                // val=idex+base
                int val = i + a;
                System.out.println(val);
            }
        }

    }

    private static ArrayList<Integer> sieve(int n) {
        boolean prime[] = new boolean[n + 1];

        for (int i = 2; i * i <= n; i++) {
            if (prime[i] == true) {
                continue;
            }
            for (int j = i * 2; j <= n; j = j + i) {
                prime[j] = true;
            }

        }
        ArrayList<Integer> ans = new ArrayList<>();

        for (int i = 2; i <= n; i++) {
            if (prime[i] == false)
                ans.add(i);
        }
        return ans;
    }

    // Push Dominoes
    public String pushDominoes(String dominoes) {
        int l = dominoes.length();
        char[] arr = new char[l + 2];
        arr[0] = 'L';
        arr[l + 1] = 'R';

        for (int i = 1; i <= l; i++) {
            arr[i] = dominoes.charAt(i - 1);
        }

        int i = 0;
        int j = 1;
        while (j < arr.length) {
            while (arr[j] == '.')
                j++;

            if (j - i > 1) {
                solveConfig(arr, i, j);

            }
            i = j;
            j++;
        }
        StringBuilder res = new StringBuilder();

        for (int k = 1; k <= l; k++)
            res.append(arr[k]);

        return res.toString();

    }

    static void solveConfig(char arr[], int i, int j) {
        if (arr[i] == 'L' && arr[j] == 'L') {
            for (int k = i + 1; k < j; k++)
                arr[k] = 'L';
        } else if (arr[i] == 'R' && arr[j] == 'R') {
            for (int k = i + 1; k < j; k++)
                arr[k] = 'R';
        } else if (arr[i] == 'L' && arr[j] == 'R') {
            // nothing to do
        } else if (arr[i] == 'R' && arr[j] == 'L') {
            i++;
            j--;
            while (i < j) {
                arr[i] = 'R';
                arr[j] = 'L';
                i++;
                j--;
            }

        }
    }
    // Consecutive Numbers sum

    public int consecutiveNumbersSum(int n) {

        int count = 0;
        for (int k = 1; k * (k - 1) < 2 * n; k++) {
            int num = n - (k * (k - 1)) / 2;
            if (num % k == 0)
                count++;
        }
        return count;

    }

    // Leetcode 754->Reach a number using +ve or -ve jumps
    public int reachNumber(int target) {
        target = Math.abs(target);
        int jump = 0;
        int s = 0;
        while (s < target) {
            jump++;
            s += jump;

        }
        if (s == target)
            return jump;
        else if ((s - target) % 2 == 0)
            return jump;
        else if ((s + jump + 1 - target) % 2 == 0)
            return jump + 1;
        else
            return jump + 2;
    }

    // Partition labels of String
    public List<Integer> partitionLabels(String s) {
        // 1.Make HashMap of last occurence
        HashMap<Character, Integer> map = new HashMapp<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            // update idx
            map.put(ch, i);
        }

        // 2.Solve using chaining technique
        List<Integer> res = new ArrayList<>();
        int prev = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            max = Math.max(max, map.get(ch));
            if (max == i) {
                res.add(i - prev + 1);
                prev = i + 1;
            }
        }
        return res;

    }

    // Number of boats required to save people
    public int numRescueBoats(int[] people, int limit) {
        int boats = 0;
        Arrays.sort(people);
        int l = 0;
        int r = people.length - 1;
        while (l <= r) {
            int sum = people[l] + people[r];
            if (sum <= limit) {
                l++;
                r--;

            } else {
                r--;
            }
            boats++;
        }
        return boats;

    }

    // Print subarray with sum
    public int maxSubArray(int[] nums) {
        int csum = 0;
        int max = Integer.MIN_VALUE;
        int ost = 0;
        int oend = 0;
        int st = 0;
        int end = 0;
        for (int i = 0; i < nums.length; i++) {
            if (csum < 0) {
                csum = nums[i];
                st = i;
            } else {
                csum += nums[i];
                end = i;
            }
            if (csum > max) {
                max = csum;
                ost = st;
                oend = end;
            }
        }

        for (int i = ost; i <= oend; i++)
            System.out.print(nums[i] + " ");
        return max;
    }

    // Merge Intervals
    public static int[][] mergeIntervals(int Intervals[][]) {
        // write your code here
        if (Intervals.length == 0)
            return new int[0][0];
        Pair pairs[] = new Pair[Intervals.length];
        for (int i = 0; i < Intervals.length; i++) {
            pairs[i] = new Pair(Intervals[i][0], Intervals[i][1]);
        }
        Arrays.sort(pairs);

        Stack<Pair> st = new Stack<>();
        st.push(pairs[0]);

        for (int i = 1; i < pairs.length; i++) {
            Pair rem = pairs[i];
            if (rem.st < st.peek().end) {
                if (rem.end > st.peek().end) {
                    st.peek().end = rem.end;
                }
            } else {
                st.push(rem);
            }
        }

        int[][] res = new int[st.size()][2];
        Stack<Pair> ans = new Stack<>();
        while (st.size() > 0) {
            ans.push(st.pop());
        }
        int i = 0;
        while (ans.size() > 0) {
            res[i][0] = ans.peek().st;
            res[i][1] = ans.peek().end;
            i++;
            ans.pop();
        }
        return res;
    }

    static class Pair implements Comparable<Pair> {

        int st;
        int end;

        Pair(int st, int end) {
            this.st = st;
            this.end = end;
        }

        public int compareTo(Pair o) {
            return this.st - o.st;
        }

    }

    // Meeting room1-
    public static boolean canAttendMeetings(List<Interval> intervals) {
        // Write your code here
        if (intervals.size() == 0)
            return true;
        Collections.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));
        int lsp = intervals.get(0).start;
        int lep = intervals.get(0).end;
        for (int i = 1; i < intervals.size(); i++) {
            int sp = intervals.get(i).start;
            int ep = intervals.get(i).end;
            if (lep <= sp) {
                lsp = sp;
                lep = ep;
            } else
                return false;
        }
        return true;

    }

    // Meeting rooms2
    // https://www.lintcode.com/problem/919/
    public int minMeetingRooms(List<Interval> intervals) {
        // Write your code here
        int n = intervals.size();
        int[] start = new int[n];
        int end[] = new int[n];
        for (int i = 0; i < n; i++) {
            start[i] = intervals.get(i).start;
            end[i] = intervals.get(i).end;
        }

        Arrays.sort(start);
        Arrays.sort(end);

        int cmax = 0;
        int max_room = 0;
        int i = 0;
        int j = 0;
        while (i < n) {
            if (start[i] < end[j]) {
                cmax++;
                i++;
            } else {
                cmax--;
                j++;
            }
            max_room = Math.max(max_room, cmax);
        }
        return max_room;

    }

    public static class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    // leetcode239-https://leetcode.com/problems/sliding-window-maximum/
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] ngr = ngr(nums);

        int j = 0;
        int[] res = new int[nums.length - (k - 1)];
        for (int i = 0; i < res.length; i++) {
            if (j < i) {
                j = i;
            }
            while (ngr[j] < i + k) {
                j = ngr[j];
            }
            res[i] = nums[j];

        }
        return res;

    }

    static int[] ngr(int[] nums) {
        int res[] = new int[nums.length];
        Stack<Integer> st = new Stack<>();
        st.push(0);
        for (int i = 1; i < nums.length; i++) {
            while (st.size() > 0 && nums[i] > nums[st.peek()]) {
                res[st.pop()] = i;
            }
            st.push(i);
        }

        while (st.size() > 0) {
            res[st.pop()] = nums.length;
        }
        return res;
    }

    // Digit
    // Multiplier-https://practice.geeksforgeeks.org/problems/digit-multiplier3000/1
    static String getSmallest(Long N) {
        // code here
        String res = "";
        if (N == 1)
            return "1";
        for (int i = 9; i >= 2; i--) {
            while (N % i == 0) {
                res = i + res;
                N = N / i;
            }
        }

        return res.length() == 0 || N != 1 ? "-1" : res;
    }

    // First negative interger in k size window
    public long[] printFirstNegativeInteger(long A[], int N, int K) {
        int idx = N;
        long res[] = new long[N - K + 1];
        // mark -ve in last k size window
        for (int i = N - 1; i >= N - K; i--) {
            if (A[i] < 0)
                idx = i;
        }

        // travel from N-K to 0 and set in result
        for (int i = N - K; i >= 0; i--) {
            if (A[i] < 0)
                idx = i;
            if (idx < i + K) {
                res[i] = A[idx];
            }

        }

        return res;

    }

    // leetcode 9853:https://leetcode.com/problems/car-fleet/
    static class Pairr implements Comparable<Pairr> {
        int pos;
        int speed;

        Pairr(int pos, int speed) {
            this.pos = pos;
            this.speed = speed;
        }

        public int compareTo(Pairr o) {
            return this.pos - o.pos;
        }
    }

    public int carFleet(int target, int[] position, int[] speed) {
        PriorityQueue<Pairr> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < position.length; i++)
            pq.add(new Pairr(position[i], speed[i]));

        double maxt = 0;
        int fleets = 0;
        while (pq.size() > 0) {
            Pairr rem = pq.remove();
            double time = (target - rem.pos) / (rem.speed * 1.0);
            if (time > maxt) {
                fleets++;
                maxt = time;
            }
        }
        return fleets;

    }

    // leetcode-1191https://leetcode.com/problems/k-concatenation-maximum-sum/
    private int kadanes_1_2(int[] arr) {
        int n = arr.length;
        int[] narr = new int[2 * n];
        for (int i = 0; i < n; i++) {
            narr[i] = arr[i];
            narr[n + i] = arr[i];
        }
        return maxSubArray(narr);

    }

    public int kConcatenationMaxSum(int[] arr, int k) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        int kadanesum12 = kadanes_1_2(arr);
        if (sum < 0) {
            return kadanesum12;
        } else {
            return kadanesum12 + (sum * (k - 2));
        }
    }

    // Max sum in Sub Arrays
    // https://practice.geeksforgeeks.org/problems/max-sum-in-sub-arrays0824/1
    public static long pairWithMaxSum(long arr[], long N) {
        // Your code goes here
        long max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length - 1; i++) {
            max = Math.max(max, arr[i] + arr[i + 1]);
        }

        return max;
    }

    // Insert interval intersections
    // https://leetcode.com/problems/interval-list-intersections/
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        ArrayList<int[]> list = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < firstList.length && j < secondList.length) {
            int st = Math.max(firstList[i][0], secondList[j][0]);
            int end = Math.min(firstList[i][1], secondList[j][1]);
            if (st <= end) {
                int sublist[] = { st, end };
                list.add(sublist);
            }

            if (firstList[i][1] < secondList[j][1]) {
                i++;
            } else
                j++;
        }

        return list.toArray(new int[list.size()][2]);

    }

    // Insert interval
    // leetcode57->https://leetcode.com/problems/insert-interval/
    public int[][] insert(int[][] intervals, int[] newInterval) {
        boolean isused = false;
        int lsp = 0;
        int lep = 0;
        ArrayList<int[]> list = new ArrayList<>();
        if (intervals[0][0] < newInterval[0]) {
            lsp = intervals[0][0];
            lep = intervals[0][1];
        } else {
            isused = true;
            lsp = newInterval[0];
            lep = newInterval[1];
        }

        int i = 1;
        while (i < intervals.length) {
            int sp = intervals[i][0];
            int ep = intervals[i][1];
            if (isused == false && sp > newInterval[0]) {
                sp = newInterval[0];
                ep = newInterval[1];
            } else {
                i++;
            }

            if (lep < sp) {
                int[] sublist = { lsp, lep };
                list.add(sublist);
                lsp = ep;
                lep = ep;
            } else if (lep < ep)
                lep = ep;
            else {

            }
        }
        if (isused == false) {
            if (lep < newInterval[0]) {
                int sublist[] = { lsp, lep };
                int sublist2[] = { newInterval[0], newInterval[1] };

                list.add(sublist);
                list.add(sublist2);
            } else if (lep < newInterval[1]) {
                int sublist[] = { lsp, newInterval[1] };
                list.add(sublist);
            }

        } else {

        }
        return list.toArray(new int[list.size()][]);
    }

    // Min. Domino Rotations
    // https://leetcode.com/problems/minimum-domino-rotations-for-equal-row/
    public int minDominoRotations(int[] tops, int[] bottoms) {
        int val1 = tops[0];
        int val2 = bottoms[0];

        int count1 = 0; // Rotation for top as val1
        int count2 = 0; // Rotation for bottom as val1
        int count3 = 0; // Rotation for top as val2
        int count4 = 0; // Rotation for bottom as val2
        for (int i = 0; i < tops.length; i++) {
            if (count1 != Integer.MAX_VALUE) {
                if (tops[i] == val1) {
                    // nothing to do;
                } else if (bottoms[i] == val1) {
                    // rotation required
                    count1++;
                } else {
                    count1 = Integer.MAX_VALUE;
                }
            }

            if (count2 != Integer.MAX_VALUE) {
                if (tops[i] == val2) {
                    // nothing to do;
                } else if (bottoms[i] == val2) {
                    // rotation required
                    count2++;
                } else {
                    count2 = Integer.MAX_VALUE;
                }
            }

            if (count3 != Integer.MAX_VALUE) {
                if (bottoms[i] == val1) {
                    // nothing to do;
                } else if (tops[i] == val1) {
                    // rotation required
                    count3++;
                } else {
                    count3 = Integer.MAX_VALUE;
                }
            }

            if (count4 != Integer.MAX_VALUE) {
                if (bottoms[i] == val2) {
                    // nothing to do;
                } else if (tops[i] == val2) {
                    // rotation required
                    count4++;
                } else {
                    count4 = Integer.MAX_VALUE;
                }
            }
        }
        int res = Math.min(Math.min(count1, count2), Math.min(count3, count4));
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    // Leetcode 134->Gas station
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int gastank = 0;
        int gasshortage = 0;
        int start = 0;
        for (int i = 0; i < gas.length; i++) {
            gastank += gas[i] - cost[i];
            if (gastank < 0) {
                gasshortage += gastank;
                gastank = 0;
                start = i + 1;
            }
        }
        if (gastank + gasshortage >= 0)
            return start;
        else
            return -1;

    }

    // Leetcode->891. Sum of Subsequence Widths
    public int sumSubseqWidths(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        long sum = 0;
        int mod = 1000000007;
        long power[] = new long[n];
        power[0] = 1;
        for (int i = 1; i < n; i++)
            power[i] = (power[i - 1] * 2) % mod;

        for (int i = 0; i < n; i++)
            sum = (sum + (power[i] - power[n - i - 1]) * nums[i]) % mod;

        return (int) (sum % mod);
    }

    // 632. Smallest Range Covering Elements from K Lists
    static class pair implements Comparable<pair> {

        int val;
        int i;
        int j;

        pair(int val, int i, int j) {
            this.val = val;
            this.i = i;
            this.j = j;
        }

        public int compareTo(pair o) {
            return this.val - o.val;
        }
    }

    // public int[] smallestRange(List<List<Integer>> nums) {
    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<Pairrr> pq = new PriorityQueue<>();
        int minx = Integer.MAX_VALUE;
        int maxy = Integer.MIN_VALUE;
        int range = Integer.MAX_VALUE;
        for (int i = 0; i < nums.size(); i++) {
            int val = nums.get(i).get(0);
            minx = Math.min(minx, val);
            maxy = Math.max(maxy, val);
            pq.add(new Pairrr(val, i, 0));
        }
        int low = 0;
        int high = 0;

        while (pq.size() > 0) {
            Pairrr rem = pq.remove();
            int li = rem.li;
            int vi = rem.vi;
            int nval = rem.val;
            if (range > maxy - nval) {

                minx = nval;
                low = minx;
                maxy = maxy;
                high = maxy;
                range = maxy - nval;
            }

            if (vi == nums.get(li).size() - 1)
                break;

            pq.add(new Pairrr(nums.get(li).get(vi + 1), li, vi + 1));
            maxy = Math.max(maxy, nums.get(li).get(vi + 1));

        }

        int[] res = new int[2];
        res[0] = low;
        res[1] = high;
        return res;

    }

    static class Pairrr implements Comparable<Pairrr> {
        int val;
        int li;
        int vi;

        Pairrr(int val, int li, int vi) {
            this.val = val;
            this.li = li;
            this.vi = vi;
        }

        public int compareTo(Pairrr o) {
            return this.val - o.val;
        }

    }

    // 1094. Car Pooling
    public boolean carPooling(int[][] trips, int capacity) {
        int len = 0;
        // rows->no of trips
        // columns[0]->no of passenger
        // column[1]->starting of trip
        // column[2]->ending of trip

        int arr[] = new int[1001];
        for (int i = 0; i < trips.length; i++) {
            int start = trips[i][1];
            int end = trips[i][2];
            int pass = trips[i][0];
            arr[start] += pass;
            arr[end] -= pass;
            len = Math.max(len, end);
        }

        // make psum to calculate impact of passneger count
        int psum = 0;
        for (int i = 0; i <= len; i++) {
            psum += arr[i];
            arr[i] = psum;

            if (arr[i] > capacity)
                return false;
        }
        return true;

    }

    // 152. Maximum Product Subarray
    public int maxProduct(int[] nums) {
        int cprod = 1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                max = Math.max(max, nums[i]);
                cprod = 1;
            } else {
                cprod *= nums[i];
                max = Math.max(max, cprod);
            }

        }

        cprod = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] == 0) {
                max = Math.max(max, nums[i]);
                cprod = 1;
            } else {
                cprod *= nums[i];
                max = Math.max(max, cprod);
            }
        }
        return max;
    }

    // 209. Minimum Size Subarray Sum
    public int minSubArrayLen(int target, int[] nums) {
        int i = 0;
        int j = 0;
        int len = 0;
        int sum = 0;
        while (j < nums.length) {
            if (nums[i] >= target)
                return 1;
            sum += nums[j];
            while (sum >= target) {
                len = Math.min(len, j - i + 1);
                sum -= nums[i];
                i++;

            }
            j++;
        }
        return len;
    }

    // 643. Maximum Average Subarray I
    public double findMaxAverage(int[] nums, int k) {
        double ans = Integer.MIN_VALUE;
        double sum = 0;
        int i = 0;
        int j = 0;
        while (j < nums.length) {
            sum += nums[j];
            if (j - i + 1 < k)
                j++;
            else if (j - i + 1 == k) {
                ans = Math.max(ans, sum);
                sum -= nums[i];
                i++;
                j++;
            }
        }
        return ans / k;
    }

    // 1750. Minimum Length of String After Deleting Similar Ends
    public int minimumLength(String s) {
        int l = 0;
        int r = s.length() - 1;
        while (l < r && s.charAt(l) == s.charAt(r)) {
            char ch = s.charAt(l);
            // move left ahead with same characters
            while (l <= r && s.charAt(l) == ch)
                l++;

            // move right backward with same characters
            while (l <= r && s.charAt(r) == ch)
                r--;
        }

        return r - l + 1;
    }

    public List<Integer> findDuplicates(int[] nums) {
        List<Integer>res=new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            int idx=Math.abs(nums[i])-1;
            int val=nums[idx];
            if(val<0){
                res.add(idx+1);
            }
            else{
                nums[idx]*=-1;
            }
        }
        return res;
    }

    public static void main(String[] args) {

    }
}