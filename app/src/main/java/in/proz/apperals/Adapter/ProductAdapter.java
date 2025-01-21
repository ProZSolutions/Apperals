package in.proz.apperals.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.proz.apperals.Modal.ProductModal;
import in.proz.apperals.R;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<ProductModal> productList;

    public ProductAdapter(List<ProductModal> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductModal product = productList.get(position);
        holder.imageProduct.setImageResource(product.getImageResource());
        holder.productName.setText(product.getName());
        holder.productColors.setText(product.getColors());
        holder.productPrice.setText(product.getPrice());
        holder.originalPrice.setText(product.getOriginalPrice());
        holder.discountTag.setText(product.getDiscount());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imageProduct, favoriteIcon;
        TextView productName, productColors, productPrice, originalPrice, discountTag;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imageProduct = itemView.findViewById(R.id.imageProduct);
            favoriteIcon = itemView.findViewById(R.id.favoriteIcon);
            productName = itemView.findViewById(R.id.productName);
            productColors = itemView.findViewById(R.id.productColors);
            productPrice = itemView.findViewById(R.id.productPrice);
            originalPrice = itemView.findViewById(R.id.originalPrice);
            discountTag = itemView.findViewById(R.id.discountTag);
        }
    }
}

