package gr.aueb.cf4.orderapp.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public enum Size {
    SMALL("SMALL"), MEDIUM("MEDIUM"), LARGE("LARGE"), XL("XL"), ONE_SIZE("ONE_SIZE"), SIZE_100("SIZE_100"),
    SIZE_110("SIZE_110"), SIZE_120("SIZE_120"), SIZE_130("SIZE_130"), SIZE_140("SIZE_140"), SIZE_145("SIZE_145"),
    SIZE_150("SIZE_150"), SIZE_155("SIZE_155"), SIZE_160("SIZE_160"), SIZE_165("SIZE_165"), SIZE_170("SIZE_170"),
    SIZE_175("SIZE_175"), SIZE_180("SIZE_180"), SIZE_185("SIZE_185"), SIZE_190("SIZE_190"), SIZE_195("SIZE_195"),
    SIZE_200("SIZE_200"), OZ_6("OZ_6"), OZ_8("OZ_8"), OZ_10("OZ_10"), OZ_12("OZ_12"), OZ_14("OZ_14");

    @Column(name = "size")
    @Enumerated(EnumType.STRING)
    private final String displayName;

    Size(String displayName) {
        this.displayName = displayName;
    }

    @JsonValue
    public String getDisplayName() {
        return displayName;
    }

    @JsonCreator
    public static Size fromDisplayName(String displayName) {
        for (Size size : values()) {
            if (size.displayName.equalsIgnoreCase(displayName)) {
                return size;
            }
        }
        throw new IllegalArgumentException("Unknown size: " + displayName);
    }
}
