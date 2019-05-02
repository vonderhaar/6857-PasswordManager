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

    public Packet(String genPoint, String publicKey, String r, String V, String n) {
        this.r = new BigInteger(r);
        this.n = new BigInteger(n);

        this.point = makePointFromString(genPoint);
        this.publicKey = makePointFromString(publicKey);
        this.V = makePointFromString(V);
    }

    public String toString() {
        return this.point.toString() + " " + this.publicKey.toString() + " " + this.r.toString() + " " + this.V.toString() + " " + this.n.toString();
    }

    public static ECPoint makePointFromString(String s) {
        s = s.replace("(", "");
        s = s.replace(")", "");
        s = s.replace(",", "");
        String ary[] = s.split("\\s+");
        return new ECPoint(new BigInteger(ary[0]), new BigInteger(ary[1]));
    }

    public ECPoint getPoint() { return this.point; }

    public ECPoint getPublicKey() { return this.publicKey; }

    public ECPoint getV() { return this.V; }

    public BigInteger getR() { return this.r; }

    public BigInteger getN() { return this.n; }
}
