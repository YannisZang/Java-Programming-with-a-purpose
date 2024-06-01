
public class Huntingtons {
    public static int maxRepeats(String dna) {
        int n = dna.length();
        int count = 0;
        int max = 0;
        for (int i = 0; i < n - 2; i++) {
            max = Math.max(max, count);
            String cag = dna.substring(i, i+3);
            if (cag.equals("CAG")) {
                count++;
                i+=2; // switch to next 3 char substring;
                max = Math.max(max, count);
            }
            else count = 0;
        }
        return max;
    }
    public static String removeWhitespace(String s) {
        s = s.replace(" ", "");
        s = s.replace("\n", "");
        s = s.replace("\t", "");
        return s;
    }
    public static String diagnose(int maxRepeats) {
        if (maxRepeats <= 9) return "not human";
        else if (maxRepeats <= 35) return "normal";
        else if (maxRepeats <= 39) return "high risk";
        else if (maxRepeats <= 180) return "Huntington's";
        else return "not human";
    }
    public static void main(String[] args) {
        In s = new In(args[0]);
        String dna = removeWhitespace(s.readAll());
        int max = maxRepeats(dna);
        String diagnose = diagnose(maxRepeats(dna));
        System.out.println("max repeats = " + max);
        System.out.println(diagnose);
    }
}
