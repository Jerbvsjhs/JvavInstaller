package install.jvav;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

public class ButtonListener implements ActionListener{
	Map<Object, Runnable> buttonMap = new HashMap<>();
	public ButtonListener() {
		// TODO 自动生成的构造函数存根
		buttonMap.put(InstallerMain.iButton, ()-> {
			if (!InstallerMain.toiletCheck.getState()) {
				InstallerMain.targetPage();
			} else {
				Utils.sleep(1000);
				JOptionPane.showOptionDialog(null,
						InstallerMain.installerVersion + " 发生了严重错误\n"
						+ "发生了什麽事？\n"
						+ "张浩杨博士规定Jvav只允许安装在Toliet中，否则它将抛出OUT OF TOLIET异常并自动安装迷你世界\n"
						+ "\n"
						+ "按下\"确定\"退出Jvav安装程序",
						"错误",
						JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE,
						null,
						new String[]{"确定"}, "确定");
				System.exit(0);
			}
		});
		buttonMap.put(InstallerMain.uButton, ()-> {
			Utils.sleep(1000);
			JOptionPane.showOptionDialog(null,
					InstallerMain.installerVersion + " 发生了严重错误\n"
					+ "发生了什麽事？\n"
					+ "   由于张浩杨博士更新了宇宙最先进的反卸载系统，您将无法通过Jvav卸载程序安全地卸载Jvav。\n"
					+ " 我可以安全地卸载Jvav吗？\n"
					+ "   不可以，您只能危险地卸载Jvav。\n"
					+ " 我如何危险地卸载Jvav?\n"
					+ "   您可以通过鼠标将Jvav丢进垃圾桶来卸载Jvav以破坏Windoos系统的安全性。\n"
					+ " 我不知道Jvav的安装路径如何卸载？\n"
					+ "   您可以通过粉碎硬盘或卸载迷你世界以彻底摧毁Jvav。\n"
					+ "\n"
					+ "按下\"确定\"退出Jvav安装程序",
					"错误",
					JOptionPane.DEFAULT_OPTION,
					JOptionPane.ERROR_MESSAGE,
					null,
					new String[]{"确定"}, "确定");
				System.exit(0);
		});
		buttonMap.put(InstallerMain.nextButton, ()-> {
			if (!InstallerMain.toiletExits) {
				try {
					Utils.tolietNotFind();
				} catch (ToiletNotFindException e) {
					// TODO: handle exception
					StringWriter sw = new StringWriter();
		            e.printStackTrace(new PrintWriter(sw,true));
		            String e1 = sw.toString();
					JOptionPane.showOptionDialog(null,
							InstallerMain.installerVersion + " 异常:\n"
							+ "\n"
							+ e1
							+ "\n"
							+ "按下\"确定\"创建Toilet并继续安装",
							"警告",
							JOptionPane.DEFAULT_OPTION,
							JOptionPane.WARNING_MESSAGE,
							null,
							new String[]{"确定"}, "确定");
					File userpath = new File(Utils.userpath);
					File toiletD = new File(userpath, "Toilet");
					toiletD.mkdir();
				}
			}
			InstallerMain.releasePage();
			Utils.releaseJvav();
		});
		buttonMap.put(InstallerMain.finshButton, ()-> {
			System.exit(0);
		});
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if (buttonMap.containsKey(e.getSource())) {
            buttonMap.get(e.getSource()).run();
        }
	}
}
