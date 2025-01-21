package in.proz.apperals;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

import in.proz.apperals.Adapter.HorizontalAdapter;
import in.proz.apperals.Adapter.ProductAdapter;
import in.proz.apperals.Adapter.SliderAdapter;
import in.proz.apperals.BottomNavigation.BottomNavigationHandler;
import in.proz.apperals.Modal.ImageSlider;
import in.proz.apperals.Modal.ProductModal;


public class HomeScreen extends AppCompatActivity {
    private ProductAdapter productAdapter;
    private List<ProductModal> productModalList;
    LinearLayout bottom_navigation;
    RecyclerView horizontalRV,popularRv;
    ViewPager2 viewPager;
    private Handler sliderHandler;
    private int currentPage = 0;
    List<ImageSlider> imageList =new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        initView();

        // Initialize the handler and set up the navigation

    }

    private void initView() {
        sliderHandler = new Handler(Looper.getMainLooper());
        horizontalRV = findViewById(R.id.horizontalRV);
        popularRv = findViewById(R.id.popularRv);

        GridLayoutManager manager = new GridLayoutManager(getApplicationContext(), 2);
        popularRv.setLayoutManager(manager);

        bottom_navigation = findViewById(R.id.bottom_navigation);
        BottomNavigationHandler navigationHandler = new BottomNavigationHandler(this);
        navigationHandler.setupNavigation(bottom_navigation);

        // top home
        List<String> data = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            data.add("Item " + i);
        }
        HorizontalAdapter adapter = new HorizontalAdapter(this, data);
        horizontalRV.setAdapter(adapter);

        viewPager = findViewById(R.id.viewPager);
         imageList = new ArrayList<>();
        imageList.add(new ImageSlider(R.drawable.banner_1));
        imageList.add(new ImageSlider(R.drawable.banner_2));
        SliderAdapter adapter1 = new SliderAdapter(imageList);
        viewPager.setAdapter(adapter1);
        autoSlideImages();


        productModalList = new ArrayList<>();
        productModalList.add(new ProductModal(R.drawable.long_banner, "Women's Ath Runner Zip Neck Tulip Wood", "4 colors", "₹1,550.00", "₹2,999.00", "SAVE 48%"));
        productModalList.add(new ProductModal(R.drawable.long_banner, "Men's Jacket Blue", "3 colors", "₹2,000.00", "₹3,500.00", "SAVE 43%"));

        // Set the adapter
        productAdapter = new ProductAdapter(productModalList);
        popularRv.setAdapter(productAdapter);

    }
    private void autoSlideImages() {
        sliderHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (currentPage == imageList.size()) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
                sliderHandler.postDelayed(this, 3000);
            }
        }, 3000);
    }
    @Override
    protected void onPause() {
        super.onPause();
        sliderHandler.removeCallbacksAndMessages(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        autoSlideImages();
    }
}
