package gaming178.com.mylibrary.base.soap;

import org.ksoap2.transport.HttpTransportSE;
import org.ksoap2.transport.ServiceConnection;

import java.io.IOException;

public class MAndroidHttpTransport extends HttpTransportSE {

    //超时默认时间
    private int timeout = 100 * 1000;

    public MAndroidHttpTransport(String url) {
        super(url);
    }

    public MAndroidHttpTransport(String url, int timeout) {
        super(url);
        this.timeout = timeout;
    }

    @Override
    protected ServiceConnection getServiceConnection() throws IOException {
        MServiceConnection serviceConnection = new MServiceConnection(url, timeout);
        return serviceConnection;
    }
}
