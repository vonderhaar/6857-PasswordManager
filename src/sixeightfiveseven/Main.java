package sixeightfiveseven;

import java.io.IOException;
import java.math.BigInteger;

public class Main {

    public static void main(String[] args) throws IOException {
		Verifier verifier = new Verifier();
		Prover prover = new Prover(new BigInteger("020896"), verifier);
		boolean accept = verifier.verify(prover.getPacket());
		System.out.println(prover.getPacket().toString());
		System.out.println("Accepted: " + accept);
    }
}
