package com.alan.tool.common.utils;


import com.alan.tool.common.exception.BadRequestException;
import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import static com.alan.tool.common.constant.Constant.*;


public class OkHttpClientUtils {

    Logger log = LogManager.getLogger(OkHttpClientUtils.class);
    private OkHttpClient client = new OkHttpClient();

    private Map<String, String> headers;

    public OkHttpClientUtils(Map<String, String> headers) {
        headers = headers;
    }

    public Headers addHeader() {
        Headers headers = Headers.of(this.headers);
        return headers;
    }

    public Response post(String url, String jsonRequest) {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(mediaType, jsonRequest);
        log.info("calling ...");
        try {
            Request request = new Request.Builder()
                    .url(url)
                    .headers(this.addHeader())
                    .method(POST_METHOD, requestBody)
                    .build();


            Call call = client.newCall(request);
            Response response = call.execute();

            if (!checkCode(response)) {
                log.error(POST_API_FAIL, response.message());
//                throw new BadRequestException(POST_API_FAIL);
            }
            return response;
        } catch (Exception e) {
            log.error(POST_API_FAIL, e);
            throw new BadRequestException(e.getMessage());
        }
    }

    public Response patch(String url, String jsonRequest) {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(mediaType, jsonRequest);

        try {
            Request request = new Request.Builder()
                    .url(url)
                    .headers(this.addHeader())
                    .method(PATCH_METHOD, requestBody)
                    .build();
            Call call = client.newCall(request);
            Response response = call.execute();

            if (!checkCode(response)) {
                log.error(POST_API_FAIL, response.message());
                throw new BadRequestException(PATCH_API_FAIL);
            }
            return response;
        } catch (Exception e) {
            log.error(POST_API_FAIL, e);
            throw new BadRequestException(e.getMessage());
        }
    }

    public Response get(String baseUrl, Map<String, String> params) {
        HttpUrl.Builder urlBuilder
                = HttpUrl.parse(baseUrl).newBuilder();

        if (params.size() > 0) {
            params.forEach(urlBuilder::addQueryParameter);
        }

        String url = urlBuilder.build().toString();
        try {
            Request request = new Request.Builder()
                    .headers(this.addHeader())
                    .url(url)
                    .build();

            Call call = client.newCall(request);
            Response response = call.execute();

            if (!checkCode(response)) {
                log.error(POST_API_FAIL, response.message());
                throw new BadRequestException(GET_API_FAIL);
            }
            return response;
        } catch (Exception e) {
            log.error(GET_API_FAIL, e);
            throw new BadRequestException(e.getMessage());
        }
    }

    public boolean checkCode(Response response) {
        return response.code() >= 200 && response.code() < 300;
    }
}
