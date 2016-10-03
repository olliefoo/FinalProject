package model;

/**
 * Created by cbbjs on 10/2/2016.
 */
public enum userType {
    U ("USER"),
    W ("WORKER"),
    M ("MANAGER"),
    A ("ADMIN");

    private final String standing;

    userType (String standing) {this.standing = standing;}
    public String getStanding() {
        return standing;
    }
    public String toString() {
        return standing;
    }
}
