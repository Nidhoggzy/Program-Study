import java.io.*;
import java.util.*;

public class Dictionary {
    private String path;//单词文件的地址
    private String regex;
    //使用ArrayList分别存储英文单词及其对应的中文解释，方便随机生成选项
    private final List<String> keyWord = new ArrayList<>();//英文单词
    private final List<String> valueWord = new ArrayList<>();//中文解释

    Dictionary(String path, String regex)
    {
        this.path = path;
        this.regex = regex;
        initiDic();
    }

    private void initiDic()
    //使用FileReader和BUfferedReader从文件中读取单词数据
    {
        FileReader reader = null;
        BufferedReader bufferedReader = null;

        try {
            reader = new FileReader(path);
            bufferedReader = new BufferedReader(reader);
            while(true) {
                String line = bufferedReader.readLine();
                if(line==null) break;

                String[] word = line.split(regex);

                if(word.length > 2) {
                    for(int i = 2; i < word.length; i++)
                        word[1] += " " + word[i];
                }
                else if(word.length < 2) continue;
                //将英文加入keyWord中，其对应的中文加入valueWord中，
                //一组单词的中英文在两个ArrayList中的索引是一致的
                keyWord.add(word[0]);
                valueWord.add(word[1]);
            }
        } catch(Exception e){
            System.out.println(e);
        }
        finally {
            try{
                bufferedReader.close();
                reader.close();

            }catch(Exception e){
                System.out.println(e);
            }
        }
    }

    private int getRandomIndex()
    //随机生成索引，用于随机生成四个单词选项
    {
        int size = keyWord.size();
        return (int) (Math.random()*100*size)%size;
    }

    HashMap<String, String> getChoice()
    //生成四个选项
    {
        //防止生成的索引会有重复，保证四个选项不重复
        List<Integer> indexTemp = new ArrayList<>();
        //生成的选项用HashMap存储，以保持二者之间的对应关系
        HashMap<String, String> choiceTemp = new HashMap<>();
        for (int i = 0; i < 4 ; i++) {
            int index = getRandomIndex();
            while( indexTemp.contains( index ) )
            //如果生成的索引有重复就一直生成直到没有重复
            {
                index = getRandomIndex();
            }

            indexTemp.add( index );

            choiceTemp.put(keyWord.get(index), valueWord.get(index));
        }

        return choiceTemp;
    }

}
