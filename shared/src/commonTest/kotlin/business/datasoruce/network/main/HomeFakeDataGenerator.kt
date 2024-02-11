package business.datasoruce.network.main

object HomeFakeDataGenerator {


    const val empty = "{}"
    const val malformedData = ""
    const val unauthenticatedData = "{\"result\":null,\"status\":false,\"alert\":{\"title\":\"Unauthenticated\",\"message\":\"Unauthenticated.\"}}"
    const val goodData = "{\n" +
            "  \"result\": {\n" +
            "    \"banners\": [\n" +
            "      {\n" +
            "        \"id\": 1,\n" +
            "        \"banner\": \"https://m.media-amazon.com/images/I/614VOsGXsqL._SX1500_.jpg\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 2,\n" +
            "        \"banner\": \"https://m.media-amazon.com/images/I/71kFc7PP3PL._SX3000_.jpg\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 3,\n" +
            "        \"banner\": \"https://m.media-amazon.com/images/I/717Qv6Rdi+L._SX3000_.jpg\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"categories\": [\n" +
            "      {\n" +
            "        \"id\": 1,\n" +
            "        \"name\": \"Computer\",\n" +
            "        \"parent\": 0,\n" +
            "        \"icon\": \"\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 2,\n" +
            "        \"name\": \"Electronics\",\n" +
            "        \"parent\": 0,\n" +
            "        \"icon\": \"\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 3,\n" +
            "        \"name\": \"Arts & Crafts\",\n" +
            "        \"parent\": 0,\n" +
            "        \"icon\": \"\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 4,\n" +
            "        \"name\": \"Automotive\",\n" +
            "        \"parent\": 0,\n" +
            "        \"icon\": \"\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 5,\n" +
            "        \"name\": \"Baby\",\n" +
            "        \"parent\": 0,\n" +
            "        \"icon\": \"\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 6,\n" +
            "        \"name\": \"Beauty and personal care\",\n" +
            "        \"parent\": 0,\n" +
            "        \"icon\": \"\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 7,\n" +
            "        \"name\": \"Women's Fashion\",\n" +
            "        \"parent\": 0,\n" +
            "        \"icon\": \"\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 8,\n" +
            "        \"name\": \"Men's Fashion\",\n" +
            "        \"parent\": 0,\n" +
            "        \"icon\": \"\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 9,\n" +
            "        \"name\": \"Health and Household\",\n" +
            "        \"parent\": 0,\n" +
            "        \"icon\": \"\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 10,\n" +
            "        \"name\": \"Home and Kitchen\",\n" +
            "        \"parent\": 0,\n" +
            "        \"icon\": \"\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 11,\n" +
            "        \"name\": \"Industrial and Scientific\",\n" +
            "        \"parent\": 0,\n" +
            "        \"icon\": \"\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 12,\n" +
            "        \"name\": \"Luggage\",\n" +
            "        \"parent\": 0,\n" +
            "        \"icon\": \"\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 13,\n" +
            "        \"name\": \"Movies & Television\",\n" +
            "        \"parent\": 0,\n" +
            "        \"icon\": \"\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 14,\n" +
            "        \"name\": \"Pet supplies\",\n" +
            "        \"parent\": 0,\n" +
            "        \"icon\": \"\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 15,\n" +
            "        \"name\": \"Sports and Outdoors\",\n" +
            "        \"parent\": 0,\n" +
            "        \"icon\": \"\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 16,\n" +
            "        \"name\": \"Tools & Home Improvement\",\n" +
            "        \"parent\": 0,\n" +
            "        \"icon\": \"\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 17,\n" +
            "        \"name\": \"Toys and Games\",\n" +
            "        \"parent\": 0,\n" +
            "        \"icon\": \"\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"newest_product\": [\n" +
            "      {\n" +
            "        \"id\": 1,\n" +
            "        \"title\": \"Nike model-934\",\n" +
            "        \"description\": \"Nike model-934\",\n" +
            "        \"price\": 120,\n" +
            "        \"image\": \"https://www.deadstock.de/wp-content/uploads/2023/01/Jordan-1-Mid-Barely-Grape-DQ8423-501-dead-stock-titel-bild-.jpeg\",\n" +
            "        \"likes\": 0,\n" +
            "        \"isLike\": false,\n" +
            "        \"rate\": 3.5\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 2,\n" +
            "        \"title\": \"Nike model-934\",\n" +
            "        \"description\": \"Nike model-934\",\n" +
            "        \"price\": 120,\n" +
            "        \"image\": \"https://www.deadstock.de/wp-content/uploads/2023/01/Jordan-1-Mid-Barely-Grape-DQ8423-501-dead-stock-titel-bild-.jpeg\",\n" +
            "        \"likes\": 0,\n" +
            "        \"isLike\": false,\n" +
            "        \"rate\": 3.5\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 3,\n" +
            "        \"title\": \"Nike model-934\",\n" +
            "        \"description\": \"Nike model-934\",\n" +
            "        \"price\": 120,\n" +
            "        \"image\": \"https://www.deadstock.de/wp-content/uploads/2023/01/Jordan-1-Mid-Barely-Grape-DQ8423-501-dead-stock-titel-bild-.jpeg\",\n" +
            "        \"likes\": 0,\n" +
            "        \"isLike\": false,\n" +
            "        \"rate\": 3.5\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 4,\n" +
            "        \"title\": \"Shirt model-131\",\n" +
            "        \"description\": \"Shirt model-131\",\n" +
            "        \"price\": 13,\n" +
            "        \"image\": \"https://img.ltwebstatic.com/images3_pi/2022/08/22/1661139363d0af2b9ebc4be1a701c62b3af5e237ef.webp\",\n" +
            "        \"likes\": 0,\n" +
            "        \"isLike\": false,\n" +
            "        \"rate\": 3.5\n" +
            "      }\n" +
            "    ],\n" +
            "    \"flash_sale\": {\n" +
            "      \"expired_at\": \"2023-11-24T19:50:04.774834Z\",\n" +
            "      \"products\": [\n" +
            "        {\n" +
            "          \"id\": 1,\n" +
            "          \"title\": \"Nike model-934\",\n" +
            "          \"description\": \"Nike model-934\",\n" +
            "          \"price\": 120,\n" +
            "          \"image\": \"https://www.deadstock.de/wp-content/uploads/2023/01/Jordan-1-Mid-Barely-Grape-DQ8423-501-dead-stock-titel-bild-.jpeg\",\n" +
            "          \"likes\": 0,\n" +
            "          \"isLike\": false,\n" +
            "          \"rate\": 3.5\n" +
            "        },\n" +
            "        {\n" +
            "          \"id\": 2,\n" +
            "          \"title\": \"Nike model-934\",\n" +
            "          \"description\": \"Nike model-934\",\n" +
            "          \"price\": 120,\n" +
            "          \"image\": \"https://www.deadstock.de/wp-content/uploads/2023/01/Jordan-1-Mid-Barely-Grape-DQ8423-501-dead-stock-titel-bild-.jpeg\",\n" +
            "          \"likes\": 0,\n" +
            "          \"isLike\": false,\n" +
            "          \"rate\": 3.5\n" +
            "        },\n" +
            "        {\n" +
            "          \"id\": 3,\n" +
            "          \"title\": \"Nike model-934\",\n" +
            "          \"description\": \"Nike model-934\",\n" +
            "          \"price\": 120,\n" +
            "          \"image\": \"https://www.deadstock.de/wp-content/uploads/2023/01/Jordan-1-Mid-Barely-Grape-DQ8423-501-dead-stock-titel-bild-.jpeg\",\n" +
            "          \"likes\": 0,\n" +
            "          \"isLike\": false,\n" +
            "          \"rate\": 3.5\n" +
            "        },\n" +
            "        {\n" +
            "          \"id\": 4,\n" +
            "          \"title\": \"Shirt model-131\",\n" +
            "          \"description\": \"Shirt model-131\",\n" +
            "          \"price\": 13,\n" +
            "          \"image\": \"https://img.ltwebstatic.com/images3_pi/2022/08/22/1661139363d0af2b9ebc4be1a701c62b3af5e237ef.webp\",\n" +
            "          \"likes\": 0,\n" +
            "          \"isLike\": false,\n" +
            "          \"rate\": 3.5\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    \"most_sale\": [\n" +
            "      {\n" +
            "        \"id\": 1,\n" +
            "        \"title\": \"Nike model-934\",\n" +
            "        \"description\": \"Nike model-934\",\n" +
            "        \"price\": 120,\n" +
            "        \"image\": \"https://www.deadstock.de/wp-content/uploads/2023/01/Jordan-1-Mid-Barely-Grape-DQ8423-501-dead-stock-titel-bild-.jpeg\",\n" +
            "        \"likes\": 0,\n" +
            "        \"isLike\": false,\n" +
            "        \"rate\": 3.5\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 2,\n" +
            "        \"title\": \"Nike model-934\",\n" +
            "        \"description\": \"Nike model-934\",\n" +
            "        \"price\": 120,\n" +
            "        \"image\": \"https://www.deadstock.de/wp-content/uploads/2023/01/Jordan-1-Mid-Barely-Grape-DQ8423-501-dead-stock-titel-bild-.jpeg\",\n" +
            "        \"likes\": 0,\n" +
            "        \"isLike\": false,\n" +
            "        \"rate\": 3.5\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 3,\n" +
            "        \"title\": \"Nike model-934\",\n" +
            "        \"description\": \"Nike model-934\",\n" +
            "        \"price\": 120,\n" +
            "        \"image\": \"https://www.deadstock.de/wp-content/uploads/2023/01/Jordan-1-Mid-Barely-Grape-DQ8423-501-dead-stock-titel-bild-.jpeg\",\n" +
            "        \"likes\": 0,\n" +
            "        \"isLike\": false,\n" +
            "        \"rate\": 3.5\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 4,\n" +
            "        \"title\": \"Shirt model-131\",\n" +
            "        \"description\": \"Shirt model-131\",\n" +
            "        \"price\": 13,\n" +
            "        \"image\": \"https://img.ltwebstatic.com/images3_pi/2022/08/22/1661139363d0af2b9ebc4be1a701c62b3af5e237ef.webp\",\n" +
            "        \"likes\": 0,\n" +
            "        \"isLike\": false,\n" +
            "        \"rate\": 3.5\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  \"status\": true,\n" +
            "  \"alert\": null\n" +
            "}"
    const val timeout  = ""
}