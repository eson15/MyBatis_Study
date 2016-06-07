1. 带你走进mybatis的世界

0. 示例需求
根据用户id查询用户信息
根据用户名称模糊查询用户信息
添加用户、删除用户、更新用户
1. 环境准备
	1.1 环境
	mybatis运行环境（jar包）
	从jar包中就可以看出，mybatis和hibernate有着强烈的对比，mybatis的jar包包括一个核心包和几个依赖包
	然后就是mysql的驱动包

	1.2 log4j.properties
	从mybatis的依赖包中可以看出，有好几个log4j的日志包，既然mybatis依赖log4j，那我们首先要建立一个log4j.properties文件
	在开发环境下日志级别要设置成DEBUG
	
	1.3 xml配置文件
	 SqlMapConfig.xml全局配置文件，配置mybatis的运行环境，数据源、事务等。配一下
	   多个mapper.xml配置文件，后面都放到config/sqlmap包下
	最后截个图
	
2. 根据用户id查询用户信息
	2.1 创建po：User
	2.2 创建映射文件User.xml
	映射文件名User.xml，mapper代理开发映射文件名称相对应的为UserMapper.xml
	在映射文件中配置sql语句
	2.2 在SqlMapConfig.xml中加载User.xml文件
	2.4 编写java程序

3. 根据用户名称模糊查询用户信息
	xxx
	
4. 添加用户
	xxx
	自增主键的查询
	非自增主键的查询:使用mysql的uuid()函数生成主键，需要修改表中id字段类型为string，长度设置为35位
		执行过程：先通过uuid()查询到主键，将主键输入到sql语句中
		执行uuid()语句的顺序应该相对于insert语句之前执行

5. 删除用户
