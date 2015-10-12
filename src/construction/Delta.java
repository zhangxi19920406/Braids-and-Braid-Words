package construction;

import java.util.LinkedList;

import braidInterface.BraidWord;

/**
 * The braid word delta.
 * 
 * @author Zhang Xi
 * @author 200906945
 * @version 06-05-2014
 */
public class Delta implements BraidWord {

	/** The index number of the braid word. */
	private int index;
	/** The inverse mark of the braid word. */
	private boolean isInverse;
	/** The half twist braid. */
	private Braid halfTwistBraid;
	/** Whether the braid has error. */
	private boolean error;
	/** The braid word is not sigma. */
	private boolean isSigma = false;

	/** The string symbol of delta. */
	private static final String deltaSymbol = "жд";

	/**
	 * Create the delta.
	 * 
	 */
	public Delta() {
		index = 0;
		isInverse = false;
		error = true;
		halfTwistBraid = null;
	}

	/**
	 * Create the delta.
	 * 
	 * @param index
	 *            the index number
	 * @param isInverse
	 *            the inverse mark
	 */
	public Delta(int index, boolean isInverse) {
		this.index = index;
		this.isInverse = isInverse;
		error = false;
		halfTwistBraid = null;
	}

	/**
	 * Create the delta.
	 * 
	 * @param braidWord
	 *            the input braid words
	 */
	public Delta(String braidWord) {
		error = false;
		halfTwistBraid = null;
		String[] bArray = braidWord.split("-");
		if (bArray.length <= 2) {
			try {
				index = Integer.parseInt(bArray[0]);
				if (bArray.length == 2 && bArray[1].equals("1")) {
					isInverse = true;
				} else if (bArray.length == 1) {
					isInverse = false;
				} else {
					error = true;
				}
			} catch (Exception e) {
				error = true;
			}
			return;
		}
	}

	/**
	 * Get the index number.
	 * 
	 * @return the index number
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Set the index number of the braid.
	 * 
	 * @param index
	 *            the index number
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * Whether the braid is in inverse case.
	 * 
	 * @return whether is inverse
	 */
	public boolean isInverse() {
		return isInverse;
	}

	/**
	 * Change the braid into the opposite inverse mark
	 * 
	 */
	public void oppositeInverse() {
		isInverse = !isInverse;
	}

	/**
	 * Calculate the half twist rewrite braid.
	 * 
	 * @return the half twist rewrite braid
	 */
	private LinkedList<BraidWord> caculateBraid() {
		LinkedList<BraidWord> tmp = new LinkedList<BraidWord>();
		for (int i = getIndex() - 1; i >= 1; i--) {
			for (int j = 1; j <= i; j++) {
				tmp.add(new Sigma(j, isInverse()));
			}
		}
		halfTwistBraid = new Braid(tmp);
		return tmp;
	}

	/**
	 * Return the half twist rewrite.
	 * 
	 * @return the rewrite form braid
	 */
	public Braid getBraid() {
		if (halfTwistBraid == null) {
			caculateBraid();
		}
		return halfTwistBraid;
	}

	/**
	 * Whether is the braid is error.
	 * 
	 * @return whether the braid is error
	 */
	public boolean isError() {
		return error;
	}

	/**
	 * Whether the braid word is sigma.
	 * 
	 * @return whether the braid word is sigma
	 */
	public boolean isSigma() {
		return isSigma;
	}

	/**
	 * Get the string case of the braid.
	 * 
	 * @return the string braid word.
	 */
	public String toString() {
		String inverse = "";
		if (isInverse()) {
			inverse = "-1";
		}
		return deltaSymbol + index + inverse;
	}

}
