package construction;

import java.util.LinkedList;

import algorithm.Algorithms;
import braidInterface.BraidWord;

/**
 * The object saved the braid with the braid words
 * 
 * @author Zhang Xi
 * @author 200906945
 * @version 06-05-2014
 */
public class Braid {

	/**
	 * 
	 */
	private LinkedList<BraidWord> braid;
	private int strandsNum;
	private int rewriteTimes;
	private Braid halfTwistFormBraid;
	private boolean hasHalfTwistForm;
	private TreeNode rewriteTree;
	private LinkedList<Braid> rewriteTreelist;
	private boolean error;
	private Algorithms algorithms = new Algorithms();

	private static final String sigmaSymbol = "ж╥";
	private static final String sigmaSymbol2 = "s";
	private static final String deltaSymbol = "жд";
	private static final String deltaSymbol2 = "d";

	public Braid() {
		braid = new LinkedList<BraidWord>();
		strandsNum = -1;
		rewriteTimes = -2;
		hasHalfTwistForm = false;
		halfTwistFormBraid = null;
		rewriteTree = null;
		rewriteTreelist = null;
		error = true;
	}

	public Braid(Braid braid) {
		this.braid = new LinkedList<BraidWord>();
		for (BraidWord b : braid.getBraid()) {
			if (b.isSigma()) {
				this.braid.add(new Sigma(b.getIndex(), b.isInverse()));
			} else {
				this.braid.add(new Delta(b.getIndex(), b.isInverse()));
			}
		}
		strandsNum = -1;
		rewriteTimes = -2;
		hasHalfTwistForm = braid.hasHalfTwistForm();
		halfTwistFormBraid = null;
		rewriteTree = null;
		rewriteTreelist = null;
		error = braid.isError();
	}

	public Braid(LinkedList<BraidWord> braid) {
		this.braid = new LinkedList<BraidWord>();
		for (BraidWord b : braid) {
			if (b.isSigma()) {
				this.braid.add(new Sigma(b.getIndex(), b.isInverse()));
			} else {
				this.braid.add(new Delta(b.getIndex(), b.isInverse()));
			}
		}
		strandsNum = -1;
		rewriteTimes = -2;
		hasHalfTwistForm = false;
		halfTwistFormBraid = null;
		rewriteTree = null;
		rewriteTreelist = null;
		error = false;
	}

	public Braid(BraidWord[] braidWordArray) {
		braid = new LinkedList<BraidWord>();
		strandsNum = -1;
		rewriteTimes = -2;
		hasHalfTwistForm = false;
		for (BraidWord o : braidWordArray) {
			braid.add(o);
			if (!o.isSigma()) {
				hasHalfTwistForm = true;
			}
		}
		halfTwistFormBraid = null;
		rewriteTree = null;
		rewriteTreelist = null;
		errorCheck();
	}

	public Braid(String braidWord) {
		braid = new LinkedList<BraidWord>();
		strandsNum = -1;
		rewriteTimes = -2;
		halfTwistFormBraid = null;
		rewriteTree = null;
		rewriteTreelist = null;
		String tmp = braidWord;
		while (true) {
			boolean isDelta = false;
			if ((tmp.startsWith(deltaSymbol) || tmp.startsWith(deltaSymbol2))
					|| (tmp.startsWith(sigmaSymbol) || tmp
							.startsWith(sigmaSymbol2))) {
				if ((tmp.startsWith(deltaSymbol) || tmp
						.startsWith(deltaSymbol2))) {
					isDelta = true;
				}
				tmp = tmp.substring(1);
				int sigmaLocation = tmp.indexOf(sigmaSymbol);
				if (tmp.indexOf(sigmaSymbol2) != -1) {
					if ((tmp.indexOf(sigmaSymbol) == -1)
							|| (tmp.indexOf(sigmaSymbol2) < tmp
									.indexOf(sigmaSymbol))) {
						sigmaLocation = tmp.indexOf(sigmaSymbol2);
					}
				}
				int deltaLocation = tmp.indexOf(deltaSymbol);
				if (tmp.indexOf(deltaSymbol2) != -1) {
					if ((tmp.indexOf(deltaSymbol) == -1)
							|| (tmp.indexOf(deltaSymbol2) < tmp
									.indexOf(deltaSymbol))) {
						deltaLocation = tmp.indexOf(deltaSymbol2);
					}
				}
				String braidInfo = "";
				if (sigmaLocation == -1 && deltaLocation == -1) {
					braidInfo = tmp;
					if (!isDelta) {
						braid.add(new Sigma(braidInfo));
					} else {
						braid.add(new Delta(braidInfo));
						hasHalfTwistForm = true;
					}
					errorCheck();
					return;
				} else if (sigmaLocation == -1 || deltaLocation == -1) {
					braidInfo = tmp.substring(0,
							Math.max(sigmaLocation, deltaLocation));
					tmp = tmp.substring(Math.max(sigmaLocation, deltaLocation));
				} else {
					braidInfo = tmp.substring(0,
							Math.min(sigmaLocation, deltaLocation));
					tmp = tmp.substring(Math.min(sigmaLocation, deltaLocation));
				}
				if (!isDelta) {
					braid.add(new Sigma(braidInfo));
				} else {
					braid.add(new Delta(braidInfo));
					hasHalfTwistForm = true;
				}
			} else {
				break;
			}
		}
		error = true;
	}

	public int getStrandsNum() {
		if (braid.size() == 0) {
			return 0;
		}
		for (BraidWord o : braid) {
			if (o.isSigma()) {
				if (strandsNum < o.getIndex() + 1) {
					strandsNum = o.getIndex() + 1;
				}
			} else {
				if (strandsNum < o.getIndex()) {
					strandsNum = o.getIndex();
				}
			}
		}
		return strandsNum;
	}
	
	public void resetRewriteTimes() {
		rewriteTimes = 0;
	}
	
	public void setRewriteTimes(int times) {
		rewriteTimes = times;
	}
	
	public int getRewriteTimes() {
		return rewriteTimes;
	}

	private void errorCheck() {
		for (int i = 0; i < braid.size(); i++) {
			if (braid.get(i).isError()) {
				error = true;
				return;
			}
		}
		error = false;
	}

	public boolean isError() {
		errorCheck();
		return error;
	}

	public LinkedList<BraidWord> getBraid() {
		return braid;
	}

	public boolean hasHalfTwistForm() {
		return hasHalfTwistForm;
	}

	public Braid getHalfTwistFormBraid() {
		if (halfTwistFormBraid == null) {
			LinkedList<BraidWord> halfTwistForm = new LinkedList<BraidWord>();
			for (BraidWord o1 : braid) {
				if (o1.isSigma()) {
					halfTwistForm.add(o1);
				} else {
					Delta tmpD = (Delta) o1;
					for (BraidWord o2 : tmpD.getBraid().getBraid()) {
						halfTwistForm.add(o2);
					}
				}
			}
			halfTwistFormBraid = new Braid(halfTwistForm);
		}
		return halfTwistFormBraid;
	}

	public TreeNode rewriteForms() {
		if (rewriteTree == null) {
			rewriteTree = new TreeNode(this);
			buildRewriteTree(rewriteTree);
		}
		return rewriteTree;
	}

	public LinkedList<Braid> rewriteFormsList() {
		if (rewriteTree == null) {
			rewriteTree = new TreeNode(this);
			buildRewriteTree(rewriteTree);
		}
		if (rewriteTreelist == null) {
			rewriteTreelist = new LinkedList<Braid>();
			buildRewriteTreeList(rewriteTree);
		}
		return rewriteTreelist;
	}

	public String[] rewriteFormsStringList() {
		String[] tmpList = new String[rewriteFormsList().size()];
		for (int i = 0; i < rewriteFormsList().size(); i++) {
			tmpList[i] = rewriteFormsList().get(i).toString();
		}
		return tmpList;
	}

	private void buildRewriteTree(TreeNode node) {
		Braid b = new Braid(node.getBraid());
		for (int i = 0; i < b.getBraid().size(); i++) {
			if (i >= 1) {
				if (Math.abs(b.getBraid().get(i).getIndex()
						- b.getBraid().get(i - 1).getIndex()) >= 2) {
					BraidWord tmp = b.getBraid().get(i);
					b.getBraid().remove(i);
					b.getBraid().add(i - 1, tmp);
					if (!isInTree(b, rewriteTree)) {
						node.addChild(new TreeNode(new Braid(b)));
					}
					b = new Braid(node.getBraid());
				}
				if (((b.getBraid().get(i).getIndex() == b.getBraid().get(i - 1)
						.getIndex()) && (b.getBraid().size() != 2))
						&& (b.getBraid().get(i).isInverse() != b.getBraid()
								.get(i - 1).isInverse())) {
					b.getBraid().remove(i);
					b.getBraid().remove(i - 1);
					if (!isInTree(b, rewriteTree)) {
						node.addChild(new TreeNode(new Braid(b)));
					}
					b = new Braid(node.getBraid());
				}
			}
			if (i >= 2) {
				if ((b.getBraid().get(i).isInverse() == b.getBraid().get(i - 1)
						.isInverse())
						&& (b.getBraid().get(i - 1).isInverse() == b.getBraid()
								.get(i - 2).isInverse())) {
					if (b.getBraid().get(i - 2).getIndex() == b.getBraid()
							.get(i).getIndex()) {
						if (Math.abs(b.getBraid().get(i).getIndex()
								- b.getBraid().get(i - 1).getIndex()) == 1) {
							BraidWord tmp = b.getBraid().get(i - 1);
							b.getBraid().remove(i - 2);
							b.getBraid().add(i, tmp);
							if (!isInTree(b, rewriteTree)) {
								node.addChild(new TreeNode(new Braid(b)));
							}
							b = new Braid(node.getBraid());
						}
					}
				}
			}
		}
		for (TreeNode t : node.getChild()) {
			buildRewriteTree(t);
		}
	}

	private boolean isInTree(Braid braid, TreeNode node) {
		if (algorithms.isSame(braid, node.getBraid())) {
			return true;
		} else if (node.getChild().size() == 0) {
			return false;
		}
		for (TreeNode t : node.getChild()) {
			if (isInTree(braid, t)) {
				return true;
			}
		}
		return false;
	}

	private void buildRewriteTreeList(TreeNode node) {
		rewriteTreelist.add(node.getBraid());
		for (TreeNode t : node.getChild()) {
			buildRewriteTreeList(t);
		}
	}

	public String toString() {
		String tmp = "";
		for (int i = 0; i < braid.size(); i++) {
			tmp += braid.get(i).toString();
		}
		return tmp;
	}

	class TreeNode {

		private Braid braid;
		private LinkedList<TreeNode> childList;

		public TreeNode() {
			braid = null;
			childList = new LinkedList<TreeNode>();
		}

		public TreeNode(Braid braid) {
			this.braid = braid;
			childList = new LinkedList<TreeNode>();
		}

		public void addChild(TreeNode child) {
			childList.add(child);
		}

		public Braid getBraid() {
			return braid;
		}

		public LinkedList<TreeNode> getChild() {
			return childList;
		}

		public String toString() {
			return braid.toString() + childList.toString();
		}

	}

}
