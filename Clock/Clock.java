public class Clock {
    private int htime, mtime;
    // Creates a clock whose initial time is h hours and m minutes.
    public Clock(int h, int m) {
        if (h < 0 || h > 23 || m < 0 || m > 59)
            throw new IllegalArgumentException();
        htime = h;
        mtime = m;
    }
    // Creates a clock whose initial time is specified as a string, using the format HH:MM.
    public Clock(String s) {
        int length = s.length();
        int hour = Integer.parseInt(s.substring(0, 2));
        int min = Integer.parseInt(s.substring(3, 5));
        if (!Character.isDigit(s.charAt(0)) || !Character.isDigit(s.charAt(1)) || !Character.isDigit(s.charAt(3)) || !Character.isDigit(s.charAt(4))) {
            throw new IllegalArgumentException();
        }
        if (s.charAt(2) != ':' || length != 5 || hour < 0 || hour > 23 || min < 0 || min > 59) {
            throw new IllegalArgumentException();
        }
        htime = hour;
        mtime = min;
    }
    // Returns a string representation of this clock, using the format HH:MM.
    public String toString() {
        return String.format("%02d:%02d", htime, mtime);
    }
    // Is the time on this clock earlier than the time on that one?
    public boolean isEarlierThan(Clock b) {
        return htime < b.htime || (htime == b.htime && mtime < b.mtime);
    }
    // Adds 1 minute to the time on this clock.
    public void tic() {
        if (htime == 23 && mtime == 59) {
            htime = 00;
            mtime = 00;
        }
        else if (mtime == 59) {
            htime = htime + 1;
            mtime = 00;
        }
        else {
            mtime = mtime + 1;
        }
    }
    // Adds Δ minutes to the time on this clock.
    public void toc(int delta) {
        if (delta < 0)
            throw new IllegalArgumentException();
        int plusHour = (mtime + delta) / 60;
        mtime = (mtime + delta) % 60;
        if (htime + plusHour > 23) htime = (htime + plusHour) % 24;
        else htime = htime + plusHour;
    }
    public static void main(String[] args) {
        int h = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);
        String s = args[2];
        Clock a = new Clock(h, m);
        Clock b = new Clock(s);
        System.out.println(a.toString());
        System.out.println(a.isEarlierThan(new Clock(23, 30)));
        a.tic();
        System.out.println(a);
        b.toc(20);
        System.out.println(b);
    }
}
