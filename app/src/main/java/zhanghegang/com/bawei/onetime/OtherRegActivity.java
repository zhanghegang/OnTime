package zhanghegang.com.bawei.onetime;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhanghegang.com.bawei.onetime.base.BaseActivity;
import zhanghegang.com.bawei.onetime.base.BasePresenter;

public class OtherRegActivity extends BaseActivity {

    @BindView(R.id.tv_add_acount)
    TextView tvAddAcount;
    @BindView(R.id.iv_other_middle)
    ImageView ivOtherMiddle;
    @BindView(R.id.et_user)
    EditText etUser;
    @BindView(R.id.et_pass)
    EditText etPass;
    @BindView(R.id.ll_edit)
    LinearLayout llEdit;
    @BindView(R.id.btn_other_reg)
    Button btnOtherReg;
    @BindView(R.id.tv_forget_pass)
    TextView tvForgetPass;
    @BindView(R.id.tv_auto_reg)
    TextView tv_auto_reg;

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_other_reg;
    }

    @Override
    public void initView() {
        setStatus(true);
    }

    @Override
    public void ionDestroy() {

    }




    @OnClick({R.id.tv_add_acount, R.id.btn_other_reg, R.id.tv_forget_pass, R.id.tv_auto_reg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_add_acount:
                break;
            case R.id.btn_other_reg:
                break;
            case R.id.tv_forget_pass:
                break;
            case R.id.tv_auto_reg:
                start(MainActivity.class,false);
                break;
        }
    }
}
