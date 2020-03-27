# 实验六
## 接口回调及集合类应用

### (1). 运行以下三个程序，并对每一个程序的每一个输出结果给出分析。
程序1：
    public class Test1 {
      public static void main(String[] args) {
        System.out.println("Hi, ABC, good".matches("ABC "));
        System.out.println("Hi, ABC, good".matches(".*ABC.*"));
        System.out.println("A,B,C".replaceAll(",;","#"));
        System.out.println("A,B;C".replaceAll("[,;]","#"));

        String[] tokens = "A,B;C".split("[,;]");
        for(int i = 0; i < tokens.length; i++)
          System.out.print(tokens[i] + " ");
      }
    }

程序2：
    public class Test2 {

      public static void main(String[] args) {
        String s = "Hi, Good Morning";
        System.out.println(m(s));
      }

      public static int m(String s)
      {
        int count = 0;
        for(int i = 0; i < s.length(); i++)
          if(Character.isUpperCase(s.charAt(i)))
            count++;

        return count;
      }

    }
程序3
    public class Test3 {

        public static void main(String[] args) {
            String s = "Java";
            StringBuilder builder = new StringBuilder(s);
            change(s, builder);

            System.out.println(s);
            System.out.println(builder);
        }

        private static void change(String s, StringBuilder builder)
        {
            s = s + " and HTML";
            builder.append(" and HTML");
        }

    }

### (2). 抽象类和接口的实验。
    * (2.1) 定义一个抽象类Human：包含一个成员变量String name；构造方法Human(String name)，用于初始化姓名name；一个抽象方法double sayHello()。
    * (2.2) 定义三个继承抽象类Human的类，分别命名为Chinese、Japanese和English，在这三个类中重写sayHello()方法，分别输出一句中文、日文和英文的问候；
    * (2.3) 定义一个测试类HumanTest：创建一个包含3个Human对象的数组，3个Human对象来自Chinese、Japanese和English类，循环调用该数组中的元素的sayHello()方法。
    * (2.4) 通过一个接口（命名为Human）和三个实现类（命名为Chinese、Japanese和English）来达到如上类似的效果。

### (3). 编写Java应用程序，计算菜单“北京烤鸭：189元；西芹炒肉：12.9元；酸菜鱼：69元；铁板牛柳：32元”的总价格。

### (4). 编写Java应用程序，每次从键盘读入一个包含有大写英文字母、小写英文字母和数字混杂的字符串(例如Aa123bEFGaaa49023)，要求按顺序输出大写英文字母（例如AEFG）、小写英文字母（abaaa）和数字（12349023）。

### (5). 编写Java应用程序，统计分析网页http://csse.szu.edu.cn/staff/panwk/publications/中的每个英文单词出现的次数（不要写爬虫，可以把整个页面的内容当作一个字符串读入），要过滤掉不是英文单词的符号，例如星号*等。

### (6). 张三、李四等人是A社团成员，李四、王五等人是B社团成员，编写一个Java应用程序（要求使用集合类），输出参加A社团的人、参加B社团的人、以及同时参加两个社团的人。

### (7). 有8个显示器，其属性有尺寸和价格。编写一个Java应用程序，使用TreeMap<K,V>，按照价格从大到小排序输出所有显示器的信息，要求通过两种方式实现：通过实现Comparator接口和通过实现Comparable接口。
