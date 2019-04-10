package com.netmanagerdemo.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.netmanagerdemo.R;
import com.netmanagerdemo.net.LogUtil;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by hluo on 2019/4/10.
 */
public abstract class BaseActivity<T extends BasePresenter> extends RxAppCompatActivity implements EasyPermissions.PermissionCallbacks,IBaseView {

    private ProgressDialog loadingDialog = null;
    private Unbinder unBinder;

    @Override
    public void showLoading() {
        showLoadingDialog();
    }

    @Override
    public void hideLoading() {
        hideLoadingDialog();
    }

    @Override
    public void showError(String msg) {

    }

    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        unBinder = ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent != null)
            getIntent(intent);
        initView();
        initData();
        initListener();
    }


    /**
     * 重写要申请权限的Activity或者Fragment的onRequestPermissionsResult()方法，
     * 在里面调用EasyPermissions.onRequestPermissionsResult()，实现回调。
     *
     * @param requestCode  权限请求的识别码
     * @param permissions  申请的权限
     * @param grantResults 授权结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    /**
     * 当权限被成功申请的时候执行回调
     *
     * @param requestCode 权限请求的识别码
     * @param perms       申请的权限的名字
     */
    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    /**
     * 当权限申请失败的时候执行的回调
     *
     * @param requestCode 权限请求的识别码
     * @param perms       申请的权限的名字
     */
    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        StringBuffer sb = new StringBuffer();
        for (String str : perms){
            sb.append(str);
            sb.append("\n");
        }
        sb.replace(sb.length() - 2,sb.length(),"");
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)){

//            AppSettingsDialog.Builder(this)
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unBinder != null) {
            unBinder.unbind();
        }
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    /**
     * 显示进度框
     */
    protected void showLoadingDialog() {
        LogUtil.i("我是进度条显示了");
        createLoadingDialog();
        if (!loadingDialog.isShowing())
            loadingDialog.show();
    }

    /**
     * 创建LoadingDialog
     */
    private void createLoadingDialog() {
        if (loadingDialog == null) {
            loadingDialog = new ProgressDialog(this);
            loadingDialog.setContentView(R.layout.layout_loading_view);
            loadingDialog.setCancelable(true);
            loadingDialog.setCanceledOnTouchOutside(false);
        }
    }

    /**
     * 隐藏进度框
     */
    protected void hideLoadingDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }


    /**
     * 获取布局 Id
     * @return
     */
    protected abstract @LayoutRes
    int getLayoutId();
    /** 获取 Intent 数据 **/
    protected abstract void getIntent(Intent intent);

    /** 初始化View的代码写在这个方法中 */
    protected abstract void initView();

    /** 初始化监听器的代码写在这个方法中 */
    protected abstract void initListener();

    /** 初始数据的代码写在这个方法中，用于从服务器获取数据 */
    protected abstract void initData();

    protected abstract T createPresenter();
}
