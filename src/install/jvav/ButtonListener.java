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
		// TODO �Զ����ɵĹ��캯�����
		buttonMap.put(InstallerMain.iButton, ()-> {
			if (!InstallerMain.toiletCheck.getState()) {
				InstallerMain.targetPage();
			} else {
				Utils.sleep(1000);
				JOptionPane.showOptionDialog(null,
						InstallerMain.installerVersion + " ���������ش���\n"
						+ "������ʲ���£�\n"
						+ "�ź��ʿ�涨Jvavֻ����װ��Toliet�У����������׳�OUT OF TOLIET�쳣���Զ���װ��������\n"
						+ "\n"
						+ "����\"ȷ��\"�˳�Jvav��װ����",
						"����",
						JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE,
						null,
						new String[]{"ȷ��"}, "ȷ��");
				System.exit(0);
			}
		});
		buttonMap.put(InstallerMain.uButton, ()-> {
			Utils.sleep(1000);
			JOptionPane.showOptionDialog(null,
					InstallerMain.installerVersion + " ���������ش���\n"
					+ "������ʲ���£�\n"
					+ "   �����ź��ʿ�������������Ƚ��ķ�ж��ϵͳ�������޷�ͨ��Jvavж�س���ȫ��ж��Jvav��\n"
					+ " �ҿ��԰�ȫ��ж��Jvav��\n"
					+ "   �����ԣ���ֻ��Σ�յ�ж��Jvav��\n"
					+ " �����Σ�յ�ж��Jvav?\n"
					+ "   ������ͨ����꽫Jvav��������Ͱ��ж��Jvav���ƻ�Windoosϵͳ�İ�ȫ�ԡ�\n"
					+ " �Ҳ�֪��Jvav�İ�װ·�����ж�أ�\n"
					+ "   ������ͨ������Ӳ�̻�ж�����������Գ��״ݻ�Jvav��\n"
					+ "\n"
					+ "����\"ȷ��\"�˳�Jvav��װ����",
					"����",
					JOptionPane.DEFAULT_OPTION,
					JOptionPane.ERROR_MESSAGE,
					null,
					new String[]{"ȷ��"}, "ȷ��");
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
							InstallerMain.installerVersion + " �쳣:\n"
							+ "\n"
							+ e1
							+ "\n"
							+ "����\"ȷ��\"����Toilet��������װ",
							"����",
							JOptionPane.DEFAULT_OPTION,
							JOptionPane.WARNING_MESSAGE,
							null,
							new String[]{"ȷ��"}, "ȷ��");
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
		// TODO �Զ����ɵķ������
		if (buttonMap.containsKey(e.getSource())) {
            buttonMap.get(e.getSource()).run();
        }
	}
}
