package model;

/**
 * Created by cbbjs on 10/2/2016.
 */
public enum UserType {
    U ("USER"),
    W ("WORKER"),
    M ("MANAGER"),
    A ("ADMIN");

    private final String type;

    UserType (String standing) {this.type = standing;}
    public String getStanding() {
        return type;
    }
    public String toString() {
        return type;
    }
}
