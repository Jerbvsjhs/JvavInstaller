package install.jvav;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;
import java.awt.Color;
import java.awt.Checkbox;
import java.awt.Cursor;
import java.awt.Button;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;

/**
 * Jvav安装器
 * @version 2.1
 * @author 张浩杨博士
 */
public class InstallerMain {
	static int width = 725;
	static int height = 400;
	
	private static Image icon = Utils.icon1.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
	private static Image logo =  Utils.logo1.getScaledInstance(InstallerMain.width, 60, Image.SCALE_SMOOTH);
	private static Color yellow = new Color(231, 229, 213);
	private static Color white = new Color(216, 216, 216);
	private static Font font = new Font("微软雅黑", Font.PLAIN, 14);
	private static JLabel weLabel = new JLabel();
	
	private static JPanel topLogo = new JPanel() {
		private static final long serialVersionUID = -8272428750779978194L;
		@Override
		public void paint(Graphics g) {
			// TODO 自动生成的方法存根
			g.drawImage(logo, 0, 0, this);
		}
	};
	
	private static JPanel panel = new JPanel() {
		private static final long serialVersionUID = 119866415454419263L;
		@Override
		public void paint(Graphics g) {
			// TODO 自动生成的方法存根
			g.setColor(new Color(160, 160, 160));
			g.drawLine(0, 0, width, 0);
			g.setColor(Color.WHITE);
			g.drawLine(0, 1, width, 1);
		}
	};
	
	private static JLabel pathJLabel = new JLabel();
	private static JFrame insFrame = new JFrame();
	private static JFrame jFrame = new JFrame();
	
	private static File toilet = new File(Utils.userpath + "/Toilet");
	public static boolean toiletExits = false;
	public static boolean outToilet = toilet.exists() && toilet.isDirectory();
	
	public static String version = Utils.jvavVersion();
	public static String installerVersion = Utils.installerVersion();
	public static String path = null;
	
	public static Button iButton = new Button("安装");
	public static Button uButton = new Button("卸载(R)");
	public static JButton nextButton = new JButton();
	public static JButton finshButton = new JButton("完成");
	
	public static JProgressBar progressBar = new JProgressBar();
	public static JLabel label = new JLabel("状态：安装Jvav");
	
	public static Checkbox toiletCheck = new Checkbox();

	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
		}
		Utils.ToiletPlayer().start();
		new InstallerMain();
	}
	public InstallerMain() {
		insFrame.getContentPane().setBackground(yellow);
		insFrame.setTitle(version + " 安装 - 欢迎使用");
		insFrame.setSize(width, height);
		insFrame.setLocationRelativeTo(null);
		insFrame.setIconImage(icon);
		insFrame.setResizable(false);
		insFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		insFrame.getContentPane().setLayout(null);
		
		topLogo.setBounds(0, 0, width, 70);
		insFrame.getContentPane().add(topLogo);
		
		weLabel.setText("欢迎使用 Jvav - 张浩杨博士更新的许可证条款");
		weLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
		weLabel.setHorizontalAlignment(SwingConstants.CENTER);
		weLabel.setBounds(0, 70, width, 40);
		insFrame.getContentPane().add(weLabel);
		
		JLabel agreement = new JLabel();
		agreement.setHorizontalAlignment(SwingConstants.CENTER);
		agreement.setFont(font);
		agreement.setBackground(Color.WHITE);
		agreement.setOpaque(true);
		agreement.setText(
				"<html><center>"
				+ "<font color='red'>授权使用此软件版本的条款已更改。<br>"
				+ "<font color='blue'><u>更新的许可协议</u><br>"
				+ "<font color='black'>此Jvav运行时仅授权供您的厕所（非商用）以及各种便器使用。"
				+ "<br>要对Jvav进行商业使用，您需要从张浩杨博士获得单独的便器许可证。"
				+ "<br>单击\"安装\"即接受许可协议并立即安装Jvav，单击\"删除\"可从您的厕所中卸载Jvav。"
				+ "<br>在Jvav安装过程中，张浩杨博士不会对您安装迷你世界。"
				+ "<font color='blue'><a href='Jvav.top'>有关Jvav的详细信息</a>"
				+ "<br>"
				+ "<br>"
				+ "<br>"
				+ "<br>"
				+ "</font></center></html>");
		agreement.setBounds(0, 115, width, 200);
		insFrame.getContentPane().add(agreement);
		
		panel.setBackground(yellow);
		panel.setBounds(0, 314, 725, 47);
		insFrame.getContentPane().add(panel);
		panel.setLayout(null);
		
		toiletCheck.setBounds(17, 14, 134, 23);
		toiletCheck.setIgnoreRepaint(true);
		toiletCheck.setFocusTraversalKeysEnabled(false);
		toiletCheck.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		toiletCheck.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		toiletCheck.setLabel("更改目标文件夹");
		toiletCheck.setFocusable(false);
		panel.add(toiletCheck);
		
		iButton.setFocusable(false);
		iButton.setBackground(white);
		iButton.setBounds(473, 11, 107, 27);
		panel.add(iButton);
		
		uButton.setFocusable(false);
		uButton.setBounds(593, 11, 107, 27);
		uButton.setBackground(white);
		panel.add(uButton);
		
		JRadioButton coolButton = new JRadioButton("Toilet Story IV ! :P");
		coolButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		coolButton.setBackground(yellow);
		coolButton.setBounds(159, 14, 134, 23);
		panel.add(coolButton);
		
		uButton.addActionListener(new ButtonListener());
		iButton.addActionListener(new ButtonListener());
		
		insFrame.setVisible(true);
	}
	
	public static void targetPage() {
		jFrame.setTitle(version + " 安装 - 目标Toilet");
		jFrame.setSize(width, height);
		jFrame.setLocationRelativeTo(insFrame);
		jFrame.setIconImage(icon);
		jFrame.setResizable(false);
		jFrame.setBackground(null);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.getContentPane().setLayout(null);
		
		topLogo.setBounds(0, 0, width, 70);
		jFrame.getContentPane().add(topLogo);
		
		weLabel.setText("目标厕所");
		weLabel.setOpaque(true);
		weLabel.setBackground(yellow);
		weLabel.setBounds(0, 60, width, 55);
		jFrame.getContentPane().add(weLabel);
		
		JPanel panel = new JPanel(){
			private static final long serialVersionUID = 1L;
			@Override
			public void paint(Graphics g) {
				// TODO 自动生成的方法存根
				g.setColor(new Color(160, 160, 160));
				g.drawLine(0, 0, width, 0);
				g.setColor(Color.WHITE);
				g.drawLine(0, 1, width, 1);
				g.setColor(yellow);
				g.fillRect(0, 2, width, 55);
			}
		};
		panel.setBounds(0, 314, 725, 57);
		panel.removeAll();
		jFrame.getContentPane().add(panel);
		panel.setLayout(null);
		
		nextButton.setBounds(547, 10, 123, 27);
		nextButton.setFont(font);
		nextButton.setText("下一步>");
		nextButton.addActionListener(new ButtonListener());
		panel.add(nextButton);
		
		JLabel label1 = new JLabel("<HTML><font color='red'>警告：无法自定义安装路径,使用默认路径!</HTML>");
		label1.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		label1.setBounds(33, 139, 260, 26);
		
		jFrame.getContentPane().add(label1);
		
		JButton changeButton = new JButton("更改(C)...");
		changeButton.setFont(font);
		changeButton.setEnabled(false);
		changeButton.setBounds(564, 176, 111, 32);
		jFrame.getContentPane().add(changeButton);
		
		pathJLabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		pathJLabel.setBounds(32, 175, 522, 55);
		jFrame.getContentPane().add(pathJLabel);
		/*
		 * Check The Toilet
		 */
		if (outToilet || Utils.userpath.toLowerCase().contains("toilet")) {
			toiletExits = true;
			if (outToilet) {
				path = Utils.userpath + "\\Toilet";
				InstallerMain.pathJLabel.setText("<HTML>安装路径:<br>"
						+ path + "</HTML>");
			} else {
				path = Utils.userpath;
				InstallerMain.pathJLabel.setText("<HTML>安装路径:<br>"
						+ path + "</HTML>");
			}
		} else {
			path = Utils.userpath + "\\Toilet";
			InstallerMain.pathJLabel.setText("<HTML>安装路径:<br>"
					+ Utils.userpath + "<font color='red'>\\Toilet" + "</HTML>");
		}
		insFrame.dispose();
		jFrame.setVisible(true);
	}
	public static void releasePage() {
		JFrame jFrame1 = new JFrame();
		jFrame1.setTitle(version + " 安装 - 进度");
		jFrame1.setSize(width, height);
		jFrame1.setLocationRelativeTo(jFrame);
		jFrame1.setIconImage(icon);
		jFrame1.setResizable(false);
		jFrame1.setBackground(null);
		jFrame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame1.getContentPane().setBackground(yellow);
		jFrame1.getContentPane().setLayout(null);
		
		topLogo.setBounds(0, 0, width, 70);
		jFrame1.getContentPane().add(topLogo);
		
		JPanel panel1 = new JPanel();
		panel1.setBackground(yellow);
		panel1.setBounds(0, 55, 725, 80);
		jFrame1.getContentPane().add(panel1);
		panel1.setLayout(null);
		
		label.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		label.setBounds(24, 20, 136, 25);
		panel1.add(label);
		
		progressBar.setValue(10);
		progressBar.setBounds(24, 56, 669, 14);
		panel1.add(progressBar);
		
		panel.setBounds(0, 314, 725, 57);
		panel.removeAll();
		jFrame1.getContentPane().add(panel);
		panel.setLayout(null);
		
		finshButton.setBounds(590, 10, 107, 23);
		finshButton.addActionListener(new ButtonListener());
		finshButton.setEnabled(false);
		panel.add(finshButton);
		
		JPanel panel_1 = new JPanel() {
			private static final long serialVersionUID = 4817668017891115106L;
			@Override
			public void paint(Graphics g) {
				// TODO 自动生成的方法存根
				g.drawImage(Utils.jvavLogo, 295, 10, null);
			}
		};
		panel_1.setBounds(0, 132, 725, 184);
		jFrame1.getContentPane().add(panel_1);
		
		jFrame.dispose();
		jFrame1.setVisible(true);
	}
}
