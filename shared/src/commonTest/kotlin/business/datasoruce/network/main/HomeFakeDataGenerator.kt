package business.datasoruce.network.main

object HomeFakeDataGenerator {


    const val empty = "{}"
    const val malformedData = ""
    const val unauthenticatedData = "{\"result\":null,\"status\":false,\"alert\":{\"title\":\"Unauthenticated\",\"message\":\"Unauthenticated.\"}}"
    const val goodData = "{\n" +
            "    \"result\": {\n" +
            "        \"banners\": [\n" +
            "            {\n" +
            "                \"id\": 1,\n" +
            "                \"banner\": \"https://m.media-amazon.com/images/I/614VOsGXsqL._SX1500_.jpg\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 2,\n" +
            "                \"banner\": \"https://m.media-amazon.com/images/I/71kFc7PP3PL._SX3000_.jpg\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 3,\n" +
            "                \"banner\": \"https://m.media-amazon.com/images/I/717Qv6Rdi+L._SX3000_.jpg\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"categories\": [\n" +
            "            {\n" +
            "                \"id\": 1,\n" +
            "                \"name\": \"Computer\",\n" +
            "                \"parent\": 0,\n" +
            "                \"icon\": \"\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 2,\n" +
            "                \"name\": \"Electronics\",\n" +
            "                \"parent\": 0,\n" +
            "                \"icon\": \"\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 3,\n" +
            "                \"name\": \"Arts & Crafts\",\n" +
            "                \"parent\": 0,\n" +
            "                \"icon\": \"\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 4,\n" +
            "                \"name\": \"Automotive\",\n" +
            "                \"parent\": 0,\n" +
            "                \"icon\": \"\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 5,\n" +
            "                \"name\": \"Baby\",\n" +
            "                \"parent\": 0,\n" +
            "                \"icon\": \"\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 6,\n" +
            "                \"name\": \"Beauty and personal care\",\n" +
            "                \"parent\": 0,\n" +
            "                \"icon\": \"\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 7,\n" +
            "                \"name\": \"Women's Fashion\",\n" +
            "                \"parent\": 0,\n" +
            "                \"icon\": \"\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 8,\n" +
            "                \"name\": \"Men's Fashion\",\n" +
            "                \"parent\": 0,\n" +
            "                \"icon\": \"\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 9,\n" +
            "                \"name\": \"Health and Household\",\n" +
            "                \"parent\": 0,\n" +
            "                \"icon\": \"\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 10,\n" +
            "                \"name\": \"Home and Kitchen\",\n" +
            "                \"parent\": 0,\n" +
            "                \"icon\": \"\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 11,\n" +
            "                \"name\": \"Industrial and Scientific\",\n" +
            "                \"parent\": 0,\n" +
            "                \"icon\": \"\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 12,\n" +
            "                \"name\": \"Luggage\",\n" +
            "                \"parent\": 0,\n" +
            "                \"icon\": \"\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 13,\n" +
            "                \"name\": \"Movies & Television\",\n" +
            "                \"parent\": 0,\n" +
            "                \"icon\": \"\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 14,\n" +
            "                \"name\": \"Pet supplies\",\n" +
            "                \"parent\": 0,\n" +
            "                \"icon\": \"\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 15,\n" +
            "                \"name\": \"Sports and Outdoors\",\n" +
            "                \"parent\": 0,\n" +
            "                \"icon\": \"\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 16,\n" +
            "                \"name\": \"Tools & Home Improvement\",\n" +
            "                \"parent\": 0,\n" +
            "                \"icon\": \"\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 17,\n" +
            "                \"name\": \"Toys and Games\",\n" +
            "                \"parent\": 0,\n" +
            "                \"icon\": \"\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"newest_product\": [\n" +
            "            {\n" +
            "                \"id\": 1,\n" +
            "                \"title\": \"MANGOPOP Women's Mock Turtle Neck Slim Fit Long Half Short Sleeve T Shirt Tight Tops Tee\",\n" +
            "                \"description\": \"MANGOPOP Women's Mock Turtle Neck Slim Fit Long Half Short Sleeve T Shirt Tight Tops Tee\",\n" +
            "                \"price\": 19,\n" +
            "                \"image\": \"https://m.media-amazon.com/images/W/MEDIAX_792452-T1/images/I/712koZKUvHL._AC_SX679_.jpg\",\n" +
            "                \"likes\": 5,\n" +
            "                \"isLike\": false,\n" +
            "                \"rate\": 3.5\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 2,\n" +
            "                \"title\": \"Amazfit Band 5 Activity Fitness Tracker\",\n" +
            "                \"description\": \"Amazfit Band 5 Activity Fitness Tracker with Alexa Built-in, 15-Day Battery Life, Blood Oxygen, Heart Rate, Sleep & Stress Monitoring, 5 ATM Water Resistant, Fitness Watch for Men Women Kids, Black\",\n" +
            "                \"price\": 120,\n" +
            "                \"image\": \"https://m.media-amazon.com/images/W/MEDIAX_792452-T1/images/I/51ja6ds+pML._AC_SX679_.jpg\",\n" +
            "                \"likes\": 2,\n" +
            "                \"isLike\": false,\n" +
            "                \"rate\": 3.5\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 3,\n" +
            "                \"title\": \"Alpine Corporation Weather-resistant Bluetooth Solar-Powered Outdoor Wireless Rock Speaker – Set of 2, Brown\",\n" +
            "                \"description\": \"Alpine Corporation Weather-resistant Bluetooth Solar-Powered Outdoor Wireless Rock Speaker – Set of 2, Brown\",\n" +
            "                \"price\": 320,\n" +
            "                \"image\": \"https://m.media-amazon.com/images/W/MEDIAX_792452-T1/images/I/816gTVTqs5L._AC_SL1500_.jpg\",\n" +
            "                \"likes\": 0,\n" +
            "                \"isLike\": false,\n" +
            "                \"rate\": 3.5\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 4,\n" +
            "                \"title\": \"Nike model-934\",\n" +
            "                \"description\": \"Nike model-934\",\n" +
            "                \"price\": 120,\n" +
            "                \"image\": \"https://www.deadstock.de/wp-content/uploads/2023/01/Jordan-1-Mid-Barely-Grape-DQ8423-501-dead-stock-titel-bild-.jpeg\",\n" +
            "                \"likes\": 1,\n" +
            "                \"isLike\": false,\n" +
            "                \"rate\": 3.5\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 5,\n" +
            "                \"title\": \"Nike model-934\",\n" +
            "                \"description\": \"Nike model-934\",\n" +
            "                \"price\": 120,\n" +
            "                \"image\": \"https://www.deadstock.de/wp-content/uploads/2023/01/Jordan-1-Mid-Barely-Grape-DQ8423-501-dead-stock-titel-bild-.jpeg\",\n" +
            "                \"likes\": 0,\n" +
            "                \"isLike\": false,\n" +
            "                \"rate\": 3.5\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 6,\n" +
            "                \"title\": \"Nike model-934\",\n" +
            "                \"description\": \"Nike model-934\",\n" +
            "                \"price\": 120,\n" +
            "                \"image\": \"https://www.deadstock.de/wp-content/uploads/2023/01/Jordan-1-Mid-Barely-Grape-DQ8423-501-dead-stock-titel-bild-.jpeg\",\n" +
            "                \"likes\": 1,\n" +
            "                \"isLike\": false,\n" +
            "                \"rate\": 3.5\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 7,\n" +
            "                \"title\": \"Shirt model-131\",\n" +
            "                \"description\": \"Shirt model-131\",\n" +
            "                \"price\": 13,\n" +
            "                \"image\": \"https://img.ltwebstatic.com/images3_pi/2022/08/22/1661139363d0af2b9ebc4be1a701c62b3af5e237ef.webp\",\n" +
            "                \"likes\": 1,\n" +
            "                \"isLike\": false,\n" +
            "                \"rate\": 3.5\n" +
            "            }\n" +
            "        ],\n" +
            "        \"address\": {\n" +
            "            \"id\": 3,\n" +
            "            \"address\": \"Alisoon wood St\",\n" +
            "            \"city\": \"NC\",\n" +
            "            \"county\": \"USA\",\n" +
            "            \"state\": \"NC\",\n" +
            "            \"zip_code\": \"8287373\"\n" +
            "        },\n" +
            "        \"flash_sale\": {\n" +
            "            \"expired_at\": \"2024-03-13T03:30:03.641004Z\",\n" +
            "            \"products\": [\n" +
            "                {\n" +
            "                    \"id\": 1,\n" +
            "                    \"title\": \"MANGOPOP Women's Mock Turtle Neck Slim Fit Long Half Short Sleeve T Shirt Tight Tops Tee\",\n" +
            "                    \"description\": \"MANGOPOP Women's Mock Turtle Neck Slim Fit Long Half Short Sleeve T Shirt Tight Tops Tee\",\n" +
            "                    \"price\": 19,\n" +
            "                    \"image\": \"https://m.media-amazon.com/images/W/MEDIAX_792452-T1/images/I/712koZKUvHL._AC_SX679_.jpg\",\n" +
            "                    \"likes\": 5,\n" +
            "                    \"isLike\": false,\n" +
            "                    \"rate\": 3.5\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": 2,\n" +
            "                    \"title\": \"Amazfit Band 5 Activity Fitness Tracker\",\n" +
            "                    \"description\": \"Amazfit Band 5 Activity Fitness Tracker with Alexa Built-in, 15-Day Battery Life, Blood Oxygen, Heart Rate, Sleep & Stress Monitoring, 5 ATM Water Resistant, Fitness Watch for Men Women Kids, Black\",\n" +
            "                    \"price\": 120,\n" +
            "                    \"image\": \"https://m.media-amazon.com/images/W/MEDIAX_792452-T1/images/I/51ja6ds+pML._AC_SX679_.jpg\",\n" +
            "                    \"likes\": 2,\n" +
            "                    \"isLike\": false,\n" +
            "                    \"rate\": 3.5\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": 3,\n" +
            "                    \"title\": \"Alpine Corporation Weather-resistant Bluetooth Solar-Powered Outdoor Wireless Rock Speaker – Set of 2, Brown\",\n" +
            "                    \"description\": \"Alpine Corporation Weather-resistant Bluetooth Solar-Powered Outdoor Wireless Rock Speaker – Set of 2, Brown\",\n" +
            "                    \"price\": 320,\n" +
            "                    \"image\": \"https://m.media-amazon.com/images/W/MEDIAX_792452-T1/images/I/816gTVTqs5L._AC_SL1500_.jpg\",\n" +
            "                    \"likes\": 0,\n" +
            "                    \"isLike\": false,\n" +
            "                    \"rate\": 3.5\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": 4,\n" +
            "                    \"title\": \"Nike model-934\",\n" +
            "                    \"description\": \"Nike model-934\",\n" +
            "                    \"price\": 120,\n" +
            "                    \"image\": \"https://www.deadstock.de/wp-content/uploads/2023/01/Jordan-1-Mid-Barely-Grape-DQ8423-501-dead-stock-titel-bild-.jpeg\",\n" +
            "                    \"likes\": 1,\n" +
            "                    \"isLike\": false,\n" +
            "                    \"rate\": 3.5\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": 5,\n" +
            "                    \"title\": \"Nike model-934\",\n" +
            "                    \"description\": \"Nike model-934\",\n" +
            "                    \"price\": 120,\n" +
            "                    \"image\": \"https://www.deadstock.de/wp-content/uploads/2023/01/Jordan-1-Mid-Barely-Grape-DQ8423-501-dead-stock-titel-bild-.jpeg\",\n" +
            "                    \"likes\": 0,\n" +
            "                    \"isLike\": false,\n" +
            "                    \"rate\": 3.5\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": 6,\n" +
            "                    \"title\": \"Nike model-934\",\n" +
            "                    \"description\": \"Nike model-934\",\n" +
            "                    \"price\": 120,\n" +
            "                    \"image\": \"https://www.deadstock.de/wp-content/uploads/2023/01/Jordan-1-Mid-Barely-Grape-DQ8423-501-dead-stock-titel-bild-.jpeg\",\n" +
            "                    \"likes\": 1,\n" +
            "                    \"isLike\": false,\n" +
            "                    \"rate\": 3.5\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\": 7,\n" +
            "                    \"title\": \"Shirt model-131\",\n" +
            "                    \"description\": \"Shirt model-131\",\n" +
            "                    \"price\": 13,\n" +
            "                    \"image\": \"https://img.ltwebstatic.com/images3_pi/2022/08/22/1661139363d0af2b9ebc4be1a701c62b3af5e237ef.webp\",\n" +
            "                    \"likes\": 1,\n" +
            "                    \"isLike\": false,\n" +
            "                    \"rate\": 3.5\n" +
            "                }\n" +
            "            ]\n" +
            "        },\n" +
            "        \"most_sale\": [\n" +
            "            {\n" +
            "                \"id\": 1,\n" +
            "                \"title\": \"MANGOPOP Women's Mock Turtle Neck Slim Fit Long Half Short Sleeve T Shirt Tight Tops Tee\",\n" +
            "                \"description\": \"MANGOPOP Women's Mock Turtle Neck Slim Fit Long Half Short Sleeve T Shirt Tight Tops Tee\",\n" +
            "                \"price\": 19,\n" +
            "                \"image\": \"https://m.media-amazon.com/images/W/MEDIAX_792452-T1/images/I/712koZKUvHL._AC_SX679_.jpg\",\n" +
            "                \"likes\": 5,\n" +
            "                \"isLike\": false,\n" +
            "                \"rate\": 3.5\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 2,\n" +
            "                \"title\": \"Amazfit Band 5 Activity Fitness Tracker\",\n" +
            "                \"description\": \"Amazfit Band 5 Activity Fitness Tracker with Alexa Built-in, 15-Day Battery Life, Blood Oxygen, Heart Rate, Sleep & Stress Monitoring, 5 ATM Water Resistant, Fitness Watch for Men Women Kids, Black\",\n" +
            "                \"price\": 120,\n" +
            "                \"image\": \"https://m.media-amazon.com/images/W/MEDIAX_792452-T1/images/I/51ja6ds+pML._AC_SX679_.jpg\",\n" +
            "                \"likes\": 2,\n" +
            "                \"isLike\": false,\n" +
            "                \"rate\": 3.5\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 3,\n" +
            "                \"title\": \"Alpine Corporation Weather-resistant Bluetooth Solar-Powered Outdoor Wireless Rock Speaker – Set of 2, Brown\",\n" +
            "                \"description\": \"Alpine Corporation Weather-resistant Bluetooth Solar-Powered Outdoor Wireless Rock Speaker – Set of 2, Brown\",\n" +
            "                \"price\": 320,\n" +
            "                \"image\": \"https://m.media-amazon.com/images/W/MEDIAX_792452-T1/images/I/816gTVTqs5L._AC_SL1500_.jpg\",\n" +
            "                \"likes\": 0,\n" +
            "                \"isLike\": false,\n" +
            "                \"rate\": 3.5\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 4,\n" +
            "                \"title\": \"Nike model-934\",\n" +
            "                \"description\": \"Nike model-934\",\n" +
            "                \"price\": 120,\n" +
            "                \"image\": \"https://www.deadstock.de/wp-content/uploads/2023/01/Jordan-1-Mid-Barely-Grape-DQ8423-501-dead-stock-titel-bild-.jpeg\",\n" +
            "                \"likes\": 1,\n" +
            "                \"isLike\": false,\n" +
            "                \"rate\": 3.5\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 5,\n" +
            "                \"title\": \"Nike model-934\",\n" +
            "                \"description\": \"Nike model-934\",\n" +
            "                \"price\": 120,\n" +
            "                \"image\": \"https://www.deadstock.de/wp-content/uploads/2023/01/Jordan-1-Mid-Barely-Grape-DQ8423-501-dead-stock-titel-bild-.jpeg\",\n" +
            "                \"likes\": 0,\n" +
            "                \"isLike\": false,\n" +
            "                \"rate\": 3.5\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 6,\n" +
            "                \"title\": \"Nike model-934\",\n" +
            "                \"description\": \"Nike model-934\",\n" +
            "                \"price\": 120,\n" +
            "                \"image\": \"https://www.deadstock.de/wp-content/uploads/2023/01/Jordan-1-Mid-Barely-Grape-DQ8423-501-dead-stock-titel-bild-.jpeg\",\n" +
            "                \"likes\": 1,\n" +
            "                \"isLike\": false,\n" +
            "                \"rate\": 3.5\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 7,\n" +
            "                \"title\": \"Shirt model-131\",\n" +
            "                \"description\": \"Shirt model-131\",\n" +
            "                \"price\": 13,\n" +
            "                \"image\": \"https://img.ltwebstatic.com/images3_pi/2022/08/22/1661139363d0af2b9ebc4be1a701c62b3af5e237ef.webp\",\n" +
            "                \"likes\": 1,\n" +
            "                \"isLike\": false,\n" +
            "                \"rate\": 3.5\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    \"status\": true,\n" +
            "    \"alert\": null\n" +
            "}"
    const val timeout  = ""
}