# Vi, Java, Ant, Junit 的自学报告

## Vi

1. **概述**
	vi 编辑器是所有 Unix 及 Linux 系统下最基本的编辑器，它直接以命令行窗口作为显示界面。因为 vi 完全脱离鼠标而使用纯键盘控制，所以熟练以后能大大提高编辑效率。

2. **Vi 模式**
	+ **插入模式**
		文字输入模式，可对文本进行编辑。按下「ESC」键返回命令模式
	+ **底行模式**
		进行一些除输入外的操作，可将其看作**特殊的命令模式**。
		底行模式的特点是：在最后一行有冒号 + 输入的底行命令。
		在命令模式下输入冒号，就进入底行模式。在底行模式连续按两次「ESC」回到命令模式。
	+ **命令模式**
		提供除输入外的操作，如光标移动，字符删除复制等。

3. **命令模式常用命令**
	+ 「i」键进入插入模式
	+ 「h」「j」「k」「l」控制光标左下上右移动
	+ 「x」删除光标后位置一个字符并保存到缓冲区
	+ 「#x」删除光标后位置 # 个字符并保存到缓冲区
	+ 「X」删除光标前位置一个字符并保存到缓冲区
	+ 「#X」删除光标前位置 # 个字符并保存到缓冲区
	+ 「dd」删除整行并保存到缓冲区
	+ 「#dd」从光标所在行数开始删除 # 行并保存到缓冲区
	+ 「p」将缓冲区内字符粘贴到当前位置
	+ 「u」撤销一次命令
	+ 「/key_word」查找关键字，可按「n」向下查找
	+ 「?key_word」查找关键字，可按「n」向上查找
3. **Vim 与 Vi**
	vim是vi的升级版，完全兼容vi并提供了一些新的特性。在自己的 Linux 系统安装的就是 Vim。相较于 Vi，Vim有了更人性化的设计。感受突出的就是 vi 按「u」只能撤消上次命令，而在vim里可以无限制的撤消。Vim 在各种 OS 有[对应版本](https://www.vim.org/download.php#pc) 。*(Windows 版本是以窗口形式打开，让人难以忍受)*

## Java
Java 语法本身与C++很相似，对于有 C++ 基础的同学不难学习。坑点主要在环境配置和jar包的使用上。

1.  环境配置
	JAVA 运行环境的简称是 JRE *(Java Runtime Environment)*，操作系统使用它来**运行** JAVA 代码。但作为开发者，我们直接安装 JDK *(Java Development Kit)* 。它是整个java开发的核心，它包含了 JAVA 的运行环境 *(JVM + Java系统类库)* 和 JAVA 工具。
	+ 安装过程
		1. 从 [JAVA 官网](http://www.oracle.com/technetwork/java/javase/downloads/index.html) 下载对应 OS 版本的 JDK 压缩包( .tar.gz 后缀)。

		2. 解压和移动文件
			```shell
			cd /the_path_of_your_download_file 
			tar -zxvf jdk-8u160-linux-x64.tar.gz
			mkdir /usr/java
			mv ./jdk1.8.0_160 /usr/java
			```

		3. 设置环境变量
			```shell
			vim /etc/profile
			```
			在文件末尾添加如下语句
			```shell
			export JAVA_HOME=/usr/java/jdk1.8.0_102
			export JRE_HOME=/usr/java/jdk1.8.0_102/jre
			export CLASSPATH=.:$JAVA_HOME/lib:$JRE_HOME/lib:$CLASSPATH
			export PATH=$JAVA_HOME/bin:$JRE_HOME/bin:$PATH
			```

		4. 使环境变量生效
			```shell
			source /etc/profile
			```
			但是这个只在当前 terminal 生效，退出或新开终端则无用。可以在 .bashrc 文件中设置其**永久生效**。
			```shell
			vim ~/.bashrc
			```
			在里面加一句
			```shell
			source /etc/profile
			```

		5. 测试 JAVA 环境
			Terminal 输入如下指令，若显示 JAVA 版本，则环境配置成功
			```shell
			JAVA -version
			```

2. HelloWorld
	+ 代码编写
		新建 JAVA 代码文件(文件名一定要和类名一样，这和 JAVA 的运行机制有关，否则会报错)
		```shell
		vim HelloWorld.java
		```
		插入以下代码
		```java
		public class HelloWorld {
			public static void main(String[] args) {
				System.out.println("Hello World!\n");
			}
		}
		```

	+ 代码运行
		使用 JAVA Compiler 编译字节代码 class 文件并运行
		```shell
		javac HelloWorld.java
		java HelloWorld
		```

## Ant

Ant 的作用类似于 Makefile。它利用一个 xml 文件，进行程序的编译，运行，打包等操作，可以简化工程项目的编译运行工作量。
粗浅地讲讲学习的 Ant 执行的 xml 文件构架和项目架构
1. **项目架构**
	+	src 存放源文件。
	+	class 存放编译后的文件。
	+	lib 存放第三方JAR包。
	+	dist 存放打包，发布以后的代码。

2. **Ant xml 文件架构**
	```xml
	<project name="" default="" basedir="">
		<property name="" value=""></property>
		<target name="" depends="target_name">
			<echo>message</echo>
			<mkdir dir="${path}"></mkdir>
			<javac srcidr="${src}" destdir="${dest}"></javac>
			<jar jarfile="${PATH}/${name}" basedir="${PATH}"></jar>
			<java classname="${name}" classpath="${path}"></java>
		</target>
	</project>
	```

	+ project 定义了一个项目，一个 xml 文档只有一个。
		+ name 属性指定了项目名称
		+ default 表示默认执行目标，必须指定。
		+ basedir 指定项目的基准目录
	+ property 相当于一个变量
		+ name 指定变量名
		+ value 指定变量值
	+ target 是运行目标
		target执行顺序由依赖关系指定，被依赖的运行目标要优先执行。每个运行目标都会被执行一次。
		+ name 为目标名
		+ depends 为依赖关系
	+ echo 是输出标签，标签块内的信息会被输出
	+ mkdir 是目录创建标签，可以指定目录创建路径
	+ javac 是编译标签
		+ srcdir 指定编译文件路径
		+ destdir	指定编译后 class 文件路径
	+ jar 是打包标签
		+ jarfile 指定打包名称和目录
		+ basedir 指定项目根目录
	+ java 是运行标签
		+ classname 指明运行的 class 文件名
		+ classpath 指明运行的 class 文件路径

3. **Ant 实践**
	了解了基础知识，自行实现了以下 build.xml 文档代码
	```xml
	<?xml version= "1.0" encoding = "UTF-8" ?>

	<project name = "HelloWorld" default = "run" basedir = ".">
		<property name="src" value="src"/>
		<property name="class" value="class"/>
		<property name="lib" value="lib"/>
		<property name="dist" value="dist"/>
		<property name="jar" value="hello.jar"/>
		
		<target name="init">
			<echo>
			============================
			||
			||	Creating Folders
			||
			============================
			</echo>

			<mkdir dir="${class}"/>	
			<mkdir dir="${lib}"/>
			<mkdir dir="${dist}"/>
		</target>

		<target name="compile" depends="init">
			<echo>
			============================
			||
			||	Compiling
			||
			============================
			</echo>

			<javac srcdir="${src}" destdir="${class}"/>
		</target>
		
		<target name="build" depends="compile">
			<echo>
			============================
			||
			||	Building
			||
			============================
			</echo>
			
			<jar jarfile="${dist}/${jar}" basedir="${class}"/>
		</target>
		
		<target name="run" depends="build">
			<echo>
			============================
			||
			||	Run
			||
			============================
			</echo>
			
			<java classname="HelloWorld" classpath="${dist}/${jar}"/>
		</target>
	</project>
	```

	使用 ant 运行之后，输出了 Hello World，且程序包在 dist 文件夹下成功生成。

## Junit

JUnit 是 JAVA 的单元测试框架。你只要告诉 Junit 你期望的代码功能，它就会运行代码并检查代码功能是否符合预期。

1. **Junit 配置**
	首先从 Junit 下载所需版本 Junit 的 jar 包(此处下载了 [Junit-4.9.jar](http://search.maven.org/#artifactdetails|junit|junit|4.9|jar))。然后配置环境路径：在之前配置的 JAVA 路径处，更改 CLASSPATH 路径环境为
	```shell
	export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar:$JAVA_HOME/lib/junit-4.9.jar:$CLASSPATH
	```
	*(注意更改为下载的 Junit 版本名称。)* 接着记得运行生效路径环境。

2. **Junit测试代码**
	```java
	import org.junit.After;
	import org.junit.Before;
	import org.junit.Test;
	import static org.junit.Assert.*;
	
	public class AddOperationTest {
		@Before
		public void setUp() throws Exception {}

		@After
		public void tearDown() throws Exception {}

		@Test
		public void test() {
			//test code
		}
	}
	```
	相较于 Junit 3 的较为复杂的测试语法，Junit 4 提供了更为简练的代码结构：
	1. 测试类不再要求显式继承 TestCase 父类;
	2. 测试函数名没有要求一定要以 test 开头;
	3. 通过 @Before，@After，@Test 即可指明函数的执行顺序。
		+	@Before：在测试时可能多个测试函数需要相同的变量或资源，可在测试前申请，类似于初始化;
		+	@After：与@Before 相对应，释放测试完毕后初始申请的资源;
		+	@Test：测试函数，Junit 4 读到这个关键字将自动执行测试功能，不再需要像 Junit 3进行复杂的指定

3. **Junit实践** 
	按照 Junit 4 的规范架构，进行HelloWorld测试。测试类代码如下：
	```java
	import static org.junit.Assert.*;
	import org.junit.Test;
	import org.junit.Before;
	import org.junit.After;

	public class MyTest {
		HelloWorld hello;

		@Before
		public void initial() {
			hello = new HelloWorld();
		}

		@After
		public void release() {}

		@Test
		public void testHello() {
			assertEquals("Hello World!\n", hello.Hello());
		}
	}
	```
	值得注意的一点，terminal 使用 Junit 4 的指令需要指定 org.junit.runner.JUnitCore，一个支持多版本 Junit 测试的类。不指定的话，则会报测试类无主函数。即，执行指令如下：
	```shell
	javac MyTest.java
	java org.junit.runner.JUnitCore  MyTest
	```
## Ant With Junit

1. 环境配置
	将 Junit 包放入 ~/Ant/lib 目录下
2. 代码编写
	```xml
	<?xml version="1.0" encoding="UTF-8" ?>

	<project name="HelloWorld" default="test" basedir=".">
		<property name="src" value="src"/>
		<property name="testSrc" value="testSrc"/>
		<property name="class" value="class"/>
		<property name="lib" value="lib"/>
		<property name="dist" value="dist"/>
		
		<target name="init">
			<echo>
			============================
			||
			||	Creating Folders
			||
			============================
			</echo>

			<mkdir dir="${class}"/>	
			<mkdir dir="${lib}"/>
			<mkdir dir="${dist}"/>
		</target>

		<target name="codeCompile" depends="init">
			<echo>
			============================
			||
			||	Code Compiling
			||
			============================
			</echo>

			<javac srcdir="${src}" destdir="${class}"/>
		</target>

		<target name="testCompile" depends="init">
			<echo>
			============================
			||
			||	Testfile Compiling
			||
			============================
			</echo>

			<javac srcdir="${testSrc}" destdir="${class}"/>
		</target>

		<target name="test" depends="codeCompile, testCompile">
			<echo>
			============================
			||
			||	Testing
			||
			============================
			</echo>
		
			<junit printsummary="false">
				<formatter type="plain" usefile="false"/>
				<classpath path="${class}"/>
				<test name="MyTest"/>
			</junit>
		</target>
	</project>
	```
	
	+ junit 标签是进行 Junit 测试
		+	printsummary 属性指定是否输出 junit 信息
		+	formatter 指定输出格式
		+	usefile 控制是否输出到文件(*和 printsummary 使用一个就好*)
		+	classpath 指定测试类路径
		+	test 指定测试类名称
