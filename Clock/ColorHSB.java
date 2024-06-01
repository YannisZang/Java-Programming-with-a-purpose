
public class ColorHSB {
    private final int hue, saturation, brightness;
    // Creates a color with hue h, saturation s, and brightness b.
    public ColorHSB(int h, int s, int b) {
        if (h < 0 || h > 359 || s < 0 || s > 100 || b < 0 || b > 100)
            throw new IllegalArgumentException();
        hue = h;
        saturation = s;
        brightness = b;
    }
    // Returns a string representation of this color, using the format (h, s, b).
    public String toString() {
        return "(" + hue + ", " + saturation + ", " +brightness + ")";
    }
    // Is this color a shade of gray?
    public boolean isGrayscale() {
        return saturation == 0 || brightness == 0;
    }
    // Returns the squared distance between the two colors.
    public int distanceSquaredTo(ColorHSB b) {
        if (b == null) throw new IllegalArgumentException();
        int dh = Math.abs(hue - b.hue);
        int ds = Math.abs(saturation - b.saturation);
        int db = Math.abs(brightness - b.brightness);
        return Math.min(dh*dh, (360-dh)*(360-dh)) + ds * ds + db * db;
    }
    public static void main(String[] args) {
        int h = Integer.parseInt(args[0]);
        int s = Integer.parseInt(args[1]);
        int b = Integer.parseInt(args[2]);
        ColorHSB a = new ColorHSB(h, s, b);
        int minDistance = Integer.MAX_VALUE;
        String colorName = null;
        ColorHSB endColor = null;
        while (!StdIn.isEmpty()) {
            String preColorName = StdIn.readString();
            int h1 = Integer.parseInt(StdIn.readString());
            int s1 = Integer.parseInt(StdIn.readString());
            int b1 = Integer.parseInt(StdIn.readString());
            ColorHSB color = new ColorHSB(h1, s1, b1);
            int distance = a.distanceSquaredTo(color);
            if (distance <= minDistance) {
                minDistance = distance; // keep minimum distance
                colorName = preColorName; // keep color name of nearest
                endColor = color; // keep color object of nearest
            }
        }
    System.out.println(colorName + " " + endColor);
    }
}
