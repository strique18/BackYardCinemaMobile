package me.ervinforth.barkyardcinema;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class Home extends AppCompatActivity {

    private ArrayList<Movie> nowShowingMovies = new ArrayList<>();
    private MovieAdapter nowShowingAdapter = new MovieAdapter(nowShowingMovies);

    // Access a Cloud Firestore instance from your Activity
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        AndroidNetworking.initialize(getApplicationContext());
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL, false);

        RecyclerView mNowShowingRecycler = findViewById(R.id.mNowShowingRecycler);
        mNowShowingRecycler.setLayoutManager(manager);
        mNowShowingRecycler.setAdapter(nowShowingAdapter);

        loadDataFromFireStore();
    }

    private void loadDataFromFireStore() {
        db.collection("movies").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    QuerySnapshot documents = task.getResult();
                    nowShowingMovies.clear();

                    for (QueryDocumentSnapshot document : documents)  {
                        String docID = document.getId();
                        HashMap<String, Object> movieMap = (HashMap<String, Object>) document.getData();
                        movieMap.put("id", docID);
                        Movie m = Movie.fromMapToMovie(movieMap);
                        Log.i("loadDataFromFireStore", m.toString());

                        // add a movie to the list only if the availability property is "now showing"
                        if (m.getAvailability().equals("now showing")) {
                            nowShowingMovies.add(m);
                            // notify the adapter that the list has changed
                            nowShowingAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        });
    }
}