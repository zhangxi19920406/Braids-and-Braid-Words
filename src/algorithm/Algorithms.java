package algorithm;

import java.util.LinkedList;

import braidInterface.BraidWord;
import construction.Braid;
import construction.Delta;
import construction.Sigma;

/**
 * The Algorithms of the Braids and Braid Words Program, and it stores most of
 * the algorithms of the braid.
 * 
 * @author Zhang Xi
 * @author 200906945
 * @version 06-05-2014
 */
public class Algorithms {

	/** The variable stores the time of the rewrite times. */
	private int rewriteTimes;

	/**
	 * Create a algorithm object.
	 * 
	 */
	public Algorithms() {
		rewriteTimes = 0;
	}

	/**
	 * Check whether there is handle in the braid.
	 * 
	 * @param braid
	 *            the braid need to check
	 * @return whether there is a handle
	 */
	public boolean hasHandle(Braid braid) {
		Braid tmpBraid;
		if (braid.hasHalfTwistForm()) {
			tmpBraid = braid.getHalfTwistFormBraid();
		} else {
			tmpBraid = braid;
		}
		boolean tmpBoolean = false;
		BraidWord tmpB1 = tmpBraid.getBraid().get(0);
		for (int i = 1; i < tmpBraid.getBraid().size(); i++) {
			BraidWord tmpB2 = tmpBraid.getBraid().get(i);
			if (tmpB2.getIndex() < tmpB1.getIndex()) {
				tmpB1 = tmpB2;
				tmpBoolean = false;
			} else if (tmpB2.getIndex() == tmpB1.getIndex()) {
				if ((tmpB1.isInverse() == true && tmpB2.isInverse() == false)
						|| (tmpB1.isInverse() == false && tmpB2.isInverse() == true)) {
					tmpBoolean = true;
				}
			}
		}
		return tmpBoolean;
	}

	/**
	 * Get the sigma positive rewrite form of a braid.
	 * 
	 * @param braid
	 *            the braid need to rewrite
	 * @return the rewrite braid
	 */
	public Braid sigmaPositiveForm(Braid braid) {
		Braid tmpBraid;
		if (braid.hasHalfTwistForm()) {
			tmpBraid = new Braid(braid.getHalfTwistFormBraid());
		} else {
			tmpBraid = new Braid(braid);
		}
		while (rewriteTimes <= 1000) {
			boolean hasSigmaPositive = false;
			if (tmpBraid.getBraid().size() == 0) {
				Braid newBraid = new Braid();
				newBraid.setRewriteTimes(rewriteTimes);
				resetRewriteTimes();
				return newBraid;
			}
			BraidWord tmpB1 = tmpBraid.getBraid().get(0);
			int countB1 = 0;
			int countB2 = -1;
			for (int i = 1; i < tmpBraid.getBraid().size(); i++) {
				BraidWord tmpB2 = tmpBraid.getBraid().get(i);
				if (tmpB2.getIndex() < tmpB1.getIndex()) {
					tmpB1 = tmpB2;
					countB1 = i;
					countB2 = -1;
					hasSigmaPositive = false;
				} else if (tmpB2.getIndex() == tmpB1.getIndex()) {
					if ((tmpB1.isInverse() == true && tmpB2.isInverse() == false)
							|| (tmpB1.isInverse() == false && tmpB2.isInverse() == true)) {
						countB2 = i;
						hasSigmaPositive = true;
						break;
					} else {
						countB1 = i;
					}
				}
			}
			if (!hasSigmaPositive) {
				tmpBraid.setRewriteTimes(rewriteTimes);
				resetRewriteTimes();
				return tmpBraid;
			} else {
				int tmpIndexcount = tmpBraid.getStrandsNum();
				int tmpIndex = tmpBraid.getStrandsNum() - 1;
				for (int j = countB1 + 1; j < countB2; j++) {
					tmpBraid.getBraid()
							.get(j)
							.setIndex(tmpBraid.getBraid().get(j).getIndex() - 1);
				}
				LinkedList<BraidWord> tmpList1 = new LinkedList<BraidWord>();
				LinkedList<BraidWord> tmpList2 = new LinkedList<BraidWord>();
				for (int k = tmpB1.getIndex() + 1; k < tmpIndexcount; k++) {
					tmpList1.add(new Sigma(k, !tmpB1.isInverse()));
					tmpList2.add(new Sigma(tmpIndex, tmpB1.isInverse()));
					tmpIndex--;
				}
				tmpBraid.getBraid().remove(countB2);
				tmpBraid.getBraid().addAll(countB2, tmpList2);
				tmpBraid.getBraid().remove(countB1);
				tmpBraid.getBraid().addAll(countB1, tmpList1);
			}
			rewriteTimes++;
		}
		Braid newBraid = new Braid();
		newBraid.setRewriteTimes(-1);
		resetRewriteTimes();
		return newBraid;
	}

	/**
	 * Check whether the braid is a sigma positive braid.
	 * 
	 * @param braid
	 *            the braid need to be checked
	 * @return whether the braid is a sigma positive braid
	 */
	public boolean isSigmaPositive(Braid braid) {
		Boolean isSigmaPositive = false;
		Braid tmpBraid = new Braid(sigmaPositiveForm(braid));
		int tmpIndex = tmpBraid.getStrandsNum();
		for (BraidWord b : tmpBraid.getBraid()) {
			if (b.getIndex() < tmpIndex) {
				tmpIndex = b.getIndex();
				if (b.isInverse()) {
					isSigmaPositive = false;
				} else {
					isSigmaPositive = true;
				}
			} else if (b.getIndex() == tmpIndex) {
				if (b.isInverse()) {
					isSigmaPositive = false;
				}
			}
		}
		return isSigmaPositive;
	}

	/**
	 * Get the inverse case of a braid.
	 * 
	 * @param braid
	 *            the braid need to rewrite
	 * @return the inverse rewrite braid
	 */
	public Braid inverseBraid(Braid braid) {
		LinkedList<BraidWord> list = new LinkedList<BraidWord>();
		for (int i = 0; i < braid.getBraid().size(); i++) {
			if (braid.getBraid().get(i).isSigma()) {
				list.add(0, new Sigma(braid.getBraid().get(i).getIndex(),
						!braid.getBraid().get(i).isInverse()));
			} else {
				list.add(0, new Delta(braid.getBraid().get(i).getIndex(),
						!braid.getBraid().get(i).isInverse()));
			}
		}
		Braid tmpBraid = new Braid(list);
		return tmpBraid;
	}

	/**
	 * Check which braid is smaller.
	 * 
	 * @param braid1
	 *            the check braid
	 * @param braid2
	 *            the check braid
	 * @return whether braid1 is smaller then braid 2
	 */
	public boolean isSmaller(Braid braid1, Braid braid2) {
		LinkedList<BraidWord> list = new LinkedList<BraidWord>();
		list.addAll(new Braid(inverseBraid(braid1)).getBraid());
		list.addAll(braid2.getBraid());
		Braid tmpBraid = new Braid(list);
		if (isSigmaPositive(tmpBraid)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Check whether two braids are equal.
	 * 
	 * @param braid1
	 *            the check braid
	 * @param braid2
	 *            the check braid
	 * @return whether braid1 and braid2 are equal
	 */
	public boolean isEqual(Braid braid1, Braid braid2) {
		if (!isSmaller(braid1, braid2) && !isSmaller(braid2, braid1)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Rewrite the braid into Garside normal form
	 * 
	 * @param braid
	 *            the braid to rewrite
	 * @return the Gariside normal form of the braid
	 */
	public Braid garsideNormalForm(Braid braid) {
		Braid tmpBraid;
		if (braid.hasHalfTwistForm()) {
			tmpBraid = new Braid(braid.getHalfTwistFormBraid());
		} else {
			tmpBraid = new Braid(braid);
		}
		boolean transForm = false;
		int count = 0;
		int i = tmpBraid.getBraid().size() - 1;
		for (; i >= 0; i--) {
			if (tmpBraid.getBraid().get(i).isInverse()) {
				LinkedList<BraidWord> tmpList = inverseDeltaTransform(
						(Sigma) tmpBraid.getBraid().get(i),
						braid.getStrandsNum());
				if (transForm) {
					for (BraidWord b : tmpList) {
						b.setIndex(braid.getStrandsNum() - b.getIndex());
					}
				}
				tmpBraid.getBraid().remove(i);
				tmpBraid.getBraid().addAll(i, tmpList);
				for (int j = 0; j < i; j++) {
					if (!tmpBraid.getBraid().get(j).isInverse()) {
						tmpBraid.getBraid()
								.get(j)
								.setIndex(
										braid.getStrandsNum()
												- tmpBraid.getBraid().get(j)
														.getIndex());
					}
				}
				transForm = !transForm;
				count++;
			}
		}
		for (int k = 0; k < count; k++) {
			tmpBraid.getBraid().add(0, new Delta(braid.getStrandsNum(), true));
		}
		return tmpBraid;
	}

	/**
	 * Transform the braid in to inverse delta case.
	 * 
	 * @param sigma
	 *            the sigma braid
	 * @param strandsNum
	 *            the total strand number
	 * @return the final braid
	 */
	public LinkedList<BraidWord> inverseDeltaTransform(Sigma sigma,
			int strandsNum) {
		LinkedList<BraidWord> list = new LinkedList<BraidWord>();
		if (!sigma.isInverse()) {
			list.add(sigma);
			return list;
		} else if (sigma.getIndex() >= strandsNum) {
			return null;
		}
		for (int i = 1; i < strandsNum - 1; i++) {
			for (int j = i; j >= 1; j--) {
				list.add(new Sigma(j, false));
			}
		}
		for (int k = strandsNum - 1; k > sigma.getIndex(); k--) {
			list.add(new Sigma(k, false));
		}
		for (int l = 1; l < sigma.getIndex(); l++) {
			list.add(0, new Sigma(strandsNum - l, false));
		}
		return list;
	}

	/**
	 * Check whether two braids are same.
	 * 
	 * @param braid1
	 *            the check braid
	 * @param braid2
	 *            the check braid
	 * @return whether two braids are same
	 */
	public boolean isSame(Braid braid1, Braid braid2) {
		if (braid1.getBraid().size() == braid2.getBraid().size()) {
			for (int i = 0; i < braid1.getBraid().size(); i++) {
				if (braid1.getBraid().get(i).getIndex() != braid2.getBraid()
						.get(i).getIndex()) {
					return false;
				}
				if (braid1.getBraid().get(i).isInverse() != braid2.getBraid()
						.get(i).isInverse()) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * The main method support the compare experiment.
	 * 
	 * @param strandNum
	 *            the strand number of the braid
	 * @param braidLength
	 *            the total braid length
	 * @return the final compare list
	 */
	public LinkedList<Braid> compareList(int strandNum, int braidLength) {
		LinkedList<Braid> tmpBraid = new LinkedList<Braid>();
		sortBraids(createBraidList(strandNum, braidLength), tmpBraid);
		return tmpBraid;
	}

	/**
	 * Support the compare experiment and get the String result.
	 * 
	 * @param strandNum
	 *            the strand number of the braid
	 * @param braidLength
	 *            the total braid length
	 * @return the final compare list
	 */
	public String[] compareStringList(int strandNum, int braidLength) {
		LinkedList<Braid> tmpBraid = compareList(strandNum, braidLength);
		String[] tmpStringBraid = new String[tmpBraid.size()];
		for (int i = 0; i < tmpBraid.size(); i++) {
			tmpStringBraid[i] = tmpBraid.get(i).toString();
		}
		return tmpStringBraid;
	}

	/**
	 * Create the whole rewrite list.
	 * 
	 * @param strandNum
	 *            the strand number of the braid
	 * @param braidLength
	 *            the total braid length
	 * @return the final result
	 */
	public LinkedList<Braid> createBraidList(int strandNum, int braidLength) {
		LinkedList<Braid> tmpList = new LinkedList<Braid>();
		String[][] tmpString = new String[braidLength][];
		tmpString[0] = new String[(strandNum - 1) * 2];
		for (int i = 1; i <= (strandNum - 1); i++) {
			tmpString[0][i * 2 - 2] = "s" + i;
			tmpString[0][i * 2 - 1] = "s" + i + "-1";
		}
		for (int i = 2; i <= braidLength; i++) {
			tmpString[i - 1] = new String[tmpString[i - 2].length
					* (strandNum - 1) * 2];
			for (int j = 0; j < tmpString[i - 2].length; j++) {
				for (int k = 1; k <= (strandNum - 1); k++) {
					tmpString[i - 1][k * 2 - 2 + (strandNum - 1) * 2 * j] = tmpString[i - 2][j]
							+ "s" + k;
					tmpString[i - 1][k * 2 - 1 + (strandNum - 1) * 2 * j] = tmpString[i - 2][j]
							+ "s" + k + "-1";
				}
			}
		}
		for (String[] s : tmpString) {
			for (String ss : s) {
				tmpList.add(new Braid(ss));
			}
		}
		return tmpList;
	}

	/**
	 * Sorts the braids
	 * 
	 * @param braids
	 *            list of braids
	 * @param finalBraid
	 *            the braid list the the result stored in
	 */
	public void sortBraids(LinkedList<Braid> braids,
			LinkedList<Braid> finalBraid) {
		if (braids.size() == 0) {
			return;
		} else if (braids.size() == 1) {
			finalBraid.add(braids.getFirst());
		} else {
			LinkedList<Braid> left = new LinkedList<Braid>();
			LinkedList<Braid> mid = new LinkedList<Braid>();
			LinkedList<Braid> right = new LinkedList<Braid>();
			Braid thisBraid = braids.getFirst();
			for (Braid b : braids) {
				if (isSmaller(b, thisBraid)) {
					left.add(b);
				} else if (isSmaller(thisBraid, b)) {
					right.add(b);
				} else if (isEqual(b, thisBraid)) {
					mid.add(b);
				}
			}
			sortBraids(left, finalBraid);
			finalBraid.addAll(mid);
			sortBraids(right, finalBraid);
		}
	}

	/**
	 * Reset the rewrite times in to 0;
	 * 
	 */
	private void resetRewriteTimes() {
		rewriteTimes = 0;
	}

}

