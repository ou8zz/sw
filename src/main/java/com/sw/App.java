package com.sw;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Hello world!
 *
 */
public class App extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		App app = new App();
		app.setVisible(true);
	}

	private JLabel jLabel;
	private Image image = null;

	// 固定背景图片，允许这个JPanel可以在图片上添加其他组件
	protected void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	}

	public App() {
		super();
		this.setSize(1300, 800);
		this.getContentPane().setLayout(null);
		Image image = new ImageIcon("D://迅雷下载/photo-1431887773042-803ed52bed26.jpg").getImage();
		JPanel panel = new BackgroundPanel(image);
		panel.setBounds(1, 1, 1300, 800);
		this.add(getJLabel(), null);
		this.setTitle("HelloWorld");
	}
	
	int i = 0;
	private JLabel getJLabel() {
		final String[] ps = new String[]{"IMGP0790", "IMGP0785", "IMGP0804", "IMGP0805", "IMGP0774", "IMGP0723", "IMGP0768", "IMGP0769", "IMGP0770"};
		final ImageIcon[] iis = new ImageIcon[]{new ImageIcon("C:\\Users\\OLE\\Pictures\\IMGP0790.JPG"),
				new ImageIcon("C:\\Users\\OLE\\Pictures\\IMGP0785.JPG"),
				new ImageIcon("C:\\Users\\OLE\\Pictures\\IMGP0804.JPG"),
				new ImageIcon("C:\\Users\\OLE\\Pictures\\IMGP0805.JPG"),
				new ImageIcon("C:\\Users\\OLE\\Pictures\\IMGP0774.JPG"),
				new ImageIcon("C:\\Users\\OLE\\Pictures\\IMGP0723.JPG"),
				new ImageIcon("C:\\Users\\OLE\\Pictures\\IMGP0768.JPG"),
				new ImageIcon("C:\\Users\\OLE\\Pictures\\IMGP0769.JPG"),
				new ImageIcon("C:\\Users\\OLE\\Pictures\\IMGP0770.JPG")};
		
		if (jLabel == null) {
			jLabel = new JLabel();
			jLabel.setBounds(1, 1, 2000, 1600);
			jLabel.setText("Name:");
//			icon.setImage(icon.getImage().getScaledInstance(icon.getIconWidth(), icon.getIconHeight(), Image.SCALE_DEFAULT));
			jLabel.setIcon(iis[0]);
			jLabel.addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if((i+1)<ps.length) {
						i++;
					} else {
						i=0;
					}
//					ImageIcon icon = new ImageIcon("C:\\Users\\OLE\\Pictures\\"+ps[i]+".JPG");
					jLabel.setIcon(iis[i]);
					System.out.println("update on click");
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
		}
		return jLabel;
	}

}

/**
 * 有背景图片的Panel类
 * 
 * @author tntxia
 * 
 */
class BackgroundPanel extends JPanel {

	/** 
	 *  
	 */
	private static final long serialVersionUID = -6352788025440244338L;

	private Image image = null;

	public BackgroundPanel(Image image) {
		this.image = image;
	}

	// 固定背景图片，允许这个JPanel可以在图片上添加其他组件
	protected void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	}
}
