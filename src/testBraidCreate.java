import java.util.LinkedList;

import braidInterface.BraidWord;
import algorithm.Algorithms;
import construction.Braid;
import construction.Delta;
import construction.Sigma;

public class testBraidCreate {

	public static void main(String[] args) {
		Algorithms a = new Algorithms();

		Braid b = new Braid("s1-1s2s3s1s2-1");
		System.out.println(b.toString());
		if (a.hasHandle(b)) {
			System.out.println(a.sigmaPositiveForm(b));
			System.out.println(a.isSigmaPositive(b));
		}

		System.out.println("=====");

		Braid b1 = new Braid("s1s3-1s2");
		Braid b2 = new Braid("s2s1");
		System.out.println(a.isSmaller(b1, b2));
		System.out.println(a.isSmaller(b2, b1));
		System.out.println(a.isEqual(b1, b2));

		System.out.println("=====");

		b1 = new Braid("s2s1");
		b2 = new Braid("s2s1");
		System.out.println(a.isSmaller(b1, b2));
		System.out.println(a.isEqual(b1, b2));

		System.out.println("=====");

		Braid b3 = new Braid("d3s1s1-1");
		System.out.println(b3);
		System.out.println(b3.getHalfTwistFormBraid());

		System.out.println("=====");

		Delta d = new Delta(4, true);
		System.out.println(d.getBraid());
		LinkedList<BraidWord> list1 = a.inverseDeltaTransform(new Sigma("3-1"),
				4);
		LinkedList<BraidWord> list2 = a.inverseDeltaTransform(new Sigma("2-1"),
				4);
		System.out.println(list1);
		System.out.println(list2);

		System.out.println("=====");

		Braid b4 = new Braid("s3s2-1s3-1s1s2s3-1");
		System.out.println(b4);
		System.out.println(a.garsideNormalForm(b4));

		System.out.println("=====");

		Braid b5 = new Braid("s2s1s2s1-1s2s1s2-1s1");
		System.out.println(b5);
		System.out.println(a.garsideNormalForm(b5));
		
		System.out.println("=====");
		
		Braid b6 = new Braid("s2s1s2s1-1s2s1s2-1s1");
		Braid b7 = new Braid("s2s1s2s1-1s2s1s2s1");
		System.out.println(a.isSame(b1, b7));
		System.out.println(a.isSame(b6, b7));
		
		System.out.println("=====");
		
		Braid b8 = new Braid("s2-1s2s4-1");
		System.out.println(b8.rewriteFormsList());
		
		System.out.println("=====");
		LinkedList<Braid> bb1 = a.createBraidList(2, 3);
		System.out.println(bb1.toString());
		
		System.out.println("=====");
		System.out.println(a.compareList(2, 3).toString());
	}

}
