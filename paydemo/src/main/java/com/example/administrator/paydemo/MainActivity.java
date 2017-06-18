package com.example.administrator.paydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.alipay.sdk.app.PayTask;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final String PID = "2088121427784129";

    public static final String 收款 = "zhifubao@whunf.com.cn";

//    //上传公钥到支付宝后台，生成支付宝公钥
//    public static final String 支付宝公钥 = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

    public static final String PKCS8私钥 = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMve52ezDiNu9tlpbcUZK1LMfXtt+uZv737L4R8PWDMWAFJnc1UWXe2yW0owaMej1Ltfc+Io25oBflQ0HkJtvZcW/pQS49BxPFHrFSYyMZ85zuHKmiL9X9Vg1aS3Nycq0z4n6/FEPAI7taLHC4GdypWkrvFso0FezpyQuX8d0RMdAgMBAAECgYBLVjG5FmfRHD5IFTelMCncTQjJxdiDszWTa4/jRG5ZYnX3/ZaXCM7o+ZHWlpEBxT7lht5x4ptUC909DI1qCteWBtbjLdxkhDJLqFqdAfrLFFWukaH64q/y4l//7dRDudxpK0P94f7k57OkQb1CZz2e2bimJa9S/AD+QNFZsFncGQJBAOrRcPaOSUjYde4wPp5lXOT1tlCYYxW2M7LOK3NkOwodOGBEB5MUOTq0KYoxOEsNZvrQcfthKVzxyBPwthy6E2MCQQDeQs6JnjUvzVW2bWZKwb2dlmVjzXucieyUey1DhqDMzL0UEca+3i7LAU4NdYEcHBA/iJ+NcfNa+TLteL0lq0d/AkEA0ooxl8+h47+5lXQKYSkPLnclHYRUqx3voq16hf7jdMEYUDAO/o/OdFUfPbm9+IUuxUnqOPM4DA75Tu+vyXgJcwJAVcEk+0gFcWoiNf7Dxvg1dsX+XKF9ngpBM5DLCBC7ngkossOhESecmkromJzLsdLKGhlWpjZfFKytj55ULGKHdQJBAKHpthAgr7Evu5oM081kzi5FaHxgVCCmfDx2DGBL/UFIpJkMf/1AvVQGJHyu/lRVricK8eeUPwIHrvoYceBgxQ0=";


    EditText goodName, goodValue, goodSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goodName = (EditText) findViewById(R.id.et_goodName);
        goodSign = (EditText) findViewById(R.id.et_goodSign);
        goodValue = (EditText) findViewById(R.id.et_goodValue);
    }

    public void pay(View v) {
        String name = goodName.getText().toString().trim();
        String sign = goodSign.getText().toString().trim();
        String value = goodValue.getText().toString().trim();
        //生成订单
        String 订单信息 = getOrderInfo(name, sign, value);
        Log.e("TAG", "---------->>订单信息： " + 订单信息);
        String 签名后的订单 = SignUtils.sign(订单信息, PKCS8私钥);
        Log.e("TAG", "---------->>签名后的订单： " + 签名后的订单);
        try {
            String 转utf8的订单 = URLEncoder.encode(签名后的订单, "UTF-8");
            final String 最终字符串 = 订单信息 + "&sign=\"" + 转utf8的订单 + "\"&sign_type=\"RSA\"";
            Log.e("TAG", "---------->>最终字符串： " + 最终字符串);
            //最终字符串由服务器生成
            //请求服务器的接口   http://baidu.com/getOrderInfo   参数 key加密字段  商品的id 返回订单信息
            //告诉服务器开始调用支付宝
            new Thread() {
                @Override
                public void run() {
                    PayTask task = new PayTask(MainActivity.this);
                    //第一个由服务器返回的字段，第二个进度条
                    String result = task.pay(最终字符串, true);
                    Log.e("TAG", "---------->>result： " + result);
                    //告诉服务器，调用完毕，支付成功还是失败
                }
            }.start();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Log.e("TAG", "---转换失败");
        }
    }

    //服务器做的
    //拼接订单
    private String getOrderInfo(String subject, String body, String price) {
        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + PID + "\"";
        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + 收款 + "\"";
        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) + "\"";
        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";
        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";
        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";
        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + "https://www.baidu.com" + "\"";
        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";
        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";
        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";
        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";
        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";
        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";
        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";
        return orderInfo;
    }

}
