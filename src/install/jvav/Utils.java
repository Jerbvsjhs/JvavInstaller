package install.jvav;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

/**
 * Jvav安装程序工具类
 * @author 张浩杨博士
 *
 */
public class Utils {
	public static Image icon1 = Toolkit.getDefaultToolkit().getImage(InstallerMain.class.getResource("/install/jvav/icon.jpg"));
	public static Image logo1 = Toolkit.getDefaultToolkit().getImage(InstallerMain.class.getResource("/install/jvav/jvlo.jpg"));
	public static Image jvavLogo = Toolkit.getDefaultToolkit().getImage(InstallerMain.class.getResource("/install/jvav/jvavlogo-81x162.png"));
	private static InputStream musicFileInputStream = InstallerMain.class.getResourceAsStream("/install/jvav/ToiletStroyIV.mid");
	public static String userpath = System.getProperty("user.dir");
	private static InputStream contentFiles = Utils.class.getResourceAsStream("/install/jvav/InstallContent.txt");
	
	@SuppressWarnings("finally")
	/*
	 * 此方法用于获取Jvav版本
	 */
	public static String jvavVersion(){
		String[] version = null;
		try {
			InputStream vStream = Utils.class.getResourceAsStream("/install/jvav/Version.txt");
			InputStreamReader sr = new InputStreamReader(vStream, "UTF-8");
			BufferedReader br = new BufferedReader(sr);
			String version1 = br.readLine();
			version = version1.split("\\|");
		} finally {
			// TODO 自动生成的 catch 块
			
			return version[0];
		}
	}
	
	@SuppressWarnings("finally")
	/*
	 * 此方法用于获取安装器版本
	 */
	public static String installerVersion(){
		String[] version = null;
		try {
			InputStream vStream = Utils.class.getResourceAsStream("/install/jvav/Version.txt");
			InputStreamReader sr = new InputStreamReader(vStream, "UTF-8");
			BufferedReader br = new BufferedReader(sr);
			String version1 = br.readLine();
			version = version1.split("\\|");
		} finally {
			// TODO: handle finally clause
			return version[1];
		}
	}
	/*
	 * sleep方法
	 */
	public static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
		}
	}
	public static void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
		}
	}
	/*
	 * Toilet not find方法
	 */
	public static void tolietNotFind() {
		throw new ToiletNotFindException("ToiletNotFind");
	}
	/*
	 * Jvav的音乐播放器
	 */
	public static Thread ToiletPlayer() {
		Runnable runnable =()-> {
			try {
				while (true) {
					Sequence midiSequence = MidiSystem.getSequence(musicFileInputStream);
					Sequencer midiPlayer = MidiSystem.getSequencer();
					midiPlayer.open();
					midiPlayer.setSequence(midiSequence);
					midiPlayer.start();
					midiPlayer.setLoopCount(-1);
				}
			} catch (InvalidMidiDataException | IOException | MidiUnavailableException e) {
				// TODO 自动生成的 catch 块
			}
		};
		Thread thread = new Thread(runnable);
		return thread;
	}
	/*
	 * 此方法用于下载Jvav
	 */
	public static void downloadJvav() {
		Runnable downloadTask =()-> {
			String source = null;
			File file = new File(InstallerMain.path + "//Jvav");
			if (!file.exists() || !file.isDirectory()) {
				file.mkdir();
			}
			try {
				if (!InstallerMain.useGithub.isSelected()) {
					source = "https://gitee.com/jsxhdgrkj/jvav/releases/download/";
				} else {
					source = "https://github.com/Jerbvsjhs/JvavSrc/releases/download/";
				}
				String line = null;
				InputStreamReader sr = new InputStreamReader(contentFiles, "UTF-8");
				BufferedReader br = new BufferedReader(sr);
				while ((line = br.readLine()) != null) {
					FileOutputStream outputStream = new FileOutputStream(InstallerMain.path+"\\Jvav\\" + line);
					URL url = new URL(source + jvavVersion() + "/" + line);
					System.out.println(url);
					URLConnection urlConnection = url.openConnection();
					InputStream content = urlConnection.getInputStream();
					byte [] buffer = new byte[content.available()];
//					content.read(buffer);
					int read;
					while ((read = content.read(buffer)) != -1) {
						//输出从第0个到read个字节数
						outputStream.write(buffer, 0, read);
					}
//					outputStream.write(buffer);
					outputStream.flush();
					outputStream.close();
					System.out.println("ok");
				}
				InstallerMain.finish();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
			}
		};
		Thread downloadThread = new Thread(downloadTask);
		downloadThread.start();
	}
}
