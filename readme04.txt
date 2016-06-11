4. SqlMapConfig.xml文件中的配置

SqlMapConfig.xml是mybatis的全局配置的文件，配置的内容如下：

1. properties(属性)
2. settings(全局参数)
3. typeAliases(类型别名)
4. typeHandlers(类型处理器)
5. objectFactory(对象工厂)
6. plugins(插件)
7. environments(环境集合属性对象)
	environment(环境子属性对象)
		transactionManager(事务管理)
		dataSource(数据源)
8. mappers(映射器)

具体细节： 
1. properties属性
需求：将数据库连接参数单独配在db.properties文件中，只需要在SqlMapConfig.xml中加载db.properties的属性值
在SqlMapConfig.xml中就不需要对数据库连接参数进行硬编码了。
SqlMapConfig.xml中加载属性文件：
	<properties resource="db.properties"></properties>
	在properties内部还可以定义属性值：<property name="" value=""/>
		在property中定义的属性也可以被evironments中来加载，这就涉及到了mybatis加载属性的顺序了：
		1) 在properties元素体内定义的首先被读取
		2) 然后读取properties元素中resource或者url加载的属性，它会覆盖已读取的同名属性
		3) 最后读取parameterType传递的属性，它会覆盖已读的同名属性
		建议：
		不要在properties元素体内添加任何属性值，只将属性值定义在properties文件中
		在properties文件中定义的属性名要有一定的特殊性，如xxx.xxx
		
2. settings全局参数配置
mybatis框架在运行时可以调整一些运行参数，比如：开启二级缓存、开启延迟加载。
这些全局参数的配置会影响mybatis的运行行为，需要的时候再设置，不需要的时候不能乱设置

3. typeAliases类型别名(重点)
在mapper.xml中定义了很多的statement，statement需要parameterType；来指定输入参数的类型、
需要resultType来指定输出结果的类型。
如果在指定类型时输入类型全路径，有时候会很长，不方便进行开发，可以针对parameterType或resultType指定的类型定义一些别名，
在mapper.xml中通过别名来定义，方便开发
mybatis有默认支持的一些别名，一般基本类型都有别名，如java.lang.Integer的别名为int
但是针对pojo的需要我们自定义别名了
	<!-- 别名的定义 -->
	<typeAliases>
		<!-- 针对单个别名的定义 
			type：类型的路径； alias：别名
		-->		
		<typeAlias type="mybatis.po.User" alias="user"/>
	</typeAliases>
	然后输入参数或者输出结果为mybatis.po.User时，就可以用user来代替了
	
	<!-- 批量别名定义(这个常用)
		指定一个包名，mybatis会自动的扫描包中po类，自动定义别名，别名就是类名（首字母大写或小写都可以）
	-->
	<package name="mybatis.po"/>
	
4. typeHandlers类型处理器
在mybatis中是通过typeHandlers完成jdbc类型和java类型的转换，mybatis默认支持的类型处理器基本够我们使用了
一般不需要自定义

5. objectFactory(对象工厂)
6. plugins(插件)
这两个一般我们不用

7. environments(环境集合属性对象)
	environment(环境子属性对象)
		transactionManager(事务管理)
		dataSource(数据源)
	这个目前是用mybatis来管理的，后面与Spring整合后，会废弃掉
	
8. mappers(映射配置)
通过resource方法加载单个映射文件
	<mappers>
		<mapper resource="sqlmap/User.xml" />
		<mapper resource="mapper/UserMapper.xml" />
	</mappers>
通过url方法加载单个映射文件，不过使用url的话是绝对路径，硬盘中的路径
通过mapper接口来加载
	<!-- 通过mapper接口加载映射文件 
			遵循一些规范：需要将mapper接口类名和mapper.xml映射文件名称保持一致，且在一个目录中
			这个规范的前提是：使用的是mapper代理的方法
	-->
	所以上面的<mapper resource="mapper/UserMapper.xml" />就可以改成
	<mapper class="mybatis.mapper.UserMapper"/>
	所以要把config.mapper中的UserMapper.xml拖到和UserMapper.java一个目录下且同名
	
	
	也可以批量加载（推荐使用）
	<!-- 批量加载mapper
			指定mapper接口的包名，mybatis自动扫描包下面所有的mapper接口进行加载
			遵循一些规范：需要将mapper接口类名和mapper.xml映射文件名称保持一致，且在一个目录中
			这个规范的前提是：使用的是mapper代理的方法
	 -->
	<package name="mybatis.mapper"/>
	这样mybatis.mapper包下的所有mapper.xml配置文件都可以加载了
	