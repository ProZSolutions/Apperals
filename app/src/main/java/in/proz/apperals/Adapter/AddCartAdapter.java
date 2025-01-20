package in.proz.apperals.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.proz.apperals.Modal.CartModal;
import in.proz.apperals.R;

public class AddCartAdapter extends RecyclerView.Adapter<AddCartAdapter.ProductViewHolder> {

    private Context context;
    private List<CartModal> productList;
    private OnProductChangeListener listener;

    public interface OnProductChangeListener {
        void onQuantityChange();
        void onDeleteProduct(int productId);
    }

    public AddCartAdapter(Context context, List<CartModal> productList, OnProductChangeListener listener) {
        this.context = context;
        this.productList = productList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        CartModal product = productList.get(position);

        holder.tvName.setText(product.getName());
        holder.tvPrice.setText("Price: " + product.getPrice());
        holder.tvQuantity.setText("Quantity: " + product.getQuantity());

        holder.btnIncrease.setOnClickListener(v -> {
            product.setQuantity(product.getQuantity() + 1);
            notifyItemChanged(position);
            listener.onQuantityChange();
        });

        holder.btnDecrease.setOnClickListener(v -> {
            if (product.getQuantity() > 1) {
                product.setQuantity(product.getQuantity() - 1);
                notifyItemChanged(position);
                listener.onQuantityChange();
            }
        });

        holder.btnDelete.setOnClickListener(v -> {
            productList.remove(position);
            notifyItemRemoved(position);
            listener.onDeleteProduct(product.getId());
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPrice, tvQuantity;
        TextView btnIncrease, btnDecrease, btnDelete;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            btnIncrease = itemView.findViewById(R.id.btnIncrease);
            btnDecrease = itemView.findViewById(R.id.btnDecrease);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
