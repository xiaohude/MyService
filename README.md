##添加抢红包服务

添加一个开关---------/

提示用户跳转到设置服务界面--------/

可以先给用户一个有过期时间的app当体验app，并且这个app可以获取手机的IMEI号，让用户将这个IMEI号提供给开发者，开发者再提供一个唯一可在此设备运行的app。


需要添加过期逻辑类

添加缓存聊天消息类，防止撤回消息看不到

添加数据库来缓存消息

加上NotificationListenerService服务，另一种方式监听消息

解决ticwatch不兼容问题。