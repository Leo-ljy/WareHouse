package com.hzmy.zm.warehouse.third_party_libs.a_li_yun_wang.helper;

import android.app.Application;
import android.text.TextUtils;

import com.alibaba.mobileim.IYWLoginService;
import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWConstants;
import com.alibaba.mobileim.YWIMKit;
import com.alibaba.mobileim.YWLoginParam;
import com.alibaba.mobileim.channel.LoginParam;
import com.alibaba.mobileim.channel.event.IWxCallback;
import com.alibaba.mobileim.contact.IYWContactCacheUpdateListener;
import com.alibaba.mobileim.contact.IYWContactOperateNotifyListener;
import com.alibaba.mobileim.login.YWPwdType;
import com.alibaba.mobileim.utility.IMAutoLoginInfoStoreUtil;
import com.alibaba.tcms.env.EnvManager;
import com.alibaba.tcms.env.TcmsEnvType;
import com.alibaba.tcms.env.YWEnvManager;
import com.alibaba.wxlib.util.SysUtil;
import com.hzmy.zm.warehouse.app.AppContext;
import com.hzmy.zm.warehouse.constant.SPName;
import com.hzmy.zm.warehouse.third_party_libs.a_li_yun_wang.contact.ContactCacheUpdateListenerImpl;
import com.hzmy.zm.warehouse.third_party_libs.a_li_yun_wang.contact.ContactOperateNotifyListenerImpl;
import com.hzmy.zm.warehouse.ui.MainActivity;
import com.hzmy.zm.warehouse.utils.SPUtils;

/**
 * SDK 初始化和登录
 *
 * @author jing.huai
 */
public class LoginSampleHelper
{
    private static LoginSampleHelper sInstance = new LoginSampleHelper();
    private Application mApp;
    // openIM UI解决方案提供的相关API，创建成功后，保存为全局变量使用
    private YWIMKit mIMKit;


    public static LoginSampleHelper getInstance() {
        return sInstance;
    }

    public YWIMKit getIMKit() {
        return mIMKit;
    }


    private IYWContactOperateNotifyListener mContactOperateNotifyListener = new ContactOperateNotifyListenerImpl();

    private IYWContactCacheUpdateListener mContactCacheUpdateListener = new ContactCacheUpdateListenerImpl();

    /**
     * 联系人相关操作通知回调，SDK使用方可以实现此接口来接收联系人操作通知的监听
     * 所有方法都在UI线程调用
     * SDK会自动处理这些事件，一般情况下，用户不需要监听这个事件
     * @author shuheng
     *
     */
    private void addContactListeners(){
        //添加联系人通知和更新监听，先删除再添加，以免多次添加该监听
        removeContactListeners();
        if(mIMKit!=null){
            if(mContactOperateNotifyListener!=null)
                mIMKit.getContactService().addContactOperateNotifyListener(mContactOperateNotifyListener);
            if(mContactCacheUpdateListener!=null)
                mIMKit.getContactService().addContactCacheUpdateListener(mContactCacheUpdateListener);

        }
    }

    private void removeContactListeners(){
        if(mIMKit!=null){
            if(mContactOperateNotifyListener!=null)
                mIMKit.getContactService().removeContactOperateNotifyListener(mContactOperateNotifyListener);
            if(mContactCacheUpdateListener!=null)
                mIMKit.getContactService().removeContactCacheUpdateListener(mContactCacheUpdateListener);

        }
    }


    /**
     * 初始化SDK
     *
     * @param context
     */
    public void initSDK_Sample(Application context)
    {
        mApp = context;
        //初始化IMKit
        //登陆后，从本地获取
        final String userId = (String) SPUtils.get(context,  SPName.getUserId(), "");
        final String appkey = (String) SPUtils.get(context, SPName.getAppkey(), AppContext.getALIYUNWANGAPPKEY());
        //默认的key
        if(SysUtil.isMainProcess()){
            YWAPI.init(mApp, appkey);
        }

        if (!TextUtils.isEmpty(userId) && !TextUtils.isEmpty(appkey)){
            LoginSampleHelper.getInstance().initIMKit(userId, appkey);
        }

    }

    public void initIMKit(String userId, String appKey) {
//      获取SDK对象实现
        mIMKit = YWAPI.getIMKitInstance(userId, appKey);


//         TODO: 2016/8/11   添加消息监听
//        addPushMessageListener();


//        添加联系人通知和更新监听
//        在初始化后、登录前添加监听，离线的联系人系统消息才能触发监听器
        addContactListeners();

    }

    //------------------请特别注意，OpenIMSDK会自动对所有输入的用户ID转成小写处理-------------------
    //所以开发者在注册用户信息时，尽量用小写
    public void login_Sample(String userId, String password, String appKey,
                             IWxCallback callback) {

        if (mIMKit == null) {
            return;
        }


        YWLoginParam loginParam = YWLoginParam.createLoginParam(userId,
                password);
        if (TextUtils.isEmpty(appKey) || appKey.equals(YWConstants.YWSDKAppKey)
            || appKey.equals(YWConstants.YWSDKAppKeyCnHupan)) {
            loginParam.setServerType(LoginParam.ACCOUNTTYPE_WANGXIN);
            loginParam.setPwdType(YWPwdType.pwd);
        }
        // openIM SDK提供的登录服务
        IYWLoginService mLoginService = mIMKit.getLoginService();

        mLoginService.login(loginParam, callback);
    }


}
