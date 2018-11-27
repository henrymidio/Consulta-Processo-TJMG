package org.cptjmg.consultaprocesso;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;

import org.cptjmg.consultaprocesso.util.CommonUtils;

public abstract class BaseFragment extends Fragment {

    private ProgressDialog mProgressDialog;

    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(getContext());
    }

}
