package sixeightfiveseven.csn.zkpassword;

import java.math.BigInteger;
import java.lang.Math;

public class Main {

    public static void main(String[] args) {
		Verifier verifier = new Verifier();
		Prover prover = new Prover(new BigInteger("020896"), verifier);
		boolean accept = verifier.verify(prover.getPacket());
		System.out.println("Accepted: " + accept);
    }
}
