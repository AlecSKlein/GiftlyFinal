package rest;

import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import retrofit.Converter;

/**
 * Created by Alec Klein on 11/17/2015.
 */
public class ToStringConverter implements Converter<Object> {
    @Override
    public Object fromBody(ResponseBody body) throws IOException {
        return null;
    }

    @Override
    public RequestBody toBody(Object value) {
        return null;
    }
}
