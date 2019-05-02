package sixeightfiveseven.csn.zkpassword;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.lang.Math;

public class Verifier {

    private final BigInteger c;
    private final int t = 160;
    private final BigInteger p = new BigInteger("E0A67598CD1B763BC98C8ABB333E5DDA0CD3AA0E5E1FB5BA8A7B4EABC10BA338FAE06DD4B90FDA70D7CF0CB0C638BE3341BEC0AF8A7330A3307DED2299A0EE606DF035177A239C34A912C202AA5F83B9C4A7CF0235B5316BFC6EFB9A248411258B30B839AF172440F32563056CB67A861158DDD90E6A894C72A5BBEF9E286C6B", 16);


    public Verifier() {
        c = chooseRandom(t);
    }

    public  BigInteger getC() {
        return c;
    }

    public  boolean verify(Packet packet) {
        ECPoint genPoint = packet.getPoint();
        ECPoint publicKey = packet.getPublicKey();
        ECPoint V = packet.getV();
        BigInteger r = packet.getR();
        BigInteger n = packet.getN();

        // TODO: check point is on the elliptical curve ??
        BigInteger littleH = p.divide(n);
        ECPoint point = publicKey.multiply(littleH);

        ECPoint testV = genPoint.multiply(r).add(publicKey.multiply(this.c));

        System.out.println(V.getX());
        System.out.println(testV.getX());
        System.out.println(V.getY());
        System.out.println(testV.getY());

        return V.getX().equals(testV.getX()) && V.getY().equals(testV.getY());
    }

    private BigInteger chooseRandom(int t) {
        SecureRandom random = new SecureRandom();
        return new BigInteger(""+random.nextInt((int)Math.pow(2, t)-2)).add(BigInteger.ONE);
    }
}
