package com.hzmy.zm.warehouse.ui;

import android.app.Activity;
import android.app.Application;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.alibaba.mobileim.YWChannel;
import com.alibaba.mobileim.YWConstants;
import com.alibaba.mobileim.channel.event.IWxCallback;
import com.alibaba.mobileim.channel.util.YWLog;
import com.alibaba.mobileim.login.YWLoginCode;
import com.alibaba.mobileim.login.YWLoginState;
import com.alibaba.mobileim.utility.IMPrefsTools;
import com.alibaba.tcms.env.YWEnvType;
import com.hzmy.zm.warehouse.R;
import com.hzmy.zm.warehouse.app.AppContext;
import com.hzmy.zm.warehouse.constant.SPName;
import com.hzmy.zm.warehouse.third_party_libs.a_li_yun_wang.helper.LoginSampleHelper;
import com.hzmy.zm.warehouse.utils.LogUtils;
import com.hzmy.zm.warehouse.utils.SPUtils;
import com.hzmy.zm.warehouse.utils.ToastUtils;

import java.util.Random;

public class LoginActivity extends Activity
{
    private static final int GUEST = 1;
    private static final String USER_ID = "userId";
    private static final String PASSWORD = "password";
    private static final String APPKEY = "appkey";
    private static final String TAG = LoginActivity.class.getSimpleName();
    private LoginSampleHelper loginHelper;
    private EditText userIdView;
    private EditText passwordView;
//    private EditText appKeyView;
    private ProgressBar progressBar;
    private Button loginButton;
    private Handler handler = new Handler(Looper.getMainLooper());
    private ImageView lg;
    private int mClickCount = 0;
    private Context mContext;


    public static final String AUTO_LOGIN_STATE_ACTION = "com.openim.autoLoginStateActionn";
    private BroadcastReceiver mAutoLoginStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final int state = intent.getIntExtra("state", -1);
            YWLog.i(TAG, "mAutoLoginStateReceiver, loginState = " + state);
            if (state == -1){
                return;
            }
            handleAutoLoginState(state);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mContext = LoginActivity.this;

        initView();
    }

    private void initView()
    {
        loginHelper = LoginSampleHelper.getInstance();
        userIdView = (EditText) findViewById(R.id.account);
        passwordView = (EditText) findViewById(R.id.password);
//        appKeyView = (EditText) findViewById(R.id.appkey);
//        appKeyView.setVisibility(View.VISIBLE);
        progressBar = (ProgressBar) findViewById(R.id.login_progress);

        //等登陆了APP后，获取登陆的用户，密码，再次登陆阿里云旺
        initLoginInfo();

        init(userIdView.getText().toString(), AppContext.getALIYUNWANGAPPKEY());

        myRegisterReceiver();

        //一些其它的初始化
        //自定义消息处理初始化(如果不需要自定义消息，则可以省去)
//				CustomMessageSampleHelper.initHandler();

        loginButton = (Button) findViewById(R.id.login);


        Bitmap logo = BitmapFactory.decodeResource(getResources(),
                R.mipmap.home_grid_view_item1);
        lg = (ImageView) findViewById(R.id.logo);
        lg.setImageBitmap(logo);


        userIdView.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if (TextUtils.isEmpty(s))
                {
                    passwordView.setText("");
                }

            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });

        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //判断当前网络状态，若当前无网络则提示用户无网络
                if (YWChannel.getInstance().getNetWorkState().isNetWorkNull())
                {
                    Toast.makeText(LoginActivity.this, "网络已断开，请稍后再试哦~", Toast.LENGTH_SHORT).show();
                    return;
                }
                //点击一次后，让登陆按钮不可点击
                loginButton.setClickable(false);
                final Editable userId = userIdView.getText();
                final Editable password = passwordView.getText();
//                final Editable appKey = appKeyView.getText();
                if (userId == null || userId.toString().length() == 0)
                {
                    Toast.makeText(mContext, "用户名不能为空", Toast.LENGTH_SHORT).show();
                    loginButton.setClickable(true);
                    return;
                }
                if (password == null || password.toString().length() == 0)
                {
                    Toast.makeText(mContext, "密码不能为空", Toast.LENGTH_SHORT).show();
                    loginButton.setClickable(true);
                    return;
                }

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(userIdView.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(passwordView.getWindowToken(), 0);


                init(userId.toString(), AppContext.getALIYUNWANGAPPKEY());

                progressBar.setVisibility(View.VISIBLE);

                loginHelper.login_Sample(userId.toString(), password.toString(), AppContext.getALIYUNWANGAPPKEY(), new IWxCallback()
                {

                    @Override
                    public void onSuccess(Object... arg0)
                    {
                        saveLoginInfoToLocal(userId.toString(), password.toString(), AppContext.getALIYUNWANGAPPKEY());

                        loginButton.setClickable(true);
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(LoginActivity.this, "登录成功",
                                Toast.LENGTH_SHORT).show();
                        YWLog.i(TAG, "login success!");
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                        // TODO: 2016/8/11  给MainActivity添加LOGIN_SUCCESS判断
//                        intent.putExtra(MainActivity.LOGIN_SUCCESS, "loginSuccess");

                        LoginActivity.this.startActivity(intent);
                        LoginActivity.this.finish();
//						YWIMKit mKit = LoginSampleHelper.getInstance().getIMKit();
//						EServiceContact contact = new EServiceContact("qn店铺测试账号001:找鱼");
//						LoginActivity.this.startActivity(mKit.getChattingActivityIntent(contact));
//                        mockConversationForDemo();
                    }

                    @Override
                    public void onProgress(int arg0)
                    {

                    }

                    @Override
                    public void onError(int errorCode, String errorMessage)
                    {
                        progressBar.setVisibility(View.GONE);



                        if (errorCode == YWLoginCode.LOGON_FAIL_INVALIDUSER)
                        { //若用户不存在，则提示使用游客方式登陆
                            showDialog(GUEST);
                            loginButton.setClickable(true);
                            ToastUtils.show(mContext, "用户不存在");
                        }
                        else
                        {
                            loginButton.setClickable(true);
                            ToastUtils.show(mContext, "登陆失败");
                            YWLog.w(TAG, "登录失败，错误码：" + errorCode + "  错误信息：" + errorMessage);
                        }
                    }
                });
            }


        });

    }

    private void init(String userId, String appKey)
    {
        //初始化imkit
        LoginSampleHelper.getInstance().initIMKit(userId, appKey);
        //自定义头像和昵称回调初始化(如果不需要自定义头像和昵称，则可以省去)
        // TODO: 2016/8/11   自定义头像和昵称
//        UserProfileSampleHelper.initProfileCallback();

        // TODO: 2016/8/11  通知栏状态的变化
        //通知栏相关的初始化
//        NotificationInitSampleHelper.init();
    }

    /**
     * 保存到本地
     *
     * @param userId
     * @param password
     * @param appkey
     */
    private void saveLoginInfoToLocal(String userId, String password, String appkey)
    {
        SPUtils.put(mContext, SPName.getUserId(), userId);
        SPUtils.put(mContext, SPName.getPassword(), password);
        SPUtils.put(mContext, SPName.getAppkey(), appkey);
    }


    //暂时留着
    private void initLoginInfo()
    {
        userIdView.setText("testpro2");
        passwordView.setText("taobao1234");

//        //读取登录成功后保存的用户名、密码和appkey
//        String localId = IMPrefsTools.getStringPrefs(LoginActivity.this, USER_ID, "");
//        if (!TextUtils.isEmpty(localId))
//        {
//            userIdView.setText("testpro2");
//            String localPassword = IMPrefsTools.getStringPrefs(LoginActivity.this, PASSWORD, "");
//            if (!TextUtils.isEmpty(localPassword))
//            {
//                passwordView.setText("taobao1234");
//            }
//            String localAppKey = IMPrefsTools.getStringPrefs(LoginActivity.this, APPKEY, "");
////            if (!TextUtils.isEmpty(localAppKey))
////            {
////                appKeyView.setText(localAppKey);
////            }
//        }
    }

    private void myRegisterReceiver(){
        IntentFilter filter = new IntentFilter();
        filter.addAction(AUTO_LOGIN_STATE_ACTION);
        LocalBroadcastManager.getInstance(YWChannel.getApplication()).registerReceiver(mAutoLoginStateReceiver, filter);
    }

    private void myUnregisterReceiver(){
        LocalBroadcastManager.getInstance(YWChannel.getApplication()).unregisterReceiver(mAutoLoginStateReceiver);
    }

    private void handleAutoLoginState(int loginState){
        if (loginState == YWLoginState.logining.getValue()){
            if (progressBar.getVisibility() != View.VISIBLE){
                progressBar.setVisibility(View.VISIBLE);
            }
            loginButton.setClickable(false);
        }else if (loginState == YWLoginState.success.getValue()){
            loginButton.setClickable(true);
            progressBar.setVisibility(View.GONE);
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            LoginActivity.this.startActivity(intent);
            LoginActivity.this.finish();
        } else {
//            这里需要注释掉，否则无法实现多账号登陆
//            YWIMKit ywimKit = LoginSampleHelper.getInstance().getIMKit();
//            if (ywimKit != null) {
//                if (ywimKit.getIMCore().getLoginState() == YWLoginState.success) {
//                    loginButton.setClickable(true);
//                    progressBar.setVisibility(View.GONE);
//                    Intent intent = new Intent(LoginActivity.this, FragmentTabs.class);
//                    LoginActivity.this.startActivity(intent);
//                    LoginActivity.this.finish();
//                    return;
//                }
//            }
//            //当作失败处理
            progressBar.setVisibility(View.GONE);
            loginButton.setClickable(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myUnregisterReceiver();
    }

}