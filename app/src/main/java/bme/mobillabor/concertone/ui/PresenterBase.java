package bme.mobillabor.concertone.ui;

public abstract class PresenterBase<S extends IScreen> {
    protected S screen;

    public void attachScreen(S screen) {
        this.screen = screen;
    }

    public void detachScreen() {
        this.screen = null;
    }
}
