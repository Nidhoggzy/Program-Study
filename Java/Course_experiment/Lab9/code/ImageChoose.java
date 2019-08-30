import javax.swing.filechooser.FileFilter;
import java.io.*;
import java.util.*;

public class ImageChoose {
	
	//当前文件夹的经筛选文件（所有的图片文件）形成一个文件列表
    public static List<File> fileList=new ArrayList<File>();
    
    public static void addFile(File f)
    //将当前文件加入到文件列表中
    {
        if(!fileList.contains(f))
            fileList.add(f);
    }
    
    public static File getNext(File f)
    //获取当前文件的下一个文件
    {
        if(fileList.indexOf(f)+1<fileList.size())
            return fileList.get(fileList.indexOf(f)+1);
        else
        	//如果已经浏览到列表的最后，就跳回第一个
            return fileList.get(0);
    }
    
    public static File getLast(File f)
    //获取当前文件的上一个文件
    {
        if(fileList.indexOf(f)-1>=0)
            return fileList.get(fileList.indexOf(f)-1);
        else
        	//如果已经浏览到列表的首端，就跳回最后一个
            return fileList.get(fileList.size()-1);
    }

    public static FileFilter myFilter()
    //一个筛选器，筛选文件出图片文件
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
    //一个筛选器，筛选文件出图片文件
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

//启用一个线程，将该目录下的所有图片文件导入到文件列表中
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