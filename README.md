###项目结构

* adapter
    * AdapterItem：完成Adapter Item具体的操作
    * BaseRecyclerViewAdapter：Adapter基类
    * HeaderAndFooterRecyclerViewAdapter：带有Header和Footer的Adapter
* fragment：这一块会重构为MVP或者MVVM的设计模式
    * BaseFragment：Fragment的基类
    * BaseLoadDataFragment：加载数据的Fragment的基类，在BaseFragment添加加载错误，网络错误这些UI展示，以及RxJava生命周期管理，但无法处理一个页面多个请求的情况
    * BaseListLoadDataFragment：加载列表的Fragment，继承BaseLoadDataFragment，增添了RecyclerView的的初始化
    * BasePageListLoadDataFragment：分页加载的列表Fragment，继承自BaseListLoadDataFragment，添加了分页加载和RecyclerView添加Footer的功能(未完成)

### TODO

* 结合DataBinding实现MVVM架构
* 网络请求封装：增添错误处理，以及添加额外参数的拦截器
* 常用工具类整理
*

