package sixeightfiveseven;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		System.out.println("end me");

		PrintStream out = new PrintStream(new FileOutputStream("/home/sylvielee/Desktop/out.txt"));
		out.println("this is a test");
		out.close();
    	Verifier verifier = new Verifier();

    	BigInteger xCoord = new BigInteger(args[0]);
		BigInteger yCoord = new BigInteger(args[1]);
		ECPoint genPoint = new ECPoint(xCoord, yCoord);

		BigInteger pubKeyX = new BigInteger(args[2]);
		BigInteger pubKeyY = new BigInteger(args[3]);
		ECPoint publicKey = new ECPoint(pubKeyX, pubKeyY);

		BigInteger vX = new BigInteger(args[4]);
		BigInteger vY = new BigInteger(args[5]);
		ECPoint V = new ECPoint(vX, vY);

		BigInteger r = new BigInteger(args[6]);

		BigInteger n = new BigInteger(args[7]);

		BigInteger userId = new BigInteger(args[8]);

		boolean accept = verifier.verify(genPoint, publicKey, V, r, n);

		System.out.println("Accepted: " + accept);
    }
}
