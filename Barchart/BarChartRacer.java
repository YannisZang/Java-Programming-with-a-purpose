import java.util.Arrays;

public class BarChartRacer {
    public static void main(String[] args) {
        // read file
        String filename = args[0];
        int k = Integer.parseInt(args[1]);
        In input = new In(filename);
        String title = input.readLine();
        String xAxis = input.readLine();
        String source = input.readLine();
        BarChart chart = new BarChart(title, xAxis, source);

        StdDraw.setCanvasSize(1000, 700);
        StdDraw.enableDoubleBuffering();

        while (input.hasNextLine()) {
            input.readLine();
            int n = Integer.parseInt(input.readLine());
            Bar[] bar = new Bar[n];
            String year = null;
            for (int i = 0; i < n; i++) {
                String[] line = input.readLine().split(",");

                year = line[0];
                String name = line[1];
                String value = line[3];
                String category = line[4];

                bar[i] = new Bar(name, Integer.parseInt(value), category);
            }
            Arrays.sort(bar);

            chart.reset();
            StdDraw.clear();
            for (int j = n-1; j >= n - k ; j--) {
                chart.add(bar[j].getName(), bar[j].getValue(), bar[j].getCategory());
            }

            chart.setCaption(year);
            chart.draw();
            StdDraw.show();
            StdDraw.pause(1);
        }
    }
}
