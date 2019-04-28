package sixeightfiveseven.csn.zkpassword;

import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {
	Verifier verifier = new Verifier();
	Prover prover = new Prover(new BigInteger("020896"), verifier);
	BigInteger[] packet = prover.generatePacket();
	boolean accept = verifier.verify(packet);
	System.out.println(accept);
    }
}
