import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class ImageViewer extends JFrame implements ActionListener,MouseListener,MouseWheelListener{

	JMenuBar navBar;//上方导航栏容器
	JMenu file,about;
	JMenuItem open,last,next,prop;
	JLabel body, footer;//显示图片的body和显示图片信息的footer
	ImageIcon imgIco,showii;
	JFileChooser chooseFile;
	File f;
	double width, height;
	
	public ImageViewer()
	//初始化组件及布局
	{
		navBar = new JMenuBar();
		file = new JMenu("文件");
		about = new JMenu("关于");
		chooseFile = new JFileChooser("./");
		
		open = new JMenuItem("打开");
		open.addActionListener(this);
		last = new JMenuItem("上一张");
		last.addActionListener(this);
		next = new JMenuItem("下一张");
		next.addActionListener(this);
		prop = new JMenuItem("属性");
		prop.addActionListener(this);
		
		file.add(open);
		file.add(last);
		file.add(next);
		about.add(prop);
		
		navBar.add(file);
		navBar.add(about);
		
		
		body = new JLabel("请选择图片",JLabel.CENTER);
		body.setForeground(Color.GRAY);
		body.addMouseListener(this);
		JScrollPane scroll = new JScrollPane(body);
		
		footer = new JLabel("图片信息");
		
		this.setJMenuBar(navBar);
		this.add(scroll);
		this.add(footer, "South");
		
		this.setBounds(360, 240, 810, 630);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void Show()
	//在body 中显示图片和在footer中显示属性的方法
	{
        if(imgIco.getIconWidth()>720||imgIco.getIconHeight()>540)
        //如果图片过大，就对其进行缩小，方便查看全体
        {
            width=720;
            height=675*(double)imgIco.getIconHeight()/imgIco.getIconWidth();
        }
        else 
        {
            width=imgIco.getIconWidth();
            height=imgIco.getIconHeight();
        }
        
        //在body中显示图片
        showii = new ImageIcon(
                imgIco.getImage().getScaledInstance((int)width,(int)height,0));
        this.body.setText("");
        this.body.setIcon(showii);
        
        //在footer中显示属性
        String[] n=f.getName().split("\\."); //获取文件类型 
        float per=(float)showii.getIconWidth()/imgIco.getIconWidth();  
        String msg="原图："+imgIco.getIconWidth()+"x"+imgIco.getIconHeight()+"像素。"  
                +"显示："+showii.getIconWidth()+"x"+showii.getIconHeight()+"像素。"  
                +"缩放度："+(int)(per*100)+"%"  
                +"格式:"+(n.length==1?"未知":n[1]);  
        this.footer.setText(msg);  
        this.setTitle(f.toString());  
        this.footer.setForeground(Color.gray);
    }
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		if(e.getSource()==open||e.getSource()==body)
		{  
            //设置上次打开位置  
            chooseFile.setCurrentDirectory(chooseFile.getCurrentDirectory());  
            //设置过滤  
            chooseFile.setFileFilter(ImageChoose.myFilter());  
            int result = chooseFile.showOpenDialog(this);  
            f = chooseFile.getSelectedFile();  
            if(result==0&&f!=null) {  
                ImageChoose.addFile(f);  
                new getFileList(chooseFile.getCurrentDirectory()).start();  
                imgIco = new ImageIcon(f.getPath());  
                Show();  
            }  
        }
		else if(e.getSource()==prop)
		//显示文件属性：路径，大小
		{
			String info;
			if( body.getIcon() == null )
				info = "请选择文件!";
			else 
				info = "图片路径:" + f.getPath() + 
					"\n宽:" + body.getWidth() + 
					"  高:" + body.getHeight()  ;
			JOptionPane.showMessageDialog( this , info , "图片信息" , JOptionPane.PLAIN_MESSAGE);
		}
		else if(f!=null&&e.getSource()==last)
		{  
            //上一张  
            f=ImageChoose.getLast(f);  
            imgIco=new ImageIcon(f.getPath());  
            Show();  
        }
		else if(f!=null&&e.getSource()==next)
		{  
            //下一张  
            f=ImageChoose.getNext(f);  
            imgIco=new ImageIcon(f.getPath());  
            Show(); 
		}  
	}
	
	@Override
	public void mouseClicked(MouseEvent e) 
	//点击图片浏览器的左右，分别显示当前的上一张和下一张
	{
		if(f!=null) 
		{
            if (e.getClickCount() == 1 && e.getX() < 250) 
            {
                f = ImageChoose.getLast(f);
                imgIco = new ImageIcon(f.getPath());
                Show();
            }
            else if (e.getClickCount() == 1 && e.getX() > 540) 
            {
                f = ImageChoose.getNext(f);
                imgIco = new ImageIcon(f.getPath());
                Show();
            }
        }
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}

	public static void main(String[] args)
	{
		try{
			UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
		}catch( Exception e ){
			e.printStackTrace();
		}
		
		ImageViewer image = new ImageViewer();
	}
}
