# 实验五
## 包与继承的应用

(1). 编写一个Grade类和一个Student类。Grade类中包含有三个Student类的实例：张三、李四和王五。调用Grade类的实例中的getStudentNames方法时，能够输出班中所有学生的名字。
  Grade类中将三个Student对象作为成员变量，并在构造函数中对其进行初始化。getStudentName方法中，将Student对象中的成员变量输出。Student类中包括name一个成员变量，构造函数是带参数的，方法包括setName()和getName()。在Test类中声明了一个Grade的对象，测试了getStudentname()方法。

(2). 定义一个包，在该包中定义一个类并输出“Hello, Java”字符串。
  首先定义了一个名为lab5pro2的包，在包内定义了HelloJava类，输出”Hello, Java!”字符串。

(3). 在一个包中定义一个默认访问权限控制的类DefaultClass，然后在该包以及另一个包中分别定义类来测试DefaultClass类中的protected和public数据成员的访问控制能力。
  在包lab5pro3中定义了默认访问权限的DefaultClass类，其中包括受保护的成员变量str1，和公有的成员变量str2. 在同一个包中定义了一个测试类Test1，在另一个包中定义了一个测试类Test2，对DefaultClass类进行测试。
默认访问权限的类，可以被同一个包中的其他类访问，但是不能被其他包中的类访问。

(4). 在一个包中定义一个public访问权限控制的类PublicClass，然后在该包以及另一个包中分别定义类来测试PublicClass类中的protected和public数据成员的访问控制能力。
  在包lab5pro4中定义了public访问权限的类PublicClass，其中包括受保护的成员变量str1，和公有的成员变量str2. 并分别在包内和包外定义了两个测试类Test1和Test2，对PublicClass类进行测试。
public访问权限的类，既可以被同一个包中的类访问，也可以被包外的类访问。但是包外的类不能访问该类的受保护成员变量。

(5). 把Grade类和Student类放进cn.edu.szu.csse包中。编写一个测试类，在源代码中用import语句引入cn.edu.szu.csse包中的所有类，并对它们所包含的方法进行测试。
  将Grade类和Student类放进cn.edu.szu.csse包中，测试类中分别声明了一个Grade类的对象和一个Student类的对象，分别对两个类中的方法惊醒测试。


NOTE：除第一题代码外，其余代码丢失。
