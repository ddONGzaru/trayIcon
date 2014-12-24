import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;


public class SystrayApp implements ActionListener {

	private SystemTray systemTray = SystemTray.getSystemTray(); 
	
	private TrayIcon trayIcon;
	
	public SystrayApp() {
		initTray();
	}
	
	public void initTray() {
		
		//Image image = Toolkit.getDefaultToolkit().getImage("pdf-icon.png");
		Image image = null;
		try {
			image = ImageIO.read(getClass().getResource("mng-console.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		trayIcon = new TrayIcon(image, "PDF Gateway Server 7.3.1.2", createPopupMenu());
		trayIcon.setImageAutoSize(true);
	}
	
	/*private ActionListener actionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			System.out.println(e.getActionCommand());
			
			trayIcon.displayMessage("[status] running...", "PDF Gateway Server is running...",
				TrayIcon.MessageType.INFO);
		}
	};*/
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		System.out.println(e.getActionCommand());
		
		if (e.getActionCommand() == "메뉴항목1") {
	        System.out.println("메뉴항목1");
		} else if (e.getActionCommand() == "shutdown") {
			trayIcon.displayMessage("[status] running...", "PDF Gateway Management Console is running...",
					TrayIcon.MessageType.INFO);
			System.out.println("메뉴항목2");
			System.exit(0);
		}
	}
	
	public void run() {
		try {
			systemTray.add(trayIcon);
			trayIcon.displayMessage("[pg-mng-console 1.0.1] running...", "PDF Gateway Management Console is running...",
					TrayIcon.MessageType.INFO);
			
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private PopupMenu createPopupMenu() {
		
        PopupMenu popupMenu = new PopupMenu();
        
        MenuItem miShow = new MenuItem("[status] running...");
        MenuItem miQuit = new MenuItem("shutdown");
        
        //각각에 항목에 대해 리스너 장착. 
        //miShow.addActionListener(this);
        miQuit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				trayIcon.displayMessage("[status] running...", "PDF Gateway Server is running...",
						TrayIcon.MessageType.ERROR);
				
				/*System.out.println("메뉴항목22");
				*/
				
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				System.exit(0);
				
			}
		});
        
        //팝업 메뉴에 등록 
        popupMenu.add(miShow);
        // 줄 생성
        popupMenu.addSeparator();
        popupMenu.add(miQuit);
        
        return popupMenu;
    }
	
	public static void main(String[] args) {
		
		SystrayApp app = new SystrayApp();
		
		app.run();
		
		
		
	}

}
