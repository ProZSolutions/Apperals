package in.proz.apperals;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import in.proz.apperals.Adapter.AddCartAdapter;
import in.proz.apperals.Modal.CartModal;
import in.proz.apperals.SQLiteDatabase.AddToCart;

public class AddCartActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<CartModal> productList = new ArrayList<>();


    private EditText etProductName, etProductPrice, etProductQuantity,etProductColor,etProductSize;
    private Button btnAddProduct;
    private TextView tvTotal;
    private AddToCart dbHelper;
    private AddCartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cart);
        recyclerView = findViewById(R.id.recyclerView);

        etProductName = findViewById(R.id.etProductName);
        etProductPrice = findViewById(R.id.etProductPrice);
        etProductQuantity = findViewById(R.id.etProductQuantity);
        btnAddProduct = findViewById(R.id.btnAddProduct);
         etProductColor = findViewById(R.id.etProductColor);
        etProductSize = findViewById(R.id.etProductSize);
        tvTotal = findViewById(R.id.tvTotal);

        dbHelper = new AddToCart(this);

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etProductName.getText().toString();
                double price = Double.parseDouble(etProductPrice.getText().toString());
                int quantity = Integer.parseInt(etProductQuantity.getText().toString());
                String color =etProductColor.getText().toString();
                String size = etProductSize.getText().toString();

                boolean isInserted = dbHelper.addProduct(name, price, quantity,color,size);
                if (isInserted) {
                    Toast.makeText(AddCartActivity.this, "Product Added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddCartActivity.this, "Error Adding Product", Toast.LENGTH_SHORT).show();
                }
            }
        });


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AddCartAdapter(this, productList, new AddCartAdapter.OnProductChangeListener() {
            @Override
            public void onQuantityChange() {
                calculateTotal();
            }

            @Override
            public void onDeleteProduct(int productId) {
                dbHelper.deleteProduct(productId); // Add a delete method to your DatabaseHelper
                calculateTotal();
            }
        });
        recyclerView.setAdapter(adapter);

        loadProducts();
        calculateTotal();

    }
    private void loadProducts() {
        productList.clear();
        Cursor cursor = dbHelper.getAllProducts();
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String size = cursor.getString(cursor.getColumnIndex("size"));
                String color = cursor.getString(cursor.getColumnIndex("color"));
                double price = cursor.getDouble(cursor.getColumnIndex("price"));
                int quantity = cursor.getInt(cursor.getColumnIndex("quantity"));

                productList.add(new CartModal(id, name,color,size, price, quantity));
            } while (cursor.moveToNext());
        }
        cursor.close();
        adapter.notifyDataSetChanged();
    }


    private void calculateTotal() {
        double total = 0.0;
        for (CartModal product : productList) {
            total += product.getPrice() * product.getQuantity();
        }
        tvTotal.setText("Total: " + total);
    }
}
