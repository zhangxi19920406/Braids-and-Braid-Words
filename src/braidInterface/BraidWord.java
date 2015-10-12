package braidInterface;

/**
 * The interface that is the data type of the sigma and delta.
 * 
 * @author Zhang Xi
 * @author 200906945
 * @version 06-05-2014
 */
public interface BraidWord {

	/**
	 * Get the index number.
	 * 
	 * @return the index number
	 */
	public int getIndex();
	
	/**
	 * Set the index number of the braid.
	 * 
	 * @param index the index number
	 */
	public void setIndex(int index);
	
	/**
	 * Whether the braid is in inverse case.
	 * 
	 * @return whether is inverse
	 */
	public boolean isInverse();
	
	/**
	 * Change the braid into the opposite inverse mark
	 * 
	 */
	public void oppositeInverse();
	
	/**
	 * Whether is the braid is error.
	 * 
	 * @return whether the braid is error
	 */
	public boolean isError();
	
	/**
	 * Whether the braid word is sigma.
	 * 
	 * @return whether the braid word is sigma
	 */
	public boolean isSigma();
	
	/**
	 * Get the string case of the braid.
	 * 
	 * @return the string braid word.
	 */
	public String toString();
}
