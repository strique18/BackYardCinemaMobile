package me.ervinforth.barkyardcinema;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MovieDetailsFragment extends Fragment {

    private ImageView moviePoster;
    private TextView movieTitle;
    private TextView moviePlot;
    private Button purchaseTicketBtn;

    public MovieDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        moviePoster = view.findViewById(R.id.movie_image);
        movieTitle = view.findViewById(R.id.movie_title);
        moviePlot = view.findViewById(R.id.movie_plot);
        purchaseTicketBtn = view.findViewById(R.id.buy_ticket_btn);

        SharedViewModel model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        model.getSelected().observe(getViewLifecycleOwner(), item -> {
            Log.i("selectedMovie", item.toString());
            moviePlot.setText(item.getPlot());
            movieTitle.setText(item.getTitle());
            Glide.with(requireContext()).load(item.getPoster()).into(moviePoster);
        });


        purchaseTicketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_movieDetailsFragment_to_payTicketDialogFragment);
            }
        });

    }
}