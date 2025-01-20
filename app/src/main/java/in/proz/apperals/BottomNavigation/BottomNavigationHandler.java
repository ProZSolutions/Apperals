package in.proz.apperals.BottomNavigation;


import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import in.proz.apperals.R;

public class BottomNavigationHandler {

    private Context context;
    private LinearLayout navHome, navCart, navShop,navWish,navAcc;
    private ImageView home_icon, cart_icon, shop_icon,wish_icon,icon_acc;
    private TextView home_text, cart_text, shop_text,wish_text,acc_text;

    public BottomNavigationHandler(Context context) {
        this.context = context;
    }

    public void setupNavigation(LinearLayout bottomNavigationView) {
        // Initialize views
        navHome = bottomNavigationView.findViewById(R.id.nav_home);
        navCart = bottomNavigationView.findViewById(R.id.nav_cart);
        navShop = bottomNavigationView.findViewById(R.id.nav_shop);
        navWish = bottomNavigationView.findViewById(R.id.nav_wishlist);
        navAcc = bottomNavigationView.findViewById(R.id.nav_acc);
        home_icon = bottomNavigationView.findViewById(R.id.home_icon);
        cart_icon = bottomNavigationView.findViewById(R.id.cart_icon);
        shop_icon = bottomNavigationView.findViewById(R.id.shop_icon);
        wish_icon = bottomNavigationView.findViewById(R.id.wish_icon);
        icon_acc = bottomNavigationView.findViewById(R.id.icon_acc);
        home_text = bottomNavigationView.findViewById(R.id.home_text);
        cart_text = bottomNavigationView.findViewById(R.id.cart_text);
        shop_text = bottomNavigationView.findViewById(R.id.shop_text);
        wish_text = bottomNavigationView.findViewById(R.id.wish_text);
        acc_text = bottomNavigationView.findViewById(R.id.acc_text);

        // Set click listeners for navigation items
        navHome.setOnClickListener(this::onHomeClick);
        navShop.setOnClickListener(this::onSearchClick);
        navCart.setOnClickListener(this::onProfileClick);
        navAcc.setOnClickListener(this::onProfileClick);
        navWish.setOnClickListener(this::onProfileClick);
    }

    private void onHomeClick(View v) {
        setActiveTab(R.id.nav_home);
        Toast.makeText(context, "Home clicked", Toast.LENGTH_SHORT).show();
    }

    private void onSearchClick(View v) {
        setActiveTab(R.id.nav_search);
        Toast.makeText(context, "Search clicked", Toast.LENGTH_SHORT).show();
    }

    private void onProfileClick(View v) {
        setActiveTab(R.id.nav_profile);
        Toast.makeText(context, "Profile clicked", Toast.LENGTH_SHORT).show();
    }

    private void setActiveTab(int activeTabId) {
        // Reset all icons to their default state
        home_icon.setColorFilter(context.getResources().getColor(R.color.secondaryColor));
        wish_icon.setColorFilter(context.getResources().getColor(R.color.secondaryColor));
        icon_acc.setColorFilter(context.getResources().getColor(R.color.secondaryColor));
        cart_icon.setColorFilter(context.getResources().getColor(R.color.secondaryColor));
        shop_icon.setColorFilter(context.getResources().getColor(R.color.secondaryColor));
        home_text.setTextColor(context.getResources().getColor(R.color.secondaryColor));
        shop_text.setTextColor(context.getResources().getColor(R.color.secondaryColor));
        cart_text.setTextColor(context.getResources().getColor(R.color.secondaryColor));
        wish_text.setTextColor(context.getResources().getColor(R.color.secondaryColor));
        acc_text.setTextColor(context.getResources().getColor(R.color.secondaryColor));

        // Set the active tab's icon color
        if (activeTabId == R.id.nav_home) {
            home_text.setTextColor(context.getResources().getColor(R.color.primaryColor));
            home_icon.setColorFilter(context.getResources().getColor(R.color.primaryColor));
        } else if (activeTabId == R.id.nav_cart) {
            cart_text.setTextColor(context.getResources().getColor(R.color.primaryColor));
            cart_icon.setColorFilter(context.getResources().getColor(R.color.primaryColor));
        } else if (activeTabId == R.id.nav_shop) {
            shop_text.setTextColor(context.getResources().getColor(R.color.primaryColor));
            shop_icon.setColorFilter(context.getResources().getColor(R.color.primaryColor));
        }
        else if (activeTabId == R.id.nav_wishlist) {
            wish_text.setTextColor(context.getResources().getColor(R.color.primaryColor));
            wish_icon.setColorFilter(context.getResources().getColor(R.color.primaryColor));
        }
        else if (activeTabId == R.id.nav_acc) {
            acc_text.setTextColor(context.getResources().getColor(R.color.primaryColor));
            icon_acc.setColorFilter(context.getResources().getColor(R.color.primaryColor));
        }
    }
}
