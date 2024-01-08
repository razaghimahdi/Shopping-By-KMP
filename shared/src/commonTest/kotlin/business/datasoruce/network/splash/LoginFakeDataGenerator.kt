package business.datasoruce.network.splash

object LoginFakeDataGenerator {


    const val email = "admin@shop.com"
    const val password = "admin_admin"

    const val empty = "{}"
    const val malformedData = "{\n" +
            "  \"result\": null,\n" +
            "  \"status\": false,\n" +
            "  \"alert\": {\n" +
            "    \"title\": \"Invalid\",\n" +
            "    \"message\": \"Invalid credentials\"\n" +
            "  }\n" +
            "}"
    const val fillDataCurrently = "{\n" +
            "  \"result\": null,\n" +
            "    \"status\": false,\n" +
            "    \"alert\": {\n" +
            "        \"title\": \"Error\",\n" +
            "        \"message\": \"validation error , please fill data currently\"\n" +
            "    }\n" +
            "}"
    const val goodData = "{\n" +
            "  \"result\": \"1|3U9NPkkdXKhsG8NfaTelMu6tzBpLKcWVJOmZ57RN79992073\",\n" +
            "  \"status\": true,\n" +
            "  \"alert\": {\n" +
            "    \"title\": \"Login\",\n" +
            "    \"message\": \"Login successful\"\n" +
            "  }\n" +
            "}"
    const val timeout  = ""
}