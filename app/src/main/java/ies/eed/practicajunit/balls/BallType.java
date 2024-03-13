package ies.eed.practicajunit.balls;

public enum BallType {
    EXTRABIG(48, 40, 1),
    BIG(32, 26, 52),
    MEDIUM(16, 14, 86),
    LITTLE(8, 7, 106);
    protected final int width;
    protected final int heigth;
    protected final int starx;

    BallType(int width, int height, int startx) {
        this.width = width;
        this.heigth = height;
        this.starx = startx;
    }

    public int getWidth() {
        return width;
    }

    public int getHeigth() {
        return heigth;
    }

    public int getStartx() {
        return starx;
    }
}
