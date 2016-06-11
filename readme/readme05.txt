4. MyBatis的输入映射和输出映射

4.1 输入映射：通过parameterType指定输入参数的类型，类型可以是简单类型、hashmap、pojo的包装类型

主要看pojo的包装类型
4.1.1 需求：完成用户信息的综合查询，需要传入查询条件很复杂（可能包括用户信息、其它信息，比如商品、订单的）
4.1.2 定义包装类型pojo，将要查询的条件包装进去：UserQueryVo类
4.1.3 UserMapper.xml
4.1.4 UserMapper.java
4.1.5 测试UserMapperTest.java

4.2 输出映射	