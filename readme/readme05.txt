4. MyBatis的输入映射和输出映射

4.1 输入映射：通过parameterType指定输入参数的类型，类型可以是简单类型、hashmap、pojo的包装类型

主要看pojo的包装类型
4.1.1 需求：完成用户信息的综合查询，需要传入查询条件很复杂（可能包括用户信息、其它信息，比如商品、订单的）
4.1.2 定义包装类型pojo，将要查询的条件包装进去：UserQueryVo类
4.1.3 UserMapper.xml
4.1.4 UserMapper.java
4.1.5 测试UserMapperTest.java

4.2 输出映射	
4.2.1 resultType：前面已经有介绍了
4.2.2 resultMap：完成高级输出结果映射
	如果查询出来的列名和pojo的属性名不一致，通过定义一个resultMap对列名和pojo属性名之间作一个映射关系。
	1. 定义resultMap
	2. 使用resultMap作为statement的输出映射类型