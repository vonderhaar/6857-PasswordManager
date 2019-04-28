package sixeightfiveseven.csn.zkpassword;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Prover {

    private BigInteger privateKey;
    //
    private BigInteger publicKey;
    //
    private BigInteger userID;
    //
    private BigInteger littleV;
    //
    private BigInteger bigV;
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


    public BigInteger[] generatePacket() {
        return new BigInteger[]{userID, publicKey, bigV, littleR, littleH};
    }

    private void chooseKeys() {
        this.privateKey = chooseRandom(new BigInteger("0"), verifier.getQ().subtract(new BigInteger("1")));
        this.publicKey = verifier.getG().modPow(privateKey, verifier.getP());
    }


    private BigInteger computeV() {
        this.littleV = chooseRandom(new BigInteger("0"), verifier.getQ().subtract(new BigInteger("1")));
        this.bigV = verifier.getG().modPow(this.littleV, verifier.getP());
        return this.bigV;
    }

    private BigInteger computeR() throws NoSuchAlgorithmException {
        BigInteger g = verifier.getG();
        BigInteger valToHash = g.add(g.modPow(littleV, g)).add(g.modPow(privateKey, g)).add(userID);
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        littleH = new BigInteger(digest.digest(valToHash.toByteArray()));
        littleR = littleV.subtract(privateKey.modPow(littleH, verifier.getQ()));
        return littleR;
    }

    private BigInteger chooseRandom(BigInteger min, BigInteger max) {
        return new BigInteger("1");
    }
}
