import java.util.Arrays;

public class Bar implements Comparable<Bar> { // make data type comparable
    private final String n, c;
    private final int v;
    public Bar(String name, int value, String category) {
        if (name == null || value < 0 || category == null) {
            throw new IllegalArgumentException("Bar is null");
        }
        n = name;
        v = value;
        c = category;
    }
    public String getName() {
        return n;
    }
    public int getValue() {
        return v;
    }
    public String getCategory() {
        return c;
    }
    public int compareTo(Bar that) {
        if (that == null) {
            throw new NullPointerException("Bar is null");
        }
        else return v - that.v;
    }

    public static void main(String[] args) {
        Bar[] bars = new Bar[10];
        bars[0] = new Bar("Beijing",     22674, "East Asia");
        bars[1] = new Bar("Cairo",       19850, "Middle East");
        bars[2] = new Bar("Delhi",       27890, "South Asia");
        bars[3] = new Bar("Dhaka",       19633, "South Asia");
        bars[4] = new Bar("Mexico City", 21520, "Latin America");
        bars[5] = new Bar("Mumbai",      22120, "South Asia");
        bars[6] = new Bar("Osaka",       20409, "East Asia");
        bars[7] = new Bar("São Paulo",   21698, "Latin America");
        bars[8] = new Bar("Shanghai",    25779, "East Asia");
        bars[9] = new Bar("Tokyo",       38194, "East Asia");
        Arrays.sort(bars);

        System.out.println(bars[0].getName() + " " + bars[0].getValue() + " " + bars[0].getCategory());
        System.out.print(bars[0].compareTo(bars[1]));
    }
}
