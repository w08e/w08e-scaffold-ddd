# scaffold-ddd
ddd脚手架

概念这玩意不想墨迹，想看我这也没有

于我而言，无非是增加代码量的恶心思路。

千人千面 我的理解与您吻合，您就瞧瞧。



## 思路：
1. 结构分层，这玩意无所吊谓的其实，看你业务情况 你闲麻烦 搞一个模块也可以。不过核心的几部分是需要。
   
 <img width="304" alt="image" src="https://github.com/user-attachments/assets/bf86cb6f-4e2d-4973-9760-ecb1306c55e9">
 
 领域对象聚合根，领域服务，领域事件 领域仓库 这是比较核心领域部分，也是说独立出来不依赖持久化和应用层也可以完成业务的。 infrastructure是服务于领域的。做服务仓库的实现 

  换言之 从mysql到oracle 从Kafka到rocketmq。是不需要改你领域逻辑的。 

2. 创建或调整领域流程
<img width="921" alt="image" src="https://github.com/user-attachments/assets/6ccdb31f-62fd-4f2a-a726-3d20f1694954">

主意：save这个操作是在应用服务做（指责分离单一职责又保证领域的整洁）。领域中add event后面讲

3. ddd领域事件的落地方案
   1. 在应用服务直接调用send message不就得了？
      确实可以啊 有啥不行的  领域对象完整的生命周期中事件不算他的属性吗？ 
