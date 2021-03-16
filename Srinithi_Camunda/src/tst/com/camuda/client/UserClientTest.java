package tst.com.camuda.client;

import com.camunda.client.UserClient;
import com.camunda.user.UserDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserClientTest {

    @Test
    public void getUserListForFirstPage_normalInput_returnsExpectedNumberOfUsers() throws IOException {
        String restResponseString = "{\"page\":1,\"per_page\":6,\"total\":12,\"total_pages\":2,\"data\":[{\"id\":1,\"email\":\"george.bluth@reqres.in\",\"first_name\":\"George\",\"last_name\":\"Bluth\",\"avatar\":\"https://reqres.in/img/faces/1-image.jpg\"},{\"id\":2,\"email\":\"janet.weaver@reqres.in\",\"first_name\":\"Janet\",\"last_name\":\"Weaver\",\"avatar\":\"https://reqres.in/img/faces/2-image.jpg\"},{\"id\":3,\"email\":\"emma.wong@reqres.in\",\"first_name\":\"Emma\",\"last_name\":\"Wong\",\"avatar\":\"https://reqres.in/img/faces/3-image.jpg\"},{\"id\":4,\"email\":\"eve.holt@reqres.in\",\"first_name\":\"Eve\",\"last_name\":\"Holt\",\"avatar\":\"https://reqres.in/img/faces/4-image.jpg\"},{\"id\":5,\"email\":\"charles.morris@reqres.in\",\"first_name\":\"Charles\",\"last_name\":\"Morris\",\"avatar\":\"https://reqres.in/img/faces/5-image.jpg\"},{\"id\":6,\"email\":\"tracey.ramos@reqres.in\",\"first_name\":\"Tracey\",\"last_name\":\"Ramos\",\"avatar\":\"https://reqres.in/img/faces/6-image.jpg\"}],\"support\":{\"url\":\"https://reqres.in/#support-heading\",\"text\":\"To keep ReqRes free, contributions towards server costs are appreciated!\"}}";
        JSONObject restResponseJSON = new JSONObject(restResponseString);
        JSONArray jsonarr = restResponseJSON.getJSONArray("data");
        ArrayList<UserDTO> userList = new Gson().fromJson(jsonarr.toString(), new TypeToken<List<UserDTO>>() {
        }.getType());
        UserClient userClient = mock(UserClient.class);
        when(userClient.getUserListForFirstPage()).thenReturn(userList);
        List<UserDTO> resultUserList = userClient.getUserListForFirstPage();
        Assert.assertEquals(6, resultUserList.size());
        Assert.assertEquals("George", resultUserList.get(0).getFirstName());
    }
}

