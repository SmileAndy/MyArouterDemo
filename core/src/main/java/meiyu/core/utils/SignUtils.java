package meiyu.core.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.Log;


import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Random;

import meiyu.core.Constant;
import meiyu.core.application.MyApplication;
import meiyu.core.module.SignPackage;
import meiyu.core.sp.Prefs;
import meiyu.core.sp.SpEnum;

/**
 * Created by Administrator on 2017/4/5.
 */

public class SignUtils {
    private static String TAG = "SignUtils";
    public static String SIGN_SECRET = "yhjsHwAdnroid520";

    public static String getSignPackageString(@Nullable String[] valueList) {
        String signPackageString = "";
        long timeStamp = System.currentTimeMillis();
        SignPackage signPackage = new SignPackage(timeStamp, getNonStr(timeStamp), getSign(timeStamp, valueList), 1);
        signPackageString = JsonUtils.toJSONString(signPackage);
        return signPackageString;
    }

    public static String getSign(long timestamp, @Nullable String[] valueList) {
        StringBuilder sbResult = new StringBuilder();
        String uid = "";

        sbResult.append(timestamp)
                .append(comDeviceId())
                .append(uid);
        if (valueList != null) {
            for (int i = 0; i < valueList.length; i++) {
                if (valueList[i] != null) {
                    sbResult.append(valueList[i]);
                }
            }
        }
        return md5(sbResult.toString());
    }

    /*获取随机字符串*/
    public static String getNonStr(long timeStamp) {
        if (!Prefs.getString(SpEnum.SIGN_DYNAMIC_VALUE.name(), "").equals("")) {
            String dynamic = Prefs.getString(SpEnum.SIGN_DYNAMIC_VALUE.name(), "");
            int a = Integer.parseInt(dynamic.substring(0, 2));
            int b = Integer.parseInt(dynamic.substring(3, 5));
            int c = Integer.parseInt(dynamic.substring(5, 6));
            int d = Integer.parseInt(dynamic.substring(6, 7));
            int e = Integer.parseInt(dynamic.substring(7, 8));
            String timeString = String.valueOf(timeStamp);
            int size = timeString.length();
            int y = Integer.parseInt(timeString.substring(size - 4, size - 3));
            int x = Integer.parseInt(timeString.substring(size - 3, size - 2));
            int n = Integer.parseInt(timeString.substring(size - 2, size - 1));
            int m = Integer.parseInt(timeString.substring(size - 1, size));

            int unitSize = (c + 2 * d) * e;
            StringBuilder
                    sb1 = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            StringBuilder sb3 = new StringBuilder();
            StringBuilder sb4 = new StringBuilder();
            for (int i = 0; i < unitSize; i++) {
                switch ((2 * m + 3 * a) % 4) {
                    case 0:
                        sb1.append(Constant.NONSTR_UNIT1[new Random().nextInt(6)]);
                        break;
                    case 1:
                        sb1.append(Constant.NONSTR_UNIT2[new Random().nextInt(6)]);
                        break;
                    case 2:
                        sb1.append(Constant.NONSTR_UNIT3[new Random().nextInt(6)]);
                        break;
                    case 3:
                        sb1.append(Constant.NONSTR_UNIT4[new Random().nextInt(6)]);
                        break;
                }
                switch ((2 * n + 5 * b) % 4) {
                    case 0:
                        sb2.append(Constant.NONSTR_UNIT1[new Random().nextInt(6)]);
                        break;
                    case 1:
                        sb2.append(Constant.NONSTR_UNIT2[new Random().nextInt(6)]);
                        break;
                    case 2:
                        sb2.append(Constant.NONSTR_UNIT3[new Random().nextInt(6)]);
                        break;
                    case 3:
                        sb2.append(Constant.NONSTR_UNIT4[new Random().nextInt(6)]);
                        break;
                }
                switch ((5 * x + 6 * a) % 4) {
                    case 0:
                        sb3.append(Constant.NONSTR_UNIT1[new Random().nextInt(6)]);
                        break;
                    case 1:
                        sb3.append(Constant.NONSTR_UNIT2[new Random().nextInt(6)]);
                        break;
                    case 2:
                        sb3.append(Constant.NONSTR_UNIT3[new Random().nextInt(6)]);
                        break;
                    case 3:
                        sb3.append(Constant.NONSTR_UNIT4[new Random().nextInt(6)]);
                        break;
                }
                switch ((6 * y + 8 * b) % 4) {
                    case 0:
                        sb4.append(Constant.NONSTR_UNIT1[new Random().nextInt(6)]);
                        break;
                    case 1:
                        sb4.append(Constant.NONSTR_UNIT2[new Random().nextInt(6)]);
                        break;
                    case 2:
                        sb4.append(Constant.NONSTR_UNIT3[new Random().nextInt(6)]);
                        break;
                    case 3:
                        sb4.append(Constant.NONSTR_UNIT4[new Random().nextInt(6)]);
                        break;
                }
            }
            sb1.append(sb2)
                    .append(sb3)
                    .append(sb4);
            return sb1.toString();
        } else {
            ToastUtils.showToast("初始化失败，请杀死应用重新进入即可");
        }
        return "";
    }

    public static String md5(String string) {
        if (string == null) {
            return "";
        }
        if (string.equals("")) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getSignString(Map<String, String> map) {
        map.put("secret", SIGN_SECRET);

        StringBuilder sb = new StringBuilder();

        //升序排key
        List<String> list = new ArrayList<>(map.keySet());
        Collections.sort(list);

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                String key = list.get(i);
                String value = map.get(key);
                if (value != null) {
                    if (!value.equals("")) {
                        sb.append("&");
                        sb.append(key)
                                .append("=")
                                .append(value);
                    }
                }
            }
        }

        String mapString = sb.toString().trim().length() > 0 ? sb.toString().trim().substring(1) : sb.toString().trim();
        Log.i(TAG, mapString);
        return md5(mapString).toUpperCase();
    }

    public static String comDeviceId() {
        try {
            Context context = MyApplication.getContext();
            TelephonyManager TelephonyMgr = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
            String m_szImei = "";
            try {
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CAMERA}, 1);

                }else{
                    m_szImei = TelephonyMgr.getDeviceId();
                }

            }catch (Exception e){
                e.printStackTrace();
            }

            String m_szDevIDShort = "35" + //we make this look like a valid IMEI
                    Build.BOARD.length()%10 +
                    Build.BRAND.length()%10 +
                    Build.CPU_ABI.length()%10 +
                    Build.DEVICE.length()%10 +
                    Build.DISPLAY.length()%10 +
                    Build.HOST.length()%10 +
                    Build.ID.length()%10 +
                    Build.MANUFACTURER.length()%10 +
                    Build.MODEL.length()%10 +
                    Build.PRODUCT.length()%10 +
                    Build.TAGS.length()%10 +
                    Build.TYPE.length()%10 +
                    Build.USER.length()%10 ; //13 digits

            String m_szAndroidID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);

            @SuppressLint("WifiManagerLeak") WifiManager wm = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
            String m_szWLANMAC = wm.getConnectionInfo().getMacAddress();


            BluetoothAdapter m_BluetoothAdapter = null; // Local Bluetooth adapter
            m_BluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            String m_szBTMAC = m_BluetoothAdapter.getAddress();

            String m_szLongID = m_szImei + m_szDevIDShort
                    + m_szAndroidID+ m_szWLANMAC + m_szBTMAC;
//        String m_szLongID="";
// compute md5
            MessageDigest m = null;
            try {
                m = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            m.update(m_szLongID.getBytes(),0,m_szLongID.length());
// get md5 bytes
            byte p_md5Data[] = m.digest();
// create a hex string
            String m_szUniqueID = new String();
            for (int i=0;i<p_md5Data.length;i++) {
                int b =  (0xFF & p_md5Data[i]);
                // if it is a single digit, make sure it have 0 in front (proper padding)
                if (b <= 0xF)
                    m_szUniqueID+="0";
                // add number to string
                m_szUniqueID+= Integer.toHexString(b);
            }   // hex string to uppercase
            m_szUniqueID = m_szUniqueID.toUpperCase();
            return m_szUniqueID;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getIPAddress() {
        Context context=MyApplication.getContext();
        NetworkInfo info = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {//当前使用2G/3G/4G网络
                try {
                    //Enumeration<NetworkInterface> en=NetworkInterface.getNetworkInterfaces();
                    for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                        NetworkInterface intf = en.nextElement();
                        for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                            InetAddress inetAddress = enumIpAddr.nextElement();
                            if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                                return inetAddress.getHostAddress();
                            }
                        }
                    }
                } catch (SocketException e) {
                    e.printStackTrace();
                }

            } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {//当前使用无线网络
                WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                String ipAddress = intIP2StringIP(wifiInfo.getIpAddress());//得到IPV4地址
                return ipAddress;
            }
        } else {
            //当前无网络连接,请在设置中打开网络
        }
        return null;
    }

    /**
     * 将得到的int类型的IP转换为String类型
     *
     * @param ip
     * @return
     */
    public static String intIP2StringIP(int ip) {
        return (ip & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                (ip >> 24 & 0xFF);
    }
}
