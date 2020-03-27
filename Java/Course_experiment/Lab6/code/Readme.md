实验代码略做参考

## Q1
    String类中的matches()方法用于检测是否匹配给定的正则表达式。第一句中，给定字符串并不包含("ABC ")，所以返回false，而对于(".*ABC.*")，*表示包含任意个，即是可以有0个＂.＂，所以此表达式可以匹配给定字符串中的"ABC"，所以返回true。
    replaceAll()方法用于将给定参数替换字符串所有匹配给定的正则表达式的子字符串。第三句中，指用"#"替换给定字符串中的",;"子字符串，给定字符串中匹配不到该子字符串，所以没有替换；而第四局中，指用"#"替换","或";"子字符串，所以替换后的字符串为＂A#B#C＂。
    split()方法用于去掉字符串中所有匹配给定正则表达式的子字符串，所给程序是指删除","或";"，所以输出中并不包含","";"。
 
    对于这个类中的m()方法，其作用是统计字符串中大写字母的个数，isUpperCase()判断某个字母是否为大写字母，是的话就返回true。对于给定字符串"Hi, Good Morning"，中只有‘A’，‘G’，‘M’三个大写字母，所以输出的统计结果即是3
 
    该段测试程序中，声明了一个String型变量s并赋值为"Java"，一个StringBuilder类型builder并同样初始化为初始化为"Java"，调用change()方法想将修改s和builder。但是String类是不能被修改的，而且由于s是按值传递，所以在change()方法中定义的String型变量s改变了，但是在main方法中的s并没有被修改。而StringBuilder是可以被修改的，builder实例调用了自身的修改方法，所以builder后面成功的加上了" and HTML"。
 
 
## Q3
    在类Price中，声明了Pattern类的模式对象p和Matcher类的对象m，定义了一个浮点型totalPrice用于计算总价格。使用compile方法和正则表达式"\\d+([.]\\d+)?"初始化一个模式对象p。p调用了matcher方法返回了Matcher对象即是匹配对象。在while循环中使用find方法匹配子序列，再调用group方法返回所匹配的字符串，并用变量str储存。再将返回的字符串转换为float型数字，进行计算。最后得出账单总价格。
    


## Q4
    在类中定义了三个String类型的变量：regNum、regLower、regCapital，分别作为匹配数字、大写字母、小写字母的模式。使用compile方法初始化一个模式对象p。p调用了matcher方法返回了Matcher对象即是匹配对象。实际上，在该题目中，我们首先匹配的是非数字、非大写字母、非小写字母的字符串，然后将他们使用replaceAll()方法逐一置为""，剩下的即是所需要求的数字字符串、大写字母字符串、小写字母字符串。
    


## Q5
    在该题目中，将整个网页页面的内容作为一个字符串，并使用split()方法对该字符串进行分割。将正则表达式"[[^x00-xff]\\s\\d\\p{Punct}]+"作为split()方法分割的标记，提取其中的英文单词，并且定义了一个String型的数组储存分离出的单词。同时，声明一个HashMap<String,Integer>类型的对象wordslist用于存储每个单词以及期出现的次数，英文单词作为key，而出现次数作为value。使用一个for循环统计每个单词出现的次数。最后遍历一次这个散列映射，输出其中的统计结果。
    
    
## Q7
    对于Comparable接口，在定义显示器类Displayer实现了Comparable接口，在该类中重写了compareTo方法，将Displayer中的price作为比较的依据。在主函数中，声明了TreeMap<Displayer,Integer> displayers将Displayer作为key，而将显示器的序号作为value。最后声明了一个List类型的对象list，将displayers中的key和value存储其中，在一个for循环中，将其中的内容输出。
对于Comparator接口，首先先实现Comparator接口自定义了一个比较器comparePrice，在构造TreeMap时，指定使用该比较器。同样的，将Displayer作为TreeMap的key，而将显示器序号作为value。输出时也是先转换为一个List，再在for循环中输出

