package edu.neu.madcourse.numad21fa_sihengwei;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {

    private ArrayList<ItemLink> itemLinks = new ArrayList<>();

    private RecyclerView recyclerView;
    private RviewAdapter rviewAdapter;
    private FloatingActionButton addButton;
    private RecyclerView.LayoutManager rLayoutManager;

    private View.OnClickListener addItemClickListener;

    private static final String KEY_OF_INSTANCE = "KEY_OF_INSTANCE";
    private static final String NUMBER_OF_ITEMS = "NUMBER_OF_ITEMS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        initialItemData(savedInstanceState);
        createRecyclerView();

        addButton = findViewById(R.id.fab_addLink);
        //show dialog and add item
        addItemClickListener = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int position = 0;
                addItem(position);
            }
        };
        addButton.setOnClickListener(addItemClickListener);
    }


    // Handling Orientation Changes on Android
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        int size = itemLinks == null ? 0 : itemLinks.size();
        outState.putInt(NUMBER_OF_ITEMS, size);

        for(int i = 0; i < size; i++){
            outState.putString(KEY_OF_INSTANCE + i + "0", itemLinks.get(i).getLinkName());
            outState.putString(KEY_OF_INSTANCE + i + "1", itemLinks.get(i).getLinkUrl());
        }

        super.onSaveInstanceState(outState);
    }

    private void initialItemData(Bundle savedInstanceState) {
        if(savedInstanceState != null && savedInstanceState.containsKey(NUMBER_OF_ITEMS)){
            if(itemLinks == null || itemLinks.size() == 0){
                int size = savedInstanceState.getInt(NUMBER_OF_ITEMS);
                for(int i = 0; i < size; i++){
                    String linkName = savedInstanceState.getString(KEY_OF_INSTANCE + i + "0");
                    String linkUrl = savedInstanceState.getString(KEY_OF_INSTANCE + i + "1");
                    itemLinks.add(new ItemLink(linkName, linkUrl));
                }
            }
        }
    }

    private void createRecyclerView() {
        rLayoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.rv_link);
        recyclerView.setHasFixedSize(true);

        rviewAdapter = new RviewAdapter(itemLinks);
        ItemLinkClickListener itemLinkClickListener =
            new ItemLinkClickListener() {
              @Override
              public void onItemLinkClick(int position) {
                ItemLink itemLink = itemLinks.get(position);
                String linkUrl = itemLink.getLinkUrl();
                if(!linkUrl.startsWith("http://") && !linkUrl.startsWith("https://")){
                    linkUrl = "http://" + linkUrl;
                }
                Uri uri = Uri.parse(linkUrl);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
              }
            };
            rviewAdapter.setOnItemClickListener(itemLinkClickListener);
            recyclerView.setAdapter(rviewAdapter);
            recyclerView.setLayoutManager(rLayoutManager);


    }

    private void addItem(int position){
        View dialogView = View.inflate(this, R.layout.item_link_add, null);
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(RecyclerViewActivity.this);
        builder.setMessage(R.string.add_link)
                .setView(dialogView)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditText et_linkName = dialogView.findViewById(R.id.et_linkName);
                        EditText et_linkUrl = dialogView.findViewById(R.id.et_linkUrl);
                        String linkName = et_linkName.getText().toString();
                        String linkUrl = et_linkUrl.getText().toString();

                        if (Patterns.WEB_URL.matcher(linkUrl).matches()) {
                            itemLinks.add(position, new ItemLink(linkName, linkUrl));
                            Snackbar.make(recyclerView, "Add an item successfully!", Snackbar.LENGTH_LONG).show();
                            rviewAdapter.notifyItemInserted(position);
                        } else {
                            Snackbar.make(recyclerView, "Invalid Url, Failed to add!", Snackbar.LENGTH_LONG)
                                    .setAction("Redo", addItemClickListener).show();
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog

                    }
                });
        builder.create().show();

    }

}