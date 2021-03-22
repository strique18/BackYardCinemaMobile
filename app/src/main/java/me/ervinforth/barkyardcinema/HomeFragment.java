package me.ervinforth.barkyardcinema;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeFragment extends Fragment {

    final private ArrayList<Movie> nowShowingMovies = new ArrayList<>();
    final private ArrayList<Movie> comingSoonMovies = new ArrayList<>();
    private MovieAdapter nowShowingAdapter;
    private MovieAdapter comingSoonAdapter;
    RecyclerView comingSoonRecycler;
    private SharedViewModel model;

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

        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        nowShowingAdapter = new MovieAdapter(nowShowingMovies, model);
        comingSoonAdapter = new MovieAdapter(comingSoonMovies, model);

        RecyclerView mNowShowingRecycler = view.findViewById(R.id.mNowShowingRecycler);
        mNowShowingRecycler.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        mNowShowingRecycler.setAdapter(nowShowingAdapter);

        RecyclerView comingSoonRecycler = view.findViewById(R.id.mComingSoonRecycler);
        comingSoonRecycler.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        comingSoonRecycler.setAdapter(comingSoonAdapter);

        loadDataFromFireStore();
    }

    private void loadDataFromFireStore() {
        db.collection("movies").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    QuerySnapshot documents = task.getResult();
                    nowShowingMovies.clear();
                    comingSoonMovies.clear();

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
                        } else {
                            comingSoonMovies.add(m);
                            comingSoonAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        });
    }
}