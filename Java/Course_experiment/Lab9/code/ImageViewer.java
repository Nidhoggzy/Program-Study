import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class ImageViewer extends JFrame implements ActionListener,MouseListener,MouseWheelListener{

	JMenuBar navBar;//�Ϸ�����������
	JMenu file,about;
	JMenuItem open,last,next,prop;
	JLabel body, footer;//��ʾͼƬ��body����ʾͼƬ��Ϣ��footer
	ImageIcon imgIco,showii;
	JFileChooser chooseFile;
	File f;
	double width, height;
	
	public ImageViewer()
	//��ʼ�����������
	{
		navBar = new JMenuBar();
		file = new JMenu("�ļ�");
		about = new JMenu("����");
		chooseFile = new JFileChooser("./");
		
		open = new JMenuItem("��");
		open.addActionListener(this);
		last = new JMenuItem("��һ��");
		last.addActionListener(this);
		next = new JMenuItem("��һ��");
		next.addActionListener(this);
		prop = new JMenuItem("����");
		prop.addActionListener(this);
		
		file.add(open);
		file.add(last);
		file.add(next);
		about.add(prop);
		
		navBar.add(file);
		navBar.add(about);
		
		
		body = new JLabel("��ѡ��ͼƬ",JLabel.CENTER);
		body.setForeground(Color.GRAY);
		body.addMouseListener(this);
		JScrollPane scroll = new JScrollPane(body);
		
		footer = new JLabel("ͼƬ��Ϣ");
		
		this.setJMenuBar(navBar);
		this.add(scroll);
		this.add(footer, "South");
		
		this.setBounds(360, 240, 810, 630);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void Show()
	//��body ����ʾͼƬ����footer����ʾ���Եķ���
	{
        if(imgIco.getIconWidth()>720||imgIco.getIconHeight()>540)
        //���ͼƬ���󣬾Ͷ��������С������鿴ȫ��
        {
            width=720;
            height=675*(double)imgIco.getIconHeight()/imgIco.getIconWidth();
        }
        else 
        {
            width=imgIco.getIconWidth();
            height=imgIco.getIconHeight();
        }
        
        //��body����ʾͼƬ
        showii = new ImageIcon(
                imgIco.getImage().getScaledInstance((int)width,(int)height,0));
        this.body.setText("");
        this.body.setIcon(showii);
        
        //��footer����ʾ����
        String[] n=f.getName().split("\\."); //��ȡ�ļ����� 
        float per=(float)showii.getIconWidth()/imgIco.getIconWidth();  
        String msg="ԭͼ��"+imgIco.getIconWidth()+"x"+imgIco.getIconHeight()+"���ء�"  
                +"��ʾ��"+showii.getIconWidth()+"x"+showii.getIconHeight()+"���ء�"  
                +"���Ŷȣ�"+(int)(per*100)+"%"  
                +"��ʽ:"+(n.length==1?"δ֪":n[1]);  
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
            //�����ϴδ�λ��  
            chooseFile.setCurrentDirectory(chooseFile.getCurrentDirectory());  
            //���ù���  
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
		//��ʾ�ļ����ԣ�·������С
		{
			String info;
			if( body.getIcon() == null )
				info = "��ѡ���ļ�!";
			else 
				info = "ͼƬ·��:" + f.getPath() + 
					"\n��:" + body.getWidth() + 
					"  ��:" + body.getHeight()  ;
			JOptionPane.showMessageDialog( this , info , "ͼƬ��Ϣ" , JOptionPane.PLAIN_MESSAGE);
		}
		else if(f!=null&&e.getSource()==last)
		{  
            //��һ��  
            f=ImageChoose.getLast(f);  
            imgIco=new ImageIcon(f.getPath());  
            Show();  
        }
		else if(f!=null&&e.getSource()==next)
		{  
            //��һ��  
            f=ImageChoose.getNext(f);  
            imgIco=new ImageIcon(f.getPath());  
            Show(); 
		}  
	}
	
	@Override
	public void mouseClicked(MouseEvent e) 
	//���ͼƬ����������ң��ֱ���ʾ��ǰ����һ�ź���һ��
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
