

参考文章：https://mp.weixin.qq.com/s/Ew6gHeHp7rFuy-4RfU7RPQ

#创建两个module，一个是默认的app，一个是appkotlin，一个库common
app用来编写Java测试代码
appkotlin用来编写kotlin测试代码
common库用于存放一些公共的依赖，工具类

#创建项目的包结构
adapter   适配器包，用来存放RecyclerView等的适配器
app 创建自定义Application，因为后面可能配置很多第三方，而第三方的初始化建议是每个第三方单独的创建一个单例模式的类，提供一个init方法去初始化，不然全都写在Application里面，Application代码会太多，太凌乱，后面会提供一个AppConfig类作为示范，可以使用插件去生成单例模式的类，参考我的另一篇文章 https://www.jianshu.com/p/6a3b0ae4aeb4
module 业务包，用来存放我们的各种实际练习代码，refrence包，这是用来存放你的练习草稿的，怎么说呢，你在练习的时候如果有什么需要单独提取出来测试的，你都可以在里面创建一个Activity，那个draft.txt也很好理解，就是草稿，你可以把在网上复制的代码先放到里面，后面写时可以去参考
net包用来存放网络请求相关的类
util包用来存放工具类
view包用来存放用到的自定义控件

#Common库：
存放项目通用的工具，例如Glide工具封装，还有https://www.jianshu.com/p/8b7186624ea5 中修改底部导航切换效果的工具类BottomNavigationViewHelper
app 和 appkotlin都去引用它，这样app就不会显示过多的依赖，显得比较简洁

#到res-values包下的colors文件，修改一下它的配色
<color name="colorAccent">#0094ff</color>
<color name="colorPrimary">#33aaff</color>
<color name="colorPrimaryDark">#2299ee</color>


#创建Menu文件，实现点击事件:
在res包下创建Menu文件夹，再创建menu.xml文件

#在Common的string.xml文件夹下添加几条网络图片链接
<resources>
    <string name="app_name">template</string>
    <string name="meizi_01">https://ww1.sinaimg.cn/large/0065oQSqly1fu7xueh1gbj30hs0uwtgb.jpg</string>
</resources>


#AndroidStudio 3.0以上后，添加依赖推荐使用api和implementation和替代compile
Common库里使用api , app 和 appkotlin 这些依赖于Common库的Module才会检测到，如果Common库里使用implementation, 只能Common库里使用