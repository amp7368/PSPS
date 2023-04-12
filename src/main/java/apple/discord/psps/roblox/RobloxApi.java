package apple.discord.psps.roblox;

import apple.discord.psps.roblox.username.RobloxUsernameRequest;
import apple.utilities.threading.service.queue.TaskHandlerQueue;
import com.google.gson.Gson;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RobloxApi {

    public static final String USER_BY_USERNAME = "https://users.roblox.com/v1/usernames/users";
    private static TaskHandlerQueue SERVICE = new TaskHandlerQueue(1, 1000, 1000);

    private static OkHttpClient client = new OkHttpClient.Builder().callTimeout(Duration.ofSeconds(1)).build();

    public static synchronized void fetchUser(String username) {
        Gson gson = new Gson();
        byte[] body = gson.toJson(new RobloxUsernameRequest(username))
            .getBytes(StandardCharsets.UTF_8);
        Request request = new Request.Builder().post(RequestBody.create(body)).url(USER_BY_USERNAME).build();
        Call call = client.newCall(request);
        RobloxUsernameRequest response = SERVICE.taskCreator().accept(() -> {
            try (Response execute = call.execute(); Reader responseBody = execute.body().charStream()) {
                return gson.fromJson(responseBody, RobloxUsernameRequest.class);
            }
        }, SERVICE.emptyConsumer(), SERVICE.emptyConsumer()).complete();
    }
}
