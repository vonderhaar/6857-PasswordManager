package sixeightfiveseven.csn.zkpassword;

import java.math.BigInteger;

public class Packet {

    private ECPoint point;
    private ECPoint publicKey;
    private ECPoint V;
    private BigInteger r;
    private BigInteger n;

    public Packet(ECPoint genPoint, ECPoint publicKey, BigInteger r, ECPoint V, BigInteger n) {
        this.point = genPoint;
        this.publicKey = publicKey;
        this.r = r;
        this.V = V;
        this.n = n;
    }

    public String toString() {
        return this.point.toString() + " " + this.publicKey.toString() + " " + this.r.toString() + " " + this.V.toString() + " " + this.n.toString();
    }

    public ECPoint getPoint() { return this.point; }

    public ECPoint getPublicKey() { return this.publicKey; }

    public ECPoint getV() { return this.V; }

    public BigInteger getR() { return this.r; }

    public BigInteger getN() { return this.n; }
}
