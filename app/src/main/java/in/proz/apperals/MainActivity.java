package in.proz.apperals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.github.chrisbanes.photoview.PhotoView;

import java.util.Arrays;
import java.util.List;

import in.proz.apperals.ProductImageView.ThumbnailAdapter;

public class MainActivity extends AppCompatActivity {
    private PhotoView mainImageView;
    private RecyclerView recyclerView;
    TextView cart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cart = findViewById(R.id.cart);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(), AddCartActivity.class);
                startActivity(intent);
            }
        });
        mainImageView = findViewById(R.id.mainImageView);
        recyclerView = findViewById(R.id.recyclerView);

        // Sample image list
        List<Integer> imageList = Arrays.asList(
                R.drawable.image1,
                R.drawable.image2,
                R.drawable.image3);

        // Set the first image as the main image
        mainImageView.setImageResource(imageList.get(0));

        // Set up RecyclerView
        ThumbnailAdapter adapter = new ThumbnailAdapter(this, imageList, imageRes -> {
            // Update the main image when a thumbnail is clicked
            mainImageView.setImageResource(imageRes);
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

    }
}