package ies.eed.practicajunit.balls;

public enum BallColor {
    RED(26),
    GREEN(125),
    BLUE(76);
    protected final int centery;

    BallColor(int centery) {
        this.centery = centery;
    }

    public int getCenter() {
        return this.centery;
    }
}
