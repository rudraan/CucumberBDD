package awesomecucumber.constants;

public enum EndPoint {
    STORE("/store"),
    ACCOUNT("/account");


    public final String url;

    EndPoint(String url) {
        this.url = url;
    }
}
