package com.example.carflip;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class CarDetailsActivity extends AppCompatActivity {

    private TextView carNameTextView;
    private TextView carPriceTextView;
    private TextView carImageTextView;
    private ImageView carImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webscraper);

        // Initialize views
        carNameTextView = findViewById(R.id.carNameTextView);
        carPriceTextView = findViewById(R.id.carPriceTextView);
        carImageTextView = findViewById(R.id.carImageTextView);
        carImageView = findViewById(R.id.carImageView);

        // Execute AsyncTask to perform network operations
        new FetchCarDataTask().execute();
    }

    private class FetchCarDataTask extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... voids) {
            String[] carData = new String[3];

            try {
                String url = "https://www.cardekho.com/";
                Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0").get();
                Elements carElements = doc.select("li[class^=gsc_col]");

                // Assuming there's only one car, get the first one
                Element firstCar = carElements.first();
                if (firstCar != null) {
                    // Extract car details
                    String carName = firstCar.select("a.title").text().trim();
                    String carPrice = firstCar.select("div.price").text().trim();

                    // Extract URL from inline CSS style
                    String carImageStyle = firstCar.select("div.imageTransition.active").attr("style");
                    String carImage = extractImageUrl(carImageStyle);

                    if (carImage != null) {
                        carData[0] = carName;
                        carData[1] = carPrice;
                        carData[2] = carImage;
                    } else {
                        // Handle the case where the URL extraction fails
                        carData = null;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return carData;
        }


        private String extractImageUrl(String styleAttribute) {
            if (styleAttribute != null && !styleAttribute.isEmpty()) {
                int startIndex = styleAttribute.indexOf("url(\"") + 5;
                int endIndex = styleAttribute.indexOf("\")", startIndex);
                if (startIndex != -1 && endIndex != -1) {
                    return styleAttribute.substring(startIndex, endIndex);
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String[] carData) {
            super.onPostExecute(carData);

            // Update TextViews with scraped data
            if (carData != null && carData.length == 3) {
                carNameTextView.setText("Car Name: " + carData[0]);
                carPriceTextView.setText("Car Price: " + carData[1]);
                carImageTextView.setText("Car Image URL: " + carData[2]);

                // Load image into ImageView using Glide
                Glide.with(CarDetailsActivity.this)
                        .load(carData[2]) // Image URL
                        .into(carImageView);
            }
        }
    }
}
