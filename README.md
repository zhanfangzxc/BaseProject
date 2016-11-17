###项目结构

* adapter
    * AdapterItem：完成Adapter Item具体的操作
    * BaseRecyclerViewAdapter：Adapter基类
    * HeaderAndFooterRecyclerViewAdapter：带有Header和Footer的Adapter
* fragment：
    * BaseFragment：Fragment的基类
    * BaseLoadDataFragment：加载数据的Fragment的基类，在BaseFragment添加加载错误，网络错误这些UI展示，以及RxJava生命周期管理，但无法处理一个页面多个请求的情况
    * BaseListLoadDataFragment：加载列表的Fragment，继承BaseLoadDataFragment，增添了RecyclerView的的初始化
    * BasePageListLoadDataFragment：分页加载的列表Fragment，继承自BaseListLoadDataFragment，添加了分页加载和RecyclerView添加Footer的功能(未完成)
* ui：
    * FButton 一个扁平的Button，用代码实现selector 避免创建多个selector文件
    * StateView 显示各种状态的封装
* network
    * ApiError：服务器返回错误比如{resultCode:"",resultMessage:""}
    * ApiErrorGsonConverterFactory：服务器返回错误处理
    * ApiService：具体的接口
    * RestApi：Retrofit封装
    * RetrofitException：
    * RxErrorHandlingCallAdapterFactory：错误处理
### 工具类
    * 参考：https://github.com/Blankj/AndroidUtilCode




