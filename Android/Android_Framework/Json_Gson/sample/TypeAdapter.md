### TypeAdapter  
先看数据类
```
data class UserEntity(
        var name: String = "",
        val hobbitList: List<String> = listOf(),
        val enLevel: String = "",
        val address: AddressEntity = AddressEntity(),
        val skillEntity: SkillEntity = SkillEntity()
)
```
有时候，会传 完整的JsonObject  {}， 有的时候，只传 name "";   
UserTypeAdapter  
```
public class UserTypeAdapter extends TypeAdapter<UserEntity> {
    private final Gson gson = new Gson();

    @Override
    public void write(JsonWriter out, UserEntity value) throws IOException {
        out.beginObject();
        out.endObject();
    }

    @Override
    public UserEntity read(JsonReader in) throws IOException {
        UserEntity userEntity = new UserEntity();
        in.beginObject();
        while (in.hasNext()) {
            switch (in.nextName()) {
                case "code":
                    break;
                case "message":
                    break;
                case "data":
                    JsonToken jsonToken = in.peek();
                    if (JsonToken.BEGIN_OBJECT == jsonToken) {
                        JsonElement element = Streams.parse(in);
                        userEntity = gson.fromJson(element, UserEntity.class);
                    } else if (JsonToken.STRING == jsonToken) {
                        userEntity.setName(in.nextString());
                    }
                    break;
            }
        }
        in.endObject();
        return userEntity;
    }
}
```
```
@JsonAdapter(UserTypeAdapter::class)
data class UserEntity(
        var name: String = "",
        val hobbitList: List<String> = listOf(),
        val enLevel: String = "",
        val address: AddressEntity = AddressEntity(),
        val skillEntity: SkillEntity = SkillEntity()
)
```

  