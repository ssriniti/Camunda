package com.camunda.client;

import com.camunda.user.UserDTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class UserClient {

    public static final String HTTPS_ENDPOINT = "https://reqres.in/api/users";
    public static final String FIRST_PAGE_QUERY_PARAMETER = "?page=1";

    public List<UserDTO> getUserListForFirstPage() throws IOException {
        HttpURLConnection conn = getHTTPConnectionForFirstPage();
        BufferedReader streamReader = getRESTData(conn);
        return getUserListFromInputStream(streamReader);
    }

    private List<UserDTO> getUserListFromInputStream(final BufferedReader streamReader) throws IOException, JsonMappingException {
        StringBuilder responseStrBuilder = new StringBuilder();
        String inputStr;
        while ((inputStr = streamReader.readLine()) != null)
            responseStrBuilder.append(inputStr);
        JSONObject restResponseJSON = new JSONObject(responseStrBuilder.toString());
        JSONArray jsonarr = restResponseJSON.getJSONArray("data");
        return new Gson().fromJson(jsonarr.toString(), new TypeToken<List<UserDTO>>() {
        }.getType());
    }

    private BufferedReader getRESTData(final HttpURLConnection conn) throws IOException {
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP Error code : "
                    + conn.getResponseCode());
        }
        BufferedReader streamReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        conn.disconnect();
        return streamReader;
    }

    private HttpURLConnection getHTTPConnectionForFirstPage() throws IOException {
        URL url = new URL(HTTPS_ENDPOINT + FIRST_PAGE_QUERY_PARAMETER);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        return conn;
    }

    public static void main(String[] args) throws IOException {
        UserClient userClient = new UserClient();
        List<UserDTO> users = userClient.getUserListForFirstPage();
        for (UserDTO user : users) {
            System.out.println("FirstName: " + user.getFirstName() + ", LastName: " + user.getLastName());
        }
    }
}