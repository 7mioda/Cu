package CackeProject.Utils;


import CackeProject.Entities.Post;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


public class CupCakeApi {

    private static String URI = "http://localhost:8000/api/";
    private static String Token = "eyJhbGciOiJSUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwidXNlcm5hbWUiOiJhaG1lZCIsImlhdCI6MTUyMzc0NzQ3OSwiZXhwIjoxNTIzNzUxMDc5fQ.QxpmDL3EOznWd7twvkNB-nitOp1crGglzJ3IJ8Hq3VxtLHONGaXzQPkGQH8_YU6xiCLSiRvmRYo4V-XK74CZ0u1f7VeJ05fpJP_98I2NwuP6_haSX9jI8eZrxtP9olHlpGAratqCfJE6kO6Fbh1rYI0fuTwT8cEeZK4qXtZYS3JPFlBehy4wnV9EVB-e7Pv_FnaZcvy1snK89wpXt_uiW-g6nGz4GOMFsH8b19viomEhvrBtp2iPE_571CQPXmNryBN2JszmAgA8Ez7EIfRJQCIgaFv3LzBV-VLDGBIBvCb-cmUCdTnBqHkqDLJRpCHj1g6fZw5M78LlzNSRuQAVm9cKxa5P0oJpUj3fLPx8uAufz4NWMkmBdd2S3jErAGZrR5VY167lT0lRjgqPO8NOkS2VvsYE8TOQmmSQChvCgH-5CimHcvGXUM4o8ASl8ruFScu-06O7zoq9_VuLnQ5URjYb-6Wz1MKTv1Oy1TW5i94OuYb3RCQRpxZl2hPDtvkMcuaMF-8r3-7ced43eS_2bPyph8YUGghC3rUlZZMdhJu7F3ie5a4GoEJmOC8ELNd8RBGGKjf_xvW2fxVb4o9I2HFa3Yt8Uhx_Cx5msSpXxOtGXFZCUHbQsyxUeQq8lt6eaK7uws7a4iLk6nXPbIinn7H6JvaypmjM-9RJ_BFUhiY";

    public static void GET(String controler) {

        try {
            URL url = new URL (URI + controler);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setRequestProperty  ("Authorization", "Bearer " + Token);

            ObjectMapper mapper = new ObjectMapper();


            InputStream content = (InputStream)connection.getInputStream();
            BufferedReader in   = new BufferedReader (new InputStreamReader(content));
            String line;




            while ((line = in.readLine()) != null) {

                List<Post> myObjects = mapper.readValue(line, new TypeReference<List<Post>>(){});
                myObjects.forEach(e->{
                    System.out.println(e.id);
                    System.out.println(e.description);
                    System.out.println(e.title);
                });
            }



           /*
           a single object
           while ((line = in.readLine()) != null) {
                Post post = mapper.readValue(line.substring(1,line.length()-1),Post.class);
                System.out.println(post.id);
                System.out.println(post.title);
                System.out.println(post.description);

            }*/
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}

