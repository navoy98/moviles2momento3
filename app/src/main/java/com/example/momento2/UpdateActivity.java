package com.example.momento2;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import com.example.momento2.models.ShoesModels;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;

public class UpdateActivity extends BaseActivity {

    FloatingActionButton fab_update_save, fab_update_clear, fab_update_back;
    ImageView iv_update_image;
    EditText et_update_brand, et_update_size, et_update_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        super.init();
        init();

        fab_update_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToDetail(model);
            }
        });

        fab_update_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();
            }
        });

        fab_update_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String brand, size, description;
                boolean active;
                brand = et_update_brand.getText().toString();
                size = et_update_size.getText().toString();
                description = et_update_description.getText().toString();

                if (brand.isEmpty() || size.isEmpty() || description.isEmpty()){
                    makeSimpleAlertDialog("Info", "Please fill all fields");
                }else{
                    model = new ShoesModels();
                    model.setActive(true);
                    model.setDescription(description);
                    model.setBrand(brand);
                    model.setSize(size);

                    save(model);
                }
            }
        });
    }

    private void save(ShoesModels model) {
        if (collectionReference != null){
            collectionReference.add(model)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if (task.isSuccessful()){
                                if (task.getResult() != null){
                                    makeSimpleAlertDialog("Success", "Shoe saved");
                                    clear();
                                }else{
                                    makeSimpleAlertDialog("Warning", "Shoe not saved");
                                }
                            }else{
                                makeSimpleAlertDialog("Error", task.getException().getMessage());
                            }
                        }
                    });

        }else{
            makeSimpleAlertDialog("Error", "Not database connection");
        }
    }

    protected void init(){
        fab_update_back = findViewById(R.id.fab_update_back);
        fab_update_save = findViewById(R.id.fab_update_save);
        fab_update_clear = findViewById(R.id.fab_update_clear);
        iv_update_image = findViewById(R.id.iv_update_image);
        et_update_brand = findViewById(R.id.et_update_brand);
        et_update_size = findViewById(R.id.et_update_size);
        et_update_description = findViewById(R.id.et_update_description);
    }

    private void clear(){
        et_update_brand.setText("");
        et_update_description.setText("");
        et_update_size.setText("");

        et_update_brand.requestFocus();

        iv_update_image.setImageResource(R.drawable.ic_running_shoe);
    }
}