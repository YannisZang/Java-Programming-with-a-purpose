import java.awt.Color;

public class KernelFilter {
    // round and clamp red, green and blue
    private static int roundClamping(double a) {
        int r = (int) Math.round(a);
        if (a > 255) r = 255;
        if (a < 0) r = 0;
        return r;
    }
    // boundary conditions, how to treat neighboring pixels when kernel in boundary
    private static int boundary(int a, int b, int n)
    { // b is length or width of kennel
        if (a + n < 0) return a + b + n;
        else if (a + n >= b) return a + n - b;
        else return a + n;
    }
    // define kernel
    private static Picture kernel(Picture pic, double[][] k, double a) {
        int col = pic.width();
        int row = pic.height();
        Picture target = new Picture(col, row);
        int n = k[0].length;
        int m = k.length;
        int nn = n / 2, mm = m / 2;
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                double r = 0, g = 0, b = 0;
                for (int kCol = -nn; kCol <= nn; kCol++) {
                    int c1 = boundary(i, col, kCol);
                    for (int kRow = -mm; kRow <= mm; kRow++) {
                        int r1 = boundary(j, row, kRow);
                        r = r + k[kRow + mm][kCol + nn] * pic.get(c1, r1).getRed() * a;
                        g = g + k[kRow+mm][kCol+nn] * pic.get(c1, r1).getGreen() * a;
                        b = b + k[kRow+mm][kCol+nn] * pic.get(c1, r1).getBlue() * a;
                    }
                }
                int red = roundClamping(r);
                int green = roundClamping(g);
                int blue = roundClamping(b);
                Color c = new Color(red, green, blue);
                target.set(i, j, c);
            }
        }
        return target;
    }
    public static Picture identity(Picture picture) {
        double a = 1.0;
        double[][] k = { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } };
        return kernel(picture, k, a);
    }
    public static Picture gaussian(Picture picture) {
        double a = 1.0 / 16.0;
        double[][] k = { { 1, 2, 1 }, { 2, 4, 2 }, { 1, 2, 1 } };
        return kernel(picture, k, a);
    }
    public static Picture sharpen(Picture picture) {
        double a = 1.0;
        double[][] k = { { 0, -1, 0 }, { -1, 5, -1 }, { 0, -1, 0 } };
        return kernel(picture, k, a);
    }
    public static Picture laplacian(Picture picture) {
        double a = 1.0;
        double[][] k = { { -1, -1, -1 }, { -1, 8, -1 }, { -1, -1, -1 } };
        return kernel(picture, k, a);
    }
    public static Picture emboss(Picture picture) {
        double a = 1.0;
        double[][] k = { { -2, -1, 0 }, { -1, 1, 1 }, { 0, 1, 2 } };
        return kernel(picture, k, a);
    }
    public static Picture motionBlur(Picture picture) {
        double a = 1.0 / 9.0;
        double[][] k = new double[9][9];
        for (int p = 0; p < 9; p++) {
            for (int j = 0; j < 9; j++) {
                if (p == j) k[p][j] = 1;
                else k[p][j] = 0;
            }
        }
        return kernel(picture, k, a);
    }
    public static void main(String[] args) {
        String filename = args[0];
        Picture picture = new Picture(filename);
        picture = identity(picture);
        picture = gaussian(picture);
        picture = sharpen(picture);
        picture = laplacian(picture);
        picture = emboss(picture);
        picture = motionBlur(picture);
        picture.show();
    }
}
