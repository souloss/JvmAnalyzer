本项目打算做成一个JVM分析库，实际上也就仅仅是利用java工具监视java程序，从中获取它的各种运行时信息，然后对其解析为对象，最后再进行归纳分类的一个程序。当然，数据获取是分析的第一步，这一切都才刚刚开始。

**2019年7月8日更新：**
目前的版本仅是利用java工具间接获取这些信息的方式，实际上JDK源码中有这些信息的直接获取接口，以及通过jmx可以很轻易的获取这些信息。
所以该工具会开一个jre版本和jdk版本以及一个jmx版本。  
jre版本即是通过exec调用java工具，通过运行时包即可使用的版本。  
jdk版本即是通过直接调用java工具类接口获取这些信息的版本，当然，这需要jdk工具包。  
jmx版本即是通过Java Management Extensions(Java管理扩展)实现的

这里需要感谢[tzfun](https://github.com/tzfun)提供的思路，JDK-lib提供的tools.jar、sa-jdi.jar包能有效对JVM进行监控。
也需要感谢java.lang.management包的作者Mandy Chung使我制作这个工具更加容易。

# 设计思想：
1. 本程序需要尽可能的精简，易懂，作为第三方库能让人轻松上手。
2. 本程序采用前后端分离的形式进行开发，因为后端也可以作为本地库进行使用，在WEB方面需要尽可能简单，只需要做到最基本的功能即可。目前Web方面只作为该库的使用demo，所以不用特别复杂，能全面的体现出该库的功能即可。
3. 后台着重于数据获取和分析的实现，对于Web方面仅需要做到简单的对象解析和基本的使用接口即可。但后续可能会考虑弄个Web主系的分支，加强对Web的支持。

# 目前的情况:  
1. 使用Vue做了基本的客户端程序。
2. 做了Jvm线程堆栈数据的获取，但代码还有点差劲。
3. Web方面的直接获取固定监听Launcher进程，因为我使用IDEA，只有这个Java进程常驻hhh。
4. 觉得 jmx挺厉害挺有意思，也是个不错的数据源，可以考虑并进，现在写了个tomcat-jmx的demo代码。
5. 前端做的很挫，没有设计性，没有好看的UI，没有精简的代码(学习中...)

# TODO:  
1. 将平台相关代码整合集中化，使该程序能方便的扩展到任意能运行JVM的系统中。
2. 完善堆以及GC对象化的部分。
3. 完善异常和日志部分。  
4. ......

# 使用说明：
由于本程序本是作为本地库进行设计的，所以可以直接将他打包为jar，然后调用service包中的程序调用public-Api即可使用。但目前还不太完善，所以开发了一个Web-demo来对其进行展示。
后端的Web部分使用了较为轻量级的[spark](http://sparkjava.com/)作为Web框架，他提供了一个精简的嵌入式Web容器(Jetty)，以及简单好用的MVC-API。
前端使用node.js环境的vue进行开发，所以如果要使用该Web-demo需要安装以下环境:
- [node.js](https://nodejs.org/en/)
- [vue-cli3](https://cli.vuejs.org/)

**使用步骤为:**  
1. 启动web包中的WebLauncher主函数，使之开启Web服务。
2. 使用vue ui来开启vue开发脚手架。
3. 导入该项目，在任务中运行server即可。




# 外在参考项
**可能需要使用到的内置工具:**
## Console Version
### jinfo 
1. 查看Java进程的栈空间大小
2. 查看是否使用了压缩指针
3. 查看系统属性


### jstack
1. 查看一个指定的Java进程中的线程的状态

### jstat
1. 统计gc的信息
2. 统计堆信息

### jmap
1. 空间中各个年龄段的空间的使用情况


## GUI Version
### jvisualvm
.....


其实网上也有类似的程序，但他们有以下局限性：
1. 没有开源，所以不能定制，功能不够灵活。
2. 只能通过文件和网络操作，以JSON为信息载体使用，不能作为本地库集成到其它系统中。

同时我认为他们也有很多可学习之处，通过这样一个强大而完善的同类型前辈做参考，让自己去实现一些东西，这不是一件很有趣的事情吗？

值得学习：  
[Metrics](https://metrics.dropwizard.io/3.1.0)   
[VmConsole-Api](https://github.com/tzfun/VmConsole-Api)  
[线程堆栈分析](https://www.fastthread.io/index.jsp)    
[堆分析](https://heaphero.io/)  
[GC日志分析](https://gceasy.io/)