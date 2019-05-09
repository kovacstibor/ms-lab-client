package bme.mobillabor.concertone.stub;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkEmptyCallStub implements Call<ResponseBody> {

    private boolean isError;
    private int errorCode;

    public NetworkEmptyCallStub(int errorCode, boolean isError) {
        this.isError = isError;
        this.errorCode = errorCode;
    }

    @Override
    public Response<ResponseBody> execute() throws IOException {
        if (isError) {
            return Response.error(errorCode, ResponseBody.create(MediaType.parse("text"), ""));
        }
        else {
            return Response.success(errorCode, ResponseBody.create(MediaType.parse("text"), ""));
        }
    }

    @Override
    public void enqueue(Callback<ResponseBody> callback) {

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
    public Call<ResponseBody> clone() {
        return null;
    }

    @Override
    public Request request() {
        return null;
    }
}
