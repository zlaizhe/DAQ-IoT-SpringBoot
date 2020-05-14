## 基于SpringBoot框架搭建的物联网数据采集系统服务器端
DAQ-IoT-SSM的升级版
#### 更新内容
* 1.框架迁移到SpringBoot+MyBatis，相比于SSM版的项目大大减少了xml配置，仅在application.yml文件中配置了少量信息
* 2.添加Redis缓存，在以下部分提供缓存支持：
    * 当查询当个Gateway、sensor、sensorClassify时使用查询缓存，查询过的数据会存入缓存，提高查询效率
    * 传感器提交Data数据时使用添加缓存，不直接操作数据库，而是将Data添加到Redis中形成缓存队列，提高并发效率
    * 将用户登录信息不直接存入session，而是存入Redis缓存，以实现分布式session共享
* 3.提交Data数据的异步任务支持。通过线程池实现异步地将Redis中缓存队列添加到数据库，减少数据库的写入压力。
* 4.nginx与tomcat集群支持：
   * 通过SpringBoot的内置Tomcat方便了Tomcat集群的部署
   * 提供查看IP和端口API方便进行nginx反向代理和负载均衡的部署和测试
   * 分布式session共享避免了集群环境下用户登录信息失效的问题
    
### 注意：
* 前端页面仅供测试，本系统主要是为底层传感网络提供数据提交和管理的平台。
* 默认请求路径 http://localhost:8080/iot/   其中iot是项目虚拟路径，8080为SpringBoot内置Tomcat端口，均可在application.yml文件中修改。
* 以下所有API除测试、用户相关的/login、/info、/exit之外，都会被登录拦截器所拦截，调用其他API需要先登录一个用户。
* 本系统除下载部分外，所有响应数据均为同样的JSON格式。
  * 格式：{"status" : true/false, "message" : "description...", "data": data }
    * status: 表示请求是否成功。
    * message: 对请求的描述，如果响应失败，描述失败原因。
    * data: 请求成功时响应的数据，为Object类型，可以是任何类型的数据。data的具体json的格式可参考domain包中的实体类结构。

### 提供API
#### 0. 测试
|功能|请求uri|请求方式|请求参数|
|----|------|--------|-------|
|测试|iot/api/home|GET|无|
|查看IP和端口|iot/api/address|GET|无|

#### 1. 用户相关 
|功能|请求uri|请求方式|请求参数|
|----|------|--------|-------|
|用户登录|iot/api/user/login|POST|User对象|
|查看登录用户|iot/api/user/info|GET|无|
|退出登录|iot/api/user/exit|GET|无|
|用户注册|iot/api/user/regist|POST|User对象|
|修改密码|iot/api/user/password|POST|User对象、新密码|
|修改基本信息|iot/api/user/modify|POST|User对象|

#### 2. 网关相关(restful风格)
|功能|请求uri|请求方式|请求参数|
|----|------|--------|-------|
|添加网关|iot/api/gateway|POST|Gateway对象|
|更新网关|iot/api/gateway|PUT|Gateway对象|
|删除网关|iot/api/gateway/{id}|DELETE|id值|
|查询网关|iot/api/gateway/{id}|GET|id值|
|查询所有网关|iot/api/gateway|GET|无|
|关联查询网关下的传感器|iot/api/gateway?withSensors=true|GET|布尔值|

#### 3. 传感器分类相关(restful风格)
|功能|请求uri|请求方式|请求参数|
|----|------|--------|-------|
|添加传感器分类|iot/api/classify|POST|SensorClassify对象|
|查询传感器分类|iot/api/classify/{id}|GET|id值|
|查询所有传感器分类|iot/api/classify|GET|无|
|关联查询传感器分类下的传感器|iot/api/classify?withSensors=true|GET|布尔值|
|查询某一网关下的所有传感器分类|iot/api/classify/gateway/{id}|GET|网关id值|

#### 4. 传感器相关(restful风格)
|功能|请求uri|请求方式|请求参数|
|----|------|--------|-------|
|添加传感器|iot/api/sensor|POST|Sensor对象|
|更新传感器|iot/api/sensor|PUT|Sensor对象|
|删除传感器|iot/api/sensor/{id}|DELETE|id值|
|查询传感器|iot/api/sensor/{id}|GET|id值|
|查询所有传感器|iot/api/sensor|GET|无|
|查询某一分类所有传感器|iot/api/sensor/classify/{id}|GET|分类id|
|查询某一网关所有传感器|iot/api/sensor/gateway/{id}|GET|网关id|

#### 5. 传感器数据相关
|功能|请求uri|请求方式|请求参数|
|----|------|--------|-------|
|提交一个数据|iot/api/data/receive|POST|Data对象的json格式字符串|
|提交多个数据|iot/api/data/receiveAll|POST|Data对象数组的json格式字符串|
|查询一个传感器的所有数据|iot/api/data/sensor/{id}|GET|传感器id|

#### 6. 异常相关
|功能|请求uri|请求方式|请求参数|
|----|------|--------|-------|
|查询网关异常|iot/api/gatewayException|GET|无|
|查询网关异常（分页）|iot/api/gatewayException/page/{page}|GET|页码|
|查询一段时间内的网关异常|iot/api/gatewayException/{timetamp}|GET|字符串格式:"时间戳1@时间戳2"|
|查询传感器异常|iot/api/sensorException|GET|无|
|查询传感器异常（分页）|iot/api/sensorException/page/{page}|GET|页码|
|查询一段时间内的传感器异常|iot/api/sensorException/{timetamp}|GET|字符串格式:"时间戳1@时间戳2"|

#### 7. 下载相关
|功能|请求uri|请求方式|请求参数|
|----|------|--------|-------|
|下载测试文件|iot/api/file/test|GET|无|
|下载所有网关的xls表格|iot/api/file/gateway|GET|无|
|下载所有传感器的xls表格|iot/api/file/sensor|GET|无|
|下载一个网关及其所有传感器的xls表格|iot/api/file/gateway/{id}|GET|网关id|
|下载一个传感器及其所有数据的xls表格|iot/api/file/sensor/{id}|GET|传感器id|

#### 其他
由于目前所有传感器提交的数据都是存在同一个表中，项目运行时间长了之后单表数据量会非常大，影响数据库效率，以后考虑加入分库分表功能。
