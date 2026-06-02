public class SegmentProject {

    int[] tree;
    int n;

    SegmentProject(int[] arr) {
        n = arr.length;
        tree = new int[4 * n];
        build(arr, 0, 0, n - 1);
    }

    void build(int[] arr, int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;

            build(arr, 2 * node + 1, start, mid);
            build(arr, 2 * node + 2, mid + 1, end);

            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }

    int query(int node, int start, int end, int l, int r) {

        if (r < start || end < l)
            return 0;

        if (l <= start && end <= r)
            return tree[node];

        int mid = (start + end) / 2;

        int leftSum = query(2 * node + 1, start, mid, l, r);
        int rightSum = query(2 * node + 2, mid + 1, end, l, r);

        return leftSum + rightSum;
    }

    void update(int node, int start, int end, int idx, int value) {

        if (start == end) {
            tree[node] = value;
            return;
        }

        int mid = (start + end) / 2;

        if (idx <= mid)
            update(2 * node + 1, start, mid, idx, value);
        else
            update(2 * node + 2, mid + 1, end, idx, value);

        tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
    }

    public static void main(String[] args) {

        int[] arr = {5, 8, 6, 3, 2, 7, 4, 6};

        SegmentProject st = new SegmentProject(arr);

        System.out.println("=== SEGMENT TREE IMPLEMENTATION ===");

        System.out.print("\nInput Array:\n");
        for (int x : arr)
            System.out.print(x + " ");

        System.out.println("\n\nQuery 1:");
        System.out.println("Sum of elements from index 2 to 5");

        int sum1 = st.query(0, 0, arr.length - 1, 2, 5);
        System.out.println("Output: " + sum1);

        System.out.println("\nQuery 2:");
        System.out.println("Sum of elements from index 0 to 7");

        int sum2 = st.query(0, 0, arr.length - 1, 0, 7);
        System.out.println("Output: " + sum2);

        System.out.println("\nUpdating index 3:");
        System.out.println("3 -> 10");

        arr[3] = 10;
        st.update(0, 0, arr.length - 1, 3, 10);

        System.out.println("\nQuery after Update:");
        System.out.println("Sum of elements from index 2 to 5");

        int sum3 = st.query(0, 0, arr.length - 1, 2, 5);
        System.out.println("Output: " + sum3);

        System.out.println("\nSegment Tree Updated Successfully.");
    }
}