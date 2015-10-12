package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

import algorithm.Algorithms;
import visualization.BraidView;
import construction.Braid;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class GUI extends JFrame {
	/**
	 * 
	 */
	private Algorithms algorithms;
	private static final long serialVersionUID = 1L;
	private JPanel view;
	private JTabbedPane visualization;
	private JPanel compare;
	private JPanel rewrite;
	private JList<String> rewriteResult;
	private JButton compareConfirm;
	private JScrollPane rewriteListScrollPane;
	private JScrollPane rewriteScrollPane;
	private JScrollPane compareScrollPane2;
	private JScrollPane compareScrollPane1;
	private JScrollPane viewScrollPane;
	private JPanel compareBraidView2;
	private JPanel compareBraidView1;
	private JPanel rewriteBraidView;
	private JPanel viewBraidView;
	private JTextField viewSigmaPositiveResult;
	private JButton viewSigmaPositive;
	private JButton viewGarsideNomalForm;
	private JTextField viewBraidOutput;
	private JTextField compareResult;
	private JTextField compareInput2;
	private JTextField compareInput1;
	private JButton rewriteConfirm;
	private JTextField rewriteInput;
	private JButton viewConfirm;
	private JTextField viewInput;

	private static final String inputFomat = "([s|ж╥|d|жд][1-9]{1}[0-9]*([\\-][1])?)+";// ([s|ж╥|d|жд][1-9]{1}[0-9]+([\-][1])?)+
	private static final String errorMessage = "The input should be in correct format. \r\neg: s1-1d1ж╥2жд3-1";
	private JTextField compareExperimentBraidLength;
	private JTextField compareExperimentTextField2;
	private JTextField compareExperimentstrandsNumber;
	private JTextField compareExperimentTextField1;
	private JButton compareExperimentConfirm;
	private JList<String> compareExperimentList;
	private JTextField textField2;
	private JTextField textField1;
	private JPanel compareExperimentPanel;
	private JScrollPane compareExperimentScrollPane;
	private JScrollPane compareExperimentListScrollPane;
	private JTextField viewSigmaPositiveRewriteTimes;
	private JPanel compareExperiment;
	private JButton viewBraidSave;

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GUI inst = new GUI();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
				inst.setPreferredSize(new java.awt.Dimension(663, 458));
				inst.setSize(663, 458);
			}
		});
	}

	public GUI() {
		super();
		initGUI();
		algorithms = new Algorithms();
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("Braids and Braid Words");
			this.setPreferredSize(new java.awt.Dimension(662, 458));
			this.setResizable(false);
			{
				visualization = new JTabbedPane();
				getContentPane().add(visualization, BorderLayout.CENTER);
				visualization
						.setPreferredSize(new java.awt.Dimension(660, 429));
				{
					view = new JPanel();
					visualization.addTab("View", null, view, null);
					view.setLayout(null);
					{
						viewInput = new JTextField();
						view.add(viewInput);
						viewInput.setBounds(23, 28, 375, 32);
					}
					{
						viewConfirm = new JButton();
						view.add(viewConfirm);
						viewConfirm.setText("Confirm");
						viewConfirm.setBounds(428, 28, 190, 31);
						viewConfirm.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent arg0) {
								String braidText = viewInput.getText();
								if (!braidText.matches(inputFomat)
										|| braidText == "") {
									JOptionPane.showMessageDialog(null,
											errorMessage);
									return;
								}
								view.remove(viewBraidOutput);
								viewBraidOutput = new JTextField();
								view.add(viewBraidOutput);
								viewBraidOutput.setBounds(23, 77, 375, 31);
								viewBraidOutput.setEditable(false);
								Braid braid = new Braid(braidText);
								viewScrollPane.remove(viewBraidView);
								viewBraidView = new BraidView(braid);
								viewScrollPane.setViewportView(viewBraidView);
								viewSigmaPositiveResult = new JTextField();
								view.add(viewSigmaPositiveResult);
								viewSigmaPositiveResult.setBounds(566, 189, 52,
										24);
								viewSigmaPositiveResult.setEditable(false);
								viewSigmaPositiveRewriteTimes = new JTextField();
								view.add(viewSigmaPositiveRewriteTimes);
								viewSigmaPositiveRewriteTimes
										.setEditable(false);
								viewSigmaPositiveRewriteTimes.setBounds(566,
										232, 52, 24);
							}
						});
					}
					{
						viewBraidOutput = new JTextField();
						view.add(viewBraidOutput);
						viewBraidOutput.setBounds(23, 77, 375, 31);
						viewBraidOutput.setEditable(false);
					}
					{
						viewGarsideNomalForm = new JButton();
						view.add(viewGarsideNomalForm);
						viewGarsideNomalForm.setText("Garside Nomal Form");
						viewGarsideNomalForm.setBounds(428, 75, 190, 33);
						viewGarsideNomalForm
								.addMouseListener(new MouseAdapter() {
									public void mouseClicked(MouseEvent arg0) {
										String braidText = viewInput.getText();
										if (!braidText.matches(inputFomat)
												|| braidText == "") {
											JOptionPane.showMessageDialog(null,
													errorMessage);
											return;
										}
										Braid tmpBraid = new Braid(braidText);
										Braid braid = algorithms
												.garsideNormalForm(tmpBraid);
										view.remove(viewBraidOutput);
										viewBraidOutput = new JTextField();
										view.add(viewBraidOutput);
										if (!tmpBraid.hasHalfTwistForm()) {
											viewBraidOutput.setText(braid
													.toString());
											viewScrollPane
													.remove(viewBraidView);
											viewBraidView = new BraidView(braid);
											viewScrollPane
													.setViewportView(viewBraidView);
										} else {
											viewBraidOutput
													.setText("The braid should not have the halftwist form braid word(жд)!");
										}
										viewBraidOutput.setBounds(23, 77, 375,
												31);
										viewBraidOutput.setEditable(false);
										viewSigmaPositiveResult = new JTextField();
										view.add(viewSigmaPositiveResult);
										viewSigmaPositiveResult.setBounds(566,
												189, 52, 24);
										viewSigmaPositiveResult
												.setEditable(false);
										viewSigmaPositiveRewriteTimes = new JTextField();
										view.add(viewSigmaPositiveRewriteTimes);
										viewSigmaPositiveRewriteTimes
												.setEditable(false);
										viewSigmaPositiveRewriteTimes
												.setBounds(566, 232, 52, 24);
									}
								});
					}
					{
						viewSigmaPositive = new JButton();
						view.add(viewSigmaPositive);
						viewSigmaPositive.setText("Sigma Positive Rewrite");
						viewSigmaPositive.setBounds(428, 127, 190, 32);
						viewSigmaPositive.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent arg0) {
								String braidText = viewInput.getText();
								if (!braidText.matches(inputFomat)
										|| braidText == "") {
									JOptionPane.showMessageDialog(null,
											errorMessage);
									return;
								}
								Braid tmpBraid = new Braid(braidText);
								Braid braid = algorithms
										.sigmaPositiveForm(tmpBraid);
								view.remove(viewBraidOutput);
								viewBraidOutput = new JTextField();
								view.add(viewBraidOutput);
								viewBraidOutput.setText(braid.toString());
								viewScrollPane.remove(viewBraidView);
								viewBraidView = new BraidView(braid);
								viewScrollPane.setViewportView(viewBraidView);
								viewSigmaPositiveResult = new JTextField();
								view.add(viewSigmaPositiveResult);
								viewSigmaPositiveResult.setBounds(566, 189, 52,
										24);
								if (algorithms.isSigmaPositive(tmpBraid)) {
									viewSigmaPositiveResult.setText("Yes");
								} else {
									viewSigmaPositiveResult.setText("No");
								}
								viewSigmaPositiveResult.setEditable(false);
								viewSigmaPositiveRewriteTimes = new JTextField();
								view.add(viewSigmaPositiveRewriteTimes);
								viewSigmaPositiveRewriteTimes.setBounds(566,
										232, 52, 24);
								viewSigmaPositiveRewriteTimes.setText(braid
										.getRewriteTimes() + "");
								viewSigmaPositiveRewriteTimes
										.setEditable(false);
								viewBraidOutput.setBounds(23, 77, 375, 31);
								viewBraidOutput.setEditable(false);
							}
						});
					}
					{
						viewSigmaPositiveResult = new JTextField();
						view.add(viewSigmaPositiveResult);
						viewSigmaPositiveResult.setBounds(566, 189, 52, 24);
						viewSigmaPositiveResult.setEditable(false);
					}
					{
						viewScrollPane = new JScrollPane();
						view.add(viewScrollPane);
						setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						viewScrollPane.setBounds(23, 136, 375, 233);
						{
							viewBraidView = new JPanel();
							viewScrollPane.setViewportView(viewBraidView);
							viewBraidView.setBackground(new java.awt.Color(255,
									255, 255));
							viewBraidView.setPreferredSize(new Dimension(0, 0));
						}
					}
					{
						viewBraidSave = new JButton();
						view.add(viewBraidSave);
						viewBraidSave.setText("Export the Braid Picture");
						viewBraidSave.setBounds(421, 337, 197, 32);
						viewBraidSave.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent arg0) {
								Dimension imageSize = viewBraidView.getSize();
								BufferedImage image = new BufferedImage(
										imageSize.width, imageSize.height,
										BufferedImage.TYPE_INT_ARGB);
								File file = new File("braid.png");
								Graphics2D g = image.createGraphics();
								viewBraidView.paint(g);
								g.dispose();
								try {
									if (!file.exists()) {
										try {
											file.delete();
											file.createNewFile();
										} catch (IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
									ImageIO.write(image, "png", file);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						});
					}
					{
						viewSigmaPositiveRewriteTimes = new JTextField();
						view.add(viewSigmaPositiveRewriteTimes);
						viewSigmaPositiveRewriteTimes.setEditable(false);
						viewSigmaPositiveRewriteTimes.setBounds(566, 232, 52,
								24);
					}
					{
						textField1 = new JTextField();
						view.add(textField1);
						textField1.setText("\u03c3-positive braid:");
						textField1.setBounds(428, 189, 105, 24);
						textField1.setEditable(false);
						textField1.setBorder(BorderFactory.createEmptyBorder(0,
								0, 0, 0));
					}
					{
						textField2 = new JTextField();
						view.add(textField2);
						textField2.setText("Handle removed times:");
						textField2.setBounds(428, 232, 138, 24);
						textField2.setEditable(false);
						textField2.setBorder(BorderFactory.createEmptyBorder(0,
								0, 0, 0));
					}
				}
				{
					rewrite = new JPanel();
					visualization.addTab("Rewrite", null, rewrite, null);
					rewrite.setPreferredSize(new java.awt.Dimension(564, 367));
					rewrite.setLayout(null);
					{
						rewriteInput = new JTextField();
						rewrite.add(rewriteInput);
						rewriteInput.setBounds(26, 24, 281, 34);
					}
					{
						rewriteConfirm = new JButton();
						rewrite.add(rewriteConfirm);
						rewriteConfirm.setText("Confirm");
						rewriteConfirm.setBounds(341, 24, 113, 34);
						rewriteConfirm.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent arg0) {
								String braidText = rewriteInput.getText();
								if (!braidText.matches(inputFomat)
										|| braidText == "") {
									JOptionPane.showMessageDialog(null,
											errorMessage);
									return;
								}
								Braid braid = new Braid(braidText);
								rewriteScrollPane.remove(rewriteBraidView);
								rewriteBraidView = new BraidView(braid);
								rewriteScrollPane
										.setViewportView(rewriteBraidView);
								rewriteListScrollPane.remove(rewriteResult);
								ListModel<String> rewriteResultModel = new DefaultComboBoxModel<String>(
										braid.rewriteFormsStringList());
								rewriteResult = new JList<String>();
								rewriteListScrollPane
										.setViewportView(rewriteResult);
								rewriteResult.setModel(rewriteResultModel);
								rewriteResult.setBounds(26, 80, 281, 303);
								rewriteResult
										.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
								rewriteResult
										.addMouseListener(new MouseAdapter() {
											public void mouseClicked(
													MouseEvent e) {
												String braidText = rewriteResult
														.getSelectedValue();
												Braid braid = new Braid(
														braidText);
												rewriteScrollPane
														.remove(rewriteBraidView);
												rewriteBraidView = new BraidView(
														braid);
												rewriteScrollPane
														.setViewportView(rewriteBraidView);
											}
										});
							}
						});
					}
					{
						rewriteScrollPane = new JScrollPane();
						rewrite.add(rewriteScrollPane);
						rewriteScrollPane.setBounds(341, 80, 278, 303);
						{
							rewriteBraidView = new JPanel();
							rewriteScrollPane.setViewportView(rewriteBraidView);
							rewriteBraidView.setBounds(105, 69, 278, 303);
							rewriteBraidView.setBackground(new java.awt.Color(
									255, 255, 255));
							rewriteBraidView
									.setPreferredSize(new java.awt.Dimension(
											30, 30));
						}
					}
					{
						rewriteListScrollPane = new JScrollPane();
						rewrite.add(rewriteListScrollPane);
						rewriteListScrollPane.setBounds(26, 80, 281, 303);
						{
							ListModel<String> rewriteResultModel = new DefaultComboBoxModel<String>();
							rewriteResult = new JList<String>();
							rewriteListScrollPane
									.setViewportView(rewriteResult);
							rewriteResult.setModel(rewriteResultModel);
							rewriteResult.setBounds(141, 19, 232, 196);
							rewriteResult
									.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						}
					}
				}
				{
					compare = new JPanel();
					visualization.addTab("Compare", null, compare, null);
					compare.setLayout(null);
					{
						compareInput1 = new JTextField();
						compare.add(compareInput1);
						compareInput1.setBounds(33, 30, 201, 28);
					}
					{
						compareInput2 = new JTextField();
						compare.add(compareInput2);
						compareInput2.setBounds(403, 31, 201, 28);
					}
					{
						compareResult = new JTextField();
						compare.add(compareResult);
						compareResult.setBounds(299, 31, 34, 26);
						compareResult.setEditable(false);
					}
					{
						compareConfirm = new JButton();
						compare.add(compareConfirm);
						compareConfirm.setText("Confirm");
						compareConfirm.setBounds(267, 84, 97, 35);
						compareConfirm.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent arg0) {
								String braidText1 = compareInput1.getText();
								String braidText2 = compareInput2.getText();
								if (!braidText1.matches(inputFomat)
										|| braidText1 == "") {
									JOptionPane.showMessageDialog(null,
											errorMessage);
									return;
								}
								if (!braidText2.matches(inputFomat)
										|| braidText2 == "") {
									JOptionPane.showMessageDialog(null,
											errorMessage);
									return;
								}
								Braid braid1 = new Braid(braidText1);
								Braid braid2 = new Braid(braidText2);
								compareScrollPane1.remove(compareBraidView1);
								compareBraidView1 = new BraidView(braid1);
								compareScrollPane1
										.setViewportView(compareBraidView1);
								compareScrollPane2.remove(compareBraidView2);
								compareBraidView2 = new BraidView(braid2);
								compareScrollPane2
										.setViewportView(compareBraidView2);
								compare.remove(compareResult);
								compareResult = new JTextField();
								compare.add(compareResult);
								compareResult.setBounds(299, 31, 34, 26);
								compareResult.setEditable(false);
								if (algorithms.isEqual(braid1, braid2)) {
									compareResult.setText("=");
								} else if (algorithms.isSmaller(braid1, braid2)) {
									compareResult.setText("<");
								} else {
									compareResult.setText(">");
								}
							}
						});
					}
					{
						compareScrollPane1 = new JScrollPane();
						compare.add(compareScrollPane1);
						compareScrollPane1.setBounds(33, 90, 201, 274);
						{
							compareBraidView1 = new JPanel();
							compareScrollPane1
									.setViewportView(compareBraidView1);
							compareBraidView1.setBounds(262, 90, 201, 274);
							compareBraidView1.setBackground(new java.awt.Color(
									255, 255, 255));
						}
					}
					{
						compareScrollPane2 = new JScrollPane();
						compare.add(compareScrollPane2);
						compareScrollPane2.setBounds(403, 90, 201, 274);
						{
							compareBraidView2 = new JPanel();
							compareScrollPane2
									.setViewportView(compareBraidView2);
							compareBraidView2.setBounds(173, 90, 201, 274);
							compareBraidView2.setBackground(new java.awt.Color(
									255, 255, 255));
							compareBraidView2
									.setPreferredSize(new java.awt.Dimension(
											20, 27));
						}
					}
				}
				{
					compareExperiment = new JPanel();
					visualization.addTab("Compare Experiment", null,
							compareExperiment, null);
					compareExperiment.setLayout(null);
					{
						compareExperimentListScrollPane = new JScrollPane();
						compareExperiment.add(compareExperimentListScrollPane);
						compareExperimentListScrollPane.setBounds(26, 80, 281,
								303);
						{
							ListModel<String> compareExperimentListModel = new DefaultComboBoxModel<String>();
							compareExperimentList = new JList<String>();
							compareExperimentListScrollPane
									.setViewportView(compareExperimentList);
							compareExperimentList
									.setModel(compareExperimentListModel);
						}
					}
					{
						compareExperimentScrollPane = new JScrollPane();
						compareExperiment.add(compareExperimentScrollPane);
						compareExperimentScrollPane
								.setBounds(341, 80, 281, 303);
						{
							compareExperimentPanel = new JPanel();
							FlowLayout compareExperimentPanelLayout = new FlowLayout();
							compareExperimentScrollPane
									.setViewportView(compareExperimentPanel);
							compareExperimentPanel
									.setLayout(compareExperimentPanelLayout);
							compareExperimentPanel
									.setPreferredSize(new java.awt.Dimension(
											278, 300));
							compareExperimentPanel
									.setBackground(new java.awt.Color(255, 255,
											255));
						}
					}
					{
						compareExperimentConfirm = new JButton();
						compareExperiment.add(compareExperimentConfirm);
						compareExperimentConfirm.setText("Confirm");
						compareExperimentConfirm.setBounds(525, 22, 97, 35);
						compareExperimentConfirm
								.addMouseListener(new MouseAdapter() {
									public void mouseClicked(MouseEvent arg0) {
										String strandNumber = compareExperimentstrandsNumber
												.getText();
										String braidLength = compareExperimentBraidLength
												.getText();
										if (!strandNumber.matches("^[1-9]\\d*$")
												|| strandNumber == "") {
											JOptionPane.showMessageDialog(null,
													"Input a number.");
											return;
										}
										if (!braidLength.matches("^[1-9]\\d*$")
												|| braidLength == "") {
											JOptionPane.showMessageDialog(null,
													"Input a number.");
											return;
										}
										String[] braidList = algorithms.compareStringList(
												Integer.parseInt(strandNumber),
												Integer.parseInt(braidLength));
										compareExperimentScrollPane
												.remove(compareExperimentPanel);
										compareExperimentScrollPane
												.setViewportView(compareExperimentPanel);
										compareExperimentListScrollPane
												.remove(compareExperimentList);
										ListModel<String> compareExperimentListModel = new DefaultComboBoxModel<String>(
												braidList);
										compareExperimentList = new JList<String>();
										compareExperimentListScrollPane
												.setViewportView(compareExperimentList);
										compareExperimentList
												.setModel(compareExperimentListModel);
										compareExperimentList
												.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
										compareExperimentList
												.addMouseListener(new MouseAdapter() {
													public void mouseClicked(
															MouseEvent e) {
														String braidText = compareExperimentList
																.getSelectedValue();
														Braid braid = new Braid(
																braidText);
														compareExperimentScrollPane
																.remove(compareExperimentPanel);
														compareExperimentPanel = new BraidView(
																braid);
														compareExperimentScrollPane
																.setViewportView(compareExperimentPanel);
													}
												});
									}
								});
					}
					{
						compareExperimentTextField1 = new JTextField();
						compareExperiment.add(compareExperimentTextField1);
						compareExperimentTextField1
								.setText("The strands number:");
						compareExperimentTextField1.setBounds(26, 22, 126, 35);
						compareExperimentTextField1.setEditable(false);
						compareExperimentTextField1.setBorder(BorderFactory
								.createEmptyBorder(0, 0, 0, 0));
					}
					{
						compareExperimentstrandsNumber = new JTextField();
						compareExperiment.add(compareExperimentstrandsNumber);
						compareExperimentstrandsNumber.setBounds(164, 22, 65,
								35);
					}
					{
						compareExperimentTextField2 = new JTextField();
						compareExperiment.add(compareExperimentTextField2);
						compareExperimentTextField2
								.setText("The length of the braid");
						compareExperimentTextField2.setBounds(270, 23, 140, 35);
						compareExperimentTextField2.setEditable(false);
						compareExperimentTextField2.setBorder(BorderFactory
								.createEmptyBorder(0, 0, 0, 0));
					}
					{
						compareExperimentBraidLength = new JTextField();
						compareExperiment.add(compareExperimentBraidLength);
						compareExperimentBraidLength.setBounds(422, 23, 65, 35);
					}
				}
			}
			pack();
			this.setSize(662, 458);
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}
}
