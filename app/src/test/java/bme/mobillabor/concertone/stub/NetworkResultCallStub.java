package bme.mobillabor.concertone.stub;

import android.net.Network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class NetworkResultCallStub<TBase, TImpl extends TBase> implements Call<TBase> {

    private TBase response;
    private int responseCode;
    private boolean isError;

    public NetworkResultCallStub(int responseCode, TImpl response, boolean isError) {
        this.responseCode = responseCode;
        this.response = response;
        this.isError = isError;
    }

    @Override
    public Response<TBase> execute() throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            ObjectOutput out = new ObjectOutputStream(bos);
            out.writeObject(response);
            out.flush();
            byte[] serialized = bos.toByteArray();

            if (isError) {
                return Response.error(responseCode, ResponseBody.create(MediaType.parse("application/json"), serialized));
            }
            else {
                return Response.success(responseCode, response);
            }
        }
    }

    @Override
    public void enqueue(Callback<TBase> callback) {

    }

    @Override
    public boolean isExecuted() {
        return true;
    }

    @Override
    public void cancel() {

    }

    @Override
    public boolean isCanceled() {
        return false;
    }

    @Override
    public Call<TBase> clone() {
        return null;
    }

    @Override
    public Request request() {
        return null;
    }
}
