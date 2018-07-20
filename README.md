# SideBar
[点我查看项目介绍](https://www.jianshu.com/p/038d1e8b3625)

### 效果图
![仿魅族文件索引](https://github.com/hewenyuAndroid/SideBar/blob/master/screen/7082912-8cf87c5e0f2cb964.gif)

![仿微信好友索引](https://github.com/hewenyuAndroid/SideBar/blob/master/screen/7082912-c2f161ceb8bb2d96.gif)

![索引效果](https://github.com/hewenyuAndroid/SideBar/blob/master/screen/7082912-e8306bf070ca6e2c.gif)

![左侧索引栏](https://github.com/hewenyuAndroid/SideBar/blob/master/screen/7082912-ea0889a7be6f4e3e.gif)

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

* **索引栏背景相关**

| 属性        |    |
| --------   | -----  | 
|sideBarWidth     |索引栏的宽度 |
|sideBarSpacing        |索引栏与左侧/右侧的间距  | 
|sideBarPressColor         |索引栏按下时的背景色    | 
|sideBarPosition         |索引栏的位置（左侧/右侧）    | 
|sideBarPaddingTop         |索引栏的上内边距    | 
|sideBarPaddingBottom         |索引栏的下内边距    | 
|sideBarMarginTop         |索引栏的上外边距    | 
|sideBarMarginBottom         |索引栏的下外边距    | 
|sideBarNormalColor         |索引栏默认状态的背景色    | 
|sideBarCap         |索引栏两端的形状（圆形/方形）    | 
|showSideBarNormalColor         |索引栏默认状态的背景是否显示    | 


* **索引栏文字相关**

| 属性        |    |
| --------   | -----  | 
|sideBarLetterSize     |索引栏字符的大小 |
|sideBarLetterSelectColor        |索引栏被选中字符的颜色  | 
|sideBarLetterPressWidth         |索引栏被按压时的字符边框粗细    | 
|sideBarLetterNormalWidth         |索引栏字符默认的边框粗细    | 
|sideBarLetterNormalColor         |索引栏字符默认的颜色    | 


* **字符弹窗相关**

| 属性        |    |
| --------   | -----  | 
|dialogColor     |弹窗的背景色 |
|dialogCorner     |弹窗的圆角半径（方形时有效） |
|dialogHorizontalPercent     |弹窗的水平中心坐标与控件宽度的百分比，用于控制控件的x轴位置 |
|dialogIsFixed     |弹窗是否垂直固定在中间 |
|dialogLetterColor     |弹窗字符的颜色 |
|dialogLetterSize     |弹窗字符的大小 |
|dialogLetterWidth     |弹窗字符的线条边框 |
|dialogShape     |弹窗的形状(圆形/方形) |
|dialogSizePercent     |弹窗的宽度与控件宽度的百分比 |