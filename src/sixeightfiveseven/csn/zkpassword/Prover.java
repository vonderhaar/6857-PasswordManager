package sixeightfiveseven.csn.zkpassword;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Prover {

    private BigInteger privateKey;
    private ECPoint publicKey;
    private BigInteger userID;
    private BigInteger littleV;
    private ECPoint bigV;
    private BigInteger littleH;
    private BigInteger littleR;
    private Verifier verifier;

    // define generator point and big prime n
    private BigInteger xCoord = new BigInteger("55066263022277343669578718895168534326250603453777594175500187360389116729240");
    private BigInteger yCoord = new BigInteger("32670510020758816978083085130507043184471273380659243275938904335757337482424");
    private ECPoint genPoint = new ECPoint(xCoord, yCoord);
    private BigInteger n = new BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEBAAEDCE6AF48A03BBFD25E8CD0364141", 16);

    public Prover(BigInteger userID, Verifier verifier) {
        this.userID = userID;
        this.verifier = verifier;
        chooseKeys();
        computeV();
        computeR();
    }

    public Packet getPacket () {
        return new Packet(this.genPoint, this.publicKey, this.littleR, this.bigV, this.n);
    }

    private void chooseKeys() {
        // choose private key between 1 and n-1
        this.privateKey = chooseRandom(this.n.subtract(BigInteger.ONE));
        this.publicKey = this.genPoint.multiply(this.privateKey);
    }

    private void computeV() {
        // choose v between 1 and n-1
        this.littleV = chooseRandom(this.n.subtract(BigInteger.ONE));
        this.bigV = this.genPoint.multiply(this.littleV);
    }

    private void computeR() {
        this.littleR = this.littleV.subtract(this.privateKey.multiply(verifier.getC())).mod(this.n);
    }

    private BigInteger chooseRandom(BigInteger max) {
        SecureRandom random = new SecureRandom();
        return new BigInteger(max.toString(2).length(), random).mod(max).add(BigInteger.ONE);
    }
}
