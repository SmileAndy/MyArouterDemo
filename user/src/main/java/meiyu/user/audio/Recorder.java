package meiyu.user.audio;

/**
 * Author: GuoDandan
 * Version: V1.0版本
 * Description: Recorder
 * Date: 2018/12/7 13:42.
 */
//数据类
public class Recorder{

   public  float time;
   public  String filePath;

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Recorder(float time, String filePath) {
        super();
        this.time = time;
        this.filePath = filePath;
    }
}

