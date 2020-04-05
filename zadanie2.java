// Mikołaj Stegliński 6577 D
// Zadanie 2
import java.util.Arrays;
class zadanie2{
    int min, max;
    public zadanie2(int minimum, int maximum) {
        min = minimum;
        max = maximum;
    }

    public int rand(){
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }
}
class MergeSort implements Runnable{
    int[] arr;
    int l,r;
    int lvl;

    void merge(int tab[], int l, int mid, int r) {

        int n1 = mid - l + 1;
        int n2 = r - mid;

        int L[] = new int [n1];
        int R[] = new int [n2];

                    // Two-table division
        for (int i=0; i<n1; ++i)
            L[i] = tab[l + i];
        for (int j=0; j<n2; ++j)
            R[j] = tab[mid + 1+ j];
        System.out.println("Joining the next table");

        System.out.println(Arrays.toString(L));
        System.out.println(Arrays.toString(R));
        System.out.println("##### ##### #####");

        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                tab[k] = L[i];
                i++;
            }
            else {
                tab[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            tab[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            tab[k] = R[j];
            j++;
            k++;
        }
    }
    static void insertSort(int a[], int left, int right) {
        int j;
        for (int p = left; p < right; p++) {
            int tmp = a[p];
            for(j = p; j > 0 && tmp < a[j - 1]; j--) {
                a[j] = a[j-1];
            }
            a[j] = tmp;
        }
    }

    public MergeSort(int tab[], int launch, int end, int level) {
        lvl = level;
        lvl ++;
        arr = tab;
        l = launch;
        r = end;

    }
    @Override
    public void run() {
        if(lvl < 5) {         //sorting to level 5
            if (l < r) {
                try {
                    int m = (l + r) / 2;
                    System.out.println("level "+lvl);

                            // recurring
                    Thread ms1 = new Thread(new MergeSort(arr, l, m,lvl));
                    Thread ms2 = new Thread(new MergeSort(arr, m + 1, r,lvl));

                    ms1.start();
                    ms2.start();
                    ms1.join();
                    ms2.join();

                    merge(arr, l, m, r);
                }
                catch(InterruptedException ex){}

            }
        }
        else {               //below level five
            System.out.println("insert here");
            System.out.println("level "+lvl);
            insertSort(arr,l,r);
        }
    }
        public static void main(String args[]) throws InterruptedException {
        zadanie2 ri = new zadanie2(10,99);      // maximum and minimum range
        int tab[] =new int[12];             // table size
        for (int i = 0; i < tab.length; i++)  tab[i] = ri.rand();

        Thread  ms = new Thread(new MergeSort(tab, 0, tab.length-1,0));

        System.out.println("##### START #####");
        System.out.println(Arrays.toString(tab));
        ms.start();
        ms.join();
        System.out.println(Arrays.toString(tab));
        System.out.println("##### STOP #####");
    }
}