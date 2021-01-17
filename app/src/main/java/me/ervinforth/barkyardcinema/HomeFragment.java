package me.ervinforth.barkyardcinema;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeFragment extends Fragment {

    final private ArrayList<Movie> nowShowingMovies = new ArrayList<>();
    final private MovieAdapter nowShowingAdapter = new MovieAdapter(nowShowingMovies);
    // Access a Cloud Firestore instance from your Activity
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false);

        RecyclerView mNowShowingRecycler = view.findViewById(R.id.mNowShowingRecycler);
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