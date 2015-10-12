package construction;

import braidInterface.BraidWord;

/**
 * The braid word sigma.
 * 
 * @author Zhang Xi
 * @author 200906945
 * @version 06-05-2014
 */
public class Sigma implements BraidWord {

	/** The index number of the braid word. */
	private int index;
	/** The inverse mark of the braid word. */
	private boolean isInverse;
	/** Whether the braid has error. */
	private boolean error = true;
	/** The braid word is not sigma. */
	private boolean isSigma = true;

	/** The string symbol of sigma. */
	private static final String sigmaSymbol = "¦Ò";

	/**
	 * Create the sigma.
	 * 
	 */
	public Sigma() {
		index = 0;
		isInverse = false;
		error = true;
	}

	/**
	 * Create the sigma.
	 * 
	 * @param index
	 *            the index number
	 * @param isInverse
	 *            the inverse mark
	 */
	public Sigma(int index, boolean isInverse) {
		this.index = index;
		this.isInverse = isInverse;
		error = false;
	}

	/**
	 * Create the sigma.
	 * 
	 * @param braidWord
	 *            the input braid words
	 */
	public Sigma(String braidWord) {
		error = false;
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
		return sigmaSymbol + index + inverse;
	}

}
