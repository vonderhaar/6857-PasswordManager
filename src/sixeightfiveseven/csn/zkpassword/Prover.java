package sixeightfiveseven.csn.zkpassword;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Prover {

    private BigInteger privateKey = new BigInteger("A665A45920422F9D417E4867EFDC4FB8A04A1F3FFF1FA07E998E86F7F7A27AE3", 16);
    //
    private ECPoint publicKey;
    //
    private BigInteger xCoord = new BigInteger("55066263022277343669578718895168534326250603453777594175500187360389116729240");
    private BigInteger yCoord = new BigInteger("32670510020758816978083085130507043184471273380659243275938904335757337482424");

    private ECPoint genPoint = new ECPoint(xCoord, yCoord);

    private BigInteger n = new BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEBAAEDCE6AF48A03BBFD25E8CD0364141", 16);

    private BigInteger userID;
    //
    private BigInteger littleV;
    //
    private ECPoint bigV;
    //
    private BigInteger littleH;
    //
    private BigInteger littleR;
    //
    private Verifier verifier;

    public Prover(BigInteger userID, Verifier verifier) {
        this.userID = userID;
        this.verifier = verifier;
        chooseKeys();
        computeV();
        try {
            computeR();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


    public ECPoint getG() {
        return this.genPoint;
    }

    public BigInteger getR() {
        return this.littleR;
    }

    public ECPoint getV() {
        return this.bigV;
    }

    public ECPoint getPublicKey() {
        return this.publicKey;
    }

    public BigInteger getN() { return this.n;  }

    private void chooseKeys() {
        this.privateKey = chooseRandom(this.n.subtract(BigInteger.ONE));
        this.publicKey = this.genPoint.multiply(this.privateKey);
    }


    private void computeV() {
        this.littleV = chooseRandom(this.n.subtract(BigInteger.ONE));
//        this.littleV = new BigInteger("d90688c575782cb4970514c7623fee2d6045e5d3aa48507beb3c9804453718d2", 16);
        this.bigV = this.genPoint.multiply(this.littleV);
    }

    private void computeR() throws NoSuchAlgorithmException {
        this.littleR = this.littleV.subtract(this.privateKey.multiply(verifier.getC())).mod(this.n);


//        BigInteger valToHash = g.add(g.modPow(littleV, g)).add(g.modPow(privateKey, g)).add(userID);
//        MessageDigest digest = MessageDigest.getInstance("SHA-256");
//        littleH = new BigInteger(digest.digest(valToHash.toByteArray()));
//        System.out.println(littleH);
//        littleH = BigInteger.TEN;//(littleH.compareTo(BigInteger.ZERO) == -1) ? littleH.multiply(BigInteger.valueOf(-1)) : littleH;
//        System.out.println(littleH);
//        littleR = littleV.subtract(privateKey.modPow(littleH, verifier.getQ()));
    }

    private BigInteger chooseRandom(BigInteger max) {
        SecureRandom random = new SecureRandom();
        return new BigInteger(max.toString(2).length(), random).mod(n.subtract(BigInteger.ONE)).add(BigInteger.ONE);

    }
}
