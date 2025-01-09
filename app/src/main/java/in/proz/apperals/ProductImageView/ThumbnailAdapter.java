package in.proz.apperals.ProductImageView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import in.proz.apperals.R;

public class ThumbnailAdapter extends RecyclerView.Adapter<ThumbnailAdapter.ViewHolder> {

    private List<Integer> imageList;
    private Context context;
    private OnImageClickListener listener;

    public ThumbnailAdapter(Context context, List<Integer> imageList, OnImageClickListener listener) {
        this.context = context;
        this.imageList = imageList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_thumbnail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int imageRes = imageList.get(position);
        holder.thumbnailImageView.setImageResource(imageRes);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onImageClick(imageRes);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnailImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnailImageView = itemView.findViewById(R.id.thumbnailImageView);
        }
    }

    public interface OnImageClickListener {
        void onImageClick(int imageRes);
    }
}

