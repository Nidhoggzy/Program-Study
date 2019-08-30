import javax.swing.filechooser.FileFilter;
import java.io.*;
import java.util.*;

public class ImageChoose {
	
	//��ǰ�ļ��еľ�ɸѡ�ļ������е�ͼƬ�ļ����γ�һ���ļ��б�
    public static List<File> fileList=new ArrayList<File>();
    
    public static void addFile(File f)
    //����ǰ�ļ����뵽�ļ��б���
    {
        if(!fileList.contains(f))
            fileList.add(f);
    }
    
    public static File getNext(File f)
    //��ȡ��ǰ�ļ�����һ���ļ�
    {
        if(fileList.indexOf(f)+1<fileList.size())
            return fileList.get(fileList.indexOf(f)+1);
        else
        	//����Ѿ�������б����󣬾����ص�һ��
            return fileList.get(0);
    }
    
    public static File getLast(File f)
    //��ȡ��ǰ�ļ�����һ���ļ�
    {
        if(fileList.indexOf(f)-1>=0)
            return fileList.get(fileList.indexOf(f)-1);
        else
        	//����Ѿ�������б���׶ˣ����������һ��
            return fileList.get(fileList.size()-1);
    }

    public static FileFilter myFilter()
    //һ��ɸѡ����ɸѡ�ļ���ͼƬ�ļ�
    {
        FileFilter ff = new FileFilter() {
            @Override
            public boolean accept(File f) {
                String name=f.getName();
                if ( name.endsWith(".jpg")||name.endsWith(".jpeg")
                        || name.endsWith(".gif") || name.endsWith(".png")
                        || name.endsWith(".bmp")||!name.contains("."))
                    return true;
                return false;
            }

            @Override
            public String getDescription() {
                return "*.jpg|*.jpeg|*.gif|*.bmp";
            }
        };
        return ff;
    }
    
    public static FilenameFilter myFilenameFilter()
    //һ��ɸѡ����ɸѡ�ļ���ͼƬ�ļ�
    {
        FilenameFilter ff = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if ( name.endsWith(".jpg")||name.endsWith(".jpeg")
                        || name.endsWith(".gif") || name.endsWith(".png")
                        || name.endsWith(".bmp"))
                    return true;
                return false;
            }
        };
        return ff;
    }
}

//����һ���̣߳�����Ŀ¼�µ�����ͼƬ�ļ����뵽�ļ��б���
class getFileList extends Thread {
    File Current;
    public getFileList(File f){
        this.Current = f;
    }

    public void run(){
        File[] cur = Current.listFiles(ImageChoose.myFilenameFilter());
        for(int i=0;i<cur.length;i++){
           // System.out.println(cd[i]);
            ImageChoose.addFile(cur[i]);
        }
    }
}