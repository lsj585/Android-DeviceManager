# Android-DeviceManager
### 1.2 方案

### 1.2.1 通过系统签名，获取最高系统权限

我们也知道，如果要取得一个设备的最高权限就要进行ROOT。因为ROOT后的设备可以支持所有LINUX下的指令。此时不管是adb命令还是用 linux 原生的shell脚本去执行，都可以达到很好的预期效果。然而很多用户并不乐于设备被ROOT，而且要为了一个软件去ROOT也是一件很麻烦的事。这就给实现上述功能加大了难度。

如果不用ROOT，那么用什么？对了，使用Android自带的系统签名和证书。并且将自己的应用的shareId改为系统应用的shareId。虽然针对原生的Android操作系统还可以关联，但是经过国内外产商定制后的Android系统，这些系统证书和签名都和产商关联，已经不在使用原生Android系统内置的签名和应用证书。这就表明该方案很难实现上述的能力。

### 1.2.2 厂商合作

针对上文提出的功能，如果要实现该功能，就需要类似像ND ROM这种对接的方式，进行和系统开发产商的合作。

针对产商的系统调研，目前可确定产商的支持能力：  
1.三星系列设备-knox方案  
2.华为设备设备-华为MDM方案即华为BYOD。 

针对以上产商方案的介绍，后续会出单独的文章进行讲解。本篇着重讲解Android for work。

### 1.2.3 安卓系统原生支撑能力

谷歌着手企业应用并在企业移动应用方面提出了G suit解决方案。通过调研发现，G suit利用的是Android 原生Launcher模块下的android for work模式进行对应用的托管。


### 2 内容提要

本系列包括以下几篇文章：

* 三星移动应用SDK预研方案
* 华为移动应用SDK预研方案
* Android for Work
* DevicePolicyManager源码研究
* Android多用户管理
* Google Play for Work
* 隐藏功能的调用

本篇仅围绕Android for work进行阐述。

## 3 关于Android For Work

### 3.1 简介

Android for Work是由google主导开发的一套旨在支持Android在企业中运用的一套方案。这套方案可以实现在同一台设备上同时支持工作应用和个人应用。

该方案有以下几个优点：

* 1.数据安全 

工作数据通过work profile和个人数据隔离出来，并且受到work profile的保护, IT可以部署工作数据被保护的策略

* 2.应用安全

work profile里面的app是通过Google play for work部署的。IT可以阻止 
安装未知来源的app，并且可以配置app.

3.设备安全

支持Android for Work的设备受到磁盘加密，锁屏，远程attestation services的保护。如果hardware-backed keystore存在的话，也受到它的保护。

### 3.2 Android For Work的构成

Android For Work主要由四大部分构成：

* 设备管理API

 在Android中提供一套API 来支持Android For Work。该API为DevicePolicyManager(设备管理策略类)以及UserManager（多用户管理）。API主要实现了以下几个功能
  ：  
  1.允许/不允许开启/关闭蓝牙  
  2.允许/不允许开启/关闭Usb  
  3.允许/不允许加载SD卡  
  4.允许/不允许USB通讯  
  5.恢复出产设置  
  6.重启手机  
  7.允许/不允许打开相机  
  8.允许/不允许开启移动网络  
  9.允许/不允许卸载应用程序  
  10.锁屏  
  11.解锁   
  12.修改锁屏密码  
  13.磁盘加密  
  14.应用隐藏/显示  
  15.允许/阻止下载文件
  
* 企业移动管理（EMM）
* Google Play for Work
* 安全证书

总结，从上面来看，Android For work所提供的API只能满足上述功能需求中的一小部分。

### 3.3 API版本

仅支持版本在android level 18及以上


## 4 技术实现

现在，就来一窥Android For Work的运行模式吧。

### 4.1.激活设备管理

1.Android For Work要求用户必须要是设备用户，才能进行对设备功能开关、应用功能列表进行管理。要实现android for work模式，必须要确保现有应用运行在设备拥有者模式下

代码如下：

    //注册组件名称，用于接收设备功能激活成功的广播
    ComponentName componentName= new ComponentName(context, AdminReciver.class);
    //获取devicePolicyManager类
    DevicePolicyManager devicePolicyManager=  (DevicePolicyManager)context.getSystemService(Context.DEVICE_POLICY_SERVICE);
    //判断设备管理功能是否开启
    if (!devicePolicyManager.isAdminActive(getComponentName())) {
            //激活
            Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, getComponentName());
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "Adding app as an admin to test Knox");
            context.startActivity(intent);
        }

2.在AndroidManifest.xml中注册广播接收者。

     <!-- 设备管理 -->
        <receiver android:name="com.nd.adhoc.dmsdk.revicer.AdminReciver"
            android:label="@string/device"
            android:permission="android.permission.BIND_DEVICE_ADMIN">
            <meta-data android:name="android.app.device_admin"
                android:resource="@xml/device_admin_receiver" />
            <intent-filter>
                <action
                    android:name="android.app.action.DEVICE_ADMIN_ENABLED" />
            </intent-filter>
        </receiver>

3.在res/xml目录下创建一个device_admin.xml，该文件是配置policy的一些执行策略，比如<wipedata>

        <device-admin xmlns:android="http://schemas.android.com/apk/res/android">
        <uses-policies>
            <!--<wipe-data/> 恢复出产
                <watch-login /> 监听屏幕解锁尝试次数
                <encrypted-storage /> 存储设备加密
                <disable-keyguard-features /> 禁用输入法
                <disable-camera /> 禁用相机
            -->
        </uses-policies>
    </device-admin>

4.注册设备管理激活监听者

    public class AdminReciver extends DeviceAdminReceiver {


    @Override
    public DevicePolicyManager getManager(Context context) {
        return super.getManager(context);
    }

    @Override
    public ComponentName getWho(Context context) {
        return super.getWho(context);
    }

    @Override
    public void onDisabled(Context context, Intent intent) {
        //设备管理功能关闭
        super.onDisabled(context, intent);
    }

    @Override
    public CharSequence onDisableRequested(Context context, Intent intent) {
        return super.onDisableRequested(context, intent);
    }

    @Override
    public void onEnabled(Context context, Intent intent) {
        //设备管理功能激活成功
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(ReciverConstants.DEVICE_MANAGER_ACTIVE_ACTION));
    }

    @Override
    public void onPasswordChanged(Context context, Intent intent, UserHandle user) {
        super.onPasswordChanged(context, intent, user);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }

    @Override
    public IBinder peekService(Context myContext, Intent service) {
        return super.peekService(myContext, service);
    }
        
    }

### 4.2.常见的API方法调用

激活设备功能管理后，此时的应用进程的用户角色已切换至设备拥有者了。这时候就可以使用DevicePolicyManager来实现自己想要的功能了。

在DevicePolicyManager中的API要实现上文的功能需求，则调用以下API即可：
    
1.恢复出产设置

    void wipeData（int flags）

2.锁定设置
    
    void lockNow（int flags）

3.阻止XXX策略
    
    void addUserRestriction (ComponentName admin, 
                String key)
  代码如下：
  
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                devicePolicyManager.addUserRestriction(getComponentName(),UserManager.DISALLOW_MOUNT_PHYSICAL_MEDIA);
            }
            
通过源码发现，DevicePolicyManager所关联的KEY都由UserManager类提供。该KEY所提供的能力如下：

    1.DISALLOW_MODIFY_ACCOUNTS
    修改账号信息
    2.DISALLOW_USB_FILE_TRANSFER
    USB文件传输
    3.DISALLOW_DEBUGGING_FEATURES
    4.DISALLOW_CONFIG_WIFI
    WIFI配置
    5.DISALLOW_CONFIG_BLUETOOTH
    蓝牙配置
    6.DISALLOW_INSTALL_UNKNOWN_SOURCES
    未知资源安装
    7.DISALLOW_CONFIG_MOBILE_NETWORKS
    8.启用自动系统更新。设备将自动下载并应用 OTA 更新。
    9.设置 LockTask 允许的软件包。
    10.将设备恢复出厂设置。
    11.停用锁屏功能。
    12.阻止设置密码/指纹。
    13.控制 WLAN 网络更改。与用户限制DISALLOW_CONFIG_WIFI 一起使用时，设备所有者应用可以控制对 WLAN * 网络选择设置的访问权限。
    15.重新启动设备。
    16.通过 PackageInstaller 安装软件包。
    17.设置一组已加入白名单
    Settings.Global、Settings.Secure 和 Settings.System 设置。
    18.卸载软件包。

3.允许XXX策略
    
    void clearUserRestriction (ComponentName admin,
                String key)
  案例如下：
  
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                devicePolicyManager.clearUserRestriction(getComponentName(),UserManager.DISALLOW_MOUNT_PHYSICAL_MEDIA);
            }else{

            }
### 4.3.注意事项
当使用到设备管理权限中的最高策略时，需要将策略名称配置在res/xml的device_admin.xml中。至于什么情况下配置，需要遵从DevicePolicyManager中的方法API提示。例如以下API提示：

    wipeData

    
    void wipeData (int flags, 
                CharSequence reason)
                
Ask that all user data be wiped. If called as a secondary user, the user will be removed and other users will remain unaffected, the provided reason for wiping data can be shown to user. Calling from the primary user will cause the device to reboot, erasing all device data - including all the secondary users and their data - while booting up. In this case, we don't show the reason to the user since the device would be factory reset.

The calling device admin must have requested USES_POLICY_WIPE_DATA to be able to call this method; if it has not, a security exception will be thrown.

上述提示USES_POLICY_WIPE_DATA 表明，你需要配置<wipe-data>节点到你的use-poicy节点下。如果不配置，就会抛出SecurityException。


## 5 常见误区和源码分析
在Android的DevicePolicyManager是通过调用一个IDevicePolicyManager的AIDL实现的。通过调研发现，该类实现了一些DevicePolicyManager没有对外开放的API。那么要实现该类，查看网络上的方案是要通过反射来读取:

### 5.1.网络上的方案

通过反射获取服务、注册权限、实现一些未开放的API功能：
    
    
    public class LockActivity extends Activity {  
    IDevicePolicyManager mService;  
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.main);  
    }  
    //锁屏   
    public void lock(View view){  
        try {  
            //通过反射获取到sdk隐藏的服务   
            Method method = Class.forName("android.os.ServiceManager")  
                    .getMethod("getService", String.class);  
            IBinder binder = (IBinder) method.invoke(null,//激活服务  
                    new Object[] { Context.DEVICE_POLICY_SERVICE });  
             mService = IDevicePolicyManager.Stub.asInterface(binder);  
               
             //定义组件的名字   
             ComponentName mAdminName = new ComponentName(this, MyAdmin.class);  
              
             //注册权限  
             if (mService != null) {  
                    //判断自定义的广播接受者 是不是被注册成 deviceadmin的权限   
                    if (!mService.isAdminActive(mAdminName)) {  
                                Intent intent = new Intent(  
                                DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);  
                                intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN,  
                                        mAdminName);  
                                startActivity(intent);  
                            }  
                    //调用服务实现锁屏   
                    mService.lockNow();  
                    //设置解锁密码  
                    mService.resetPassword("123", 0);  
             }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }   
    }}  
    
### 5.2.纠正误区

网络上的方案是说利用反射取到DevicePolicyManager中未开放的API。然而，通过源码发现，IDevicePolicyManager.aidl实现的类是由DevicePolicyService来实现的。该Service服务是在zygote启动时创建SystemServer被创建的。从DevicePolicyService中发现，该接口所实现的方法都是调用一个UserManagerService来实现接口。通过UserManagerService找到了IDevicPolicyManager所对应的实现方法。并由此发现了UserManager。

就从DevicePolicy实现允许/阻止打开相机的方法实现来看吧。该方法首先要拿到当前用户的id,之后再判断当前用户是否是设备用户。如果是设备用户则调用多用户管理-UserManager来管理用户对设备、APP的策略管理。

步骤如下：

1.获取当前用户的ID为：

    int userid=Binder.getCallingUid();
    
2.获取当前用户：

    UserHandle userHandler= UserHandle.getUserId(userid)；
    
3.判断当前用户是否是设备用户：
    
    private ActiveAdmin getActiveAdminWithPolicyForUidLocked(ComponentName who, int reqPolicy,
        int uid) {
    // Try to find an admin which can use reqPolicy
    final int userId = UserHandle.getUserId(uid);
    final DevicePolicyData policy = getUserData(userId);
    if (who != null) {
        ActiveAdmin admin = policy.mAdminMap.get(who);
        if (admin == null) {
            throw new SecurityException("No active admin " + who);
        }
        if (admin.getUid() != uid) {
            throw new SecurityException("Admin " + who + " is not owned by uid " + uid);
        }
        if (isActiveAdminWithPolicyForUserLocked(admin, reqPolicy, userId)) {
            return admin;
        }
    } else {
        for (ActiveAdmin admin : policy.mAdminList) {
            if (admin.getUid() == uid && isActiveAdminWithPolicyForUserLocked(admin, reqPolicy,
                    userId)) {
                return admin;
            }
        }
    }

    return null;
    }

    ActiveAdmin getActiveAdminForCallerLocked(ComponentName who, int reqPolicy)
        throws SecurityException {
    final int callingUid = mInjector.binderGetCallingUid();

    ActiveAdmin result = getActiveAdminWithPolicyForUidLocked(who, reqPolicy, callingUid);
    if (result != null) {
        return result;
    }

    if (who != null) {
        final int userId = UserHandle.getUserId(callingUid);
        final DevicePolicyData policy = getUserData(userId);
        ActiveAdmin admin = policy.mAdminMap.get(who);
        if (reqPolicy == DeviceAdminInfo.USES_POLICY_DEVICE_OWNER) {
            throw new SecurityException("Admin " + admin.info.getComponent()
                     + " does not own the device");
        }
        if (reqPolicy == DeviceAdminInfo.USES_POLICY_PROFILE_OWNER) {
            throw new SecurityException("Admin " + admin.info.getComponent()
                    + " does not own the profile");
        }
        throw new SecurityException("Admin " + admin.info.getComponent()
                + " did not specify uses-policy for: "
                + admin.info.getTagForPolicy(reqPolicy));
    } else {
        throw new SecurityException("No active admin owned by uid "
                + mInjector.binderGetCallingUid() + " for policy #" + reqPolicy);
    }}
    
4.从源码中UserManager中找到添加设备管控功能的方法

    /**
     * Called by a profile or device owner to get user restrictions set with
     * {@link #setUserRestrictions(ComponentName, String)}.
     * <p>
     * The target user may have more restrictions set by the system or other device owner / profile
     * owner. To get all the user restrictions currently set, use
     * {@link UserManager#getUserRestrictions()}.
     *
     * @param admin Which {@link DeviceAdminReceiver} this request is associated with.
     * @throws SecurityException if {@code admin} is not a device or profile owner.
     */
    public @NonNull Bundle setUserRestrictions(@NonNull ComponentName admin) {
        throwIfParentInstance("setUserRestrictions");
        Bundle ret = null;
        if (mService != null) {
            try {
                ret = mService.getUserRestrictions(admin);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        return ret == null ? new Bundle() : ret;
    }
5.移除设备管控功能的方法

    public void clearUserRestriction(@NonNull ComponentName admin, String key) {
        throwIfParentInstance("clearUserRestriction");
        if (mService != null) {
            try {
                mService.setUserRestriction(admin, key, false);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

通过对源码的研究，发现DevicePolicyManager仅仅只是一个对外的接口，其真正实现的都是由UserManager来处理。这样就不需要用反射就可以实现上述功能了。

### 5.3 总结
从上述可以看出，当DevicePolicyManager执行某个策略时，会通过UserManager将该策略更新到一个xxx.xml中。该该文件位于/data/system/users目录下，配置文件如下：
    
    <user id="10" serialNumber="10" flags="4" created="1493886837832" lastLoggedIn="0">
    <name>Owner</name>
    <restrictions no_install_apps="false" no_install_unknown_sources="true" no_config_credentials="true" no_outgoing_calls="false" no_sms="false" />
    </user>
步骤如下：

    1.添加策略；
    2.获取当前用户，判断当前用户是否是设备用户；
    3.读取设备用户所对应的xxx.xml文件，获取限制条件；
    4.修改restrictions中的节点，并根据传入的参数值修改该限制节点属性。
