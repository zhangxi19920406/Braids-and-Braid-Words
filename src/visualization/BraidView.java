package visualization;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import braidInterface.BraidWord;
import construction.Braid;

/**
 * The class support the braid visualization.
 * 
 * @author Zhang Xi
 * @author 200906945
 * @version 06-05-2014
 */
public class BraidView extends JPanel {

	private static final long serialVersionUID = 1L;
	private Braid braid;
	private final static int bound = 10;
	private final static int singleLength = 30;

	public BraidView( Braid braid) {
		setPreferredSize(new Dimension((braid.getStrandsNum()) * singleLength,
				braid.getBraid().size() * singleLength + bound * 2));
		setBackground(new java.awt.Color(255, 255, 255));
		this.braid = braid;
	}

	public void paintBorder(Graphics g) {
		for (int i = 0; i < braid.getBraid().size(); i++) {
			BraidWord tmpBraidWord = braid.getBraid().get(i);
			if (tmpBraidWord.isSigma()) {
				for (int j = 1; j <= braid.getStrandsNum(); j++) {
					if (tmpBraidWord.getIndex() == j) {
						if (!tmpBraidWord.isInverse()) {
							g.drawLine(bound + singleLength * (j - 1), bound
									+ singleLength * i, bound + singleLength
									* j, bound + singleLength * (i + 1));
						} else {
							g.drawLine(bound + singleLength * j, bound
									+ singleLength * i, bound + singleLength
									* (j - 1), bound + singleLength * (i + 1));
						}
					} else if (tmpBraidWord.getIndex() == j - 1) {
						if (!tmpBraidWord.isInverse()) {
							g.drawLine(bound + singleLength * (j - 1), bound
									+ singleLength * i, bound + singleLength
									* (j - 1) - singleLength / 3, bound
									+ singleLength * i + singleLength / 3);
							g.drawLine(bound + singleLength * (j - 1)
									- singleLength / 3 * 2, bound
									+ singleLength * i + singleLength / 3 * 2,
									bound + singleLength * ((j - 1) - 1), bound
											+ singleLength * (i + 1));
						} else {
							g.drawLine(bound + singleLength * ((j - 1) - 1),
									bound + singleLength * i, bound
											+ singleLength * ((j - 1) - 1)
											+ singleLength / 3, bound
											+ singleLength * i + singleLength
											/ 3);
							g.drawLine(bound + singleLength * ((j - 1) - 1)
									+ singleLength / 3 * 2, bound
									+ singleLength * i + singleLength / 3 * 2,
									bound + singleLength * (j - 1), bound
											+ singleLength * (i + 1));
						}
					} else {
						g.drawLine(bound + singleLength * (j - 1), bound
								+ singleLength * i, bound + singleLength
								* (j - 1), bound + singleLength * (i + 1));
					}
				}
			} else {
				if (!tmpBraidWord.isInverse()) {
					g.drawLine(bound, bound + singleLength * i, bound
							+ singleLength * (braid.getStrandsNum() - 1), bound
							+ singleLength * (i + 1));
					g.drawLine(bound + singleLength
							* (braid.getStrandsNum() - 1), bound + singleLength
							* i, bound + singleLength
							* (braid.getStrandsNum() - 1) - singleLength
							* (braid.getStrandsNum() - 1) / 3, bound
							+ singleLength * i + singleLength / 3);
					g.drawLine(bound + singleLength
							* (braid.getStrandsNum() - 1) - singleLength
							* (braid.getStrandsNum() - 1) / 3 * 2, bound
							+ singleLength * i + singleLength / 3 * 2, bound,
							bound + singleLength * (i + 1));
				} else {
					g.drawLine(bound + singleLength
							* (braid.getStrandsNum() - 1), bound + singleLength
							* i, bound, bound + singleLength * (i + 1));
					g.drawLine(bound, bound + singleLength * i, bound
							+ singleLength * (braid.getStrandsNum() - 1)
							- singleLength * (braid.getStrandsNum() - 1) / 3
							* 2, bound + singleLength * i + singleLength / 3);
					g.drawLine(bound + singleLength
							* (braid.getStrandsNum() - 1) - singleLength
							* (braid.getStrandsNum() - 1) / 3, bound
							+ singleLength * i + singleLength / 3 * 2, bound
							+ singleLength * (braid.getStrandsNum() - 1), bound
							+ singleLength * (i + 1));
				}
			}
		}
	}

}
