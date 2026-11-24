package analysis.app.AM5298_5224_5206.domain;

public enum Role {
	DEVELOPER("Developer");

    private final String value;

    private Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
