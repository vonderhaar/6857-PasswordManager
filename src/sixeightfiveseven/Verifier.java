package sixeightfiveseven;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.io.PrintStream;

public class Verifier {

    private final int t = 160;
    private final BigInteger p = new BigInteger("E0A67598CD1B763BC98C8ABB333E5DDA0CD3AA0E5E1FB5BA8A7B4EABC10BA338FAE06DD4B90FDA70D7CF0CB0C638BE3341BEC0AF8A7330A3307DED2299A0EE606DF035177A239C34A912C202AA5F83B9C4A7CF0235B5316BFC6EFB9A248411258B30B839AF172440F32563056CB67A861158DDD90E6A894C72A5BBEF9E286C6B", 16);


    public Verifier() {

    }

    public  boolean verify(Packet packet) throws IOException {
        ECPoint genPoint = packet.getPoint();
        ECPoint publicKey = packet.getPublicKey();
        ECPoint V = packet.getV();
        BigInteger r = packet.getR();
        BigInteger n = packet.getN();
        BigInteger c = packet.getC();

        // TODO: check point is on the elliptical curve ??
        BigInteger littleH = p.divide(n);
        ECPoint point = publicKey.multiply(littleH);

        ECPoint testV = genPoint.multiply(r).add(publicKey.multiply(c));

        PrintStream out = new PrintStream(new FileOutputStream("./src/password_server/output.txt"));
        out.println(V.getX());
        out.println(testV.getX());
        out.println(V.getY());
        out.println(testV.getY());

//        System.out.println(V.getX());
//        System.out.println(testV.getX());
//        System.out.println(V.getY());
//        System.out.println(testV.getY());

        return V.getX().equals(testV.getX()) && V.getY().equals(testV.getY());
    }
}
