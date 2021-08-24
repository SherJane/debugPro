AbstractBeanFactory持有Map<String, Scope>，Scope接口，包括RequestScope和SessionScope（singleton和prototype貌似不能手动注册，registerScope函数会抛异常），通过CustomScopeConfigurer可配置。

AbstractBeanDefinition的beanClass类型为Object，可以为String（类名），也可以是Class（说明已解析）

createBeanInstance函数

真正创建bean实例，3种方法：工厂方法、构造函数注入、普通实例化。

