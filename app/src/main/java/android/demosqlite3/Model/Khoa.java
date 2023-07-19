package android.demosqlite3.Model;

public class Khoa {
    int makhoa;
    String tenkhoa;

    public Khoa(int makhoa, String tenkhoa) {
        this.makhoa = makhoa;
        this.tenkhoa = tenkhoa;
    }
    public Khoa()
    {

    }

    public int getMakhoa() {
        return makhoa;
    }

    public void setMakhoa(int makhoa) {
        this.makhoa = makhoa;
    }

    public String getTenkhoa() {
        return tenkhoa;
    }

    public void setTenkhoa(String tenkhoa) {
        this.tenkhoa = tenkhoa;
    }
}
