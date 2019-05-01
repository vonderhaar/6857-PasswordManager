package sixeightfiveseven.csn.zkpassword;

import java.math.BigInteger;

public class Verifier {
    //large prime
    private final BigInteger p = new BigInteger("E0A67598CD1B763BC98C8ABB333E5DDA0CD3AA0E5E1FB5BA8A7B4EABC10BA338FAE06DD4B90FDA70D7CF0CB0C638BE3341BEC0AF8A7330A3307DED2299A0EE606DF035177A239C34A912C202AA5F83B9C4A7CF0235B5316BFC6EFB9A248411258B30B839AF172440F32563056CB67A861158DDD90E6A894C72A5BBEF9E286C6B", 16);
    //large prime divisor of p-1
    private final BigInteger q = new BigInteger("E950511EAB424B9A19A2AEB4E159B7844C589C4F", 16);
    //
    private final BigInteger g = new BigInteger("D29D5121B0423C2769AB21843E5A3240FF19CACC792264E3BB6BE4F78EDD1B15C4DFF7F1D905431F0AB16790E1F773B5CE01C804E509066A9919F5195F4ABC58189FD9FF987389CB5BEDF21B4DAB4F8B76A055FFE2770988FE2EC2DE11AD92219F0B351869AC24DA3D7BA87011A701CE8EE7BFE49486ED4527B7186CA4610A75", 16);
    //

    public Verifier() {

    }


    public  BigInteger getP() {
        return p;
    }

    public  BigInteger getQ() {
        return q;
    }

    public  BigInteger getG() {
        return g;
    }

    public  boolean verify(BigInteger[] packet) {
        BigInteger userID = packet[0];
        BigInteger publicKey = packet[1];
        BigInteger bigV = packet[2];
        BigInteger littleR = packet[3];
        BigInteger littleH = packet[4];
        System.out.println(verifyV(littleR, publicKey, littleH, bigV));
        boolean correctKey =
                        (publicKey.compareTo(BigInteger.ZERO) == 1) &&
                        (publicKey.compareTo(p) == -1) &&
                        (publicKey.modPow(q, p).compareTo(BigInteger.ONE) == 0);
        boolean correctV = verifyV(littleR, publicKey, littleH, bigV);
        System.out.println("Key true: " + correctKey);
        System.out.println("V true " + correctV);
        return correctKey && correctV;
    }

    private boolean verifyV(BigInteger littleR, BigInteger publicKey, BigInteger littleH, BigInteger bigV) {
/*        BigInteger rModPowG = g.modPow(littleR, g);
        BigInteger hValue = littleH;
        BigInteger currentValue = rModPowG;
        System.out.println(hValue);
        while (hValue.compareTo(BigInteger.ZERO) == 1) {
            currentValue = currentValue.multiply(publicKey).mod(littleH);
            hValue.subtract(BigInteger.ONE);
        }
        return currentValue.compareTo(bigV) == 0;*/
        return (g.modPow(littleR, g).multiply(publicKey.modPow(littleH, p)).compareTo(bigV) == 0);
    }
}
