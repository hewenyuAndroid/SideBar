# SideBar
[点我查看项目介绍](https://www.jianshu.com/p/038d1e8b3625)

### 效果图
![仿魅族文件索引](https://upload-images.jianshu.io/upload_images/7082912-8cf87c5e0f2cb964.gif?imageMogr2/auto-orient/strip)

![仿微信好友索引](https://upload-images.jianshu.io/upload_images/7082912-c2f161ceb8bb2d96.gif?imageMogr2/auto-orient/strip)

![左侧索引栏](https://upload-images.jianshu.io/upload_images/7082912-ea0889a7be6f4e3e.gif?imageMogr2/auto-orient/strip)

### 使用方法

```xml

   <com.hwy.library.SideBar
        android:id="@+id/sideBar"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:dialogColor="#99666666"
        app:dialogCorner="5dp"
        app:dialogHorizontalPercent="0.55"
        app:dialogIsFixed="true"
        app:dialogLetterColor="#FFFFFF"
        app:dialogLetterSize="30sp"
        app:dialogLetterWidth="0.5dp"
        app:dialogShape="SQUARE"
        app:dialogSizePercent="0.2"
        app:showSideBarNormalColor="false"
        app:sideBarCap="NORMAL"
        app:sideBarLetterNormalColor="#333333"
        app:sideBarLetterNormalWidth="0dp"
        app:sideBarLetterPressWidth="0.5dp"
        app:sideBarLetterSelectColor="#666666"
        app:sideBarLetterSize="16sp"
        app:sideBarMarginBottom="0dp"
        app:sideBarMarginTop="0dp"
        app:sideBarNormalColor="#66EBEBEB"
        app:sideBarPaddingBottom="2dp"
        app:sideBarPaddingTop="2dp"
        app:sideBarPosition="RIGHT"
        app:sideBarPressColor="#99888888"
        app:sideBarSpacing="0dp"
        app:sideBarWidth="30dp" />
        
```

自定义的属性还是比较多的这里分为三类：

* 索引栏背景
`app:sideBarWidth` 索引栏的宽度；
`app:sideBarSpacing` 索引栏与左侧/右侧的间距；
`app:sideBarPressColor` 索引栏按下时的背景色；
`app:sideBarPosition` 索引栏的位置（左侧/右侧）；
`app:sideBarPaddingTop` 索引栏的上内边距；
`app:sideBarPaddingBottom` 索引栏的下内边距；
`app:sideBarMarginTop` 索引栏的上外边距；
`app:sideBarMarginBottom` 索引栏的下外边距；
`app:sideBarNormalColor` 索引栏默认状态的背景色；
`app:sideBarCap` 索引栏两端的形状（圆形/方形）；
`app:showSideBarNormalColor` 索引栏默认状态的背景是否显示；

* 索引栏文字
`app:sideBarLetterSize` 索引栏字符的大小；
`app:sideBarLetterSelectColor` 索引栏被选中字符的颜色；
`app:sideBarLetterPressWidth` 索引栏被按压时的字符边框粗细；
`app:sideBarLetterNormalWidth` 索引栏字符默认的边框粗细；
`app:sideBarLetterNormalColor` 索引栏字符默认的颜色；

* 字符弹窗
`app:dialogColor` 弹窗的背景色；
`app:dialogCorner` 弹窗的圆角半径（方形时有效）；
`app:dialogHorizontalPercent` 弹窗的水平中心坐标与控件宽度的百分比，用于控制控件的x轴位置；
`app:dialogIsFixed` 弹窗是否垂直固定在中间；
`app:dialogLetterColor` 弹窗字符的颜色；
`app:dialogLetterSize` 弹窗字符的大小；
`app:dialogLetterWidth` 弹窗字符的线条边框；
`app:dialogShape` 弹窗的形状(圆形/方形)；
`app:dialogSizePercent` 弹窗的宽度与控件宽度的百分比；