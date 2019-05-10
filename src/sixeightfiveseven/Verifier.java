package sixeightfiveseven;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.io.PrintStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Verifier {

    private final int t = 160;
    private final BigInteger p = new BigInteger("E0A67598CD1B763BC98C8ABB333E5DDA0CD3AA0E5E1FB5BA8A7B4EABC10BA338FAE06DD4B90FDA70D7CF0CB0C638BE3341BEC0AF8A7330A3307DED2299A0EE606DF035177A239C34A912C202AA5F83B9C4A7CF0235B5316BFC6EFB9A248411258B30B839AF172440F32563056CB67A861158DDD90E6A894C72A5BBEF9E286C6B", 16);


    public Verifier() {

    }

    public boolean verify(ECPoint genPoint, ECPoint publicKey, ECPoint V, BigInteger r, BigInteger n) throws IOException, NoSuchAlgorithmException {
        BigInteger c = makeChallenge(genPoint, publicKey, V);

        // TODO: check point is on the elliptical curve ??
        BigInteger littleH = p.divide(n);
        ECPoint point = publicKey.multiply(littleH);

        ECPoint testV = genPoint.multiply(r).add(publicKey.multiply(c));

        boolean output = V.getX().equals(testV.getX()) && V.getY().equals(testV.getY());

        PrintStream out = new PrintStream(new FileOutputStream("/home/sylvielee/Desktop/out.txt"));

        out.println(output? "y" : "n");

        out.close();


        return output;
    }

    private static BigInteger makeChallenge(ECPoint genPoint, ECPoint publicKey, ECPoint V) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        List<Byte> toHashList = new ArrayList<>();
        addByteArray(toHashList, genPoint.getX().toByteArray());
        addByteArray(toHashList, genPoint.getY().toByteArray());
        addByteArray(toHashList, V.getX().toByteArray());
        addByteArray(toHashList, V.getY().toByteArray());
        addByteArray(toHashList, publicKey.getX().toByteArray());
        addByteArray(toHashList, publicKey.getY().toByteArray());
//        addByteArray(toHashList, userID.toByteArray());

        byte[] toHash = new byte[toHashList.size()];
        for(int i = 0; i < toHashList.size(); i++) {
            toHash[i] = toHashList.get(i).byteValue();
        }
        BigInteger c = new BigInteger(digest.digest(toHash));
        return c.abs();
    }


    private static void addByteArray(List<Byte> aryList, byte[] ary) {
        for (byte b : ary) {
            aryList.add(b);
        }
    }
}
